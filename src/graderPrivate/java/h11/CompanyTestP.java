package h11;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertFalse;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;

@TestForSubmission
public class CompanyTestP extends H11_TestP {


    @ParameterizedTest
    @JsonParameterSetTest(value = "getListOfAllEmployee.json", customConverters = "customConverters")
    public void testGetListOfAllEmployee(final JsonParameterSet params) {
        List<Employee> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toEmployee);

        Company company = params.get("company");
        List<Employee> actual = company.getListOfAllEmployee();

        assertContainsAll(expected, actual, params.toContext("mocked", "arguments"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getQuantityOfProduct.json", customConverters = "customConverters")
    public void testGetQuantityOfProduct(final JsonParameterSet params) {
        long expected = params.getRootNode().get("expected").asLong();

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        Product product = JsonConverter.toList(params.getRootNode().get("arguments"), JsonConverter::toProduct).get(0);
        long actual = company.getQuantityOfProduct(product);

        assertEquals(
            expected,
            actual,
            params.toContext("mocked"),
            r -> "The returned amount of Products does not match the expected."
        );
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getFilteredProductNames_single.json", customConverters = "customConverters")
    public void testGetFilteredProductNames_single(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        List<Predicate<Product>> predicates = JsonConverter.toList(
            params.getRootNode().get("arguments").get(0),
            jsonNode -> ProductPredicate.valueOf(jsonNode.asText()).getPredicate()
        );
        List<String> actual = company.getFilteredProductNames(predicates);

        assertListEquals(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getFilteredProductNames_multiple.json", customConverters = "customConverters")
    public void testGetFilteredProductNames_multiple(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doAnswer(invocationOnMock ->
                mocked.products.stream()
                    .filter(invocationOnMock.getArgument(0))
                    .toList()
            )
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        List<Predicate<Product>> predicates = JsonConverter.toList(
            params.getRootNode().get("arguments").get(0),
            jsonNode -> ProductPredicate.valueOf(jsonNode.asText()).getPredicate()
        );
        List<String> actual = company.getFilteredProductNames(predicates);

        assertListEquals(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getFilteredProductNames_none.json", customConverters = "customConverters")
    public void testGetFilteredProductNames_none(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doAnswer(invocationOnMock ->
                mocked.products.stream()
                    .filter(invocationOnMock.getArgument(0))
                    .toList()
            )
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        List<Predicate<Product>> predicates = JsonConverter.toList(
            params.getRootNode().get("arguments").get(0),
            jsonNode -> ProductPredicate.valueOf(jsonNode.asText()).getPredicate()
        );
        List<String> actual = company.getFilteredProductNames(predicates);

        assertListEquals(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "priceRange.json", customConverters = "customConverters")
    public void testPriceRange_filter(final JsonParameterSet params) {
        List<Product> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toProduct);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        double low = JsonConverter.toList(params.getRootNode().get("arguments"), JsonNode::asDouble).get(0);
        double high = JsonConverter.toList(params.getRootNode().get("arguments"), JsonNode::asDouble).get(1);
        List<Product> actual = company.priceRange(low, high);

        assertContainsAll(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "priceRange.json", customConverters = "customConverters")
    public void testPriceRange_sorted(final JsonParameterSet params) {
        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        double low = JsonConverter.toList(params.getRootNode().get("arguments"), JsonNode::asDouble).get(0);
        double high = JsonConverter.toList(params.getRootNode().get("arguments"), JsonNode::asDouble).get(1);
        List<Product> actual = company.priceRange(low, high);

        double highest = Double.MIN_VALUE;
        for (int i = 0; i < actual.size(); i++) {
            if (actual.get(i).price() < highest) {
                Context context = contextBuilder()
                    .add(params.toContext("mocked", "expected"))
                    .add("actual", actual)
                    .build();
                fail(context, r -> "The returned List of Products is not correctly sorted");
            }
            highest = actual.get(i).price();
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getEmployeesSortedByName.json", customConverters = "customConverters")
    public void testGetEmployeesSortedByName(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");
        company = spy(company);
        when(company.getListOfAllEmployee()).thenReturn(JsonConverter.toList(
            params.getRootNode().get("mocked"),
            JsonConverter::toEmployee
        ));

        List<String> actual = company.getEmployeesSortedByName();

        Context context = contextBuilder()
            .add(params.toContext("mocked"))
            .add("actual", actual)
            .build();

        assertEquals(
            expected.size(),
            actual.size(),
            context,
            r -> "Returned list does not contain the expected amount of entries."
        );

        boolean correctlySorted = true;
        String last = null;
        for (String current : actual) {
            if (last == null) {
                last = current;
                continue;
            }
            if (last.compareTo(current) >= 1) {
                correctlySorted = false;
                break;
            }
        }

        assertTrue(correctlySorted, context, r -> "The names ot the employees are not correctly sorted.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getEmployeesSortedByName.json", customConverters = "customConverters")
    public void testGetEmployeesSortedByName_formatting(final JsonParameterSet params) {
        Company company = params.get("company");
        company = spy(company);

        when(company.getListOfAllEmployee()).thenReturn(JsonConverter.toList(
            params.getRootNode().get("mocked"),
            JsonConverter::toEmployee
        ));

        List<String> actual = company.getEmployeesSortedByName();

        assertFalse(actual.isEmpty(), params.toContext("mocked"), r -> "getEmployeesSortedByName() did not return any names.");

        boolean correctlyFormatted = actual.stream()
            .allMatch(name -> name.matches("[a-zA-Z]+, [a-zA-Z]+"));

        Context context = contextBuilder()
            .add(params.toContext("mocked"))
            .add("actual", actual)
            .build();

        assertTrue(correctlyFormatted, context, r -> "The names ot the employees are not correctly formatted.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getAllProductsByType.json", customConverters = "customConverters")
    public void testGetAllProductsByType_numberItems(final JsonParameterSet params) {
        int expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText).size();

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        int mockNum = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).products.isEmpty()) {
                continue;
            }
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct)
                .subList(mockNum, ++mockNum))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        ProductType type = JsonConverter.toProductType(params.getRootNode().get("arguments").get(0));
        int amount = params.getRootNode().get("arguments").get(1).asInt();
        int actual = company.getAllProductsByType(type, amount).size();

        assertEquals(
            expected,
            actual,
            params.toContext("mocked"),
            r -> "The returned amount of Items does not match the expected."
        );
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getAllProductsByType_correctItems.json", customConverters = "customConverters")
    public void testGetAllProductsByType_correctItems(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        int mockNum = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).products.isEmpty()) {
                continue;
            }
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct)
                .subList(mockNum, ++mockNum))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        ProductType type = JsonConverter.toProductType(params.getRootNode().get("arguments").get(0));
        int amount = params.getRootNode().get("arguments").get(1).asInt();
        List<String> actual = company.getAllProductsByType(type, amount);

        Pattern pattern = Pattern.compile(expected.stream().map(str -> "(.*(" + str + ").*)").collect(Collectors.joining("|")));

        for (int i = 0; i < Math.min(expected.size(), actual.size()); i++) {
            int finalI = i;
            assertTrue(
                pattern.matcher(actual.get(i)).matches(),
                contextBuilder()
                    .add(params.toContext("mocked"))
                    .add("actual", actual.get(i))
                    .build(),
                r -> "The returned List of Items does not contain any expected item at position %d.".formatted(finalI)
            );
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getAllProductsByType.json", customConverters = "customConverters")
    public void testGetAllProductsByType_sorted(final JsonParameterSet params) {
        List<String> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonNode::asText);

        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        int mockNum = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).products.isEmpty()) {
                continue;
            }
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct)
                .subList(mockNum, ++mockNum))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        ProductType type = JsonConverter.toProductType(params.getRootNode().get("arguments").get(0));
        int amount = params.getRootNode().get("arguments").get(1).asInt();
        List<String> actual = company.getAllProductsByType(type, amount);

        for (int i = 0; i < Math.min(expected.size(), actual.size()); i++) {
            int finalI = i;

            assertTrue(
                actual.get(i).contains(expected.get(i).split(": ")[0]) || actual.get(i)
                    .contains(expected.get(i).split(": ")[1].replace("€", "")),
                contextBuilder()
                    .add(params.toContext("mocked"))
                    .add("actual", actual)
                    .build(),
                r -> "The returned List of Items does not have the correct item at position %d.".formatted(finalI)
            );
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getAllProductsByType.json", customConverters = "customConverters")
    public void testGetAllProductsByType_formatting(final JsonParameterSet params) {
        Company company = params.get("company");

        List<Warehouse> warehouses = company.warehouses();
        int mockNum = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).products.isEmpty()) {
                continue;
            }
            Warehouse mocked = spy(warehouses.get(i));
            doReturn(JsonConverter.toList(params.getRootNode().get("mocked"), JsonConverter::toProduct)
                .subList(mockNum, ++mockNum))
                .when(mocked)
                .getProducts(any());
            warehouses.set(i, mocked);
        }

        ProductType type = JsonConverter.toProductType(params.getRootNode().get("arguments").get(0));
        int amount = params.getRootNode().get("arguments").get(1).asInt();
        List<String> actual = company.getAllProductsByType(type, amount);

        Pattern pattern = Pattern.compile("\\D+: [+-]?\\d+([.,]\\d+)?€?");

        for (int i = 0; i < actual.size(); i++) {
            int finalI = i;
            assertTrue(
                pattern.matcher(actual.get(i)).matches(),
                contextBuilder()
                    .add(params.toContext("mocked", "expected"))
                    .add("actual", actual.get(i))
                    .build(),
                r -> "The returned List of Items does not have the correct formatting at position %d.".formatted(finalI)
            );
        }
    }

    @Test
    public void testGetListOfAllEmployee_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getListOfAllEmployee")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetQuantityOfProduct_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getQuantityOfProduct")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetFilteredProductNames_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getFilteredProductNames")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testPriceRange_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("priceRange")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetEmployeesSortedByName_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getEmployeesSortedByName")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetAllProductsByType_va() {
        Method method = BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getAllProductsByType")).reflection();
        assertNoLoopOrRecursion(method);
    }

    public enum ProductPredicate {

        NAME_STARTS_WITH_R(p -> p.name().startsWith("R")),
        NAME_CONTAINS_DIGIT(p -> p.name().matches(".*\\d.*")),
        IS_HARDWARE(p -> p.type() == ProductType.Hardware),
        IS_SOFTWARE(p -> p.type() == ProductType.Software),
        PRICE_OVER_50(p -> p.price() > 50),
        PRICE_OVER_200(p -> p.price() > 200);

        private Predicate<Product> predicate;

        ProductPredicate(Predicate<Product> predicate) {
            this.predicate = predicate;
        }

        public Predicate<Product> getPredicate() {
            return predicate;
        }
    }
}
