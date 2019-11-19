package org.reflections.util;

import java.util.regex.Pattern;
import com.google.common.base.Predicate;

public abstract static class Matcher implements Predicate<String>
{
    final Pattern pattern;
    
    public Matcher(final String s) {
        super();
        this.pattern = Pattern.compile(s);
    }
    
    @Override
    public abstract boolean apply(final String p0);
    
    @Override
    public String toString() {
        return this.pattern.pattern();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((String)o);
    }
}
