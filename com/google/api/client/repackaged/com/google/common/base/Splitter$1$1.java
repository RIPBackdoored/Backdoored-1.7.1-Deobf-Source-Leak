package com.google.api.client.repackaged.com.google.common.base;

class Splitter$1$1 extends SplittingIterator {
    final /* synthetic */ Splitter$1 this$0;
    
    Splitter$1$1(final Splitter$1 this$0, final Splitter splitter, final CharSequence charSequence) {
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
}