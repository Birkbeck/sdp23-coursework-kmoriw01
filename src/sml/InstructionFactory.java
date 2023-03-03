package sml;
import java.lang.reflect.*;
import sml.Registers.Register;
/**
 * This class is responsibel for creating instructions
 * @author kmoriw01
 */

public class InstructionFactory implements InstructionFactoryInterface {
    public Instruction createInstruction(String label, String opcode, String r, String s){
        try{
            String className = opcode.substring(0, 1).toUpperCase() + opcode.substring(1, 3) + "Instruction";
            Class<?> clazz = Class.forName("sml.instruction." + className);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Constructor<?> cons = clazz.getConstructor(parameterTypes);
                Object[] parameters;
                if (parameterTypes.length == 2) {
                    parameters = new Object[]{
                        label, Register.valueOf(r)
                    };
                } else if (parameterTypes[2].equals(int.class)) {
                    parameters = new Object[]{
                        label, Register.valueOf(r), Integer.parseInt(s)
                    };
                } else if (parameterTypes[2].equals(RegisterName.class)) {
                    parameters = new Object[]{
                        label, Register.valueOf(r), Integer.parseInt(s)
                    };
                } else {
                    parameters = new Object[]{
                        label, Register.valueOf(r), s
                    };
                }
                return (Instruction) cons.newInstance(parameters);
            }
    } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
                 | InstantiationException | NoSuchMethodException e) {
        e.printStackTrace();
    } return null;
}
}

