package javassist.tools.reflect;

import java.io.PrintStream;
import javassist.CtClass;
import javassist.ClassPool;

public class Compiler
{
    public Compiler() {
        super();
    }
    
    public static void main(final String[] array) throws Exception {
        if (array.length == 0) {
            help(System.err);
            return;
        }
        final CompiledClass[] array2 = new CompiledClass[array.length];
        final int parse = parse(array, array2);
        if (parse < 1) {
            System.err.println("bad parameter.");
            return;
        }
        processClasses(array2, parse);
    }
    
    private static void processClasses(final CompiledClass[] array, final int n) throws Exception {
        final Reflection reflection = new Reflection();
        final ClassPool default1 = ClassPool.getDefault();
        reflection.start(default1);
        for (int i = 0; i < n; ++i) {
            final CtClass value = default1.get(array[i].classname);
            if (array[i].metaobject != null || array[i].classobject != null) {
                String metaobject;
                if (array[i].metaobject == null) {
                    metaobject = "javassist.tools.reflect.Metaobject";
                }
                else {
                    metaobject = array[i].metaobject;
                }
                String classobject;
                if (array[i].classobject == null) {
                    classobject = "javassist.tools.reflect.ClassMetaobject";
                }
                else {
                    classobject = array[i].classobject;
                }
                if (!reflection.makeReflective(value, default1.get(metaobject), default1.get(classobject))) {
                    System.err.println("Warning: " + value.getName() + " is reflective.  It was not changed.");
                }
                System.err.println(value.getName() + ": " + metaobject + ", " + classobject);
            }
            else {
                System.err.println(value.getName() + ": not reflective");
            }
        }
        for (int j = 0; j < n; ++j) {
            reflection.onLoad(default1, array[j].classname);
            default1.get(array[j].classname).writeFile();
        }
    }
    
    private static int parse(final String[] array, final CompiledClass[] array2) {
        int n = -1;
        for (int i = 0; i < array.length; ++i) {
            final String classname = array[i];
            if (classname.equals("-m")) {
                if (n < 0 || i + 1 > array.length) {
                    return -1;
                }
                array2[n].metaobject = array[++i];
            }
            else if (classname.equals("-c")) {
                if (n < 0 || i + 1 > array.length) {
                    return -1;
                }
                array2[n].classobject = array[++i];
            }
            else {
                if (classname.charAt(0) == '-') {
                    return -1;
                }
                final CompiledClass compiledClass = new CompiledClass();
                compiledClass.classname = classname;
                compiledClass.metaobject = null;
                compiledClass.classobject = null;
                array2[++n] = compiledClass;
            }
        }
        return n + 1;
    }
    
    private static void help(final PrintStream printStream) {
        printStream.println("Usage: java javassist.tools.reflect.Compiler");
        printStream.println("            (<class> [-m <metaobject>] [-c <class metaobject>])+");
    }
}
