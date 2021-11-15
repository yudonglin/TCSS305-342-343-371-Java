/**
 * The super class of both Binary and Hexadecimal class.
 * It will handle most of the calculation since it provides a way for converting both Binary and Hexadecimal class into decimal for calculation
 * Based on the {@param positionalNotation} provided by itself child class
 */
abstract class GeneralDataType {
    private final String value;
    private final int positionalNotation;
    private final boolean isNegative;

    /**
     * @param value              the raw data of the object
     * @param positionalNotation positional notation that will be used in toDecimal() method
     */
    public GeneralDataType(String value, int positionalNotation) {
        this.positionalNotation = positionalNotation;
        this.isNegative = value.startsWith("-");
        this.value = this.isNegative ? value.replaceFirst("-", "0") : value;
        // check whether input value is valid
        try {
            this.toDecimal();
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid value.");
        }
    }

    /**
     * @param l will be used to generate a new GeneralDataType
     * @return a new GeneralDataType with the equivalent value of l
     */
    public abstract GeneralDataType fromDecimal(long l);

    /**
     * @return a string value that present the real value of this object
     */
    public String toString() {
        return this.isNegative ? this.value.toUpperCase().replaceFirst("0", "-") : this.value.toUpperCase();
    }

    /**
     * @return a Long that represent the value of this object in decimal integer
     */
    public long toDecimal() {
        return this.isNegative ? -Long.parseUnsignedLong(this.value, this.positionalNotation) : Long.parseUnsignedLong(this.value, this.positionalNotation);
    }

    /**
     * add two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType add(GeneralDataType o) {
        return fromDecimal(this.toDecimal() + o.toDecimal());
    }

    /**
     * subtract two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType subtract(GeneralDataType o) {
        return fromDecimal(this.toDecimal() - o.toDecimal());
    }

    /**
     * multiply two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType multiply(GeneralDataType o) {
        return fromDecimal(this.toDecimal() * o.toDecimal());
    }

    /**
     * divide two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType divide(GeneralDataType o) {
        return fromDecimal(this.toDecimal() / o.toDecimal());
    }

    /**
     * modulo two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType modulo(GeneralDataType o) {
        return fromDecimal(this.toDecimal() % o.toDecimal());
    }
}
