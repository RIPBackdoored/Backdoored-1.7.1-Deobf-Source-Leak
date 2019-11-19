package javassist.compiler;

import javassist.compiler.ast.MethodDecl;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.Modifier;
import javassist.bytecode.Descriptor;
import javassist.bytecode.AccessFlag;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Keyword;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.ArrayInit;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Pair;
import javassist.compiler.ast.Visitor;
import java.util.ArrayList;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.Stmnt;
import javassist.CtMethod;
import javassist.bytecode.ClassFile;
import javassist.ClassPool;
import javassist.bytecode.Bytecode;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;

public class MemberCodeGen extends CodeGen
{
    protected MemberResolver resolver;
    protected CtClass thisClass;
    protected MethodInfo thisMethod;
    protected boolean resultStatic;
    
    public MemberCodeGen(final Bytecode bytecode, final CtClass thisClass, final ClassPool classPool) {
        super(bytecode);
        this.resolver = new MemberResolver(classPool);
        this.thisClass = thisClass;
        this.thisMethod = null;
    }
    
    public int getMajorVersion() {
        final ClassFile classFile2 = this.thisClass.getClassFile2();
        if (classFile2 == null) {
            return ClassFile.MAJOR_VERSION;
        }
        return classFile2.getMajorVersion();
    }
    
    public void setThisMethod(final CtMethod ctMethod) {
        this.thisMethod = ctMethod.getMethodInfo2();
        if (this.typeChecker != null) {
            this.typeChecker.setThisMethod(this.thisMethod);
        }
    }
    
    public CtClass getThisClass() {
        return this.thisClass;
    }
    
    @Override
    protected String getThisName() {
        return MemberResolver.javaToJvmName(this.thisClass.getName());
    }
    
