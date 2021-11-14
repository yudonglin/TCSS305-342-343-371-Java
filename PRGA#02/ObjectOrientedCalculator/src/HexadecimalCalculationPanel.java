/**
 * A {@code Hexadecimal} object is the child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 16 as {@param positionalNotation}.
 */
class Hexadecimal extends GeneralDataType {
    public Hexadecimal(String value) {
        super(value, 16);
    }

    public static Hexadecimal fromDecimal(String value) {
        return new Hexadecimal(Long.toHexString(Long.parseLong(value)));
    }

    public Hexadecimal valueOf(long value) {
        return new Hexadecimal(Long.toHexString(value));
    }
}

class HexadecimalCalculationPanel extends CalculationPanel {


    public HexadecimalCalculationPanel() {
        super("Hexadecimal");

    }

    protected Hexadecimal creatNumObject(String text) {
        return new Hexadecimal(text);
    }
}

class HexadecimalToDecimalPanel extends ToDecimalPanel {

    public HexadecimalToDecimalPanel() {
        super("Hexadecimal");
    }

    protected Hexadecimal creatNumObject(String text) {
        return new Hexadecimal(text);
    }

}

class DecimalToHexadecimalPanel extends FromDecimalPanel {

    public DecimalToHexadecimalPanel() {
        super("Hexadecimal");

    }

    protected Hexadecimal fromDecimal(String text) {
        return Hexadecimal.fromDecimal(text);
    }

}