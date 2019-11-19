package javassist.util.proxy;

import java.security.PrivilegedExceptionAction;

class SerializedProxy$1 implements PrivilegedExceptionAction {
    final /* synthetic */ String val$className;
    final /* synthetic */ SerializedProxy this$0;
    
    SerializedProxy$1(final SerializedProxy this$0, final String val$className) {
        this.this$0 = this$0;
        this.val$className = val$className;
        super();
    }
    
    @Override
    public Object run() throws Exception {
        return Class.forName(this.val$className, true, Thread.currentThread().getContextClassLoader());
    }
}