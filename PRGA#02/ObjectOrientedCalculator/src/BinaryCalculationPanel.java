/**
 * A {@code Binary} object is the child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 2 as {@param positionalNotation}.
 */
class Binary extends GeneralDataType {
    public Binary(String value) {
        super(value, 2);
    }

    public static Binary fromDecimal(String value) {
        return new Binary(Long.toBinaryString(Long.parseLong(value)));
    }

    public Binary valueOf(long value) {
        return new Binary(Long.toBinaryString(value));
    }
}

class BinaryCalculationPanel extends CalculationPanel {


    public BinaryCalculationPanel() {
        super("Binary");
    }

    public Binary creatNumObject(String text) {
        return new Binary(text);
    }

}

class BinaryToDecimalPanel extends ToDecimalPanel {

    public BinaryToDecimalPanel() {
        super("Binary");
    }

    public Binary creatNumObject(String text) {
        return new Binary(text);
    }
}

class DecimalToBinaryPanel extends FromDecimalPanel {

    public DecimalToBinaryPanel() {
        super("Binary");
    }

    protected Binary fromDecimal(String text) {
        return Binary.fromDecimal(text);
    }
}
