package javassist.compiler;

import javassist.compiler.ast.Symbol;
import javassist.compiler.ast.CallExpr;
import javassist.compiler.ast.Expr;
import javassist.compiler.ast.Member;
import javassist.compiler.ast.ASTree;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.CodeAttribute;
import javassist.CtPrimitiveType;
import javassist.compiler.ast.Stmnt;
import javassist.NotFoundException;
import javassist.CtMethod;
import javassist.compiler.ast.Visitor;
import javassist.CtConstructor;
import javassist.Modifier;
import javassist.compiler.ast.Declarator;
import javassist.CtField;
import javassist.CtBehavior;
import javassist.compiler.ast.ASTList;
import javassist.CannotCompileException;
import javassist.bytecode.BadBytecode;
import javassist.compiler.ast.MethodDecl;
import javassist.compiler.ast.FieldDecl;
import javassist.CtMember;
import javassist.CtClass;
import javassist.bytecode.Bytecode;

public class Javac
{
    JvstCodeGen gen;
    SymbolTable stable;
    private Bytecode bytecode;
    public static final String param0Name = "$0";
    public static final String resultVarName = "$_";
    public static final String proceedName = "$proceed";
    
    public Javac(final CtClass ctClass) {
        this(new Bytecode(ctClass.getClassFile2().getConstPool(), 0, 0), ctClass);
    }
    
    public Javac(final Bytecode bytecode, final CtClass ctClass) {
        super();
        this.gen = new JvstCodeGen(bytecode, ctClass, ctClass.getClassPool());
        this.stable = new SymbolTable();
        this.bytecode = bytecode;
    }
    
    public Bytecode getBytecode() {
        return this.bytecode;
    }
    
    public CtMember compile(final String s) throws CompileError {
        final Parser parser = new Parser(new Lex(s));
        final ASTList member1 = parser.parseMember1(this.stable);
        try {
            if (member1 instanceof FieldDecl) {
                return this.compileField((FieldDecl)member1);
            }
            final CtBehavior compileMethod = this.compileMethod(parser, (MethodDecl)member1);
            final CtClass declaringClass = compileMethod.getDeclaringClass();
            compileMethod.getMethodInfo2().rebuildStackMapIf6(declaringClass.getClassPool(), declaringClass.getClassFile2());
            return compileMethod;
        }
        catch (BadBytecode badBytecode) {
            throw new CompileError(badBytecode.getMessage());
        }
        catch (CannotCompileException ex) {
            throw new CompileError(ex.getMessage());
        }
    }
    
    private CtField compileField(final FieldDecl fieldDecl) throws CompileError, CannotCompileException {
        final Declarator declarator = fieldDecl.getDeclarator();
        final CtFieldWithInit ctFieldWithInit = new CtFieldWithInit(this.gen.resolver.lookupClass(declarator), declarator.getVariable().get(), this.gen.getThisClass());
        ctFieldWithInit.setModifiers(MemberResolver.getModifiers(fieldDecl.getModifiers()));
        if (fieldDecl.getInit() != null) {
            ctFieldWithInit.setInit(fieldDecl.getInit());
        }
        return ctFieldWithInit;
    }
    
    private CtBehavior compileMethod(final Parser parser, MethodDecl method2) throws CompileError {
        final int modifiers = MemberResolver.getModifiers(method2.getModifiers());
        final CtClass[] paramList = this.gen.makeParamList(method2);
        final CtClass[] throwsList = this.gen.makeThrowsList(method2);
        this.recordParams(paramList, Modifier.isStatic(modifiers));
        method2 = parser.parseMethod2(this.stable, method2);
        try {
            if (method2.isConstructor()) {
                final CtConstructor ctConstructor = new CtConstructor(paramList, this.gen.getThisClass());
                ctConstructor.setModifiers(modifiers);
                method2.accept(this.gen);
                ctConstructor.getMethodInfo().setCodeAttribute(this.bytecode.toCodeAttribute());
                ctConstructor.setExceptionTypes(throwsList);
                return ctConstructor;
            }
            final Declarator return1 = method2.getReturn();
            final CtClass lookupClass = this.gen.resolver.lookupClass(return1);
            this.recordReturnType(lookupClass, false);
            final CtMethod thisMethod = new CtMethod(lookupClass, return1.getVariable().get(), paramList, this.gen.getThisClass());
            thisMethod.setModifiers(modifiers);
            this.gen.setThisMethod(thisMethod);
            method2.accept(this.gen);
            if (method2.getBody() != null) {
                thisMethod.getMethodInfo().setCodeAttribute(this.bytecode.toCodeAttribute());
            }
            else {
                thisMethod.setModifiers(modifiers | 0x400);
            }
            thisMethod.setExceptionTypes(throwsList);
            return thisMethod;
        }
        catch (NotFoundException ex) {
            throw new CompileError(ex.toString());
        }
    }
    
