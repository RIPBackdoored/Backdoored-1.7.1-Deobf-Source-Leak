package javassist.compiler;

public class SyntaxError extends CompileError
{
    public SyntaxError(final Lex lex) {
        super("syntax error near \"" + lex.getTextAround() + "\"", lex);
    }
}
