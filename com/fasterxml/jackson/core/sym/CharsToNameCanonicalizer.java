package com.fasterxml.jackson.core.sym;

import java.util.Arrays;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.core.JsonFactory;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

public final class CharsToNameCanonicalizer
{
    public static final int HASH_MULT = 33;
    private static final int DEFAULT_T_SIZE = 64;
    private static final int MAX_T_SIZE = 65536;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    static final int MAX_COLL_CHAIN_LENGTH = 100;
    private final CharsToNameCanonicalizer _parent;
    private final AtomicReference<TableInfo> _tableInfo;
    private final int _seed;
    private final int _flags;
    private boolean _canonicalize;
    private String[] _symbols;
    private Bucket[] _buckets;
    private int _size;
    private int _sizeThreshold;
    private int _indexMask;
    private int _longestCollisionList;
    private boolean _hashShared;
    private BitSet _overflows;
    
    private CharsToNameCanonicalizer(final int seed) {
        super();
        this._parent = null;
        this._seed = seed;
        this._canonicalize = true;
        this._flags = -1;
        this._hashShared = false;
        this._longestCollisionList = 0;
        this._tableInfo = new AtomicReference<TableInfo>(TableInfo.createInitial(64));
    }
    
    private CharsToNameCanonicalizer(final CharsToNameCanonicalizer parent, final int flags, final int seed, final TableInfo tableInfo) {
        super();
        this._parent = parent;
        this._seed = seed;
        this._tableInfo = null;
        this._flags = flags;
        this._canonicalize = JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(flags);
        this._symbols = tableInfo.symbols;
        this._buckets = tableInfo.buckets;
        this._size = tableInfo.size;
        this._longestCollisionList = tableInfo.longestCollisionList;
        final int length = this._symbols.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._hashShared = true;
    }
    
    private static int _thresholdSize(final int n) {
        return n - (n >> 2);
    }
    
    public static CharsToNameCanonicalizer createRoot() {
        final long currentTimeMillis = System.currentTimeMillis();
        return createRoot((int)currentTimeMillis + (int)(currentTimeMillis >>> 32) | 0x1);
    }
    
    protected static CharsToNameCanonicalizer createRoot(final int n) {
        return new CharsToNameCanonicalizer(n);
    }
    
    public CharsToNameCanonicalizer makeChild(final int n) {
        return new CharsToNameCanonicalizer(this, n, this._seed, this._tableInfo.get());
    }
    
