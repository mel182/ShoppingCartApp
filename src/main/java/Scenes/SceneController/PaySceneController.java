package Scenes.SceneController;

import CustomAbstractClasses.CustomBaseController;
import Enumeration.PlaceHolder;
import Enumeration.PropertyIdentifier;
import Model.CartItem;
import Model.TableColumnReference;
import Utility.CustomDecimalFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This is the invoice pay scene controller class which inherit the {@link CustomBaseController}</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class PaySceneController extends CustomBaseController
{
    //region FXML properties
    @FXML
    private TableView purchase_item_table_view;

    @FXML
    private TableColumn invoice_quantity_column;

    @FXML
    private TableColumn invoice_description_column;

    @FXML
    private TableColumn invoice_price_column;

    @FXML
    private Text sub_total;

    @FXML
    private Text vat;

    @FXML
    private Text total;

    @FXML
    private Button ok_button;
    //endregion

    //region Local instances
    private double sub_total_value = 0.0;
    private double vat_value = 0.0;
    private List<CartItem> purchase_list = new ArrayList<>();
    //endregion

    /**
     * Set purchase data
     * @param purchaseData The purchase data list
     */
    public void setPurchaseData(List<CartItem> purchaseData)
    {
        this.purchase_list = purchaseData;
        initialPurchaseTable();
         calculateSubTotal();
        calculateVAT();
        calculateTotal();
    }

    /**
     * Initialize purchase table
     */
    @SuppressWarnings("unchecked")
    private void initialPurchaseTable()
    {
        TableColumnReference[] column_references = {
                new TableColumnReference(invoice_quantity_column,PropertyIdentifier.CART_ITEM_QUANTITY.toString()),
                new TableColumnReference(invoice_description_column,PropertyIdentifier.CART_ITEM_DESCRIPTION.toString()),
                new TableColumnReference(invoice_price_column,PropertyIdentifier.CART_ITEM_TOTAL_PRICE.toString())
        };

        intitializeCartItemTableView(purchase_item_table_view,this.purchase_list,null, PlaceHolder.EMPTY_CART_LIST.toString(), column_references);
    }

    /**
     * Calculate invoice sub total
     */
    private void calculateSubTotal()
    {
        for (CartItem purchaseItem: purchase_list)
        {
            sub_total_value += purchaseItem.getTotal_price();
        }

        double raw_sub_total = CustomDecimalFormat.roundMaxTwoDecimal(sub_total_value);
        sub_total.setText(getValueIncludeCurrency(raw_sub_total));
    }

    /**
     * Calculate VAT (BTW)
     */
    private void calculateVAT()
    {
        double raw_vat_value = (sub_total_value/100)*21;

        vat_value = CustomDecimalFormat.roundMaxTwoDecimal(raw_vat_value);

        vat.setText(getValueIncludeCurrency(vat_value));
    }

    /**
     * Calculate total invoice price
     */
    private void calculateTotal()
    {
        double raw_total_value = sub_total_value + vat_value;

        double total_value = CustomDecimalFormat.roundMaxTwoDecimal(raw_total_value);

        total.setText(getValueIncludeCurrency(total_value));
    }

    /**
     * Close window
     */
    public void closeWindow()
    {
        Stage stage = (Stage) ok_button.getScene().getWindow();
        stage.close();
    }
}
