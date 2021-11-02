import java.util.Scanner;

public class ProceduralCLICalculator {

    private static final Scanner cliInput = new Scanner(System.in);
    private static final String hex_chars_array = "0123456789ABCDEF";

    public static void main(String[] args) {
        System.out.println("What do you want to calculate (Binary / Hexadecimal):");
        String dataType = cliInput.nextLine().equalsIgnoreCase("binary") ? "Binary" : "Hexadecimal";
        System.out.println("Please Enter your first " + dataType + ":");
        String dataIn1 = cliInput.nextLine();
        System.out.println("Please Enter your second " + dataType + ":");
        String dataIn2 = cliInput.nextLine();
        System.out.println("What kind of calculation do you want to perform (+, -, *, /):");
        String result;
        String action;
        switch (cliInput.nextLine().toLowerCase()) {
            case "add", "+" -> {
                result = dataType.equals("Binary") ? addBinary(dataIn1, dataIn2) : addHex(dataIn1, dataIn2);
                action = "+";
            }
            case "subtract", "-" -> {
                result = dataType.equals("Binary") ? subtractBinary(dataIn1, dataIn2) : subtractHex(dataIn1, dataIn2);
                action = "-";
            }
            case "multiply", "*" -> {
                result = dataType.equals("Binary") ? multiplyBinary(dataIn1, dataIn2) : multiplyHex(dataIn1, dataIn2);
                action = "*";
            }
            case "divide", "/" -> {
                result = dataType.equals("Binary") ? divideBinary(dataIn1, dataIn2) : divideHex(dataIn1, dataIn2);
                action = "/";
            }
            default -> {
                result = "";
                action = "";
            }
        }
        System.out.printf("%s value: %s %s %s = %s", dataType, dataIn1, action, dataIn2, result);
        System.out.println();
        int dataInDecimal1 = dataType.equals("Binary") ? binaryToDecimal(dataIn1) : hexToDecimal(dataIn1);
        int dataInDecimal2 = dataType.equals("Binary") ? binaryToDecimal(dataIn2) : hexToDecimal(dataIn2);
        int resultInDecimal = dataType.equals("Binary") ? binaryToDecimal(result) : hexToDecimal(result);
        System.out.printf("Decimal value: %d %s %d = %d", dataInDecimal1, action, dataInDecimal2, resultInDecimal);
    }

    /**
     * Convert a decimal integer to a binary format String
     *
     * @param num_in an int that will be converted to Binary String
     * @return a binary format String
     */
    public static String decimalToBinary(int num_in) {
        if (num_in == 0) {
            return "0";
        } else {
            String binaryOutReversed = "";
            boolean isNegative = num_in < 0;
            while (Math.abs(num_in) > 0) {
                binaryOutReversed = binaryOutReversed.concat(((num_in % 2) == 0 ? "0" : "1"));
                num_in = num_in / 2;
            }
            if (isNegative) {
                binaryOutReversed = binaryOutReversed.concat("-");
            }
            String binaryOut = "";
            for (int i = binaryOutReversed.length() - 1; i >= 0; i--) {
                binaryOut = binaryOut.concat(String.valueOf(binaryOutReversed.charAt(i)));
            }
            return binaryOut;
        }
    }

    /**
     * Convert a binary format String to a decimal integer
     *
     * @param binaryIn the binary String that will be converted to an integer
     * @return an int
     */
    public static int binaryToDecimal(String binaryIn) {
        int num_out = 0;
        if (binaryIn.charAt(0) == '-') {
            for (int i = 1; i < binaryIn.length(); i++) {
                if (binaryIn.charAt(i) == '1') {
                    num_out -= Math.pow(2, (binaryIn.length() - i - 1));
                }
            }
        } else {
            for (int i = 0; i < binaryIn.length(); i++) {
                if (binaryIn.charAt(i) == '1') {
                    num_out += Math.pow(2, (binaryIn.length() - i - 1));
                }
            }
        }
        return num_out;
    }

    /**
     * Convert a decimal integer to a hexadecimal format String
     *
     * @param decimal_in an int that will be converted to a hexadecimal String
     * @return hexadecimal format String
     */
    public static String decimalToHex(int decimal_in) {
        String hex_out = "";
        while (decimal_in > 0) {
            hex_out = hex_chars_array.charAt(decimal_in % hex_chars_array.length()) + hex_out;
            decimal_in /= 16;
        }
        return hex_out;
    }

    /**
     * Convert a hexadecimal format String to a decimal integer
     *
     * @param hexIn the hexadecimal String that will be converted to an integer
     * @return an int
     */

    public static int hexToDecimal(String hexIn) {
        int decimal_out = 0;
        for (char char_letter : hexIn.toUpperCase().toCharArray()) {
            decimal_out = 16 * decimal_out + hex_chars_array.indexOf(char_letter);
        }
        return decimal_out;
    }

    /**
     * Add two Binary format Strings
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String addBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) + binaryToDecimal(binaryIn2));
    }

    /**
     * Subtract two Binary format Strings
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */

    public static String subtractBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) - binaryToDecimal(binaryIn2));
    }

    /**
     * Multiply two Binary format Strings
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */

    public static String multiplyBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) * binaryToDecimal(binaryIn2));
    }

    /**
     * Divide two Binary format Strings
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */

    public static String divideBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) / binaryToDecimal(binaryIn2));
    }

    /**
     * Add two Hexadecimal format Strings
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */

    public static String addHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) + hexToDecimal(hexIn2));
    }

    /**
     * Subtract two Hexadecimal format Strings
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String subtractHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) - hexToDecimal(hexIn2));
    }

    /**
     * Multiply two Hexadecimal format Strings
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String multiplyHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) * hexToDecimal(hexIn2));
    }

    /**
     * Divide two Hexadecimal format Strings
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String divideHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) / hexToDecimal(hexIn2));
    }
}
