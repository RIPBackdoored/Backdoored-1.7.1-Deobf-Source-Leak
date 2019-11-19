package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;

static final class Splitter$1 implements Strategy {
    final /* synthetic */ CharMatcher val$separatorMatcher;
    
    Splitter$1(final CharMatcher val$separatorMatcher) {
        this.val$separatorMatcher = val$separatorMatcher;
        super();
    }
    
    @Override
    public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return new SplittingIterator(splitter, charSequence) {
            final /* synthetic */ Splitter$1 this$0;
            
            Splitter$1$1(final Splitter splitter, final CharSequence charSequence) {
                this.this$0 = this$0;
                super(splitter, charSequence);
            }
            
            @Override
            int separatorStart(final int n) {
                return this.this$0.val$separatorMatcher.indexIn(this.toSplit, n);
            }
            
            @Override
            int separatorEnd(final int n) {
                return n + 1;
            }
        };
    }
    
    @Override
    public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return this.iterator(splitter, charSequence);
    }
}