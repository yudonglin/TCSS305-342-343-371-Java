import java.util.Scanner;

/**
 * A {@code GeneralDataType} object is the super class of both Binary and Hexadecimal class.
 * It will handle most of the calculation since it provides a way for converting both Binary and Hexadecimal class into decimal for calculation
 * Based on the {@param positionalNotation} provided by itself child class
 */
abstract class GeneralDataType {
    private final String value;
    private final int positionalNotation;

    public GeneralDataType(String value, int positionalNotation) {
        this.value = value;
        this.positionalNotation = positionalNotation;
    }

    public String toString() {
        return this.value.toUpperCase();
    }

    /**
     * add two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType add(GeneralDataType o) {
        return this.positionalNotation == 2 ? new Binary(Long.toBinaryString(this.toDecimal() + o.toDecimal())) : new Hexadecimal(Long.toHexString(this.toDecimal() + o.toDecimal()));
    }

    /**
     * subtract two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType subtract(GeneralDataType o) {
        return this.positionalNotation == 2 ? new Binary(Long.toBinaryString(this.toDecimal() - o.toDecimal())) : new Hexadecimal(Long.toHexString(this.toDecimal() - o.toDecimal()));
    }

    /**
     * multiply two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType multiply(GeneralDataType o) {
        return this.positionalNotation == 2 ? new Binary(Long.toBinaryString(this.toDecimal() * o.toDecimal())) : new Hexadecimal(Long.toHexString(this.toDecimal() * o.toDecimal()));
    }

    /**
     * divide two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType divide(GeneralDataType o) {
        return this.positionalNotation == 2 ? new Binary(Long.toBinaryString(this.toDecimal() / o.toDecimal())) : new Hexadecimal(Long.toHexString(this.toDecimal() / o.toDecimal()));
    }

    /**
     * modulo two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType modulo(GeneralDataType o) {
        return this.positionalNotation == 2 ? new Binary(Long.toBinaryString(this.toDecimal() % o.toDecimal())) : new Hexadecimal(Long.toHexString(this.toDecimal() % o.toDecimal()));
    }

    /**
     * @return a Long that represent the value of this object in decimal integer
     */
    public long toDecimal() {
        return Long.parseUnsignedLong(this.value, this.positionalNotation);
    }
}

/**
 * A {@code Binary} object is the child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 2 as {@param positionalNotation}.
 */
class Binary extends GeneralDataType {
    public Binary(String value) {
        super(value, 2);
    }
}

/**
 * A {@code Hexadecimal} object is the child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 16 as {@param positionalNotation}.
 */
class Hexadecimal extends GeneralDataType {
    public Hexadecimal(String value) {
        super(value, 16);
    }
}

public class ObjectOrientedCLICalculator {
    // User input module
    private static final Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("What do you want to do?\n1) Binary Calculation—Add, Subtract, Multiply, or Divide\n2) Convert Binary Value to Decimal Value\n3) Convert Decimal Value to Binary Value\n4) Hexadecimal Calculation—Add, Subtract, Multiply, or Divide\n5) Convert Hexadecimal Value to Decimal Value\n6) Convert Decimal Value to Hexadecimal Value\nPlease enter the index:");
        var actionIndex = cliInput.nextLine();
        String dataType = (actionIndex.equals("1") || actionIndex.equals("2") || actionIndex.equals("3")) ? "Binary" : "Hexadecimal";
        switch (actionIndex) {
            case "1", "4" -> {
                System.out.println("Please Enter your first " + dataType + ":");
                var dataIn1 = dataType.equals("Binary") ? new Binary(cliInput.nextLine()) : new Hexadecimal(cliInput.nextLine());
                System.out.println("Please Enter your second " + dataType + ":");
                var dataIn2 = dataType.equals("Binary") ? new Binary(cliInput.nextLine()) : new Hexadecimal(cliInput.nextLine());
                System.out.println("What kind of calculation do you want to perform (+, -, *, /):");
                GeneralDataType result;
                String action;
                switch (cliInput.nextLine().toLowerCase()) {
                    case "add", "+" -> {
                        result = dataIn1.add(dataIn2);
                        action = "+";
                    }
                    case "subtract", "-" -> {
                        result = dataIn1.subtract(dataIn2);
                        action = "-";
                    }
                    case "multiply", "*" -> {
                        result = dataIn1.multiply(dataIn2);
                        action = "*";
                    }
                    case "divide", "/" -> {
                        result = dataIn1.divide(dataIn2);
                        action = "/";
                    }
                    default -> {
                        result = new Binary("");
                        action = "";
                    }
                }
                GeneralDataType remainder = action.equals("/") ? dataIn1.modulo(dataIn2) : new Binary("0");
                System.out.printf("%s value: %s %s %s = %s%s", dataType, dataIn1, action, dataIn2, result, (remainder.toDecimal() != 0 ? " Remainder: " + remainder : ""));
                System.out.printf("\nDecimal value: %d %s %d = %d%s", dataIn1.toDecimal(), action, dataIn2.toDecimal(), result.toDecimal(), (remainder.toDecimal() != 0 ? " Remainder: " + remainder.toDecimal() : ""));
            }
            case "2", "5" -> {
                System.out.println("Please Enter your " + dataType + " value:");
                System.out.println("Decimal value: " + (dataType.equals("Binary") ? new Binary(cliInput.nextLine()).toDecimal() : new Hexadecimal(cliInput.nextLine()).toDecimal()));
            }
            case "3", "6" -> {
                System.out.println("Please Enter your Decimal value:");
                System.out.println(dataType + " value: " + (dataType.equals("Binary") ? Long.toBinaryString(Long.parseLong(cliInput.nextLine())) : Long.toHexString(Long.parseLong(cliInput.nextLine())).toUpperCase()));
            }
        }
    }
}