package h11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Warehouse {

    public List<Product> products;
    private int capacity;

    public Warehouse() {
        this.products = new ArrayList<>();
        this.capacity = 100; // arbitrary choosen
    }

    public Warehouse(List<Product> products) {
        this.products = products;
        this.capacity = 100; // arbitrary choosen
    }


    public List<Product> getProducts() {
        return this.products;
    }

    public int getTotalQuantity() {
        return this.products.stream()
            .mapToInt(Product::getQuantity)
            .sum();
    }


    public Stream<Product> makeStreamOfProducts(ProductTyp category, int price, int quantity) {
        return Stream.generate(() -> new Product(category, price, quantity, null));
    }

    public void addProducts(ProductTyp category, int price, int quantity, int numberOfProducts) {
        this.makeStreamOfProducts(category, price, quantity)
            .limit(numberOfProducts)
            .forEach(this.products::add);
    }

    public List<Product> filterByAvailability() {
        return this.products.stream()
            .filter(Product::isAvailable)
            .collect(Collectors.toList());
    }
}
