package r.k.p.m.y;

import java.util.Iterator;
import r.k.b.k;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import \u0000r.\u0000k.\u0000p.\u0000m.\u0000y.\u0000g;
import r.k.d.m.o;
import java.util.List;
import r.k.p.c.h;

public class g extends h
{
    public static final boolean soe;
    public static final int soo;
    public static final boolean sot;
    
    public g() {
        super("Enabled Hacks");
    }
    
    @Override
    public void d(final int n, final int n2, final float n3) {
        super.d(n, n2, n3);
        if (!this.xb()) {
            return;
        }
        final List<? super Object> list = o.lc().stream().filter((Predicate<? super Object>)\u0000g::d).<List<? super Object>, ?>collect((Collector<? super Object, ?, List<? super Object>>)Collectors.<Object>toList());
        int n4 = 0;
        final int n5 = 0;
        final Iterator<r.k.d.m.g> iterator = list.iterator();
        if (iterator.hasNext()) {
            final r.k.d.m.g g = iterator.next();
            k.d(g.cm, this.z().slo, this.z().slt + n5);
            final int n6 = n5 + (r.k.p.m.y.g.mc.fontRenderer.FONT_HEIGHT + 2);
            final int stringWidth = r.k.p.m.y.g.mc.fontRenderer.getStringWidth(g.cm);
            if (stringWidth > n4) {
                n4 = stringWidth;
            }
        }
        int n7 = list.size() * (g.mc.fontRenderer.FONT_HEIGHT + 2);
        n7 -= 2;
        this.s(new r.k.b.i.h(n4, n7));
    }
    
    private static /* synthetic */ boolean d(final r.k.d.m.g p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: aload_0        
        //     9: getfield        r/k/d/m/g.as:Lr/k/d/o/m;
        //    12: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    15: checkcast       Ljava/lang/Boolean;
        //    18: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    21: ifeq            26
        //    24: iconst_1       
        //    25: nop            
        //    26: iconst_0       
        //    27: ireturn        
        //    StackMapTable: 00 05 FF 00 04 00 04 07 00 89 00 00 01 00 00 01 FF 00 01 00 04 07 00 89 01 01 01 00 00 11 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
