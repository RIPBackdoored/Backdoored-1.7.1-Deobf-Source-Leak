package javassist.tools;

import javassist.CtClass;
import javassist.bytecode.analysis.FramePrinter;
import javassist.ClassPool;

public class framedump
{
    private framedump() {
        super();
    }
    
    public static void main(final String[] array) throws Exception {
        if (array.length != 1) {
            System.err.println("Usage: java javassist.tools.framedump <fully-qualified class name>");
            return;
        }
        final CtClass value = ClassPool.getDefault().get(array[0]);
        System.out.println("Frame Dump of " + value.getName() + ":");
        FramePrinter.print(value, System.out);
    }
}
