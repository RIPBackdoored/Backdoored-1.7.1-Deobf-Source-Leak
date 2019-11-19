package com.google.api.client.repackaged.com.google.common.base;

class Splitter$4$1 extends SplittingIterator {
    final /* synthetic */ Splitter$4 this$0;
    
    Splitter$4$1(final Splitter$4 this$0, final Splitter splitter, final CharSequence charSequence) {
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
}