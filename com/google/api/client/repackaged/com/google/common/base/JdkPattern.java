package com.google.api.client.repackaged.com.google.common.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.api.client.repackaged.com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;

@GwtIncompatible
final class JdkPattern extends CommonPattern implements Serializable
{
    private final Pattern pattern;
    private static final long serialVersionUID = 0L;
    
    JdkPattern(final Pattern pattern) {
        super();
        this.pattern = Preconditions.<Pattern>checkNotNull(pattern);
    }
    
    @Override
    CommonMatcher matcher(final CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }
    
    @Override
    String pattern() {
        return this.pattern.pattern();
    }
    
    @Override
    int flags() {
        return this.pattern.flags();
    }
    
    @Override
    public String toString() {
        return this.pattern.toString();
    }
    
    @Override
    public int hashCode() {
        return this.pattern.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof JdkPattern && this.pattern.equals(((JdkPattern)o).pattern);
    }
}
