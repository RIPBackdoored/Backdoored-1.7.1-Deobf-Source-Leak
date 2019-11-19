package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;

static final class Splitter$3 implements Strategy {
    final /* synthetic */ CommonPattern val$separatorPattern;
    
    Splitter$3(final CommonPattern val$separatorPattern) {
        this.val$separatorPattern = val$separatorPattern;
        super();
    }
    
    @Override
    public SplittingIterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return new SplittingIterator(splitter, charSequence) {
            final /* synthetic */ CommonMatcher val$matcher = Splitter$3.this.val$separatorPattern.matcher(charSequence);
            final /* synthetic */ Splitter$3 this$0;
            
            Splitter$3$1(final Splitter splitter, final CharSequence charSequence) {
                this.this$0 = this$0;
                super(splitter, charSequence);
            }
            
            public int separatorStart(final int n) {
                return this.val$matcher.find(n) ? this.val$matcher.start() : -1;
            }
            
            public int separatorEnd(final int n) {
                return this.val$matcher.end();
            }
        };
    }
    
    @Override
    public /* bridge */ Iterator iterator(final Splitter splitter, final CharSequence charSequence) {
        return this.iterator(splitter, charSequence);
    }
}