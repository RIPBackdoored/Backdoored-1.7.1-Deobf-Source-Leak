package r.k.r;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.network.NetworkPlayerInfo;

public class ck extends cz
{
    public NetworkPlayerInfo networkPlayerInfo;
    public ResourceLocation resourceLocation;
    public static final int sbr;
    
    public ck(final NetworkPlayerInfo networkPlayerInfo) {
        super();
        this.networkPlayerInfo = networkPlayerInfo;
        this.resourceLocation = null;
    }
}
