package javassist.compiler;

import javassist.NotFoundException;
import javassist.Modifier;
import javassist.compiler.ast.InstanceOfExpr;
import javassist.compiler.ast.Keyword;
import javassist.CtField;
import javassist.compiler.ast.DoubleConst;
import javassist.compiler.ast.IntConst;
import javassist.compiler.ast.StringL;
import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.BinExpr;
import javassist.compiler.ast.CastExpr;
import javassist.compiler.ast.CondExpr;
import javassist.compiler.ast.Declarator;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Variable;
import javassist.compiler.ast.AssignExpr;
import javassist.compiler.ast.ASTree;
import javassist.compiler.ast.ArrayInit;
import javassist.compiler.ast.NewExpr;
import javassist.compiler.ast.ASTList;
import javassist.ClassPool;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;
import javassist.bytecode.Opcode;
import javassist.compiler.ast.Visitor;

public class TypeChecker extends Visitor implements Opcode, TokenId
{
    static final String javaLangObject = "java.lang.Object";
    static final String jvmJavaLangObject = "java/lang/Object";
    static final String jvmJavaLangString = "java/lang/String";
    static final String jvmJavaLangClass = "java/lang/Class";
    protected int exprType;
    protected int arrayDim;
    protected String className;
    protected MemberResolver resolver;
    protected CtClass thisClass;
    protected MethodInfo thisMethod;
    
    public TypeChecker(final CtClass thisClass, final ClassPool classPool) {
        super();
        this.resolver = new MemberResolver(classPool);
        this.thisClass = thisClass;
        this.thisMethod = null;
    }
    
