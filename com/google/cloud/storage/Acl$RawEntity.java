package com.google.cloud.storage;

public static final class RawEntity extends Entity
{
    private static final long serialVersionUID = 3966205614223053950L;
    
    RawEntity(final String s) {
        super(Type.UNKNOWN, s);
    }
    
    @Override
    String toPb() {
        return this.getValue();
    }
}
