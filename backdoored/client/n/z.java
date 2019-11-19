package r.k.n;

public class z extends c
{
    public static final boolean wp;
    public static final int wr;
    public static final boolean wf;
    public static final boolean wp;
    public static final int wr;
    public static final boolean wf;
    
    public z() {
        super(new String[] { "spectate", "view", "watch", "possess" });
    }
    
    @Override
    public boolean d(final String[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Could not show original bytecode, likely due to the same error.
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public z() {
        super(new String[] { "spectate", "view", "watch", "possess" });
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
        //     9: iconst_0       
        //    10: aaload         
        //    11: ldc             "off"
        //    13: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    16: ifne            30
        //    19: aload_1        
        //    20: iconst_0       
        //    21: aaload         
        //    22: ldc             "self"
        //    24: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    27: ifeq            53
        //    30: aload_0        
        //    31: getfield        r/k/n/z.mc:Lnet/minecraft/client/Minecraft;
        //    34: aload_0        
        //    35: getfield        r/k/n/z.mc:Lnet/minecraft/client/Minecraft;
        //    38: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    41: invokevirtual   net/minecraft/client/Minecraft.setRenderViewEntity:(Lnet/minecraft/entity/Entity;)V
        //    44: ldc             "Now viewing from own perspective"
        //    46: ldc             "green"
        //    48: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //    51: iconst_1       
        //    52: ireturn        
        //    53: aload_2        
        //    54: invokeinterface java/util/Iterator.hasNext:()Z
        //    59: ifeq            128
        //    62: aload_2        
        //    63: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    68: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //    71: astore_3       
        //    72: aload_3        
        //    73: invokevirtual   net/minecraft/entity/player/EntityPlayer.getDisplayNameString:()Ljava/lang/String;
        //    76: aload_1        
        //    77: iconst_0       
        //    78: aaload         
        //    79: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    82: ifeq            127
        //    85: aload_0        
        //    86: getfield        r/k/n/z.mc:Lnet/minecraft/client/Minecraft;
        //    89: aload_3        
        //    90: invokevirtual   net/minecraft/client/Minecraft.setRenderViewEntity:(Lnet/minecraft/entity/Entity;)V
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: ldc             "Now viewing from perspective of '"
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload_3        
        //   106: invokevirtual   net/minecraft/entity/player/EntityPlayer.getDisplayNameString:()Ljava/lang/String;
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   112: ldc             "'"
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   120: ldc             "green"
        //   122: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //   125: iconst_1       
        //   126: ireturn        
        //   127: nop            
        //   128: new             Ljava/lang/StringBuilder;
        //   131: dup            
        //   132: invokespecial   java/lang/StringBuilder.<init>:()V
        //   135: ldc             "Couldnt find player '"
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: aload_1        
        //   141: iconst_0       
        //   142: aaload         
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: ldc             "'"
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: invokestatic    r/k/b/c/h.sj:(Ljava/lang/String;)V
        //   157: nop            
        //   158: astore_2       
        //   159: new             Ljava/lang/StringBuilder;
        //   162: dup            
        //   163: invokespecial   java/lang/StringBuilder.<init>:()V
        //   166: ldc             "Error: "
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: aload_2        
        //   172: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   181: ldc             "red"
        //   183: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //   186: aload_2        
        //   187: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   190: iconst_0       
        //   191: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      52     158    192    Ljava/lang/Exception;
        //  53     53     158    192    Ljava/lang/Exception;
        //  53     126    158    192    Ljava/lang/Exception;
        //  127    128    158    192    Ljava/lang/Exception;
        //  128    157    158    192    Ljava/lang/Exception;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String k() {
        return "-spectate <playername/self>";
    }
}
