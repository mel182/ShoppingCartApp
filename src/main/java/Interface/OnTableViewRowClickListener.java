package Interface;

/**
 * <p>This is the table view on row clicked interface callback used for reported item that has been clicked in the table view</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public interface OnTableViewRowClickListener
{
    /**
     * On Click function
     * @param clickCount The total amount of clicks
     * @param selectedItem The selected item ({@link Object})
     */
    void OnClick(int clickCount,Object selectedItem);
}
