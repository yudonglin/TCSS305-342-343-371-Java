package simulator;

import java.util.Arrays;

/**
 * The Computer class is composed of registers, memory, PC, IR, and CC.
 * The Computer can execute a program based on the the instructions in memory.
 *
 * @author mmuppa
 * @author acfowler
 * @version 1.2
 */
public class Computer {

    private final static int MAX_MEMORY = 50;
    private final static int MAX_REGISTERS = 8;

    private final BitString[] mRegisters;
    private final BitString[] mMemory;
    private final BitString mPC;
    private final BitString mCC;
    private BitString mIR;

    /**
     * Initialize all memory addresses to 0, registers to 0 to 7
     * PC, IR to 16 bit 0s and CC to 000.
     */
    public Computer() {
        mPC = new BitString();
        mPC.setUnsignedValue(0);
        mIR = new BitString();
        mIR.setUnsignedValue(0);
        mCC = new BitString();
        mCC.setBits(new char[]{'0', '0', '0'});

        mRegisters = new BitString[MAX_REGISTERS];
        for (int i = 0; i < MAX_REGISTERS; i++) {
            mRegisters[i] = new BitString();
            mRegisters[i].setUnsignedValue(i);
        }

        mMemory = new BitString[MAX_MEMORY];
        for (int i = 0; i < MAX_MEMORY; i++) {
            mMemory[i] = new BitString();
            mMemory[i].setUnsignedValue(0);
        }
    }

    // The public accessor methods shown below are useful for unit testing.
    // Do NOT add public mutator methods (setters)!

    /**
     * @return the registers
     */
    public BitString[] getRegisters() {
        return copyBitStringArray(mRegisters);
    }

    /**
     * @return the memory
     */
    public BitString[] getMemory() {
        return copyBitStringArray(mMemory);
    }

    /**
     * @return the PC
     */
    public BitString getPC() {
        return mPC.copy();
    }

    /**
     * @return the IR
     */
    public BitString getIR() {
        return mIR.copy();
    }

    /**
     * @return the CC
     */
    public BitString getCC() {
        return mCC.copy();
    }

    /**
     * update the CC based on the result
     *
     * @param destination the place where the result has been stored
     */
    private void updateCC(BitString destination) {
        if (mRegisters[destination.getUnsignedValue()].get2sCompValue() > 0) {
            mCC.setBits(new char[]{'0', '0', '1'});
        } else if (mRegisters[destination.getUnsignedValue()].get2sCompValue() == 0) {
            mCC.setBits(new char[]{'0', '1', '0'});
        } else {
            mCC.setBits(new char[]{'1', '0', '0'});
        }
    }

    /**
     * Safely copies a BitString array.
     *
     * @param theArray the array to copy.
     * @return a copy of theArray.
     */
    private BitString[] copyBitStringArray(final BitString[] theArray) {
        BitString[] bitStrings = new BitString[theArray.length];
        Arrays.setAll(bitStrings, n -> bitStrings[n] = theArray[n].copy());
        return bitStrings;
    }

    /**
     * Loads a 16 bit word into memory at the given address.
     *
     * @param address memory address
     * @param word    data or instruction or address to be loaded into memory
     */
    public void loadWord(int address, BitString word) {
        if (address < 0 || address >= MAX_MEMORY) {
            throw new IllegalArgumentException("Invalid address");
        }
        mMemory[address] = word;
    }

    /**
     * Loads a machine code program, as Strings.
     *
     * @param theWords the Strings that contain the instructions or data.
     */
    public void loadMachineCode(final String... theWords) {
        if (theWords.length == 0 || theWords.length >= MAX_MEMORY) {
            throw new IllegalArgumentException("Invalid words");
        }
        for (int i = 0; i < theWords.length; i++) {
            final BitString instruction = new BitString();
            instruction.setBits(theWords[i].toCharArray());
            loadWord(i, instruction);
        }
    }


    // The next 6 methods are used to execute the required instructions:
    // BR, ADD, LD, AND, NOT, TRAP

