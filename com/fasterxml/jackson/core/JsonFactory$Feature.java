package com.fasterxml.jackson.core;

public enum Feature
{
    INTERN_FIELD_NAMES(true), 
    CANONICALIZE_FIELD_NAMES(true), 
    FAIL_ON_SYMBOL_HASH_OVERFLOW(true), 
    USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);
    
    private final boolean _defaultState;
    private static final /* synthetic */ Feature[] $VALUES;
    
    public static Feature[] values() {
        return Feature.$VALUES.clone();
    }
    
    public static Feature valueOf(final String s) {
        return Enum.<Feature>valueOf(Feature.class, s);
    }
    
    public static int collectDefaults() {
        int n = 0;
        for (final Feature feature : values()) {
            if (feature.enabledByDefault()) {
                n |= feature.getMask();
            }
        }
        return n;
    }
    
    private Feature(final boolean defaultState) {
        this._defaultState = defaultState;
    }
    
    public boolean enabledByDefault() {
        return this._defaultState;
    }
    
    public boolean enabledIn(final int n) {
        return (n & this.getMask()) != 0x0;
    }
    
    public int getMask() {
        return 1 << this.ordinal();
    }
    
    static {
        $VALUES = new Feature[] { Feature.INTERN_FIELD_NAMES, Feature.CANONICALIZE_FIELD_NAMES, Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW, Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING };
    }
}
