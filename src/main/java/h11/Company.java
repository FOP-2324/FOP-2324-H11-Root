package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.SolutionOnly;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A {@link Company} manages a list of {@link Department}s and a list of {@link Warehouse}s and provides
 * methods to query information about them.
 *
 * @param departments The departments of this company.
 * @param warehouses  The warehouses of this company.
 */
public record Company(@DoNotTouch List<Department> departments, @DoNotTouch List<Warehouse> warehouses) {

    /**
     * Creates a new {@link Company} with the given departments and warehouses.
     *
     * @param departments the departments
     * @param warehouses  the warehouses
     */
    @DoNotTouch
    public Company {
    }

    /**
     * Returns a {@link Stream} of all products in all warehouses which match all the given {@linkplain Predicate
     * predicates}.
     *
     * @param pred the predicates to match the products against
     * @return the stream of products
     */
    @SolutionOnly("Hilfsmethode")
    public Stream<Product> getProducts(Predicate<? super Product> pred) {
        return this.warehouses.stream()
            .flatMap(warehouse -> warehouse.getProducts().stream())
            .filter(pred);
    }

    /**
     * Returns a {@link List} of all employees in all departments.
     *
     * @return the list of employees
     */
    @StudentImplementationRequired
    public List<Employee> getListOfAllEmployee() {
        return this.departments.stream()
            .flatMap(d -> d.employees().stream())
            .toList();
    }

    /**
     * Returns the quantity of the given {@link Product} in all the {@linkplain Warehouse warehouses} of this {@link Company}.
     *
     * @param product the product
     * @return the quantity of the product
     */
    @StudentImplementationRequired
    public long getQuantityOfProduct(Product product) {
        return this.warehouses.stream()
            .mapToInt(warehouse -> warehouse.getProducts(product::equals).size())
            .sum();
    }

    /**
     * Returns the Names of all {@linkplain Product products} in all the {@linkplain Warehouse warehouses} of this
     * {@link Company} which match all the given {@link Predicate predicates}.
     *
     * @param predicates the predicate to match the products against
     * @return the names of the products
     */
    @StudentImplementationRequired
    public List<String> getFilteredProductNames(List<Predicate<Product>> predicates) {
        return getProducts(predicates.stream().reduce(Predicate::and).orElse(p -> true)) // just to show that allMatch isn't necessary here ;) (but it would be better)
            .map(Product::name)
            .toList();
    }

    /**
     * Returns all {@linkplain Product products} in all the {@linkplain Warehouse warehouses} of this {@link Company}
     * that are in the given price range. The products are sorted by price in ascending order.
     *
     * @param low  the lower bound of the price range
     * @param high the upper bound of the price range
     * @return the products in the price range
     */
    @StudentImplementationRequired
    public List<Product> priceRange(double low, double high) {
        return getProducts(p -> p.price() <= high && p.price() >= low)
            .sorted(Comparator.comparingDouble(Product::price))
            .toList();
    }

    /**
     * Returns a {@link List} of all {@link Employee}s in all {@link Department}s of this {@link Company} sorted by
     * their name in ascending order. Each name is formatted as follows: {@code "{lastName}, {firstName}"}.
     *
     * @return the list of employees
     */
    @StudentImplementationRequired
    public List<String> getEmployeesSortedByName() {
        return getListOfAllEmployee().stream()
            .map(Employee::getName)
            .map(name -> Pattern.compile("(?<firstName>\\w+)\\s+(?<lastName>\\w+)")
                .matcher(name)
                .replaceAll("${lastName}, ${firstName}")
            )
            .toList();
    }

    /**
     * Returns a {@link List} of all the {@linkplain Product products} in all the {@linkplain Warehouse warehouses} of
     * this {@link Company} which are of the given {@link ProductType type}. The products are sorted by price in
     * descending order. The list contains at most 5 products. Each product is formatted as follows: {@code "{name}: {price}€"}.
     *
     * @param type the type of the product
     * @return the list of employees
     */
    @StudentImplementationRequired
    public List<String> getAllProductsByType(ProductType type, int numberOfProducts) {
        return getProducts(product -> product.type().equals(type))
            .sorted(Comparator.comparingDouble(Product::price).reversed())
            .limit(numberOfProducts)
            .map(product -> String.format("%s: %f€", product.name(), product.price()))
            .toList();
        // {Laptop: 1000$, ()}
    }

    @Override
    public List<Department> departments() {
        return departments;
    }

    @Override
    public List<Warehouse> warehouses() {
        return warehouses;
    }
}
