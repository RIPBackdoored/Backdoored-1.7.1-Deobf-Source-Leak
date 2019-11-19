package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static final class And extends CharMatcher
{
    final CharMatcher first;
    final CharMatcher second;
    
    And(final CharMatcher charMatcher, final CharMatcher charMatcher2) {
        super();
        this.first = Preconditions.<CharMatcher>checkNotNull(charMatcher);
        this.second = Preconditions.<CharMatcher>checkNotNull(charMatcher2);
    }
    
    @Override
    public boolean matches(final char c) {
        return this.first.matches(c) && this.second.matches(c);
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        final BitSet bits = new BitSet();
        this.first.setBits(bits);
        final BitSet bits2 = new BitSet();
        this.second.setBits(bits2);
        bits.and(bits2);
        set.or(bits);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.and(" + this.first + ", " + this.second + ")";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
}
