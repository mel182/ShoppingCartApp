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

    /**
     * Initialize table view based on the properties provided
     * @param tableView The target table view
     * @param cartItems The {@link CartItem} object list containing the cart item properties
     * @param onTableViewRowClickListener The interface callback instance
     * @param placeholder The table view place holder
     * @param columnReferences The column reference list containing the table column and the property ID
     */
    @SuppressWarnings("unchecked")
    protected void initializeCartItemTableView(TableView tableView, List<CartItem> cartItems, @Nullable OnTableViewRowClickListener onTableViewRowClickListener, String placeholder, TableColumnReference...columnReferences)
    {
        final ObservableList<CartItem> items = FXCollections.observableArrayList(cartItems);

        for (TableColumnReference tableColumnReference: columnReferences)
        {
            tableColumnReference.getTableColumn().setCellValueFactory(new PropertyValueFactory<CartItem,String>(tableColumnReference.getReferenceID()));
        }

        tableView.setItems(items);
        setItemClickListener(tableView,onTableViewRowClickListener);
        tableView.setPlaceholder(new Label(placeholder));
    }

    /**
     * Initialize table view based on the properties provided
     * @param tableView The target table view
     * @param productItems The {@link ProductItem} object list containing the product properties
     * @param onTableViewRowClickListener The interface callback instance
     * @param placeholder The table view place holder
     * @param columnReferences The column reference list containing the table column and the property ID
     */
    @SuppressWarnings("unchecked")
    protected void initializeTableView(TableView tableView, List<ProductItem> productItems, OnTableViewRowClickListener onTableViewRowClickListener, String placeholder, TableColumnReference...columnReferences)
    {
        final ObservableList<ProductItem> items = FXCollections.observableArrayList(productItems);

        for (TableColumnReference tableColumnReference: columnReferences)
        {
            tableColumnReference.getTableColumn().setCellValueFactory(new PropertyValueFactory<CartItem,String>(tableColumnReference.getReferenceID()));
        }

        tableView.setItems(items);
        setItemClickListener(tableView,onTableViewRowClickListener);
        tableView.setPlaceholder(new Label(placeholder));
    }
}