    @Override
    protected String getSuperName() throws CompileError {
        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(this.thisClass).getName());
    }
    
    @Override
    protected void insertDefaultSuperCall() throws CompileError {
        this.bytecode.addAload(0);
        this.bytecode.addInvokespecial(MemberResolver.getSuperclass(this.thisClass), "<init>", "()V");
    }
    
    @Override
    protected void atTryStmnt(final Stmnt stmnt) throws CompileError {
        final Bytecode bytecode = this.bytecode;
        final Stmnt stmnt2 = (Stmnt)stmnt.getLeft();
        if (stmnt2 == null) {
            return;
        }
        ASTList tail = (ASTList)stmnt.getRight().getLeft();
        final Stmnt stmnt3 = (Stmnt)stmnt.getRight().getRight().getLeft();
        final ArrayList<Integer> list = new ArrayList<Integer>();
        JsrHook jsrHook = null;
        if (stmnt3 != null) {
            jsrHook = new JsrHook(this);
        }
        final int currentPc = bytecode.currentPc();
        stmnt2.accept(this);
        final int currentPc2 = bytecode.currentPc();
        if (currentPc == currentPc2) {
            throw new CompileError("empty try block");
        }
        int n = this.hasReturned ? 0 : 1;
        if (n != 0) {
            bytecode.addOpcode(167);
            list.add(new Integer(bytecode.currentPc()));
            bytecode.addIndex(0);
        }
        final int maxLocals = this.getMaxLocals();
        this.incMaxLocals(1);
        while (tail != null) {
            final Pair pair = (Pair)tail.head();
            tail = tail.tail();
            final Declarator declarator = (Declarator)pair.getLeft();
            final Stmnt stmnt4 = (Stmnt)pair.getRight();
            declarator.setLocalVar(maxLocals);
            final CtClass lookupClassByJvmName = this.resolver.lookupClassByJvmName(declarator.getClassName());
            declarator.setClassName(MemberResolver.javaToJvmName(lookupClassByJvmName.getName()));
            bytecode.addExceptionHandler(currentPc, currentPc2, bytecode.currentPc(), lookupClassByJvmName);
            bytecode.growStack(1);
            bytecode.addAstore(maxLocals);
            this.hasReturned = false;
            if (stmnt4 != null) {
                stmnt4.accept(this);
            }
            if (!this.hasReturned) {
                bytecode.addOpcode(167);
                list.add(new Integer(bytecode.currentPc()));
                bytecode.addIndex(0);
                n = 1;
            }
        }
        if (stmnt3 != null) {
            jsrHook.remove(this);
            final int currentPc3 = bytecode.currentPc();
            bytecode.addExceptionHandler(currentPc, currentPc3, currentPc3, 0);
            bytecode.growStack(1);
            bytecode.addAstore(maxLocals);
            this.hasReturned = false;
            stmnt3.accept(this);
            if (!this.hasReturned) {
                bytecode.addAload(maxLocals);
                bytecode.addOpcode(191);
            }
            this.addFinally(jsrHook.jsrList, stmnt3);
        }
        this.patchGoto(list, bytecode.currentPc());
        this.hasReturned = true;
        if (stmnt3 != null && n != 0) {
            stmnt3.accept(this);
        }
    }
    
    private void addFinally(final ArrayList list, final Stmnt stmnt) throws CompileError {
        final Bytecode bytecode = this.bytecode;
        for (int size = list.size(), i = 0; i < size; ++i) {
            final int[] array = list.get(i);
            final int n = array[0];
            bytecode.write16bit(n, bytecode.currentPc() - n + 1);
            final JsrHook2 jsrHook2 = new JsrHook2(this, array);
            stmnt.accept(this);
            jsrHook2.remove(this);
            if (!this.hasReturned) {
                bytecode.addOpcode(167);
                bytecode.addIndex(n + 3 - bytecode.currentPc());
            }
        }
    }
    
    @Override
    public void atNewExpr(final NewExpr newExpr) throws CompileError {
        if (newExpr.isArray()) {
            this.atNewArrayExpr(newExpr);
        }
        else {
            final CtClass lookupClassByName = this.resolver.lookupClassByName(newExpr.getClassName());
            final String name = lookupClassByName.getName();
            final ASTList arguments = newExpr.getArguments();
            this.bytecode.addNew(name);
            this.bytecode.addOpcode(89);
            this.atMethodCallCore(lookupClassByName, "<init>", arguments, false, true, -1, null);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = MemberResolver.javaToJvmName(name);
        }
    }
    
    public void atNewArrayExpr(final NewExpr newExpr) throws CompileError {
        final int arrayType = newExpr.getArrayType();
        final ASTList arraySize = newExpr.getArraySize();
        final ASTList className = newExpr.getClassName();
        final ArrayInit initializer = newExpr.getInitializer();
        if (arraySize.length() <= 1) {
            this.atNewArrayExpr2(arrayType, arraySize.head(), Declarator.astToClassName(className, '/'), initializer);
            return;
        }
        if (initializer != null) {
            throw new CompileError("sorry, multi-dimensional array initializer for new is not supported");
        }
        this.atMultiNewArray(arrayType, className, arraySize);
    }
    
    private void atNewArrayExpr2(final int exprType, final ASTree asTree, final String s, final ArrayInit arrayInit) throws CompileError {
        if (arrayInit == null) {
            if (asTree == null) {
                throw new CompileError("no array size");
            }
            asTree.accept(this);
        }
        else {
            if (asTree != null) {
                throw new CompileError("unnecessary array size specified for new");
            }
            this.bytecode.addIconst(arrayInit.length());
        }
        String resolveClassName;
        if (exprType == 307) {
            resolveClassName = this.resolveClassName(s);
            this.bytecode.addAnewarray(MemberResolver.jvmToJavaName(resolveClassName));
        }
        else {
            resolveClassName = null;
            int n = 0;
            switch (exprType) {
                case 301:
                    n = 4;
                    break;
                case 306:
                    n = 5;
                    break;
                case 317:
                    n = 6;
                    break;
                case 312:
                    n = 7;
                    break;
                case 303:
                    n = 8;
                    break;
                case 334:
                    n = 9;
                    break;
                case 324:
                    n = 10;
                    break;
                case 326:
                    n = 11;
                    break;
                default:
                    badNewExpr();
                    break;
            }
            this.bytecode.addOpcode(188);
            this.bytecode.add(n);
        }
        if (arrayInit != null) {
            final int length = arrayInit.length();
            ASTList tail = arrayInit;
            for (int i = 0; i < length; ++i) {
                this.bytecode.addOpcode(89);
                this.bytecode.addIconst(i);
                tail.head().accept(this);
                if (!CodeGen.isRefType(exprType)) {
                    this.atNumCastExpr(this.exprType, exprType);
                }
                this.bytecode.addOpcode(CodeGen.getArrayWriteOp(exprType, 0));
                tail = tail.tail();
            }
        }
        this.exprType = exprType;
        this.arrayDim = 1;
        this.className = resolveClassName;
    }
    
    private static void badNewExpr() throws CompileError {
        throw new CompileError("bad new expression");
    }
    
    @Override
    protected void atArrayVariableAssign(final ArrayInit arrayInit, final int n, final int n2, final String s) throws CompileError {
        this.atNewArrayExpr2(n, null, s, arrayInit);
    }
    
    @Override
    public void atArrayInit(final ArrayInit arrayInit) throws CompileError {
        throw new CompileError("array initializer is not supported");
    }
    
    protected void atMultiNewArray(final int exprType, final ASTList list, ASTList tail) throws CompileError {
        final int length = tail.length();
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head == null) {
                break;
            }
            ++n;
            head.accept(this);
            if (this.exprType != 324) {
                throw new CompileError("bad type for array size");
            }
            tail = tail.tail();
        }
        this.exprType = exprType;
        this.arrayDim = length;
        String s;
        if (exprType == 307) {
            this.className = this.resolveClassName(list);
            s = CodeGen.toJvmArrayName(this.className, length);
        }
        else {
            s = CodeGen.toJvmTypeName(exprType, length);
        }
        this.bytecode.addMultiNewarray(s, n);
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) throws CompileError {
        String s = null;
        CtClass ctClass = null;
        final ASTree oprand1 = callExpr.oprand1();
        final ASTList list = (ASTList)callExpr.oprand2();
        boolean b = false;
        boolean b2 = false;
        int n = -1;
        final MemberResolver.Method method = callExpr.getMethod();
        if (oprand1 instanceof Member) {
            s = ((Member)oprand1).get();
            ctClass = this.thisClass;
            if (this.inStaticMethod || (method != null && method.isStatic())) {
                b = true;
            }
            else {
                n = this.bytecode.currentPc();
                this.bytecode.addAload(0);
            }
        }
        else if (oprand1 instanceof Keyword) {
            b2 = true;
            s = "<init>";
            ctClass = this.thisClass;
            if (this.inStaticMethod) {
                throw new CompileError("a constructor cannot be static");
            }
            this.bytecode.addAload(0);
            if (((Keyword)oprand1).get() == 336) {
                ctClass = MemberResolver.getSuperclass(ctClass);
            }
        }
        else if (oprand1 instanceof Expr) {
            final Expr expr = (Expr)oprand1;
            s = ((Symbol)expr.oprand2()).get();
            final int operator = expr.getOperator();
            if (operator == 35) {
                ctClass = this.resolver.lookupClass(((Symbol)expr.oprand1()).get(), false);
                b = true;
            }
            else if (operator == 46) {
                final ASTree oprand2 = expr.oprand1();
                final String dotSuper = TypeChecker.isDotSuper(oprand2);
                if (dotSuper != null) {
                    b2 = true;
                    ctClass = MemberResolver.getSuperInterface(this.thisClass, dotSuper);
                    if (this.inStaticMethod || (method != null && method.isStatic())) {
                        b = true;
                    }
                    else {
                        n = this.bytecode.currentPc();
                        this.bytecode.addAload(0);
                    }
                }
                else {
                    if (oprand2 instanceof Keyword && ((Keyword)oprand2).get() == 336) {
                        b2 = true;
                    }
                    try {
                        oprand2.accept(this);
                    }
                    catch (NoFieldException ex) {
                        if (ex.getExpr() != oprand2) {
                            throw ex;
                        }
                        this.exprType = 307;
                        this.arrayDim = 0;
                        this.className = ex.getField();
                        b = true;
                    }
                    if (this.arrayDim > 0) {
                        ctClass = this.resolver.lookupClass("java.lang.Object", true);
                    }
                    else if (this.exprType == 307) {
                        ctClass = this.resolver.lookupClassByJvmName(this.className);
                    }
                    else {
                        badMethod();
                    }
                }
            }
            else {
                badMethod();
            }
        }
        else {
            fatal();
        }
        this.atMethodCallCore(ctClass, s, list, b, b2, n, method);
    }
    
    private static void badMethod() throws CompileError {
        throw new CompileError("bad method");
    }
    
    public void atMethodCallCore(final CtClass ctClass, final String s, final ASTList list, boolean b, final boolean b2, final int n, MemberResolver.Method lookupMethod) throws CompileError {
        final int methodArgsLength = this.getMethodArgsLength(list);
        final int[] array = new int[methodArgsLength];
        final int[] array2 = new int[methodArgsLength];
        final String[] array3 = new String[methodArgsLength];
        if (lookupMethod != null && lookupMethod.isStatic()) {
            this.bytecode.addOpcode(87);
            b = true;
        }
        this.bytecode.getStackDepth();
        this.atMethodArgs(list, array, array2, array3);
        if (lookupMethod == null) {
            lookupMethod = this.resolver.lookupMethod(ctClass, this.thisClass, this.thisMethod, s, array, array2, array3);
        }
        if (lookupMethod == null) {
            String string;
            if (s.equals("<init>")) {
                string = "constructor not found";
            }
            else {
                string = "Method " + s + " not found in " + ctClass.getName();
            }
            throw new CompileError(string);
        }
        this.atMethodCallCore2(ctClass, s, b, b2, n, lookupMethod);
    }
    
    private void atMethodCallCore2(final CtClass ctClass, String accessiblePrivate, boolean b, boolean b2, final int n, final MemberResolver.Method method) throws CompileError {
        CtClass declaring = method.declaring;
        final MethodInfo info = method.info;
        String s = info.getDescriptor();
        int accessFlags = info.getAccessFlags();
        if (accessiblePrivate.equals("<init>")) {
            b2 = true;
            if (declaring != ctClass) {
                throw new CompileError("no such constructor: " + ctClass.getName());
            }
            if (declaring != this.thisClass && AccessFlag.isPrivate(accessFlags)) {
                s = this.getAccessibleConstructor(s, declaring, info);
                this.bytecode.addOpcode(1);
            }
        }
        else if (AccessFlag.isPrivate(accessFlags)) {
            if (declaring == this.thisClass) {
                b2 = true;
            }
            else {
                b2 = false;
                b = true;
                final String s2 = s;
                if ((accessFlags & 0x8) == 0x0) {
                    s = Descriptor.insertParameter(declaring.getName(), s2);
                }
                accessFlags = (AccessFlag.setPackage(accessFlags) | 0x8);
                accessiblePrivate = this.getAccessiblePrivate(accessiblePrivate, s2, s, info, declaring);
            }
        }
        boolean b3 = false;
        if ((accessFlags & 0x8) != 0x0) {
            b = true;
            if (n >= 0) {
                this.bytecode.write(n, 0);
            }
            else {
                b3 = true;
            }
            this.bytecode.addInvokestatic(declaring, accessiblePrivate, s);
        }
        else if (b2) {
            this.bytecode.addInvokespecial(ctClass, accessiblePrivate, s);
        }
        else {
            if (!Modifier.isPublic(declaring.getModifiers()) || declaring.isInterface() != ctClass.isInterface()) {
                declaring = ctClass;
            }
            if (declaring.isInterface()) {
                this.bytecode.addInvokeinterface(declaring, accessiblePrivate, s, Descriptor.paramSize(s) + 1);
            }
            else {
                if (b) {
                    throw new CompileError(accessiblePrivate + " is not static");
                }
                this.bytecode.addInvokevirtual(declaring, accessiblePrivate, s);
            }
        }
        this.setReturnType(s, b, b3);
    }
    
    protected String getAccessiblePrivate(final String s, final String s2, final String s3, final MethodInfo methodInfo, final CtClass ctClass) throws CompileError {
        if (this.isEnclosing(ctClass, this.thisClass)) {
            final AccessorMaker accessorMaker = ctClass.getAccessorMaker();
            if (accessorMaker != null) {
                return accessorMaker.getMethodAccessor(s, s2, s3, methodInfo);
            }
        }
        throw new CompileError("Method " + s + " is private");
    }
    
    protected String getAccessibleConstructor(final String s, final CtClass ctClass, final MethodInfo methodInfo) throws CompileError {
        if (this.isEnclosing(ctClass, this.thisClass)) {
            final AccessorMaker accessorMaker = ctClass.getAccessorMaker();
            if (accessorMaker != null) {
                return accessorMaker.getConstructor(ctClass, s, methodInfo);
            }
        }
        throw new CompileError("the called constructor is private in " + ctClass.getName());
    }
    
    private boolean isEnclosing(final CtClass ctClass, CtClass declaringClass) {
        try {
            while (declaringClass != null) {
                declaringClass = declaringClass.getDeclaringClass();
                if (declaringClass == ctClass) {
                    return true;
                }
            }
        }
        catch (NotFoundException ex) {}
        return false;
    }
    
    public int getMethodArgsLength(final ASTList list) {
        return ASTList.length(list);
    }
    
    public void atMethodArgs(ASTList tail, final int[] array, final int[] array2, final String[] array3) throws CompileError {
        int n = 0;
        while (tail != null) {
            tail.head().accept(this);
            array[n] = this.exprType;
            array2[n] = this.arrayDim;
            array3[n] = this.className;
            ++n;
            tail = tail.tail();
        }
    }
    
    void setReturnType(final String s, final boolean b, final boolean b2) throws CompileError {
        int index = s.indexOf(41);
        if (index < 0) {
            badMethod();
        }
        char c = s.charAt(++index);
        int arrayDim = 0;
        while (c == '[') {
            ++arrayDim;
            c = s.charAt(++index);
        }
        this.arrayDim = arrayDim;
        if (c == 'L') {
            final int index2 = s.indexOf(59, index + 1);
            if (index2 < 0) {
                badMethod();
            }
            this.exprType = 307;
            this.className = s.substring(index + 1, index2);
        }
        else {
            this.exprType = MemberResolver.descToType(c);
            this.className = null;
        }
        final int exprType = this.exprType;
        if (b && b2) {
            if (CodeGen.is2word(exprType, arrayDim)) {
                this.bytecode.addOpcode(93);
                this.bytecode.addOpcode(88);
                this.bytecode.addOpcode(87);
            }
            else if (exprType == 344) {
                this.bytecode.addOpcode(87);
            }
            else {
                this.bytecode.addOpcode(95);
                this.bytecode.addOpcode(87);
            }
        }
    }
    
    @Override
    protected void atFieldAssign(final Expr expr, final int n, final ASTree asTree, final ASTree asTree2, final boolean b) throws CompileError {
        final CtField fieldAccess = this.fieldAccess(asTree, false);
        final boolean resultStatic = this.resultStatic;
        if (n != 61) {
            this.bytecode.addOpcode(89);
        }
        int n2;
        if (n == 61) {
            final FieldInfo fieldInfo2 = fieldAccess.getFieldInfo2();
            this.setFieldType(fieldInfo2);
            if (this.isAccessibleField(fieldAccess, fieldInfo2) == null) {
                n2 = this.addFieldrefInfo(fieldAccess, fieldInfo2);
            }
            else {
                n2 = 0;
            }
        }
        else {
            n2 = this.atFieldRead(fieldAccess, resultStatic);
        }
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        this.atAssignCore(expr, n, asTree2, exprType, arrayDim, className);
        final boolean is2word = CodeGen.is2word(exprType, arrayDim);
        if (b) {
            int n3;
            if (resultStatic) {
                n3 = (is2word ? 92 : 89);
            }
            else {
                n3 = (is2word ? 93 : 90);
            }
            this.bytecode.addOpcode(n3);
        }
        this.atFieldAssignCore(fieldAccess, resultStatic, n2, is2word);
        this.exprType = exprType;
        this.arrayDim = arrayDim;
        this.className = className;
    }
    
    private void atFieldAssignCore(final CtField ctField, final boolean b, final int n, final boolean b2) throws CompileError {
        if (n != 0) {
            if (b) {
                this.bytecode.add(179);
                this.bytecode.growStack(b2 ? -2 : -1);
            }
            else {
                this.bytecode.add(181);
                this.bytecode.growStack(b2 ? -3 : -2);
            }
            this.bytecode.addIndex(n);
        }
        else {
            final CtClass declaringClass = ctField.getDeclaringClass();
            final MethodInfo fieldSetter = declaringClass.getAccessorMaker().getFieldSetter(ctField.getFieldInfo2(), b);
            this.bytecode.addInvokestatic(declaringClass, fieldSetter.getName(), fieldSetter.getDescriptor());
        }
    }
    
    @Override
    public void atMember(final Member member) throws CompileError {
        this.atFieldRead(member);
    }
    
    @Override
    protected void atFieldRead(final ASTree asTree) throws CompileError {
        final CtField fieldAccess = this.fieldAccess(asTree, true);
        if (fieldAccess == null) {
            this.atArrayLength(asTree);
            return;
        }
        final boolean resultStatic = this.resultStatic;
        final ASTree constantFieldValue = TypeChecker.getConstantFieldValue(fieldAccess);
        if (constantFieldValue == null) {
            this.atFieldRead(fieldAccess, resultStatic);
        }
        else {
            constantFieldValue.accept(this);
            this.setFieldType(fieldAccess.getFieldInfo2());
        }
    }
    
    private void atArrayLength(final ASTree asTree) throws CompileError {
        if (this.arrayDim == 0) {
            throw new CompileError(".length applied to a non array");
        }
        this.bytecode.addOpcode(190);
        this.exprType = 324;
        this.arrayDim = 0;
    }
    
    private int atFieldRead(final CtField ctField, final boolean b) throws CompileError {
        final FieldInfo fieldInfo2 = ctField.getFieldInfo2();
        final int setFieldType = this.setFieldType(fieldInfo2) ? 1 : 0;
        final AccessorMaker accessibleField = this.isAccessibleField(ctField, fieldInfo2);
        if (accessibleField != null) {
            final MethodInfo fieldGetter = accessibleField.getFieldGetter(fieldInfo2, b);
            this.bytecode.addInvokestatic(ctField.getDeclaringClass(), fieldGetter.getName(), fieldGetter.getDescriptor());
            return 0;
        }
        final int addFieldrefInfo = this.addFieldrefInfo(ctField, fieldInfo2);
        if (b) {
            this.bytecode.add(178);
            this.bytecode.growStack((setFieldType != 0) ? 2 : 1);
        }
        else {
            this.bytecode.add(180);
            this.bytecode.growStack(setFieldType);
        }
        this.bytecode.addIndex(addFieldrefInfo);
        return addFieldrefInfo;
    }
    
    private AccessorMaker isAccessibleField(final CtField ctField, final FieldInfo fieldInfo) throws CompileError {
        if (!AccessFlag.isPrivate(fieldInfo.getAccessFlags()) || ctField.getDeclaringClass() == this.thisClass) {
            return null;
        }
        final CtClass declaringClass = ctField.getDeclaringClass();
        if (!this.isEnclosing(declaringClass, this.thisClass)) {
            throw new CompileError("Field " + ctField.getName() + " in " + declaringClass.getName() + " is private.");
        }
        final AccessorMaker accessorMaker = declaringClass.getAccessorMaker();
        if (accessorMaker != null) {
            return accessorMaker;
        }
        throw new CompileError("fatal error.  bug?");
    }
    
    private boolean setFieldType(final FieldInfo fieldInfo) throws CompileError {
        final String descriptor = fieldInfo.getDescriptor();
        int n = 0;
        int arrayDim = 0;
        char c;
        for (c = descriptor.charAt(n); c == '['; c = descriptor.charAt(++n)) {
            ++arrayDim;
        }
        this.arrayDim = arrayDim;
        this.exprType = MemberResolver.descToType(c);
        if (c == 'L') {
            this.className = descriptor.substring(n + 1, descriptor.indexOf(59, n + 1));
        }
        else {
            this.className = null;
        }
        return c == 'J' || c == 'D';
    }
    
    private int addFieldrefInfo(final CtField ctField, final FieldInfo fieldInfo) {
        final ConstPool constPool = this.bytecode.getConstPool();
        return constPool.addFieldrefInfo(constPool.addClassInfo(ctField.getDeclaringClass().getName()), fieldInfo.getName(), fieldInfo.getDescriptor());
    }
    
    @Override
    protected void atClassObject2(final String s) throws CompileError {
        if (this.getMajorVersion() < 49) {
            super.atClassObject2(s);
        }
        else {
            this.bytecode.addLdc(this.bytecode.getConstPool().addClassInfo(s));
        }
    }
    
    @Override
    protected void atFieldPlusPlus(final int n, final boolean b, final ASTree asTree, final Expr expr, final boolean b2) throws CompileError {
        final CtField fieldAccess = this.fieldAccess(asTree, false);
        final boolean resultStatic = this.resultStatic;
        this.bytecode.addOpcode(89);
        final int atFieldRead = this.atFieldRead(fieldAccess, resultStatic);
        final boolean is2word = CodeGen.is2word(this.exprType, this.arrayDim);
        int n2;
        if (resultStatic) {
            n2 = (is2word ? 92 : 89);
        }
        else {
            n2 = (is2word ? 93 : 90);
        }
        this.atPlusPlusCore(n2, b2, n, b, expr);
        this.atFieldAssignCore(fieldAccess, resultStatic, atFieldRead, is2word);
    }
    
    protected CtField fieldAccess(final ASTree asTree, final boolean b) throws CompileError {
        if (!(asTree instanceof Member)) {
            if (asTree instanceof Expr) {
                final Expr expr = (Expr)asTree;
                final int operator = expr.getOperator();
                if (operator == 35) {
                    final CtField lookupField = this.resolver.lookupField(((Symbol)expr.oprand1()).get(), (Symbol)expr.oprand2());
                    this.resultStatic = true;
                    return lookupField;
                }
                if (operator == 46) {
                    CtField lookupFieldByJvmName = null;
                    try {
                        expr.oprand1().accept(this);
                        if (this.exprType == 307 && this.arrayDim == 0) {
                            lookupFieldByJvmName = this.resolver.lookupFieldByJvmName(this.className, (Symbol)expr.oprand2());
                        }
                        else {
                            if (b && this.arrayDim > 0 && ((Symbol)expr.oprand2()).get().equals("length")) {
                                return null;
                            }
                            badLvalue();
                        }
                        final boolean static1 = Modifier.isStatic(lookupFieldByJvmName.getModifiers());
                        if (static1) {
                            this.bytecode.addOpcode(87);
                        }
                        this.resultStatic = static1;
                        return lookupFieldByJvmName;
                    }
                    catch (NoFieldException ex) {
                        if (ex.getExpr() != expr.oprand1()) {
                            throw ex;
                        }
                        final CtField lookupFieldByJvmName2 = this.resolver.lookupFieldByJvmName2(ex.getField(), (Symbol)expr.oprand2(), asTree);
                        this.resultStatic = true;
                        return lookupFieldByJvmName2;
                    }
                }
                badLvalue();
            }
            else {
                badLvalue();
            }
            this.resultStatic = false;
            return null;
        }
        final String value = ((Member)asTree).get();
        CtField field;
        try {
            field = this.thisClass.getField(value);
        }
        catch (NotFoundException ex2) {
            throw new NoFieldException(value, asTree);
        }
        final boolean static2 = Modifier.isStatic(field.getModifiers());
        if (this.inStaticMethod) {
            throw new CompileError("not available in a static method: " + value);
        }
        this.bytecode.addAload(0);
        this.resultStatic = static2;
        return field;
    }
    
    private static void badLvalue() throws CompileError {
        throw new CompileError("bad l-value");
    }
    
    public CtClass[] makeParamList(final MethodDecl methodDecl) throws CompileError {
        ASTList list = methodDecl.getParams();
        CtClass[] array;
        if (list == null) {
            array = new CtClass[0];
        }
        else {
            int n = 0;
            array = new CtClass[list.length()];
            while (list != null) {
                array[n++] = this.resolver.lookupClass((Declarator)list.head());
                list = list.tail();
            }
        }
        return array;
    }
    
    public CtClass[] makeThrowsList(final MethodDecl methodDecl) throws CompileError {
        ASTList list = methodDecl.getThrows();
        if (list == null) {
            return null;
        }
        int n = 0;
        final CtClass[] array = new CtClass[list.length()];
        while (list != null) {
            array[n++] = this.resolver.lookupClassByName((ASTList)list.head());
            list = list.tail();
        }
        return array;
    }
    
    @Override
    protected String resolveClassName(final ASTList list) throws CompileError {
        return this.resolver.resolveClassName(list);
    }
    
    @Override
    protected String resolveClassName(final String s) throws CompileError {
        return this.resolver.resolveJvmClassName(s);
    }
}
