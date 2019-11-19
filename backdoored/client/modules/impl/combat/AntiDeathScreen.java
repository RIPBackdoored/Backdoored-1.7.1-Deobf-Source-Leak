package r.k.d.m.combat;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import r.k.b.c.h;
import net.minecraft.client.gui.GuiScreen;
import r.k.e.o;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.client.event.GuiOpenEvent;
import r.k.d.m.m.b;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Anti Death Screen", description = "Prevents the death screen from incorrectly coming up during combat", category = y.COMBAT)
public class AntiDeathScreen extends g
{
    public static b stt;
    public static final boolean stn;
    public static final int sti;
    public static final boolean stp;
    
    public AntiDeathScreen() {
        AntiDeathScreen.stt = (b)this;
    }
    
    @SubscribeEvent
    public void d(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiGameOver) {
            try {
                guiOpenEvent.setGui((GuiScreen)new o((ITextComponent)ObfuscationReflectionHelper.getPrivateValue((Class)GuiGameOver.class, (Object)guiOpenEvent.getGui(), new String[] { "causeOfDeath", "field_184871_f" })));
            }
            catch (Exception ex) {
                h.sj("Disabled Anti Death Screen due to an error: " + ex.toString());
                ex.printStackTrace();
                this.s(false);
            }
        }
    }
}
