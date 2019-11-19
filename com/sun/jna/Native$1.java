package com.sun.jna;

static final class Native$1 implements Callback.UncaughtExceptionHandler {
    Native$1() {
        super();
    }
    
    @Override
    public void uncaughtException(final Callback callback, final Throwable t) {
        System.err.println("JNA: Callback " + callback + " threw the following exception:");
        t.printStackTrace();
    }
}