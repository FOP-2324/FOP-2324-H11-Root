package h11;

import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.SolutionOnly;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Warehouse {

    @DoNotTouch
    public List<Product> products = new ArrayList<>();
    @DoNotTouch
    private int maxCapacity = 100;
    @DoNotTouch
    private int currentCapacity = 0;

    @DoNotTouch
    public Warehouse(List<Product> products) {
        this.products = products;
    }

    @SolutionOnly
    public Product getProduct(Predicate<? super Product> pred) {
        return this.products.stream()
            .filter(pred)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No product found"));
    }

    @StudentImplementationRequired
    public double getPrice(@Nullable Product prod) {
        return Optional.ofNullable(prod)
            .map(Product::price)
            .orElse(0.0);
    }

    @DoNotTouch
    public List<Product> getProducts() {
        return this.products;
    }

    @StudentImplementationRequired
    public List<Product> getProducts(Predicate<? super Product> predicate) {
        return this.products.stream()
            .filter(predicate)
            .toList();
    }

    @StudentImplementationRequired
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


    // Do NOT forget to add getters, otherwise testing will be problematic

    @DoNotTouch
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    @DoNotTouch
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @DoNotTouch
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    @StudentImplementationRequired
    public long getTotalQuantityOfProduct(Product product) {
        return getProducts(p -> p == product)
            .size();
    }

    @StudentImplementationRequired
    public double getTotalPrice() {
        return this.products.stream()
            .mapToDouble(Product::price)
            .sum();
    }

    @StudentImplementationRequired
    public void addProducts(Product product, int numberOfProducts) {
        this.generateProducts(product.type(), product.price(), product.name())
            .limit(numberOfProducts)
            .forEach(this.products::add);
    }

    @StudentImplementationRequired
    public Stream<Product> generateProducts(ProductType typ, double price, String name) {
        return Stream.generate(() -> new Product(typ, price, 1, name));
    }
}
