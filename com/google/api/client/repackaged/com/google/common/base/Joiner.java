package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import java.util.Map;
import java.util.AbstractList;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public class Joiner
{
    private final String separator;
    
    public static Joiner on(final String s) {
        return new Joiner(s);
    }
    
    public static Joiner on(final char c) {
        return new Joiner(String.valueOf(c));
    }
    
    private Joiner(final String s) {
        super();
        this.separator = Preconditions.<String>checkNotNull(s);
    }
    
    private Joiner(final Joiner joiner) {
        super();
        this.separator = joiner.separator;
    }
    
    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(final A a, final Iterable<?> iterable) throws IOException {
        return this.<A>appendTo(a, iterable.iterator());
    }
    
    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(final A a, final Iterator<?> iterator) throws IOException {
        Preconditions.<A>checkNotNull(a);
        if (iterator.hasNext()) {
            a.append(this.toString(iterator.next()));
            while (iterator.hasNext()) {
                a.append(this.separator);
                a.append(this.toString(iterator.next()));
            }
        }
        return a;
    }
    
    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(final A a, final Object[] array) throws IOException {
        return this.<A>appendTo(a, Arrays.<Object>asList(array));
    }
    
    @CanIgnoreReturnValue
    public final <A extends Appendable> A appendTo(final A a, @Nullable final Object o, @Nullable final Object o2, final Object... array) throws IOException {
        return this.<A>appendTo(a, iterable(o, o2, array));
    }
    
    @CanIgnoreReturnValue
    public final StringBuilder appendTo(final StringBuilder sb, final Iterable<?> iterable) {
        return this.appendTo(sb, iterable.iterator());
    }
    
    @CanIgnoreReturnValue
    public final StringBuilder appendTo(final StringBuilder sb, final Iterator<?> iterator) {
        try {
            this.<StringBuilder>appendTo(sb, iterator);
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
        return sb;
    }
    
    @CanIgnoreReturnValue
    public final StringBuilder appendTo(final StringBuilder sb, final Object[] array) {
        return this.appendTo(sb, (Iterable<?>)Arrays.<Object>asList(array));
    }
    
    @CanIgnoreReturnValue
    public final StringBuilder appendTo(final StringBuilder sb, @Nullable final Object o, @Nullable final Object o2, final Object... array) {
        return this.appendTo(sb, (Iterable<?>)iterable(o, o2, array));
    }
    
    public final String join(final Iterable<?> iterable) {
        return this.join(iterable.iterator());
    }
    
    public final String join(final Iterator<?> iterator) {
        return this.appendTo(new StringBuilder(), iterator).toString();
    }
    
    public final String join(final Object[] array) {
        return this.join(Arrays.<Object>asList(array));
    }
    
    public final String join(@Nullable final Object o, @Nullable final Object o2, final Object... array) {
        return this.join(iterable(o, o2, array));
    }
    
    public Joiner useForNull(final String s) {
        Preconditions.<String>checkNotNull(s);
        return new Joiner(this) {
            final /* synthetic */ String val$nullText;
            final /* synthetic */ Joiner this$0;
            
            Joiner$1(final Joiner joiner) {
                this.this$0 = this$0;
                super(joiner, null);
            }
            
            @Override
            CharSequence toString(@Nullable final Object o) {
                return (o == null) ? s : this.this$0.toString(o);
            }
            
            @Override
            public Joiner useForNull(final String s) {
                throw new UnsupportedOperationException("already specified useForNull");
            }
            
            @Override
            public Joiner skipNulls() {
                throw new UnsupportedOperationException("already specified useForNull");
            }
        };
    }
    
    public Joiner skipNulls() {
        return new Joiner(this) {
            final /* synthetic */ Joiner this$0;
            
            Joiner$2(final Joiner joiner) {
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
                        a.append(this.this$0.separator);
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
        };
    }
    
    public MapJoiner withKeyValueSeparator(final char c) {
        return this.withKeyValueSeparator(String.valueOf(c));
    }
    
    public MapJoiner withKeyValueSeparator(final String s) {
        return new MapJoiner(this, s);
    }
    
    CharSequence toString(final Object o) {
        Preconditions.<Object>checkNotNull(o);
        return (o instanceof CharSequence) ? ((CharSequence)o) : o.toString();
    }
    
    private static Iterable<Object> iterable(final Object o, final Object o2, final Object[] array) {
        Preconditions.<Object[]>checkNotNull(array);
        return new AbstractList<Object>() {
            final /* synthetic */ Object[] val$rest;
            final /* synthetic */ Object val$first;
            final /* synthetic */ Object val$second;
            
            Joiner$3() {
                super();
            }
            
            @Override
            public int size() {
                return array.length + 2;
            }
            
            @Override
            public Object get(final int n) {
                switch (n) {
                    case 0:
                        return o;
                    case 1:
                        return o2;
                    default:
                        return array[n - 2];
                }
            }
        };
    }
    
    Joiner(final Joiner joiner, final Joiner$1 joiner2) {
        this(joiner);
    }
    
    static /* synthetic */ String access$100(final Joiner joiner) {
        return joiner.separator;
    }
}
