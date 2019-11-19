package r.k.d.o;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class p
{
    public static final boolean gj;
    public static final int ge;
    public static final boolean go;
    
    public p() {
        super();
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void d(final InputEvent.KeyInputEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            5
        //     4: return         
        //     5: invokestatic    org/lwjgl/input/Keyboard.getEventKeyState:()Z
        //     8: ifeq            104
        //    11: invokestatic    org/lwjgl/input/Keyboard.getEventKey:()I
        //    14: istore_2       
        //    15: iload_2        
        //    16: ifeq            104
        //    19: iload_2        
        //    20: invokestatic    org/lwjgl/input/Keyboard.getKeyName:(I)Ljava/lang/String;
        //    23: astore_3       
        //    24: aload_3        
        //    25: ldc             "NONE"
        //    27: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    30: ifne            104
        //    33: getstatic       r/k/d/o/y.qh:Lr/k/d/o/y;
        //    36: invokevirtual   r/k/d/o/y.h:()Ljava/util/ArrayList;
        //    39: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    42: astore          4
        //    44: aload           4
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            104
        //    54: aload           4
        //    56: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    61: checkcast       Lr/k/d/o/m;
        //    64: astore          5
        //    66: aload_3        
        //    67: aload           5
        //    69: invokevirtual   r/k/d/o/m.yv:()Ljava/lang/Object;
        //    72: checkcast       Ljava/lang/String;
        //    75: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    78: ifeq            103
        //    81: aload           5
        //    83: invokevirtual   r/k/d/o/m.yk:()Lr/k/d/m/g;
        //    86: aload           5
        //    88: invokevirtual   r/k/d/o/m.yk:()Lr/k/d/m/g;
        //    91: invokevirtual   r/k/d/m/g.lo:()Z
        //    94: ifne            99
        //    97: iconst_1       
        //    98: nop            
        //    99: iconst_0       
        //   100: invokevirtual   r/k/d/m/g.s:(Z)V
        //   103: nop            
        //   104: return         
        //    StackMapTable: 00 07 FF 00 04 00 09 07 00 1C 07 00 07 00 00 00 00 00 00 01 00 00 FF 00 00 00 09 07 00 1C 07 00 07 00 00 00 00 01 01 01 00 00 FF 00 26 00 09 07 00 1C 07 00 07 01 07 00 2E 07 00 44 00 01 01 01 00 00 FF 00 36 00 09 07 00 1C 07 00 07 01 07 00 2E 07 00 44 07 00 5B 01 01 01 00 01 07 00 5D FF 00 00 00 09 07 00 1C 07 00 07 01 07 00 2E 07 00 44 07 00 5B 01 01 01 00 02 07 00 5D 01 02 FF 00 00 00 09 07 00 1C 07 00 07 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
