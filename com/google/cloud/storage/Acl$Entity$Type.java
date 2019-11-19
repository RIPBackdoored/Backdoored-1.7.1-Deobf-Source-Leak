package com.google.cloud.storage;

public enum Type
{
    DOMAIN, 
    GROUP, 
    USER, 
    PROJECT, 
    UNKNOWN;
    
    private static final /* synthetic */ Type[] $VALUES;
    
    public static Type[] values() {
        return Type.$VALUES.clone();
    }
    
    public static Type valueOf(final String s) {
        return Enum.<Type>valueOf(Type.class, s);
    }
    
    static {
        $VALUES = new Type[] { Type.DOMAIN, Type.GROUP, Type.USER, Type.PROJECT, Type.UNKNOWN };
    }
}
