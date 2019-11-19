package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.io.Serializable;

private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable
{
    final Converter<A, B> original;
    private static final long serialVersionUID = 0L;
    
    ReverseConverter(final Converter<A, B> original) {
        super();
        this.original = original;
    }
    
    @Override
    protected A doForward(final B b) {
        throw new AssertionError();
    }
    
    @Override
    protected B doBackward(final A a) {
        throw new AssertionError();
    }
    
    @Nullable
    @Override
    A correctedDoForward(@Nullable final B b) {
        return this.original.correctedDoBackward(b);
    }
    
    @Nullable
    @Override
    B correctedDoBackward(@Nullable final A a) {
        return this.original.correctedDoForward(a);
    }
    
    @Override
    public Converter<A, B> reverse() {
        return this.original;
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return o instanceof ReverseConverter && this.original.equals(((ReverseConverter)o).original);
    }
    
    @Override
    public int hashCode() {
        return ~this.original.hashCode();
    }
    
    @Override
    public String toString() {
        return this.original + ".reverse()";
    }
}
