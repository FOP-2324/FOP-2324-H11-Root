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
import static h11.CompanyTestP.ProductPredicate.PRICE_OVER_50;
import static h11.TestObjects.products;
import static h11.TestObjects.warehouses;

public class WarehouseTestGenerator extends TestGenerator{

    @Test
    public void generateGetPrice_null() throws Throwable {
        generateJson("null",
            List.of(
                new Warehouse(List.of())
            ),
            BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getPrice")),
            List.of(
                Arguments.arguments((Product) null)
            )
        );
    }

    @Test
    public void testGetPrice_nonNull() throws Throwable {
        generateJson("nonNull",
            List.of(
                new Warehouse(List.of()),
                new Warehouse(List.of()),
                new Warehouse(List.of()),
                new Warehouse(List.of())
            ),
            BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getPrice")),
            List.of(
                Arguments.arguments(products.get(0)),
                Arguments.arguments(products.get(4)),
                Arguments.arguments(products.get(13)),
                Arguments.arguments(products.get(15))
            )
        );
    }


    @Test
    public void generateGetProducts() throws Throwable {
        generateJson("getProducts", "",
            List.of(
                new Warehouse(products),
                new Warehouse(products),
                new Warehouse(products),
                new Warehouse(products),
                new Warehouse(products)
            ),
            List.of(
                new Warehouse(products).getProducts(NAME_CONTAINS_DIGIT.getPredicate()),
                new Warehouse(products).getProducts(NAME_STARTS_WITH_R.getPredicate()),
                new Warehouse(products).getProducts(IS_SOFTWARE.getPredicate()),
                new Warehouse(products).getProducts(PRICE_OVER_50.getPredicate()),
                new Warehouse(products).getProducts(IS_HARDWARE.getPredicate())
            ),
            List.of(
                Arguments.arguments(NAME_CONTAINS_DIGIT.name()),
                Arguments.arguments(NAME_STARTS_WITH_R.name()),
                Arguments.arguments(IS_SOFTWARE.name()),
                Arguments.arguments(PRICE_OVER_50.name()),
                Arguments.arguments(IS_HARDWARE.name())
            )
        );
    }

    @Test
    public void generateGetTotalQuantityOfProduct() throws Throwable {
        generateJson("",
            List.of(
                warehouses.get(0),
                warehouses.get(0),
                warehouses.get(1),
                warehouses.get(2)
            ),
            BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getTotalQuantityOfProduct")),
            List.of(
                Arguments.arguments(products.get(0)),
                Arguments.arguments(products.get(3)),
                Arguments.arguments(products.get(0)),
                Arguments.arguments(products.get(0))
            )
        );
    }

    @Test
    public void generateGetTotalPrice() throws Throwable {
        generateJson("",
            List.of(
                warehouses.get(0),
                warehouses.get(1),
                warehouses.get(2),
                new Warehouse(products)
            ),
            BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("getTotalPrice")),
            List.of(
                Arguments.arguments(),
                Arguments.arguments(),
                Arguments.arguments(),
                Arguments.arguments()
            )
        );
    }

    @Test
    public void generateAddProducts() throws Throwable {
        generateJson("",
            List.of(
                warehouses.get(0),
                warehouses.get(1),
                warehouses.get(2),
                new Warehouse(products)
            ),
            BasicTypeLink.of(Warehouse.class).getMethod(BasicStringMatchers.identical("addProducts")),
            List.of(
                Arguments.arguments(products.get(0), 4),
                Arguments.arguments(products.get(1), 2),
                Arguments.arguments(products.get(15), 7),
                Arguments.arguments(products.get(8), 2)
            ),
             true
        );
    }
}
