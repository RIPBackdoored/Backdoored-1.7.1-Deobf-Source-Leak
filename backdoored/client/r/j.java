package r.k.r;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;

public class j extends cz
{
    public ItemRenderer itemRenderer;
    public AbstractClientPlayer abstractClientPlayer;
    public float ab;
    public float aw;
    public EnumHand enumHand;
    public float au;
    public ItemStack itemStack;
    public float ac;
    public static final int aa;
    
    public j(final ItemRenderer itemRenderer, final AbstractClientPlayer abstractClientPlayer, final float ab, final float aw, final EnumHand enumHand, final float au, final ItemStack itemStack, final float ac) {
        super();
        this.itemRenderer = itemRenderer;
        this.abstractClientPlayer = abstractClientPlayer;
        this.ab = ab;
        this.aw = aw;
        this.au = au;
        this.itemStack = itemStack;
        this.ac = ac;
    }
}
