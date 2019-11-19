package r.k.d.m.client;

import net.minecraft.nbt.NBTTagCompound;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import r.k.b.t;
import r.k.b.c.h;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.function.Predicate;
import java.util.function.Function;
import \u0000r.\u0000k.\u0000d.\u0000m.\u0000b.\u0000w;
import net.minecraft.network.play.server.SPacketChunkData;
import r.k.d.o.h.p;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Chunk Logger", description = "Save chunks with chests in them to a text file", category = y.CLIENT)
public class ChunkLogger extends g
{
    private final m<Integer> scl;
    private final m<Boolean> scy;
    private final m<Boolean> scx;
    public static final boolean sck;
    public static final int scq;
    public static final boolean scv;
    
    public ChunkLogger() {
        super();
        this.scl = (m<Integer>)new x("Min Chests", this, 10, 1, 50);
        this.scy = (m<Boolean>)new p("Save to File", this, true);
        this.scx = (m<Boolean>)new p("Notifications", this, true);
    }
    
    @SubscribeEvent
    public void s(final r.k.r.h.p p) {
        if (this.lo() && p.packet instanceof SPacketChunkData) {
            final SPacketChunkData sPacketChunkData = (SPacketChunkData)p.packet;
            final long count = sPacketChunkData.getTileEntityTags().stream().map(\u0000w::d).filter(\u0000w::sv).count();
            if (count >= this.scl.yv()) {
                this.d(sPacketChunkData, count);
            }
        }
    }
    
    private void d(final SPacketChunkData sPacketChunkData, final long n) {
        final String string = "(" + sPacketChunkData.getChunkX() * 16 + ", " + sPacketChunkData.getChunkZ() * 16 + "):" + n + " chests";
        h.sj(string);
        if (this.scx.yv()) {
            t.d("Visual Range", string);
        }
        new Thread(this::sq, "Backdoored Chunk Logger File Saving Thread").start();
    }
    
    private /* synthetic */ void sq(final String s) {
        if (this.scy.yv()) {
            try {
                Files.write(Paths.get("Backdoored/loggedchunks.txt", new String[0]), s.getBytes(), StandardOpenOption.APPEND);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private static /* synthetic */ void d(final Exception ex) {
    }
    
    private static /* synthetic */ boolean sv(final String s) {
        return s.equals("minecraft:chest");
    }
    
    private static /* synthetic */ String d(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound.getString("id");
    }
}
