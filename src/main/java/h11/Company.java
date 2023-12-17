package h11;

import java.util.List;
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
            .collect(Collectors.toList());
    }

    public int getQuantityOfProduct(Product product) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(p -> p.equals(product))
            .collect(Collectors.toList())
            .size();
    }
}
