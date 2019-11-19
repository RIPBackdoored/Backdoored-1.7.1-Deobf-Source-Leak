package javassist.compiler;

import javassist.bytecode.Bytecode;

protected abstract static class ReturnHook
{
    ReturnHook next;
    
    protected abstract boolean doit(final Bytecode p0, final int p1);
    
    protected ReturnHook(final CodeGen codeGen) {
        super();
        this.next = codeGen.returnHooks;
        codeGen.returnHooks = this;
    }
    
    protected void remove(final CodeGen codeGen) {
        codeGen.returnHooks = this.next;
    }
}
