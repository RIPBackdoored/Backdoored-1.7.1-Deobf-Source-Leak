package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;

class Optional$1$1 extends AbstractIterator<T> {
    private final Iterator<? extends Optional<? extends T>> iterator = Preconditions.<Iterator<? extends Optional<? extends T>>>checkNotNull(this.this$0.val$optionals.iterator());
    final /* synthetic */ Optional$1 this$0;
    
    Optional$1$1(final Optional$1 this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    protected T computeNext() {
        while (this.iterator.hasNext()) {
            final Optional optional = (Optional)this.iterator.next();
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return this.endOfData();
    }
}