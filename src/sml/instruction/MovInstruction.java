package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;
// TODO: write a JavaDoc for the class

/**
 * This class is for "mov" instruction
 * @author Kenichi Moriwaki (kmoriw01)
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int value;
    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName source, int value) {
        super(label, OP_CODE);
        this.result = source;
        this.value = value;
    }

    @Override
    public int execute(Machine machine) {
        machine.getRegisters().set(result,value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabel() + " register " + result + " value is " + value;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        MovInstruction that = (MovInstruction) o;
        return Objects.equals(this.label, that.label) && result.equals(that.result) && value == that.value;
    }

    @Override
    public int hashCode(){
        return Objects.hash(result, value);
    }
}
