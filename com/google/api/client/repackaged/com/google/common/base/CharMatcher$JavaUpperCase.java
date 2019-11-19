package com.google.api.client.repackaged.com.google.common.base;

private static final class JavaUpperCase extends CharMatcher
{
    static final JavaUpperCase INSTANCE;
    
    private JavaUpperCase() {
        super();
    }
    
    @Override
    public boolean matches(final char c) {
        return Character.isUpperCase(c);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.javaUpperCase()";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
    
    static {
        INSTANCE = new JavaUpperCase();
    }
}
