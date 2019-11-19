package javassist.compiler.ast;

import javassist.compiler.CompileError;

public class BinExpr extends Expr
{
    private BinExpr(final int n, final ASTree asTree, final ASTList list) {
        super(n, asTree, list);
    }
    
    public static BinExpr makeBin(final int n, final ASTree asTree, final ASTree asTree2) {
        return new BinExpr(n, asTree, new ASTList(asTree2));
    }
    
    @Override
    public void accept(final Visitor visitor) throws CompileError {
        visitor.atBinExpr(this);
    }
}
