/*
 * Unit tests for the Computer class. 
 */

package simulator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Computer#executeAdd()}.
	 */
	@Test
	void testExecuteAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Computer#executeLoad()}.
	 */
	@Test
	void testExecuteLoad() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Computer#executeAnd()}.
	 */
	@Test
	void testExecuteAnd() {
		fail("Not yet implemented");
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
		
		String program[] = {
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

}
