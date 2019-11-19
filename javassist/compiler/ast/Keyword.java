package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class Keyword extends ASTree
{
    protected int tokenId;
    
    public Keyword(final int tokenId) {
        super();
        this.tokenId = tokenId;
    }
    
    public int get() {
        return this.tokenId;
    }
    
    @Override
    public String toString() {
        return "id:" + this.tokenId;
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atKeyword(this);
    }
}
