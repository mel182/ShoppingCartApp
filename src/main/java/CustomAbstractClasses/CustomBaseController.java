package CustomAbstractClasses;

import Enumeration.SceneSelection;
import Interface.OnTableViewRowClickListener;
import Interface.QuantitySelectionCallback;
import Model.CartItem;
import Model.ProductItem;
import Model.TableColumnReference;
import Utility.SceneLauncher;
import com.sun.istack.internal.Nullable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>This is the custom scene controller abstract class that will be inherit by all controllers within the entire application.</p>
 *
 * <p>Created by Melchior vrolijk</p>
 */
public abstract class CustomBaseController
{
    protected static final String DECIMAL_ONLY_PATTERN = "\\d*";

    /**
     * Launch scene based on the properties provided
     * @param path The FXML file path
     * @param title The window title
     */
    protected void launchScene(String path, String title)
    {
        new SceneLauncher().launch(path,title);
    }

    /**
     * Launch scene based on the properties provided
     * @param path The FXML file path
     * @param title The window title
     * @param productItem The target {@link ProductItem} object
     * @param quantitySelectionCallback The {@link QuantitySelectionCallback} interface callback instance
     * @param selection The {@link SceneSelection} option
     */
    protected void launchScene(String path, String title, ProductItem productItem, QuantitySelectionCallback quantitySelectionCallback, SceneSelection selection)
    {
        new SceneLauncher().launch(path,title,productItem,quantitySelectionCallback,selection);
    }

    /**
     * Launch scene based on the properties provided
     * @param path The FXML file path
     * @param title The window title
     * @param cartItemList The target {@link CartItem} object
     * @param sceneSelection The {@link SceneSelection} option
     */
    protected void launchScene(String path, String title, List<CartItem> cartItemList, SceneSelection sceneSelection)
    {
        new SceneLauncher().launch(path,title,cartItemList,sceneSelection);
    }

    /**
     * Set Item clicked to a target table view
     * @param target_tableView The target table view
     * @param tableViewClickListener The {@link OnTableViewRowClickListener} interface for reporting occurring events
     */
    @SuppressWarnings("unchecked")
    protected void setItemClickListener(TableView target_tableView, OnTableViewRowClickListener tableViewClickListener)
    {
        target_tableView.setRowFactory( table_view -> {
            final TableRow<Object> selected_item_row = new TableRow<>();

            selected_item_row.setOnMouseClicked(event -> {
                if (! selected_item_row.isEmpty() && event.getButton() == MouseButton.PRIMARY && tableViewClickListener != null)
                {
                    tableViewClickListener.OnClick(event.getClickCount(),selected_item_row.getItem());
                }
            });

            return selected_item_row;
        });
    }

    /**
     * Get value including the corresponding currency symbol
     * @param value The value (in {@link Double} format)
     * @return The value including the currency symbol (in {@link String} format)
     */
    protected String getValueIncludeCurrency(double value)
    {
        return "€ "+value;
    }


    protected void intitializeCartItemTableView(TableView tableView, List<CartItem> cartItems, @Nullable OnTableViewRowClickListener onTableViewRowClickListener, String placeholder, TableColumnReference...columnReferences)
    {

        final ObservableList<CartItem> items = FXCollections.observableArrayList(cartItems);

        for (TableColumnReference tableColumnReference: columnReferences)
        {
            tableColumnReference.getTableColumn().setCellValueFactory(new PropertyValueFactory<CartItem,String>(tableColumnReference.getReferenceID()));
        }

        tableView.setItems(items);

        setItemClickListener(tableView,onTableViewRowClickListener);

        tableView.setPlaceholder(new Label(placeholder));


        /*
        final ObservableList<CartItem> cartItems = FXCollections.observableArrayList(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_QUANTITY.toString()));

        cart_quantity_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_QUANTITY.toString()));
        cart_product_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_DESCRIPTION.toString()));
        cart_price_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_TOTAL_PRICE.toString()));
        shopping_cart_list.setItems(cartItems);

        setItemClickListener(shopping_cart_list, (clickCount, selectedItem) -> {

            if (selectedItem instanceof CartItem)
            {
                cartSelectedItem = (CartItem) selectedItem;
                delete_cart_item_button.setVisible(true);
            }
        });

        shopping_cart_list.setPlaceholder(new Label(PlaceHolder.EMPTY_CART_LIST.toString()));
        */


    }

    // ---------------

    protected void intitializeTableView(TableView tableView,List<ProductItem> productItems, OnTableViewRowClickListener onTableViewRowClickListener, String placeholder, TableColumnReference...columnReferences)
    {

        final ObservableList<ProductItem> items = FXCollections.observableArrayList(productItems);

        for (TableColumnReference tableColumnReference: columnReferences)
        {
            tableColumnReference.getTableColumn().setCellValueFactory(new PropertyValueFactory<CartItem,String>(tableColumnReference.getReferenceID()));
        }

        tableView.setItems(items);

        setItemClickListener(tableView,onTableViewRowClickListener);

        tableView.setPlaceholder(new Label(placeholder));


        /*
        final ObservableList<CartItem> cartItems = FXCollections.observableArrayList(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_QUANTITY.toString()));

        cart_quantity_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_QUANTITY.toString()));
        cart_product_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_DESCRIPTION.toString()));
        cart_price_column.setCellValueFactory(new PropertyValueFactory<CartItem,String>(PropertyIdentifier.CART_ITEM_TOTAL_PRICE.toString()));
        shopping_cart_list.setItems(cartItems);

        setItemClickListener(shopping_cart_list, (clickCount, selectedItem) -> {

            if (selectedItem instanceof CartItem)
            {
                cartSelectedItem = (CartItem) selectedItem;
                delete_cart_item_button.setVisible(true);
            }
        });

        shopping_cart_list.setPlaceholder(new Label(PlaceHolder.EMPTY_CART_LIST.toString()));
        */


    }



}
