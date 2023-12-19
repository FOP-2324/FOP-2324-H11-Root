package h11;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Company {

    private List<Department> departments;
    private List<Warehouse> warehouses;


    public Company(List<Department> departments, List<Warehouse> warehouses) {
        this.departments = departments;
        this.warehouses = warehouses;
    }


    public List<Employee> getListOfAllEmployee() {
        return this.departments.stream()
            .flatMap(d -> d.getEmployees().stream())
            .toList();
    }

    public long getQuantityOfProduct(Product product) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(p -> p == product)
            .count();
    }

    public List<String> getFilteredProductNames(List<Predicate<Product>> predicates) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(product -> predicates.stream().allMatch(p -> p.test(product)))
            .map(Product::getName)
            .toList();
    }


    public List<Warehouse> getWarehousesByCapacity(int capacity) {
        return this.warehouses.stream()
            .filter(warehouse -> warehouse.getCurrentCapacity() < capacity)
            .toList();
    }


    public List<Product> priceRange(double low, double high) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(p -> p.getPrice() < high && p.getPrice() > low)
            .sorted(Comparator.comparingDouble(Product::getPrice))
            .toList();
    }

    public List<String> getEmployeesSortedByName() {
        return this.departments.stream()
            .flatMap(department -> department.getEmployees().stream())
            .map(employee -> employee.getName())
            .map(name -> {
                String[] splittedName = name.split(" ");
                return splittedName[1] + ", "+ splittedName[0];
            })
            .toList();
    }

    public List<String> getAllProductsByType(ProductTyp type, int numberOfProducts) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(product -> product.getProductTyp().equals(type))
            .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
            .limit(numberOfProducts)
            .map(product -> String.format("%s: %f€", product.getName(), product.getPrice()))
            .toList();

        // {Laptop: 1000$, ()}
    }



}
