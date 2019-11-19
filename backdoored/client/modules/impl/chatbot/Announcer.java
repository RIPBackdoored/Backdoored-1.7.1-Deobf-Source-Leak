package r.k.d.m.chatbot;

import r.k.r.cb;
import r.k.r.cv;
import r.k.r.u;
import r.k.r.s;
import net.minecraftforge.client.event.ScreenshotEvent;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.v;
import r.k.r.cr$y;
import r.k.b.c.h;
import r.k.d.m.q.p;
import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3d;
import java.time.Instant;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Announcer", description = "Don't use this unless your a penis", category = y.CHATBOT)
public class Announcer extends g
{
    private m<Boolean> ssp;
    private Instant ssr;
    private Vec3d vec3d;
    private m<Boolean> ssb;
    private Instant ssw;
    private Block block;
    private int ssu;
    private m<Boolean> ssz;
    private Instant ssc;
    private Block block;
    private int ssm;
    private m<Boolean> ssh;
    private Instant sld;
    private m<Boolean> sls;
    private m<Boolean> sll;
    private p sly;
    public static final boolean slx;
    public static final int slk;
    public static final boolean slq;
    
    public Announcer() {
        super();
        this.ssp = (m<Boolean>)new r.k.d.o.h.p("Movement", this, true);
        this.ssr = Instant.now();
        this.vec3d = null;
        this.ssb = (m<Boolean>)new r.k.d.o.h.p("Block Place", this, true);
        this.ssw = Instant.now();
        this.ssu = 0;
        this.ssz = (m<Boolean>)new r.k.d.o.h.p("Block Break", this, true);
        this.ssc = Instant.now();
        this.block = null;
        this.ssm = 0;
        this.ssh = (m<Boolean>)new r.k.d.o.h.p("Attack Entities", this, true);
        this.sld = Instant.now();
        this.sls = (m<Boolean>)new r.k.d.o.h.p("Gui", this, true);
        this.sll = (m<Boolean>)new r.k.d.o.h.p("Screenshot", this, true);
    }
    
    public void v() {
        try {
            this.sly = new p();
        }
        catch (Exception ex) {
            this.s(false);
            h.o("Failed to initialise Announcer script: " + ex.getMessage(), "red");
            ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void d(final cr$y cr$y) {
        if (this.lo() && v.d(this.ssr, Instant.now(), 60L) && this.ssp.yv()) {
            if (this.vec3d == null) {
                this.vec3d = Announcer.mc.player.getPositionVector();
                return;
            }
            final int n = (int)Math.round(this.vec3d.distanceTo(Announcer.mc.player.getPositionVector()));
            if (n > 0) {
                this.ss(this.sly.k(n));
                this.ssr = Instant.now();
            }
        }
    }
    
    @SubscribeEvent
    public void d(final r.k.r.h.y y) {
        if (this.lo() && y.packet instanceof CPacketPlayerTryUseItemOnBlock && this.ssb.yv()) {
            final ItemStack heldItem = Announcer.mc.player.getHeldItem(((CPacketPlayerTryUseItemOnBlock)y.packet).getHand());
            if (heldItem.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)heldItem.getItem()).getBlock();
                if (this.block == null) {
                    this.block = block;
                }
                if (this.block.equals(block)) {
                    ++this.ssu;
                }
            }
            if (v.d(this.ssw, Instant.now(), 60L) && this.ssu > 0) {
                this.ss(this.sly.l(this.ssu, heldItem.getDisplayName()));
                this.ssw = Instant.now();
                this.block = null;
            }
        }
    }
    
    @SubscribeEvent
    public void d(final BlockEvent.BreakEvent breakEvent) {
        if (this.lo() && breakEvent.getPlayer().equals((Object)Announcer.mc.player)) {
            final Block block = breakEvent.getState().getBlock();
            if (this.block == null) {
                this.block = block;
            }
            if (this.block.equals(block)) {
                ++this.ssm;
            }
            if (v.d(this.ssc, Instant.now(), 60L) && this.ssm > 0) {
                this.ss(this.sly.s(this.ssm, block.getLocalizedName()));
                this.ssc = Instant.now();
                this.block = null;
            }
        }
    }
    
    @SubscribeEvent
    public void d(final AttackEntityEvent attackEntityEvent) {
        if (this.lo() && attackEntityEvent.getTarget() instanceof EntityLivingBase && v.d(this.sld, Instant.now(), 60L)) {
            this.ss(this.sly.sd(attackEntityEvent.getTarget().getDisplayName().getUnformattedText()));
            this.sld = Instant.now();
        }
    }
    
    @SubscribeEvent
    public void l(final GuiOpenEvent guiOpenEvent) {
        if (this.sls.yv() && guiOpenEvent.getGui() != null && guiOpenEvent.getGui() instanceof GuiInventory) {
            this.ss(this.sly.d((GuiInventory)guiOpenEvent.getGui()));
        }
    }
    
    @SubscribeEvent
    public void d(final ScreenshotEvent screenshotEvent) {
        if (this.lo() && this.sll.yv()) {
            this.ss(this.sly.yd());
        }
    }
    
    @SubscribeEvent
    public void d(final s s) {
        if (this.lo()) {
            this.ss(this.sly.ys());
        }
    }
    
    @SubscribeEvent
    public void d(final u u) {
        if (this.lo()) {
            this.ss(this.sly.yl());
        }
    }
    
    @SubscribeEvent
    public void d(final cv cv) {
        if (this.lo()) {
            this.ss(this.sly.yy());
        }
    }
    
    @SubscribeEvent
    public void d(final cb cb) {
        if (this.lo()) {
            this.ss(this.sly.yx());
        }
    }
    
    private void ss(String h) {
        if (h == null) {
            return;
        }
        h = this.sly.h(h);
        if (h == null) {
            return;
        }
        if (this.lo()) {
            Announcer.mc.player.sendChatMessage(h + " thanks to Backdoored Client");
        }
    }
}
