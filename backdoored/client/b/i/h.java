package r.k.b.i;

public class h
{
    public int slo;
    public int slt;
    public static final boolean sln;
    public static final int sli;
    public static final boolean slp;
    
    public h(final int slo, final int n) {
        super();
        this.slo = slo;
    }
    
    @Override
    public boolean equals(final Object p0) {
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
        //     8: aload_1        
        //     9: instanceof      Lr/k/b/i/h;
        //    12: ifeq            46
        //    15: aload_1        
        //    16: checkcast       Lr/k/b/i/h;
        //    19: astore_2       
        //    20: aload_2        
        //    21: getfield        r/k/b/i/h.slo:I
        //    24: aload_0        
        //    25: getfield        r/k/b/i/h.slo:I
        //    28: if_icmpne       44
        //    31: aload_2        
        //    32: getfield        r/k/b/i/h.slt:I
        //    35: aload_0        
        //    36: getfield        r/k/b/i/h.slt:I
        //    39: if_icmpne       44
        //    42: iconst_1       
        //    43: nop            
        //    44: iconst_0       
        //    45: ireturn        
        //    46: aload_0        
        //    47: aload_1        
        //    48: invokespecial   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    51: ireturn        
        //    StackMapTable: 00 06 FF 00 04 00 06 07 00 17 07 00 04 00 00 00 01 00 00 01 FF 00 01 00 06 07 00 17 07 00 04 00 01 01 01 00 00 FF 00 23 00 06 07 00 17 07 00 04 07 00 17 01 01 01 00 00 40 01 FF 00 00 00 06 07 00 17 07 00 04 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String toString() {
        return "(" + this.slo + ", " + this.slt + ")";
    }
}
