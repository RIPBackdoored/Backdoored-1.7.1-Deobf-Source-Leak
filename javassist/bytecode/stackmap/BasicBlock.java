package javassist.bytecode.stackmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.BadBytecode;

public class BasicBlock
{
    protected int position;
    protected int length;
    protected int incoming;
    protected BasicBlock[] exit;
    protected boolean stop;
    protected Catch toCatch;
    
    protected BasicBlock(final int position) {
        super();
        this.position = position;
        this.length = 0;
        this.incoming = 0;
    }
    
    public static BasicBlock find(final BasicBlock[] array, final int n) throws BadBytecode {
        for (int i = 0; i < array.length; ++i) {
            final int position = array[i].position;
            if (position <= n && n < position + array[i].length) {
                return array[i];
            }
        }
        throw new BadBytecode("no basic block at " + n);
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        sb.append((lastIndex < 0) ? name : name.substring(lastIndex + 1));
        sb.append("[");
        this.toString2(sb);
        sb.append("]");
        return sb.toString();
    }
    
    protected void toString2(final StringBuffer sb) {
        sb.append("pos=").append(this.position).append(", len=").append(this.length).append(", in=").append(this.incoming).append(", exit{");
        if (this.exit != null) {
            for (int i = 0; i < this.exit.length; ++i) {
                sb.append(this.exit[i].position).append(",");
            }
        }
        sb.append("}, {");
        for (Catch catch1 = this.toCatch; catch1 != null; catch1 = catch1.next) {
            sb.append("(").append(catch1.body.position).append(", ").append(catch1.typeIndex).append("), ");
        }
        sb.append("}");
    }
}
