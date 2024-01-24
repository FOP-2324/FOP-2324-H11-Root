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

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertFalse;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class WarehouseTest extends H11_Test {

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

    @Test
    public void testGetProducts() {

    }

    @Test
    public void testGetTotalQuantityOfProduct() {

    }

    @Test
    public void testGetTotalPrice() {

    }

    @Test
    public void testAddProducts() {

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
}
