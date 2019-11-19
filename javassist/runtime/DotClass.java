package javassist.runtime;

public class DotClass
{
    public DotClass() {
        super();
    }
    
    public static NoClassDefFoundError fail(final ClassNotFoundException ex) {
        return new NoClassDefFoundError(ex.getMessage());
    }
}
