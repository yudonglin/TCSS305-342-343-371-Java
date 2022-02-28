package simulator;

import java.util.Arrays;

/**
 * A BitString class represents a series of 1s and 0s and can hold up to
 * a maximum of 16 bits and also keeps track of the number of bits stored.
 * It has operations to do various operations associated with 1s and 0s - 
 * substring, append, copy, setting and getting 2s complement value, etc.
 *  
 * @author mmuppa
 * @author acfowler
 * @version 1.2
 */
public class BitString {

	// Constants for range checking
	private final static int MAX_BITS = 16;
	private final static int MAX_VALUE = 32767; // 2^15 - 1
	private final static int MIN_VALUE = -32768; // -2^15
	private final static int MAX_UNSIGNED_VALUE = 65535; // 2^16 - 1
	
	
	private char[] myBits;
	private int myLength;


	/**
	 * Sets the bits by copying the parameter and
	 * sets the length of the BitString. 
	 * @param theBits the char array representing the bits 
	 */
	public void setBits(char[] theBits) {
		if (theBits == null || theBits.length > MAX_BITS) {
			throw new IllegalArgumentException("Invalid input: null or exceeds bit string length");
		}
		myBits = Arrays.copyOf(theBits, theBits.length);
		myLength = theBits.length;
	}

	/**
	 * Flips all the bits of the BitString. 
	 */
	public void invert() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		for (int i = 0; i < myLength; i++) {
			if (myBits[i] == '0') {
				myBits[i] = '1';
			} else {
				myBits[i] = '0';
			}
		}
	}

	/**
	 * Adds 1 to the BitString. 
	 */
	public void addOne() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		for (int i = myLength - 1; i >= 0; i--) {
			if (myBits[i] == '0') {
				myBits[i] = '1';
				return;
			} else {
				myBits[i] = '0';
			}
		}
	}

	/**
	 * Sets the unsigned binary representation of the input 
	 * decimal number 
	 * @param n a positive decimal value
	 */
	public void setUnsignedValue(int n) {
		// Check if it can be represented in the bits available
		if (n < 0 || n > MAX_UNSIGNED_VALUE) {
			throw new IllegalArgumentException("Cannot represent in "
					+ MAX_BITS + " bits.");
		}
		
		myBits = new char[MAX_BITS];
		myLength = MAX_BITS;
		for (int i = myLength - 1; i >= 0; i--) {
			myBits[i] = (n % 2 == 0) ? '0' : '1';
			n /= 2;
		}
	}

	/**
	 * Sets the 2s complement binary value of the input value
	 * @param n a negative or positive decimal value
	 */
	public void set2sCompValue(int n) {
		if (n < MIN_VALUE || n > MAX_VALUE) {
			throw new IllegalArgumentException("Cannot represent in "
					+ MAX_BITS + " bits.");
		}
		boolean isNegative = n < 0;
		if (n < 0) {
			n *= -1;
		}
		setUnsignedValue(n);
		if (isNegative) {
			invert();
			addOne();
		}
	}

	/**
	 * Displays the BitString in groups of four or
	 * in one group of 16. 
	 * @param groupsOfFour 
	 */
	public void display(boolean groupsOfFour) {
		for (int i = 0; i < myLength; i++) {
			if (groupsOfFour && (i % 4 == 0) && i != 0) {
				System.out.print(" ");
			}
			if (myBits[i] == '0') {
				System.out.print("0");
			} else {
				System.out.print("1");
			}
		}
	}

	/**
	 * Returns a copy of the BitString
	 * @return copy of BitString object
	 */
	public BitString copy() {
		if (myBits == null) {
			throw new IllegalArgumentException("Nothing to copy.");
		}
		BitString copy = new BitString();
		copy.myLength = myLength;
		copy.myBits = Arrays.copyOf(myBits, myLength);
		return copy;
	}

	/**
	 * Returns the unsigned value of the BitString representation. 
	 * @return decimal unsigned value
	 */
	public int getUnsignedValue() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		int value = 0;
		for (int i = 0; i < myLength; i++) {
			value *= 2;
			value += myBits[i] == '1' ? 1 : 0;
		}
		return value;
	}

	/**
	 * Returns the 2s complement value of the BitString representation. 
	 * @return decimal value
	 */
	public int get2sCompValue() {
		if (myBits == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		boolean negative = myBits[0] == '1';
		if (negative) {
			BitString copyString = copy();
			copyString.invert();
			copyString.addOne();
			return -1 * copyString.getUnsignedValue();
		} else {
			return getUnsignedValue();
		}
	}

	/**
	 * Returns a new BitString that is the result of appending the parameter
	 * BitString to th end of the current BitString.
	 *  
	 * @param other the BitString to append
	 * @return a new BitString representing the parameter appended to this BitString  
	 */
	public BitString append(BitString other) {
		if (myBits == null || other == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		if (myLength + other.myLength > MAX_BITS) {
			throw new IllegalArgumentException("Exceeds bit string length");
		}
		
		BitString bitString = new BitString();
		bitString.myLength = myLength + other.myLength;
		bitString.myBits = new char[bitString.myLength];
		int index = 0;
		for (int i = 0; i < this.myLength; i++) {
			bitString.myBits[index++] = this.myBits[i];
		}
		for (int i = 0; i < other.myLength; i++) {
			bitString.myBits[index++] = other.myBits[i];
		}
		return bitString;
	}

	/**
	 * Returns a substring of the given string. 
	 * @param source
	 * @param start
	 * @param length
	 * @return A new BitString is created from the source starting at the
	 *         specified index and with the specified length.
	 */
	public BitString substring(int start, int length) {
		BitString subStr = new BitString();
		subStr.myBits = new char[length];
		int i;
		for (i = 0; i < length; i++) {
			subStr.myBits[i] = myBits[start + i];
		}
		subStr.myLength = length;
		return subStr;
	}

	/**
	 * Returns an array of the bits stored in the BitString
	 * @return character array of bits
	 */
	public char[] getBits() {
		return myBits;
	}

	/**
	 * Returns the length of the BitString
	 * @return length
	 */
	public int getLength() {
		return myLength;
	}

}