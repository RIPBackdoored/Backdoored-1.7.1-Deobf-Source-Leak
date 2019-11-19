package r.k.r;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;

public class x$h extends x
{
    public ResourceLocation resourceLocation;
    public static final int sim;
    
    public x$h(final NetworkPlayerInfo networkPlayerInfo, final ResourceLocation resourceLocation) {
        super(networkPlayerInfo);
        this.resourceLocation = resourceLocation;
    }
}
