package javassist.util.proxy;

static class Find2MethodsArgs
{
    String methodName;
    String delegatorName;
    String descriptor;
    int origIndex;
    
    Find2MethodsArgs(final String methodName, final String delegatorName, final String descriptor, final int origIndex) {
        super();
        this.methodName = methodName;
        this.delegatorName = delegatorName;
        this.descriptor = descriptor;
        this.origIndex = origIndex;
    }
}
