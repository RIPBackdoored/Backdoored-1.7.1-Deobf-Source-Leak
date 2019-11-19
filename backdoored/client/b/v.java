package r.k.b;

import java.time.temporal.Temporal;
import java.time.Duration;
import java.time.Instant;

public class v
{
    public static final boolean sdh;
    public static final boolean ssd;
    
    public v() {
        super();
    }
    
    public static Instant yn() {
        return Instant.now();
    }
    
    public static long d(final Instant instant, final Instant instant2) {
        return Duration.between(instant, instant2).getSeconds();
    }
    
    public static boolean d(final Instant p0, final Instant p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_1       
        //     5: ireturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/v.d:(Ljava/time/Instant;Ljava/time/Instant;)J
        //    12: lload_2        
        //    13: lcmp           
        //    14: iflt            19
        //    17: iconst_1       
        //    18: nop            
        //    19: iconst_0       
        //    20: ireturn        
        //    StackMapTable: 00 04 FE 00 04 00 00 01 01 FF 00 0C 00 06 07 00 12 07 00 12 04 01 00 01 00 00 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static long s(final Instant instant, final Instant instant2) {
        return Duration.between(instant, instant2).toMillis();
    }
    
    public static boolean s(final Instant p0, final Instant p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokestatic    r/k/b/v.s:(Ljava/time/Instant;Ljava/time/Instant;)J
        //    12: lload_2        
        //    13: lcmp           
        //    14: iflt            19
        //    17: iconst_1       
        //    18: nop            
        //    19: iconst_0       
        //    20: ireturn        
        //    StackMapTable: 00 04 FE 00 04 00 00 01 01 FF 00 0C 00 06 07 00 12 07 00 12 04 01 00 01 00 00 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
