package Interface;

import Model.CartItem;

/**
 * <p>This is the quantity selection interface callback</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public interface QuantitySelectionCallback
{
    /**
     * On quantity selected
     * @param cartItem The {@link CartItem} contain the selected product
     */
    void OnQuantitySelected(CartItem cartItem);

    /**
     * On quantity selection task cancel
     */
    void OnQuantitySelectionTaskCancel();
}
