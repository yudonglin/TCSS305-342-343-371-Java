import java.util.Scanner;

public class ProceduralCLICalculator {
    // User input module
    private static final Scanner cliInput = new Scanner(System.in);
    // A string of
    private static final String hexCharsString = "0123456789ABCDEF";
    // Error identifier
    private static final String ERROR_RESULT = "ERROR";

    public static void main(String[] args) {
        System.out.println("What do you want to do?\n1) Binary Calculation—Add, Subtract, Multiply, or Divide\n2) Convert Binary Value to Decimal Value\n3) Convert Decimal Value to Binary Value\n4) Hexadecimal Calculation—Add, Subtract, Multiply, or Divide\n5) Convert Hexadecimal Value to Decimal Value\n6) Convert Decimal Value to Hexadecimal Value\nPlease enter the index:");
        var actionIndex = cliInput.nextLine();
        String dataType = (actionIndex.equals("1") || actionIndex.equals("2") || actionIndex.equals("3")) ? "Binary" : "Hexadecimal";
        switch (actionIndex) {
            case "1", "4" -> {
                System.out.println("Please Enter your first " + dataType + ":");
                var dataIn1 = cliInput.nextLine();
                System.out.println("Please Enter your second " + dataType + ":");
                var dataIn2 = cliInput.nextLine();
                System.out.println("What kind of calculation do you want to perform (+, -, *, /):");
                String result = "";
                String action = "";
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
                }
                String remainder = action.equals("/") && !result.equals(ERROR_RESULT)? (dataType.equals("Binary") ? moduloBinary(dataIn1, dataIn2) : moduloHex(dataIn1, dataIn2)) : "0";
                System.out.printf("%s value: %s %s %s = %s%s", dataType, dataIn1, action, dataIn2, result, (!remainder.equals("0") ? " Remainder: " + remainder : ""));
                if (!result.equals(ERROR_RESULT)) {
                    long dataInDecimal1 = dataType.equals("Binary") ? binaryToDecimal(dataIn1) : hexToDecimal(dataIn1);
                    long dataInDecimal2 = dataType.equals("Binary") ? binaryToDecimal(dataIn2) : hexToDecimal(dataIn2);
                    long resultInDecimal = dataType.equals("Binary") ? binaryToDecimal(result) : hexToDecimal(result);
                    System.out.printf(
                            "\nDecimal value: %d %s %d = %d%s", dataInDecimal1, action, dataInDecimal2, resultInDecimal,
                            (!remainder.equals("0") ? " Remainder: " + (dataType.equals("Binary") ? binaryToDecimal(remainder) : hexToDecimal(remainder)) : "")
                    );
                }
            }
            case "2", "5" -> {
                System.out.println("Please Enter your " + dataType + " value:");
                System.out.println("Decimal value: " + (dataType.equals("Binary") ? binaryToDecimal(cliInput.nextLine()) : hexToDecimal(cliInput.nextLine())));
            }
            case "3", "6" -> {
                System.out.println("Please Enter your Decimal value:");
                System.out.println(
                        dataType + " value: " + (
                                dataType.equals("Binary") ? decimalToBinary(stringToLong(cliInput.nextLine())) : decimalToHex(stringToLong(cliInput.nextLine()))
                        )
                );
            }
        }
    }

    private static long stringToLong(String numLikedStr) {
        long result = 0;
        boolean isNegative = numLikedStr.charAt(0) == '-';
        // Process each character of the string;
        for (int i = !isNegative ? 0 : 1; i < numLikedStr.length(); i++) {
            result *= 10;
            result += numLikedStr.charAt(i) - '0';
        }
        return !isNegative ? result : -result;
    }

    /**
     * Convert a decimal integer to a binary format String
     *
     * @param num_in a long that will be converted to Binary String
     * @return a binary format String
     */
    public static String decimalToBinary(long num_in) {
        if (num_in == 0) {
            return "0";
        } else {
            StringBuilder binaryOut = new StringBuilder();
            boolean isNegative = num_in < 0;
            while (num_in != 0) {
                binaryOut.insert(0, ((num_in % 2) == 0 ? "0" : "1"));
                num_in = num_in / 2;
            }
            return !isNegative ? binaryOut.toString() : "-" + binaryOut;
        }
    }

    /**
     * Convert a binary format String to a decimal integer
     *
     * @param binaryIn the binary String that will be converted to an integer
     * @return a long
     */
    public static long binaryToDecimal(String binaryIn) {
        long num_out = 0;
        int starting_point = binaryIn.charAt(0) == '-' ? 1 : 0;
        for (int i = starting_point; i < binaryIn.length(); i++) {
            if (binaryIn.charAt(i) == '1') {
                num_out += Math.pow(2, (binaryIn.length() - i - 1));
            }
        }
        return starting_point == 0 ? num_out : -num_out;
    }

    /**
     * Convert a decimal integer to a hexadecimal format String
     *
     * @param decimal_in a long that will be converted to a hexadecimal String
     * @return hexadecimal format String
     */
    public static String decimalToHex(long decimal_in) {
        if (decimal_in == 0) {
            return "0";
        } else {
            StringBuilder hex_out = new StringBuilder();
            boolean isNegative = false;
            if (decimal_in < 0) {
                isNegative = true;
                decimal_in = -decimal_in;
            }
            while (decimal_in != 0) {
                hex_out.insert(0, hexCharsString.charAt((int) (decimal_in % hexCharsString.length())));
                decimal_in /= 16;
            }
            return !isNegative ? hex_out.toString() : "-" + hex_out;
        }
    }

    /**
     * Convert a hexadecimal format String to a decimal integer
     *
     * @param hexIn the hexadecimal String that will be converted to an integer
     * @return a long
     */
    public static long hexToDecimal(String hexIn) {
        long decimal_out = 0;
        hexIn = hexIn.toUpperCase();
        int starting_point = hexIn.charAt(0) == '-' ? 1 : 0;
        for (int i = starting_point; i < hexIn.length(); i++) {
            decimal_out = 16 * decimal_out + hexCharsString.indexOf(hexIn.charAt(i));
        }
        return starting_point == 1 ? -decimal_out : decimal_out;
    }

    /**
     * Add two Binary format Strings, return the production of the calculation
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String addBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) + binaryToDecimal(binaryIn2));
    }

    /**
     * Subtract two Binary format Strings, return the production of the calculation
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String subtractBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) - binaryToDecimal(binaryIn2));
    }

    /**
     * Multiply two Binary format Strings, return the production of the calculation
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String multiplyBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) * binaryToDecimal(binaryIn2));
    }

    /**
     * Divide two Binary format Strings, return the production of the calculation
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String divideBinary(String binaryIn1, String binaryIn2) {
        var num2 = binaryToDecimal(binaryIn2);
        if (num2 != 0) {
            return decimalToBinary(binaryToDecimal(binaryIn1) / num2);
        } else {
            System.out.println("Dividing by Zero is not allowed");
            return ERROR_RESULT;
        }
    }

    /**
     * Modulo two Binary format Strings, return the production of the calculation
     *
     * @param binaryIn1 a Binary format String
     * @param binaryIn2 another Binary format String
     * @return a new Binary format String
     */
    public static String moduloBinary(String binaryIn1, String binaryIn2) {
        return decimalToBinary(binaryToDecimal(binaryIn1) % binaryToDecimal(binaryIn2));
    }

    /**
     * Add two Hexadecimal format Strings, return the production of the calculation
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String addHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) + hexToDecimal(hexIn2));
    }

    /**
     * Subtract two Hexadecimal format Strings, return the production of the calculation
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String subtractHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) - hexToDecimal(hexIn2));
    }

    /**
     * Multiply two Hexadecimal format Strings, return the production of the calculation
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String multiplyHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) * hexToDecimal(hexIn2));
    }

    /**
     * Divide two Hexadecimal format Strings, return the production of the calculation
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String divideHex(String hexIn1, String hexIn2) {
        var num2 = hexToDecimal(hexIn2);
        if (num2 != 0) {
            return decimalToHex(hexToDecimal(hexIn1) / num2);
        } else {
            System.out.println("Dividing by Zero is not allowed");
            return ERROR_RESULT;
        }
    }

    /**
     * Modulo two Hexadecimal format Strings, return the production of the calculation
     *
     * @param hexIn1 a Hexadecimal format String
     * @param hexIn2 another Hexadecimal format String
     * @return a new Hexadecimal format String
     */
    public static String moduloHex(String hexIn1, String hexIn2) {
        return decimalToHex(hexToDecimal(hexIn1) % hexToDecimal(hexIn2));
    }
}
