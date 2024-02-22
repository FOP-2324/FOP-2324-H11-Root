package h11;

import com.fasterxml.jackson.databind.JsonNode;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions4;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

public class H11_TestP {

    public static final Map<String, Function<JsonNode, ?>> customConverters = new HashMap<>() {
        {
            put("company", JsonConverter::toCompany);
            put("product", JsonConverter::toProduct);
            put("employee", JsonConverter::toEmployee);
            put("warehouse", JsonConverter::toWarehouse);
            put("department", JsonConverter::toDepartment);
            put("position", JsonConverter::toPosition);
            put("productType", JsonConverter::toProductType);
        }
    };

    public void assertContainsAll(List<?> expected, List<?> actual, Context context) {
        assertEquals(expected.size(), actual.size(), context, r -> "List does not contain same amount of items.");

        assertTrue(expected.containsAll(actual), contextBuilder()
            .add(context)
            .add("actual", actual)
            .add("expected", expected)
            .build(), r -> "Actual List does not contain all expected Elements");
    }

    public void assertListEquals(List<?> expected, List<?> actual, Context context) {
        assertEquals(expected.size(), actual.size(), context, r -> "List does not contain same amount of items.");

        assertEquals(expected, actual, context, r -> "The List does not have the correct ordering.");
    }

    public void assertNoLoopOrRecursion(Method methodToCheck) {
        Assertions4.assertIsNotRecursively(BasicMethodLink.of(methodToCheck).getCtElement(), emptyContext(), r -> "Method %s uses recursion.");
        Assertions4.assertIsNotIteratively(BasicMethodLink.of(methodToCheck).getCtElement(), emptyContext(), r -> "Method %s uses loops.");
    }
}
