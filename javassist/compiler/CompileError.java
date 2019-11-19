package javassist.compiler;

import javassist.NotFoundException;
import javassist.CannotCompileException;

public class CompileError extends Exception
{
    private Lex lex;
    private String reason;
    
    public CompileError(final String reason, final Lex lex) {
        super();
        this.reason = reason;
        this.lex = lex;
    }
    
    public CompileError(final String reason) {
        super();
        this.reason = reason;
        this.lex = null;
    }
    
    public CompileError(final CannotCompileException ex) {
        this(ex.getReason());
    }
    
    public CompileError(final NotFoundException ex) {
        this("cannot find " + ex.getMessage());
    }
    
    public Lex getLex() {
        return this.lex;
    }
    
    @Override
    public String getMessage() {
        return this.reason;
    }
    
    @Override
    public String toString() {
        return "compile error: " + this.reason;
    }
}