    public Bytecode compileBody(final CtBehavior ctBehavior, final String s) throws CompileError {
        try {
            this.recordParams(ctBehavior.getParameterTypes(), Modifier.isStatic(ctBehavior.getModifiers()));
            CtClass ctClass;
            if (ctBehavior instanceof CtMethod) {
                this.gen.setThisMethod((CtMethod)ctBehavior);
                ctClass = ((CtMethod)ctBehavior).getReturnType();
            }
            else {
                ctClass = CtClass.voidType;
            }
            this.recordReturnType(ctClass, false);
            final boolean b = ctClass == CtClass.voidType;
            if (s == null) {
                makeDefaultBody(this.bytecode, ctClass);
            }
            else {
                final Parser parser = new Parser(new Lex(s));
                final Stmnt statement = parser.parseStatement(new SymbolTable(this.stable));
                if (parser.hasMore()) {
                    throw new CompileError("the method/constructor body must be surrounded by {}");
                }
                boolean b2 = false;
                if (ctBehavior instanceof CtConstructor) {
                    b2 = !((CtConstructor)ctBehavior).isClassInitializer();
                }
                this.gen.atMethodBody(statement, b2, b);
            }
            return this.bytecode;
        }
        catch (NotFoundException ex) {
            throw new CompileError(ex.toString());
        }
    }
    
    private static void makeDefaultBody(final Bytecode bytecode, final CtClass ctClass) {
        int returnOp;
        int n;
        if (ctClass instanceof CtPrimitiveType) {
            returnOp = ((CtPrimitiveType)ctClass).getReturnOp();
            if (returnOp == 175) {
                n = 14;
            }
            else if (returnOp == 174) {
                n = 11;
            }
            else if (returnOp == 173) {
                n = 9;
            }
            else if (returnOp == 177) {
                n = 0;
            }
            else {
                n = 3;
            }
        }
        else {
            returnOp = 176;
            n = 1;
        }
        if (n != 0) {
            bytecode.addOpcode(n);
        }
        bytecode.addOpcode(returnOp);
    }
    
    public boolean recordLocalVariables(final CodeAttribute codeAttribute, final int n) throws CompileError {
        final LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
        if (localVariableAttribute == null) {
            return false;
        }
        for (int tableLength = localVariableAttribute.tableLength(), i = 0; i < tableLength; ++i) {
            final int startPc = localVariableAttribute.startPc(i);
            final int codeLength = localVariableAttribute.codeLength(i);
            if (startPc <= n && n < startPc + codeLength) {
                this.gen.recordVariable(localVariableAttribute.descriptor(i), localVariableAttribute.variableName(i), localVariableAttribute.index(i), this.stable);
            }
        }
        return true;
    }
    
