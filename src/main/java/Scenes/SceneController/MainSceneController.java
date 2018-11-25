package Scenes.SceneController;

import CustomAbstractClasses.CustomBaseController;
import Enumeration.PlaceHolder;
import Enumeration.PropertyIdentifier;
import Enumeration.ScenePath;
import Enumeration.SceneSelection;
import Interface.QuantitySelectionCallback;
import Model.CartItem;
import Model.ProductItem;
import Model.TableColumnReference;
import ProductList.ProductList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>This is the main scene controller class which inherit the {@link CustomBaseController}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class MainSceneController extends CustomBaseController implements QuantitySelectionCallback
{
    //region FXML properties
    @FXML
    private VBox main_scene_window;

    @FXML
    private Button delete_cart_item_button;

    @FXML
    private TableView shopping_cart_list;

    @FXML
    private Text cart_total_text;

    @FXML
    private Button cart_pay_button;

    @FXML
    private TableView product_tableView;

    @FXML
    private TableColumn product_table_product_column;

    @FXML
    private TableColumn product_list_price_column;

    @FXML
    private TableColumn cart_quantity_column;

    @FXML
    private TableColumn cart_product_column;

    @FXML
    private TableColumn cart_price_column;
    //endregion

    //region local instance
    private List<CartItem> cartItems = new ArrayList<>();
    private List<ProductItem> productItems = new ArrayList<>();
    private CartItem cartSelectedItem;
    //endregion

    /**
     * Controller initializer
     */
    public void initialize()
    {
        initializeSetProductList();
        initializeSetCartList();
    }

    /**
     * Initializer and set product list
     */
    @SuppressWarnings("unchecked")
    private void initializeSetProductList()
    {
        productItems = ProductList.getInstance().getList();

        TableColumnReference[] column_references = {
                new TableColumnReference(product_table_product_column,PropertyIdentifier.PRODUCT_NAME.toString()),
                new TableColumnReference(product_list_price_column,PropertyIdentifier.PRODUCT_PRICE.toString())
        };

        intitializeTableView(product_tableView,productItems,(clickCount, selectedItem) -> {

            if (selectedItem instanceof ProductItem)
            {
                ProductItem productItem = (ProductItem) selectedItem;
                launchScene(ScenePath.QUANTITY_SELECTION_SCENE.toString(),"Quantity",productItem,MainSceneController.this,SceneSelection.QUANTITY_SCENE);
            }

        }, PlaceHolder.EMPTY_PRODUCT_LIST.toString(), column_references);
    }

    /**
     * Initialize and set cart list
     */
    @SuppressWarnings("unchecked")
    private void initializeSetCartList()
    {
        TableColumnReference[] column_references = {
                new TableColumnReference(cart_quantity_column,PropertyIdentifier.CART_ITEM_QUANTITY.toString()),
                new TableColumnReference(cart_product_column,PropertyIdentifier.CART_ITEM_DESCRIPTION.toString()),
                new TableColumnReference(cart_price_column,PropertyIdentifier.CART_ITEM_TOTAL_PRICE.toString())
        };

        intitializeCartItemTableView(shopping_cart_list,this.cartItems,(clickCount, selectedItem) -> {

            if (selectedItem instanceof CartItem)
            {
                cartSelectedItem = (CartItem) selectedItem;
                delete_cart_item_button.setVisible(true);
            }
        }, PlaceHolder.EMPTY_CART_LIST.toString(), column_references);
    }

    /**
     * <p>Delete item from shopping cart</p>
     */
    public void deleteCartItem()
    {
        cartItems.remove(cartSelectedItem);
        refreshCartList();
    }

    /**
     * <p>Close and exit the system</p>
     */
    public void closeApplication()
    {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Launch product list scene
     */
    public void launchProductListScene()
    {
        launchScene(ScenePath.VIEW_PRODUCT_LIST.toString(),"ProductItem");
        setFocusChangeListener();
    }

    /**
     * Calculate sub total
     */
    private void calculateSubTotal()
    {
        double subtotal = 0.0;

        for (CartItem cartItem: cartItems)
        {
            subtotal += cartItem.getTotal_price();
        }

        cart_total_text.setText(getValueIncludeCurrency(subtotal));
        cart_pay_button.setDisable(cartItems.isEmpty());
    }

    /**
     * Set focus change listener to the corresponding window
     */
    @SuppressWarnings("unchecked")
    private void setFocusChangeListener()
    {
        Stage stage = (Stage) main_scene_window.getScene().getWindow();

        stage.focusedProperty().addListener((observable, oldValue, focus) -> {
            if (focus)
            {
                updateProductList();
            }
        });
    }

    /**
     * Update product list
     */
    @SuppressWarnings("unchecked")
    private void updateProductList()
    {
        productItems = ProductList.getInstance().getList();
        product_tableView.getItems().clear();
        product_tableView.getItems().addAll(productItems);
    }

    /**
     * Refresh cart list
     */
    @SuppressWarnings("unchecked")
    private void refreshCartList()
    {
        shopping_cart_list.getItems().clear();
        shopping_cart_list.getItems().addAll(cartItems);
        calculateSubTotal();
    }

    /**
     * Clear shopping cart list
     */
    @SuppressWarnings("unchecked")
    private void clearShoppingCart()
    {
        cartItems.clear();
        shopping_cart_list.getItems().clear();
    }

    /**
     * Launch pay scene
     */
    public void launchPayScene()
    {
        launchScene(ScenePath.PAY_SCENE.toString(),"Invoice",cartItems,SceneSelection.PAY_SCENE);
        clearShoppingCart();
    }

    /**
     * Launch about scene
     */
    public void launchAboutScene()
    {
        launchScene(ScenePath.ABOUT_SCENE.toString(),"About");
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void OnQuantitySelected(CartItem cartItem)
    {
        cartItems.add(cartItem);
        refreshCartList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void OnQuantitySelectionTaskCancel() { }
}
