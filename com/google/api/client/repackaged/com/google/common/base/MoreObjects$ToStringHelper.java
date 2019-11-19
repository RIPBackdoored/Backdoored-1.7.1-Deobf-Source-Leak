package com.google.api.client.repackaged.com.google.common.base;

import java.util.Arrays;
import javax.annotation.Nullable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public static final class ToStringHelper
{
    private final String className;
    private final ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;
    
    private ToStringHelper(final String s) {
        super();
        this.holderHead = new ValueHolder();
        this.holderTail = this.holderHead;
        this.omitNullValues = false;
        this.className = Preconditions.<String>checkNotNull(s);
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper omitNullValues() {
        this.omitNullValues = true;
        return this;
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, @Nullable final Object o) {
        return this.addHolder(s, o);
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final boolean b) {
        return this.addHolder(s, String.valueOf(b));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final char c) {
        return this.addHolder(s, String.valueOf(c));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final double n) {
        return this.addHolder(s, String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final float n) {
        return this.addHolder(s, String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final int n) {
        return this.addHolder(s, String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper add(final String s, final long n) {
        return this.addHolder(s, String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(@Nullable final Object o) {
        return this.addHolder(o);
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final boolean b) {
        return this.addHolder(String.valueOf(b));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final char c) {
        return this.addHolder(String.valueOf(c));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final double n) {
        return this.addHolder(String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final float n) {
        return this.addHolder(String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final int n) {
        return this.addHolder(String.valueOf(n));
    }
    
    @CanIgnoreReturnValue
    public ToStringHelper addValue(final long n) {
        return this.addHolder(String.valueOf(n));
    }
    
    @Override
    public String toString() {
        final boolean omitNullValues = this.omitNullValues;
        String s = "";
        final StringBuilder append = new StringBuilder(32).append(this.className).append('{');
        for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
            final Object value = valueHolder.value;
            if (!omitNullValues || value != null) {
                append.append(s);
                s = ", ";
                if (valueHolder.name != null) {
                    append.append(valueHolder.name).append('=');
                }
                if (value != null && value.getClass().isArray()) {
                    final String deepToString = Arrays.deepToString(new Object[] { value });
                    append.append(deepToString, 1, deepToString.length() - 1);
                }
                else {
                    append.append(value);
                }
            }
        }
        return append.append('}').toString();
    }
    
    private ValueHolder addHolder() {
        final ValueHolder valueHolder = new ValueHolder();
        final ValueHolder holderTail = this.holderTail;
        final ValueHolder valueHolder2 = valueHolder;
        holderTail.next = valueHolder2;
        this.holderTail = valueHolder2;
        return valueHolder;
    }
    
    private ToStringHelper addHolder(@Nullable final Object value) {
        this.addHolder().value = value;
        return this;
    }
    
    private ToStringHelper addHolder(final String s, @Nullable final Object value) {
        final ValueHolder addHolder = this.addHolder();
        addHolder.value = value;
        addHolder.name = Preconditions.<String>checkNotNull(s);
        return this;
    }
    
    ToStringHelper(final String s, final MoreObjects$1 object) {
        this(s);
    }
}
