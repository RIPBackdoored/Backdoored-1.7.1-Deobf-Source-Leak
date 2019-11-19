package com.google.api.client.repackaged.com.google.common.base;

import java.util.regex.Matcher;

private static final class JdkMatcher extends CommonMatcher
{
    final Matcher matcher;
    
    JdkMatcher(final Matcher matcher) {
        super();
        this.matcher = Preconditions.<Matcher>checkNotNull(matcher);
    }
    
    @Override
    boolean matches() {
        return this.matcher.matches();
    }
    
    @Override
    boolean find() {
        return this.matcher.find();
    }
    
    @Override
    boolean find(final int n) {
        return this.matcher.find(n);
    }
    
    @Override
    String replaceAll(final String s) {
        return this.matcher.replaceAll(s);
    }
    
    @Override
    int end() {
        return this.matcher.end();
    }
    
    @Override
    int start() {
        return this.matcher.start();
    }
}
