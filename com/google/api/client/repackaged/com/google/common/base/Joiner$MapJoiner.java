package com.google.api.client.repackaged.com.google.common.base;

import java.util.Iterator;
import com.google.api.client.repackaged.com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.util.Map;

public static final class MapJoiner
{
    private final Joiner joiner;
    private final String keyValueSeparator;
    
    private MapJoiner(final Joiner joiner, final String s) {
        super();
        this.joiner = joiner;
        this.keyValueSeparator = Preconditions.<String>checkNotNull(s);
    }
    
    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(final A a, final Map<?, ?> map) throws IOException {
        return this.<A>appendTo(a, map.entrySet());
    }
    
    @CanIgnoreReturnValue
    public StringBuilder appendTo(final StringBuilder sb, final Map<?, ?> map) {
        return this.appendTo(sb, (Iterable<? extends Map.Entry<?, ?>>)map.entrySet());
    }
    
    public String join(final Map<?, ?> map) {
        return this.join(map.entrySet());
    }
    
    @Beta
    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(final A a, final Iterable<? extends Map.Entry<?, ?>> iterable) throws IOException {
        return this.<A>appendTo(a, iterable.iterator());
    }
    
    @Beta
    @CanIgnoreReturnValue
    public <A extends Appendable> A appendTo(final A a, final Iterator<? extends Map.Entry<?, ?>> iterator) throws IOException {
        Preconditions.<A>checkNotNull(a);
        if (iterator.hasNext()) {
            final Map.Entry entry = (Map.Entry)iterator.next();
            a.append(this.joiner.toString(entry.getKey()));
            a.append(this.keyValueSeparator);
            a.append(this.joiner.toString(entry.getValue()));
            while (iterator.hasNext()) {
                a.append(Joiner.access$100(this.joiner));
                final Map.Entry entry2 = (Map.Entry)iterator.next();
                a.append(this.joiner.toString(entry2.getKey()));
                a.append(this.keyValueSeparator);
                a.append(this.joiner.toString(entry2.getValue()));
            }
        }
        return a;
    }
    
    @Beta
    @CanIgnoreReturnValue
    public StringBuilder appendTo(final StringBuilder sb, final Iterable<? extends Map.Entry<?, ?>> iterable) {
        return this.appendTo(sb, iterable.iterator());
    }
    
    @Beta
    @CanIgnoreReturnValue
    public StringBuilder appendTo(final StringBuilder sb, final Iterator<? extends Map.Entry<?, ?>> iterator) {
        try {
            this.<StringBuilder>appendTo(sb, iterator);
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
        return sb;
    }
    
    @Beta
    public String join(final Iterable<? extends Map.Entry<?, ?>> iterable) {
        return this.join(iterable.iterator());
    }
    
    @Beta
    public String join(final Iterator<? extends Map.Entry<?, ?>> iterator) {
        return this.appendTo(new StringBuilder(), iterator).toString();
    }
    
    public MapJoiner useForNull(final String s) {
        return new MapJoiner(this.joiner.useForNull(s), this.keyValueSeparator);
    }
    
    MapJoiner(final Joiner joiner, final String s, final Joiner$1 joiner2) {
        this(joiner, s);
    }
}
