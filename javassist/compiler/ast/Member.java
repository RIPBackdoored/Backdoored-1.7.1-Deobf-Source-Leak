package javassist.compiler.ast;

import javassist.compiler.CompileError;
import javassist.CtField;

public class Member extends Symbol
{
    private CtField field;
    
    public Member(final String s) {
        super(s);
        this.field = null;
    }
    
    public void setField(final CtField field) {
        this.field = field;
    }
    
    public CtField getField() {
        return this.field;
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atMember(this);
    }
}
