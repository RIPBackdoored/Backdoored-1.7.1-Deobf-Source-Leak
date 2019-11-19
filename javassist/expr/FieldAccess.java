package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.CtPrimitiveType;
import javassist.compiler.ast.ASTList;
import javassist.compiler.JvstCodeGen;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.compiler.CompileError;
import javassist.CannotCompileException;
import javassist.compiler.ProceedHandler;
import javassist.bytecode.Descriptor;
import javassist.compiler.Javac;
import javassist.bytecode.ConstPool;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.CtBehavior;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.CodeIterator;

public class FieldAccess extends Expr
{
    int opcode;
    
    protected FieldAccess(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final int opcode) {
        super(n, codeIterator, ctClass, methodInfo);
        this.opcode = opcode;
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
    
    public boolean isStatic() {
        return isStatic(this.opcode);
    }
    
    static boolean isStatic(final int n) {
        return n == 178 || n == 179;
    }
    
    public boolean isReader() {
        return this.opcode == 180 || this.opcode == 178;
    }
    
    public boolean isWriter() {
        return this.opcode == 181 || this.opcode == 179;
    }
    
    private CtClass getCtClass() throws NotFoundException {
        return this.thisClass.getClassPool().get(this.getClassName());
    }
    
    public String getClassName() {
        return this.getConstPool().getFieldrefClassName(this.iterator.u16bitAt(this.currentPos + 1));
    }
    
    public String getFieldName() {
        return this.getConstPool().getFieldrefName(this.iterator.u16bitAt(this.currentPos + 1));
    }
    
    public CtField getField() throws NotFoundException {
        final CtClass ctClass = this.getCtClass();
        final int u16bit = this.iterator.u16bitAt(this.currentPos + 1);
        final ConstPool constPool = this.getConstPool();
        return ctClass.getField(constPool.getFieldrefName(u16bit), constPool.getFieldrefType(u16bit));
    }
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    public String getSignature() {
        return this.getConstPool().getFieldrefType(this.iterator.u16bitAt(this.currentPos + 1));
    }
    
    @Override
    public void replace(final String s) throws CannotCompileException {
        this.thisClass.getClassFile();
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        final int u16bit = this.iterator.u16bitAt(currentPos + 1);
        final Javac javac = new Javac(this.thisClass);
        final CodeAttribute value = this.iterator.get();
        try {
            final CtClass ctClass = Descriptor.toCtClass(constPool.getFieldrefType(u16bit), this.thisClass.getClassPool());
            final boolean reader = this.isReader();
            CtClass[] array;
            CtClass voidType;
            if (reader) {
                array = new CtClass[0];
                voidType = ctClass;
            }
            else {
                array = new CtClass[] { ctClass };
                voidType = CtClass.voidType;
            }
            final int maxLocals = value.getMaxLocals();
            javac.recordParams(constPool.getFieldrefClassName(u16bit), array, true, maxLocals, this.withinStatic());
            boolean checkResultValue = Expr.checkResultValue(voidType, s);
            if (reader) {
                checkResultValue = true;
            }
            final int recordReturnType = javac.recordReturnType(voidType, checkResultValue);
            if (reader) {
                javac.recordProceed(new ProceedForRead(voidType, this.opcode, u16bit, maxLocals));
            }
            else {
                javac.recordType(ctClass);
                javac.recordProceed(new ProceedForWrite(array[0], this.opcode, u16bit, maxLocals));
            }
            final Bytecode bytecode = javac.getBytecode();
            Expr.storeStack(array, this.isStatic(), maxLocals, bytecode);
            javac.recordLocalVariables(value, currentPos);
            if (checkResultValue) {
                if (voidType == CtClass.voidType) {
                    bytecode.addOpcode(1);
                    bytecode.addAstore(recordReturnType);
                }
                else {
                    bytecode.addConstZero(voidType);
                    bytecode.addStore(recordReturnType, voidType);
                }
            }
            javac.compileStmnt(s);
            if (reader) {
                bytecode.addLoad(recordReturnType, voidType);
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
