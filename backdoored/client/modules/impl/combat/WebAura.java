package r.k.d.m.combat;

import java.util.Iterator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.List;
import r.k.b.b;
import net.minecraft.init.Blocks;
import r.k.d.o.h.i.s;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Web Aura", description = "Trap people camping in holes", category = y.COMBAT)
public class WebAura extends g
{
    private m<Double> vw;
    public static final boolean vg;
    public static final int vu;
    public static final boolean vz;
    
    public WebAura() {
        super();
        this.vw = (m<Double>)new s("Range", this, 4.0, 1.0, 10.0);
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final int s = b.s(Blocks.WEB);
        if (s == -1) {
            return;
        }
        final List list = (List)WebAura.mc.world.playerEntities.stream().filter(this::d).collect(Collectors.<Object>toList());
        if (list.size() > 0) {
            WebAura.mc.player.inventory.currentItem = s;
        }
        final Iterator<EntityPlayer> iterator = list.iterator();
        if (iterator.hasNext()) {
            final EntityPlayer entityPlayer = iterator.next();
            final BlockPos blockPos = new BlockPos((int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);
            if (WebAura.mc.world.getBlockState(blockPos).getMaterial().isReplaceable()) {
                b.y(blockPos);
            }
            if (WebAura.mc.world.getBlockState(blockPos.add(1, 0, 0)).getMaterial().isReplaceable()) {
                b.y(blockPos.add(1, 0, 0));
            }
            if (WebAura.mc.world.getBlockState(blockPos.add(0, 0, 1)).getMaterial().isReplaceable()) {
                b.y(blockPos.add(0, 0, 1));
            }
            if (WebAura.mc.world.getBlockState(blockPos.add(0, 0, -1)).getMaterial().isReplaceable()) {
                b.y(blockPos.add(0, 0, -1));
            }
            if (WebAura.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getMaterial().isReplaceable()) {
                b.y(blockPos.add(-1, 0, 0));
            }
        }
    }
    
    private /* synthetic */ boolean d(final EntityPlayer p0) {
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
        //     8: getstatic       r/k/d/m/combat/WebAura.mc:Lnet/minecraft/client/Minecraft;
        //    11: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    14: aload_1        
        //    15: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(Lnet/minecraft/entity/Entity;)F
        //    18: f2d            
        //    19: aload_0        
        //    20: getfield        r/k/d/m/combat/WebAura.vw:Lr/k/d/o/m;
        //    23: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    26: checkcast       Ljava/lang/Double;
        //    29: invokevirtual   java/lang/Double.doubleValue:()D
        //    32: dcmpg          
        //    33: ifgt            51
        //    36: getstatic       r/k/d/m/combat/WebAura.mc:Lnet/minecraft/client/Minecraft;
        //    39: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    42: aload_1        
        //    43: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.equals:(Ljava/lang/Object;)Z
        //    46: ifne            51
        //    49: iconst_1       
        //    50: nop            
        //    51: iconst_0       
        //    52: ireturn        
        //    StackMapTable: 00 05 FF 00 04 00 05 07 00 35 07 00 A0 00 00 01 00 00 01 FF 00 01 00 05 07 00 35 07 00 A0 01 01 01 00 00 2A 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
