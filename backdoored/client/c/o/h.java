package r.k.c.o;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class h
{
    public static final int am;
    public static final boolean ah;
    
    h() {
        super();
    }
    
    public void li() {
    }
    
    @SubscribeEvent
    public void s(final RenderGameOverlayEvent.Post post) {
        if (post.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        this.li();
    }
}
