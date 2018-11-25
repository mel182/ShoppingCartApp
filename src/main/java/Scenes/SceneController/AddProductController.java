package Scenes.SceneController;

import CustomAbstractClasses.CustomBaseController;
import Model.ProductItem;
import ProductList.ProductList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <p>This is the add product scene controller class which inherit the {@link CustomBaseController}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class AddProductController extends CustomBaseController
{
    //region FXML properties
    @FXML
    private TextField product_description_textfield;

    @FXML
    private TextField product_price_textfield;

    @FXML
    private TextField product_price_decimal_textfield;

    @FXML
    private Text notification_text;

    @FXML
    private Button add_button;
    //endregion

    /**
     * Initialize controller
     */
    public void initialize()
    {
        product_price_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            resetNotificationTextState();
            product_price_textfield.setText(newValue.matches(DECIMAL_ONLY_PATTERN) ? newValue : oldValue);
        });

        product_price_decimal_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            resetNotificationTextState();
            product_price_decimal_textfield.setText(newValue.matches(DECIMAL_ONLY_PATTERN) ? newValue : oldValue);
        });
    }

    /**
     * Reset notification text state
     */
    private void resetNotificationTextState()
    {
        notification_text.setText("");
    }

    /**
     * Submit result
     */
    public void submit()
    {

        if (product_description_textfield.getText().equals(""))
        {
            notification_text.setText("Please fill product description");
            return;
        }

        if (product_price_textfield.getText().equals("") && product_price_decimal_textfield.getText().equals(""))
        {
            notification_text.setText("Please fill product price");
            return;
        }

        ProductItem new_product_item = new ProductItem(new SimpleStringProperty(product_description_textfield.getText().trim()), new SimpleDoubleProperty(getPrice()));
        ProductList.getInstance().getList().add(new_product_item);

        closeWindow();
    }

    /**
     * Close window
     */
    private void closeWindow()
    {
        Stage stage = (Stage) add_button.getScene().getWindow();
        stage.close();
    }

    /**
     * Get price by converting the inserted string to double
     * @return The product price
     */
    private double getPrice()
    {
        String raw_price = String.format("%s.%s",product_price_textfield.getText().trim(),product_price_decimal_textfield.getText().trim());
        return Double.parseDouble(raw_price);
    }
}
