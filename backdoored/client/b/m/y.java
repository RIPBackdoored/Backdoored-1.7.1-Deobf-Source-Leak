package r.k.b.m;

public class y extends Error
{
    public static final int u;
    public static final boolean z;
    
    public y() {
        this("");
    }
    
    public y(final String s) {
        super(s);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public String toString() {
        return "Go away john";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
