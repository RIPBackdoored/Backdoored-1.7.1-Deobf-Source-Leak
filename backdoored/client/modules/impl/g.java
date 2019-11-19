package r.k.d.m;

import r.k.b.m.y;
import r.k.a;
import net.minecraftforge.fml.common.FMLLog;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.u;
import net.minecraftforge.fml.common.eventhandler.Event;
import r.k.r.s;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import r.k.d.o.h.f;
import java.util.Objects;
import r.k.d.o.m;
import r.k.d.c.h;
import net.minecraft.client.Minecraft;

public class g
{
    protected static final Minecraft mc;
    public final String cm;
    public final h ch;
    public final String ad;
    public final m<Boolean> as;
    public final m<String> al;
    private boolean ay;
    private boolean ax;
    public static final boolean ak;
    public static final int aq;
    public static final boolean av;
    
    public g() {
        super();
        this.cm = Objects.<String>requireNonNull(this.le().name());
        this.ch = Objects.<h>requireNonNull(this.le().category().xf);
        this.ad = this.le().description();
        this.s(this.le().defaultOn());
        this.al = (m<String>)new f("Bind", this, this.le().defaultBind());
        o.lc().add(this);
        o.ha.put(this.cm, this);
        this.ch.xy().add(this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    private g$i le() {
        return this.getClass().<g$i>getAnnotation(g$i.class);
    }
    
    public void s(final boolean ay) {
        this.ay = ay;
    }
    
    public static void d(final String s, final boolean ay) {
        o.ha.get(s).ay = ay;
    }
    
    public boolean lo() {
        return this.ax;
    }
    
    public static boolean w(final String s) {
        return o.ha.get(s).ax;
    }
    
    protected void j() {
    }
    
    protected void a() {
    }
    
    protected void m() {
    }
    
    protected void v() {
    }
    
    protected void t() {
        r();
    }
    
    public void lt() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public boolean ln() {
        return this.ay;
    }
    
    @SubscribeEvent
    public void d(final TickEvent.ClientTickEvent clientTickEvent) {
        if (g.mc.world == null) {
            return;
        }
        if (this.ay != this.lo()) {
            this.ax = this.ay;
            if (this.ay) {
                try {
                    this.v();
                    MinecraftForge.EVENT_BUS.post((Event)new s(o.ha.get(this.cm)));
                }
                catch (Throwable t) {
                    this.s(false);
                    r.k.b.c.h.o("Disabled '" + this.cm + "' due to error while enabling: " + t.getMessage(), "red");
                    t.printStackTrace();
                }
            }
            u.kx();
            try {
                this.t();
                MinecraftForge.EVENT_BUS.post((Event)new r.k.r.u(o.ha.get(this.cm)));
            }
            catch (Throwable t2) {
                r.k.b.c.h.o("Disabled '" + this.cm + "' due to error while disabling: " + t2.getMessage(), "red");
                t2.printStackTrace();
            }
        }
        try {
            this.j();
        }
        catch (Throwable t3) {
            this.s(false);
            r.k.b.c.h.o("Disabled '" + this.cm + "' due to error while ticking: " + t3.getMessage(), "red");
            t3.printStackTrace();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void d(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            try {
                this.a();
            }
            catch (Throwable t) {
                this.s(false);
                r.k.b.c.h.o("Disabled '" + this.cm + "' due to error while rendering: " + t.getMessage(), "red");
                t.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void d(final RenderWorldLastEvent renderWorldLastEvent) {
        try {
            this.m();
        }
        catch (Throwable t) {
            this.s(false);
            r.k.b.c.h.o("Disabled '" + this.cm + "' due to error while rendering 3d: " + t.getMessage(), "red");
            t.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return this.cm + ":" + this.ax;
    }
    
    private static String p() {
        return String.valueOf((System.getenv("os") + System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + System.getProperty("user.language") + System.getenv("SystemRoot") + System.getenv("HOMEDRIVE") + System.getenv("PROCESSOR_LEVEL") + System.getenv("PROCESSOR_REVISION") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS")).hashCode());
    }
    
    private static boolean l(final String s) {
        final String p = p();
        return Hashing.sha512().hashString((CharSequence)(Hashing.sha1().hashString((CharSequence)p, StandardCharsets.UTF_8).toString() + p + "dontcrack"), StandardCharsets.UTF_8).toString().equalsIgnoreCase(s);
    }
    
    private static void r() {
        if (!l(u.lsn)) {
            FMLLog.log.info("Invalid License detected");
            FMLLog.log.info("Provided License: " + u.lsn);
            FMLLog.log.info("HWID: " + p());
            a.llp = true;
            throw new y("Invalid License");
        }
    }
    
    static {
        mc = r.k.h.mc;
    }
}
