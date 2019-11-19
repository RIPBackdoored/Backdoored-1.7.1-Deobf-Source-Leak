package r.k.d.m.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiChat;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Gui Move", description = "Walk while in guis", category = y.MOVEMENT)
public class GuiMove extends g
{
    public static final int md;
    public static final boolean ms;
    
    public GuiMove() {
        super();
    }
    
    public void j() {
        if (this.lo() && GuiMove.mc.currentScreen != null && !(GuiMove.mc.currentScreen instanceof GuiChat)) {
            if (Keyboard.isKeyDown(200)) {
                final EntityPlayerSP player = GuiMove.mc.player;
                player.rotationPitch -= 2.0f;
            }
            if (Keyboard.isKeyDown(208)) {
                final EntityPlayerSP player2 = GuiMove.mc.player;
                player2.rotationPitch += 2.0f;
            }
            if (Keyboard.isKeyDown(203)) {
                final EntityPlayerSP player3 = GuiMove.mc.player;
                player3.rotationYaw -= 2.0f;
            }
            if (Keyboard.isKeyDown(205)) {
                final EntityPlayerSP player4 = GuiMove.mc.player;
                player4.rotationYaw += 2.0f;
            }
        }
    }
    
    @SubscribeEvent
    public void d(final r.k.r.g g) {
        if (this.lo() && !(GuiMove.mc.currentScreen instanceof GuiChat)) {
            g.ml = g.my;
        }
    }
}
