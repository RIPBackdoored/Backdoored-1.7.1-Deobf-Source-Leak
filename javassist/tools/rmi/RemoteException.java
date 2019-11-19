package javassist.tools.rmi;

public class RemoteException extends RuntimeException
{
    public RemoteException(final String s) {
        super(s);
    }
    
    public RemoteException(final Exception ex) {
        super("by " + ex.toString());
    }
}
