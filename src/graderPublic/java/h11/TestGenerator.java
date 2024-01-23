package h11;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.params.provider.Arguments;
import org.tudalgo.algoutils.tutor.general.reflections.MethodLink;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestGenerator {

    public void generateJson(String testName, List<Object> invokedObject, MethodLink toCall, List<Arguments> argumentsList)
        throws Throwable {
        generateJson(testName, invokedObject, toCall, argumentsList, null, false);
    }

    public void generateJson(String testName, List<Object> invokedObject, MethodLink toCall, List<Arguments> argumentsList,
                             List<?> mocked)
        throws Throwable {
        generateJson(testName, invokedObject, toCall, argumentsList, mocked, false);
    }

    public void generateJson(String testName, List<Object> invokedObject, MethodLink toCall, List<Arguments> argumentsList,
                             boolean inPlace)
        throws Throwable {
        generateJson(testName, invokedObject, toCall, argumentsList, null, inPlace);
    }

    public void generateJson(String testName, List<Object> invokedObject, MethodLink toCall, List<Arguments> argumentsList,
                             List<?> mocked, boolean inPlace)
        throws Throwable {
        ArrayNode rootNode = JsonConverter.MAPPER.createArrayNode();

        if (invokedObject.size() == 1) {
            for (Arguments arguments : argumentsList) {
                ObjectNode objectNode = JsonConverter.MAPPER.createObjectNode();

                objectNode.set(
                    invokedObject.get(0).getClass().getSimpleName().toLowerCase(),
                    JsonConverter.toJsonNode(invokedObject.get(0))
                );

                Object expected = toCall.invoke(invokedObject.get(0), arguments.get());

                objectNode.set("expected", JsonConverter.toJsonNode(inPlace ? invokedObject.get(0) : expected));


                objectNode.set("arguments", JsonConverter.toJsonNode(Arrays.asList(arguments.get())));
                if (mocked != null) {
                    objectNode.set("mocked", JsonConverter.toJsonNode(mocked.get(0)));
                }
                rootNode.add(objectNode);
            }
        } else {
            for (int i = 0; i < invokedObject.size(); i++) {
                ObjectNode objectNode = JsonConverter.MAPPER.createObjectNode();

                objectNode.set(
                    invokedObject.get(i).getClass().getSimpleName().toLowerCase(),
                    JsonConverter.toJsonNode(invokedObject.get(i))
                );

                Object expected = toCall.invoke(invokedObject.get(i), argumentsList.get(i).get());

                objectNode.set("expected", JsonConverter.toJsonNode(inPlace ? invokedObject.get(i) : expected));


                objectNode.set("arguments", JsonConverter.toJsonNode(Arrays.asList(argumentsList.get(i).get())));
                if (mocked != null) {
                    objectNode.set("mocked", JsonConverter.toJsonNode(mocked.get(i)));
                }
                rootNode.add(objectNode);
            }
        }

        writeToFile(toCall.name() + (testName.isEmpty() ? "" : "_" + testName), rootNode);
    }

    public void generateJson(String methodName, String testName, List<Object> invokedObject, List<?> expected,
                             List<Arguments> argumentsList) throws Throwable {
        generateJson(methodName, testName, invokedObject, expected, argumentsList, null);
    }

    public void generateJson(String methodName, String testName, List<Object> invokedObject, List<?> expected,
                             List<Arguments> argumentsList, List<?> mocked)
        throws Throwable {
        ArrayNode rootNode = JsonConverter.MAPPER.createArrayNode();

        for (int i = 0; i < invokedObject.size(); i++) {
            ObjectNode objectNode = JsonConverter.MAPPER.createObjectNode();

            objectNode.set(
                invokedObject.get(i).getClass().getSimpleName().toLowerCase(),
                JsonConverter.toJsonNode(invokedObject.get(i))
            );


            objectNode.set("expected", JsonConverter.toJsonNode(expected.get(i)));
            objectNode.set("arguments", JsonConverter.toJsonNode(Arrays.asList(argumentsList.get(i).get())));
            if (mocked != null) {
                objectNode.set("mocked", JsonConverter.toJsonNode(mocked.get(i)));
            }
            rootNode.add(objectNode);
        }

        writeToFile(methodName + (testName.isEmpty() ? "" : "_" + testName), rootNode);
    }

    private void writeToFile(String testName, JsonNode rootNode) throws IOException {
        // write to file in src/graderPrivate/ressources/a/package/name/${fileName}.json
        final var resourcePath = "src/graderPublic/resources/" + getClass()
            .getPackageName()
            .replace('.', '/') + "/" + testName + ".json";
        File file = new File(resourcePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        JsonConverter.MAPPER.writeValue(file, rootNode);
    }
}
