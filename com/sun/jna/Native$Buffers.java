package com.sun.jna;

import java.nio.Buffer;

private static class Buffers
{
    private Buffers() {
        super();
    }
    
    static boolean isBuffer(final Class<?> clazz) {
        return Buffer.class.isAssignableFrom(clazz);
    }
}