    protected static String argTypesToString(final int[] array, final int[] array2, final String[] array3) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(');
        final int length = array.length;
        if (length > 0) {
            int n = 0;
            while (true) {
                typeToString(sb, array[n], array2[n], array3[n]);
                if (++n >= length) {
                    break;
                }
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }
    
    protected static StringBuffer typeToString(final StringBuffer sb, final int n, int n2, final String s) {
        String s2;
        if (n == 307) {
            s2 = MemberResolver.jvmToJavaName(s);
        }
        else if (n == 412) {
            s2 = "Object";
        }
        else {
            try {
                s2 = MemberResolver.getTypeName(n);
            }
            catch (CompileError compileError) {
                s2 = "?";
            }
        }
        sb.append(s2);
        while (n2-- > 0) {
            sb.append("[]");
        }
        return sb;
    }
    
    public void setThisMethod(final MethodInfo thisMethod) {
        this.thisMethod = thisMethod;
    }
    
    protected static void fatal() throws CompileError {
        throw new CompileError("fatal");
    }
    
    protected String getThisName() {
        return MemberResolver.javaToJvmName(this.thisClass.getName());
    }
    
    protected String getSuperName() throws CompileError {
        return MemberResolver.javaToJvmName(MemberResolver.getSuperclass(this.thisClass).getName());
    }
    
    protected String resolveClassName(final ASTList list) throws CompileError {
        return this.resolver.resolveClassName(list);
    }
    
    protected String resolveClassName(final String s) throws CompileError {
        return this.resolver.resolveJvmClassName(s);
    }
    
    @Override
    public void atNewExpr(final NewExpr newExpr) throws CompileError {
        if (newExpr.isArray()) {
            this.atNewArrayExpr(newExpr);
        }
        else {
            final CtClass lookupClassByName = this.resolver.lookupClassByName(newExpr.getClassName());
            final String name = lookupClassByName.getName();
            this.atMethodCallCore(lookupClassByName, "<init>", newExpr.getArguments());
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
        if (initializer != null) {
            initializer.accept(this);
        }
        if (arraySize.length() > 1) {
            this.atMultiNewArray(arrayType, className, arraySize);
        }
        else {
            final ASTree head = arraySize.head();
            if (head != null) {
                head.accept(this);
            }
            this.exprType = arrayType;
            this.arrayDim = 1;
            if (arrayType == 307) {
                this.className = this.resolveClassName(className);
            }
            else {
                this.className = null;
            }
        }
    }
    
    @Override
    public void atArrayInit(final ArrayInit arrayInit) throws CompileError {
        ASTList tail = arrayInit;
        while (tail != null) {
            final ASTree head = tail.head();
            tail = tail.tail();
            if (head != null) {
                head.accept(this);
            }
        }
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
            tail = tail.tail();
        }
        this.exprType = exprType;
        this.arrayDim = length;
        if (exprType == 307) {
            this.className = this.resolveClassName(list);
        }
        else {
            this.className = null;
        }
    }
    
    @Override
    public void atAssignExpr(final AssignExpr assignExpr) throws CompileError {
        final int operator = assignExpr.getOperator();
        final ASTree oprand1 = assignExpr.oprand1();
        final ASTree oprand2 = assignExpr.oprand2();
        if (oprand1 instanceof Variable) {
            this.atVariableAssign(assignExpr, operator, (Variable)oprand1, ((Variable)oprand1).getDeclarator(), oprand2);
        }
        else {
            if (oprand1 instanceof Expr && ((Expr)oprand1).getOperator() == 65) {
                this.atArrayAssign(assignExpr, operator, (Expr)oprand1, oprand2);
                return;
            }
            this.atFieldAssign(assignExpr, operator, oprand1, oprand2);
        }
    }
    
    private void atVariableAssign(final Expr expr, final int n, final Variable variable, final Declarator declarator, final ASTree asTree) throws CompileError {
        final int type = declarator.getType();
        final int arrayDim = declarator.getArrayDim();
        final String className = declarator.getClassName();
        if (n != 61) {
            this.atVariable(variable);
        }
        asTree.accept(this);
        this.exprType = type;
        this.arrayDim = arrayDim;
        this.className = className;
    }
    
    private void atArrayAssign(final Expr expr, final int n, final Expr expr2, final ASTree asTree) throws CompileError {
        this.atArrayRead(expr2.oprand1(), expr2.oprand2());
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        asTree.accept(this);
        this.exprType = exprType;
        this.arrayDim = arrayDim;
        this.className = className;
    }
    
    protected void atFieldAssign(final Expr expr, final int n, final ASTree asTree, final ASTree asTree2) throws CompileError {
        this.atFieldRead(this.fieldAccess(asTree));
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        asTree2.accept(this);
        this.exprType = exprType;
        this.arrayDim = arrayDim;
        this.className = className;
    }
    
    @Override
    public void atCondExpr(final CondExpr condExpr) throws CompileError {
        this.booleanExpr(condExpr.condExpr());
        condExpr.thenExpr().accept(this);
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        condExpr.elseExpr().accept(this);
        if (arrayDim == this.arrayDim) {
            if (CodeGen.rightIsStrong(exprType, this.exprType)) {
                condExpr.setThen(new CastExpr(this.exprType, 0, condExpr.thenExpr()));
            }
            else if (CodeGen.rightIsStrong(this.exprType, exprType)) {
                condExpr.setElse(new CastExpr(exprType, 0, condExpr.elseExpr()));
                this.exprType = exprType;
            }
        }
    }
    
    @Override
    public void atBinExpr(final BinExpr binExpr) throws CompileError {
        final int operator = binExpr.getOperator();
        if (CodeGen.lookupBinOp(operator) >= 0) {
            if (operator == 43) {
                final Expr atPlusExpr = this.atPlusExpr(binExpr);
                if (atPlusExpr != null) {
                    binExpr.setOprand1(CallExpr.makeCall(Expr.make(46, atPlusExpr, new Member("toString")), null));
                    binExpr.setOprand2(null);
                    this.className = "java/lang/String";
                }
            }
            else {
                final ASTree oprand1 = binExpr.oprand1();
                final ASTree oprand2 = binExpr.oprand2();
                oprand1.accept(this);
                final int exprType = this.exprType;
                oprand2.accept(this);
                if (!this.isConstant(binExpr, operator, oprand1, oprand2)) {
                    this.computeBinExprType(binExpr, operator, exprType);
                }
            }
        }
        else {
            this.booleanExpr(binExpr);
        }
    }
    
    private Expr atPlusExpr(final BinExpr binExpr) throws CompileError {
        final ASTree oprand1 = binExpr.oprand1();
        final ASTree oprand2 = binExpr.oprand2();
        if (oprand2 == null) {
            oprand1.accept(this);
            return null;
        }
        if (isPlusExpr(oprand1)) {
            final Expr atPlusExpr = this.atPlusExpr((BinExpr)oprand1);
            if (atPlusExpr != null) {
                oprand2.accept(this);
                this.exprType = 307;
                this.arrayDim = 0;
                this.className = "java/lang/StringBuffer";
                return makeAppendCall(atPlusExpr, oprand2);
            }
        }
        else {
            oprand1.accept(this);
        }
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        oprand2.accept(this);
        if (this.isConstant(binExpr, 43, oprand1, oprand2)) {
            return null;
        }
        if ((exprType == 307 && "java/lang/String".equals(className)) || (this.exprType == 307 && this.arrayDim == 0 && "java/lang/String".equals(this.className))) {
            final NewExpr newExpr = new NewExpr(ASTList.make(new Symbol("java"), new Symbol("lang"), new Symbol("StringBuffer")), null);
            this.exprType = 307;
            this.arrayDim = 0;
            this.className = "java/lang/StringBuffer";
            return makeAppendCall(makeAppendCall(newExpr, oprand1), oprand2);
        }
        this.computeBinExprType(binExpr, 43, exprType);
        return null;
    }
    
    private boolean isConstant(final BinExpr binExpr, final int n, ASTree stripPlusExpr, ASTree stripPlusExpr2) throws CompileError {
        stripPlusExpr = stripPlusExpr(stripPlusExpr);
        stripPlusExpr2 = stripPlusExpr(stripPlusExpr2);
        ASTree oprand1 = null;
        if (stripPlusExpr instanceof StringL && stripPlusExpr2 instanceof StringL && n == 43) {
            oprand1 = new StringL(((StringL)stripPlusExpr).get() + ((StringL)stripPlusExpr2).get());
        }
        else if (stripPlusExpr instanceof IntConst) {
            oprand1 = ((IntConst)stripPlusExpr).compute(n, stripPlusExpr2);
        }
        else if (stripPlusExpr instanceof DoubleConst) {
            oprand1 = ((DoubleConst)stripPlusExpr).compute(n, stripPlusExpr2);
        }
        if (oprand1 == null) {
            return false;
        }
        binExpr.setOperator(43);
        binExpr.setOprand1(oprand1);
        binExpr.setOprand2(null);
        oprand1.accept(this);
        return true;
    }
    
    static ASTree stripPlusExpr(final ASTree asTree) {
        if (asTree instanceof BinExpr) {
            final BinExpr binExpr = (BinExpr)asTree;
            if (binExpr.getOperator() == 43 && binExpr.oprand2() == null) {
                return binExpr.getLeft();
            }
        }
        else if (asTree instanceof Expr) {
            final Expr expr = (Expr)asTree;
            final int operator = expr.getOperator();
            if (operator == 35) {
                final ASTree constantFieldValue = getConstantFieldValue((Member)expr.oprand2());
                if (constantFieldValue != null) {
                    return constantFieldValue;
                }
            }
            else if (operator == 43 && expr.getRight() == null) {
                return expr.getLeft();
            }
        }
        else if (asTree instanceof Member) {
            final ASTree constantFieldValue2 = getConstantFieldValue((Member)asTree);
            if (constantFieldValue2 != null) {
                return constantFieldValue2;
            }
        }
        return asTree;
    }
    
    private static ASTree getConstantFieldValue(final Member member) {
        return getConstantFieldValue(member.getField());
    }
    
    public static ASTree getConstantFieldValue(final CtField ctField) {
        if (ctField == null) {
            return null;
        }
        final Object constantValue = ctField.getConstantValue();
        if (constantValue == null) {
            return null;
        }
        if (constantValue instanceof String) {
            return new StringL((String)constantValue);
        }
        if (constantValue instanceof Double || constantValue instanceof Float) {
            return new DoubleConst(((Number)constantValue).doubleValue(), (constantValue instanceof Double) ? 405 : 404);
        }
        if (constantValue instanceof Number) {
            return new IntConst(((Number)constantValue).longValue(), (constantValue instanceof Long) ? 403 : 402);
        }
        if (constantValue instanceof Boolean) {
            return new Keyword(constantValue ? 410 : 411);
        }
        return null;
    }
    
    private static boolean isPlusExpr(final ASTree asTree) {
        return asTree instanceof BinExpr && ((BinExpr)asTree).getOperator() == 43;
    }
    
    private static Expr makeAppendCall(final ASTree asTree, final ASTree asTree2) {
        return CallExpr.makeCall(Expr.make(46, asTree, new Member("append")), new ASTList(asTree2));
    }
    
    private void computeBinExprType(final BinExpr binExpr, final int n, final int exprType) throws CompileError {
        final int exprType2 = this.exprType;
        if (n == 364 || n == 366 || n == 370) {
            this.exprType = exprType;
        }
        else {
            this.insertCast(binExpr, exprType, exprType2);
        }
        if (CodeGen.isP_INT(this.exprType) && this.exprType != 301) {
            this.exprType = 324;
        }
    }
    
    private void booleanExpr(final ASTree asTree) throws CompileError {
        final int compOperator = CodeGen.getCompOperator(asTree);
        if (compOperator == 358) {
            final BinExpr binExpr = (BinExpr)asTree;
            binExpr.oprand1().accept(this);
            final int exprType = this.exprType;
            final int arrayDim = this.arrayDim;
            binExpr.oprand2().accept(this);
            if (this.arrayDim == 0) {
                this.insertCast(binExpr, exprType, this.exprType);
            }
        }
        else if (compOperator == 33) {
            ((Expr)asTree).oprand1().accept(this);
        }
        else if (compOperator == 369 || compOperator == 368) {
            final BinExpr binExpr2 = (BinExpr)asTree;
            binExpr2.oprand1().accept(this);
            binExpr2.oprand2().accept(this);
        }
        else {
            asTree.accept(this);
        }
        this.exprType = 301;
        this.arrayDim = 0;
    }
    
    private void insertCast(final BinExpr binExpr, final int exprType, final int n) throws CompileError {
        if (CodeGen.rightIsStrong(exprType, n)) {
            binExpr.setLeft(new CastExpr(n, 0, binExpr.oprand1()));
        }
        else {
            this.exprType = exprType;
        }
    }
    
    @Override
    public void atCastExpr(final CastExpr castExpr) throws CompileError {
        final String resolveClassName = this.resolveClassName(castExpr.getClassName());
        castExpr.getOprand().accept(this);
        this.exprType = castExpr.getType();
        this.arrayDim = castExpr.getArrayDim();
        this.className = resolveClassName;
    }
    
    @Override
    public void atInstanceOfExpr(final InstanceOfExpr instanceOfExpr) throws CompileError {
        instanceOfExpr.getOprand().accept(this);
        this.exprType = 301;
        this.arrayDim = 0;
    }
    
    @Override
    public void atExpr(final Expr expr) throws CompileError {
        final int operator = expr.getOperator();
        final ASTree oprand1 = expr.oprand1();
        if (operator == 46) {
            final String value = ((Symbol)expr.oprand2()).get();
            if (value.equals("length")) {
                try {
                    this.atArrayLength(expr);
                }
                catch (NoFieldException ex) {
                    this.atFieldRead(expr);
                }
            }
            else if (value.equals("class")) {
                this.atClassObject(expr);
            }
            else {
                this.atFieldRead(expr);
            }
        }
        else if (operator == 35) {
            if (((Symbol)expr.oprand2()).get().equals("class")) {
                this.atClassObject(expr);
            }
            else {
                this.atFieldRead(expr);
            }
        }
        else if (operator == 65) {
            this.atArrayRead(oprand1, expr.oprand2());
        }
        else if (operator == 362 || operator == 363) {
            this.atPlusPlus(operator, oprand1, expr);
        }
        else if (operator == 33) {
            this.booleanExpr(expr);
        }
        else if (operator == 67) {
            fatal();
        }
        else {
            oprand1.accept(this);
            if (!this.isConstant(expr, operator, oprand1) && (operator == 45 || operator == 126) && CodeGen.isP_INT(this.exprType)) {
                this.exprType = 324;
            }
        }
    }
    
    private boolean isConstant(final Expr expr, final int n, ASTree stripPlusExpr) {
        stripPlusExpr = stripPlusExpr(stripPlusExpr);
        if (stripPlusExpr instanceof IntConst) {
            final IntConst intConst = (IntConst)stripPlusExpr;
            final long value = intConst.get();
            long n2;
            if (n == 45) {
                n2 = -value;
            }
            else {
                if (n != 126) {
                    return false;
                }
                n2 = ~value;
            }
            intConst.set(n2);
        }
        else {
            if (!(stripPlusExpr instanceof DoubleConst)) {
                return false;
            }
            final DoubleConst doubleConst = (DoubleConst)stripPlusExpr;
            if (n != 45) {
                return false;
            }
            doubleConst.set(-doubleConst.get());
        }
        expr.setOperator(43);
        return true;
    }
    
    @Override
    public void atCallExpr(final CallExpr callExpr) throws CompileError {
        String s = null;
        CtClass ctClass = null;
        final ASTree oprand1 = callExpr.oprand1();
        final ASTList list = (ASTList)callExpr.oprand2();
        if (oprand1 instanceof Member) {
            s = ((Member)oprand1).get();
            ctClass = this.thisClass;
        }
        else if (oprand1 instanceof Keyword) {
            s = "<init>";
            if (((Keyword)oprand1).get() == 336) {
                ctClass = MemberResolver.getSuperclass(this.thisClass);
            }
            else {
                ctClass = this.thisClass;
            }
        }
        else if (oprand1 instanceof Expr) {
            final Expr expr = (Expr)oprand1;
            s = ((Symbol)expr.oprand2()).get();
            final int operator = expr.getOperator();
            if (operator == 35) {
                ctClass = this.resolver.lookupClass(((Symbol)expr.oprand1()).get(), false);
            }
            else if (operator == 46) {
                final ASTree oprand2 = expr.oprand1();
                final String dotSuper = isDotSuper(oprand2);
                if (dotSuper != null) {
                    ctClass = MemberResolver.getSuperInterface(this.thisClass, dotSuper);
                }
                else {
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
                        expr.setOperator(35);
                        expr.setOprand1(new Symbol(MemberResolver.jvmToJavaName(this.className)));
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
        callExpr.setMethod(this.atMethodCallCore(ctClass, s, list));
    }
    
    private static void badMethod() throws CompileError {
        throw new CompileError("bad method");
    }
    
    static String isDotSuper(final ASTree asTree) {
        if (asTree instanceof Expr) {
            final Expr expr = (Expr)asTree;
            if (expr.getOperator() == 46) {
                final ASTree oprand2 = expr.oprand2();
                if (oprand2 instanceof Keyword && ((Keyword)oprand2).get() == 336) {
                    return ((Symbol)expr.oprand1()).get();
                }
            }
        }
        return null;
    }
    
    public MemberResolver.Method atMethodCallCore(final CtClass ctClass, final String s, final ASTList list) throws CompileError {
        final int methodArgsLength = this.getMethodArgsLength(list);
        final int[] array = new int[methodArgsLength];
        final int[] array2 = new int[methodArgsLength];
        final String[] array3 = new String[methodArgsLength];
        this.atMethodArgs(list, array, array2, array3);
        final MemberResolver.Method lookupMethod = this.resolver.lookupMethod(ctClass, this.thisClass, this.thisMethod, s, array, array2, array3);
        if (lookupMethod == null) {
            final String name = ctClass.getName();
            final String argTypesToString = argTypesToString(array, array2, array3);
            String s2;
            if (s.equals("<init>")) {
                s2 = "cannot find constructor " + name + argTypesToString;
            }
            else {
                s2 = s + argTypesToString + " not found in " + name;
            }
            throw new CompileError(s2);
        }
        this.setReturnType(lookupMethod.info.getDescriptor());
        return lookupMethod;
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
    
    void setReturnType(final String s) throws CompileError {
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
    }
    
    private void atFieldRead(final ASTree asTree) throws CompileError {
        this.atFieldRead(this.fieldAccess(asTree));
    }
    
    private void atFieldRead(final CtField ctField) throws CompileError {
        final String descriptor = ctField.getFieldInfo2().getDescriptor();
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
    }
    
    protected CtField fieldAccess(final ASTree asTree) throws CompileError {
        if (asTree instanceof Member) {
            final Member member = (Member)asTree;
            final String value = member.get();
            try {
                final CtField field = this.thisClass.getField(value);
                if (Modifier.isStatic(field.getModifiers())) {
                    member.setField(field);
                }
                return field;
            }
            catch (NotFoundException ex2) {
                throw new NoFieldException(value, asTree);
            }
        }
        if (asTree instanceof Expr) {
            final Expr expr = (Expr)asTree;
            final int operator = expr.getOperator();
            if (operator == 35) {
                final Member member2 = (Member)expr.oprand2();
                final CtField lookupField = this.resolver.lookupField(((Symbol)expr.oprand1()).get(), member2);
                member2.setField(lookupField);
                return lookupField;
            }
            if (operator == 46) {
                try {
                    expr.oprand1().accept(this);
                }
                catch (NoFieldException ex) {
                    if (ex.getExpr() != expr.oprand1()) {
                        throw ex;
                    }
                    return this.fieldAccess2(expr, ex.getField());
                }
                CompileError compileError = null;
                try {
                    if (this.exprType == 307 && this.arrayDim == 0) {
                        return this.resolver.lookupFieldByJvmName(this.className, (Symbol)expr.oprand2());
                    }
                }
                catch (CompileError compileError2) {
                    compileError = compileError2;
                }
                final ASTree oprand1 = expr.oprand1();
                if (oprand1 instanceof Symbol) {
                    return this.fieldAccess2(expr, ((Symbol)oprand1).get());
                }
                if (compileError != null) {
                    throw compileError;
                }
            }
        }
        throw new CompileError("bad filed access");
    }
    
    private CtField fieldAccess2(final Expr expr, final String s) throws CompileError {
        final Member member = (Member)expr.oprand2();
        final CtField lookupFieldByJvmName2 = this.resolver.lookupFieldByJvmName2(s, member, expr);
        expr.setOperator(35);
        expr.setOprand1(new Symbol(MemberResolver.jvmToJavaName(s)));
        member.setField(lookupFieldByJvmName2);
        return lookupFieldByJvmName2;
    }
    
    public void atClassObject(final Expr expr) throws CompileError {
        this.exprType = 307;
        this.arrayDim = 0;
        this.className = "java/lang/Class";
    }
    
    public void atArrayLength(final Expr expr) throws CompileError {
        expr.oprand1().accept(this);
        if (this.arrayDim == 0) {
            throw new NoFieldException("length", expr);
        }
        this.exprType = 324;
        this.arrayDim = 0;
    }
    
    public void atArrayRead(final ASTree asTree, final ASTree asTree2) throws CompileError {
        asTree.accept(this);
        final int exprType = this.exprType;
        final int arrayDim = this.arrayDim;
        final String className = this.className;
        asTree2.accept(this);
        this.exprType = exprType;
        this.arrayDim = arrayDim - 1;
        this.className = className;
    }
    
    private void atPlusPlus(final int n, ASTree oprand2, final Expr expr) throws CompileError {
        if (oprand2 == null) {
            oprand2 = expr.oprand2();
        }
        if (oprand2 instanceof Variable) {
            final Declarator declarator = ((Variable)oprand2).getDeclarator();
            this.exprType = declarator.getType();
            this.arrayDim = declarator.getArrayDim();
        }
        else {
            if (oprand2 instanceof Expr) {
                final Expr expr2 = (Expr)oprand2;
                if (expr2.getOperator() == 65) {
                    this.atArrayRead(expr2.oprand1(), expr2.oprand2());
                    final int exprType = this.exprType;
                    if (exprType == 324 || exprType == 303 || exprType == 306 || exprType == 334) {
                        this.exprType = 324;
                    }
                    return;
                }
            }
            this.atFieldPlusPlus(oprand2);
        }
    }
    
    protected void atFieldPlusPlus(final ASTree asTree) throws CompileError {
        this.atFieldRead(this.fieldAccess(asTree));
        final int exprType = this.exprType;
        if (exprType == 324 || exprType == 303 || exprType == 306 || exprType == 334) {
            this.exprType = 324;
        }
    }
    
    @Override
    public void atMember(final Member member) throws CompileError {
        this.atFieldRead(member);
    }
    
    @Override
    public void atVariable(final Variable variable) throws CompileError {
        final Declarator declarator = variable.getDeclarator();
        this.exprType = declarator.getType();
        this.arrayDim = declarator.getArrayDim();
        this.className = declarator.getClassName();
    }
    
    @Override
    public void atKeyword(final Keyword keyword) throws CompileError {
        this.arrayDim = 0;
        final int value = keyword.get();
        switch (value) {
            case 410:
            case 411:
                this.exprType = 301;
                break;
            case 412:
                this.exprType = 412;
                break;
            case 336:
            case 339:
                this.exprType = 307;
                if (value == 339) {
                    this.className = this.getThisName();
                    break;
                }
                this.className = this.getSuperName();
                break;
            default:
                fatal();
                break;
        }
    }
    
    @Override
    public void atStringL(final StringL stringL) throws CompileError {
        this.exprType = 307;
        this.arrayDim = 0;
        this.className = "java/lang/String";
    }
    
    @Override
    public void atIntConst(final IntConst intConst) throws CompileError {
        this.arrayDim = 0;
        final int type = intConst.getType();
        if (type == 402 || type == 401) {
            this.exprType = ((type == 402) ? 324 : 306);
        }
        else {
            this.exprType = 326;
        }
    }
    
    @Override
    public void atDoubleConst(final DoubleConst doubleConst) throws CompileError {
        this.arrayDim = 0;
        if (doubleConst.getType() == 405) {
            this.exprType = 312;
        }
        else {
            this.exprType = 317;
        }
    }
}
