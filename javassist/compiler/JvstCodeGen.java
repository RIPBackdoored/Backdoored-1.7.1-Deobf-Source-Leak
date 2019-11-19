package javassist.compiler;

import javassist.NotFoundException;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Stmnt;
import javassist.compiler.ast.CallExpr;
import javassist.CtPrimitiveType;
import javassist.compiler.ast.ASTList;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.Visitor;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.Expr;
import javassist.bytecode.Descriptor;
import javassist.compiler.ast.Member;
import javassist.ClassPool;
import javassist.bytecode.Bytecode;
import javassist.CtClass;

public class JvstCodeGen extends MemberCodeGen
{
    String paramArrayName;
    String paramListName;
    CtClass[] paramTypeList;
    private int paramVarBase;
    private boolean useParam0;
    private String param0Type;
    public static final String sigName = "$sig";
    public static final String dollarTypeName = "$type";
    public static final String clazzName = "$class";
    private CtClass dollarType;
    CtClass returnType;
    String returnCastName;
    private String returnVarName;
    public static final String wrapperCastName = "$w";
    String proceedName;
    public static final String cflowName = "$cflow";
    ProceedHandler procHandler;
    
    public JvstCodeGen(final Bytecode bytecode, final CtClass ctClass, final ClassPool classPool) {
        super(bytecode, ctClass, classPool);
        this.paramArrayName = null;
        this.paramListName = null;
        this.paramTypeList = null;
        this.paramVarBase = 0;
        this.useParam0 = false;
        this.param0Type = null;
        this.dollarType = null;
        this.returnType = null;
        this.returnCastName = null;
        this.returnVarName = null;
        this.proceedName = null;
        this.procHandler = null;
        this.setTypeChecker(new JvstTypeChecker(ctClass, classPool, this));
    }
    
    private int indexOfParam1() {
        return this.paramVarBase + (this.useParam0 ? 1 : 0);
    }
    
    public void setProceedHandler(final ProceedHandler procHandler, final String proceedName) {
        this.proceedName = proceedName;
        this.procHandler = procHandler;
    }
    
    public void addNullIfVoid() {
        if (this.exprType == 344) {
            this.bytecode.addOpcode(1);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Object";
        }
    }
    
    @Override
    public void atMember(final Member member) throws CompileError {
        final String value = member.get();
        if (value.equals(this.paramArrayName)) {
            compileParameterList(this.bytecode, this.paramTypeList, this.indexOfParam1());
            this.exprType = 307;
            this.arrayDim = 1;
            this.className = "java/lang/Object";
        }
        else if (value.equals("$sig")) {
            this.bytecode.addLdc(Descriptor.ofMethod(this.returnType, this.paramTypeList));
            this.bytecode.addInvokestatic("javassist/runtime/Desc", "getParams", "(Ljava/lang/String;)[Ljava/lang/Class;");
            this.exprType = 307;
            this.arrayDim = 1;
            this.className = "java/lang/Class";
        }
        else if (value.equals("$type")) {
            if (this.dollarType == null) {
                throw new CompileError("$type is not available");
            }
            this.bytecode.addLdc(Descriptor.of(this.dollarType));
            this.callGetType("getType");
        }
        else if (value.equals("$class")) {
            if (this.param0Type == null) {
                throw new CompileError("$class is not available");
            }
            this.bytecode.addLdc(this.param0Type);
            this.callGetType("getClazz");
        }
        else {
            super.atMember(member);
        }
    }
    
    private void callGetType(final String s) {
        this.bytecode.addInvokestatic("javassist/runtime/Desc", s, "(Ljava/lang/String;)Ljava/lang/Class;");
        this.exprType = 307;
        this.arrayDim = 0;
        this.className = "java/lang/Class";
    }
    
    @Override
    protected void atFieldAssign(final Expr expr, final int n, final ASTree asTree, final ASTree asTree2, final boolean b) throws CompileError {
        if (asTree instanceof Member && ((Member)asTree).get().equals(this.paramArrayName)) {
            if (n != 61) {
                throw new CompileError("bad operator for " + this.paramArrayName);
            }
            asTree2.accept(this);
            if (this.arrayDim != 1 || this.exprType != 307) {
                throw new CompileError("invalid type for " + this.paramArrayName);
            }
            this.atAssignParamList(this.paramTypeList, this.bytecode);
            this.bytecode.addOpcode(87);
        }
        else {
            super.atFieldAssign(expr, n, asTree, asTree2, b);
        }
    }
    
