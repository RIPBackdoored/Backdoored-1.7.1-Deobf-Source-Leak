package r.k;

import \u0000r.\u0000k.\u0000d.\u0000m.\u0000g;
import r.k.r.ct;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import r.k.n.r;
import r.k.n.k;
import r.k.n.q;
import java.util.Set;
import r.k.r.z$h;
import net.minecraft.world.border.WorldBorder;
import r.k.d.m.m.x;
import r.k.d.m.p.w;
import r.k.d.m.m.z;
import r.k.d.m.b.i;
import r.k.d.m.p.h;
import r.k.d.m.m.g;
import r.k.d.m.p.f;
import r.k.d.m.m.d;
import r.k.d.m.b.j;
import r.k.d.m.t.c;
import r.k.d.m.m.b;
import r.k.d.m.p.s;
import r.k.d.m.q.l;
import net.minecraftforge.fml.common.eventhandler.Event;
import r.k.r.z$y;
import r.k.c.o.p;
import r.k.c.m;
import org.lwjgl.opengl.Display;
import \u0000r.\u0000k.\u0000u;
import r.k.w.n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import r.k.b.m.y;
import java.io.File;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import r.k.p.o;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "backdoored", version = "1.7.1", clientSideOnly = true)
public class u
{
    public static final String lso;
    public static final String lst;
    public static String lsn;
    private static Boolean lsi;
    public static o lsp;
    static String lsr;
    public static final int lsf;
    public static final boolean lsb;
    
    public u() {
        super();
    }
    
