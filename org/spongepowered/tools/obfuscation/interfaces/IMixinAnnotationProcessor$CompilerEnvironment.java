package org.spongepowered.tools.obfuscation.interfaces;

public enum CompilerEnvironment
{
    JAVAC, 
    JDT;
    
    private static final /* synthetic */ CompilerEnvironment[] $VALUES;
    
    public static CompilerEnvironment[] values() {
        return CompilerEnvironment.$VALUES.clone();
    }
    
    public static CompilerEnvironment valueOf(final String s) {
        return Enum.<CompilerEnvironment>valueOf(CompilerEnvironment.class, s);
    }
    
    static {
        $VALUES = new CompilerEnvironment[] { CompilerEnvironment.JAVAC, CompilerEnvironment.JDT };
    }
}
