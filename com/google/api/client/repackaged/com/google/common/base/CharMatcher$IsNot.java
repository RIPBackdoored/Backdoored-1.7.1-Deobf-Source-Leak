package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static final class IsNot extends FastMatcher
{
    private final char match;
    
    IsNot(final char match) {
        super();
        this.match = match;
    }
    
    @Override
    public boolean matches(final char c) {
        return c != this.match;
    }
    
    @Override
    public CharMatcher and(final CharMatcher charMatcher) {
        return charMatcher.matches(this.match) ? super.and(charMatcher) : charMatcher;
    }
    
    @Override
    public CharMatcher or(final CharMatcher charMatcher) {
        return charMatcher.matches(this.match) ? CharMatcher.any() : this;
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        set.set(0, this.match);
        set.set(this.match + '\u0001', 65536);
    }
    
    @Override
    public CharMatcher negate() {
        return CharMatcher.is(this.match);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.isNot('" + CharMatcher.access$100(this.match) + "')";
    }
}
