package com.google.api.client.repackaged.com.google.common.base;

import java.util.NoSuchElementException;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
abstract class AbstractIterator<T> implements Iterator<T>
{
    private State state;
    private T next;
    
    protected AbstractIterator() {
        super();
        this.state = State.NOT_READY;
    }
    
    protected abstract T computeNext();
    
    @Nullable
    @CanIgnoreReturnValue
    protected final T endOfData() {
        this.state = State.DONE;
        return null;
    }
    
    @Override
    public final boolean hasNext() {
        Preconditions.checkState(this.state != State.FAILED);
        switch (this.state) {
            case READY:
                return true;
            case DONE:
                return false;
            default:
                return this.tryToComputeNext();
        }
    }
    
    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = this.computeNext();
        if (this.state != State.DONE) {
            this.state = State.READY;
            return true;
        }
        return false;
    }
    
    @Override
    public final T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NOT_READY;
        final T next = this.next;
        this.next = null;
        return next;
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
