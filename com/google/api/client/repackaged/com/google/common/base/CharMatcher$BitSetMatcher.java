package com.google.api.client.repackaged.com.google.common.base;

import java.util.BitSet;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
private static final class BitSetMatcher extends NamedFastMatcher
{
    private final BitSet table;
    
    private BitSetMatcher(BitSet table, final String s) {
        super(s);
        if (table.length() + 64 < table.size()) {
            table = (BitSet)table.clone();
        }
        this.table = table;
    }
    
    @Override
    public boolean matches(final char c) {
        return this.table.get(c);
    }
    
    @Override
    void setBits(final BitSet set) {
        set.or(this.table);
    }
    
    BitSetMatcher(final BitSet set, final String s, final CharMatcher$1 negatedFastMatcher) {
        this(set, s);
    }
}
