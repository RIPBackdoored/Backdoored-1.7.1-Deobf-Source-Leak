package com.sun.jna;

public class FromNativeContext
{
    private Class<?> type;
    
    FromNativeContext(final Class<?> type) {
        super();
        this.type = type;
    }
    
    public Class<?> getTargetType() {
        return this.type;
    }
}
