package Scenes.SceneController;

import CustomAbstractClasses.CustomBaseController;
import Discounts.Discounts;
import Interface.QuantitySelectionCallback;
import Model.CartItem;
import Model.ProductItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <p>This is the quantity selection scene controller which inherit the {@link CustomBaseController}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class QuantitySelectionController extends CustomBaseController
{
    //region FXML properties
    @FXML
    private Text product_text;

    @FXML
    private TextField quantity_textfield;

    @FXML
    private Button ok_button;

    @FXML
    private Button cancel_button;
    //endregion

    //region Local instances
    private QuantitySelectionCallback quantitySelectionCallback;
    private ProductItem selectedProduct;
    private static final String DEFAULT_QUANTITY = "1";
//    private static final String DECIMAL_ONLY_PATTERN = "\\d*";
    //endregion

    /**
     * Initialize data
     * @param productItem The target {@link ProductItem} object
     * @param quantitySelectionCallback The quantity selection callback
     */
    public void initializeData(ProductItem productItem, QuantitySelectionCallback quantitySelectionCallback)
    {
        this.selectedProduct = productItem;
        this.quantitySelectionCallback = quantitySelectionCallback;
        setProductName();
        setDefaultQuantity();
    }

    /**
     * Set product name
     */
    private void setProductName()
    {
        product_text.setText(selectedProduct.getName());
    }

    /**
     * Set default quantity value
     */
    private void setDefaultQuantity()
    {
        quantity_textfield.setText(DEFAULT_QUANTITY);
        quantity_textfield.textProperty().addListener((observable, oldValue, newValue) -> quantity_textfield.setText(newValue.matches(DECIMAL_ONLY_PATTERN) ? newValue : oldValue));
    }

    /**
     * Cancel task
     */
    public void cancelTask()
    {
        if (quantitySelectionCallback != null)
            quantitySelectionCallback.OnQuantitySelectionTaskCancel();

        closeWindow(cancel_button);
    }

    /**
     * Submit invoice result
     */
    public void submitResult()
    {
        int quantity = Integer.parseInt(quantity_textfield.getText());
        CartItem adjustedCartItem = Discounts.adjustPrice(new CartItem(selectedProduct,quantity,selectedProduct.getName()));

        if (quantitySelectionCallback != null)
        {
            quantitySelectionCallback.OnQuantitySelected(adjustedCartItem);
        }

        closeWindow(ok_button);
    }

    /**
     * Close window
     * @param button The target button
     */
    private void closeWindow(Button button)
    {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
