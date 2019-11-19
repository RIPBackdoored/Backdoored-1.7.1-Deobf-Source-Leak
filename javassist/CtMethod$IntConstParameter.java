package javassist;

import javassist.bytecode.Bytecode;

static class IntConstParameter extends ConstParameter
{
    int param;
    
    IntConstParameter(final int param) {
        super();
        this.param = param;
    }
    
    @Override
    int compile(final Bytecode bytecode) throws CannotCompileException {
        bytecode.addIconst(this.param);
        return 1;
    }
    
    @Override
    String descriptor() {
        return "([Ljava/lang/Object;I)Ljava/lang/Object;";
    }
    
    @Override
    String constDescriptor() {
        return "([Ljava/lang/Object;I)V";
    }
}
