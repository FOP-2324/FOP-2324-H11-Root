package h11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warehouse {

    public List<Product> products;
    private int maxCapacity;
    private int minCapacity;
    private int currentCapacity;

    public Warehouse() {
        this.products = new ArrayList<>();
        this.maxCapacity = 100; // arbitrary choosen
        this.minCapacity = 20;
        this.currentCapacity = 0;

    }

    public Warehouse(List<Product> products) {
        this.products = products;
        this.maxCapacity = 100; // arbitrary choosen
        this.minCapacity = 20;
        this.currentCapacity = 0;
    }


    public List<Product> getProducts() {
        return this.products;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public int getTotalQuantityOfProduct(Product product) {
        return this.products.stream()
            .filter(p -> p == product)
            .toList()
            .size();
    }

    public double getTotalPrice() {
        return this.products.stream()
            .mapToDouble(Product::getPrice)
            .sum();
    }

    public void addProducts(Product product, int numberOfProducts) {
        product.generateProducts()
            .limit(numberOfProducts)
            .forEach(this.products::add);
    }

    public List<Product> filterByAvailability() {
        return this.products.stream()
            .filter(p -> this.getTotalQuantityOfProduct(p) > 0)
            .collect(Collectors.toList());
    }
}
