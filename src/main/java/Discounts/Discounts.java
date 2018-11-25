package Discounts;

import Model.CartItem;
import ProductList.DefaultProductList;

import java.util.Calendar;

/**
 * <p>This is the discounts class which handles all task related to discounts and promotions.</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class Discounts
{
    /**
     * Adjust price based on the discount promotion to the target product
     *<p></p>
     * <ul>
     *     <li>Robijn promotion</li>
     *     <ul>
     *         <li>When 2 unit are purchased, 31% discount will be given</li>
     *     </ul>
     *     <li>Farmer fresh cheese promotion</li>
     *     <ul>
     *         <li>On wednesday the price will be set to € 1.00 instead of € 2.00 per unit</li>
     *     </ul>
     *     <li>Diaper promotion</li>
     *     <ul>
     *         <li>Buy 4 and get 1 free</li>
     *     </ul>
     * </ul>
     * <p></p>
     * @param cartItem The {@link CartItem} object containing the corresponding product properties
     * @return The adjusted {@link CartItem} object
     */
    public static CartItem adjustPrice(CartItem cartItem)
    {
        switch (cartItem.getProductItem().getName())
        {
            case DefaultProductList.ROBIJN:
                if (cartItem.getQuantity() == 2)
                {
                    int discount_rate = 31;
                    cartItem.setDescription(String.format("%s - %d %% discount",DefaultProductList.ROBIJN,discount_rate));
                    cartItem.setTotal_price(getNewPrice(cartItem,discount_rate));

                }else{
                    cartItem.setTotal_price(calculateTotalPrice(cartItem).getTotal_price());
                }
                break;
            case DefaultProductList.FARMER_CHEESE:

                if (getCurrentDayOfWeek() == Calendar.WEDNESDAY)
                {
                    cartItem.setDescription(String.format("%s (wednesday 1.00 promotion)",DefaultProductList.FARMER_CHEESE));
                    cartItem.getProductItem().setPrice(1.0);
                    cartItem.setTotal_price(calculateTotalPrice(cartItem).getTotal_price());

                }else{
                    cartItem.setTotal_price(calculateTotalPrice(cartItem).getTotal_price());
                }

                break;
            case DefaultProductList.DIAPER:

                if (cartItem.getQuantity() == 4)
                {
                    double price = 3 * cartItem.getProductItem().getPrice();
                    cartItem.setDescription(String.format("%s (buy 3 get 1 free)",cartItem.getProductItem().getName()));
                    cartItem.setTotal_price(price);
                }

                break;
            default:
                cartItem.setTotal_price(calculateTotalPrice(cartItem).getTotal_price());
                break;
        }

        return cartItem;
    }

    /**
     * Get new product new price by deduct the discount rate
     * @param cartItem The {@link CartItem} contain the product properties
     * @param discount_rate The discount rate
     * @return The new price with the deducted discount rate
     */
    private static double getNewPrice(CartItem cartItem, int discount_rate)
    {
        double price_without_discount = cartItem.getProductItem().getPrice() * cartItem.getQuantity();

        double new_price_percentage_rate = 100 - discount_rate;

        return (new_price_percentage_rate * price_without_discount)/100;
    }

    /**
     * Get the current day of the week
     * @return The current day of the week int value
     */
    private static int getCurrentDayOfWeek()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Calculate total price of the target cart item
     * @param cartItem The target {@link CartItem} object
     * @return {@link CartItem} object including the adjusted total price
     */
    private static CartItem calculateTotalPrice(CartItem cartItem)
    {
        double total_price  = cartItem.getQuantity() * cartItem.getProductItem().getPrice();
        cartItem.setTotal_price(total_price);

        return cartItem;
    }
}
