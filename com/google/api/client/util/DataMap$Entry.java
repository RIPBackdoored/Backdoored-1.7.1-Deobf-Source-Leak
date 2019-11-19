package com.google.api.client.util;

import java.util.Locale;
import java.util.Map;

final class Entry implements Map.Entry<String, Object>
{
    private Object fieldValue;
    private final FieldInfo fieldInfo;
    final /* synthetic */ DataMap this$0;
    
    Entry(final DataMap this$0, final FieldInfo fieldInfo, final Object o) {
        this.this$0 = this$0;
        super();
        this.fieldInfo = fieldInfo;
        this.fieldValue = Preconditions.<Object>checkNotNull(o);
    }
    
    @Override
    public String getKey() {
        String s = this.fieldInfo.getName();
        if (this.this$0.classInfo.getIgnoreCase()) {
            s = s.toLowerCase(Locale.US);
        }
        return s;
    }
    
    @Override
    public Object getValue() {
        return this.fieldValue;
    }
    
    @Override
    public Object setValue(final Object o) {
        final Object fieldValue = this.fieldValue;
        this.fieldValue = Preconditions.<Object>checkNotNull(o);
        this.fieldInfo.setValue(this.this$0.object, o);
        return fieldValue;
    }
    
    @Override
    public int hashCode() {
        return this.getKey().hashCode() ^ this.getValue().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        final Map.Entry entry = (Map.Entry)o;
        return this.getKey().equals(entry.getKey()) && this.getValue().equals(entry.getValue());
    }
    
    @Override
    public /* bridge */ Object getKey() {
        return this.getKey();
    }
}
