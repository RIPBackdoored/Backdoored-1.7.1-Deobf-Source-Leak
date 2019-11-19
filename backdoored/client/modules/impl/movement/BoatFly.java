package r.k.d.m.movement;

import net.minecraft.util.math.MathHelper;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "BoatFly", description = "Experimental boatfly", category = y.MOVEMENT)
public class BoatFly extends g
{
    public static final boolean stx;
    public static final int stk;
    public static final boolean stq;
    
    public BoatFly() {
        super();
    }
    
    public void j() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: nop            
        //     7: aload_0        
        //     8: invokevirtual   r/k/d/m/movement/BoatFly.lo:()Z
        //    11: ifne            15
        //    14: return         
        //    15: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //    18: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    21: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isRiding:()Z
        //    24: ifeq            650
        //    27: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //    30: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //    33: getfield        net/minecraft/client/settings/GameSettings.keyBindForward:Lnet/minecraft/client/settings/KeyBinding;
        //    36: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //    39: istore_2       
        //    40: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //    43: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //    46: getfield        net/minecraft/client/settings/GameSettings.keyBindLeft:Lnet/minecraft/client/settings/KeyBinding;
        //    49: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //    52: istore_3       
        //    53: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //    56: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //    59: getfield        net/minecraft/client/settings/GameSettings.keyBindRight:Lnet/minecraft/client/settings/KeyBinding;
        //    62: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //    65: istore          4
        //    67: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //    70: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //    73: getfield        net/minecraft/client/settings/GameSettings.keyBindBack:Lnet/minecraft/client/settings/KeyBinding;
        //    76: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //    79: istore          5
        //    81: iload_3        
        //    82: ifeq            108
        //    85: iload           4
        //    87: ifeq            108
        //    90: iload_2        
        //    91: ifeq            96
        //    94: iconst_0       
        //    95: nop            
        //    96: iload           5
        //    98: ifeq            105
        //   101: sipush          180
        //   104: nop            
        //   105: iconst_m1      
        //   106: istore_1       
        //   107: nop            
        //   108: iload_2        
        //   109: ifeq            135
        //   112: iload           5
        //   114: ifeq            135
        //   117: iload_3        
        //   118: ifeq            124
        //   121: bipush          -90
        //   123: nop            
        //   124: iload           4
        //   126: ifeq            132
        //   129: bipush          90
        //   131: nop            
        //   132: iconst_m1      
        //   133: istore_1       
        //   134: nop            
        //   135: iload_3        
        //   136: ifeq            142
        //   139: bipush          -90
        //   141: nop            
        //   142: iload           4
        //   144: ifeq            150
        //   147: bipush          90
        //   149: nop            
        //   150: iconst_0       
        //   151: istore_1       
        //   152: iload_2        
        //   153: ifeq            161
        //   156: iload_1        
        //   157: iconst_2       
        //   158: idiv           
        //   159: istore_1       
        //   160: nop            
        //   161: iload           5
        //   163: ifeq            174
        //   166: sipush          180
        //   169: iload_1        
        //   170: iconst_2       
        //   171: idiv           
        //   172: isub           
        //   173: istore_1       
        //   174: iload_1        
        //   175: iconst_m1      
        //   176: if_icmpeq       242
        //   179: iload           5
        //   181: ifeq            242
        //   184: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   187: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   190: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   193: iload_1        
        //   194: i2f            
        //   195: fadd           
        //   196: fstore          6
        //   198: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   201: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   204: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   207: aload_0        
        //   208: fload           6
        //   210: invokevirtual   r/k/d/m/movement/BoatFly.d:(F)D
        //   213: ldc2_w          0.20000000298023224
        //   216: dmul           
        //   217: putfield        net/minecraft/entity/Entity.motionX:D
        //   220: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   223: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   226: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   229: aload_0        
        //   230: fload           6
        //   232: invokevirtual   r/k/d/m/movement/BoatFly.s:(F)D
        //   235: ldc2_w          0.20000000298023224
        //   238: dmul           
        //   239: putfield        net/minecraft/entity/Entity.motionZ:D
        //   242: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   245: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   248: dconst_0       
        //   249: putfield        net/minecraft/client/entity/EntityPlayerSP.motionY:D
        //   252: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   255: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   258: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   261: dconst_0       
        //   262: putfield        net/minecraft/entity/Entity.motionY:D
        //   265: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   268: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   271: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   274: new             Lnet/minecraft/network/play/client/CPacketPlayer$PositionRotation;
        //   277: dup            
        //   278: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   281: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   284: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   287: getfield        net/minecraft/entity/Entity.posX:D
        //   290: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   293: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   296: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   299: getfield        net/minecraft/entity/Entity.motionX:D
        //   302: dadd           
        //   303: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   306: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   309: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   312: getfield        net/minecraft/entity/Entity.posY:D
        //   315: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   318: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   321: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   324: getfield        net/minecraft/entity/Entity.posZ:D
        //   327: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   330: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   333: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   336: getfield        net/minecraft/entity/Entity.motionZ:D
        //   339: dadd           
        //   340: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   343: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   346: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   349: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   352: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   355: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationPitch:F
        //   358: iconst_0       
        //   359: invokespecial   net/minecraft/network/play/client/CPacketPlayer$PositionRotation.<init>:(DDDFFZ)V
        //   362: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   365: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   368: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   371: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   374: dconst_0       
        //   375: putfield        net/minecraft/entity/Entity.motionY:D
        //   378: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   381: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   384: getfield        net/minecraft/client/settings/GameSettings.keyBindJump:Lnet/minecraft/client/settings/KeyBinding;
        //   387: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //   390: ifeq            408
        //   393: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   396: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   399: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   402: ldc2_w          0.3
        //   405: putfield        net/minecraft/entity/Entity.motionY:D
        //   408: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   411: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //   414: getfield        net/minecraft/client/settings/GameSettings.keyBindSprint:Lnet/minecraft/client/settings/KeyBinding;
        //   417: invokevirtual   net/minecraft/client/settings/KeyBinding.isKeyDown:()Z
        //   420: ifeq            438
        //   423: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   426: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   429: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   432: ldc2_w          -0.3
        //   435: putfield        net/minecraft/entity/Entity.motionY:D
        //   438: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   441: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   444: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   447: new             Lnet/minecraft/network/play/client/CPacketVehicleMove;
        //   450: dup            
        //   451: invokespecial   net/minecraft/network/play/client/CPacketVehicleMove.<init>:()V
        //   454: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   457: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   460: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   463: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   466: new             Lnet/minecraft/network/play/client/CPacketSteerBoat;
        //   469: dup            
        //   470: iconst_1       
        //   471: iconst_1       
        //   472: invokespecial   net/minecraft/network/play/client/CPacketSteerBoat.<init>:(ZZ)V
        //   475: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   478: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   481: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   484: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   487: new             Lnet/minecraft/network/play/client/CPacketPlayer$PositionRotation;
        //   490: dup            
        //   491: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   494: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   497: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   500: getfield        net/minecraft/entity/Entity.posX:D
        //   503: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   506: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   509: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   512: getfield        net/minecraft/entity/Entity.motionX:D
        //   515: dadd           
        //   516: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   519: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   522: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   525: getfield        net/minecraft/entity/Entity.posY:D
        //   528: ldc2_w          42069.0
        //   531: dsub           
        //   532: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   535: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   538: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   541: getfield        net/minecraft/entity/Entity.posZ:D
        //   544: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   547: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   550: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   553: getfield        net/minecraft/entity/Entity.motionZ:D
        //   556: dadd           
        //   557: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   560: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   563: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   566: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   569: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   572: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationPitch:F
        //   575: iconst_1       
        //   576: invokespecial   net/minecraft/network/play/client/CPacketPlayer$PositionRotation.<init>:(DDDFFZ)V
        //   579: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   582: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   585: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   588: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   591: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   594: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   597: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getRidingEntity:()Lnet/minecraft/entity/Entity;
        //   600: getfield        net/minecraft/entity/Entity.posY:D
        //   603: ldc2_w          42069.0
        //   606: dsub           
        //   607: putfield        net/minecraft/entity/Entity.posY:D
        //   610: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   613: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   616: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   619: new             Lnet/minecraft/network/play/client/CPacketVehicleMove;
        //   622: dup            
        //   623: invokespecial   net/minecraft/network/play/client/CPacketVehicleMove.<init>:()V
        //   626: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   629: getstatic       r/k/d/m/movement/BoatFly.mc:Lnet/minecraft/client/Minecraft;
        //   632: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   635: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //   638: new             Lnet/minecraft/network/play/client/CPacketSteerBoat;
        //   641: dup            
        //   642: iconst_1       
        //   643: iconst_1       
        //   644: invokespecial   net/minecraft/network/play/client/CPacketSteerBoat.<init>:(ZZ)V
        //   647: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //   650: return         
        //    StackMapTable: 00 16 FF 00 04 00 0A 07 00 25 00 00 00 00 00 00 00 00 01 00 00 00 FF 00 09 00 0A 07 00 25 00 00 00 00 00 00 01 01 01 00 00 FF 00 18 00 0A 07 00 25 00 01 00 00 00 00 01 01 01 00 00 FF 00 37 00 0A 07 00 25 00 01 01 01 01 00 01 01 01 00 00 08 40 01 01 0F 07 40 01 01 06 07 40 01 FF 00 09 00 0A 07 00 25 01 01 01 01 01 00 01 01 01 00 00 0C 09 39 FB 00 A5 1D FF 00 D3 00 0A 07 00 25 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private double[] q(final int n) {
        return new double[] { BoatFly.mc.player.rotationYaw, n };
    }
    
    public double d(final float n) {
        return MathHelper.sin(-n * 0.017453292f);
    }
    
    public double s(final float n) {
        return MathHelper.cos(n * 0.017453292f);
    }
}
