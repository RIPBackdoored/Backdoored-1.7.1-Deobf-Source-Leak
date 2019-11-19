package r.k.b.o;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.MobEffects;

public class h implements r.k.h
{
    public static final boolean hr;
    public static final int hf;
    public static final boolean hb;
    
    public h() {
        super();
    }
    
    public double lg() {
        return 0.2873 * (1.0 + 0.2 * (h.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier() + 1));
    }
    
    public static void lu() {
        h.mc.world.sendQuittingDisconnectingPacket();
        h.mc.loadWorld((WorldClient)null);
        if (h.mc.isIntegratedServerRunning()) {
            h.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
        }
        if (h.mc.isConnectedToRealms()) {
            new RealmsBridge().switchToRealms((GuiScreen)new GuiMainMenu());
        }
        h.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
    }
}
