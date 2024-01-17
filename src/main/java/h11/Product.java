package h11;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

/**
 * Represents a {@link Product} which is stored in a {@link Warehouse}.
 *
 * @param type     the {@link ProductType} of this product
 * @param price    the price of the product in euros
 * @param name     the name of the product
 */
@DoNotTouch
public record Product(ProductType type, double price, String name) {
}
