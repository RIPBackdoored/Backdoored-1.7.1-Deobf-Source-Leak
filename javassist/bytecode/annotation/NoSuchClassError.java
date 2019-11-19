package javassist.bytecode.annotation;

public class NoSuchClassError extends Error
{
    private String className;
    
    public NoSuchClassError(final String className, final Error error) {
        super(error.toString(), error);
        this.className = className;
    }
    
    public String getClassName() {
        return this.className;
    }
}
