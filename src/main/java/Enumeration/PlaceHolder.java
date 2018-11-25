package Enumeration;

/**
 * <p>This is enumeration class that contains the table view place holder text.</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public enum PlaceHolder
{
    EMPTY_CART_LIST("No item in cart"),
    EMPTY_PRODUCT_LIST("No product in list");

    private final String place_holder_text;

    /**
     * The constructor
     * @param place_holder_text The target place holder text
     */
    PlaceHolder (final String place_holder_text)
    {
        this.place_holder_text = place_holder_text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return place_holder_text;
    }
}
