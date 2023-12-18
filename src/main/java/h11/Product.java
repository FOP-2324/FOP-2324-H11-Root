package h11;

import java.util.stream.Stream;

public class Product {

    private final ProductTyp TYP;
    private double price;
    private int quantity;
    private final String PRODUCT_NAME;

    public Product(ProductTyp typ, double price, int quantity, String productName) {
        this.TYP = typ;
        this.price = price;
        this.quantity = quantity;
        this.PRODUCT_NAME = productName;
    }

    public Product(ProductTyp typ, double price, String productName) {
        this.TYP = typ;
        this.price = price;
        this.PRODUCT_NAME = productName;
    }


    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductTyp getProductTyp() {
        return this.TYP;
    }

    public String getName() {
        return this.PRODUCT_NAME;
    }


    public Stream<Product> generateProducts() {
        return Stream.iterate(new Product(this.TYP, this.price, this.PRODUCT_NAME),
            product -> new Product(this.TYP, this.price, this.PRODUCT_NAME));
    }
}
