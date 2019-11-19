package com.google.api.client.repackaged.com.google.common.base;

private static final class Ascii extends NamedFastMatcher
{
    static final Ascii INSTANCE;
    
    Ascii() {
        super("CharMatcher.ascii()");
    }
    
    @Override
    public boolean matches(final char c) {
        return c <= '\u007f';
    }
    
    static {
        INSTANCE = new Ascii();
    }
}
