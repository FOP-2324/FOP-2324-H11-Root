package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.util.List;

import static h11.CompanyTestP.ProductPredicate.IS_HARDWARE;
import static h11.CompanyTestP.ProductPredicate.IS_SOFTWARE;
import static h11.CompanyTestP.ProductPredicate.NAME_CONTAINS_DIGIT;
import static h11.CompanyTestP.ProductPredicate.NAME_STARTS_WITH_R;
import static h11.CompanyTestP.ProductPredicate.PRICE_OVER_200;
import static h11.CompanyTestP.ProductPredicate.PRICE_OVER_50;
import static h11.TestObjects.companies;
import static h11.TestObjects.departments;
import static h11.TestObjects.products;

public class CompanyTestGenerator extends TestGenerator {

    @Test
    public void generateGetListOfAllEmployee() throws Throwable {
        generateJson(
            "",
            List.of(
                companies.get(0),
                companies.get(1),
                companies.get(2)
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getListOfAllEmployee")),
            List.of(
                Arguments.arguments(),
                Arguments.arguments(),
                Arguments.arguments()
            )
        );
    }

    @Test
    public void generateGetQuantityOfProduct() throws Throwable {
        generateJson(
            "",
            List.of(
                companies.get(3),
                companies.get(4),
                companies.get(3)
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getQuantityOfProduct")),
            List.of(
                Arguments.arguments(products.get(0)),
                Arguments.arguments(products.get(0)),
                Arguments.arguments(products.get(1))
            ),
            List.of(
                List.of(
                    products.get(0)
                ),
                List.of(),
                List.of()
            )
        );
    }

    @Test
    public void generateGetFilteredProductNames_single() throws Throwable {
        generateJson("getFilteredProductNames", "single",
            List.of(
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6)
            ),
            List.of(
                companies.get(6).getFilteredProductNames(List.of(NAME_STARTS_WITH_R.getPredicate())),
                companies.get(6)
                    .getFilteredProductNames(List.of(NAME_CONTAINS_DIGIT.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(IS_HARDWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(IS_SOFTWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(PRICE_OVER_50.getPredicate()))
            ),
            List.of(
                Arguments.arguments(List.of(NAME_STARTS_WITH_R)),
                Arguments.arguments(List.of(NAME_CONTAINS_DIGIT)),
                Arguments.arguments(List.of(IS_HARDWARE)),
                Arguments.arguments(List.of(IS_SOFTWARE)),
                Arguments.arguments(List.of(PRICE_OVER_50))
            ),
            List.of(
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(NAME_STARTS_WITH_R.getPredicate())
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(NAME_CONTAINS_DIGIT.getPredicate())
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(IS_HARDWARE.getPredicate())
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(IS_SOFTWARE.getPredicate())
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(PRICE_OVER_50.getPredicate())
                    .toList()
            )
        );
    }

    @Test
    public void generateGetFilteredProductNames_multiple() throws Throwable {
        generateJson("getFilteredProductNames", "multiple",
            List.of(
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6)
            ),
            List.of(
                companies.get(6).getFilteredProductNames(List.of(NAME_STARTS_WITH_R.getPredicate(), IS_HARDWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(NAME_CONTAINS_DIGIT.getPredicate(), NAME_STARTS_WITH_R.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(NAME_CONTAINS_DIGIT.getPredicate(), NAME_STARTS_WITH_R.getPredicate(), PRICE_OVER_200.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(IS_HARDWARE.getPredicate(), IS_SOFTWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(PRICE_OVER_50.getPredicate(), IS_SOFTWARE.getPredicate()))
            ),
            List.of(
                Arguments.arguments(List.of(NAME_STARTS_WITH_R, IS_HARDWARE)),
                Arguments.arguments(List.of(NAME_CONTAINS_DIGIT, NAME_STARTS_WITH_R)),
                Arguments.arguments(List.of(NAME_CONTAINS_DIGIT, NAME_STARTS_WITH_R, PRICE_OVER_200)),
                Arguments.arguments(List.of(IS_HARDWARE, IS_SOFTWARE)),
                Arguments.arguments(List.of(PRICE_OVER_50, IS_SOFTWARE))
            )
        );
    }

    @Test
    public void generateGetFilteredProductNames_none() throws Throwable {
        generateJson("none",
            List.of(
                companies.get(6)
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getFilteredProductNames")),
            List.of(
                Arguments.arguments(List.of())
            )
        );
    }

    @Test
    public void generatePriceRange() throws Throwable {
        generateJson(
            "",
            List.of(
                companies.get(0),
                companies.get(3),
                companies.get(4),
                companies.get(5),
                companies.get(5)
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("priceRange")),
            List.of(
                Arguments.arguments(0.0, 1.0),
                Arguments.arguments(50.0, 3000.0),
                Arguments.arguments(50.0, 2000.0),
                Arguments.arguments(50.0, 2000.0),
                Arguments.arguments(50.0, 1999.0)
            ),
            List.of(
                List.of(),
                companies.get(0)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(3)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(4)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList()
            )
        );
    }

    @Test
    public void generateGetEmployeesSortedByName() throws Throwable {
        generateJson(
            "",
            List.of(
                companies.get(0),
                companies.get(2),
                new Company(List.of(
                    departments.get(4),
                    departments.get(4),
                    departments.get(4)
                ), List.of())
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getEmployeesSortedByName")),
            List.of(
                Arguments.arguments(),
                Arguments.arguments(),
                Arguments.arguments()
            ),
            List.of(
                companies.get(0).getListOfAllEmployee(),
                companies.get(2).getListOfAllEmployee(),
                new Company(List.of(
                    departments.get(4),
                    departments.get(4),
                    departments.get(4)
                ), List.of()).getListOfAllEmployee()
            )
        );
    }

    @Test
    public void generateGetAllProductsByType_correctItems() throws Throwable {

        generateJson(
            "getAllProductsByType",
            "correctItems",
            // invoked
            List.of(
                companies.get(4),
                companies.get(5),
                companies.get(5),
                companies.get(5)
            ),
            // expected
            List.of(
                companies.get(4).getProducts(p -> p.type() == ProductType.Hardware).map(Product::name).distinct().toList(),
                companies.get(4).getProducts(p -> p.type() == ProductType.Software).map(Product::name).distinct().toList(),
                companies.get(4).getProducts(p -> p.type() == ProductType.Software).map(Product::name).distinct().toList(),
                companies.get(4).getProducts(p -> p.type() == ProductType.Hardware).map(Product::name).distinct().toList()
            ),
            // arguments
            List.of(
                Arguments.arguments(ProductType.Hardware, 5),
                Arguments.arguments(ProductType.Software, 5),
                Arguments.arguments(ProductType.Software, 3),
                Arguments.arguments(ProductType.Hardware, 1)
            ),
            // mocks
            List.of(
                List.of(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList()
            )
        );
    }

    @Test
    public void generateGetAllProductsByType() throws Throwable {
        generateJson(
            "",
            // invoked
            List.of(
                companies.get(4),
                companies.get(5),
                companies.get(5),
                companies.get(5)
            ),
            BasicTypeLink.of(Company.class).getMethod(BasicStringMatchers.identical("getAllProductsByType")),
            // arguments
            List.of(
                Arguments.arguments(ProductType.Hardware, 5),
                Arguments.arguments(ProductType.Software, 5),
                Arguments.arguments(ProductType.Software, 3),
                Arguments.arguments(ProductType.Hardware, 1)
            ),
            // mocks
            List.of(
                List.of(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(5)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList()
            )
        );
    }

    @Test
    public void testPriceRange() {

    }
}
