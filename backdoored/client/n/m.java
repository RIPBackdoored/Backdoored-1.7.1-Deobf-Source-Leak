package r.k.n;

import r.k.b.c.h;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;

public class m extends c
{
    private boolean c;
    public static final int a;
    public static final boolean m;
    
    public m() {
        super(new String[] { "HudEditor", "EditHud" });
        this.c = false;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public boolean d(final String[] array) {
        return this.c = true;
    }
    
    @SubscribeEvent
    public void d(final TickEvent.ClientTickEvent clientTickEvent) {
        if (clientTickEvent.phase == TickEvent.Phase.END && this.c) {
            this.mc.addScheduledTask(this::q);
            this.c = false;
        }
    }
    
    @Override
    public String k() {
        return "-hudeditor";
    }
    
    private /* synthetic */ void q() {
        this.mc.displayGuiScreen((GuiScreen)new r.k.p.m.m());
        h.sj("Opened Hud Editor");
    }
}
