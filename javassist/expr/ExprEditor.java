package javassist.expr;

import javassist.bytecode.ExceptionTable;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.CannotCompileException;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;

public class ExprEditor
{
    public ExprEditor() {
        super();
    }
    
    public boolean doit(final CtClass ctClass, final MethodInfo methodInfo) throws CannotCompileException {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return false;
        }
        final CodeIterator iterator = codeAttribute.iterator();
        boolean b = false;
        final LoopContext loopContext = new LoopContext(codeAttribute.getMaxLocals());
        while (iterator.hasNext()) {
            if (this.loopBody(iterator, ctClass, methodInfo, loopContext)) {
                b = true;
            }
        }
        final ExceptionTable exceptionTable = codeAttribute.getExceptionTable();
        for (int size = exceptionTable.size(), i = 0; i < size; ++i) {
            final Handler handler = new Handler(exceptionTable, i, iterator, ctClass, methodInfo);
            this.edit(handler);
            if (handler.edited()) {
                b = true;
                loopContext.updateMax(handler.locals(), handler.stack());
            }
        }
        if (codeAttribute.getMaxLocals() < loopContext.maxLocals) {
            codeAttribute.setMaxLocals(loopContext.maxLocals);
        }
        codeAttribute.setMaxStack(codeAttribute.getMaxStack() + loopContext.maxStack);
        try {
            if (b) {
                methodInfo.rebuildStackMapIf6(ctClass.getClassPool(), ctClass.getClassFile2());
            }
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode.getMessage(), badBytecode);
        }
        return b;
    }
    
    boolean doit(final CtClass ctClass, final MethodInfo methodInfo, final LoopContext loopContext, final CodeIterator codeIterator, int n) throws CannotCompileException {
        boolean b = false;
        while (codeIterator.hasNext() && codeIterator.lookAhead() < n) {
            final int codeLength = codeIterator.getCodeLength();
            if (this.loopBody(codeIterator, ctClass, methodInfo, loopContext)) {
                b = true;
                final int codeLength2 = codeIterator.getCodeLength();
                if (codeLength == codeLength2) {
                    continue;
                }
                n += codeLength2 - codeLength;
            }
        }
        return b;
    }
    
    final boolean loopBody(final CodeIterator codeIterator, final CtClass ctClass, final MethodInfo methodInfo, final LoopContext loopContext) throws CannotCompileException {
        try {
            Expr expr = null;
            final int next = codeIterator.next();
            final int byte1 = codeIterator.byteAt(next);
            if (byte1 >= 178) {
                if (byte1 < 188) {
                    if (byte1 == 184 || byte1 == 185 || byte1 == 182) {
                        expr = new MethodCall(next, codeIterator, ctClass, methodInfo);
                        this.edit((MethodCall)expr);
                    }
                    else if (byte1 == 180 || byte1 == 178 || byte1 == 181 || byte1 == 179) {
                        expr = new FieldAccess(next, codeIterator, ctClass, methodInfo, byte1);
                        this.edit((FieldAccess)expr);
                    }
                    else if (byte1 == 187) {
                        loopContext.newList = new NewOp(loopContext.newList, next, methodInfo.getConstPool().getClassInfo(codeIterator.u16bitAt(next + 1)));
                    }
                    else if (byte1 == 183) {
                        final NewOp newList = loopContext.newList;
                        if (newList != null && methodInfo.getConstPool().isConstructor(newList.type, codeIterator.u16bitAt(next + 1)) > 0) {
                            expr = new NewExpr(next, codeIterator, ctClass, methodInfo, newList.type, newList.pos);
                            this.edit((NewExpr)expr);
                            loopContext.newList = newList.next;
                        }
                        else {
                            final MethodCall methodCall = new MethodCall(next, codeIterator, ctClass, methodInfo);
                            if (methodCall.getMethodName().equals("<init>")) {
                                this.edit((ConstructorCall)(expr = new ConstructorCall(next, codeIterator, ctClass, methodInfo)));
                            }
                            else {
                                expr = methodCall;
                                this.edit(methodCall);
                            }
                        }
                    }
                }
                else if (byte1 == 188 || byte1 == 189 || byte1 == 197) {
                    expr = new NewArray(next, codeIterator, ctClass, methodInfo, byte1);
                    this.edit((NewArray)expr);
                }
                else if (byte1 == 193) {
                    expr = new Instanceof(next, codeIterator, ctClass, methodInfo);
                    this.edit((Instanceof)expr);
                }
                else if (byte1 == 192) {
                    expr = new Cast(next, codeIterator, ctClass, methodInfo);
                    this.edit((Cast)expr);
                }
            }
            if (expr != null && expr.edited()) {
                loopContext.updateMax(expr.locals(), expr.stack());
                return true;
            }
            return false;
        }
        catch (BadBytecode badBytecode) {
            throw new CannotCompileException(badBytecode);
        }
    }
    
    public void edit(final NewExpr newExpr) throws CannotCompileException {
    }
    
    public void edit(final NewArray newArray) throws CannotCompileException {
    }
    
    public void edit(final MethodCall methodCall) throws CannotCompileException {
    }
    
    public void edit(final ConstructorCall constructorCall) throws CannotCompileException {
    }
    
    public void edit(final FieldAccess fieldAccess) throws CannotCompileException {
    }
    
    public void edit(final Instanceof instanceof1) throws CannotCompileException {
    }
    
    public void edit(final Cast cast) throws CannotCompileException {
    }
    
    public void edit(final Handler handler) throws CannotCompileException {
    }
}
