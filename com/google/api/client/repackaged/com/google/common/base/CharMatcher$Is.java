package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static final class Is extends FastMatcher
{
    private final char match;
    
    Is(final char match) {
        super();
        this.match = match;
    }
    
    @Override
    public boolean matches(final char c) {
        return c == this.match;
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final char c) {
        return charSequence.toString().replace(this.match, c);
    }
    
    @Override
    public CharMatcher and(final CharMatcher charMatcher) {
        return charMatcher.matches(this.match) ? this : CharMatcher.none();
    }
    
    @Override
    public CharMatcher or(final CharMatcher charMatcher) {
        return charMatcher.matches(this.match) ? charMatcher : super.or(charMatcher);
    }
    
    @Override
    public CharMatcher negate() {
        return CharMatcher.isNot(this.match);
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        set.set(this.match);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.is('" + CharMatcher.access$100(this.match) + "')";
    }
}
