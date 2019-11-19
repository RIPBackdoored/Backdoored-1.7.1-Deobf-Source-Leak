package r.k.d.m.ui;

import java.util.Iterator;
import java.awt.Color;
import java.util.Map;
import net.minecraft.potion.Potion;
import net.minecraft.entity.player.EntityPlayer;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Players Potions", description = "Show players potions", category = y.UI)
public class PlayersPotions extends g
{
    private final m<Integer> scj;
    private final m<Integer> sce;
    public static final boolean sco;
    public static final int sct;
    public static final boolean scn;
    
    public PlayersPotions() {
        super();
        this.scj = (m<Integer>)new x("x", this, 0, 0, (int)Math.round(PlayersPotions.mc.displayWidth * 1.3));
        this.sce = (m<Integer>)new x("y", this, 0, 0, (int)Math.round(PlayersPotions.mc.displayWidth * 1.3));
    }
    
    public void a() {
        if (this.lo()) {
            final int n = 0;
            final Iterator<EntityPlayer> iterator = PlayersPotions.mc.world.playerEntities.iterator();
            if (iterator.hasNext()) {
                final EntityPlayer entityPlayer = iterator.next();
                final String displayNameString = entityPlayer.getDisplayNameString();
                final Iterator<Map.Entry<Potion, V>> iterator2 = entityPlayer.getActivePotionMap().entrySet().iterator();
                if (iterator2.hasNext()) {
                    final int n2;
                    PlayersPotions.mc.fontRenderer.drawString(displayNameString + " : " + iterator2.next().getKey().getName() + " : " + n2, (int)this.scj.yv(), this.sce.yv() + n, Color.WHITE.getRGB());
                    final int n3 = n + (PlayersPotions.mc.fontRenderer.FONT_HEIGHT + 2);
                }
            }
        }
    }
}
