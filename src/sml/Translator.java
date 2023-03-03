package sml;

import sml.instruction.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import static sml.Registers.Register;
import java.lang.reflect.*;

/**
 * This class represents a translator
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author kmoriw01
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();

/*  commented out switch & reflection blocks - hidden in IntelliJ

        switch (opcode) {
            // add instruction
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // TODO: add code for all other types of instructions -- completed

            // sub instruction
            case SubInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // mul instruction
            case MulInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // div instruction
            case DivInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // out instruction
            case OutInstruction.OP_CODE -> {
                String s = scan();
                return new OutInstruction(label, Register.valueOf(s));
            }

            // mov instruction
            case MovInstruction.OP_CODE -> {
                String r = scan();
                int x = Integer.parseInt(scan());
                return new MovInstruction(label, Register.valueOf(r), x);
            }

            // jnz instruction
            case JnzInstruction.OP_CODE -> {
                String s = scan();
                String L = scan();
                return new JnzInstruction(label, Register.valueOf(s), L);
            }
        // TODO: Then, replace the switch by using the Reflection API

        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)

        // dynamically loading and constructing an instance of a class
        // based on the value given by opcode.

        try {
            String r = scan();
            String s = scan();

            // take opcode and construct a class name
            String className = opcode.substring(0, 1).toUpperCase() + opcode.substring(1, 3) + "Instruction";
            // loads the class with fully qualified name
            Class<?> clazz = Class.forName("sml.instruction." + className);
            // retrieve constructor objects for the class
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // loop over each constructor in constructors
            for (Constructor<?> constructor : constructors) {
                // retrieve array of class objects representing parameter types of the constructor
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                // retrieve a constructor object matching given parameter types
                Constructor<?> cons = clazz.getConstructor(parameterTypes);
                // declare object array
                Object[] parameters;
                // if it takes 2 parameters
                if (parameterTypes.length == 2) {
                    parameters = new Object[]{
                            label, Register.valueOf(r)
                    };
                    // third parameter is int
                } else if (parameterTypes[2].equals(int.class)) {
                    parameters = new Object[]{
                            label, Register.valueOf(r), Integer.parseInt(s)
                    };
                    // third parameter is of type RegisterName
                } else if (parameterTypes[2].equals(RegisterName.class)) {
                    parameters = new Object[]{
                            label, Register.valueOf(r), Integer.parseInt(s)
                    };
                } else {
                    parameters = new Object[]{
                            label, Register.valueOf(r), s
                    };
                }
                // create a new instance by invoking the constructor with the parameters
                // cast to instruction interface and retunred as the result
                return (Instruction) cons.newInstance(parameters);
            }
            // error handling and print the stacktrace
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
                 | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
*/

        String r = scan();
        String s = scan();

        InstructionFactory factory = new InstructionFactory();
        Instruction instruction = factory.createInstruction(label, opcode, r, s);

        return instruction;
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}