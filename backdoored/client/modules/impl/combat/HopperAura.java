package r.k.d.m.combat;

import net.minecraft.tileentity.TileEntityHopper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import java.util.Iterator;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import r.k.b.b;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000m.\u0000i;
import java.util.List;
import r.k.d.o.h.p;
import r.k.d.o.h.i.s;
import r.k.d.m.m.i;
import r.k.d.o.m;
import net.minecraft.util.math.BlockPos;
import java.util.Set;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "HopperAura", description = "Break nearby hoppers", category = y.COMBAT)
public class HopperAura extends g
{
    private Set<BlockPos> lt;
    private int[] ln;
    private m<Double> li;
    private m<Boolean> lp;
    private m<Boolean> lr;
    public static final boolean lf;
    public static final int lb;
    public static final boolean lw;
    
    public HopperAura() {
        super();
        this.lt = new i$s((i)this);
        this.ln = new int[] { 278, 285, 274, 270, 257 };
        this.li = (m<Double>)new s("Distance", this, 5.0, 1.0, 10.0);
        this.lp = (m<Boolean>)new p("LockRotations", this, true);
    }
    
    public void j() {
        if (!this.lo()) {
            return;
        }
        final List list = (List)HopperAura.mc.world.loadedTileEntityList.stream().filter(\u0000i::d).collect(Collectors.<Object>toList());
        if (list.size() > 0) {
            final Iterator<TileEntity> iterator = list.iterator();
            if (iterator.hasNext()) {
                final TileEntity tileEntity = iterator.next();
                final BlockPos pos = tileEntity.getPos();
                if (this.lr.yv() || this.lt.contains(pos)) {}
                if (HopperAura.mc.player.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) <= this.li.yv()) {
                    final int[] ln = this.ln;
                    final int length = ln.length;
                    int n = 0;
                    if (n < length) {
                        final int s = b.s(Item.getItemById(ln[n]));
                        if (s != -1) {
                            HopperAura.mc.player.inventory.currentItem = s;
                            if (this.lp.yv()) {
                                b.x(pos);
                            }
                            HopperAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, tileEntity.getPos(), EnumFacing.UP));
                            HopperAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, tileEntity.getPos(), EnumFacing.UP));
                            return;
                        }
                        ++n;
                    }
                }
            }
        }
    }
    
    public void t() {
        this.lt.clear();
    }
    
    @SubscribeEvent
    public void d(final PlayerInteractEvent.RightClickBlock rightClickBlock) {
        if (HopperAura.mc.player.inventory.getStackInSlot(HopperAura.mc.player.inventory.currentItem).getItem().equals(Item.getItemById(154))) {
            this.lt.add(HopperAura.mc.objectMouseOver.getBlockPos().offset(HopperAura.mc.objectMouseOver.sideHit));
        }
    }
    
    private static /* synthetic */ boolean d(final TileEntity tileEntity) {
        return tileEntity instanceof TileEntityHopper;
    }
}
