package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;
import java.util.Set;
import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
public abstract class Optional<T> implements Serializable
{
    private static final long serialVersionUID = 0L;
    
    public static <T> Optional<T> absent() {
        return Absent.<T>withType();
    }
    
    public static <T> Optional<T> of(final T t) {
        return new Present<T>(Preconditions.<T>checkNotNull(t));
    }
    
    public static <T> Optional<T> fromNullable(@Nullable final T t) {
        return (t == null) ? Optional.<T>absent() : new Present<T>(t);
    }
    
    Optional() {
        super();
    }
    
    public abstract boolean isPresent();
    
    public abstract T get();
    
    public abstract T or(final T p0);
    
    public abstract Optional<T> or(final Optional<? extends T> p0);
    
    @Beta
    public abstract T or(final Supplier<? extends T> p0);
    
    @Nullable
    public abstract T orNull();
    
    public abstract Set<T> asSet();
    
    public abstract <V> Optional<V> transform(final Function<? super T, V> p0);
    
    @Override
    public abstract boolean equals(@Nullable final Object p0);
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract String toString();
    
    @Beta
    public static <T> Iterable<T> presentInstances(final Iterable<? extends Optional<? extends T>> iterable) {
        Preconditions.<Iterable<? extends Optional<? extends T>>>checkNotNull(iterable);
        return new Iterable<T>() {
            final /* synthetic */ Iterable val$optionals;
            
            Optional$1() {
                super();
            }
            
            @Override
            public Iterator<T> iterator() {
                return new AbstractIterator<T>() {
                    private final Iterator<? extends Optional<? extends T>> iterator = Preconditions.<Iterator<? extends Optional<? extends T>>>checkNotNull(iterable.iterator());
                    final /* synthetic */ Optional$1 this$0;
                    
                    Optional$1$1() {
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
                };
            }
        };
    }
}
