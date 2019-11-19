package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;

static final class Splitter$4 implements Strategy {
    final /* synthetic */ int val$length;
    
    Splitter$4(final int val$length) {
        this.val$length = val$length;
        super();
    }
    
    @Override
    public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return new SplittingIterator(splitter, charSequence) {
            final /* synthetic */ Splitter$4 this$0;
            
            Splitter$4$1(final Splitter splitter, final CharSequence charSequence) {
                this.this$0 = this$0;
                super(splitter, charSequence);
            }
            
            public int separatorStart(final int n) {
                final int n2 = n + this.this$0.val$length;
                return (n2 < this.toSplit.length()) ? n2 : -1;
            }
            
            public int separatorEnd(final int n) {
                return n;
            }
        };
    }
    
    @Override
    public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return this.iterator(splitter, charSequence);
    }
}