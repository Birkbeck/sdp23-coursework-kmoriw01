package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class OutInstructionTest {
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

        // Create a new ByteArrayOutputStream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Redirect System.out to the ByteArrayOutputStream
        System.setOut(new PrintStream(outputStream));

        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString().trim();

        Assertions.assertEquals("Register EAX contents: 5", output);
    }
}