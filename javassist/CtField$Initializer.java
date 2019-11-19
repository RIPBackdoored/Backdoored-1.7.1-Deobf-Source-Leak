package javassist;

import javassist.bytecode.ConstPool;
import javassist.compiler.Javac;
import javassist.bytecode.Bytecode;
import javassist.compiler.ast.ASTree;

public abstract static class Initializer
{
    public Initializer() {
        super();
    }
    
    public static Initializer constant(final int n) {
        return new IntInitializer(n);
    }
    
    public static Initializer constant(final boolean b) {
        return new IntInitializer(b ? 1 : 0);
    }
    
    public static Initializer constant(final long n) {
        return new LongInitializer(n);
    }
    
    public static Initializer constant(final float n) {
        return new FloatInitializer(n);
    }
    
    public static Initializer constant(final double n) {
        return new DoubleInitializer(n);
    }
    
    public static Initializer constant(final String s) {
        return new StringInitializer(s);
    }
    
    public static Initializer byParameter(final int nthParam) {
        final ParamInitializer paramInitializer = new ParamInitializer();
        paramInitializer.nthParam = nthParam;
        return paramInitializer;
    }
    
    public static Initializer byNew(final CtClass objectType) {
        final NewInitializer newInitializer = new NewInitializer();
        newInitializer.objectType = objectType;
        newInitializer.stringParams = null;
        newInitializer.withConstructorParams = false;
        return newInitializer;
    }
    
    public static Initializer byNew(final CtClass objectType, final String[] stringParams) {
        final NewInitializer newInitializer = new NewInitializer();
        newInitializer.objectType = objectType;
        newInitializer.stringParams = stringParams;
        newInitializer.withConstructorParams = false;
        return newInitializer;
    }
    
    public static Initializer byNewWithParams(final CtClass objectType) {
        final NewInitializer newInitializer = new NewInitializer();
        newInitializer.objectType = objectType;
        newInitializer.stringParams = null;
        newInitializer.withConstructorParams = true;
        return newInitializer;
    }
    
    public static Initializer byNewWithParams(final CtClass objectType, final String[] stringParams) {
        final NewInitializer newInitializer = new NewInitializer();
        newInitializer.objectType = objectType;
        newInitializer.stringParams = stringParams;
        newInitializer.withConstructorParams = true;
        return newInitializer;
    }
    
    public static Initializer byCall(final CtClass objectType, final String methodName) {
        final MethodInitializer methodInitializer = new MethodInitializer();
        methodInitializer.objectType = objectType;
        methodInitializer.methodName = methodName;
        methodInitializer.stringParams = null;
        methodInitializer.withConstructorParams = false;
        return methodInitializer;
    }
    
    public static Initializer byCall(final CtClass objectType, final String methodName, final String[] stringParams) {
        final MethodInitializer methodInitializer = new MethodInitializer();
        methodInitializer.objectType = objectType;
        methodInitializer.methodName = methodName;
        methodInitializer.stringParams = stringParams;
        methodInitializer.withConstructorParams = false;
        return methodInitializer;
    }
    
    public static Initializer byCallWithParams(final CtClass objectType, final String methodName) {
        final MethodInitializer methodInitializer = new MethodInitializer();
        methodInitializer.objectType = objectType;
        methodInitializer.methodName = methodName;
        methodInitializer.stringParams = null;
        methodInitializer.withConstructorParams = true;
        return methodInitializer;
    }
    
    public static Initializer byCallWithParams(final CtClass objectType, final String methodName, final String[] stringParams) {
        final MethodInitializer methodInitializer = new MethodInitializer();
        methodInitializer.objectType = objectType;
        methodInitializer.methodName = methodName;
        methodInitializer.stringParams = stringParams;
        methodInitializer.withConstructorParams = true;
        return methodInitializer;
    }
    
    public static Initializer byNewArray(final CtClass ctClass, final int n) throws NotFoundException {
        return new ArrayInitializer(ctClass.getComponentType(), n);
    }
    
    public static Initializer byNewArray(final CtClass ctClass, final int[] array) {
        return new MultiArrayInitializer(ctClass, array);
    }
    
    public static Initializer byExpr(final String s) {
        return new CodeInitializer(s);
    }
    
    static Initializer byExpr(final ASTree asTree) {
        return new PtreeInitializer(asTree);
    }
    
    void check(final String s) throws CannotCompileException {
    }
    
    abstract int compile(final CtClass p0, final String p1, final Bytecode p2, final CtClass[] p3, final Javac p4) throws CannotCompileException;
    
    abstract int compileIfStatic(final CtClass p0, final String p1, final Bytecode p2, final Javac p3) throws CannotCompileException;
    
    int getConstantValue(final ConstPool constPool, final CtClass ctClass) {
        return 0;
    }
}
