package r.k.d.m.j;

import net.minecraftforge.client.event.RenderBlockOverlayEvent;

class l$s
{
    static final /* synthetic */ int[] rp;
    public static final boolean rr;
    public static final int rf;
    public static final boolean rb;
    
    static {
        rp = new int[RenderBlockOverlayEvent.OverlayType.values().length];
        try {
            l$s.rp[RenderBlockOverlayEvent.OverlayType.BLOCK.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            l$s.rp[RenderBlockOverlayEvent.OverlayType.WATER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
    }
}
