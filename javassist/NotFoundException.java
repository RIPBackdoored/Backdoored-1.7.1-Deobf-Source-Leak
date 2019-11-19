package javassist;

public class NotFoundException extends Exception
{
    public NotFoundException(final String s) {
        super(s);
    }
    
    public NotFoundException(final String s, final Exception ex) {
        super(s + " because of " + ex.toString());
    }
}
