package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class MethodDecl extends ASTList
{
    public static final String initName = "<init>";
    
    public MethodDecl(final ASTree asTree, final ASTList list) {
        super(asTree, list);
    }
    
    public boolean isConstructor() {
        final Symbol variable = this.getReturn().getVariable();
        return variable != null && "<init>".equals(variable.get());
    }
    
    public ASTList getModifiers() {
        return (ASTList)this.getLeft();
    }
    
    public Declarator getReturn() {
        return (Declarator)this.tail().head();
    }
    
    public ASTList getParams() {
        return (ASTList)this.sublist(2).head();
    }
    
    public ASTList getThrows() {
        return (ASTList)this.sublist(3).head();
    }
    
    public Stmnt getBody() {
        return (Stmnt)this.sublist(4).head();
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atMethodDecl(this);
    }
}
