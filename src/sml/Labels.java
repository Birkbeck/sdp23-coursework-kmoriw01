package sml;

import sml.instruction.AddInstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/** This represents a label class
 *
 * @author kmoriw01
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates. -- done
		if (labels.containsKey(label)) {
			System.out.println(label + " is duplicated!");
		} else {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels. -- done
		// Explanation: it can be thrown here
		// where there is no label argument.
		// We can re-write the code with try/catch blocks.
		// If the label exist, return NORMAL_PROGRAM_COUNTER_UPDATE
		// value, which is -1.

		try {
			return labels.get(label);
		} catch(NullPointerException e) {
			System.out.println(label + " does not exist!");
		}
		return -1;
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers). -- done

		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// TODO: Implement equals and hashCode (needed in class Machine). -- done

	@Override
	public boolean equals(Object o){
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()){
			return false;
		}
		Labels that = (Labels) o;
		return labels.equals(that.labels);
	}
	@Override
	public int hashCode(){
		return Objects.hash(labels);
	}


	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
