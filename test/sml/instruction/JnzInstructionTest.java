package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {

        // What does JnzInstruction do?
        // jnz s L
        // If the contents of register s is not zero,
        // then make the statement labeled L the next statement to execute

        registers.set(EAX, 5);
        machine.getLabels().addLabel("f3", 10);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        Assertions.assertEquals(10, instruction.execute(machine));

    }
}