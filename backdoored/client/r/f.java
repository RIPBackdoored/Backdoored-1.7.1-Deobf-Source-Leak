package r.k.r;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.shader.Framebuffer;
import java.io.File;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class f extends cz
{
    public File sql;
    public String sqy;
    public int sqx;
    public int sqk;
    public Framebuffer framebuffer;
    public ITextComponent iTextComponent;
    public static final int sqj;
    
    public f(final File sql, final String sqy, final int sqx, final int sqk, final Framebuffer framebuffer) {
        super();
        this.sql = sql;
        this.sqy = sqy;
        this.sqx = sqx;
        this.sqk = sqk;
        this.framebuffer = framebuffer;
    }
}
