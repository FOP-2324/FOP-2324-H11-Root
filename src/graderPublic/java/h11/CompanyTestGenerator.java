package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.util.List;

import static h11.TestObjects.companies;
import static h11.TestObjects.products;
import static h11.TestObjects.warehouses;

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
        generateJson("getFilteredProductNames","single",
            List.of(
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6),
                companies.get(6)
            ),
            List.of(
                companies.get(6).getFilteredProductNames(List.of(CompanyTest.ProductPredicate.NAME_STARTS_WITH_R.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(CompanyTest.ProductPredicate.NAME_CONTAINS_DIGIT.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(CompanyTest.ProductPredicate.IS_HARDWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(CompanyTest.ProductPredicate.IS_SOFTWARE.getPredicate())),
                companies.get(6).getFilteredProductNames(List.of(CompanyTest.ProductPredicate.PRICE_OVER_50.getPredicate()))
            ),
            List.of(
                Arguments.arguments(List.of(CompanyTest.ProductPredicate.NAME_STARTS_WITH_R)),
                Arguments.arguments(List.of(CompanyTest.ProductPredicate.NAME_CONTAINS_DIGIT)),
                Arguments.arguments(List.of(CompanyTest.ProductPredicate.IS_HARDWARE)),
                Arguments.arguments(List.of(CompanyTest.ProductPredicate.IS_SOFTWARE)),
                Arguments.arguments(List.of(CompanyTest.ProductPredicate.PRICE_OVER_50))
            ),
            List.of(
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList(),
                companies.get(6)
                    .warehouses()
                    .stream()
                    .flatMap(w -> w.products.stream())
                    .filter(p -> p.type().equals(ProductType.Hardware))
                    .toList()
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
