package h11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warehouse {

    public List<Product> products;
    private int maxCapacity;
    private int currentCapacity;

    public Warehouse() {
        this.products = new ArrayList<>();
        this.maxCapacity = 100; // arbitrary choosen
        this.currentCapacity = 0;
    }

    public Warehouse(List<Product> products) {
        this.products = products;
        this.maxCapacity = 100; // arbitrary choosen
        this.currentCapacity = 0;
    }

    public int getCurrentCapacity() {
        return this.currentCapacity;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public long getTotalQuantityOfProduct(Product product) {
        return this.products.stream()
            .filter(p -> p.equals(product))
            .count();
    }

    public double getTotalPrice() {
        return this.products.stream()
            .mapToDouble(Product::getPrice)
            .sum();
    }

    public void addProducts(Product product, int numberOfProducts) {
        this.generateProducts(product.getProductTyp(), product.getPrice(), product.getName())
            .limit(numberOfProducts)
            .forEach(this.products::add);
    }

    public Stream<Product> generateProducts(ProductTyp typ, double price, String name) {
        return Stream.generate(() -> new Product(typ, price, name));
    }

}
