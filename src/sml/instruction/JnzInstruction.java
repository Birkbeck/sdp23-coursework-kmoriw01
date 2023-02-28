package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * This class is for "jnz" instruction
 * @author Kenichi Moriwaki (kmoriw01)
 */

public class JnzInstruction extends Instruction {
    private final RegisterName register;
    private final String nextLabel;
    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName register, String nextLabel) {
        super(label, OP_CODE);
        this.register = register;
        this.nextLabel = nextLabel;
    }

    @Override
    public int execute(Machine m) {
        //If the contents of register s is not zero, then make the statement labeled L the next statement to execute
        if (m.getRegisters().get(register) != 0) {
            NORMAL_PROGRAM_COUNTER_UPDATE = m.getLabels().getAddress(nextLabel);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabel() + ": " + getOpcode() + " if the contents of register " + register + " is not zero, " +
                "then make the statement with " + nextLabel + " become the next one to execute";


    }
}
