package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class -- done

/**
 * This class is for "out" instruction
 * Print the contents of register s on the console
 * @author Kenichi Moriwaki (kmoriw01)
 */

public class OutInstruction extends Instruction {
    private final RegisterName source;
    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        System.out.println("Register " + source + " contents: " + m.getRegisters().get(source));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " printing the contents of register " + source;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        OutInstruction that = (OutInstruction) o;
        return Objects.equals(this.label, that.label) && source.equals(that.source);
    }

    @Override
    public int hashCode(){
        return Objects.hash(source);
    }
}
