package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static final class InRange extends FastMatcher
{
    private final char startInclusive;
    private final char endInclusive;
    
    InRange(final char startInclusive, final char endInclusive) {
        super();
        Preconditions.checkArgument(endInclusive >= startInclusive);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }
    
    @Override
    public boolean matches(final char c) {
        return this.startInclusive <= c && c <= this.endInclusive;
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        set.set(this.startInclusive, this.endInclusive + '\u0001');
    }
    
    @Override
    public String toString() {
        return "CharMatcher.inRange('" + CharMatcher.access$100(this.startInclusive) + "', '" + CharMatcher.access$100(this.endInclusive) + "')";
    }
}
