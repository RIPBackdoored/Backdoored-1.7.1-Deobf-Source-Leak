package org.reflections;

import java.net.URL;

class Reflections$1 implements Runnable {
    final /* synthetic */ URL val$url;
    final /* synthetic */ Reflections this$0;
    
    Reflections$1(final Reflections this$0, final URL val$url) {
        this.this$0 = this$0;
        this.val$url = val$url;
        super();
    }
    
    @Override
    public void run() {
        if (Reflections.log != null && Reflections.log.isDebugEnabled()) {
            Reflections.log.debug("[" + Thread.currentThread().toString() + "] scanning " + this.val$url);
        }
        this.this$0.scan(this.val$url);
    }
}