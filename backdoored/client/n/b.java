package r.k.n;

public class b extends c
{
    public static final boolean sjc;
    public static final int sja;
    public static final boolean sjm;
    
    public b() {
        super(new String[] { "drawn", "shown", "visible" });
    }
    
    @Override
    public boolean d(final String[] p0) {
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
        //     9: arraylength    
        //    10: iconst_1       
        //    11: if_icmpge       21
        //    14: ldc             "Invalid args!"
        //    16: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //    19: iconst_0       
        //    20: ireturn        
        //    21: new             Ljava/lang/StringBuilder;
        //    24: dup            
        //    25: invokespecial   java/lang/StringBuilder.<init>:()V
        //    28: astore_2       
        //    29: aload_1        
        //    30: astore_3       
        //    31: aload_3        
        //    32: arraylength    
        //    33: istore          4
        //    35: iconst_0       
        //    36: istore          5
        //    38: iload           5
        //    40: iload           4
        //    42: if_icmpge       55
        //    45: aload_3        
        //    46: iload           5
        //    48: aaload         
        //    49: astore          6
        //    51: iinc            5, 1
        //    54: nop            
        //    55: aload_2        
        //    56: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    59: astore_3       
        //    60: invokestatic    r/k/d/m/o.lc:()Ljava/util/List;
        //    63: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    68: astore          4
        //    70: aload           4
        //    72: invokeinterface java/util/Iterator.hasNext:()Z
        //    77: ifeq            207
        //    80: aload           4
        //    82: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    87: checkcast       Lr/k/d/m/g;
        //    90: astore          5
        //    92: aload_3        
        //    93: aload           5
        //    95: getfield        r/k/d/m/g.cm:Ljava/lang/String;
        //    98: ldc             " "
        //   100: ldc             ""
        //   102: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   105: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   108: ifeq            206
        //   111: aload           5
        //   113: getfield        r/k/d/m/g.as:Lr/k/d/o/m;
        //   116: aload           5
        //   118: getfield        r/k/d/m/g.as:Lr/k/d/o/m;
        //   121: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   124: checkcast       Ljava/lang/Boolean;
        //   127: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   130: ifne            135
        //   133: iconst_1       
        //   134: nop            
        //   135: iconst_0       
        //   136: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   139: invokevirtual   r/k/d/o/m.d:(Ljava/lang/Object;)V
        //   142: ldc             "not "
        //   144: astore          6
        //   146: aload           5
        //   148: getfield        r/k/d/m/g.as:Lr/k/d/o/m;
        //   151: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   154: checkcast       Ljava/lang/Boolean;
        //   157: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   160: ifeq            167
        //   163: ldc             ""
        //   165: astore          6
        //   167: new             Ljava/lang/StringBuilder;
        //   170: dup            
        //   171: invokespecial   java/lang/StringBuilder.<init>:()V
        //   174: ldc             "Hack '"
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: aload_3        
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: ldc             "' is now "
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: aload           6
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: ldc             "drawn"
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   204: iconst_1       
        //   205: ireturn        
        //   206: nop            
        //   207: new             Ljava/lang/StringBuilder;
        //   210: dup            
        //   211: invokespecial   java/lang/StringBuilder.<init>:()V
        //   214: ldc             "Could not find hack '"
        //   216: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   219: aload_3        
        //   220: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   223: ldc             "'"
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   231: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   234: iconst_0       
        //   235: ireturn        
        //    StackMapTable: 00 0C FF 00 04 00 0A 07 00 1D 07 00 1F 00 00 00 00 00 00 00 01 00 00 01 FF 00 0E 00 0A 07 00 1D 07 00 1F 00 00 00 00 00 01 01 01 00 00 FF 00 10 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 1F 01 01 00 01 01 01 00 00 FF 00 0C 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 1F 01 01 07 00 10 01 01 01 00 00 FF 00 03 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 1F 01 01 00 01 01 01 00 00 FF 00 0E 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 00 00 01 01 01 00 00 FF 00 40 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 07 00 66 00 01 01 01 00 01 07 00 68 FF 00 00 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 07 00 66 00 01 01 01 00 02 07 00 68 01 FF 00 1E 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 07 00 66 07 00 10 01 01 01 00 00 FF 00 26 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 07 00 66 00 01 01 01 00 00 FF 00 00 00 0A 07 00 1D 07 00 1F 07 00 29 07 00 10 07 00 3D 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String k() {
        return "-drawn Twerk";
    }
}
