package sml;
/**
 * This class represents interface for the instruction factory
 * @author kmoriw01
 */
public interface InstructionFactoryInterface {
    Instruction createInstruction(String label, String opcode, String r, String s);
}