package com.google.api.client.util;

import java.util.Comparator;

class ClassInfo$1 implements Comparator<String> {
    final /* synthetic */ ClassInfo this$0;
    
    ClassInfo$1(final ClassInfo this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public int compare(final String s, final String s2) {
        return Objects.equal(s, s2) ? 0 : ((s == null) ? -1 : ((s2 == null) ? 1 : s.compareTo(s2)));
    }
    
    @Override
    public /* bridge */ int compare(final Object o, final Object o2) {
        return this.compare((String)o, (String)o2);
    }
}