    protected void atAssignParamList(final CtClass[] array, final Bytecode bytecode) throws CompileError {
        if (array == null) {
            return;
        }
        int indexOfParam1 = this.indexOfParam1();
        for (int length = array.length, i = 0; i < length; ++i) {
            bytecode.addOpcode(89);
            bytecode.addIconst(i);
            bytecode.addOpcode(50);
            this.compileUnwrapValue(array[i], bytecode);
            bytecode.addStore(indexOfParam1, array[i]);
            indexOfParam1 += (CodeGen.is2word(this.exprType, this.arrayDim) ? 2 : 1);
        }
    }
    
    @Override
    public void atCastExpr(final CastExpr castExpr) throws CompileError {
        final ASTList className = castExpr.getClassName();
        if (className != null && castExpr.getArrayDim() == 0) {
            final ASTree head = className.head();
            if (head instanceof Symbol && className.tail() == null) {
                final String value = ((Symbol)head).get();
                if (value.equals(this.returnCastName)) {
                    this.atCastToRtype(castExpr);
                    return;
                }
                if (value.equals("$w")) {
                    this.atCastToWrapper(castExpr);
                    return;
                }
            }
        }
        super.atCastExpr(castExpr);
    }
    
    protected void atCastToRtype(final CastExpr castExpr) throws CompileError {
        castExpr.getOprand().accept(this);
        if (this.exprType == 344 || CodeGen.isRefType(this.exprType) || this.arrayDim > 0) {
            this.compileUnwrapValue(this.returnType, this.bytecode);
        }
        else {
            if (!(this.returnType instanceof CtPrimitiveType)) {
                throw new CompileError("invalid cast");
            }
            final int descToType = MemberResolver.descToType(((CtPrimitiveType)this.returnType).getDescriptor());
            this.atNumCastExpr(this.exprType, descToType);
            this.exprType = descToType;
            this.arrayDim = 0;
            this.className = null;
        }
    }
    
    protected void atCastToWrapper(final CastExpr castExpr) throws CompileError {
        castExpr.getOprand().accept(this);
        if (CodeGen.isRefType(this.exprType) || this.arrayDim > 0) {
            return;
        }
        final CtClass lookupClass = this.resolver.lookupClass(this.exprType, this.arrayDim, this.className);
        if (lookupClass instanceof CtPrimitiveType) {
            final CtPrimitiveType ctPrimitiveType = (CtPrimitiveType)lookupClass;
            final String wrapperName = ctPrimitiveType.getWrapperName();
            this.bytecode.addNew(wrapperName);
            this.bytecode.addOpcode(89);
            if (ctPrimitiveType.getDataSize() > 1) {
                this.bytecode.addOpcode(94);
            }
            else {
                this.bytecode.addOpcode(93);
            }
            this.bytecode.addOpcode(88);
            this.bytecode.addInvokespecial(wrapperName, "<init>", "(" + ctPrimitiveType.getDescriptor() + ")V");
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Object";
        }
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) throws CompileError {
        final ASTree oprand1 = callExpr.oprand1();
        if (oprand1 instanceof Member) {
            final String value = ((Member)oprand1).get();
            if (this.procHandler != null && value.equals(this.proceedName)) {
                this.procHandler.doit(this, this.bytecode, (ASTList)callExpr.oprand2());
                return;
            }
            if (value.equals("$cflow")) {
                this.atCflow((ASTList)callExpr.oprand2());
                return;
            }
        }
        super.atCallExpr(callExpr);
    }
    
    protected void atCflow(final ASTList list) throws CompileError {
        final StringBuffer sb = new StringBuffer();
        if (list == null || list.tail() != null) {
            throw new CompileError("bad $cflow");
        }
        makeCflowName(sb, list.head());
        final String string = sb.toString();
        final Object[] lookupCflow = this.resolver.getClassPool().lookupCflow(string);
        if (lookupCflow == null) {
            throw new CompileError("no such $cflow: " + string);
        }
        this.bytecode.addGetstatic((String)lookupCflow[0], (String)lookupCflow[1], "Ljavassist/runtime/Cflow;");
        this.bytecode.addInvokevirtual("javassist.runtime.Cflow", "value", "()I");
        this.exprType = 324;
        this.arrayDim = 0;
        this.className = null;
    }
    
    private static void makeCflowName(final StringBuffer sb, final ASTree asTree) throws CompileError {
        if (asTree instanceof Symbol) {
            sb.append(((Symbol)asTree).get());
            return;
        }
        if (asTree instanceof Expr) {
            final Expr expr = (Expr)asTree;
            if (expr.getOperator() == 46) {
                makeCflowName(sb, expr.oprand1());
                sb.append('.');
                makeCflowName(sb, expr.oprand2());
                return;
            }
        }
        throw new CompileError("bad $cflow");
    }
    
