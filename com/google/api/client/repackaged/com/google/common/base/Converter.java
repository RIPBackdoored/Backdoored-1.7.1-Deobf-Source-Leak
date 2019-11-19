package com.google.api.client.repackaged.com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class Converter<A, B> implements Function<A, B>
{
    private final boolean handleNullAutomatically;
    @LazyInit
    private transient Converter<B, A> reverse;
    
    protected Converter() {
        this(true);
    }
    
    Converter(final boolean handleNullAutomatically) {
        super();
        this.handleNullAutomatically = handleNullAutomatically;
    }
    
    protected abstract B doForward(final A p0);
    
    protected abstract A doBackward(final B p0);
    
    @Nullable
    @CanIgnoreReturnValue
    public final B convert(@Nullable final A a) {
        return this.correctedDoForward(a);
    }
    
    @Nullable
    B correctedDoForward(@Nullable final A a) {
        if (this.handleNullAutomatically) {
            return (a == null) ? null : Preconditions.<B>checkNotNull(this.doForward(a));
        }
        return this.doForward(a);
    }
    
    @Nullable
    A correctedDoBackward(@Nullable final B b) {
        if (this.handleNullAutomatically) {
            return (b == null) ? null : Preconditions.<A>checkNotNull(this.doBackward(b));
        }
        return this.doBackward(b);
    }
    
    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.<Iterable<? extends A>>checkNotNull(iterable, (Object)"fromIterable");
        return new Iterable<B>() {
            final /* synthetic */ Iterable val$fromIterable;
            final /* synthetic */ Converter this$0;
            
            Converter$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    private final Iterator<? extends A> fromIterator = iterable.iterator();
                    final /* synthetic */ Converter$1 this$1;
                    
                    Converter$1$1() {
                        this.this$1 = this$1;
                        super();
                    }
                    
                    @Override
                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }
                    
                    @Override
                    public B next() {
                        return this.this$1.this$0.convert(this.fromIterator.next());
                    }
                    
                    @Override
                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }
    
    @CanIgnoreReturnValue
    public Converter<B, A> reverse() {
        final Converter<B, A> reverse = this.reverse;
        return (reverse == null) ? (this.reverse = (Converter<B, A>)new ReverseConverter((Converter<Object, Object>)this)) : reverse;
    }
    
    public final <C> Converter<A, C> andThen(final Converter<B, C> converter) {
        return (Converter<A, C>)this.<Object>doAndThen((Converter<B, Object>)converter);
    }
    
     <C> Converter<A, C> doAndThen(final Converter<B, C> converter) {
        return (Converter<A, C>)new ConverterComposition((Converter<Object, Object>)this, (Converter<Object, Object>)Preconditions.<Converter<B, C>>checkNotNull(converter));
    }
    
    @Deprecated
    @Nullable
    @CanIgnoreReturnValue
    @Override
    public final B apply(@Nullable final A a) {
        return this.convert(a);
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        return super.equals(o);
    }
    
    public static <A, B> Converter<A, B> from(final Function<? super A, ? extends B> function, final Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter<A, B>((Function)function, (Function)function2);
    }
    
    public static <T> Converter<T, T> identity() {
        return (Converter<T, T>)IdentityConverter.INSTANCE;
    }
}
