package com.google.api.client.repackaged.com.google.common.base;

import java.io.IOException;
import java.util.Iterator;

class Joiner$2 extends Joiner {
    final /* synthetic */ Joiner this$0;
    
    Joiner$2(final Joiner this$0, final Joiner joiner) {
        this.this$0 = this$0;
        super(joiner, null);
    }
    
    @Override
    public <A extends Appendable> A appendTo(final A a, final Iterator<?> iterator) throws IOException {
        Preconditions.<A>checkNotNull(a, (Object)"appendable");
        Preconditions.<Iterator<?>>checkNotNull(iterator, (Object)"parts");
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            if (next != null) {
                a.append(this.this$0.toString(next));
                break;
            }
        }
        while (iterator.hasNext()) {
            final Object next2 = iterator.next();
            if (next2 != null) {
                a.append(Joiner.access$100(this.this$0));
                a.append(this.this$0.toString(next2));
            }
        }
        return a;
    }
    
    @Override
    public Joiner useForNull(final String s) {
        throw new UnsupportedOperationException("already specified skipNulls");
    }
    
    @Override
    public MapJoiner withKeyValueSeparator(final String s) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
    }
}