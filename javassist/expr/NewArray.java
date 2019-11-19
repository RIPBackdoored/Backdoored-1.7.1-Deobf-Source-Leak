package javassist.expr;

import javassist.compiler.JvstTypeChecker;
import javassist.compiler.ast.ASTList;
import javassist.compiler.JvstCodeGen;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ConstPool;
import javassist.compiler.ProceedHandler;
import javassist.compiler.Javac;
import javassist.CtPrimitiveType;
import javassist.bytecode.BadBytecode;
import javassist.compiler.CompileError;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import javassist.CtBehavior;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.CodeIterator;

public class NewArray extends Expr
{
    int opcode;
    
    protected NewArray(final int n, final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final int opcode) {
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
    
    @Override
    public CtClass[] mayThrow() {
        return super.mayThrow();
    }
    
    public CtClass getComponentType() throws NotFoundException {
        if (this.opcode == 188) {
            return this.getPrimitiveType(this.iterator.byteAt(this.currentPos + 1));
        }
        if (this.opcode == 189 || this.opcode == 197) {
            final String classInfo = this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1));
            return Descriptor.toCtClass(Descriptor.toArrayComponent(classInfo, Descriptor.arrayDimension(classInfo)), this.thisClass.getClassPool());
        }
        throw new RuntimeException("bad opcode: " + this.opcode);
    }
    
    CtClass getPrimitiveType(final int n) {
        switch (n) {
            case 4:
                return CtClass.booleanType;
            case 5:
                return CtClass.charType;
            case 6:
                return CtClass.floatType;
            case 7:
                return CtClass.doubleType;
            case 8:
                return CtClass.byteType;
            case 9:
                return CtClass.shortType;
            case 10:
                return CtClass.intType;
            case 11:
                return CtClass.longType;
            default:
                throw new RuntimeException("bad atype: " + n);
        }
    }
    
    public int getDimension() {
        if (this.opcode == 188) {
            return 1;
        }
        if (this.opcode == 189 || this.opcode == 197) {
            return Descriptor.arrayDimension(this.getConstPool().getClassInfo(this.iterator.u16bitAt(this.currentPos + 1))) + ((this.opcode == 189) ? 1 : 0);
        }
        throw new RuntimeException("bad opcode: " + this.opcode);
    }
    
    public int getCreatedDimensions() {
        if (this.opcode == 197) {
            return this.iterator.byteAt(this.currentPos + 3);
        }
        return 1;
    }
    
    @Override
    public void replace(final String s) throws CannotCompileException {
        try {
            this.replace2(s);
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
    
    private void replace2(final String s) throws CompileError, NotFoundException, BadBytecode, CannotCompileException {
        this.thisClass.getClassFile();
        final ConstPool constPool = this.getConstPool();
        final int currentPos = this.currentPos;
        int byte1 = 1;
        int n;
        String s2;
        int n2;
        if (this.opcode == 188) {
            n = this.iterator.byteAt(this.currentPos + 1);
            s2 = "[" + ((CtPrimitiveType)this.getPrimitiveType(n)).getDescriptor();
            n2 = 2;
        }
        else if (this.opcode == 189) {
            n = this.iterator.u16bitAt(currentPos + 1);
            final String classInfo = constPool.getClassInfo(n);
            if (classInfo.startsWith("[")) {
                s2 = "[" + classInfo;
            }
            else {
                s2 = "[L" + classInfo + ";";
            }
            n2 = 3;
        }
        else {
            if (this.opcode != 197) {
                throw new RuntimeException("bad opcode: " + this.opcode);
            }
            n = this.iterator.u16bitAt(this.currentPos + 1);
            s2 = constPool.getClassInfo(n);
            byte1 = this.iterator.byteAt(this.currentPos + 3);
            n2 = 4;
        }
        final CtClass ctClass = Descriptor.toCtClass(s2, this.thisClass.getClassPool());
        final Javac javac = new Javac(this.thisClass);
        final CodeAttribute value = this.iterator.get();
        final CtClass[] array = new CtClass[byte1];
        for (int i = 0; i < byte1; ++i) {
            array[i] = CtClass.intType;
        }
        final int maxLocals = value.getMaxLocals();
        javac.recordParams("java.lang.Object", array, true, maxLocals, this.withinStatic());
        Expr.checkResultValue(ctClass, s);
        final int recordReturnType = javac.recordReturnType(ctClass, true);
        javac.recordProceed(new ProceedForArray(ctClass, this.opcode, n, byte1));
        final Bytecode bytecode = javac.getBytecode();
        Expr.storeStack(array, true, maxLocals, bytecode);
        javac.recordLocalVariables(value, currentPos);
        bytecode.addOpcode(1);
        bytecode.addAstore(recordReturnType);
        javac.compileStmnt(s);
        bytecode.addAload(recordReturnType);
        this.replace0(currentPos, bytecode, n2);
    }
}
