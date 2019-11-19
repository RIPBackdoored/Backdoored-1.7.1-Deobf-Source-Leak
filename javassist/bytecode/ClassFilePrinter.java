package javassist.bytecode;

import java.util.List;
import javassist.Modifier;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ClassFilePrinter
{
    public ClassFilePrinter() {
        super();
    }
    
    public static void print(final ClassFile classFile) {
        print(classFile, new PrintWriter(System.out, true));
    }
    
    public static void print(final ClassFile classFile, final PrintWriter printWriter) {
        final int modifier = AccessFlag.toModifier(classFile.getAccessFlags() & 0xFFFFFFDF);
        printWriter.println("major: " + classFile.major + ", minor: " + classFile.minor + " modifiers: " + Integer.toHexString(classFile.getAccessFlags()));
        printWriter.println(Modifier.toString(modifier) + " class " + classFile.getName() + " extends " + classFile.getSuperclass());
        final String[] interfaces = classFile.getInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            printWriter.print("    implements ");
            printWriter.print(interfaces[0]);
            for (int i = 1; i < interfaces.length; ++i) {
                printWriter.print(", " + interfaces[i]);
            }
            printWriter.println();
        }
        printWriter.println();
        final List fields = classFile.getFields();
        for (int size = fields.size(), j = 0; j < size; ++j) {
            final FieldInfo fieldInfo = fields.get(j);
            printWriter.println(Modifier.toString(AccessFlag.toModifier(fieldInfo.getAccessFlags())) + " " + fieldInfo.getName() + "\t" + fieldInfo.getDescriptor());
            printAttributes(fieldInfo.getAttributes(), printWriter, 'f');
        }
        printWriter.println();
        final List methods = classFile.getMethods();
        for (int size2 = methods.size(), k = 0; k < size2; ++k) {
            final MethodInfo methodInfo = methods.get(k);
            printWriter.println(Modifier.toString(AccessFlag.toModifier(methodInfo.getAccessFlags())) + " " + methodInfo.getName() + "\t" + methodInfo.getDescriptor());
            printAttributes(methodInfo.getAttributes(), printWriter, 'm');
            printWriter.println();
        }
        printWriter.println();
        printAttributes(classFile.getAttributes(), printWriter, 'c');
    }
    
    static void printAttributes(final List list, final PrintWriter printWriter, final char c) {
        if (list == null) {
            return;
        }
        for (int size = list.size(), i = 0; i < size; ++i) {
            final AttributeInfo attributeInfo = list.get(i);
            if (attributeInfo instanceof CodeAttribute) {
                final CodeAttribute codeAttribute = (CodeAttribute)attributeInfo;
                printWriter.println("attribute: " + attributeInfo.getName() + ": " + ((CodeAttribute)attributeInfo).getClass().getName());
                printWriter.println("max stack " + codeAttribute.getMaxStack() + ", max locals " + codeAttribute.getMaxLocals() + ", " + codeAttribute.getExceptionTable().size() + " catch blocks");
                printWriter.println("<code attribute begin>");
                printAttributes(codeAttribute.getAttributes(), printWriter, c);
                printWriter.println("<code attribute end>");
            }
            else if (attributeInfo instanceof AnnotationsAttribute) {
                printWriter.println("annnotation: " + attributeInfo.toString());
            }
            else if (attributeInfo instanceof ParameterAnnotationsAttribute) {
                printWriter.println("parameter annnotations: " + attributeInfo.toString());
            }
            else if (attributeInfo instanceof StackMapTable) {
                printWriter.println("<stack map table begin>");
                StackMapTable.Printer.print((StackMapTable)attributeInfo, printWriter);
                printWriter.println("<stack map table end>");
            }
            else if (attributeInfo instanceof StackMap) {
                printWriter.println("<stack map begin>");
                ((StackMap)attributeInfo).print(printWriter);
                printWriter.println("<stack map end>");
            }
            else if (attributeInfo instanceof SignatureAttribute) {
                final String signature = ((SignatureAttribute)attributeInfo).getSignature();
                printWriter.println("signature: " + signature);
                try {
                    String s;
                    if (c == 'c') {
                        s = SignatureAttribute.toClassSignature(signature).toString();
                    }
                    else if (c == 'm') {
                        s = SignatureAttribute.toMethodSignature(signature).toString();
                    }
                    else {
                        s = SignatureAttribute.toFieldSignature(signature).toString();
                    }
                    printWriter.println("           " + s);
                }
                catch (BadBytecode badBytecode) {
                    printWriter.println("           syntax error");
                }
            }
            else {
                printWriter.println("attribute: " + attributeInfo.getName() + " (" + attributeInfo.get().length + " byte): " + ((SignatureAttribute)attributeInfo).getClass().getName());
            }
        }
    }
}
