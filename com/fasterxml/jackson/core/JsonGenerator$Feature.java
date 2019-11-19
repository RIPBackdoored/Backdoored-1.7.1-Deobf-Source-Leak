package com.fasterxml.jackson.core;

public enum Feature
{
    AUTO_CLOSE_TARGET(true), 
    AUTO_CLOSE_JSON_CONTENT(true), 
    FLUSH_PASSED_TO_STREAM(true), 
    QUOTE_FIELD_NAMES(true), 
    QUOTE_NON_NUMERIC_NUMBERS(true), 
    WRITE_NUMBERS_AS_STRINGS(false), 
    WRITE_BIGDECIMAL_AS_PLAIN(false), 
    ESCAPE_NON_ASCII(false), 
    STRICT_DUPLICATE_DETECTION(false), 
    IGNORE_UNKNOWN(false);
    
    private final boolean _defaultState;
    private final int _mask;
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
        this._mask = 1 << this.ordinal();
    }
    
    public boolean enabledByDefault() {
        return this._defaultState;
    }
    
    public boolean enabledIn(final int n) {
        return (n & this._mask) != 0x0;
    }
    
    public int getMask() {
        return this._mask;
    }
    
    static {
        $VALUES = new Feature[] { Feature.AUTO_CLOSE_TARGET, Feature.AUTO_CLOSE_JSON_CONTENT, Feature.FLUSH_PASSED_TO_STREAM, Feature.QUOTE_FIELD_NAMES, Feature.QUOTE_NON_NUMERIC_NUMBERS, Feature.WRITE_NUMBERS_AS_STRINGS, Feature.WRITE_BIGDECIMAL_AS_PLAIN, Feature.ESCAPE_NON_ASCII, Feature.STRICT_DUPLICATE_DETECTION, Feature.IGNORE_UNKNOWN };
    }
}
