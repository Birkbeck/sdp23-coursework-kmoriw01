package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class - done

/**
 * This class is for "jnz" instruction
 * @author Kenichi Moriwaki (kmoriw01)
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String nextLabel;
    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String nextLabel) {
        super(label, OP_CODE);
        this.source = source;
        this.nextLabel = nextLabel;
    }

    @Override
    public int execute(Machine m) {
        //If the contents of register s is not zero, then make the statement labeled L the next statement to execute
        if (m.getRegisters().get(source) != 0) {
            NORMAL_PROGRAM_COUNTER_UPDATE = m.getLabels().getAddress(nextLabel);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabel() + ": " + getOpcode() + " if the contents of register " + source + " is not zero, " +
                "then make the statement with " + nextLabel + " become the next one to execute";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        JnzInstruction that = (JnzInstruction) o;
        return Objects.equals(this.label, that.label) && nextLabel.equals(that.nextLabel) && source.equals(that.source);
    }
    @Override
    public int hashCode(){
        return Objects.hash(source, nextLabel);
    }

}
