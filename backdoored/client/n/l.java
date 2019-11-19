package r.k.n;

public class l extends c
{
    public static final int ldh;
    public static final boolean lsd;
    
    public l() {
        super(new String[] { "getworldborder", "worldborder", "border" });
    }
    
    @Override
    public boolean d(final String[] array) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: aload_0        
        //     8: getfield        r/k/n/l.mc:Lnet/minecraft/client/Minecraft;
        //    11: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    14: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getWorldBorder:()Lnet/minecraft/world/border/WorldBorder;
        //    17: astore_2       
        //    18: aload_2        
        //    19: invokevirtual   net/minecraft/world/border/WorldBorder.maxX:()D
        //    22: dstore_3       
        //    23: aload_2        
        //    24: invokevirtual   net/minecraft/world/border/WorldBorder.minX:()D
        //    27: dstore          7
        //    29: aload_2        
        //    30: invokevirtual   net/minecraft/world/border/WorldBorder.minZ:()D
        //    33: dstore          9
        //    35: new             Ljava/lang/StringBuilder;
        //    38: dup            
        //    39: invokespecial   java/lang/StringBuilder.<init>:()V
        //    42: ldc             "World border is at:\nMinX: "
        //    44: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    47: dload           7
        //    49: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //    52: ldc             "\nMinZ: "
        //    54: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    57: dload           9
        //    59: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //    62: ldc             "\nMaxX: "
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: dload_3        
        //    68: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //    71: ldc             "\nMaxZ: "
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    76: dload           5
        //    78: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //    81: ldc             "\n"
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    89: ldc             "green"
        //    91: invokestatic    r/k/b/c/h.o:(Ljava/lang/String;Ljava/lang/String;)V
        //    94: iconst_1       
        //    95: ireturn        
        //    StackMapTable: 00 03 FF 00 04 00 0E 07 00 1C 07 00 1E 00 00 00 00 00 00 00 00 00 00 00 01 00 00 01 FF 00 10 00 0C 07 00 1C 07 00 1E 07 00 30 03 03 00 00 00 00 00 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public String k() {
        return "-worldborder";
    }
}
