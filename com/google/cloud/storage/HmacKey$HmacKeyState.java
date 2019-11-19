package com.google.cloud.storage;

public enum HmacKeyState
{
    ACTIVE("ACTIVE"), 
    INACTIVE("INACTIVE"), 
    DELETED("DELETED");
    
    private final String state;
    private static final /* synthetic */ HmacKeyState[] $VALUES;
    
    public static HmacKeyState[] values() {
        return HmacKeyState.$VALUES.clone();
    }
    
    public static HmacKeyState valueOf(final String s) {
        return Enum.<HmacKeyState>valueOf(HmacKeyState.class, s);
    }
    
    private HmacKeyState(final String state) {
        this.state = state;
    }
    
    static {
        $VALUES = new HmacKeyState[] { HmacKeyState.ACTIVE, HmacKeyState.INACTIVE, HmacKeyState.DELETED };
    }
}
