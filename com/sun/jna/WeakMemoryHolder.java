package com.sun.jna;

import java.lang.ref.WeakReference;
import java.lang.ref.Reference;
import java.util.IdentityHashMap;
import java.lang.ref.ReferenceQueue;

public class WeakMemoryHolder
{
    ReferenceQueue<Object> referenceQueue;
    IdentityHashMap<Reference<Object>, Memory> backingMap;
    
    public WeakMemoryHolder() {
        super();
        this.referenceQueue = new ReferenceQueue<Object>();
        this.backingMap = new IdentityHashMap<Reference<Object>, Memory>();
    }
    
    public synchronized void put(final Object o, final Memory memory) {
        this.clean();
        this.backingMap.put(new WeakReference<Object>(o, this.referenceQueue), memory);
    }
    
    public synchronized void clean() {
        for (Reference<?> reference = this.referenceQueue.poll(); reference != null; reference = this.referenceQueue.poll()) {
            this.backingMap.remove(reference);
        }
    }
}
