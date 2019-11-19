package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.ast.ASTList;
import javassist.compiler.JvstCodeGen;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.compiler.CompileError;
import javassist.CannotCompileException;
import javassist.compiler.ProceedHandler;
import javassist.compiler.Javac;
import javassist.NotFoundException;
import javassist.CtBehavior;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.CodeIterator;

public class Instanceof extends Expr
{
    protected Instanceof(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo) {
        super(n, codeIterator, ctClass, methodInfo);
    }
    
    @Override
    public CtBehavior where() {
        return super.where();
    }
    
    @Override
    public int getLineNumber() {
        return super.getLineNumber();
    }
    
    @Override
    public String getFileName() {
        return super.getFileName();
    }
    
    public CtClass getType() throws NotFoundException {
        return this.thisClass.getClassPool().getCtClass(this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1)));
    }
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    @Override
    public void replace(final String s) throws CannotCompileException {
        this.thisClass.getClassFile();
        this.getConstPool();
        final int currentPos = this.currentPos;
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        final Javac javac = new Javac(this.thisClass);
        final ClassPool classPool = this.thisClass.getClassPool();
        final CodeAttribute value = this.iterator.get();
        try {
            final CtClass[] array = { classPool.get("java.lang.Object") };
            final CtClass booleanType = CtClass.booleanType;
            final int maxLocals = value.getMaxLocals();
            javac.recordParams("java.lang.Object", array, true, maxLocals, this.withinStatic());
            final int recordReturnType = javac.recordReturnType(booleanType, true);
            javac.recordProceed(new ProceedForInstanceof(u16bit));
            javac.recordType(this.getType());
            Expr.checkResultValue(booleanType, s);
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(array, true, maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            bytecode.addConstZero(booleanType);
            bytecode.addStore(recordReturnType, booleanType);
            javac.compileStmnt(s);
            bytecode.addLoad(recordReturnType, booleanType);
            this.replace0(currentPos, bytecode, 3);
        }
        catch (CompileError compileError) {
            throw new CannotCompileException(compileError);
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException("broken method");
        }
    }
}
