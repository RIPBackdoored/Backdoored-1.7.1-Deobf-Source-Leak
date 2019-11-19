package javassist.compiler;

import javassist.bytecode.Bytecode;
import java.util.ArrayList;

static class JsrHook extends ReturnHook
{
    ArrayList jsrList;
    CodeGen cgen;
    int var;
    
    JsrHook(final CodeGen cgen) {
        super(cgen);
        this.jsrList = new ArrayList();
        this.cgen = cgen;
        this.var = -1;
    }
    
    private int getVar(final int n) {
        if (this.var < 0) {
            this.var = this.cgen.getMaxLocals();
            this.cgen.incMaxLocals(n);
        }
        return this.var;
    }
    
    private void jsrJmp(final Bytecode bytecode) {
        bytecode.addOpcode(167);
        this.jsrList.add(new int[] { bytecode.currentPc(), this.var });
        bytecode.addIndex(0);
    }
    
    @Override
    protected boolean doit(final Bytecode bytecode, final int n) {
        switch (n) {
            case 177:
                this.jsrJmp(bytecode);
                break;
            case 176:
                bytecode.addAstore(this.getVar(1));
                this.jsrJmp(bytecode);
                bytecode.addAload(this.var);
                break;
            case 172:
                bytecode.addIstore(this.getVar(1));
                this.jsrJmp(bytecode);
                bytecode.addIload(this.var);
                break;
            case 173:
                bytecode.addLstore(this.getVar(2));
                this.jsrJmp(bytecode);
                bytecode.addLload(this.var);
                break;
            case 175:
                bytecode.addDstore(this.getVar(2));
                this.jsrJmp(bytecode);
                bytecode.addDload(this.var);
                break;
            case 174:
                bytecode.addFstore(this.getVar(1));
                this.jsrJmp(bytecode);
                bytecode.addFload(this.var);
                break;
            default:
                throw new RuntimeException("fatal");
        }
        return false;
    }
}