    /**
     * op   nzp pc9offset
     * 0000 000 000000000
     * <p>
     * The condition codes specified by bits [11:9] are tested.
     * If bit [11] is 1, N is tested; if bit [11] is 0, N is not tested.
     * If bit [10] is 1, Z is tested, etc.
     * If any of the condition codes tested is 1, the program branches to the memory location specified by
     * adding the sign-extended PCoffset9 field to the incremented PC.
     */
    public void executeBranch() {
        var branchConditionBits = mIR.substring(4, 3).getBits();
        var currentConditionBits = this.getCC().getBits();
        //System.out.println(mMemory[mPC.getUnsignedValue()-1].getBits());
        for (int i = 0; i < 3; i++) {
            if (branchConditionBits[i] == currentConditionBits[i] && branchConditionBits[i] == '1') {
                int offset = mIR.substring(7, 9).get2sCompValue();
                //System.out.println(offset);
                mPC.set2sCompValue(mPC.get2sCompValue() + offset);
                break;
            }
        }
    }

    /**
     * op   dr  sr1      sr2
     * 0001 000 000 0 00 000
     * <p>
     * OR
     * <p>
     * op   dr  sr1   imm5
     * 0001 000 000 1 00000
     * <p>
     * If bit [5] is 0, the second source operand is obtained from SR2.
     * If bit [5] is 1, the second source operand is obtained by sign-extending the imm5 field to 16 bits.
     * In both cases, the second source operand is added to the contents of SR1 and the
     * result stored in DR. The condition codes are set, based on whether the result is
     * negative, zero, or positive.
     */
    public void executeAdd() {
        BitString destination = mIR.substring(4, 3);
        BitString sr1 = mIR.substring(7, 3);
        if (mIR.getBits()[10] == '1') {
            mRegisters[destination.getUnsignedValue()].set2sCompValue(mRegisters[sr1.getUnsignedValue()].get2sCompValue() + mIR.substring(11, 5).get2sCompValue());
        } else {
            BitString sr2 = mIR.substring(13, 3);
            mRegisters[destination.getUnsignedValue()].set2sCompValue(mRegisters[sr1.getUnsignedValue()].get2sCompValue() + mRegisters[sr2.getUnsignedValue()].get2sCompValue());
        }
        this.updateCC(destination);
    }

    /**
     * Performs the load operation by placing the data from PC
     * + PC offset9 bits [8:0]
     * into DR - bits [11:9]
     * then sets CC.
     */
    public void executeLoad() {
        BitString destination = mIR.substring(4, 3);
        int offset = mIR.substring(7, 9).get2sCompValue();
        int result = mRegisters[destination.getUnsignedValue()].get2sCompValue() + mMemory[mPC.getUnsignedValue() + offset].get2sCompValue();
        mRegisters[destination.getUnsignedValue()].set2sCompValue(result);
        this.updateCC(destination);
    }

    /**
     * op   dr  sr1      sr2
     * 0101 000 000 0 00 000
     * <p>
     * OR
     * <p>
     * op   dr  sr1   imm5
     * 0101 000 000 1 00000
     * <p>
     * If bit [5] is 0, the second source operand is obtained from SR2.
     * If bit [5] is 1, the second source operand is obtained by sign-extending the imm5 field to 16 bits.
     * In either case, the second source operand and the contents of SR1 are bitwise ANDed
     * and the result stored in DR.
     * The condition codes are set, based on whether the binary value produced, taken as a 2’s complement integer,
     * is negative, zero, or positive.
     */
    public void executeAnd() {
        BitString destination = mIR.substring(4, 3);
        BitString sr1 = mIR.substring(7, 3);
        mRegisters[destination.getUnsignedValue()] = mRegisters[sr1.getUnsignedValue()].copy();
        var destinationBits = mRegisters[destination.getUnsignedValue()].getBits();
        if (mIR.getBits()[10] == '1') {
            var imm5Bits = mIR.substring(10, 5).getBits();
            // and the first
            for (int i = 0; i < destinationBits.length - imm5Bits.length; i++) {
                if (destinationBits[i] != imm5Bits[0]) {
                    destinationBits[i] = '0';
                }
            }
            for (int i = 1; i <= imm5Bits.length; i++) {
                if (destinationBits[destinationBits.length - i] != imm5Bits[imm5Bits.length - i]) {
                    destinationBits[destinationBits.length - i] = '0';
                }
            }
        } else {
            BitString sr2 = mIR.substring(13, 3);
            var sr2Bits = mRegisters[sr2.getUnsignedValue()].getBits();
            for (int i = 0; i < sr2Bits.length; i++) {
                if (destinationBits[i] != sr2Bits[i]) {
                    destinationBits[i] = '0';
                }
            }
        }
        this.updateCC(destination);
    }

