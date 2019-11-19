package javassist.util;

import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.MethodEntryEvent;

class HotSwapper$1 extends Thread {
    final /* synthetic */ HotSwapper this$0;
    
    HotSwapper$1(final HotSwapper this$0) {
        this.this$0 = this$0;
        super();
    }
    
    private void errorMsg(final Throwable t) {
        System.err.print("Exception in thread \"HotSwap\" ");
        t.printStackTrace(System.err);
    }
    
    @Override
    public void run() {
        EventSet waitEvent = null;
        try {
            waitEvent = this.this$0.waitEvent();
            final EventIterator eventIterator = waitEvent.eventIterator();
            while (eventIterator.hasNext()) {
                if (eventIterator.nextEvent() instanceof MethodEntryEvent) {
                    this.this$0.hotswap();
                    break;
                }
            }
        }
        catch (Throwable t) {
            this.errorMsg(t);
        }
        try {
            if (waitEvent != null) {
                waitEvent.resume();
            }
        }
        catch (Throwable t2) {
            this.errorMsg(t2);
        }
    }
}