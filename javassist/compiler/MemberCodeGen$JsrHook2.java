package javassist.compiler;

import javassist.bytecode.Bytecode;

static class JsrHook2 extends ReturnHook
{
    int var;
    int target;
    
    JsrHook2(final CodeGen codeGen, final int[] array) {
        super(codeGen);
        this.target = array[0];
        this.var = array[1];
    }
    
    @Override
    protected boolean doit(final Bytecode bytecode, final int n) {
        switch (n) {
            case 177:
                break;
            case 176:
                bytecode.addAstore(this.var);
                break;
            case 172:
                bytecode.addIstore(this.var);
                break;
            case 173:
                bytecode.addLstore(this.var);
                break;
            case 175:
                bytecode.addDstore(this.var);
                break;
            case 174:
                bytecode.addFstore(this.var);
                break;
            default:
                throw new RuntimeException("fatal");
        }
        bytecode.addOpcode(167);
        bytecode.addIndex(this.target - bytecode.currentPc() + 3);
        return true;
    }
}
