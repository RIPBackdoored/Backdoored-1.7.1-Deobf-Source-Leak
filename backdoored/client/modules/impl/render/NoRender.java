package r.k.d.m.render;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketExplosion;
import r.k.d.o.h.p;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "No Render", description = "Dont render things", category = y.RENDER)
public class NoRender extends g
{
    private m<Boolean> sxw;
    private m<Boolean> sxg;
    private m<Boolean> sxu;
    private m<Boolean> sxz;
    private m<Boolean> sxc;
    private m<Boolean> sxa;
    private m<Boolean> sxm;
    private m<Boolean> sxh;
    private m<Boolean> skd;
    private m<Boolean> sks;
    private m<Boolean> skl;
    private m<Boolean> sky;
    private m<Boolean> skx;
    private m<Boolean> skk;
    private m<Boolean> skq;
    private m<Boolean> skv;
    private m<Boolean> skj;
    private m<Boolean> ske;
    private m<Boolean> sko;
    private m<Boolean> skt;
    private m<Boolean> skn;
    private m<Boolean> ski;
    public static final boolean skp;
    public static final int skr;
    public static final boolean skf;
    
    public NoRender() {
        super();
        this.sxw = (m<Boolean>)new p("Stop Explosions", this, true);
        this.sxg = (m<Boolean>)new p("Stop Particles", this, true);
        this.sxu = (m<Boolean>)new p("helmet", this, false);
        this.sxz = (m<Boolean>)new p("portal", this, false);
        this.sxc = (m<Boolean>)new p("crosshair", this, false);
        this.sxa = (m<Boolean>)new p("bosshealth", this, false);
        this.sxm = (m<Boolean>)new p("bossinfo", this, false);
        this.sxh = (m<Boolean>)new p("armor", this, false);
        this.skd = (m<Boolean>)new p("health", this, false);
        this.sks = (m<Boolean>)new p("food", this, false);
        this.skl = (m<Boolean>)new p("air", this, false);
        this.sky = (m<Boolean>)new p("hotbar", this, false);
        this.skx = (m<Boolean>)new p("experience", this, false);
        this.skk = (m<Boolean>)new p("text", this, false);
        this.skq = (m<Boolean>)new p("horse health", this, false);
        this.skv = (m<Boolean>)new p("horse jump", this, false);
        this.skj = (m<Boolean>)new p("chat", this, false);
        this.ske = (m<Boolean>)new p("playerlist", this, false);
        this.sko = (m<Boolean>)new p("potion icon", this, false);
        this.skt = (m<Boolean>)new p("subtitles", this, false);
        this.skn = (m<Boolean>)new p("fps graph", this, false);
        this.ski = (m<Boolean>)new p("vignette", this, false);
    }
    
    @SubscribeEvent
    public void l(final r.k.r.h.p p) {
        if (this.lo() && this.sxw.yv() && p.packet instanceof SPacketExplosion) {
            p.setCanceled(true);
        }
        if (this.lo() && this.sxg.yv() && p.packet instanceof SPacketParticles) {
            p.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void s(final RenderGameOverlayEvent renderGameOverlayEvent) {
        Label_0514: {
            switch (c$s.smr[renderGameOverlayEvent.getType().ordinal()]) {
                case 1:
                    if (this.sxu.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 2:
                    if (this.sxz.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 3:
                    if (this.sxc.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 4:
                    if (this.sxa.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 5:
                    if (this.sxm.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 6:
                    renderGameOverlayEvent.setCanceled(true);
                case 7:
                    if (this.skd.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 8:
                    if (this.sks.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 9:
                    if (this.skl.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 10:
                    if (this.sky.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 11:
                    if (this.skx.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 12:
                    if (this.skk.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 13:
                    if (this.skq.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 14:
                    if (this.skv.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 15:
                    if (this.skj.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 16:
                    if (this.ske.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 17:
                    if (this.sko.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 18:
                    if (this.skt.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 19:
                    if (this.skn.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break Label_0514;
                    }
                    break;
                case 20:
                    if (this.ski.yv()) {
                        renderGameOverlayEvent.setCanceled(true);
                        break;
                    }
                    break;
            }
        }
    }
}
