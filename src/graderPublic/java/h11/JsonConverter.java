package h11;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.mock;

public class JsonConverter {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static Company toCompany(final JsonNode jsonNode){
        return new Company(toList(jsonNode.get("departments"), JsonConverter::toDepartment), toList(jsonNode.get("warehouses"), JsonConverter::toWarehouse));
    }

    public static Department toDepartment(final JsonNode jsonNode){
        return new Department(toList(jsonNode.get("employees"), JsonConverter::toEmployee));
    }

    public static Warehouse toWarehouse(final JsonNode jsonNode){
        return new Warehouse(toList(jsonNode.get("products"), JsonConverter::toProduct)){
            @Override
            public boolean equals(Object obj) {
                if (super.equals(obj)) {
                    return true;
                }
                if (!(obj instanceof Warehouse other)) {
                    return false;
                }
                return products.equals(other.products);
            }

            @Override
            public String toString() {
                return "Warehouse{" +
                    "products=" + products +
                    '}';
            }
        };
    }

    public static Employee toEmployee(final JsonNode jsonNode){
        try {
            return new Employee(jsonNode.get("name").asText(), Position.valueOf(jsonNode.get("position").asText()), jsonNode.get("salary").asDouble()){
                @Override
                public boolean equals(Object obj) {
                    if (super.equals(obj)) {
                        return true;
                    }
                    if (!(obj instanceof Employee other)) {
                        return false;
                    }
                    return getName().equals(other.getName()) && getPosition().equals(other.getPosition()) && getSalary() == other.getSalary();
                }

                @Override
                public String toString() {
                    return "Employee{name=" + getName() + ", position=" + getPosition() + ", salary=" + getSalary() + "}";
                }
            };
        } catch (Exception e) {
            return null;
        }
    }

    public static Product toProduct(final JsonNode jsonNode){
        try {
            return new Product(ProductType.valueOf(jsonNode.get("type").asText()), jsonNode.get("price").asDouble(), jsonNode.get("quantity").asInt(), jsonNode.get("name").asText());
        } catch (Exception e) {
            return null;
        }
    }

    public static Position toPosition(final JsonNode jsonNode){
        return Position.valueOf(jsonNode.asText());
    }

    public static ProductType toProductType(final JsonNode jsonNode){
        return ProductType.valueOf(jsonNode.asText());
    }

    public static <T> List<T> toList(final JsonNode jsonNode, final Function<JsonNode, T> mapper) {
        if (jsonNode instanceof ArrayNode arrayNode && arrayNode.isEmpty()) {
            return new ArrayList<>();
        }
        return StreamSupport.stream(jsonNode.spliterator(), false)
            .map(node -> {
                try {
                    return mapper.apply(node);
                } catch (Exception e) {
                    return null;
                }
            })
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public static String toJson(Company company) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(company));
    }

    public static String toJson(Department department) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(department));
    }

    public static String toJson(Warehouse warehouse) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(warehouse));
    }

    public static String toJson(Employee employee) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(employee));
    }

    public static String toJson(Product product) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(product));
    }

    public static String toJson(Position position) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(position));
    }

    public static String toJson(ProductType productType) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(productType));
    }

    public static String toJson(List<?> listToMap) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(toJson(listToMap));
    }

    public static JsonNode toJsonNode(List<?> toMap){
        ArrayNode rootNode = MAPPER.createArrayNode();
        for (Object object: toMap){
            rootNode.add(toJsonNode(object));
        }
        return rootNode;
    }

    public static JsonNode toJsonNode(Object toMap) {
        ObjectNode rootNode = MAPPER.createObjectNode();

        if (toMap instanceof List<?> list){
            return toJsonNode(list);
        } else if (toMap instanceof Company company) {
            rootNode.set("departments", toJsonNode(company.departments()));
            rootNode.set("warehouses", toJsonNode(company.warehouses()));
        } else if (toMap instanceof Warehouse warehouse) {
            rootNode.set("products", toJsonNode(warehouse.getProducts()));
        } else if (toMap instanceof Product product) {
            rootNode.put("type", product.type().name());
            rootNode.put("price", product.price());
            rootNode.put("quantity", product.quantity());
            rootNode.put("name", product.name());
        } else if (toMap instanceof Department department) {
            rootNode.set("employees", toJsonNode(department.employees()));
        } else if (toMap instanceof Employee employee) {
            rootNode.put("name", employee.getName());
            rootNode.put("position", employee.getPosition().name());
            rootNode.put("salary", employee.getSalary());
        } else if (toMap instanceof Position position) {
            return new TextNode(position.name());
        } else if (toMap instanceof ProductType productType) {
            return new TextNode(productType.name());
        } else {
            return JsonNodeFactory.instance.pojoNode(toMap);
        }

        return rootNode;
    }
}
