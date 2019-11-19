package r.k.p.m.y;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.entity.EntityPlayerSP;
import r.k.b.k;
import r.k.p.c.h;

public class l extends h
{
    public static final int uk;
    public static final boolean uq;
    
    public l() {
        super("Ping");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        int responseTime = 0;
        if (l.mc.world != null && l.mc.world.isRemote && l.mc.getConnection() != null) {
            final EntityPlayerSP player = l.mc.player;
            if (player != null) {
                final NetworkPlayerInfo playerInfo = l.mc.getConnection().getPlayerInfo(((EntityPlayer)player).getUniqueID());
                if (playerInfo != null) {
                    responseTime = playerInfo.getResponseTime();
                }
            }
        }
        final String string = responseTime + " ping";
        k.d(string, this.z().slo, this.z().slt);
        this.c().slo = l.mc.fontRenderer.getStringWidth(string);
        this.c().slt = l.mc.fontRenderer.FONT_HEIGHT;
    }
}