    public boolean isParamListName(final ASTList list) {
        if (this.paramTypeList != null && list != null && list.tail() == null) {
            final ASTree head = list.head();
            return head instanceof Member && ((Member)head).get().equals(this.paramListName);
        }
        return false;
    }
    
    @Override
    public int getMethodArgsLength(ASTList tail) {
        final String paramListName = this.paramListName;
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head instanceof Member && ((Member)head).get().equals(paramListName)) {
                if (this.paramTypeList != null) {
                    n += this.paramTypeList.length;
                }
            }
            else {
                ++n;
            }
            tail = tail.tail();
        }
        return n;
    }
    
    @Override
    public void atMethodArgs(ASTList tail, final int[] array, final int[] array2, final String[] array3) throws CompileError {
        final CtClass[] paramTypeList = this.paramTypeList;
        final String paramListName = this.paramListName;
        int n = 0;
        while (tail != null) {
            final ASTree head = tail.head();
            if (head instanceof Member && ((Member)head).get().equals(paramListName)) {
                if (paramTypeList != null) {
                    final int length = paramTypeList.length;
                    int indexOfParam1 = this.indexOfParam1();
                    for (final CtClass type : paramTypeList) {
                        indexOfParam1 += this.bytecode.addLoad(indexOfParam1, type);
                        this.setType(type);
                        array[n] = this.exprType;
                        array2[n] = this.arrayDim;
                        array3[n] = this.className;
                        ++n;
                    }
                }
            }
            else {
                head.accept(this);
                array[n] = this.exprType;
                array2[n] = this.arrayDim;
                array3[n] = this.className;
                ++n;
            }
            tail = tail.tail();
        }
    }
    
    void compileInvokeSpecial(final ASTree asTree, final int n, final String s, final ASTList list) throws CompileError {
        asTree.accept(this);
        final int methodArgsLength = this.getMethodArgsLength(list);
        this.atMethodArgs(list, new int[methodArgsLength], new int[methodArgsLength], new String[methodArgsLength]);
        this.bytecode.addInvokespecial(n, s);
        this.setReturnType(s, false, false);
        this.addNullIfVoid();
    }
    
    @Override
    protected void atReturnStmnt(final Stmnt stmnt) throws CompileError {
        ASTree left = stmnt.getLeft();
        if (left != null && this.returnType == CtClass.voidType) {
            this.compileExpr(left);
            if (CodeGen.is2word(this.exprType, this.arrayDim)) {
                this.bytecode.addOpcode(88);
            }
            else if (this.exprType != 344) {
                this.bytecode.addOpcode(87);
            }
            left = null;
        }
        this.atReturnStmnt2(left);
    }
    
    public int recordReturnType(final CtClass returnType, final String returnCastName, final String returnVarName, final SymbolTable symbolTable) throws CompileError {
        this.returnType = returnType;
        this.returnCastName = returnCastName;
        this.returnVarName = returnVarName;
        if (returnVarName == null) {
            return -1;
        }
        final int maxLocals = this.getMaxLocals();
        this.setMaxLocals(maxLocals + this.recordVar(returnType, returnVarName, maxLocals, symbolTable));
        return maxLocals;
    }
    
    public void recordType(final CtClass dollarType) {
        this.dollarType = dollarType;
    }
    
    public int recordParams(final CtClass[] array, final boolean b, final String s, final String s2, final String s3, final SymbolTable symbolTable) throws CompileError {
        return this.recordParams(array, b, s, s2, s3, true, 0, this.getThisName(), symbolTable);
    }
    
    public int recordParams(final CtClass[] paramTypeList, final boolean inStaticMethod, final String s, final String paramArrayName, final String paramListName, final boolean useParam0, final int paramVarBase, final String s2, final SymbolTable symbolTable) throws CompileError {
        this.paramTypeList = paramTypeList;
        this.paramArrayName = paramArrayName;
        this.paramListName = paramListName;
        this.paramVarBase = paramVarBase;
        this.useParam0 = useParam0;
        if (s2 != null) {
            this.param0Type = MemberResolver.jvmToJavaName(s2);
        }
        this.inStaticMethod = inStaticMethod;
        int maxLocals = paramVarBase;
        if (useParam0) {
            final String string = s + "0";
            symbolTable.append(string, new Declarator(307, MemberResolver.javaToJvmName(s2), 0, maxLocals++, new Symbol(string)));
        }
        for (int i = 0; i < paramTypeList.length; ++i) {
            maxLocals += this.recordVar(paramTypeList[i], s + (i + 1), maxLocals, symbolTable);
        }
        if (this.getMaxLocals() < maxLocals) {
            this.setMaxLocals(maxLocals);
        }
        return maxLocals;
    }
    
    public int recordVariable(final CtClass ctClass, final String s, final SymbolTable symbolTable) throws CompileError {
        if (s == null) {
            return -1;
        }
        final int maxLocals = this.getMaxLocals();
        this.setMaxLocals(maxLocals + this.recordVar(ctClass, s, maxLocals, symbolTable));
        return maxLocals;
    }
    
    private int recordVar(final CtClass type, final String s, final int n, final SymbolTable symbolTable) throws CompileError {
        if (type == CtClass.voidType) {
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/Object";
        }
        else {
            this.setType(type);
        }
        symbolTable.append(s, new Declarator(this.exprType, this.className, this.arrayDim, n, new Symbol(s)));
        return CodeGen.is2word(this.exprType, this.arrayDim) ? 2 : 1;
    }
    
    public void recordVariable(final String s, final String s2, final int n, final SymbolTable symbolTable) throws CompileError {
        int n2;
        char char1;
        for (n2 = 0; (char1 = s.charAt(n2)) == '['; ++n2) {}
        final int descToType = MemberResolver.descToType(char1);
        String substring = null;
        if (descToType == 307) {
            substring = s.substring(1, s.length() - 1);
        }
        symbolTable.append(s2, new Declarator(descToType, substring, n2, n, new Symbol(s2)));
    }
    
    public static int compileParameterList(final Bytecode bytecode, final CtClass[] array, int n) {
        if (array == null) {
            bytecode.addIconst(0);
            bytecode.addAnewarray("java.lang.Object");
            return 1;
        }
        final CtClass[] array2 = { null };
        final int length = array.length;
        bytecode.addIconst(length);
        bytecode.addAnewarray("java.lang.Object");
        for (int i = 0; i < length; ++i) {
            bytecode.addOpcode(89);
            bytecode.addIconst(i);
            if (array[i].isPrimitive()) {
                final CtPrimitiveType ctPrimitiveType = (CtPrimitiveType)array[i];
                final String wrapperName = ctPrimitiveType.getWrapperName();
                bytecode.addNew(wrapperName);
                bytecode.addOpcode(89);
                n += bytecode.addLoad(n, ctPrimitiveType);
                array2[0] = ctPrimitiveType;
                bytecode.addInvokespecial(wrapperName, "<init>", Descriptor.ofMethod(CtClass.voidType, array2));
            }
            else {
                bytecode.addAload(n);
                ++n;
            }
            bytecode.addOpcode(83);
        }
        return 8;
    }
    
    protected void compileUnwrapValue(final CtClass ctClass, final Bytecode bytecode) throws CompileError {
        if (ctClass == CtClass.voidType) {
            this.addNullIfVoid();
            return;
        }
        if (this.exprType == 344) {
            throw new CompileError("invalid type for " + this.returnCastName);
        }
        if (ctClass instanceof CtPrimitiveType) {
            final CtPrimitiveType ctPrimitiveType = (CtPrimitiveType)ctClass;
            final String wrapperName = ctPrimitiveType.getWrapperName();
            bytecode.addCheckcast(wrapperName);
            bytecode.addInvokevirtual(wrapperName, ctPrimitiveType.getGetMethodName(), ctPrimitiveType.getGetMethodDescriptor());
            this.setType(ctClass);
        }
        else {
            bytecode.addCheckcast(ctClass);
            this.setType(ctClass);
        }
    }
    
    public void setType(final CtClass ctClass) throws CompileError {
        this.setType(ctClass, 0);
    }
    
    private void setType(final CtClass ctClass, final int n) throws CompileError {
        if (ctClass.isPrimitive()) {
            this.exprType = MemberResolver.descToType(((CtPrimitiveType)ctClass).getDescriptor());
            this.arrayDim = n;
            this.className = null;
        }
        else {
            if (ctClass.isArray()) {
                try {
                    this.setType(ctClass.getComponentType(), n + 1);
                    return;
                }
                catch (NotFoundException ex) {
                    throw new CompileError("undefined type: " + ctClass.getName());
                }
            }
            this.exprType = 307;
            this.arrayDim = n;
            this.className = MemberResolver.javaToJvmName(ctClass.getName());
        }
    }
    
    public void doNumCast(final CtClass ctClass) throws CompileError {
        if (this.arrayDim == 0 && !CodeGen.isRefType(this.exprType)) {
            if (!(ctClass instanceof CtPrimitiveType)) {
                throw new CompileError("type mismatch");
            }
            this.atNumCastExpr(this.exprType, MemberResolver.descToType(((CtPrimitiveType)ctClass).getDescriptor()));
        }
    }
}
