package javassist;

import javassist.bytecode.Bytecode;

public static class ConstParameter
{
    public static ConstParameter integer(final int n) {
        return new IntConstParameter(n);
    }
    
    public static ConstParameter integer(final long n) {
        return new LongConstParameter(n);
    }
    
    public static ConstParameter string(final String s) {
        return new StringConstParameter(s);
    }
    
    ConstParameter() {
        super();
    }
    
    int compile(final Bytecode bytecode) throws CannotCompileException {
        return 0;
    }
    
    String descriptor() {
        return defaultDescriptor();
    }
    
    static String defaultDescriptor() {
        return "([Ljava/lang/Object;)Ljava/lang/Object;";
    }
    
    String constDescriptor() {
        return defaultConstDescriptor();
    }
    
    static String defaultConstDescriptor() {
        return "([Ljava/lang/Object;)V";
    }
}