    public static boolean ky() {
        if (u.lsi != null) {
            return u.lsi;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader("Backdoored/options.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("dev.enable.debugger")) {
                    return true;
                }
            }
            bufferedReader.close();
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    @Mod.EventHandler
    public void d(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        FMLLog.log.info("Loading backdoored...");
        t.sg();
        final File file = new File("Backdoored");
        if (!file.exists()) {
            file.mkdir();
        }
        if (!a.kp()) {
            throw new y("Couldnt load license, invalid drm");
        }
    }
    
    @Mod.EventHandler
    public void d(final FMLInitializationEvent fmlInitializationEvent) {
        MinecraftForge.EVENT_BUS.register((Object)new r.k.w.o());
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.kq();
        this.kj();
        this.kv();
        this.kk();
        u.lsp = new o();
        System.out.println("Reading config");
        n.sz();
        Runtime.getRuntime().addShutdownHook(new Thread(\u0000u::ke));
        new r.k.b.i.y();
        kx();
    }
    
    public static void kx() {
        Display.setTitle("Backdoored 1.7.1");
    }
    
    private void kk() {
        m.d();
        MinecraftForge.EVENT_BUS.register((Object)new r.k.c.o.m());
        MinecraftForge.EVENT_BUS.register((Object)new p());
        MinecraftForge.EVENT_BUS.register((Object)new r.k.c.o.y());
    }
    
    private void kq() {
        MinecraftForge.EVENT_BUS.post((Event)new z$y());
        final Class[] array = { l.class, s.class, b.class, c.class, j.class, r.k.d.m.j.l.class, r.k.d.m.y.j.class, r.k.d.m.m.y.class, r.k.d.m.m.s.class, r.k.d.m.m.l.class, d.class, r.k.d.m.t.j.class, f.class, g.class, r.k.d.m.m.j.class, h.class, r.k.d.m.b.y.class, i.class, r.k.d.m.b.u.class, r.k.d.m.s.f.class, z.class, r.k.d.m.t.f.class, r.k.d.m.q.f.class, r.k.d.m.b.n.class, r.k.d.m.p.y.class, r.k.d.m.b.c.class, r.k.d.m.j.y.class, r.k.d.m.p.u.class, r.k.d.m.h.c.class, r.k.d.m.y.d.class, r.k.d.m.b.g.class, r.k.d.m.s.u.class, r.k.d.m.b.z.class, r.k.d.m.y.u.class, r.k.d.m.y.s.class, r.k.d.m.p.c.class, w.class, r.k.d.m.p.l.class, r.k.d.m.j.g.class, r.k.d.m.t.l.class, r.k.d.m.t.y.class, r.k.d.m.s.i.class, r.k.d.m.y.n.class, x.class, r.k.d.m.y.l.class, r.k.d.m.j.d.class, r.k.d.m.m.h.class, r.k.d.m.j.p.class, r.k.d.m.m.i.class, r.k.d.m.b.s.class, r.k.d.m.m.c.class, r.k.d.m.p.n.class, r.k.d.m.h.l.class, r.k.d.m.s.p.class, r.k.d.m.m.p.class, r.k.d.m.y.z.class, r.k.d.m.b.f.class, r.k.d.m.b.h.class, r.k.d.m.y.b.class, r.k.d.m.j.b.class, r.k.d.m.b.d.class, r.k.d.m.p.z.class, r.k.d.m.j.u.class, r.k.d.m.j.f.class, r.k.d.m.j.c.class, r.k.d.m.y.g.class, r.k.d.m.b.b.class, r.k.d.m.h.j.class, r.k.d.m.y.h.class, r.k.d.m.s.l.class, r.k.d.m.p.d.class, r.k.d.m.y.f.class, r.k.d.m.j.i.class, r.k.d.m.t.p.class, r.k.d.m.p.j.class, r.k.d.m.p.x.class, r.k.d.m.y.p.class, r.k.d.m.y.i.class, r.k.d.m.h.g.class, r.k.d.m.b.p.class, r.k.d.m.y.w.class, r.k.d.m.p.p.class, r.k.d.m.s.g.class, r.k.d.m.m.w.class, r.k.d.m.m.f.class, r.k.d.m.h.p.x.class, r.k.d.m.h.y.class, r.k.d.m.y.x.class, r.k.d.m.t.i.class, r.k.d.m.m.u.class, WorldBorder.class, r.k.d.m.h.u.x.class, r.k.d.m.h.d.class };
        final Set<Class> yp = new r.k.s.m.h().d(array).yi().yp();
        FMLLog.log.info("Backdoored tried to load " + array.length + " hacks, out of which " + yp.size() + " failed");
        FMLLog.log.info("Failed hack: " + yp.toString());
        MinecraftForge.EVENT_BUS.post((Event)new z$h(r.k.d.m.o.lc()));
        FMLLog.log.info("Backdoored startup finished ");
    }
    
    private void kv() {
        final Class[] array = { r.k.n.s.class, q.class, r.k.n.o.class, r.k.n.j.class, r.k.n.z.class, r.k.n.g.class, r.k.n.u.class, r.k.n.x.class, r.k.n.a.class, r.k.n.h.class, k.class, r.k.n.n.class, r.class, r.k.n.t.class, r.k.n.b.class, r.k.n.d.class, r.k.n.m.class };
        final Set<Class> yp = new r.k.s.m.h().d(array).yi().yp();
        FMLLog.log.info("Backdoored tried to load " + array.length + " commands, out of which " + yp.size() + " failed");
        FMLLog.log.info("Failed commands: " + yp.toString());
        MinecraftForge.EVENT_BUS.register((Object)new r.k.n.i());
    }
    
    private void kj() {
        MinecraftForge.EVENT_BUS.register((Object)new r.k.d.o.p());
    }
    
    @SubscribeEvent
    public void y(final ClientChatReceivedEvent clientChatReceivedEvent) {
        u.lsr = clientChatReceivedEvent.getMessage().getUnformattedText();
    }
    
    @SubscribeEvent
    public void y(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiMainMenu) {
            guiOpenEvent.setGui((GuiScreen)new r.k.e.k());
        }
    }
    
    @SubscribeEvent
    public void l(final r.k.r.h.p p) {
        if (p.packet instanceof SPacketTimeUpdate) {
            MinecraftForge.EVENT_BUS.post((Event)new ct());
        }
    }
    
    private static /* synthetic */ void ke() {
        System.out.println("System shutdown, saving configs");
        r.k.d.m.o.lc().forEach(\u0000g::lt);
        r.k.b.i.p.kr();
        n.sc();
    }
    
    static {
        lst = "1.7.1";
        lso = "backdoored";
        u.lsn = "";
        u.lsi = null;
        u.lsr = "";
    }
}
