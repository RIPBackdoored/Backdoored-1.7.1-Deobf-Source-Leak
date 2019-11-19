package r.k.i;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.FontRenderer;

public class m extends FontRenderer
{
    public static final int sjp;
    
    public m(final GameSettings gameSettings, final ResourceLocation resourceLocation, final TextureManager textureManager, final boolean b) {
        super(gameSettings, resourceLocation, textureManager, b);
    }
}
