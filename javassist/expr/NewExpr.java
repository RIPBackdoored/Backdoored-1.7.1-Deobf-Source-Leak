package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.MemberResolver;
import javassist.compiler.ast.ASTList;
import javassist.compiler.JvstCodeGen;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import javassist.bytecode.BadBytecode;
import javassist.compiler.CompileError;
import javassist.compiler.ProceedHandler;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.CannotCompileException;
import javassist.CtConstructor;
import javassist.NotFoundException;
import javassist.CtBehavior;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.CodeIterator;

public class NewExpr extends Expr
{
    String newTypeName;
    int newPos;
    
    protected NewExpr(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final String newTypeName, final int newPos) {
        super(n, codeIterator, ctClass, methodInfo);
        this.newTypeName = newTypeName;
        this.newPos = newPos;
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
    
    private CtClass getCtClass() throws NotFoundException {
        return this.thisClass.getClassPool().get(this.newTypeName);
    }
    
    public String getClassName() {
        return this.newTypeName;
    }
    
    public String getSignature() {
        return this.getConstPool().getMethodrefType(this.iterator.u16bitAt(this.currentPos + 1));
    }
    
    public CtConstructor getConstructor() throws NotFoundException {
        return this.getCtClass().getConstructor(this.getConstPool().getMethodrefType(this.iterator.u16bitAt(this.currentPos + 1)));
    }
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    private int canReplace() throws CannotCompileException {
        final int byte1 = this.iterator.byteAt(this.newPos + 3);
        if (byte1 == 89) {
            return (this.iterator.byteAt(this.newPos + 4) == 94 && this.iterator.byteAt(this.newPos + 5) == 88) ? 6 : 4;
        }
        if (byte1 == 90 && this.iterator.byteAt(this.newPos + 4) == 95) {
            return 5;
        }
        return 3;
    }
    
    @Override
    public void replace(final String s) throws CannotCompileException {
        this.thisClass.getClassFile();
        final int newPos = this.newPos;
        final int u16bit = this.iterator.u16bitAt(newPos + 1);
        final int canReplace = this.canReplace();
        for (int n = newPos + canReplace, i = newPos; i < n; ++i) {
            this.iterator.writeByte(0, i);
        }
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        final int u16bit2 = this.iterator.u16bitAt(currentPos + 1);
        final String methodrefType = constPool.getMethodrefType(u16bit2);
        final Javac javac = new Javac(this.thisClass);
        final ClassPool classPool = this.thisClass.getClassPool();
        final CodeAttribute value = this.iterator.get();
        try {
            final CtClass[] parameterTypes = Descriptor.getParameterTypes(methodrefType, classPool);
            final CtClass value2 = classPool.get(this.newTypeName);
            final int maxLocals = value.getMaxLocals();
            javac.recordParams(this.newTypeName, parameterTypes, true, maxLocals, this.withinStatic());
            final int recordReturnType = javac.recordReturnType(value2, true);
            javac.recordProceed(new ProceedForNew(value2, u16bit, u16bit2));
            Expr.checkResultValue(value2, s);
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(parameterTypes, true, maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            bytecode.addConstZero(value2);
            bytecode.addStore(recordReturnType, value2);
            javac.compileStmnt(s);
            if (canReplace > 3) {
                bytecode.addAload(recordReturnType);
            }
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
