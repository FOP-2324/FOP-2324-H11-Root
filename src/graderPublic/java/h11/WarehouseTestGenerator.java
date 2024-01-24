package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.util.ArrayList;
import java.util.List;

import static h11.TestObjects.products;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;

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
}
