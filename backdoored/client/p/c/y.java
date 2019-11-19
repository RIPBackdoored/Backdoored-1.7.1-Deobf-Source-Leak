package r.k.p.c;

import r.k.h;

public abstract class y implements h
{
    private String kt;
    private boolean kn;
    private r.k.b.i.h ki;
    private r.k.b.i.h kp;
    public static final boolean kr;
    public static final int kf;
    public static final boolean kb;
    
    y() {
        super();
        this.kn = true;
        this.ki = new r.k.b.i.h(0, 0);
        this.kp = new r.k.b.i.h(0, 0);
    }
    
    public y(final int n, final int n2, final int n3, final int n4) {
        super();
        this.kn = true;
        this.ki = new r.k.b.i.h(0, 0);
        this.kp = new r.k.b.i.h(0, 0);
        this.ki = new r.k.b.i.h(n, n2);
        this.kp = new r.k.b.i.h(n3, n4);
    }
    
    public boolean s(final int p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: iload_1        
        //     9: aload_0        
        //    10: invokevirtual   r/k/p/c/y.z:()Lr/k/b/i/h;
        //    13: getfield        r/k/b/i/h.slo:I
        //    16: if_icmplt       53
        //    19: iload_2        
        //    20: aload_0        
        //    21: invokevirtual   r/k/p/c/y.z:()Lr/k/b/i/h;
        //    24: getfield        r/k/b/i/h.slt:I
        //    27: if_icmplt       53
        //    30: iload_1        
        //    31: aload_0        
        //    32: iload_2        
        //    33: aload_0        
        //    34: invokevirtual   r/k/p/c/y.z:()Lr/k/b/i/h;
        //    37: getfield        r/k/b/i/h.slt:I
        //    40: aload_0        
        //    41: invokevirtual   r/k/p/c/y.c:()Lr/k/b/i/h;
        //    44: getfield        r/k/b/i/h.slt:I
        //    47: iadd           
        //    48: if_icmpgt       53
        //    51: iconst_1       
        //    52: nop            
        //    53: iconst_0       
        //    54: ireturn        
        //    StackMapTable: 00 05 FF 00 04 00 06 07 00 23 01 01 00 00 01 00 00 01 FF 00 19 00 06 07 00 23 01 01 01 01 01 00 00 14 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public abstract void d(final int p0, final int p1, final float p2);
    
    public abstract void d(final int p0, final int p1, final int p2);
    
    public abstract void s(final int p0, final int p1, final int p2);
    
    public abstract void l(final int p0, final int p1, final int p2);
    
    public String o() {
        return this.kt;
    }
    
    public void v(final String kt) {
        this.kt = kt;
    }
    
    public boolean u() {
        return this.kn;
    }
    
    public void d(final boolean kn) {
        this.kn = kn;
    }
    
    public r.k.b.i.h z() {
        return this.ki;
    }
    
    public void d(final r.k.b.i.h ki) {
        this.ki = ki;
    }
    
    public r.k.b.i.h c() {
        return this.kp;
    }
    
    public void s(final r.k.b.i.h h) {
    }
    
    @Override
    public String toString() {
        return this.o();
    }
}
