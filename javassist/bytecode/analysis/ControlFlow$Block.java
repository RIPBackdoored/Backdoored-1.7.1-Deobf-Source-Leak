package javassist.bytecode.analysis;

import java.util.ArrayList;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.stackmap.BasicBlock;

public static class Block extends BasicBlock
{
    public Object clientData;
    int index;
    MethodInfo method;
    Block[] entrances;
    
    Block(final int n, final MethodInfo method) {
        super(n);
        this.clientData = null;
        this.method = method;
    }
    
    @Override
    protected void toString2(final StringBuffer sb) {
        super.toString2(sb);
        sb.append(", incoming{");
        for (int i = 0; i < this.entrances.length; ++i) {
            sb.append(this.entrances[i].position).append(", ");
        }
        sb.append("}");
    }
    
    BasicBlock[] getExit() {
        return this.exit;
    }
    
    public int index() {
        return this.index;
    }
    
    public int position() {
        return this.position;
    }
    
    public int length() {
        return this.length;
    }
    
    public int incomings() {
        return this.incoming;
    }
    
    public Block incoming(final int n) {
        return this.entrances[n];
    }
    
    public int exits() {
        return (this.exit == null) ? 0 : this.exit.length;
    }
    
    public Block exit(final int n) {
        return (Block)this.exit[n];
    }
    
    public Catcher[] catchers() {
        final ArrayList<Catcher> list = new ArrayList<Catcher>();
        for (Catch catch1 = this.toCatch; catch1 != null; catch1 = catch1.next) {
            list.add(new Catcher(catch1));
        }
        return list.<Catcher>toArray(new Catcher[list.size()]);
    }
}
