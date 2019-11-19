package javassist.tools.reflect;

public class CannotCreateException extends Exception
{
    public CannotCreateException(final String s) {
        super(s);
    }
    
    public CannotCreateException(final Exception ex) {
        super("by " + ex.toString());
    }
}
