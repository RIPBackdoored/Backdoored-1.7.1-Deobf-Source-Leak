package org.reflections.scanners;

import javassist.expr.FieldAccess;
import javassist.expr.ConstructorCall;
import javassist.expr.MethodCall;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.reflections.ReflectionsException;
import javassist.expr.NewExpr;
import javassist.expr.ExprEditor;

class MemberUsageScanner$1 extends ExprEditor {
    final /* synthetic */ String val$key;
    final /* synthetic */ MemberUsageScanner this$0;
    
    MemberUsageScanner$1(final MemberUsageScanner this$0, final String val$key) {
        this.this$0 = this$0;
        this.val$key = val$key;
        super();
    }
    
    @Override
    public void edit(final NewExpr newExpr) throws CannotCompileException {
        try {
            MemberUsageScanner.access$000(this.this$0, newExpr.getConstructor().getDeclaringClass().getName() + ".<init>(" + this.this$0.parameterNames(newExpr.getConstructor().getMethodInfo()) + ")", newExpr.getLineNumber(), this.val$key);
        }
        catch (NotFoundException ex) {
            throw new ReflectionsException("Could not find new instance usage in " + this.val$key, ex);
        }
    }
    
    @Override
    public void edit(final MethodCall methodCall) throws CannotCompileException {
        try {
            MemberUsageScanner.access$000(this.this$0, methodCall.getMethod().getDeclaringClass().getName() + "." + methodCall.getMethodName() + "(" + this.this$0.parameterNames(methodCall.getMethod().getMethodInfo()) + ")", methodCall.getLineNumber(), this.val$key);
        }
        catch (NotFoundException ex) {
            throw new ReflectionsException("Could not find member " + methodCall.getClassName() + " in " + this.val$key, ex);
        }
    }
    
    @Override
    public void edit(final ConstructorCall constructorCall) throws CannotCompileException {
        try {
            MemberUsageScanner.access$000(this.this$0, constructorCall.getConstructor().getDeclaringClass().getName() + ".<init>(" + this.this$0.parameterNames(constructorCall.getConstructor().getMethodInfo()) + ")", constructorCall.getLineNumber(), this.val$key);
        }
        catch (NotFoundException ex) {
            throw new ReflectionsException("Could not find member " + constructorCall.getClassName() + " in " + this.val$key, ex);
        }
    }
    
    @Override
    public void edit(final FieldAccess fieldAccess) throws CannotCompileException {
        try {
            MemberUsageScanner.access$000(this.this$0, fieldAccess.getField().getDeclaringClass().getName() + "." + fieldAccess.getFieldName(), fieldAccess.getLineNumber(), this.val$key);
        }
        catch (NotFoundException ex) {
            throw new ReflectionsException("Could not find member " + fieldAccess.getFieldName() + " in " + this.val$key, ex);
        }
    }
}