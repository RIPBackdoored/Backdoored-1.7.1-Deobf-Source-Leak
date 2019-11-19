package javassist.scopedpool;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

private static class SoftValueRef extends SoftReference
{
    public Object key;
    
    private SoftValueRef(final Object key, final Object o, final ReferenceQueue referenceQueue) {
        super(o, referenceQueue);
        this.key = key;
    }
    
    private static SoftValueRef create(final Object o, final Object o2, final ReferenceQueue referenceQueue) {
        if (o2 == null) {
            return null;
        }
        return new SoftValueRef(o, o2, referenceQueue);
    }
    
    static /* synthetic */ SoftValueRef access$000(final Object o, final Object o2, final ReferenceQueue referenceQueue) {
        return create(o, o2, referenceQueue);
    }
}
