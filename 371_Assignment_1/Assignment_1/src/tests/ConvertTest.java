/*
 * ConvertTest
 */

package tests;

import code.Convert;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests of the methods in the Convert class.
 *
 * @author Alan Fowler
 * @version 1.0
 */
class ConvertTest {

    // Binary to Decimal Tests

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompPositive0with1Bit() {
        final char[] data = {'0'};
        assertEquals(0, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompPositive0with16Bits() {
        final char[] data = {'0', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0'};
        assertEquals(0, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompPositive1() {
        final char[] data = {'0', '1'};
        assertEquals(1, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompNegative1with1bit() {
        final char[] data = {'1'};
        assertEquals(-1, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompNegative1with16Bits() {
        final char[] data = {'1', '1', '1', '1', '1', '1', '1', '1',
                '1', '1', '1', '1', '1', '1', '1', '1'};
        assertEquals(-1, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompMaxPos16BitValue() {
        final char[] data = {'0', '1', '1', '1', '1', '1', '1', '1',
                '1', '1', '1', '1', '1', '1', '1', '1'};
        assertEquals(32767, Convert.convert2sCompToDecimal(data));
    }

    /**
     * Test method for {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    public void test2sCompMinNeg16BitValue() {
        final char[] data = {'1', '0', '0', '0', '0', '0', '0', '0',
                '0', '0', '0', '0', '0', '0', '0', '0'};
        assertEquals(-32768, Convert.convert2sCompToDecimal(data));
    }


    // Decimal to Binary

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)}.
     */
    @Test
    void testConvertDecimalTo2sCompZero() {
        final int data = 0;
        final char[] expected =
                {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        final char[] result = Convert.convertDecimalTo2sComp(data);
        assertArrayEquals(expected, result);
    }

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)}.
     */
    @Test
    void testConvertDecimalTo2sCompPositive1() {
        final int data = 1;
        final char[] expected =
                {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1'};
        final char[] result = Convert.convertDecimalTo2sComp(data);
        assertArrayEquals(expected, result);
    }

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)}.
     */
    @Test
    void testConvertDecimalTo2sCompPositiveMAX() {
        final int data = 32767;
        final char[] expected =
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'};
        final char[] result = Convert.convertDecimalTo2sComp(data);
        assertArrayEquals(expected, result);
    }

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)}.
     */
    @Test
    void testConvertDecimalTo2sCompNegative1() {
        final int data = -1;
        final char[] expected =
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'};
        final char[] result = Convert.convertDecimalTo2sComp(data);
        assertArrayEquals(expected, result);
    }

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)}.
     */
    @Test
    void testConvertDecimalTo2sCompNegativeMAX() {
        final int data = -32768;
        final char[] expected =
                {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        final char[] result = Convert.convertDecimalTo2sComp(data);
        assertArrayEquals(expected, result);
    }

    /**
     * Test method for {@link Convert#convertDecimalTo2sComp(int)} and {@link Convert#convert2sCompToDecimal(char[])}.
     */
    @Test
    void testConvertDecimalTo2sAndViceVersa() {
        for (int i = 0; i < 100; i++) {
            int result = ThreadLocalRandom.current().nextInt(32767);
            assertEquals(Convert.convert2sCompToDecimal(Convert.convertDecimalTo2sComp(result)), result);
            assertEquals(Convert.convert2sCompToDecimal(Convert.convertDecimalTo2sComp(-result)), -result);
        }
    }


}
