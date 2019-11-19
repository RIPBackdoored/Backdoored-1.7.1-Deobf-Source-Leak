package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class ArrayInit extends ASTList
{
    public ArrayInit(final ASTree asTree) {
        super(asTree);
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atArrayInit(this);
    }
    
    public String getTag() {
        return "array";
    }
}
