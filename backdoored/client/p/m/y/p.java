package r.k.p.m.y;

import r.k.b.k;
import r.k.p.c.h;

public class p extends h
{
    public static final int je;
    public static final boolean jo;
    
    public p() {
        super("BiomeElement");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        k.d(p.mc.world.getChunk(p.mc.player.getPosition()).getBiome(p.mc.player.getPosition(), p.mc.world.getBiomeProvider()).getBiomeName(), this.z().slo, this.z().slt);
    }
}
