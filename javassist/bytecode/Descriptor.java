package javassist.bytecode;

import javassist.ClassPool;
import javassist.CtPrimitiveType;
import javassist.NotFoundException;
import java.util.Map;
import javassist.CtClass;

public class Descriptor
{
    public Descriptor() {
        super();
    }
    
    public static String toJvmName(final String s) {
        return s.replace('.', '/');
    }
    
    public static String toJavaName(final String s) {
        return s.replace('/', '.');
    }
    
    public static String toJvmName(final CtClass ctClass) {
        if (ctClass.isArray()) {
            return of(ctClass);
        }
        return toJvmName(ctClass.getName());
    }
    
    public static String toClassName(final String s) {
        int n = 0;
        int n2;
        char c;
        for (n2 = 0, c = s.charAt(0); c == '['; c = s.charAt(++n2)) {
            ++n;
        }
        String replace;
        if (c == 'L') {
            final int index = s.indexOf(59, n2++);
            replace = s.substring(n2, index).replace('/', '.');
            n2 = index;
        }
        else if (c == 'V') {
            replace = "void";
        }
        else if (c == 'I') {
            replace = "int";
        }
        else if (c == 'B') {
            replace = "byte";
        }
        else if (c == 'J') {
            replace = "long";
        }
        else if (c == 'D') {
            replace = "double";
        }
        else if (c == 'F') {
            replace = "float";
        }
        else if (c == 'C') {
            replace = "char";
        }
        else if (c == 'S') {
            replace = "short";
        }
        else {
            if (c != 'Z') {
                throw new RuntimeException("bad descriptor: " + s);
            }
            replace = "boolean";
        }
        if (n2 + 1 != s.length()) {
            throw new RuntimeException("multiple descriptors?: " + s);
        }
        return replace;
    }
    
    public static String of(final String s) {
        if (s.equals("void")) {
            return "V";
        }
        if (s.equals("int")) {
            return "I";
        }
        if (s.equals("byte")) {
            return "B";
        }
        if (s.equals("long")) {
            return "J";
        }
        if (s.equals("double")) {
            return "D";
        }
        if (s.equals("float")) {
            return "F";
        }
        if (s.equals("char")) {
            return "C";
        }
        if (s.equals("short")) {
            return "S";
        }
        if (s.equals("boolean")) {
            return "Z";
        }
        return "L" + toJvmName(s) + ";";
    }
    
    public static String rename(final String s, final String s2, final String s3) {
        if (s.indexOf(s2) < 0) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int n2 = 0;
        while (true) {
            final int index = s.indexOf(76, n2);
            if (index < 0) {
                break;
            }
            if (s.startsWith(s2, index + 1) && s.charAt(index + s2.length() + 1) == ';') {
                sb.append(s.substring(n, index));
                sb.append('L');
                sb.append(s3);
                sb.append(';');
                n2 = (n = index + s2.length() + 2);
            }
            else {
                n2 = s.indexOf(59, index) + 1;
                if (n2 < 1) {
                    break;
                }
                continue;
            }
        }
        return s;
    }
    
    public static String rename(final String s, final Map map) {
        if (map == null) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int n2 = 0;
        while (true) {
            final int index = s.indexOf(76, n2);
            if (index < 0) {
                break;
            }
            final int index2 = s.indexOf(59, index);
            if (index2 < 0) {
                break;
            }
            n2 = index2 + 1;
            final String s2 = map.get(s.substring(index + 1, index2));
            if (s2 == null) {
                continue;
            }
            sb.append(s.substring(n, index));
            sb.append('L');
            sb.append(s2);
            sb.append(';');
            n = n2;
        }
        return s;
    }
    
    public static String of(final CtClass ctClass) {
        final StringBuffer sb = new StringBuffer();
        toDescriptor(sb, ctClass);
        return sb.toString();
    }
    
    private static void toDescriptor(final StringBuffer sb, final CtClass ctClass) {
        if (ctClass.isArray()) {
            sb.append('[');
            try {
                toDescriptor(sb, ctClass.getComponentType());
            }
            catch (NotFoundException ex) {
                sb.append('L');
                final String name = ctClass.getName();
                sb.append(toJvmName(name.substring(0, name.length() - 2)));
                sb.append(';');
            }
        }
        else if (ctClass.isPrimitive()) {
            sb.append(((CtPrimitiveType)ctClass).getDescriptor());
        }
        else {
            sb.append('L');
            sb.append(ctClass.getName().replace('.', '/'));
            sb.append(';');
        }
    }
    
    public static String ofConstructor(final CtClass[] array) {
        return ofMethod(CtClass.voidType, array);
    }
    
