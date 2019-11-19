package org.reflections.util;

public static class Exclude extends Matcher
{
    public Exclude(final String s) {
        super(s);
    }
    
    @Override
    public boolean apply(final String s) {
        return !this.pattern.matcher(s).matches();
    }
    
    @Override
    public String toString() {
        return "-" + super.toString();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((String)o);
    }
}
