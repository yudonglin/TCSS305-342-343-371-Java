/*
 * Unit tests for the Computer class.
 */

package simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alan Fowler
 * @version 1.2
 */
class ComputerTest {

    // An instance of the Computer class to use in the tests.
    private Computer mComp;

    @BeforeEach
    void setUp() {
        mComp = new Computer();
    }

    /**
     * Test method for {@link Computer#executeBranch()}.
     */
    @Test
    void testExecuteBranch() {
        //This is just a simplified version of assignment 3
        String[] program = {
                "0101011011100000",    // R3 <- 0
                "0101100100100000",    // R4 <- 0
                "0101101101100000",    // R5 <- 0
                "0101110110100000",    // R6 <- 0
                "0001011011100101",    // R3 <- #5
                "0001100100111101",    // R4 <- #-3
                "0000011000000100",    // branch to the loop if R4 is positive or zero
                "1001011011111111",    // R3 <- NOT R3
                "0001011011100001",    // Add 1 to R3
                "1001100100111111",    // R4 <- NOT R4
                "0001100100100001",    // Add 1 to R4
                "0000010000000011",    // BRANCH past the loop when the loop counter is no longer positive
                "0001101101000011",    // R5 <- R5+R3 Adds the loop counter to the SUM
                "0001100100111111",    // Decrement the loop counter
                "0000111111111100",    // Branch unconditionally to the top of the loop
                "0001101101100000",    // R5 <- R5 + 0
                "0000110000000010",    // if R5 < 1, skip ahead to the 'else'
                "0001110110100001",    // Set R6 = 1
                "0000111000000010",    // the 'if' part ran, so skip the else part
                "0000010000000001",    // else if R5 = 0, skip the set R6 = -1 part
                "0001110110111111",    // Set R6 = -1
                "1111000000100101"     // TRAP - vector x25 - HALT
        };
        mComp.loadMachineCode(program);
        mComp.execute();

        // Does R3 now contain the value that it should contain?
        assertEquals(-5, mComp.getRegisters()[3].get2sCompValue());
        // Does R4 now contain the value that it should contain?
        assertEquals(0, mComp.getRegisters()[4].get2sCompValue());
        // Does R5 now contain the value that it should contain?
        assertEquals(-15, mComp.getRegisters()[5].get2sCompValue());
        // Does R6 now contain the value that it should contain?
        assertEquals(-1, mComp.getRegisters()[6].get2sCompValue());
    }

    /**
     * Test method for {@link Computer#executeAdd()}.
     */
    @Test
    void testExecuteAdd() {

        String[] program = {
                "0101001001100000",    // And R1 with 0 to clear R1
                "0001001001111100",    // R1 <- -4
                "0101010010100000",    // And R2 with 0 to clear R2
                "0001010010101001",    // R2 <- 9
                "0101011011100000",    // And R3 with 0 to clear R3
                "0001011001000010",    // R3 <- R1 + R2
                "1111000000100101"     // TRAP - vector x25 - HALT
        };

        mComp.loadMachineCode(program);
        mComp.execute();

        // Does R1 now contain the value that it should contain?
        assertEquals(-4, mComp.getRegisters()[1].get2sCompValue());
        // Does R2 now contain the value that it should contain?
        assertEquals(9, mComp.getRegisters()[2].get2sCompValue());
        // Does R3 now contain the value that it should contain?
        assertEquals(5, mComp.getRegisters()[3].get2sCompValue());
    }

