package r.k.d.m.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.cr$h;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Speed", description = "Speeeeeeeeeeeeeeed", category = y.MOVEMENT)
public class Speed extends g
{
    private m<Boolean> sbm;
    public static final boolean sbh;
    public static final int swd;
    public static final boolean sws;
    
    public Speed() {
        super();
    }
    
    @SubscribeEvent
    public void d(final cr$h cr$h) {
        if (!this.lo()) {
            return;
        }
    }
    
    private void d(final double p0, final float p1, final double p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: aload_0        
        //     6: getfield        r/k/d/m/movement/Speed.sbm:Lr/k/d/o/m;
        //     9: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    12: checkcast       Ljava/lang/Boolean;
        //    15: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    18: ifne            35
        //    21: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    24: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    27: getfield        net/minecraft/client/entity/EntityPlayerSP.moveForward:F
        //    30: fconst_0       
        //    31: fcmpl          
        //    32: ifne            49
        //    35: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    38: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    41: getfield        net/minecraft/client/entity/EntityPlayerSP.moveForward:F
        //    44: fconst_0       
        //    45: fcmpl          
        //    46: ifle            51
        //    49: iconst_1       
        //    50: nop            
        //    51: iconst_0       
        //    52: istore          6
        //    54: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    57: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    60: getfield        net/minecraft/client/entity/EntityPlayerSP.moveStrafing:F
        //    63: fconst_0       
        //    64: fcmpl          
        //    65: ifeq            241
        //    68: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    71: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    74: iconst_1       
        //    75: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.setSprinting:(Z)V
        //    78: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    81: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    84: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //    87: ifeq            150
        //    90: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //    93: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    96: dload_1        
        //    97: putfield        net/minecraft/client/entity/EntityPlayerSP.motionY:D
        //   100: invokestatic    r/k/b/r.sf:()F
        //   103: fstore          7
        //   105: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   108: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   111: dup            
        //   112: getfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   115: fload           7
        //   117: invokestatic    net/minecraft/util/math/MathHelper.sin:(F)F
        //   120: fload_3        
        //   121: fmul           
        //   122: f2d            
        //   123: dsub           
        //   124: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   127: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   130: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   133: dup            
        //   134: getfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   137: fload           7
        //   139: invokestatic    net/minecraft/util/math/MathHelper.cos:(F)F
        //   142: fload_3        
        //   143: fmul           
        //   144: f2d            
        //   145: dadd           
        //   146: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   149: nop            
        //   150: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   153: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   156: getfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   159: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   162: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   165: getfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   168: dmul           
        //   169: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   172: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   175: getfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   178: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   181: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   184: getfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   187: dmul           
        //   188: dadd           
        //   189: invokestatic    java/lang/Math.sqrt:(D)D
        //   192: dstore          7
        //   194: invokestatic    r/k/b/r.sf:()F
        //   197: f2d            
        //   198: dstore          9
        //   200: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   203: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   206: dload           9
        //   208: invokestatic    java/lang/Math.sin:(D)D
        //   211: dneg           
        //   212: dload           4
        //   214: dmul           
        //   215: dload           7
        //   217: dmul           
        //   218: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   221: getstatic       r/k/d/m/movement/Speed.mc:Lnet/minecraft/client/Minecraft;
        //   224: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   227: dload           9
        //   229: invokestatic    java/lang/Math.cos:(D)D
        //   232: dload           4
        //   234: dmul           
        //   235: dload           7
        //   237: dmul           
        //   238: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   241: return         
        //    StackMapTable: 00 09 FF 00 04 00 0C 07 00 27 03 02 03 00 00 00 00 00 00 00 01 00 00 FF 00 00 00 0C 07 00 27 03 02 03 00 00 00 00 00 01 01 01 00 00 1D 0D 01 40 01 FF 00 0F 00 0C 07 00 27 03 02 03 01 00 00 00 00 01 01 01 00 00 FB 00 51 FB 00 5A
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void d(final double p0, final double p1, final EntityPlayerSP p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: getstatic       r/k/h.mc:Lnet/minecraft/client/Minecraft;
        //    10: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    13: getfield        net/minecraft/client/entity/EntityPlayerSP.movementInput:Lnet/minecraft/util/MovementInput;
        //    16: astore          6
        //    18: aload           6
        //    20: getfield        net/minecraft/util/MovementInput.moveForward:F
        //    23: fstore          7
        //    25: aload           6
        //    27: getfield        net/minecraft/util/MovementInput.moveStrafe:F
        //    30: fstore          8
        //    32: getstatic       r/k/h.mc:Lnet/minecraft/client/Minecraft;
        //    35: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    38: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //    41: fstore          9
        //    43: fload           7
        //    45: f2d            
        //    46: dconst_0       
        //    47: dcmpl          
        //    48: ifeq            133
        //    51: fload           8
        //    53: f2d            
        //    54: dconst_0       
        //    55: dcmpl          
        //    56: ifle            79
        //    59: fload           9
        //    61: fload           7
        //    63: f2d            
        //    64: dconst_0       
        //    65: dcmpl          
        //    66: ifle            72
        //    69: bipush          -45
        //    71: nop            
        //    72: bipush          45
        //    74: i2f            
        //    75: fadd           
        //    76: fstore          9
        //    78: nop            
        //    79: fload           8
        //    81: f2d            
        //    82: dconst_0       
        //    83: dcmpg          
        //    84: ifge            106
        //    87: fload           9
        //    89: fload           7
        //    91: f2d            
        //    92: dconst_0       
        //    93: dcmpl          
        //    94: ifle            100
        //    97: bipush          45
        //    99: nop            
        //   100: bipush          -45
        //   102: i2f            
        //   103: fadd           
        //   104: fstore          9
        //   106: fconst_0       
        //   107: fstore          8
        //   109: fload           7
        //   111: f2d            
        //   112: dconst_0       
        //   113: dcmpl          
        //   114: ifle            121
        //   117: fconst_1       
        //   118: fstore          7
        //   120: nop            
        //   121: fload           7
        //   123: f2d            
        //   124: dconst_0       
        //   125: dcmpg          
        //   126: ifge            133
        //   129: ldc             -1.0
        //   131: fstore          7
        //   133: fload           8
        //   135: f2d            
        //   136: dconst_0       
        //   137: dcmpl          
        //   138: ifle            145
        //   141: fconst_1       
        //   142: fstore          8
        //   144: nop            
        //   145: fload           8
        //   147: f2d            
        //   148: dconst_0       
        //   149: dcmpg          
        //   150: ifge            157
        //   153: ldc             -1.0
        //   155: fstore          8
        //   157: aload           5
        //   159: dload_1        
        //   160: fload           7
        //   162: f2d            
        //   163: ldc2_w          0.2
        //   166: dmul           
        //   167: fload           9
        //   169: ldc             90.0
        //   171: fadd           
        //   172: f2d            
        //   173: invokestatic    java/lang/Math.toRadians:(D)D
        //   176: invokestatic    java/lang/Math.cos:(D)D
        //   179: dmul           
        //   180: fload           8
        //   182: f2d            
        //   183: ldc2_w          0.2
        //   186: dmul           
        //   187: fload           9
        //   189: ldc             90.0
        //   191: fadd           
        //   192: f2d            
        //   193: invokestatic    java/lang/Math.toRadians:(D)D
        //   196: invokestatic    java/lang/Math.sin:(D)D
        //   199: dmul           
        //   200: dadd           
        //   201: dadd           
        //   202: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   205: aload           5
        //   207: dload_3        
        //   208: fload           7
        //   210: f2d            
        //   211: ldc2_w          0.2
        //   214: dmul           
        //   215: fload           9
        //   217: ldc             90.0
        //   219: fadd           
        //   220: f2d            
        //   221: invokestatic    java/lang/Math.toRadians:(D)D
        //   224: invokestatic    java/lang/Math.sin:(D)D
        //   227: dmul           
        //   228: fload           8
        //   230: f2d            
        //   231: ldc2_w          0.2
        //   234: dmul           
        //   235: fload           9
        //   237: ldc             90.0
        //   239: fadd           
        //   240: f2d            
        //   241: invokestatic    java/lang/Math.toRadians:(D)D
        //   244: invokestatic    java/lang/Math.cos:(D)D
        //   247: dmul           
        //   248: dsub           
        //   249: dadd           
        //   250: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   253: return         
        //    StackMapTable: 00 0D FF 00 04 00 0B 07 00 27 03 03 07 00 48 00 00 00 00 00 00 01 00 00 00 FF 00 01 00 0B 07 00 27 03 03 07 00 48 00 00 00 00 01 01 01 00 00 FF 00 40 00 0B 07 00 27 03 03 07 00 48 07 00 83 02 02 02 01 01 01 00 01 02 FF 00 01 00 0B 07 00 27 03 03 07 00 48 07 00 83 02 02 02 01 01 01 00 02 02 01 04 54 02 FF 00 01 00 0B 07 00 27 03 03 07 00 48 07 00 83 02 02 02 01 01 01 00 02 02 01 03 0E 0B 0B 0B
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
