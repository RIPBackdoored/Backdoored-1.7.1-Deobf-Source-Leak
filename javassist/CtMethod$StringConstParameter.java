package javassist;

import javassist.bytecode.Bytecode;

static class StringConstParameter extends ConstParameter
{
    String param;
    
    StringConstParameter(final String param) {
        super();
        this.param = param;
    }
    
    @Override
    int compile(final Bytecode bytecode) throws CannotCompileException {
        bytecode.addLdc(this.param);
        return 1;
    }
    
    @Override
    String descriptor() {
        return "([Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;";
    }
    
    @Override
    String constDescriptor() {
        return "([Ljava/lang/Object;Ljava/lang/String;)V";
    }
}
