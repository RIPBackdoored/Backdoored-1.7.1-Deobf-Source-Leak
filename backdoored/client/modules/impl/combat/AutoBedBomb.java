package r.k.d.m.combat;

import net.minecraft.util.math.BlockPos;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import r.k.b.c.h;
import r.k.b.b;
import net.minecraft.init.Items;
import r.k.d.o.h.i.t;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Auto Bed Bomb", description = "Automatically suicide bomb with beds", category = y.COMBAT)
@g$i(name = "Auto Bed Bomb", description = "Automatically suicide bomb with beds", category = y.COMBAT)
public class AutoBedBomb extends g
{
    private final m<Float> sbo;
    public static final int sbt;
    public static final boolean sbn;
    private final m<Float> sbo;
    public static final int sbt;
    public static final boolean sbn;
    
    public AutoBedBomb() {
        super();
        this.sbo = (m<Float>)new t("Range", this, 5.0f, 1.0f, 10.0f);
    }
    
    public void v() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //    StackMapTable: 00 03 FF 00 04 00 06 07 00 21 00 00 00 00 01 00 00 00 FF 00 00 00 06 07 00 21 00 00 00 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public AutoBedBomb() {
        super();
        this.sbo = (m<Float>)new t("Range", this, 5.0f, 1.0f, 10.0f);
    }
    
    public void v() {
    }
    
    private boolean yr() {
        if (AutoBedBomb.mc.objectMouseOver == null || AutoBedBomb.mc.objectMouseOver.sideHit == null) {
            return false;
        }
        final BlockPos offset = AutoBedBomb.mc.objectMouseOver.getBlockPos().offset(AutoBedBomb.mc.objectMouseOver.sideHit);
        final int s = b.s(Items.BED);
        if (s == -1) {
            h.o("A bed was not found in your hotbar!", "red");
            this.s(false);
            return false;
        }
        AutoBedBomb.mc.player.inventory.currentItem = s;
        b.y(offset);
        AutoBedBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(offset, EnumFacing.UP, EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
        return true;
    }
}
