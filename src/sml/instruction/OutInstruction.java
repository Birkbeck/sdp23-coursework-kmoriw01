package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * This class is for "out" instruction
 * Print the contents of register s on the console
 * @author Kenichi Moriwaki (kmoriw01)
 */

public class OutInstruction extends Instruction {
    private final RegisterName register;
    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName register) {
        super(label, OP_CODE);
        this.register = register;
    }

    @Override
    public int execute(Machine m) {
        System.out.println("Register " + register + " contents: " + m.getRegisters().get(register));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " printing the contents of register " + register;
    }
}
