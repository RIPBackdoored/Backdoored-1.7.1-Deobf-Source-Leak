package com.google.api.client.http;

import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.Preconditions;

private enum CompositeOutput
{
    PLUS(Character.valueOf('+'), "", ",", false, true), 
    HASH(Character.valueOf('#'), "#", ",", false, true), 
    DOT(Character.valueOf('.'), ".", ".", false, false), 
    FORWARD_SLASH(Character.valueOf('/'), "/", "/", false, false), 
    SEMI_COLON(Character.valueOf(';'), ";", ";", true, false), 
    QUERY(Character.valueOf('?'), "?", "&", true, false), 
    AMP(Character.valueOf('&'), "&", "&", true, false), 
    SIMPLE((Character)null, "", ",", false, false);
    
    private final Character propertyPrefix;
    private final String outputPrefix;
    private final String explodeJoiner;
    private final boolean requiresVarAssignment;
    private final boolean reservedExpansion;
    private static final /* synthetic */ CompositeOutput[] $VALUES;
    
    public static CompositeOutput[] values() {
        return CompositeOutput.$VALUES.clone();
    }
    
    public static CompositeOutput valueOf(final String s) {
        return Enum.<CompositeOutput>valueOf(CompositeOutput.class, s);
    }
    
    private CompositeOutput(final Character propertyPrefix, final String s2, final String s3, final boolean requiresVarAssignment, final boolean reservedExpansion) {
        this.propertyPrefix = propertyPrefix;
        this.outputPrefix = Preconditions.<String>checkNotNull(s2);
        this.explodeJoiner = Preconditions.<String>checkNotNull(s3);
        this.requiresVarAssignment = requiresVarAssignment;
        this.reservedExpansion = reservedExpansion;
        if (propertyPrefix != null) {
            UriTemplate.COMPOSITE_PREFIXES.put(propertyPrefix, this);
        }
    }
    
    String getOutputPrefix() {
        return this.outputPrefix;
    }
    
    String getExplodeJoiner() {
        return this.explodeJoiner;
    }
    
    boolean requiresVarAssignment() {
        return this.requiresVarAssignment;
    }
    
    int getVarNameStartIndex() {
        return (this.propertyPrefix != null) ? 1 : 0;
    }
    
    String getEncodedValue(final String s) {
        String s2;
        if (this.reservedExpansion) {
            s2 = CharEscapers.escapeUriPath(s);
        }
        else {
            s2 = CharEscapers.escapeUri(s);
        }
        return s2;
    }
    
    boolean getReservedExpansion() {
        return this.reservedExpansion;
    }
    
    static {
        $VALUES = new CompositeOutput[] { CompositeOutput.PLUS, CompositeOutput.HASH, CompositeOutput.DOT, CompositeOutput.FORWARD_SLASH, CompositeOutput.SEMI_COLON, CompositeOutput.QUERY, CompositeOutput.AMP, CompositeOutput.SIMPLE };
    }
}
