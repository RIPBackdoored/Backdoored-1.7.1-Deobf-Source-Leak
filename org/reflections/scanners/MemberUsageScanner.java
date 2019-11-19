package org.reflections.scanners;

import javassist.ClassPath;
import javassist.LoaderClassPath;
import org.reflections.util.ClasspathHelper;
import com.google.common.base.Joiner;
import javassist.bytecode.MethodInfo;
import javassist.expr.FieldAccess;
import javassist.expr.ConstructorCall;
import javassist.expr.MethodCall;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.expr.NewExpr;
import javassist.expr.ExprEditor;
import javassist.CtMethod;
import javassist.CtConstructor;
import javassist.CtClass;
import org.reflections.ReflectionsException;
import javassist.CtBehavior;
import javassist.ClassPool;

public class MemberUsageScanner extends AbstractScanner
{
    private ClassPool classPool;
    
    public MemberUsageScanner() {
        super();
    }
    
    @Override
    public void scan(final Object o) {
        try {
            final CtClass value = this.getClassPool().get(this.getMetadataAdapter().getClassName(o));
            final CtConstructor[] declaredConstructors = value.getDeclaredConstructors();
            for (int length = declaredConstructors.length, i = 0; i < length; ++i) {
                this.scanMember(declaredConstructors[i]);
            }
            final CtMethod[] declaredMethods = value.getDeclaredMethods();
            for (int length2 = declaredMethods.length, j = 0; j < length2; ++j) {
                this.scanMember(declaredMethods[j]);
            }
            value.detach();
        }
        catch (Exception ex) {
            throw new ReflectionsException("Could not scan method usage for " + this.getMetadataAdapter().getClassName(o), ex);
        }
    }
    
    void scanMember(final CtBehavior ctBehavior) throws CannotCompileException {
        ctBehavior.instrument(new ExprEditor() {
            final /* synthetic */ String val$key = ctBehavior.getDeclaringClass().getName() + "." + ctBehavior.getMethodInfo().getName() + "(" + MemberUsageScanner.this.parameterNames(ctBehavior.getMethodInfo()) + ")";
            final /* synthetic */ MemberUsageScanner this$0;
            
            MemberUsageScanner$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void edit(final NewExpr newExpr) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(newExpr.getConstructor().getDeclaringClass().getName() + ".<init>(" + this.this$0.parameterNames(newExpr.getConstructor().getMethodInfo()) + ")", newExpr.getLineNumber(), this.val$key);
                }
                catch (NotFoundException ex) {
                    throw new ReflectionsException("Could not find new instance usage in " + this.val$key, ex);
                }
            }
            
            @Override
            public void edit(final MethodCall methodCall) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(methodCall.getMethod().getDeclaringClass().getName() + "." + methodCall.getMethodName() + "(" + this.this$0.parameterNames(methodCall.getMethod().getMethodInfo()) + ")", methodCall.getLineNumber(), this.val$key);
                }
                catch (NotFoundException ex) {
                    throw new ReflectionsException("Could not find member " + methodCall.getClassName() + " in " + this.val$key, ex);
                }
            }
            
            @Override
            public void edit(final ConstructorCall constructorCall) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(constructorCall.getConstructor().getDeclaringClass().getName() + ".<init>(" + this.this$0.parameterNames(constructorCall.getConstructor().getMethodInfo()) + ")", constructorCall.getLineNumber(), this.val$key);
                }
                catch (NotFoundException ex) {
                    throw new ReflectionsException("Could not find member " + constructorCall.getClassName() + " in " + this.val$key, ex);
                }
            }
            
            @Override
            public void edit(final FieldAccess fieldAccess) throws CannotCompileException {
                try {
                    MemberUsageScanner.this.put(fieldAccess.getField().getDeclaringClass().getName() + "." + fieldAccess.getFieldName(), fieldAccess.getLineNumber(), this.val$key);
                }
                catch (NotFoundException ex) {
                    throw new ReflectionsException("Could not find member " + fieldAccess.getFieldName() + " in " + this.val$key, ex);
                }
            }
        });
    }
    
    private void put(final String s, final int n, final String s2) {
        if (this.acceptResult(s)) {
            this.getStore().put((Object)s, (Object)(s2 + " #" + n));
        }
    }
    
    String parameterNames(final MethodInfo methodInfo) {
        return Joiner.on(", ").join(this.getMetadataAdapter().getParameterNames(methodInfo));
    }
    
    private ClassPool getClassPool() {
        if (this.classPool == null) {
            synchronized (this) {
                this.classPool = new ClassPool();
                ClassLoader[] array = this.getConfiguration().getClassLoaders();
                if (array == null) {
                    array = ClasspathHelper.classLoaders(new ClassLoader[0]);
                }
                final ClassLoader[] array2 = array;
                for (int length = array2.length, i = 0; i < length; ++i) {
                    this.classPool.appendClassPath(new LoaderClassPath(array2[i]));
                }
            }
        }
        return this.classPool;
    }
    
    static /* synthetic */ void access$000(final MemberUsageScanner memberUsageScanner, final String s, final int n, final String s2) {
        memberUsageScanner.put(s, n, s2);
    }
}
