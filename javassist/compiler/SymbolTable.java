package javassist.compiler;

import javassist.compiler.ast.Declarator;
import java.util.HashMap;

public final class SymbolTable extends HashMap
{
    private SymbolTable parent;
    
    public SymbolTable() {
        this(null);
    }
    
    public SymbolTable(final SymbolTable parent) {
        super();
        this.parent = parent;
    }
    
    public SymbolTable getParent() {
        return this.parent;
    }
    
    public Declarator lookup(final String s) {
        final Declarator declarator = this.get(s);
        if (declarator == null && this.parent != null) {
            return this.parent.lookup(s);
        }
        return declarator;
    }
    
    public void append(final String s, final Declarator declarator) {
        this.put(s, declarator);
    }
}
