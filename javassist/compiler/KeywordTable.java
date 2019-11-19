package javassist.compiler;

import java.util.HashMap;

public final class KeywordTable extends HashMap
{
    public KeywordTable() {
        super();
    }
    
    public int lookup(final String s) {
        final Integer value = this.get(s);
        if (value == null) {
            return -1;
        }
        return value;
    }
    
    public void append(final String s, final int n) {
        this.put(s, new Integer(n));
    }
}
