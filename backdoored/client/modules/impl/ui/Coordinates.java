package r.k.d.m.ui;

import net.minecraft.util.math.Vec3d;
import java.awt.Color;
import r.k.d.o.h.i.x;
import r.k.d.o.m;
import r.k.d.c.y;
import r.k.d.m.g$i;
import r.k.d.m.g;

@g$i(name = "Coordinates", description = "Show your coordinates", category = y.UI)
public class Coordinates extends g
{
    private m<Integer> sbu;
    private m<Integer> sbz;
    public static final int sbc;
    public static final boolean sba;
    
    public Coordinates() {
        super();
        this.sbu = (m<Integer>)new x("x", this, 50, 0, (int)Math.round(Coordinates.mc.displayWidth * 1.2));
        this.sbz = (m<Integer>)new x("y", this, 50, 0, (int)Math.round(Coordinates.mc.displayHeight * 1.2));
    }
    
    public void a() {
        if (this.lo()) {
            Coordinates.mc.fontRenderer.drawString(this.s(Coordinates.mc.player.getPositionVector()), (int)this.sbu.yv(), (int)this.sbz.yv(), Color.WHITE.getRGB());
        }
    }
    
    private String s(final Vec3d vec3d) {
        return (int)Math.floor(vec3d.x) + ", " + (int)Math.floor(vec3d.y) + ", " + (int)Math.floor(vec3d.z) + " (" + (int)Math.floor(vec3d.x) / 8 + ", " + (int)Math.floor(vec3d.z) / 8 + ")";
    }
}
