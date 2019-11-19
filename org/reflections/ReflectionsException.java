package org.reflections;

public class ReflectionsException extends RuntimeException
{
    public ReflectionsException(final String s) {
        super(s);
    }
    
    public ReflectionsException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public ReflectionsException(final Throwable t) {
        super(t);
    }
}
