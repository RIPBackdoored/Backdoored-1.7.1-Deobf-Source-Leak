package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;

class Joiner$1 extends Joiner {
    final /* synthetic */ String val$nullText;
    final /* synthetic */ Joiner this$0;
    
    Joiner$1(final Joiner this$0, final Joiner joiner, final String val$nullText) {
        this.this$0 = this$0;
        this.val$nullText = val$nullText;
        super(joiner, null);
    }
    
    @Override
    CharSequence toString(@Nullable final Object o) {
        return (o == null) ? this.val$nullText : this.this$0.toString(o);
    }
    
    @Override
    public Joiner useForNull(final String s) {
        throw new UnsupportedOperationException("already specified useForNull");
    }
    
    @Override
    public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
    }
}