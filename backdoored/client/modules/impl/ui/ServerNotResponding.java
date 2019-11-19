package r.k.d.m.ui;

import net.minecraft.client.gui.ScaledResolution;
import java.awt.Color;
import java.text.DecimalFormat;
import r.k.b.v;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.r.h.p;
import java.time.Instant;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Server Not Responding", description = "Get notified when the server isnt responding", category = y.UI)
public class ServerNotResponding extends g
{
    private Instant srs;
    public static final int srl;
    public static final boolean sry;
    
    public ServerNotResponding() {
        super();
    }
    
    @SubscribeEvent
    public void s(final p p) {
        this.srs = Instant.now();
    }
    
    public void a() {
        if (this.lo()) {
            final long s = v.s(this.srs, Instant.now());
            if (s >= 1000L) {
                final String string = "§7Server has not responded for §r" + new DecimalFormat("0.0").format(s / 1000L) + "s";
                final ScaledResolution scaledResolution;
                ServerNotResponding.mc.fontRenderer.drawString(string, scaledResolution.getScaledWidth() / 2 - ServerNotResponding.mc.fontRenderer.getStringWidth(string), 4, Color.WHITE.getRGB());
            }
        }
    }
}
