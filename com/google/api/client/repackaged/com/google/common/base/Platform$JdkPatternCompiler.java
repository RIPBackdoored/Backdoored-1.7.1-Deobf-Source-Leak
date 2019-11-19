package com.google.api.client.repackaged.com.google.common.base;

import java.util.regex.Pattern;

private static final class JdkPatternCompiler implements PatternCompiler
{
    private JdkPatternCompiler() {
        super();
    }
    
    @Override
    public CommonPattern compile(final String s) {
        return new JdkPattern(Pattern.compile(s));
    }
    
    JdkPatternCompiler(final Platform$1 object) {
        this();
    }
}
