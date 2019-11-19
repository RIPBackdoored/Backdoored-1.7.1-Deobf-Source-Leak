package r.k.d.m.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import r.k.d.o.h.i.t;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Pull Down", description = "Fast fall", category = y.MOVEMENT)
public class PullDown extends g
{
    private boolean slg;
    private m<Float> slu;
    public static final boolean slz;
    public static final int slc;
    public static final boolean sla;
    
    public PullDown() {
        super();
        this.slg = false;
        this.slu = (m<Float>)new t("Speed", this, 10.0f, 0.0f, 20.0f);
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
        //     8: getfield        r/k/d/m/movement/PullDown.slg:Z
        //    11: ifeq            31
        //    14: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    17: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    20: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //    23: ifeq            31
        //    26: aload_0        
        //    27: iconst_0       
        //    28: putfield        r/k/d/m/movement/PullDown.slg:Z
        //    31: aload_0        
        //    32: invokevirtual   r/k/d/m/movement/PullDown.lo:()Z
        //    35: ifeq            65
        //    38: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    41: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    44: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isElytraFlying:()Z
        //    47: ifne            65
        //    50: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    53: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    56: getfield        net/minecraft/client/entity/EntityPlayerSP.capabilities:Lnet/minecraft/entity/player/PlayerCapabilities;
        //    59: getfield        net/minecraft/entity/player/PlayerCapabilities.isFlying:Z
        //    62: ifeq            66
        //    65: return         
        //    66: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    69: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    72: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    75: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    78: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getPosition:()Lnet/minecraft/util/math/BlockPos;
        //    81: iconst_0       
        //    82: iconst_m1      
        //    83: iconst_0       
        //    84: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    87: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    90: ifeq            121
        //    93: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //    96: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    99: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //   102: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   105: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getPosition:()Lnet/minecraft/util/math/BlockPos;
        //   108: iconst_0       
        //   109: bipush          -2
        //   111: iconst_0       
        //   112: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   115: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   118: ifne            123
        //   121: iconst_1       
        //   122: nop            
        //   123: iconst_0       
        //   124: istore_1       
        //   125: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //   128: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   131: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   134: ifne            161
        //   137: getstatic       r/k/d/m/movement/PullDown.mc:Lnet/minecraft/client/Minecraft;
        //   140: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   143: aload_0        
        //   144: getfield        r/k/d/m/movement/PullDown.slu:Lr/k/d/o/m;
        //   147: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //   150: checkcast       Ljava/lang/Float;
        //   153: invokevirtual   java/lang/Float.floatValue:()F
        //   156: fneg           
        //   157: f2d            
        //   158: putfield        net/minecraft/client/entity/EntityPlayerSP.motionY:D
        //   161: return         
        //    StackMapTable: 00 09 FF 00 04 00 05 07 00 28 00 00 00 01 00 00 00 FF 00 19 00 05 07 00 28 00 01 01 01 00 00 21 00 36 01 40 01 FF 00 24 00 05 07 00 28 01 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @SubscribeEvent
    public void d(final LivingEvent.LivingJumpEvent livingJumpEvent) {
        if (livingJumpEvent.getEntityLiving().equals((Object)PullDown.mc.player)) {
            this.slg = true;
        }
    }
}
