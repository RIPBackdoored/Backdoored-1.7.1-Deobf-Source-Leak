package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

private static final class Or extends CharMatcher
{
    final CharMatcher first;
    final CharMatcher second;
    
    Or(final CharMatcher charMatcher, final CharMatcher charMatcher2) {
        super();
        this.first = Preconditions.<CharMatcher>checkNotNull(charMatcher);
        this.second = Preconditions.<CharMatcher>checkNotNull(charMatcher2);
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        this.first.setBits(set);
        this.second.setBits(set);
    }
    
    @Override
    public boolean matches(final char c) {
        return this.first.matches(c) || this.second.matches(c);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.or(" + this.first + ", " + this.second + ")";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
}
