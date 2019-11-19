package javassist.compiler;

import javassist.compiler.ast.Keyword;
import java.util.Iterator;
import java.lang.ref.WeakReference;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.ASTList;
import javassist.CtField;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Symbol;
import javassist.bytecode.Descriptor;
import java.util.List;
import javassist.bytecode.ClassFile;
import javassist.NotFoundException;
import javassist.Modifier;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import java.util.Hashtable;
import java.util.WeakHashMap;
import javassist.ClassPool;

public class MemberResolver implements TokenId
{
    private ClassPool classPool;
    private static final int YES = 0;
    private static final int NO = -1;
    private static final String INVALID = "<invalid>";
    private static WeakHashMap invalidNamesMap;
    private Hashtable invalidNames;
    
    public MemberResolver(final ClassPool classPool) {
        super();
        this.invalidNames = null;
        this.classPool = classPool;
    }
    
    public ClassPool getClassPool() {
        return this.classPool;
    }
    
    private static void fatal() throws CompileError {
        throw new CompileError("fatal");
    }
    
    public Method lookupMethod(final CtClass ctClass, final CtClass ctClass2, final MethodInfo methodInfo, final String s, final int[] array, final int[] array2, final String[] array3) throws CompileError {
        final Method method = null;
        if (methodInfo != null && ctClass == ctClass2 && methodInfo.getName().equals(s)) {
            final int compareSignature = this.compareSignature(methodInfo.getDescriptor(), array, array2, array3);
            if (compareSignature != -1) {
                return new Method(ctClass, methodInfo, compareSignature);
            }
        }
        final Method lookupMethod = this.lookupMethod(ctClass, s, array, array2, array3, method != null);
        if (lookupMethod != null) {
            return lookupMethod;
        }
        return method;
    }
    
