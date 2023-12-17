package h11;

import java.util.List;

public class Company {

    private List<Department> departments;
    private List<Warehouse> warehouses;


    public Company(List<Department> departments, List<Warehouse> warehouses) {
        this.departments = departments;
        this.warehouses = warehouses;
    }

}
