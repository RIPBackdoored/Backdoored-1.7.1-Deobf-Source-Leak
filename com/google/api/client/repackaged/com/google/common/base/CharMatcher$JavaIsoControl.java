package com.google.api.client.repackaged.com.google.common.base;

private static final class JavaIsoControl extends NamedFastMatcher
{
    static final JavaIsoControl INSTANCE;
    
    private JavaIsoControl() {
        super("CharMatcher.javaIsoControl()");
    }
    
    @Override
    public boolean matches(final char c) {
        return c <= '\u001f' || (c >= '\u007f' && c <= '\u009f');
    }
    
    static {
        INSTANCE = new JavaIsoControl();
    }
}
