package com.google.api.client.repackaged.com.google.common.base;

class Splitter$2$1 extends SplittingIterator {
    final /* synthetic */ Splitter$2 this$0;
    
    Splitter$2$1(final Splitter$2 this$0, final Splitter splitter, final CharSequence charSequence) {
        this.this$0 = this$0;
        super(splitter, charSequence);
    }
    
    public int separatorStart(final int n) {
        final int length = this.this$0.val$separator.length();
        int i = n;
        final int n2 = this.toSplit.length() - length;
    Label_0026:
        while (i <= n2) {
            for (int j = 0; j < length; ++j) {
                if (this.toSplit.charAt(j + i) != this.this$0.val$separator.charAt(j)) {
                    ++i;
                    continue Label_0026;
                }
            }
            return i;
        }
        return -1;
    }
    
    public int separatorEnd(final int n) {
        return n + this.this$0.val$separator.length();
    }
}