    public void release() {
        if (!this.maybeDirty()) {
            return;
        }
        if (this._parent != null && this._canonicalize) {
            this._parent.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }
    
    private void mergeChild(TableInfo initial) {
        final int size = initial.size;
        final TableInfo tableInfo = this._tableInfo.get();
        if (size == tableInfo.size) {
            return;
        }
        if (size > 12000) {
            initial = TableInfo.createInitial(64);
        }
        this._tableInfo.compareAndSet(tableInfo, initial);
    }
    
    public int size() {
        if (this._tableInfo != null) {
            return this._tableInfo.get().size;
        }
        return this._size;
    }
    
    public int bucketCount() {
        return this._symbols.length;
    }
    
    public boolean maybeDirty() {
        return !this._hashShared;
    }
    
    public int hashSeed() {
        return this._seed;
    }
    
    public int collisionCount() {
        int n = 0;
        for (final Bucket bucket : this._buckets) {
            if (bucket != null) {
                n += bucket.length;
            }
        }
        return n;
    }
    
    public int maxCollisionLength() {
        return this._longestCollisionList;
    }
    
    public String findSymbol(final char[] array, final int n, final int n2, final int n3) {
        if (n2 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(array, n, n2);
        }
        final int hashToIndex = this._hashToIndex(n3);
        final String s = this._symbols[hashToIndex];
        if (s != null) {
            if (s.length() == n2) {
                int n4 = 0;
                while (s.charAt(n4) == array[n + n4]) {
                    if (++n4 == n2) {
                        return s;
                    }
                }
            }
            final Bucket bucket = this._buckets[hashToIndex >> 1];
            if (bucket != null) {
                final String has = bucket.has(array, n, n2);
                if (has != null) {
                    return has;
                }
                final String findSymbol2 = this._findSymbol2(array, n, n2, bucket.next);
                if (findSymbol2 != null) {
                    return findSymbol2;
                }
            }
        }
        return this._addSymbol(array, n, n2, n3, hashToIndex);
    }
    
    private String _findSymbol2(final char[] array, final int n, final int n2, Bucket next) {
        while (next != null) {
            final String has = next.has(array, n, n2);
            if (has != null) {
                return has;
            }
            next = next.next;
        }
        return null;
    }
    
    private String _addSymbol(final char[] array, final int n, final int n2, final int n3, int hashToIndex) {
        if (this._hashShared) {
            this.copyArrays();
            this._hashShared = false;
        }
        else if (this._size >= this._sizeThreshold) {
            this.rehash();
            hashToIndex = this._hashToIndex(this.calcHash(array, n, n2));
        }
        String intern = new String(array, n, n2);
        if (JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
            intern = InternCache.instance.intern(intern);
        }
        ++this._size;
        if (this._symbols[hashToIndex] == null) {
            this._symbols[hashToIndex] = intern;
        }
        else {
            final int n4 = hashToIndex >> 1;
            final Bucket bucket = new Bucket(intern, this._buckets[n4]);
            final int length = bucket.length;
            if (length > 100) {
                this._handleSpillOverflow(n4, bucket);
            }
            else {
                this._buckets[n4] = bucket;
                this._longestCollisionList = Math.max(length, this._longestCollisionList);
            }
        }
        return intern;
    }
    
    private void _handleSpillOverflow(final int n, final Bucket bucket) {
        if (this._overflows == null) {
            (this._overflows = new BitSet()).set(n);
        }
        else if (this._overflows.get(n)) {
            if (JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
                this.reportTooManyCollisions(100);
            }
            this._canonicalize = false;
        }
        else {
            this._overflows.set(n);
        }
        this._symbols[n + n] = bucket.symbol;
        this._buckets[n] = null;
        this._size -= bucket.length;
        this._longestCollisionList = -1;
    }
    
    public int _hashToIndex(int n) {
        n += n >>> 15;
        n ^= n << 7;
        n += n >>> 3;
        return n & this._indexMask;
    }
    
    public int calcHash(final char[] array, final int n, final int n2) {
        int seed = this._seed;
        for (int i = n; i < n + n2; ++i) {
            seed = seed * 33 + array[i];
        }
        return 1;
    }
    
    public int calcHash(final String s) {
        final int length = s.length();
        int seed = this._seed;
        for (int i = 0; i < length; ++i) {
            seed = seed * 33 + s.charAt(i);
        }
        return 1;
    }
    
    private void copyArrays() {
        final String[] symbols = this._symbols;
        this._symbols = Arrays.<String>copyOf(symbols, symbols.length);
        final Bucket[] buckets = this._buckets;
        this._buckets = Arrays.<Bucket>copyOf(buckets, buckets.length);
    }
    
    private void rehash() {
        final int length = this._symbols.length;
        final int n = length + length;
        if (n > 65536) {
            this._size = 0;
            this._canonicalize = false;
            this._symbols = new String[64];
            this._buckets = new Bucket[32];
            this._indexMask = 63;
            this._hashShared = false;
            return;
        }
        final String[] symbols = this._symbols;
        final Bucket[] buckets = this._buckets;
        this._symbols = new String[n];
        this._buckets = new Bucket[n >> 1];
        this._indexMask = n - 1;
        this._sizeThreshold = _thresholdSize(n);
        int n2 = 0;
        int longestCollisionList = 0;
        for (final String s : symbols) {
            if (s != null) {
                ++n2;
                final int hashToIndex = this._hashToIndex(this.calcHash(s));
                if (this._symbols[hashToIndex] == null) {
                    this._symbols[hashToIndex] = s;
                }
                else {
                    final int n3 = hashToIndex >> 1;
                    final Bucket bucket = new Bucket(s, this._buckets[n3]);
                    this._buckets[n3] = bucket;
                    longestCollisionList = Math.max(longestCollisionList, bucket.length);
                }
            }
        }
        for (int n4 = length >> 1, j = 0; j < n4; ++j) {
            for (Bucket next = buckets[j]; next != null; next = next.next) {
                ++n2;
                final String symbol = next.symbol;
                final int hashToIndex2 = this._hashToIndex(this.calcHash(symbol));
                if (this._symbols[hashToIndex2] == null) {
                    this._symbols[hashToIndex2] = symbol;
                }
                else {
                    final int n5 = hashToIndex2 >> 1;
                    final Bucket bucket2 = new Bucket(symbol, this._buckets[n5]);
                    this._buckets[n5] = bucket2;
                    longestCollisionList = Math.max(longestCollisionList, bucket2.length);
                }
            }
        }
        this._longestCollisionList = longestCollisionList;
        this._overflows = null;
        if (n2 != this._size) {
            throw new IllegalStateException(String.format("Internal error on SymbolTable.rehash(): had %d entries; now have %d", this._size, n2));
        }
    }
    
    protected void reportTooManyCollisions(final int n) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + n + " -- suspect a DoS attack based on hash collisions");
    }
    
    static /* synthetic */ int access$000(final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        return charsToNameCanonicalizer._size;
    }
    
    static /* synthetic */ int access$100(final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        return charsToNameCanonicalizer._longestCollisionList;
    }
    
    static /* synthetic */ String[] access$200(final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        return charsToNameCanonicalizer._symbols;
    }
    
    static /* synthetic */ Bucket[] access$300(final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        return charsToNameCanonicalizer._buckets;
    }
}
