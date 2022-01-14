/*
 * Convert.java
 *
 * TCSS 371 assignment 1
 */

package code;

/**
 * A class to provide static methods for converting numbers between bases.
 *
 * @author Yudong Lin
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

    public static void main(String[] args) {
        var a = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '1'};
        var b = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '1'};
        System.out.println(Convert.convert2sCompToDecimal(a));
        System.out.println(Convert.convert2sCompToDecimal(b));
        System.out.println(Convert.convertDecimalTo2sComp(23));

    }

    /**
     * Accepts an array of characters representing the bits in a 2's complement number
     * and returns the decimal equivalent.
     * 
     * precondition:
     * This method assumes that the maximum length of the parameter array is 16.
     *
     * @param theBits an array representing the bits in a 2's complement number
     * @return the decimal equivalent of the 2's complement parameter
     */
    public static int convert2sCompToDecimal(final char[] theBits) {
        // in this case, I can use short, but for better compatibility, I choose to use int
        int result = 0;
        // determine whether the binary is positive by checking the first bit
        boolean is_positive = theBits[0] == '0';
        // loop through the char array and add value
        for (int i = 1; i < theBits.length; i++) {
            if ((is_positive && theBits[i] == '1') || (!is_positive && theBits[i] == '0')) {
                result += Math.pow(2, theBits.length - i - 1);
            }
        }
        // if the binary is negative, the negative the result, and minus 1 to get the correct result
        return is_positive ? result : -1 - result;
    }

    /**
     * Accepts a decimal parameter and returns an array of characters
     * representing the bits in the 16 bit two's complement equivalent.
     *
     * precondition:
     * This method assumes that the two's complement equivalent won't require more than 16 bits
     *
     * @param theDecimal the decimal number to convert to 2's complement
     * @return a 16 bit two's complement equivalent of the decimal parameter
     */
    public static char[] convertDecimalTo2sComp(final int theDecimal) {
        // create a char array for holding the data temporary
        char[] data = new char[16];
        // check whether the number is positive
        boolean is_positive = theDecimal >= 0;
        // if the number is negative, then convert it to positive for calculation
        int number = is_positive ? theDecimal : -theDecimal - 1;
        // create an index to track the "real" length of temporary char array
        short index = 0;
        // create a flag for check whether 1 or 0 should be place for positive and negative decimal respectably
        var flag = is_positive ? 1 : 0;
        // placing the 1 and 0 into char array
        while (number > 0) {
            data[index++] = number % 2 == flag ? '1' : '0';
            number /= 2;
        }
        // checking the offset between length of temporary char array and index
        var off_set = data.length - index;
        // if offset is 0, that means char array has been fill with the data, no shifting needed
        if (off_set == 0) {
            // so just return the array
            return data;
        } else {
            // if offset is not 0, that means shifting is needed
            // so create another char array to hold the result
            char[] result = new char[16];
            // loop through the array and place the data
            for (int i = 0; i < result.length; i++) {
                // filled the prefix with 0 and 1 depends on whether the decimal is positive or negative
                // afterwards, copy the data from the temporary char array and place them on the correct position
                result[i] = i < off_set ? (is_positive ? '0' : '1') : data[data.length - 1 - i];
            }
            // return the result
            return result;
        }
    }
}
