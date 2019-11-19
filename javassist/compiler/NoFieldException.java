package javassist.compiler;

import javassist.compiler.ast.ASTree;

public class NoFieldException extends CompileError
{
    private String fieldName;
    private ASTree expr;
    
    public NoFieldException(final String fieldName, final ASTree expr) {
        super("no such field: " + fieldName);
        this.fieldName = fieldName;
        this.expr = expr;
    }
    
    public String getField() {
        return this.fieldName;
    }
    
    public ASTree getExpr() {
        return this.expr;
    }
}
