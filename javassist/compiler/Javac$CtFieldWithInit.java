package javassist.compiler;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.compiler.ast.ASTree;
import javassist.CtField;

public static class CtFieldWithInit extends CtField
{
    private ASTree init;
    
    CtFieldWithInit(final CtClass ctClass, final String s, final CtClass ctClass2) throws CannotCompileException {
        super(ctClass, s, ctClass2);
        this.init = null;
    }
    
    protected void setInit(final ASTree init) {
        this.init = init;
    }
    
    @Override
    protected ASTree getInitAST() {
        return this.init;
    }
}
