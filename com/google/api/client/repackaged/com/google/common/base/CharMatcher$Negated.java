package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static class Negated extends CharMatcher
{
    final CharMatcher original;
    
    Negated(final CharMatcher charMatcher) {
        super();
        this.original = Preconditions.<CharMatcher>checkNotNull(charMatcher);
    }
    
    @Override
    public boolean matches(final char c) {
        return !this.original.matches(c);
    }
    
    @Override
    public boolean matchesAllOf(final CharSequence charSequence) {
        return this.original.matchesNoneOf(charSequence);
    }
    
    @Override
    public boolean matchesNoneOf(final CharSequence charSequence) {
        return this.original.matchesAllOf(charSequence);
    }
    
    @Override
    public int countIn(final CharSequence charSequence) {
        return charSequence.length() - this.original.countIn(charSequence);
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        final BitSet bits = new BitSet();
        this.original.setBits(bits);
        bits.flip(0, 65536);
        set.or(bits);
    }
    
    @Override
    public CharMatcher negate() {
        return this.original;
    }
    
    @Override
    public String toString() {
        return this.original + ".negate()";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
}
