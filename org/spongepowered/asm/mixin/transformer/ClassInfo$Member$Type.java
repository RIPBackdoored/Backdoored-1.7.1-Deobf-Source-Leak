package org.spongepowered.asm.mixin.transformer;

enum Type
{
    METHOD, 
    FIELD;
    
    private static final /* synthetic */ Type[] $VALUES;
    
    public static Type[] values() {
        return Type.$VALUES.clone();
    }
    
    public static Type valueOf(final String s) {
        return Enum.<Type>valueOf(Type.class, s);
    }
    
    static {
        $VALUES = new Type[] { Type.METHOD, Type.FIELD };
    }
}
