package javassist.tools.rmi;

import java.io.Serializable;

public class RemoteRef implements Serializable
{
    public int oid;
    public String classname;
    
    public RemoteRef(final int oid) {
        super();
        this.oid = oid;
        this.classname = null;
    }
    
    public RemoteRef(final int oid, final String classname) {
        super();
        this.oid = oid;
        this.classname = classname;
    }
}
