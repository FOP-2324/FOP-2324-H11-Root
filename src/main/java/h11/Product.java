package h11;

public class Product {

    private final ProductTyp TYP;
    private int price;
    private int quantity;
    private final String PRODUCT_NAME;

    public Product(ProductTyp typ, int price, int quantity, String productName) {
        this.TYP = typ;
        this.price = price;
        this.quantity = quantity;
        this.PRODUCT_NAME = productName;
    }


    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductTyp getProductTyp() {
        return this.TYP;
    }


    public boolean isAvailable() {
        return this.quantity >0;
    }
}
