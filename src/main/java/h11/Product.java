package h11;

public class Product {

    private final ProductTyp TYP;
    private int price;
    private int quantity;

    public Product(ProductTyp typ, int price, int quantity) {
        this.TYP = typ;
        this.price = price;
        this.quantity = quantity;
    }


    public int getPrice() {
        return this.price;
    }

    public ProductTyp getProductTyp() {
        return this.TYP;
    }


    public boolean isAvailable() {
        return this.quantity >0;
    }
}
