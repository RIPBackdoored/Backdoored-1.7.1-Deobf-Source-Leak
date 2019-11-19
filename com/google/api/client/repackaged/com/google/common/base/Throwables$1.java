package com.google.api.client.repackaged.com.google.common.base;

import java.util.AbstractList;

static final class Throwables$1 extends AbstractList<StackTraceElement> {
    final /* synthetic */ Throwable val$t;
    
    Throwables$1(final Throwable val$t) {
        this.val$t = val$t;
        super();
    }
    
    @Override
    public StackTraceElement get(final int n) {
        return (StackTraceElement)Throwables.access$200(Throwables.access$000(), Throwables.access$100(), new Object[] { this.val$t, n });
    }
    
    @Override
    public int size() {
        return (int)Throwables.access$200(Throwables.access$300(), Throwables.access$100(), new Object[] { this.val$t });
    }
    
    @Override
    public /* bridge */ Object get(final int n) {
        return this.get(n);
    }
}