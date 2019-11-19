package javassist.compiler;

import javassist.bytecode.MethodInfo;
import javassist.CtClass;

public static class Method
{
    public CtClass declaring;
    public MethodInfo info;
    public int notmatch;
    
    public Method(final CtClass declaring, final MethodInfo info, final int notmatch) {
        super();
        this.declaring = declaring;
        this.info = info;
        this.notmatch = notmatch;
    }
    
    public boolean isStatic() {
        return (this.info.getAccessFlags() & 0x8) != 0x0;
    }
}
