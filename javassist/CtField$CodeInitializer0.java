package javassist;

import javassist.compiler.ast.StringL;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.ASTree;
import javassist.bytecode.ConstPool;
import javassist.bytecode.Descriptor;
import javassist.bytecode.Bytecode;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;

abstract static class CodeInitializer0 extends Initializer
{
    CodeInitializer0() {
        super();
    }
    
    abstract void compileExpr(final Javac p0) throws CompileError;
    
    @Override
    int compile(final CtClass ctClass, final String s, final Bytecode bytecode, final CtClass[] array, final Javac javac) throws CannotCompileException {
        try {
            bytecode.addAload(0);
            this.compileExpr(javac);
            bytecode.addPutfield(Bytecode.THIS, s, Descriptor.of(ctClass));
            return bytecode.getMaxStack();
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
    }
    
    @Override
    int compileIfStatic(final CtClass ctClass, final String s, final Bytecode bytecode, final Javac javac) throws CannotCompileException {
        try {
            this.compileExpr(javac);
            bytecode.addPutstatic(Bytecode.THIS, s, Descriptor.of(ctClass));
            return bytecode.getMaxStack();
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
    }
    
    int getConstantValue2(final ConstPool constPool, final CtClass ctClass, final ASTree asTree) {
        if (ctClass.isPrimitive()) {
            if (asTree instanceof IntConst) {
                final long value = ((IntConst)asTree).get();
                if (ctClass == CtClass.doubleType) {
                    return constPool.addDoubleInfo((double)value);
                }
                if (ctClass == CtClass.floatType) {
                    return constPool.addFloatInfo((float)value);
                }
                if (ctClass == CtClass.longType) {
                    return constPool.addLongInfo(value);
                }
                if (ctClass != CtClass.voidType) {
                    return constPool.addIntegerInfo((int)value);
                }
            }
            else if (asTree instanceof DoubleConst) {
                final double value2 = ((DoubleConst)asTree).get();
                if (ctClass == CtClass.floatType) {
                    return constPool.addFloatInfo((float)value2);
                }
                if (ctClass == CtClass.doubleType) {
                    return constPool.addDoubleInfo(value2);
                }
            }
        }
        else if (asTree instanceof StringL && ctClass.getName().equals("java.lang.String")) {
            return constPool.addStringInfo(((StringL)asTree).get());
        }
        return 0;
    }
}
