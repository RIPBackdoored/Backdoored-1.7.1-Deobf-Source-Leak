package javassist.bytecode.analysis;

private static class Entry
{
    private Entry next;
    private int value;
    
    private Entry(final int value) {
        super();
        this.value = value;
    }
    
    Entry(final int n, final IntQueue$1 object) {
        this(n);
    }
    
    static /* synthetic */ Entry access$102(final Entry entry, final Entry next) {
        return entry.next = next;
    }
    
    static /* synthetic */ int access$200(final Entry entry) {
        return entry.value;
    }
    
    static /* synthetic */ Entry access$100(final Entry entry) {
        return entry.next;
    }
}
