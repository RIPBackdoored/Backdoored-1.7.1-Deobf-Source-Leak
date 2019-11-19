package r.k.n;

public class g extends c
{
    public static final boolean ldc;
    public static final int lda;
    public static final boolean ldm;
    
    public g() {
        super(new String[] { "toggle", "t" });
    }
    
    @Override
    public boolean d(final String[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: astore_2       
        //    16: aload_1        
        //    17: astore_3       
        //    18: aload_3        
        //    19: arraylength    
        //    20: istore          4
        //    22: iconst_0       
        //    23: istore          5
        //    25: iload           5
        //    27: iload           4
        //    29: if_icmpge       49
        //    32: aload_3        
        //    33: iload           5
        //    35: aaload         
        //    36: astore          6
        //    38: aload_2        
        //    39: aload           6
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: pop            
        //    45: iinc            5, 1
        //    48: nop            
        //    49: invokestatic    r/k/d/m/o.lc:()Ljava/util/List;
        //    52: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    57: astore_3       
        //    58: aload_3        
        //    59: invokeinterface java/util/Iterator.hasNext:()Z
        //    64: ifeq            119
        //    67: aload_3        
        //    68: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    73: checkcast       Lr/k/d/m/g;
        //    76: astore          4
        //    78: aload_2        
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: aload           4
        //    84: getfield        r/k/d/m/g.cm:Ljava/lang/String;
        //    87: ldc             " "
        //    89: ldc             ""
        //    91: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    94: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    97: ifeq            118
        //   100: aload           4
        //   102: aload           4
        //   104: invokevirtual   r/k/d/m/g.lo:()Z
        //   107: ifne            112
        //   110: iconst_1       
        //   111: nop            
        //   112: iconst_0       
        //   113: invokevirtual   r/k/d/m/g.s:(Z)V
        //   116: iconst_1       
        //   117: ireturn        
        //   118: nop            
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: aload_2        
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: ldc             " not found!"
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   141: ldc             "red"
        //   143: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //   146: iconst_0       
        //   147: ireturn        
        //    StackMapTable: 00 09 FF 00 04 00 0A 07 00 19 07 00 1D 00 00 00 00 00 00 00 01 00 00 01 FF 00 12 00 0A 07 00 19 07 00 1D 07 00 1F 07 00 1D 01 01 00 01 01 01 00 00 17 FF 00 08 00 0A 07 00 19 07 00 1D 07 00 1F 07 00 33 00 01 00 01 01 01 00 00 FF 00 35 00 0A 07 00 19 07 00 1D 07 00 1F 07 00 33 07 00 55 01 00 01 01 01 00 01 07 00 55 FF 00 00 00 0A 07 00 19 07 00 1D 07 00 1F 07 00 33 07 00 55 01 00 01 01 01 00 02 07 00 55 01 04 FF 00 00 00 0A 07 00 19 07 00 1D 07 00 1F 07 00 33 00 01 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String k() {
        return "-t <hackname>";
    }
}
