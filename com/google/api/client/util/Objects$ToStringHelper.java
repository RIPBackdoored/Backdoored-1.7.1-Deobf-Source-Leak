package com.google.api.client.util;

public static final class ToStringHelper
{
    private final String className;
    private ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;
    
    ToStringHelper(final String className) {
        super();
        this.holderHead = new ValueHolder();
        this.holderTail = this.holderHead;
        this.className = className;
    }
    
    public ToStringHelper omitNullValues() {
        this.omitNullValues = true;
        return this;
    }
    
    public ToStringHelper add(final String s, final Object o) {
        return this.addHolder(s, o);
    }
    
    @Override
    public String toString() {
        final boolean omitNullValues = this.omitNullValues;
        String s = "";
        final StringBuilder append = new StringBuilder(32).append(this.className).append('{');
        for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
            if (!omitNullValues || valueHolder.value != null) {
                append.append(s);
                s = ", ";
                if (valueHolder.name != null) {
                    append.append(valueHolder.name).append('=');
                }
                append.append(valueHolder.value);
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
    
    private ToStringHelper addHolder(final String s, final Object value) {
        final ValueHolder addHolder = this.addHolder();
        addHolder.value = value;
        addHolder.name = Preconditions.<String>checkNotNull(s);
        return this;
    }
}
