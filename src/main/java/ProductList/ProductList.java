package ProductList;

import Model.ProductItem;
import java.util.List;

/**
 * <p>This is product list singleton class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class ProductList {

    private static volatile ProductList instance;
    private static Object mutex = new Object();
    private List<ProductItem> productItemList;

    /**
     * private constructor
     */
    private ProductList()
    {
        productItemList = DefaultProductList.get();
    }

    /**
     * Get instance of the singleton class.
     * This method handles the Mutual Exclusion Object (mutex), this is crucial since a mutex is created with a unique name.
     * After this stage, any thread that need the resource must lock the mutex from other threads while it is using the resources.
     * The mutex object is set to unlock when the data is no longer needed or the routine is finished.
     * @return {@link ProductList} instance
     */
    public static ProductList getInstance()
    {
        ProductList currentInstance = instance;

        if (currentInstance == null)
        {
            synchronized (mutex) {
                currentInstance = instance;
                if (currentInstance == null)
                    instance = currentInstance = new ProductList();
            }
        }
        return currentInstance;
    }

    /**
     * Get the product item list
     * @return The product item array list
     */
    public List<ProductItem> getList() {
        return productItemList;
    }
}
