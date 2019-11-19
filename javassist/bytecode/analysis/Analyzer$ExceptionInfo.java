package javassist.bytecode.analysis;

private static class ExceptionInfo
{
    private int end;
    private int handler;
    private int start;
    private Type type;
    
    private ExceptionInfo(final int start, final int end, final int handler, final Type type) {
        super();
        this.start = start;
        this.end = end;
        this.handler = handler;
        this.type = type;
    }
    
    ExceptionInfo(final int n, final int n2, final int n3, final Type type, final Analyzer$1 object) {
        this(n, n2, n3, type);
    }
    
    static /* synthetic */ int access$100(final ExceptionInfo exceptionInfo) {
        return exceptionInfo.start;
    }
    
    static /* synthetic */ int access$200(final ExceptionInfo exceptionInfo) {
        return exceptionInfo.end;
    }
    
    static /* synthetic */ Type access$300(final ExceptionInfo exceptionInfo) {
        return exceptionInfo.type;
    }
    
    static /* synthetic */ int access$400(final ExceptionInfo exceptionInfo) {
        return exceptionInfo.handler;
    }
}
