package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * <p>This is cart item model class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class CartItem
{
    private ProductItem productItem;
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty(0);
    private SimpleStringProperty description = new SimpleStringProperty("test");
    private SimpleDoubleProperty total_price = new SimpleDoubleProperty(0.0);

    /**
     * Constructor
     * @param productItem The target {@link ProductItem} object
     * @param quantity The total quantity
     * @param description The product description
     */
    public CartItem(ProductItem productItem, int quantity, String description) {
        this.productItem = productItem;
        this.quantity.set(quantity);

        this.description.set(description);
    }

    /**
     * Get product item instance
     * @return The {@link ProductItem} instance
     */
    public ProductItem getProductItem() {
        return productItem;
    }

    /**
     * Get total quantity
     * @return The total quantity
     */
    public int getQuantity() {
        return quantity.getValue();
    }

    /**
     * Get the total price
     * @return The total price
     */
    public double getTotal_price() {
        return total_price.getValue();
    }

    /**
     * Set the total price
     * @param total_price The total price value
     */
    public void setTotal_price(double total_price) {
        this.total_price.set(total_price);
    }

    /**
     * Set the description
     * @param description The item description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Get cart item description
     * @return Cart item description
     */
    public String getDescription() {
        return description.get();
    }
}