    public static String ofMethod(final CtClass ctClass, final CtClass[] array) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                toDescriptor(sb, array[i]);
            }
        }
        sb.append(')');
        if (ctClass != null) {
            toDescriptor(sb, ctClass);
        }
        return sb.toString();
    }
    
    public static String ofParameters(final CtClass[] array) {
        return ofMethod(null, array);
    }
    
    public static String appendParameter(final String s, final String s2) {
        final int index = s2.indexOf(41);
        if (index < 0) {
            return s2;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s2.substring(0, index));
        sb.append('L');
        sb.append(s.replace('.', '/'));
        sb.append(';');
        sb.append(s2.substring(index));
        return sb.toString();
    }
    
    public static String insertParameter(final String s, final String s2) {
        if (s2.charAt(0) != '(') {
            return s2;
        }
        return "(L" + s.replace('.', '/') + ';' + s2.substring(1);
    }
    
    public static String appendParameter(final CtClass ctClass, final String s) {
        final int index = s.indexOf(41);
        if (index < 0) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s.substring(0, index));
        toDescriptor(sb, ctClass);
        sb.append(s.substring(index));
        return sb.toString();
    }
    
    public static String insertParameter(final CtClass ctClass, final String s) {
        if (s.charAt(0) != '(') {
            return s;
        }
        return "(" + of(ctClass) + s.substring(1);
    }
    
    public static String changeReturnType(final String s, final String s2) {
        final int index = s2.indexOf(41);
        if (index < 0) {
            return s2;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append(s2.substring(0, index + 1));
        sb.append('L');
        sb.append(s.replace('.', '/'));
        sb.append(';');
        return sb.toString();
    }
    
    public static CtClass[] getParameterTypes(final String s, final ClassPool classPool) throws NotFoundException {
        if (s.charAt(0) != '(') {
            return null;
        }
        final CtClass[] array = new CtClass[numOfParameters(s)];
        int n = 0;
        int i = 1;
        do {
            i = toCtClass(classPool, s, i, array, n++);
        } while (i > 0);
        return array;
    }
    
    public static boolean eqParamTypes(final String s, final String s2) {
        if (s.charAt(0) != '(') {
            return false;
        }
        int n = 0;
        while (true) {
            final char char1 = s.charAt(n);
            if (char1 != s2.charAt(n)) {
                return false;
            }
            if (char1 == ')') {
                return true;
            }
            ++n;
        }
    }
    
    public static String getParamDescriptor(final String s) {
        return s.substring(0, s.indexOf(41) + 1);
    }
    
    public static CtClass getReturnType(final String s, final ClassPool classPool) throws NotFoundException {
        final int index = s.indexOf(41);
        if (index < 0) {
            return null;
        }
        final CtClass[] array = { null };
        toCtClass(classPool, s, index + 1, array, 0);
        return array[0];
    }
    
    public static int numOfParameters(final String s) {
        int n = 0;
        int n2 = 1;
        while (true) {
            char c = s.charAt(n2);
            if (c == ')') {
                return n;
            }
            while (c == '[') {
                c = s.charAt(++n2);
            }
            if (c == 'L') {
                n2 = s.indexOf(59, n2) + 1;
                if (n2 <= 0) {
                    throw new IndexOutOfBoundsException("bad descriptor");
                }
            }
            else {
                ++n2;
            }
            ++n;
        }
    }
    
    public static CtClass toCtClass(final String s, final ClassPool classPool) throws NotFoundException {
        final CtClass[] array = { null };
        if (toCtClass(classPool, s, 0, array, 0) >= 0) {
            return array[0];
        }
        return classPool.get(s.replace('/', '.'));
    }
    
    private static int toCtClass(final ClassPool classPool, final String s, int n, final CtClass[] array, final int n2) throws NotFoundException {
        int n3 = 0;
        char c;
        for (c = s.charAt(n); c == '['; c = s.charAt(++n)) {
            ++n3;
        }
        if (c == 'L') {
            int index = s.indexOf(59, ++n);
            String s2 = s.substring(n, index++).replace('/', '.');
            if (n3 > 0) {
                final StringBuffer sb = new StringBuffer(s2);
                while (n3-- > 0) {
                    sb.append("[]");
                }
                s2 = sb.toString();
            }
            array[n2] = classPool.get(s2);
            return index;
        }
        final CtClass primitiveClass = toPrimitiveClass(c);
        if (primitiveClass == null) {
            return -1;
        }
        final int n4 = n + 1;
        array[n2] = primitiveClass;
        return n4;
    }
    
    static CtClass toPrimitiveClass(final char c) {
        CtClass ctClass = null;
        switch (c) {
            case 'Z':
                ctClass = CtClass.booleanType;
                break;
            case 'C':
                ctClass = CtClass.charType;
                break;
            case 'B':
                ctClass = CtClass.byteType;
                break;
            case 'S':
                ctClass = CtClass.shortType;
                break;
            case 'I':
                ctClass = CtClass.intType;
                break;
            case 'J':
                ctClass = CtClass.longType;
                break;
            case 'F':
                ctClass = CtClass.floatType;
                break;
            case 'D':
                ctClass = CtClass.doubleType;
                break;
            case 'V':
                ctClass = CtClass.voidType;
                break;
        }
        return ctClass;
    }
    
    public static int arrayDimension(final String s) {
        int n;
        for (n = 0; s.charAt(n) == '['; ++n) {}
        return n;
    }
    
    public static String toArrayComponent(final String s, final int n) {
        return s.substring(n);
    }
    
    public static int dataSize(final String s) {
        return dataSize(s, true);
    }
    
    public static int paramSize(final String s) {
        return -dataSize(s, false);
    }
    
    private static int dataSize(final String s, final boolean b) {
        int n = 0;
        char c = s.charAt(0);
        if (c == '(') {
            int n2 = 1;
            while (true) {
                char c2 = s.charAt(n2);
                if (c2 == ')') {
                    c = s.charAt(n2 + 1);
                    break;
                }
                while (c2 == '[') {
                    c2 = s.charAt(++n2);
                }
                if (c2 == 'L') {
                    n2 = s.indexOf(59, n2) + 1;
                    if (n2 <= 0) {
                        throw new IndexOutOfBoundsException("bad descriptor");
                    }
                }
                else {
                    ++n2;
                }
                if (c2 == 'J' || c2 == 'D') {
                    n -= 2;
                }
                else {
                    --n;
                }
            }
        }
        if (b) {
            if (c == 'J' || c == 'D') {
                n += 2;
            }
            else if (c != 'V') {
                ++n;
            }
        }
        return n;
    }
    
    public static String toString(final String s) {
        return PrettyPrinter.toString(s);
    }
}
