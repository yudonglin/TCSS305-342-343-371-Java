/**
 * The child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 2 as {@param positionalNotation}.
 */
class Binary extends GeneralDataType {

    /**
     * default method, create a Binary object that is equivalent to integer 0
     */
    public Binary() {
        super("0", 2);
    }

    /**
     * @param value is used to create a new Binary object
     */
    public Binary(String value) {
        super(value, 2);
    }

    /**
     * @param value a long format string that will be used to create a new Binary object
     * @return a new Binary object has an equivalent value to the input value
     */
    public static Binary valueOf(String value) {
        return new Binary().fromDecimal(Long.parseLong(value));
    }

    /**
     * @param value a long number that will be used to create a new Binary object
     * @return a new Binary object has an equivalent value to the input value
     */
    public Binary fromDecimal(long value) {
        return new Binary(value >= 0 ? Long.toBinaryString(value) : "-" + Long.toBinaryString(Math.abs(value)));
    }
}

class BinaryCalculationPanel extends CalculationPanel {

    /**
     * create the panel for Binary Calculation
     */
    public BinaryCalculationPanel() {
        super("Binary");
    }

    /**
     * @param text the value that will be used to create return Binary
     * @return a new Binary object created using the input text
     */
    public Binary creatNumObject(String text) {
        return new Binary(text);
    }

}

class BinaryToDecimalPanel extends ToDecimalPanel {

    /**
     * create the panel for Binary To Decimal Conversion
     */
    public BinaryToDecimalPanel() {
        super("Binary");
    }

    /**
     * @param text the value that will be used to create return Binary
     * @return a new Binary object created using the input text
     */
    public Binary creatNumObject(String text) {
        return new Binary(text);
    }
}

class DecimalToBinaryPanel extends FromDecimalPanel {

    /**
     * create the panel for Decimal To Binary Conversion
     */
    public DecimalToBinaryPanel() {
        super("Binary");
    }

    /**
     * @param text the raw value that will be used to generate return Binary
     * @return a new Binary object created using the input text
     */
    protected Binary fromDecimal(String text) {
        return Binary.valueOf(text);
    }
}
