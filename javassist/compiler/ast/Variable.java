package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class Variable extends Symbol
{
    protected Declarator declarator;
    
    public Variable(final String s, final Declarator declarator) {
        super(s);
        this.declarator = declarator;
    }
    
    public Declarator getDeclarator() {
        return this.declarator;
    }
    
    @Override
    public String toString() {
        return this.identifier + ":" + this.declarator.getType();
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atVariable(this);
    }
}