    public boolean recordParamNames(final CodeAttribute codeAttribute, final int n) throws CompileError {
        final LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)codeAttribute.getAttribute("LocalVariableTable");
        if (localVariableAttribute == null) {
            return false;
        }
        for (int tableLength = localVariableAttribute.tableLength(), i = 0; i < tableLength; ++i) {
            final int index = localVariableAttribute.index(i);
            if (index < n) {
                this.gen.recordVariable(localVariableAttribute.descriptor(i), localVariableAttribute.variableName(i), index, this.stable);
            }
        }
        return true;
    }
    
    public int recordParams(final CtClass[] array, final boolean b) throws CompileError {
        return this.gen.recordParams(array, b, "$", "$args", "$$", this.stable);
    }
    
    public int recordParams(final String s, final CtClass[] array, final boolean b, final int n, final boolean b2) throws CompileError {
        return this.gen.recordParams(array, b2, "$", "$args", "$$", b, n, s, this.stable);
    }
    
    public void setMaxLocals(final int maxLocals) {
        this.gen.setMaxLocals(maxLocals);
    }
    
    public int recordReturnType(final CtClass ctClass, final boolean b) throws CompileError {
        this.gen.recordType(ctClass);
        return this.gen.recordReturnType(ctClass, "$r", b ? "$_" : null, this.stable);
    }
    
    public void recordType(final CtClass ctClass) {
        this.gen.recordType(ctClass);
    }
    
    public int recordVariable(final CtClass ctClass, final String s) throws CompileError {
        return this.gen.recordVariable(ctClass, s, this.stable);
    }
    
    public void recordProceed(final String s, final String s2) throws CompileError {
        this.gen.setProceedHandler(new ProceedHandler() {
            final /* synthetic */ String val$m;
            final /* synthetic */ ASTree val$texpr = new Parser(new Lex(s)).parseExpression(Javac.this.stable);
            final /* synthetic */ Javac this$0;
            
            Javac$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
                ASTree make = new Member(s2);
                if (this.val$texpr != null) {
                    make = Expr.make(46, this.val$texpr, make);
                }
                jvstCodeGen.compileExpr(CallExpr.makeCall(make, list));
                jvstCodeGen.addNullIfVoid();
            }
            
            @Override
            public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
                ASTree make = new Member(s2);
                if (this.val$texpr != null) {
                    make = Expr.make(46, this.val$texpr, make);
                }
                CallExpr.makeCall(make, list).accept(jvstTypeChecker);
                jvstTypeChecker.addNullIfVoid();
            }
        }, "$proceed");
    }
    
    public void recordStaticProceed(final String s, final String s2) throws CompileError {
        this.gen.setProceedHandler(new ProceedHandler() {
            final /* synthetic */ String val$c;
            final /* synthetic */ String val$m;
            final /* synthetic */ Javac this$0;
            
            Javac$2() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
                jvstCodeGen.compileExpr(CallExpr.makeCall(Expr.make(35, new Symbol(s), new Member(s2)), list));
                jvstCodeGen.addNullIfVoid();
            }
            
            @Override
            public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
                CallExpr.makeCall(Expr.make(35, new Symbol(s), new Member(s2)), list).accept(jvstTypeChecker);
                jvstTypeChecker.addNullIfVoid();
            }
        }, "$proceed");
    }
    
    public void recordSpecialProceed(final String s, final String s2, final String s3, final String s4, final int n) throws CompileError {
        this.gen.setProceedHandler(new ProceedHandler() {
            final /* synthetic */ ASTree val$texpr = new Parser(new Lex(s)).parseExpression(Javac.this.stable);
            final /* synthetic */ int val$methodIndex;
            final /* synthetic */ String val$descriptor;
            final /* synthetic */ String val$classname;
            final /* synthetic */ String val$methodname;
            final /* synthetic */ Javac this$0;
            
            Javac$3() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void doit(final JvstCodeGen jvstCodeGen, final Bytecode bytecode, final ASTList list) throws CompileError {
                jvstCodeGen.compileInvokeSpecial(this.val$texpr, n, s4, list);
            }
            
            @Override
            public void setReturnType(final JvstTypeChecker jvstTypeChecker, final ASTList list) throws CompileError {
                jvstTypeChecker.compileInvokeSpecial(this.val$texpr, s2, s3, s4, list);
            }
        }, "$proceed");
    }
    
    public void recordProceed(final ProceedHandler proceedHandler) {
        this.gen.setProceedHandler(proceedHandler, "$proceed");
    }
    
    public void compileStmnt(final String s) throws CompileError {
        final Parser parser = new Parser(new Lex(s));
        final SymbolTable symbolTable = new SymbolTable(this.stable);
        while (parser.hasMore()) {
            final Stmnt statement = parser.parseStatement(symbolTable);
            if (statement != null) {
                statement.accept(this.gen);
            }
        }
    }
    
    public void compileExpr(final String s) throws CompileError {
        this.compileExpr(parseExpr(s, this.stable));
    }
    
    public static ASTree parseExpr(final String s, final SymbolTable symbolTable) throws CompileError {
        return new Parser(new Lex(s)).parseExpression(symbolTable);
    }
    
    public void compileExpr(final ASTree asTree) throws CompileError {
        if (asTree != null) {
            this.gen.compileExpr(asTree);
        }
    }
}
