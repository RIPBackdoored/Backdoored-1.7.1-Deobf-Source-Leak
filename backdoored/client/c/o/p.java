package r.k.c.o;

import java.util.Iterator;
import java.util.function.Consumer;
import \u0000r.\u0000k.\u0000c.\u0000o.\u0000p;
import r.k.c.c.j;

public class p extends h
{
    public static final boolean sfk;
    public static final int sfq;
    public static final boolean sfv;
    
    public p() {
        super();
    }
    
    @Override
    public void li() {
        final Iterator<j> iterator = j.h().iterator();
        if (iterator.hasNext()) {
            final j j = iterator.next();
            if (j.yk().lo()) {
                j.eg = "FF0000";
            }
            j.eg = "FFFFFF";
            if (j.et) {
                j.kl().forEach((Consumer<? super r.k.c.c.h>)\u0000p::s);
                final Iterator<j> iterator2 = j.ks().xh().iterator();
                if (iterator2.hasNext()) {
                    final j i = iterator2.next();
                    if (i != j) {
                        i.kl().forEach((Consumer<? super r.k.c.c.h>)\u0000p::d);
                    }
                }
                j.et = false;
            }
        }
    }
    
    private static /* synthetic */ void d(final r.k.c.c.h h) {
    }
    
    private static /* synthetic */ void s(final r.k.c.c.h p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: nop            
        //     6: aload_0        
        //     7: aload_0        
        //     8: getfield        r/k/c/c/h.eb:Z
        //    11: ifne            16
        //    14: iconst_1       
        //    15: nop            
        //    16: iconst_0       
        //    17: putfield        r/k/c/c/h.eb:Z
        //    20: return         
        //    StackMapTable: 00 04 FF 00 04 00 04 07 00 6F 00 00 01 00 00 00 FF 00 0A 00 04 07 00 6F 01 00 01 00 01 07 00 6F FF 00 00 00 04 07 00 6F 01 00 01 00 02 07 00 6F 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
