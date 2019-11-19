package com.google.api.client.repackaged.com.google.common.base;

private static final class JavaLowerCase extends CharMatcher
{
    static final JavaLowerCase INSTANCE;
    
    private JavaLowerCase() {
        super();
    }
    
    @Override
    public boolean matches(final char c) {
        return Character.isLowerCase(c);
    }
    
    @Override
    public String toString() {
        return "CharMatcher.javaLowerCase()";
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return super.apply((Character)o);
    }
    
    static {
        INSTANCE = new JavaLowerCase();
    }
}
