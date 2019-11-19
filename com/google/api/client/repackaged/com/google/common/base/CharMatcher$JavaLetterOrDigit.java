package com.google.api.client.repackaged.com.google.common.base;

private static final class JavaLetterOrDigit extends CharMatcher
{
    static final JavaLetterOrDigit INSTANCE;
    
    private JavaLetterOrDigit() {
        super();
    }
    
    @Override
    public boolean matches(final char c) {
        return Character.isLetterOrDigit(c);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.javaLetterOrDigit()";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
    
    static {
        INSTANCE = new JavaLetterOrDigit();
    }
}
