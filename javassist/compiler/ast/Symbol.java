package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class Symbol extends ASTree
{
    protected String identifier;
    
    public Symbol(final String identifier) {
        super();
        this.identifier = identifier;
    }
    
    public String get() {
        return this.identifier;
    }
    
    @Override
    public String toString() {
        return this.identifier;
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atSymbol(this);
    }
}
