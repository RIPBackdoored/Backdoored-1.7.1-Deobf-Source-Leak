package javassist.bytecode;

public static class BootstrapMethod
{
    public int methodRef;
    public int[] arguments;
    
    public BootstrapMethod(final int methodRef, final int[] arguments) {
        super();
        this.methodRef = methodRef;
        this.arguments = arguments;
    }
}
