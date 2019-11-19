package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class FieldDecl extends ASTList
{
    public FieldDecl(final ASTree asTree, final ASTList list) {
        super(asTree, list);
    }
    
    public ASTList getModifiers() {
        return (ASTList)this.getLeft();
    }
    
    public Declarator getDeclarator() {
        return (Declarator)this.tail().head();
    }
    
    public ASTree getInit() {
        return this.sublist(2).head();
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atFieldDecl(this);
    }
}
