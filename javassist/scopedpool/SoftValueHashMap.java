package javassist.scopedpool;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Set;
import java.lang.ref.ReferenceQueue;
import java.util.Map;
import java.util.AbstractMap;

public class SoftValueHashMap extends AbstractMap implements Map
{
    private Map hash;
    private ReferenceQueue queue;
    
    @Override
    public Set entrySet() {
        this.processQueue();
        return this.hash.entrySet();
    }
    
    private void processQueue() {
        SoftValueRef softValueRef;
        while ((softValueRef = (SoftValueRef)this.queue.poll()) != null) {
            if (softValueRef == this.hash.get(softValueRef.key)) {
                this.hash.remove(softValueRef.key);
            }
        }
    }
    
    public SoftValueHashMap(final int n, final float n2) {
        super();
        this.queue = new ReferenceQueue();
        this.hash = new HashMap(n, n2);
    }
    
    public SoftValueHashMap(final int n) {
        super();
        this.queue = new ReferenceQueue();
        this.hash = new HashMap(n);
    }
    
    public SoftValueHashMap() {
        super();
        this.queue = new ReferenceQueue();
        this.hash = new HashMap();
    }
    
    public SoftValueHashMap(final Map map) {
        this(Math.max(2 * map.size(), 11), 0.75f);
        this.putAll(map);
    }
    
    @Override
    public int size() {
        this.processQueue();
        return this.hash.size();
    }
    
    @Override
    public boolean isEmpty() {
        this.processQueue();
        return this.hash.isEmpty();
    }
    
    @Override
    public boolean containsKey(final Object o) {
        this.processQueue();
        return this.hash.containsKey(o);
    }
    
    @Override
    public Object get(final Object o) {
        this.processQueue();
        final SoftReference<Object> softReference = this.hash.get(o);
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }
    
    @Override
    public Object put(final Object o, final Object o2) {
        this.processQueue();
        Object o3 = this.hash.put(o, create(o, o2, this.queue));
        if (o3 != null) {
            o3 = ((SoftReference<Object>)o3).get();
        }
        return o3;
    }
    
    @Override
    public Object remove(final Object o) {
        this.processQueue();
        return this.hash.remove(o);
    }
    
    @Override
    public void clear() {
        this.processQueue();
        this.hash.clear();
    }
}
