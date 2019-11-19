package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class StringL extends ASTree
{
    protected String text;
    
    public StringL(final String text) {
        super();
        this.text = text;
    }
    
    public String get() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return "\"" + this.text + "\"";
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atStringL(this);
    }
}
