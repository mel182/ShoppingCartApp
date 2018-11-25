package ProductList;

import Model.ProductItem;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>This is default product list class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class DefaultProductList
{
    public static final String ROBIJN = "Robijn";
    private static final String BRINTA = "Brinta";
    private static final String CHINESE_VEGETABLES = "Chinese vegetables";
    public static final String FARMER_CHEESE = "Farmer's cheese";
    public static final String DIAPER = "Diaper";

    /**
     * Array containing the default product items
     */
    private static ProductItem[] default_list = {
            new ProductItem(new SimpleStringProperty(ROBIJN), new SimpleDoubleProperty(3.00)),
            new ProductItem(new SimpleStringProperty(BRINTA), new SimpleDoubleProperty(2.50)),
            new ProductItem(new SimpleStringProperty(CHINESE_VEGETABLES), new SimpleDoubleProperty(5.00)),
            new ProductItem(new SimpleStringProperty(FARMER_CHEESE), new SimpleDoubleProperty(2.00)),
            new ProductItem(new SimpleStringProperty(DIAPER), new SimpleDoubleProperty(10.00))
    };

    /**
     * Get the array list containing the default product items
     * @return Array containing the default product items
     */
    protected static ArrayList<ProductItem> get()
    {
        return new ArrayList<ProductItem>(Arrays.asList(default_list));
    }
}
