package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * <p>This is product item model class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class ProductItem
{
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    /**
     * Constructor
     * @param name The product name
     * @param price Price per unit
     */
    public ProductItem(SimpleStringProperty name, SimpleDoubleProperty price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Get product name
     * @return The product name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Get product price
     * @return The product price
     */
    public double getPrice() {
        return price.get();
    }

    /**
     * Set product price
     * @param price The product price
     */
    public void setPrice(double price) {
        this.price.set(price);
    }
}