    /**
     * Performs not operation by using the data from the source register (bits[8:6])
     * and inverting and storing in the destination register (bits[11:9]).
     * Then sets CC.
     */
    public void executeNot() {
        BitString destBS = mIR.substring(4, 3);
        BitString sourceBS = mIR.substring(7, 3);
        mRegisters[destBS.getUnsignedValue()] = mRegisters[sourceBS.getUnsignedValue()].copy();
        mRegisters[destBS.getUnsignedValue()].invert();
        // set the condition code
        this.updateCC(destBS);
    }

    /**
     * Executes the trap operation by checking the vector (bits [7:0]
     * <p>
     * vector x21 - OUT
     * vector x25 - HALT
     *
     * @return false if this Trap is a HALT command; true otherwise
     */
    public boolean executeTrap() {
        boolean halt = false;
        // TRAP instruction
        if (String.valueOf(mIR.substring(8, 8).getBits()).equals("00100101")) {
            halt = true;
        } else {
            System.out.println((char) mRegisters[0].substring(8, 8).get2sCompValue());
        }
        return halt;
    }


	
	/*
		Extra Credit: Implement LEA, LDI, STI, LDR, STR
		in addition to the above instructions for extra credit.
		
		You will only earn extra credit if the required parts
		of your simulator are correct and if the Simulator program
		runs correctly and produces correct results and if you have
		written reasonable unit tests for the required LC3 instructions.
		
		So, please DO NOT spend time on these extra credit LC3 instructions
		until your basic program is complete and correct.
	*/


    /**
     * This method will execute all the instructions starting at address 0
     * until a HALT instruction is encountered.
     */
    public void execute() {
        BitString opCodeStr;
        int opCode;
        boolean halt = false;

        while (!halt) {
            // Fetch the next instruction
            mIR = mMemory[mPC.getUnsignedValue()];
            // increment the PC
            mPC.addOne();

            // Decode the instruction's first 4 bits
            // to figure out the opcode
            opCodeStr = mIR.substring(0, 4);
            opCode = opCodeStr.getUnsignedValue();

            // What instruction is this?
            if (opCode == 0) { // BR
                executeBranch();
            } else if (opCode == 1) { // ADD
                executeAdd();
            } else if (opCode == 2) { // LD
                executeLoad();
            } else if (opCode == 5) { // AND
                executeAnd();
            } else if (opCode == 9) { // NOT
                executeNot();
            } else if (opCode == 15) { // TRAP
                halt = executeTrap();
            } else {
                throw new UnsupportedOperationException("Illegal opCode: " + opCode);
            }
        }
    }

    /**
     * Displays the computer's state
     */
    public void display() {
        System.out.println();
        System.out.print("PC ");
        mPC.display(true);
        System.out.print("   ");

        System.out.print("IR ");
        mPC.display(true);
        System.out.print("   ");

        System.out.print("CC ");
        mCC.display(true);
        System.out.println("   ");
        for (int i = 0; i < MAX_REGISTERS; i++) {
            System.out.printf("R%d ", i);
            mRegisters[i].display(true);
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
        for (int i = 0; i < MAX_MEMORY; i++) {
            System.out.printf("%3d ", i);
            mMemory[i].display(true);
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
        System.out.println();
    }
}