    private Method lookupMethod(final CtClass ctClass, final String s, final int[] array, final int[] array2, final String[] array3, final boolean b) throws CompileError {
        Method method = null;
        final ClassFile classFile2 = ctClass.getClassFile2();
        if (classFile2 != null) {
            final List methods = classFile2.getMethods();
            for (int size = methods.size(), i = 0; i < size; ++i) {
                final MethodInfo methodInfo = methods.get(i);
                if (methodInfo.getName().equals(s) && (methodInfo.getAccessFlags() & 0x40) == 0x0) {
                    final int compareSignature = this.compareSignature(methodInfo.getDescriptor(), array, array2, array3);
                    if (compareSignature != -1) {
                        return new Method(ctClass, methodInfo, compareSignature);
                    }
                }
            }
        }
        if (b) {
            method = null;
        }
        else if (method != null) {
            return method;
        }
        final boolean interface1 = Modifier.isInterface(ctClass.getModifiers());
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                final Method lookupMethod = this.lookupMethod(superclass, s, array, array2, array3, b);
                if (lookupMethod != null) {
                    return lookupMethod;
                }
            }
        }
        catch (NotFoundException ex) {}
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int length = interfaces.length, j = 0; j < length; ++j) {
                final Method lookupMethod2 = this.lookupMethod(interfaces[j], s, array, array2, array3, b);
                if (lookupMethod2 != null) {
                    return lookupMethod2;
                }
            }
            if (interface1) {
                final CtClass superclass2 = ctClass.getSuperclass();
                if (superclass2 != null) {
                    final Method lookupMethod3 = this.lookupMethod(superclass2, s, array, array2, array3, b);
                    if (lookupMethod3 != null) {
                        return lookupMethod3;
                    }
                }
            }
        }
        catch (NotFoundException ex2) {}
        return method;
    }
    
    private int compareSignature(final String s, final int[] array, final int[] array2, final String[] array3) throws CompileError {
        int n = 0;
        int i = 1;
        final int length = array.length;
        if (length != Descriptor.numOfParameters(s)) {
            return -1;
        }
        final int length2 = s.length();
        int n2 = 0;
        while (i < length2) {
            char c = s.charAt(i++);
            if (c == ')') {
                return (n2 == length) ? n : -1;
            }
            if (n2 >= length) {
                return -1;
            }
            int n3 = 0;
            while (c == '[') {
                ++n3;
                c = s.charAt(i++);
            }
            if (array[n2] == 412) {
                if (c != 'L') {
                    return -1;
                }
                if (c == 'L') {
                    i = s.indexOf(59, i) + 1;
                }
            }
            else if (array2[n2] != n3) {
                if (c != 'L' || !s.startsWith("java/lang/Object;", i)) {
                    return -1;
                }
                i = s.indexOf(59, i) + 1;
                ++n;
                if (i <= 0) {
                    return -1;
                }
            }
            else if (c == 'L') {
                final int index = s.indexOf(59, i);
                if (index < 0 || array[n2] != 307) {
                    return -1;
                }
                final String substring = s.substring(i, index);
                if (!substring.equals(array3[n2])) {
                    final CtClass lookupClassByJvmName = this.lookupClassByJvmName(array3[n2]);
                    try {
                        if (!lookupClassByJvmName.subtypeOf(this.lookupClassByJvmName(substring))) {
                            return -1;
                        }
                        ++n;
                    }
                    catch (NotFoundException ex) {
                        ++n;
                    }
                }
                i = index + 1;
            }
            else {
                final int descToType = descToType(c);
                final int n4 = array[n2];
                if (descToType != n4) {
                    if (descToType != 324 || (n4 != 334 && n4 != 303 && n4 != 306)) {
                        return -1;
                    }
                    ++n;
                }
            }
            ++n2;
        }
        return -1;
    }
    
    public CtField lookupFieldByJvmName2(String javaToJvmName, final Symbol symbol, final ASTree asTree) throws NoFieldException {
        final String value = symbol.get();
        CtClass lookupClass;
        try {
            lookupClass = this.lookupClass(jvmToJavaName(javaToJvmName), true);
        }
        catch (CompileError compileError) {
            throw new NoFieldException(javaToJvmName + "/" + value, asTree);
        }
        try {
            return lookupClass.getField(value);
        }
        catch (NotFoundException ex) {
            javaToJvmName = javaToJvmName(lookupClass.getName());
            throw new NoFieldException(javaToJvmName + "$" + value, asTree);
        }
    }
    
    public CtField lookupFieldByJvmName(final String s, final Symbol symbol) throws CompileError {
        return this.lookupField(jvmToJavaName(s), symbol);
    }
    
    public CtField lookupField(final String s, final Symbol symbol) throws CompileError {
        final CtClass lookupClass = this.lookupClass(s, false);
        try {
            return lookupClass.getField(symbol.get());
        }
        catch (NotFoundException ex) {
            throw new CompileError("no such field: " + symbol.get());
        }
    }
    
    public CtClass lookupClassByName(final ASTList list) throws CompileError {
        return this.lookupClass(Declarator.astToClassName(list, '.'), false);
    }
    
    public CtClass lookupClassByJvmName(final String s) throws CompileError {
        return this.lookupClass(jvmToJavaName(s), false);
    }
    
    public CtClass lookupClass(final Declarator declarator) throws CompileError {
        return this.lookupClass(declarator.getType(), declarator.getArrayDim(), declarator.getClassName());
    }
    
    public CtClass lookupClass(final int n, int n2, final String s) throws CompileError {
        String s2;
        if (n == 307) {
            final CtClass lookupClassByJvmName = this.lookupClassByJvmName(s);
            if (n2 <= 0) {
                return lookupClassByJvmName;
            }
            s2 = lookupClassByJvmName.getName();
        }
        else {
            s2 = getTypeName(n);
        }
        while (n2-- > 0) {
            s2 += "[]";
        }
        return this.lookupClass(s2, false);
    }
    
    static String getTypeName(final int n) throws CompileError {
        String s = "";
        switch (n) {
            case 301:
                s = "boolean";
                break;
            case 306:
                s = "char";
                break;
            case 303:
                s = "byte";
                break;
            case 334:
                s = "short";
                break;
            case 324:
                s = "int";
                break;
            case 326:
                s = "long";
                break;
            case 317:
                s = "float";
                break;
            case 312:
                s = "double";
                break;
            case 344:
                s = "void";
                break;
            default:
                fatal();
                break;
        }
        return s;
    }
    
    public CtClass lookupClass(final String s, final boolean b) throws CompileError {
        final Hashtable invalidNames = this.getInvalidNames();
        final String value = invalidNames.get(s);
        if (value == "<invalid>") {
            throw new CompileError("no such class: " + s);
        }
        if (value != null) {
            try {
                return this.classPool.get(value);
            }
            catch (NotFoundException ex) {}
        }
        CtClass ctClass;
        try {
            ctClass = this.lookupClass0(s, b);
        }
        catch (NotFoundException ex2) {
            ctClass = this.searchImports(s);
        }
        invalidNames.put(s, ctClass.getName());
        return ctClass;
    }
    
    public static int getInvalidMapSize() {
        return MemberResolver.invalidNamesMap.size();
    }
    
    private Hashtable getInvalidNames() {
        Hashtable<?, ?> invalidNames = (Hashtable<?, ?>)this.invalidNames;
        if (invalidNames == null) {
            synchronized (MemberResolver.class) {
                final WeakReference<Hashtable<?, ?>> weakReference = MemberResolver.invalidNamesMap.get(this.classPool);
                if (weakReference != null) {
                    invalidNames = weakReference.get();
                }
                if (invalidNames == null) {
                    invalidNames = new Hashtable<Object, Object>();
                    MemberResolver.invalidNamesMap.put(this.classPool, new WeakReference<Hashtable>(invalidNames));
                }
            }
            this.invalidNames = invalidNames;
        }
        return invalidNames;
    }
    
    private CtClass searchImports(final String s) throws CompileError {
        if (s.indexOf(46) < 0) {
            final Iterator importedPackages = this.classPool.getImportedPackages();
            while (importedPackages.hasNext()) {
                final String s2 = importedPackages.next();
                final String string = s2 + '.' + s;
                try {
                    return this.classPool.get(string);
                }
                catch (NotFoundException ex) {
                    try {
                        if (s2.endsWith("." + s)) {
                            return this.classPool.get(s2);
                        }
                        continue;
                    }
                    catch (NotFoundException ex2) {}
                    continue;
                }
                break;
            }
        }
        this.getInvalidNames().put(s, "<invalid>");
        throw new CompileError("no such class: " + s);
    }
    
    private CtClass lookupClass0(String string, final boolean b) throws NotFoundException {
        CtClass value = null;
        do {
            try {
                value = this.classPool.get(string);
            }
            catch (NotFoundException ex) {
                final int lastIndex = string.lastIndexOf(46);
                if (lastIndex < 0) {
                    throw ex;
                }
                final StringBuffer sb = new StringBuffer(string);
                sb.setCharAt(lastIndex, '$');
                string = sb.toString();
            }
        } while (value == null);
        return value;
    }
    
    public String resolveClassName(final ASTList list) throws CompileError {
        if (list == null) {
            return null;
        }
        return javaToJvmName(this.lookupClassByName(list).getName());
    }
    
    public String resolveJvmClassName(final String s) throws CompileError {
        if (s == null) {
            return null;
        }
        return javaToJvmName(this.lookupClassByJvmName(s).getName());
    }
    
    public static CtClass getSuperclass(final CtClass ctClass) throws CompileError {
        try {
            final CtClass superclass = ctClass.getSuperclass();
            if (superclass != null) {
                return superclass;
            }
        }
        catch (NotFoundException ex) {}
        throw new CompileError("cannot find the super class of " + ctClass.getName());
    }
    
    public static CtClass getSuperInterface(final CtClass ctClass, final String s) throws CompileError {
        try {
            final CtClass[] interfaces = ctClass.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i].getName().equals(s)) {
                    return interfaces[i];
                }
            }
        }
        catch (NotFoundException ex) {}
        throw new CompileError("cannot find the super inetrface " + s + " of " + ctClass.getName());
    }
    
    public static String javaToJvmName(final String s) {
        return s.replace('.', '/');
    }
    
    public static String jvmToJavaName(final String s) {
        return s.replace('/', '.');
    }
    
    public static int descToType(final char c) throws CompileError {
        switch (c) {
            case 'Z':
                return 301;
            case 'C':
                return 306;
            case 'B':
                return 303;
            case 'S':
                return 334;
            case 'I':
                return 324;
            case 'J':
                return 326;
            case 'F':
                return 317;
            case 'D':
                return 312;
            case 'V':
                return 344;
            case 'L':
            case '[':
                return 307;
            default:
                fatal();
                return 344;
        }
    }
    
    public static int getModifiers(ASTList tail) {
        int n = 0;
        while (tail != null) {
            final Keyword keyword = (Keyword)tail.head();
            tail = tail.tail();
            switch (keyword.get()) {
                case 335:
                    n |= 0x8;
                    continue;
                case 315:
                    n |= 0x10;
                    continue;
                case 338:
                    n |= 0x20;
                    continue;
                case 300:
                    n |= 0x400;
                    continue;
                case 332:
                    n |= 0x1;
                    continue;
                case 331:
                    n |= 0x4;
                    continue;
                case 330:
                    n |= 0x2;
                    continue;
                case 345:
                    n |= 0x40;
                    continue;
                case 342:
                    n |= 0x80;
                    continue;
                case 347:
                    n |= 0x800;
                    continue;
            }
        }
        return n;
    }
    
    static {
        MemberResolver.invalidNamesMap = new WeakHashMap();
    }
}
