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
        try {
            this.toDecimal();
        } catch (Exception e) {
            throw new RuntimeException("Invalid value.");
        }
    }

    public abstract GeneralDataType valueOf(long l);

    public String toString() {
        return this.value.toUpperCase();
    }

    /**
     * @return a Long that represent the value of this object in decimal integer
     */
    public long toDecimal() {
        return Long.parseUnsignedLong(this.value, this.positionalNotation);
    }

    /**
     * add two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType add(GeneralDataType o) {
        return valueOf(this.toDecimal() + o.toDecimal());
    }

    /**
     * subtract two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType subtract(GeneralDataType o) {
        return valueOf(this.toDecimal() - o.toDecimal());
    }

    /**
     * multiply two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType multiply(GeneralDataType o) {
        return valueOf(this.toDecimal() * o.toDecimal());
    }

    /**
     * divide two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType divide(GeneralDataType o) {
        return valueOf(this.toDecimal() / o.toDecimal());
    }

    /**
     * modulo two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    public GeneralDataType modulo(GeneralDataType o) {
        return valueOf(this.toDecimal() % o.toDecimal());
    }
}
