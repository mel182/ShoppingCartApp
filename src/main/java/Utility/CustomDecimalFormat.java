package Utility;

import java.text.DecimalFormat;

/**
 * <p>This is the custom decimal format class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class CustomDecimalFormat
{
    /**
     * Round value to tow decimal
     * @param value The target value
     * @return The value rounded to two decimal
     */
    public static double roundMaxTwoDecimal(double value)
    {
        return Math.round(value*100)/100.0d;
    }
}
