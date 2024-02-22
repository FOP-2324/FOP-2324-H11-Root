package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.cartesian.CartesianTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static h11.TestObjects.products;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertFalse;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class WarehouseTestP extends H11_TestP {

    @ParameterizedTest
    @JsonParameterSetTest(value = "getPrice_null.json", customConverters = "customConverters")
    public void testGetPrice_null(final JsonParameterSet params) {
        double expected = params.getRootNode().get("expected").asDouble();

        Warehouse warehouse = params.get("warehouse");
        double actual = warehouse.getPrice(JsonConverter.toProduct(params.getRootNode().get("arguments").get(0)));

        assertEquals(expected, actual, params.toContext("mocked", "expected"), r -> "The returned price does not match the expected");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getPrice_nonNull.json", customConverters = "customConverters")
    public void testGetPrice_nonNull(final JsonParameterSet params) {
        double expected = params.getRootNode().get("expected").asDouble();

        Warehouse warehouse = params.get("warehouse");
        double actual = warehouse.getPrice(JsonConverter.toProduct(params.getRootNode().get("arguments").get(0)));

        assertEquals(expected, actual, params.toContext("mocked", "expected"), r -> "The returned price does not match the expected");
    }

    @Test
    public void testGetPrice_va() {
        BasicMethodLink link =
            (BasicMethodLink) BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getPrice"));
        CtMethod<?> ctMethod = link.getCtElement();

        List<CtIf> ifStatements = ctMethod.filterChildren(new TypeFilter<>(CtIf.class)).list();

        assertEquals(0, ifStatements.size(), emptyContext(), r -> "Unexpected number of If-Statements found.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getProducts.json", customConverters = "customConverters")
    public void testGetProducts(final JsonParameterSet params) {
        List<Product> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toProduct);

        Warehouse warehouse = params.get("warehouse");
        List<Product> actual = warehouse.getProducts(CompanyTestP.ProductPredicate.valueOf(params.getRootNode().get("arguments").get(0).asText()).getPredicate());

        assertListEquals(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getTotalQuantityOfProduct.json", customConverters = "customConverters")
    public void testGetTotalQuantityOfProduct(final JsonParameterSet params) {
        long expected = params.getRootNode().get("expected").asLong();

        Warehouse warehouse = params.get("warehouse");
        long actual = warehouse.getTotalQuantityOfProduct(JsonConverter.toProduct(params.getRootNode().get("arguments").get(0)));

        assertEquals(expected, actual, params.toContext("mocked"), r -> "The returned number of Products does not match the expected number.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getTotalPrice.json", customConverters = "customConverters")
    public void testGetTotalPrice(final JsonParameterSet params) {
        double expected = params.getRootNode().get("expected").asDouble();

        Warehouse warehouse = params.get("warehouse");
        double actual = warehouse.getTotalPrice();

        assertEquals(expected, actual, params.toContext("mocked"), r -> "The returned total Price does not match the the expected total.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "addProducts.json", customConverters = "customConverters")
    public void testAddProducts(final JsonParameterSet params) {
        Warehouse expected = JsonConverter.toWarehouse(params.getRootNode().get("expected"));

        Warehouse warehouse = params.get("warehouse");
        warehouse.addProducts(JsonConverter.toProduct(params.getRootNode().get("arguments").get(0)), params.getRootNode().get("arguments").get(1).asInt());

        assertEquals(expected, warehouse, params.toContext("mocked"), r -> "Products were not added correctly.");
    }

    @Test
    public void testAddProducts_containsExact() {
        Warehouse warehouse = mock(Warehouse.class, CALLS_REAL_METHODS);
        warehouse.products = new ArrayList<>();

        List<Product> generatedProducts = new ArrayList<>();

        doAnswer(invocation ->
            Stream.generate(() -> {
                Product product = new Product(invocation.getArgument(0), invocation.getArgument(1), 1, invocation.getArgument(2));
                generatedProducts.add(product);
                return product;
            })
        ).when(warehouse).generateProducts(any(), anyDouble(), anyString());

        warehouse.addProducts(products.get(0), 5);


        assertFalse(warehouse.products.isEmpty(), emptyContext(), r -> "Warehouse does not contain any new Products");
        verify(warehouse, atLeastOnce()).generateProducts(any(), anyDouble(), anyString());

        boolean containsCorrectValues = warehouse.products.stream()
            .allMatch(product ->
               generatedProducts.stream().anyMatch(generated -> product == generated)
            );

        assertTrue(containsCorrectValues, emptyContext(), r -> "Warehouse does not contain products generated by generateProducts()");
    }

    @Test
    public void testGenerateProducts() {
        Stream<Product> products = new Warehouse(List.of()).generateProducts(ProductType.Hardware, 0.0, "Product Name");
        assertFalse(products.spliterator().estimateSize() < Long.MAX_VALUE, emptyContext(), r -> "Stream should have been infinite but was not.");
    }

    @CartesianTest
    public void testGenerateProducts(
        @CartesianTest.Enum ProductType type,
        @CartesianTest.Values(doubles = {0, 1, 5, 10}) double price,
        @CartesianTest.Values(strings = {"Test", "product1", "Product Name"}) String name
    ) {
        Context context = contextBuilder()
            .add("type", type)
            .add("price", price)
            .add("name", name)
            .build();

        Supplier<Stream<Product>> products = () -> new Warehouse(List.of()).generateProducts(type, price, name);
        products.get().limit(10).forEach(product -> {
            assertEquals(name, product.name(), context, r -> "The name of the generated Product does not match.");
            assertEquals(price, product.price(), context, r -> "The price of the generated Product does not match.");
            assertEquals(type, product.type(), context, r -> "The type of the generated Product does not match.");
        });
        List<Product> productList = products.get().limit(10).toList();
        Set<Integer> productsIdentity = productList.stream().map(System::identityHashCode).collect(Collectors.toSet());
        assertEquals(productList.size(), productsIdentity.size(), context, r -> "The returned Stream does not contain individually created new Products");
    }

    @Test
    public void testGetPrice_generalVa() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getPrice")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testSetMaxCapacity_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("setMaxCapacity")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetTotalQuantityOfProduct_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getTotalQuantityOfProduct")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetTotalPrice_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getTotalPrice")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testAddProducts_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("addProducts")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGenerateProducts_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("generateProducts")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetProducts_va() {
        Method method = BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getProducts")).reflection();
        assertNoLoopOrRecursion(method);
    }
}
