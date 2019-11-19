package javassist;

import javassist.bytecode.Bytecode;

static class LongConstParameter extends ConstParameter
{
    long param;
    
    LongConstParameter(final long param) {
        super();
        this.param = param;
    }
    
    @Override
    int compile(final Bytecode bytecode) throws CannotCompileException {
        bytecode.addLconst(this.param);
        return 2;
    }
    
    @Override
    String descriptor() {
        return "([Ljava/lang/Object;J)Ljava/lang/Object;";
    }
    
    @Override
    String constDescriptor() {
        return "([Ljava/lang/Object;J)V";
    }
}
