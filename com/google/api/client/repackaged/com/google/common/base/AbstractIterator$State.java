package com.google.api.client.repackaged.com.google.common.base;

private enum State
{
    READY, 
    NOT_READY, 
    DONE, 
    FAILED;
    
    private static final /* synthetic */ State[] $VALUES;
    
    public static State[] values() {
        return State.$VALUES.clone();
    }
    
    public static State valueOf(final String s) {
        return Enum.<State>valueOf(State.class, s);
    }
    
    static {
        $VALUES = new State[] { State.READY, State.NOT_READY, State.DONE, State.FAILED };
    }
}
