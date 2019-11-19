package com.google.cloud.storage;

public enum Type
{
    AGE, 
    CREATE_BEFORE, 
    NUM_NEWER_VERSIONS, 
    IS_LIVE, 
    UNKNOWN;
    
    private static final /* synthetic */ Type[] $VALUES;
    
    public static Type[] values() {
        return Type.$VALUES.clone();
    }
    
    public static Type valueOf(final String s) {
        return Enum.<Type>valueOf(Type.class, s);
    }
    
    static {
        $VALUES = new Type[] { Type.AGE, Type.CREATE_BEFORE, Type.NUM_NEWER_VERSIONS, Type.IS_LIVE, Type.UNKNOWN };
    }
}
