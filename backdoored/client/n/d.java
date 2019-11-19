package r.k.n;

import java.util.HashMap;
import r.k.b.b;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.util.math.BlockPos;
import r.k.b.u.p;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Iterator;
import r.k.b.c.h;
import net.minecraft.util.math.Vec3d;
import java.util.ArrayList;
import java.util.Map;

public class d extends c
{
    private static int lsz;
    private static final int lsc;
    private static final int lsa;
    private static final int lsm;
    private static final int lsh;
    private static Map<String, ArrayList<Vec3d>> lld;
    private static ArrayList<Vec3d> lls;
    private String lll;
    private ArrayList<Vec3d> lly;
    private int llx;
    private int llk;
    public static final boolean llq;
    public static final int llv;
    public static final boolean llj;
    
    public d() {
        super(new String[] { "build", "builder", "br" });
        this.lly = new ArrayList<Vec3d>();
        this.llx = 0;
        this.llk = 6;
    }
    
    @Override
    public String k() {
        return "-builder/br <mode> [arg]";
    }
    
    @Override
    public boolean d(final String[] array) {
        if (array.length == 1) {
            if (array[0].equalsIgnoreCase("stop")) {
                if (this.lly.size() > 0) {
                    d.lld.put(this.lll, this.lly);
                }
                this.lly.clear();
                d.lsz = 0;
                return true;
            }
            if (array[0].equalsIgnoreCase("list")) {
                final StringBuilder sb = new StringBuilder("Available configs: ");
                final Iterator<String> iterator = d.lld.keySet().iterator();
                if (iterator.hasNext()) {
                    sb.append(iterator.next());
                    sb.append(" ");
                }
                h.o(sb.toString(), "red");
                return true;
            }
            if (d.lld.containsKey(array[0])) {
                d.lls = d.lld.get(array[0]);
                d.lsz = 2;
                return true;
            }
        }
        if (array.length == 2) {
            if (array[0].equalsIgnoreCase("record")) {
                this.lll = array[1];
                this.lly.clear();
                d.lsz = 1;
                return true;
            }
            if (array[0].equalsIgnoreCase("loop")) {
                if (d.lld.containsKey(array[1])) {
                    d.lls = d.lld.get(array[1]);
                    d.lsz = 3;
                    return true;
                }
                h.o("Config not found! Use 'br list' to see all configs", "red");
                return false;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void s(final TickEvent.ClientTickEvent clientTickEvent) {
        this.llx = 0;
    }
    
    private void ko() {
        p.d(0.0f, 255.0f, 0.0f, 1.0f);
        final Vec3d vec3d = this.lly.iterator().next();
        p.l(p.k(new BlockPos(this.mc.player.getPositionVector().add(vec3d).x, this.mc.player.getPositionVector().add(vec3d).y, this.mc.player.getPositionVector().add(vec3d).z)));
        p.xn();
    }
    
    @SubscribeEvent
    public void s(final PlayerInteractEvent.RightClickBlock rightClickBlock) {
        if (d.lsz == 1) {
            this.mc.objectMouseOver.getBlockPos().offset(this.mc.objectMouseOver.sideHit);
            h.sj("added block" + this.lly.get(this.lly.size() - 1).toString());
        }
    }
    
    private void kt() {
        if (this.llx % this.llk != 0) {
            ++this.llx;
            return;
        }
        final int currentItem = this.mc.player.inventory.currentItem;
        final int xx = b.xx();
        if (xx == -1) {
            d.lsz = 0;
            return;
        }
        this.mc.player.inventory.currentItem = xx;
        final Iterator<BlockPos> iterator = this.ki().iterator();
        if (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            if (this.mc.world.getBlockState(blockPos).getMaterial().isReplaceable()) {
                b.y(blockPos);
                h.sj("place");
                ++this.llx;
                return;
            }
        }
        this.mc.player.inventory.currentItem = currentItem;
        d.lsz = 0;
    }
    
    private void kn() {
        if (this.llx % this.llk != 0) {
            ++this.llx;
            return;
        }
        final int currentItem = this.mc.player.inventory.currentItem;
        final int xx = b.xx();
        if (xx == -1) {
            d.lsz = 0;
            h.o("Blocks were not found in your hotbar!", "red");
            return;
        }
        this.mc.player.inventory.currentItem = xx;
        final Iterator<BlockPos> iterator = this.ki().iterator();
        if (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            if (this.mc.world.getBlockState(blockPos).getMaterial().isReplaceable()) {
                b.y(blockPos);
                h.sj("place");
                ++this.llx;
                return;
            }
        }
        this.mc.player.inventory.currentItem = currentItem;
    }
    
    private ArrayList<BlockPos> ki() {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final Iterator<Vec3d> iterator = d.lls.iterator();
        if (iterator.hasNext()) {
            list.add(new BlockPos(this.mc.player.getPositionVector().add((Vec3d)iterator.next())));
        }
        return list;
    }
    
    static {
        lsh = 3;
        lsm = 2;
        lsa = 1;
        lsc = 0;
        d.lsz = 0;
        d.lld = new HashMap<String, ArrayList<Vec3d>>();
        d.lls = new ArrayList<Vec3d>();
    }
}
