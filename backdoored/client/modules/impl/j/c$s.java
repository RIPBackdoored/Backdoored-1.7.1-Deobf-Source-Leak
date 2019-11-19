package r.k.d.m.j;

import net.minecraftforge.client.event.RenderGameOverlayEvent;

class c$s
{
    static final /* synthetic */ int[] smr;
    public static final boolean smf;
    public static final int smb;
    public static final boolean smw;
    
    static {
        smr = new int[RenderGameOverlayEvent.ElementType.values().length];
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.HELMET.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            try {
                c$s.smr[RenderGameOverlayEvent.ElementType.PORTAL.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError2) {
                try {
                    c$s.smr[RenderGameOverlayEvent.ElementType.CROSSHAIRS.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError3) {
                    try {
                        c$s.smr[RenderGameOverlayEvent.ElementType.BOSSHEALTH.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError noSuchFieldError4) {
                        try {
                            c$s.smr[RenderGameOverlayEvent.ElementType.BOSSINFO.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError noSuchFieldError5) {
                            try {
                                c$s.smr[RenderGameOverlayEvent.ElementType.ARMOR.ordinal()] = 6;
                            }
                            catch (NoSuchFieldError noSuchFieldError6) {
                                try {
                                    c$s.smr[RenderGameOverlayEvent.ElementType.HEALTH.ordinal()] = 7;
                                }
                                catch (NoSuchFieldError noSuchFieldError7) {}
                            }
                        }
                    }
                }
            }
        }
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.AIR.ordinal()] = 9;
        }
        catch (NoSuchFieldError noSuchFieldError8) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.HOTBAR.ordinal()] = 10;
        }
        catch (NoSuchFieldError noSuchFieldError9) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.EXPERIENCE.ordinal()] = 11;
        }
        catch (NoSuchFieldError noSuchFieldError10) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.TEXT.ordinal()] = 12;
        }
        catch (NoSuchFieldError noSuchFieldError11) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.HEALTHMOUNT.ordinal()] = 13;
        }
        catch (NoSuchFieldError noSuchFieldError12) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.JUMPBAR.ordinal()] = 14;
        }
        catch (NoSuchFieldError noSuchFieldError13) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.CHAT.ordinal()] = 15;
        }
        catch (NoSuchFieldError noSuchFieldError14) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.PLAYER_LIST.ordinal()] = 16;
        }
        catch (NoSuchFieldError noSuchFieldError15) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.POTION_ICONS.ordinal()] = 17;
        }
        catch (NoSuchFieldError noSuchFieldError16) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.SUBTITLES.ordinal()] = 18;
        }
        catch (NoSuchFieldError noSuchFieldError17) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.FPS_GRAPH.ordinal()] = 19;
        }
        catch (NoSuchFieldError noSuchFieldError18) {}
        try {
            c$s.smr[RenderGameOverlayEvent.ElementType.VIGNETTE.ordinal()] = 20;
        }
        catch (NoSuchFieldError noSuchFieldError19) {}
    }
}
