package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.io.Serializable;

private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable
{
    final Converter<A, B> first;
    final Converter<B, C> second;
    private static final long serialVersionUID = 0L;
    
    ConverterComposition(final Converter<A, B> first, final Converter<B, C> second) {
        super();
        this.first = first;
        this.second = second;
    }
    
    @Override
    protected C doForward(final A a) {
        throw new AssertionError();
    }
    
    @Override
    protected A doBackward(final C c) {
        throw new AssertionError();
    }
    
    @Nullable
    @Override
    C correctedDoForward(@Nullable final A a) {
        return this.second.correctedDoForward(this.first.correctedDoForward(a));
    }
    
    @Nullable
    @Override
    A correctedDoBackward(@Nullable final C c) {
        return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (o instanceof ConverterComposition) {
            final ConverterComposition converterComposition = (ConverterComposition)o;
            return this.first.equals(converterComposition.first) && this.second.equals(converterComposition.second);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.first.hashCode() + this.second.hashCode();
    }
    
    @Override
    public String toString() {
        return this.first + ".andThen(" + this.second + ")";
    }
}
