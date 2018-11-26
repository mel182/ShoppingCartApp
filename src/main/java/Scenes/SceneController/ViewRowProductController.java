package Scenes.SceneController;

import CustomAbstractClasses.CustomBaseController;
import Enumeration.PlaceHolder;
import Enumeration.PropertyIdentifier;
import Enumeration.ScenePath;
import Interface.OnTableViewRowClickListener;
import Model.ProductItem;
import Model.TableColumnReference;
import ProductList.ProductList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * <p>This is the view row product scene controller which inherit the {@link CustomBaseController}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class ViewRowProductController extends CustomBaseController implements OnTableViewRowClickListener
{
    //region FXML properties
    @FXML
    private TableColumn product_column;

    @FXML
    private TableColumn price_column;

    @FXML
    private TableView product_list_tableview;
    //endregion

    private ProductItem selectedItem;

    /**
     * Initialize scene controller
     */
    @SuppressWarnings("unchecked")
    public void initialize()
    {
        TableColumnReference[] column_references = {
                new TableColumnReference(price_column,PropertyIdentifier.PRODUCT_PRICE.toString()),
                new TableColumnReference(product_column,PropertyIdentifier.PRODUCT_NAME.toString())
        };

        initializeTableView(product_list_tableview,ProductList.getInstance().getList(),this, PlaceHolder.EMPTY_PRODUCT_LIST.toString(), column_references);

    }

    /**
     * Add new product to the list
     */
    public void addNewProduct()
    {
        launchScene(ScenePath.ADD_NEW_PRODUCT_SCENE.toString(),"Add new product");
        setFocusChangeListener();
    }

    /**
     * Set focus change listener to the corresponding window
     */
    @SuppressWarnings("unchecked")
    private void setFocusChangeListener()
    {
        Stage stage = (Stage) product_list_tableview.getScene().getWindow();

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
        product_list_tableview.getItems().clear();
        product_list_tableview.getItems().addAll(ProductList.getInstance().getList());
    }

    /**
     * Delete product from the product list
     */
    public void deleteProduct()
    {
        ProductList.getInstance().getList().remove(this.selectedItem);
        updateProductList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void OnClick(int clickCount, Object selectedItem) {
        this.selectedItem = (ProductItem) selectedItem;
    }
}
