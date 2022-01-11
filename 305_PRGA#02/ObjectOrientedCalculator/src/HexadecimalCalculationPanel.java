import java.math.BigInteger;

/**
 * The child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 16 as {@param positionalNotation}.
 */
class Hexadecimal extends GeneralDataType {

    /**
     * default method, create a Hexadecimal object that is equivalent to integer 0
     */
    public Hexadecimal() {
        super("0", 16);
    }

    /**
     * @param value is used to create a new Hexadecimal object
     */
    public Hexadecimal(String value) {
        super(value, 16);
    }

    /**
     * @param value a long format string that will be used to create a new Hexadecimal object
     * @return a new Hexadecimal object has an equivalent value to the input value
     */
    public static Hexadecimal valueOf(String value) {
        return new Hexadecimal().fromDecimal(new BigInteger(value));
    }

    /**
     * @param value a long number that will be used to create a new Hexadecimal object
     * @return a new Hexadecimal object has an equivalent value to the input value
     */
    public Hexadecimal fromDecimal(BigInteger value) {
        return new Hexadecimal(value.compareTo(BigInteger.ZERO) >= 0 ? value.toString(16) : "-" + value.negate().toString(16));
    }

}

class HexadecimalCalculationPanel extends CalculationPanel {

    /**
     * create the panel for Hexadecimal Calculation
     */
    public HexadecimalCalculationPanel() {
        super("Hexadecimal");

    }

    /**
     * @param text the value that will be used to create return Hexadecimal
     * @return a new Hexadecimal object created using the input text
     */
    protected Hexadecimal creatNumObject(String text) {
        return new Hexadecimal(text);
    }
}

class HexadecimalToDecimalPanel extends ToDecimalPanel {

    /**
     * create the panel for Hexadecimal To Decimal Conversion
     */
    public HexadecimalToDecimalPanel() {
        super("Hexadecimal");
    }

    /**
     * @param text the value that will be used to create return Hexadecimal
     * @return a new Hexadecimal object created using the input text
     */
    protected Hexadecimal creatNumObject(String text) {
        return new Hexadecimal(text);
    }

}

class DecimalToHexadecimalPanel extends FromDecimalPanel {

    /**
     * create the panel for Decimal To Hexadecimal Conversion
     */
    public DecimalToHexadecimalPanel() {
        super("Hexadecimal");

    }

    /**
     * @param text the raw value that will be used to generate return Hexadecimal
     * @return a new Hexadecimal object created using the input string format integer
     */
    protected Hexadecimal fromDecimal(String text) {
        return Hexadecimal.valueOf(text);
    }

}