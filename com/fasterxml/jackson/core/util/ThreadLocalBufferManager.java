package com.fasterxml.jackson.core.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;

class ThreadLocalBufferManager
{
    private final Object RELEASE_LOCK;
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers;
    private final ReferenceQueue<BufferRecycler> _refQueue;
    
    ThreadLocalBufferManager() {
        super();
        this.RELEASE_LOCK = new Object();
        this._trackedRecyclers = new ConcurrentHashMap<SoftReference<BufferRecycler>, Boolean>();
        this._refQueue = new ReferenceQueue<BufferRecycler>();
    }
    
    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.manager;
    }
    
    public int releaseBuffers() {
        synchronized (this.RELEASE_LOCK) {
            int n = 0;
            this.removeSoftRefsClearedByGc();
            final Iterator<SoftReference<BufferRecycler>> iterator = this._trackedRecyclers.keySet().iterator();
            while (iterator.hasNext()) {
                iterator.next().clear();
                ++n;
            }
            this._trackedRecyclers.clear();
            return n;
        }
    }
    
    public SoftReference<BufferRecycler> wrapAndTrack(final BufferRecycler bufferRecycler) {
        final SoftReference<BufferRecycler> softReference = new SoftReference<BufferRecycler>(bufferRecycler, this._refQueue);
        this._trackedRecyclers.put(softReference, true);
        this.removeSoftRefsClearedByGc();
        return softReference;
    }
    
    private void removeSoftRefsClearedByGc() {
        SoftReference softReference;
        while ((softReference = (SoftReference)this._refQueue.poll()) != null) {
            this._trackedRecyclers.remove(softReference);
        }
    }
}
