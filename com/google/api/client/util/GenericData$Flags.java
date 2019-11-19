package com.google.api.client.util;

public enum Flags
{
    IGNORE_CASE;
    
    private static final /* synthetic */ Flags[] $VALUES;
    
    public static Flags[] values() {
        return Flags.$VALUES.clone();
    }
    
    public static Flags valueOf(final String s) {
        return Enum.<Flags>valueOf(Flags.class, s);
    }
    
    static {
        $VALUES = new Flags[] { Flags.IGNORE_CASE };
    }
}
