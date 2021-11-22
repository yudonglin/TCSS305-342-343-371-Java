import java.math.BigInteger;

interface NumberInterface {

    /**
     * @return a long that represent the value of this object in decimal number
     */
    BigInteger toDecimal();

    /**
     * @param l will be used to generate a new GeneralDataType
     * @return a new GeneralDataType with the equivalent value of l
     */
    GeneralDataType fromDecimal(BigInteger l);

    /**
     * add two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    GeneralDataType add(GeneralDataType o);

    /**
     * subtract two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    GeneralDataType subtract(GeneralDataType o);

    /**
     * multiply two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    GeneralDataType multiply(GeneralDataType o);

    /**
     * divide two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    GeneralDataType divide(GeneralDataType o);

    /**
     * modulo two GeneralDataType, no matter whether they are Binary or Hexadecimal classes
     *
     * @return a new Binary or Hexadecimal object based on the positional notation of this class
     */
    GeneralDataType mod(GeneralDataType o);
}