    /**
     * Test method for {@link Computer#executeLoad()}.
     */
    @Test
    void testExecuteLoad() {
        String[] program = {
                "0101001001100000",   // And R1 with 0
                "0101010010100000",   // And R2 with 0
                "0101011011100000",   // And R3 with 0
                "0101100100100000",   // And R4 with 0
                "0101101101100000",   // And R5 with 0
                "0010001000000110",   // Load 32767 into R1
                "0010010000000100",   // Load -1 into R2
                "0001011001000010",   // R3 <- R1 + R2
                "0010100000000100",   // Load -32768 into R4
                "0001101100000001",   // R5 <- R1+R4
                "1111000000100101",   // TRAP - vector x25 - HALT
                "1111111111111111",   // -1
                "0111111111111111",   // 32767
                "1000000000000000"    // -32768
        };

        mComp.loadMachineCode(program);
        mComp.execute();

        // Does R1 now contain the value that it should contain?
        assertEquals(32767, mComp.getRegisters()[1].get2sCompValue());
        // Does R2 now contain the value that it should contain?
        assertEquals(-1, mComp.getRegisters()[2].get2sCompValue());
        // Does R3 now contain the value that it should contain?
        assertEquals(32766, mComp.getRegisters()[3].get2sCompValue());
        // Does R4 now contain the value that it should contain?
        assertEquals(-32768, mComp.getRegisters()[4].get2sCompValue());
        // Does R5 now contain the value that it should contain?
        assertEquals(-1, mComp.getRegisters()[5].get2sCompValue());
    }

    /**
     * Test method for {@link Computer#executeAnd()}.
     */
    @Test
    void testExecuteAnd() {

        String[] program = {
                "0001001001101001",    // R1 <- 9
                "0101001001100000",    // And R1 with 0
                "0101001001111111",    // And R1 with -1
                "0101001001100000",    // And R1 with 0
                "0101010010100000",    // And R2 with 0
                "0001010010110111",    // R2 <- -9
                "0101010010111111",    // And R2 with 11111 (so nothing should change)
                "0101011011100000",    // And R3 with 0
                "0001011011101111",    // R3 <- 15
                "0101100100100000",    // And R4 with 0
                "0101100011000010",    // R4 <- R3 AND R2
                "1111000000100101"     // TRAP - vector x25 - HALT
        };

        mComp.loadMachineCode(program);
        mComp.execute();

        // Does R1 now contain the value that it should contain?
        assertEquals(0, mComp.getRegisters()[1].get2sCompValue());
        // Does R2 now contain the value that it should contain?
        assertEquals(-9, mComp.getRegisters()[2].get2sCompValue());
        // Does R3 now contain the value that it should contain?
        assertEquals(15, mComp.getRegisters()[3].get2sCompValue());
        // Does R4 now contain the value that it should contain?
        assertEquals(7, mComp.getRegisters()[4].get2sCompValue());
    }

    /**
     * Test method for {@link Computer#executeNot()}.
     */
    @Test
    void testExecuteNot5() {

        //mComp.display();

        // NOTE: R5 contains #5 initially when the Computer is instantiated
        // So, iF we execute R4 <- NOT R5, then R4 should contain 1111 1111 1111 1010
        // AND CC should be 100

        String[] program = {
                "1001100101111111",    // R4 <- NOT R5
                "1111000000100101"     // TRAP - vector x25 - HALT
        };

        mComp.loadMachineCode(program);
        mComp.execute();

        // Does R4 now contain the value that it should contain?
        assertEquals(-6, mComp.getRegisters()[4].get2sCompValue());

        // Did R5 change? It should not. Maybe test that too?

        // Also, write a test to check that CC was set correctly

        // mComp.display();
    }


    /**
     * Test method for {@link Computer#executeTrap()}.
     */
    @Test
    void testExecuteTrap() {

        //mComp.display();

        // NOTE: R5 contains #5 initially when the Computer is instantiated
        // So, iF we execute R4 <- NOT R5, then R4 should contain 1111 1111 1111 1010
        // AND CC should be 100

        String[] program = {
                "0011000000000000",
                "0101001001100000",
                "0010001000010100",
                "0101010001100000",
                "0001010010111111",
                "0101000000100000",
                "0010000000001111",
                "0001000000000001",
                "1111000000100001",
                "0001001001000010",
                "0001010010100000",
                "0000001000000101",
                "0001001001100000",
                "0000011111110111",
                "0001010010100010",
                "0101001001100000",
                "0000111111110101",
                "0101011011100000",
                "0010011000000101",
                "0001011011000001",
                "0000110111110000",
                "1111000000100101",
                "0000000001000001",
                "0000000000011001",
                "1111111111100111",
        };
        mComp.loadMachineCode(program);
        mComp.execute();

    }
}
