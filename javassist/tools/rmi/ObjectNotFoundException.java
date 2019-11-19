package javassist.tools.rmi;

public class ObjectNotFoundException extends Exception
{
    public ObjectNotFoundException(final String s) {
        super(s + " is not exported");
    }
    
    public ObjectNotFoundException(final String s, final Exception ex) {
        super(s + " because of " + ex.toString());
    }
}
