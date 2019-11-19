package com.google.api.client.repackaged.com.google.common.base;

import javax.annotation.Nullable;
import java.io.Serializable;

private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable
{
    private final Function<? super A, ? extends B> forwardFunction;
    private final Function<? super B, ? extends A> backwardFunction;
    
    private FunctionBasedConverter(final Function<? super A, ? extends B> function, final Function<? super B, ? extends A> function2) {
        super();
        this.forwardFunction = Preconditions.<Function<? super A, ? extends B>>checkNotNull(function);
        this.backwardFunction = Preconditions.<Function<? super B, ? extends A>>checkNotNull(function2);
    }
    
    @Override
    protected B doForward(final A a) {
        return (B)this.forwardFunction.apply((Object)a);
    }
    
    @Override
    protected A doBackward(final B b) {
        return (A)this.backwardFunction.apply((Object)b);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        if (o instanceof FunctionBasedConverter) {
            final FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter)o;
            return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.forwardFunction.hashCode() * 31 + this.backwardFunction.hashCode();
    }
    
    @Override
    public String toString() {
        return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
    }
    
    FunctionBasedConverter(final Function function, final Function function2, final Converter$1 iterable) {
        this(function, function2);
    }
}
