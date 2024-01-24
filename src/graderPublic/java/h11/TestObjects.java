package h11;

import java.util.List;

public class TestObjects {

    public static List<Product> products = List.of(
        new Product(ProductType.Hardware, 150.0, 1, "Main board"),
        new Product(ProductType.Hardware, 250.0, 2, "Main board"),
        new Product(ProductType.Hardware, 100.0, 2, "RAM 8GB"),
        new Product(ProductType.Hardware, 200.0, 4, "RAM 8GB"),
        new Product(ProductType.Hardware, 150.0, 2, "RAM 16GB"),
        new Product(ProductType.Hardware, 300.0, 4, "RAM 16GB"),
        new Product(ProductType.Hardware, 500.0, 1, "CPU"),
        new Product(ProductType.Hardware, 2000.0, 2, "GPU"),
        new Product(ProductType.Hardware, 80.0, 1, "Power supply"),
        new Product(ProductType.Hardware, 15.0, 1, "Stuff"),
        new Product(ProductType.Hardware, 15.0, 1, "Stuff"),
        new Product(ProductType.Hardware, 15.0, 1, "Stuff"),
        new Product(ProductType.Software, 55.50, 1, "Anti Virus"),
        new Product(ProductType.Software, 30.0, 2, "VPN"),
        new Product(ProductType.Software, 5.0, 1, "Chat-GPT"),
        new Product(ProductType.Software, 77.0, 1, "Windows")
    );

    public static List<Employee> employees = List.of(
        new Employee("Christian Cameron", Position.Analyst, 500),
        new Employee("Grace Buckland", Position.Admin, 2500),
        new Employee("Dan Greene", Position.Manager, 10000),
        new Employee("Azubi Eins", Position.Developer, 5.7),
        new Employee("Lillian Ellison", Position.Admin, 5000),
        new Employee("Lauren Banks", Position.Analyst, 6785),
        new Employee("Rob Banks", Position.Manager, 5000000),
        new Employee("Oliver Oliver", Position.Admin, 500),
        new Employee("Ronald McDonald", Position.Analyst, 6564),
        new Employee("Ruth Paige", Position.Developer, 500),
        new Employee("Amelia Turner", Position.Admin, 7653),
        new Employee("Dorothy Underwood", Position.Admin, 4531),
        new Employee("Kimberly Young", Position.Analyst, 8654),
        new Employee("Reid Richards", Position.Developer, 654),
        new Employee("John McClane", Position.Developer, 8653),
        new Employee("Dan Butler", Position.Analyst, 500),
        new Employee("Isaac Newton", Position.Analyst, 15000),
        new Employee("Big Boss", Position.Manager, 50000)
    );

    public static List<Warehouse> warehouses = List.of(
        new Warehouse(
            List.of(
                products.get(0),
                products.get(2),
                products.get(6),
                products.get(3),
                products.get(8),
                products.get(3),
                products.get(15),
                products.get(7),
                products.get(0),
                products.get(0),
                products.get(0)
            )
        ),
        new Warehouse(
            List.of(

            )
        ),
        new Warehouse(
            List.of(
                products.get(0),
                products.get(2),
                products.get(6),
                products.get(14)
            )
        )
    );

    public static List<Department> departments = List.of(
        new Department(employees),
        new Department(List.of(
            employees.get(0)
        )),
        new Department(List.of()),
        new Department(List.of(
            employees.get(1),
            employees.get(4),
            employees.get(7)
        )),
        new Department(List.of(
            employees.get(2),
            employees.get(3),
            employees.get(5),
            employees.get(6)
        ))
    );

    public static List<Company> companies = List.of(
        new Company(
            List.of(
                departments.get(1),
                departments.get(2),
                departments.get(3)
            ),
            List.of()
        ),
        new Company(
            List.of(
                departments.get(2),
                departments.get(2),
                departments.get(2)
            ),
            List.of()
        ),
        new Company(
            List.of(
                departments.get(1),
                departments.get(2),
                departments.get(3),
                departments.get(4)
            ),
            List.of()
        ),
        new Company(
            List.of(),
            List.of(
                warehouses.get(2),
                warehouses.get(2)
            )
        ),
        new Company(
            List.of(),
            List.of(
                warehouses.get(1),
                warehouses.get(1)
            )
        ),
        new Company(
            List.of(),
            List.of(
                warehouses.get(2),
                warehouses.get(2),
                warehouses.get(2),
                warehouses.get(2),
                warehouses.get(1),
                warehouses.get(0)
            )
        ),
        new Company(
            List.of(),
            List.of(
                new Warehouse(products),
                new Warehouse(products)
            )
        )
    );
}
