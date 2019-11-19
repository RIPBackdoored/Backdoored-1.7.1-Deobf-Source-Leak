package com.sun.jna;

private static class Entry
{
    public Class<?> type;
    public Object converter;
    
    public Entry(final Class<?> type, final Object converter) {
        super();
        this.type = type;
        this.converter = converter;
    }
}
