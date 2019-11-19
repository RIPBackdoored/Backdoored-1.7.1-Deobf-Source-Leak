package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;
import java.util.Arrays;

private static final class AnyOf extends CharMatcher
{
    private final char[] chars;
    
    public AnyOf(final CharSequence charSequence) {
        super();
        Arrays.sort(this.chars = charSequence.toString().toCharArray());
    }
    
    @Override
    public boolean matches(final char c) {
        return Arrays.binarySearch(this.chars, c) >= 0;
    }
    
    @GwtIncompatible
    @Override
    void setBits(final BitSet set) {
        final char[] chars = this.chars;
        for (int length = chars.length, i = 0; i < length; ++i) {
            set.set(chars[i]);
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharMatcher.anyOf(\"");
        final char[] chars = this.chars;
        for (int length = chars.length, i = 0; i < length; ++i) {
            sb.append(CharMatcher.access$100(chars[i]));
        }
        sb.append("\")");
        return sb.toString();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
}
