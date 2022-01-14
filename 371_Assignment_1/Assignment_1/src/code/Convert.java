/*
 * Convert.java
 *
 * TCSS 371 assignment 1
 */

package code;

/**
 * A class to provide static methods for converting numbers between bases.
 *
 * @author Your name here
 * @version 1.0
 */
public final class Convert {

    /**
     * A private constructor to inhibit instantiation of this class.
     */
    private Convert() {
        // Objects should not be instantiated from this class.
        // This class is just a home for static methods (functions).
        // This design is similar to the Math class in the Java language.
    }

    /**
     * Accepts an array of characters representing the bits in a 2's complement number
     * and returns the decimal equivalent.
     * <p>
     * precondition:
     * This method assumes that the maximum length of the parameter array is 16.
     *
     * @param theBits an array representing the bits in a 2's complement number
     * @return the decimal equivalent of the 2's complement parameter
     */
    public static int convert2sCompToDecimal(final char[] theBits) {

        int result = 0;

        boolean is_positive = theBits[0] == '0';

        for (int i = theBits.length - 1; i > 0; i--) {
            if (is_positive) {
                if (theBits[i] == '1') {
                    result += Math.pow(2, theBits.length - i - 1);
                }
            } else {
                if (theBits[i] == '0') {
                    result += Math.pow(2, theBits.length - i - 1);
                }
            }
        }

        return is_positive ? result : -1 - result;
    }


    /**
     * Accepts a decimal parameter and returns an array of characters
     * representing the bits in the 16 bit two's complement equivalent.
     * <p>
     * precondition:
     * This method assumes that the two's complement equivalent won't require more than 16 bits
     *
     * @param theDecimal the decimal number to convert to 2's complement
     * @return a 16 bit two's complement equivalent of the decimal parameter
     */
    public static char[] convertDecimalTo2sComp(final int theDecimal) {
        char[] data = new char[16];
        char[] result = new char[16];

        for (int i = 0; i < data.length; i++) {
            data[i] = '0';
            result[i] = '0';
        }

        boolean is_positive = theDecimal >= 0;

        int number = is_positive ? theDecimal : -theDecimal-1;

        short index = 0;

        while (number > 0) {
            data[index] = number % 2 == 1 ?  '1': '0';
            index += 1;
            number /= 2;
        }

        var off_set = result.length - index;
        for (short i = 0; i < index; i++) {
            result[i + off_set] = data[i];
        }

        // flip 2s when negative
        if (!is_positive) {
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] == '1' ? '0' : '1';
            }
        }

        return result;
    }

    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */

}
