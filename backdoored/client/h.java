package r.k;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.client.Minecraft;

public interface h
{
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final boolean sbw = true;
    public static final boolean sbg;
    
    default NetworkManager xv() {
        return FMLClientHandler.instance().getClientToServerNetworkManager();
    }
}
