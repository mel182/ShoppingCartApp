package Utility;

import java.text.DecimalFormat;

/**
 * <p>This is the custom decimal format class</p>
 *
 * <p>Created by Melchior Vrolijk</p>
 */
public class CustomDecimalFormat
{
    private static final String TWO_DECIMAL_ROUND_PATTERN = "####0.00";

    /**
     * Round value to tow decimal
     * @param value The target value
     * @return The value rounded to two decimal
     */
    public static double roundMaxTwoDecimal(double value)
    {
        DecimalFormat decimalFormat = new DecimalFormat(TWO_DECIMAL_ROUND_PATTERN);
        return Double.valueOf(decimalFormat.format(value));
    }
}
