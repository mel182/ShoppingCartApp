package Enumeration;

/**
 * <p>This is enumeration class that contains the property identifier ID's.</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public enum PropertyIdentifier
{
    PRODUCT_NAME("name"),
    PRODUCT_PRICE("price"),
    CART_ITEM_QUANTITY("quantity"),
    CART_ITEM_DESCRIPTION("description"),
    CART_ITEM_TOTAL_PRICE("total_price");

    private final String id;

    /**
     * Constructor
     * @param ID The target property ID
     */
    PropertyIdentifier(final String ID)
    {
        this.id = ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return id;
    }
}
