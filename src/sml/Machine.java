package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 * @author kmoriw01
 */
public final class Machine {

	private final Labels labels = new Labels();

	private final List<Instruction> program = new ArrayList<>();

	private final Registers registers;

	// The program counter; it contains the index (in program)
	// of the next instruction to be executed.
	private int programCounter = 0;

	public Machine(Registers registers) {
		this.registers = registers;
	}

	/**
	 * Execute the program in program, beginning at instruction 0.
	 * Precondition: the program and its labels have been stored properly.
	 */
	public void execute() {
		programCounter = 0;
		registers.clear();
		while (programCounter < program.size()) {
			Instruction ins = program.get(programCounter);
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}

	public Labels getLabels() {
		return this.labels;
	}

	public List<Instruction> getProgram() {
		return this.program;
	}

	public Registers getRegisters() {
		return this.registers;
	}


	/**
	 * String representation of the program under execution.
	 *
	 * @return pretty formatted version of the code.
	 */
	@Override
	public String toString() {
		return program.stream()
				.map(Instruction::toString)
				.collect(Collectors.joining("\n"));
	}

	// TODO: use pattern matching for instanceof -- done
	// https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine other) {
			// TODO: -- done
			// The instanceof operator is followed by the name of a new variable other,
			// which is declared and initialized with the value of o only if o is an instance of Machine.
			// The if statement then continues as before, comparing the fields of this object
			// with the corresponding fields of other object.
			// "Machine other = (Machine) o;" has been omitted.

			return Objects.equals(this.labels, other.labels)
					&& Objects.equals(this.program, other.program)
					&& Objects.equals(this.registers, other.registers)
					&& this.programCounter == other.programCounter;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels, program, registers, programCounter);
	}
}
