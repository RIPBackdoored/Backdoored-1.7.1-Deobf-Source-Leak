package com.fasterxml.jackson.core.sym;

import java.util.Arrays;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.core.JsonFactory;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer
{
    private static final int DEFAULT_T_SIZE = 64;
    private static final int MAX_T_SIZE = 65536;
    private static final int MIN_HASH_SIZE = 16;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    private final ByteQuadsCanonicalizer _parent;
    private final AtomicReference<TableInfo> _tableInfo;
    private final int _seed;
    private boolean _intern;
    private final boolean _failOnDoS;
    private int[] _hashArea;
    private int _hashSize;
    private int _secondaryStart;
    private int _tertiaryStart;
    private int _tertiaryShift;
    private int _count;
    private String[] _names;
    private int _spilloverEnd;
    private int _longNameOffset;
    private transient boolean _needRehash;
    private boolean _hashShared;
    private static final int MULT = 33;
    private static final int MULT2 = 65599;
    private static final int MULT3 = 31;
    
    private ByteQuadsCanonicalizer(int n, final boolean intern, final int seed, final boolean failOnDoS) {
        super();
        this._parent = null;
        this._seed = seed;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        if (n < 16) {
            n = 16;
        }
        else if ((n & n - 1) != 0x0) {
            int i;
            for (i = 16; i < n; i += i) {}
            n = i;
        }
        this._tableInfo = new AtomicReference<TableInfo>(TableInfo.createInitial(n));
    }
    
    private ByteQuadsCanonicalizer(final ByteQuadsCanonicalizer parent, final boolean intern, final int seed, final boolean failOnDoS, final TableInfo tableInfo) {
        super();
        this._parent = parent;
        this._seed = seed;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        this._tableInfo = null;
        this._count = tableInfo.count;
        this._hashSize = tableInfo.size;
        this._secondaryStart = this._hashSize << 2;
        this._tertiaryStart = this._secondaryStart + (this._secondaryStart >> 1);
        this._tertiaryShift = tableInfo.tertiaryShift;
        this._hashArea = tableInfo.mainHash;
        this._names = tableInfo.names;
        this._spilloverEnd = tableInfo.spilloverEnd;
        this._longNameOffset = tableInfo.longNameOffset;
        this._needRehash = false;
        this._hashShared = true;
    }
    
    public static ByteQuadsCanonicalizer createRoot() {
        final long currentTimeMillis = System.currentTimeMillis();
        return createRoot((int)currentTimeMillis + (int)(currentTimeMillis >>> 32) | 0x1);
    }
    
    protected static ByteQuadsCanonicalizer createRoot(final int n) {
        return new ByteQuadsCanonicalizer(64, true, n, true);
    }
    
    public ByteQuadsCanonicalizer makeChild(final int n) {
        return new ByteQuadsCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(n), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(n), this._tableInfo.get());
    }
    
    public void release() {
        if (this._parent != null && this.maybeDirty()) {
            this._parent.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }
    
    private void mergeChild(TableInfo initial) {
        final int count = initial.count;
        final TableInfo tableInfo = this._tableInfo.get();
        if (count == tableInfo.count) {
            return;
        }
        if (count > 6000) {
            initial = TableInfo.createInitial(64);
        }
        this._tableInfo.compareAndSet(tableInfo, initial);
    }
    
    public int size() {
        if (this._tableInfo != null) {
            return this._tableInfo.get().count;
        }
        return this._count;
    }
    
    public int bucketCount() {
        return this._hashSize;
    }
    
    public boolean maybeDirty() {
        return !this._hashShared;
    }
    
    public int hashSeed() {
        return this._seed;
    }
    
    public int primaryCount() {
        int n = 0;
        for (int i = 3; i < this._secondaryStart; i += 4) {
            if (this._hashArea[i] != 0) {
                ++n;
            }
        }
        return n;
    }
    
    public int secondaryCount() {
        int n = 0;
        for (int i = this._secondaryStart + 3; i < this._tertiaryStart; i += 4) {
            if (this._hashArea[i] != 0) {
                ++n;
            }
        }
        return n;
    }
    
    public int tertiaryCount() {
        int n = 0;
        for (int i = this._tertiaryStart + 3; i < i + this._hashSize; i += 4) {
            if (this._hashArea[i] != 0) {
                ++n;
            }
        }
        return n;
    }
    
    public int spilloverCount() {
        return this._spilloverEnd - this._spilloverStart() >> 2;
    }
    
    public int totalCount() {
        int n = 0;
        for (int i = 3; i < this._hashSize << 3; i += 4) {
            if (this._hashArea[i] != 0) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        final int primaryCount = this.primaryCount();
        final int secondaryCount = this.secondaryCount();
        final int tertiaryCount = this.tertiaryCount();
        final int spilloverCount = this.spilloverCount();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", this.getClass().getName(), this._count, this._hashSize, primaryCount, secondaryCount, tertiaryCount, spilloverCount, primaryCount + secondaryCount + tertiaryCount + spilloverCount, this.totalCount());
    }
    
    public String findName(final int n) {
        final int calcOffset = this._calcOffset(this.calcHash(n));
        final int[] hashArea = this._hashArea;
        if (hashArea[calcOffset + 3] != 1) {
            return null;
        }
        if (hashArea[calcOffset] == n) {
            return this._names[calcOffset >> 2];
        }
        final int n2 = this._secondaryStart + (calcOffset >> 3 << 2);
        if (hashArea[n2 + 3] != 1) {
            return null;
        }
        if (hashArea[n2] == n) {
            return this._names[n2 >> 2];
        }
        return this._findSecondary(calcOffset, n);
    }
    
    public String findName(final int n, final int n2) {
        final int calcOffset = this._calcOffset(this.calcHash(n, n2));
        final int[] hashArea = this._hashArea;
        if (hashArea[calcOffset + 3] != 2) {
            return null;
        }
        if (n == hashArea[calcOffset] && n2 == hashArea[calcOffset + 1]) {
            return this._names[calcOffset >> 2];
        }
        final int n3 = this._secondaryStart + (calcOffset >> 3 << 2);
        if (hashArea[n3 + 3] != 2) {
            return null;
        }
        if (n == hashArea[n3] && n2 == hashArea[n3 + 1]) {
            return this._names[n3 >> 2];
        }
        return this._findSecondary(calcOffset, n, n2);
    }
    
    public String findName(final int n, final int n2, final int n3) {
        final int calcOffset = this._calcOffset(this.calcHash(n, n2, n3));
        final int[] hashArea = this._hashArea;
        if (hashArea[calcOffset + 3] != 3) {
            return null;
        }
        if (n == hashArea[calcOffset] && hashArea[calcOffset + 1] == n2 && hashArea[calcOffset + 2] == n3) {
            return this._names[calcOffset >> 2];
        }
        final int n4 = this._secondaryStart + (calcOffset >> 3 << 2);
        if (hashArea[n4 + 3] != 3) {
            return null;
        }
        if (n == hashArea[n4] && hashArea[n4 + 1] == n2 && hashArea[n4 + 2] == n3) {
            return this._names[n4 >> 2];
        }
        return this._findSecondary(calcOffset, n, n2, n3);
    }
    
    public String findName(final int[] array, final int n) {
        if (n < 4) {
            switch (n) {
                case 3:
                    return this.findName(array[0], array[1], array[2]);
                case 2:
                    return this.findName(array[0], array[1]);
                case 1:
                    return this.findName(array[0]);
                default:
                    return "";
            }
        }
        else {
            final int calcHash = this.calcHash(array, n);
            final int calcOffset = this._calcOffset(calcHash);
            final int[] hashArea = this._hashArea;
            final int n2 = hashArea[calcOffset + 3];
            if (calcHash == hashArea[calcOffset] && n2 == n && this._verifyLongName(array, n, hashArea[calcOffset + 1])) {
                return this._names[calcOffset >> 2];
            }
            return null;
        }
    }
    
    private final int _calcOffset(final int n) {
        return (n & this._hashSize - 1) << 2;
    }
    
    private String _findSecondary(final int n, final int n2) {
        final int n3 = this._tertiaryStart + (n >> this._tertiaryShift + 2 << this._tertiaryShift);
        final int[] hashArea = this._hashArea;
        if (n3 >= n3 + (1 << this._tertiaryShift)) {
            for (int i = this._spilloverStart(); i < this._spilloverEnd; i += 4) {
                if (n2 == hashArea[i] && 1 == hashArea[i + 3]) {
                    return this._names[i >> 2];
                }
            }
            return null;
        }
        final int n4 = hashArea[n3 + 3];
        if (n2 == hashArea[n3] && n4) {
            return this._names[n3 >> 2];
        }
        return null;
    }
    
    private String _findSecondary(final int n, final int n2, final int n3) {
        final int n4 = this._tertiaryStart + (n >> this._tertiaryShift + 2 << this._tertiaryShift);
        final int[] hashArea = this._hashArea;
        if (n4 >= n4 + (1 << this._tertiaryShift)) {
            for (int i = this._spilloverStart(); i < this._spilloverEnd; i += 4) {
                if (n2 == hashArea[i] && n3 == hashArea[i + 1] && 2 == hashArea[i + 3]) {
                    return this._names[i >> 2];
                }
            }
            return null;
        }
        final int n5 = hashArea[n4 + 3];
        if (n2 == hashArea[n4] && n3 == hashArea[n4 + 1] && 2 == n5) {
            return this._names[n4 >> 2];
        }
        return null;
    }
    
    private String _findSecondary(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this._tertiaryStart + (n >> this._tertiaryShift + 2 << this._tertiaryShift);
        final int[] hashArea = this._hashArea;
        if (n5 >= n5 + (1 << this._tertiaryShift)) {
            for (int i = this._spilloverStart(); i < this._spilloverEnd; i += 4) {
                if (n2 == hashArea[i] && n3 == hashArea[i + 1] && n4 == hashArea[i + 2] && 3 == hashArea[i + 3]) {
                    return this._names[i >> 2];
                }
            }
            return null;
        }
        final int n6 = hashArea[n5 + 3];
        if (n2 == hashArea[n5] && n3 == hashArea[n5 + 1] && n4 == hashArea[n5 + 2] && 3 == n6) {
            return this._names[n5 >> 2];
        }
        return null;
    }
    
    private String _findSecondary(final int n, final int n2, final int[] array, final int n3) {
        final int n4 = this._tertiaryStart + (n >> this._tertiaryShift + 2 << this._tertiaryShift);
        final int[] hashArea = this._hashArea;
        if (n4 >= n4 + (1 << this._tertiaryShift)) {
            for (int i = this._spilloverStart(); i < this._spilloverEnd; i += 4) {
                if (n2 == hashArea[i] && n3 == hashArea[i + 3] && this._verifyLongName(array, n3, hashArea[i + 1])) {
                    return this._names[i >> 2];
                }
            }
            return null;
        }
        final int n5 = hashArea[n4 + 3];
        if (n2 == hashArea[n4] && n3 == n5 && this._verifyLongName(array, n3, hashArea[n4 + 1])) {
            return this._names[n4 >> 2];
        }
        return null;
    }
    
    private boolean _verifyLongName(final int[] array, final int n, int n2) {
        final int[] hashArea = this._hashArea;
        int n3 = 0;
        switch (n) {
            default:
                return this._verifyLongName2(array, n, n2);
            case 8:
                if (array[n3++] != hashArea[n2++]) {
                    return false;
                }
            case 7:
                if (array[n3++] != hashArea[n2++]) {
                    return false;
                }
            case 6:
                if (array[n3++] != hashArea[n2++]) {
                    return false;
                }
            case 5:
                if (array[n3++] != hashArea[n2++]) {
                    return false;
                }
                return array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++];
            case 4:
                return array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++] && array[n3++] == hashArea[n2++];
        }
    }
    
    private boolean _verifyLongName2(final int[] array, final int n, int n2) {
        int n3 = 0;
        while (array[n3++] == this._hashArea[n2++]) {
            if (n3 >= n) {
                return true;
            }
        }
        return false;
    }
    
    public String addName(String intern, final int n) {
        this._verifySharing();
        if (this._intern) {
            intern = InternCache.instance.intern(intern);
        }
        final int findOffsetForAdd = this._findOffsetForAdd(this.calcHash(n));
        this._hashArea[findOffsetForAdd] = n;
        this._hashArea[findOffsetForAdd + 3] = 1;
        this._names[findOffsetForAdd >> 2] = intern;
        ++this._count;
        this._verifyNeedForRehash();
        return intern;
    }
    
    public String addName(String intern, final int n, final int n2) {
        this._verifySharing();
        if (this._intern) {
            intern = InternCache.instance.intern(intern);
        }
        final int findOffsetForAdd = this._findOffsetForAdd(this.calcHash(n));
        this._hashArea[findOffsetForAdd] = n;
        this._hashArea[findOffsetForAdd + 1] = n2;
        this._hashArea[findOffsetForAdd + 3] = 2;
        this._names[findOffsetForAdd >> 2] = intern;
        ++this._count;
        this._verifyNeedForRehash();
        return intern;
    }
    
    public String addName(String intern, final int n, final int n2, final int n3) {
        this._verifySharing();
        if (this._intern) {
            intern = InternCache.instance.intern(intern);
        }
        final int findOffsetForAdd = this._findOffsetForAdd(this.calcHash(n, n2, n3));
        this._hashArea[findOffsetForAdd] = n;
        this._hashArea[findOffsetForAdd + 1] = n2;
        this._hashArea[findOffsetForAdd + 2] = n3;
        this._hashArea[findOffsetForAdd + 3] = 3;
        this._names[findOffsetForAdd >> 2] = intern;
        ++this._count;
        this._verifyNeedForRehash();
        return intern;
    }
    
    public String addName(String intern, final int[] array, final int n) {
        this._verifySharing();
        if (this._intern) {
            intern = InternCache.instance.intern(intern);
        }
        int n2 = 0;
        switch (n) {
            case 1:
                n2 = this._findOffsetForAdd(this.calcHash(array[0]));
                this._hashArea[n2] = array[0];
                this._hashArea[n2 + 3] = 1;
                break;
            case 2:
                n2 = this._findOffsetForAdd(this.calcHash(array[0], array[1]));
                this._hashArea[n2] = array[0];
                this._hashArea[n2 + 1] = array[1];
                this._hashArea[n2 + 3] = 2;
                break;
            case 3:
                n2 = this._findOffsetForAdd(this.calcHash(array[0], array[1], array[2]));
                this._hashArea[n2] = array[0];
                this._hashArea[n2 + 1] = array[1];
                this._hashArea[n2 + 2] = array[2];
                this._hashArea[n2 + 3] = 3;
                break;
            default: {
                final int calcHash = this.calcHash(array, n);
                n2 = this._findOffsetForAdd(calcHash);
                this._hashArea[n2] = calcHash;
                this._hashArea[n2 + 1] = this._appendLongName(array, n);
                this._hashArea[n2 + 3] = n;
                break;
            }
        }
        this._names[n2 >> 2] = intern;
        ++this._count;
        this._verifyNeedForRehash();
        return intern;
    }
    
    private void _verifyNeedForRehash() {
        if (this._count > this._hashSize >> 1 && (this._spilloverEnd - this._spilloverStart() >> 2 > 1 + this._count >> 7 || this._count > this._hashSize * 0.8)) {
            this._needRehash = true;
        }
    }
    
    private void _verifySharing() {
        if (this._hashShared) {
            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length);
            this._names = Arrays.<String>copyOf(this._names, this._names.length);
            this._hashShared = false;
            this._verifyNeedForRehash();
        }
        if (this._needRehash) {
            this.rehash();
        }
    }
    
    private int _findOffsetForAdd(final int n) {
        final int calcOffset = this._calcOffset(n);
        final int[] hashArea = this._hashArea;
        if (hashArea[calcOffset + 3] == 0) {
            return calcOffset;
        }
        final int n2 = this._secondaryStart + (calcOffset >> 3 << 2);
        if (hashArea[n2 + 3] == 0) {
            return n2;
        }
        for (int i = this._tertiaryStart + (calcOffset >> this._tertiaryShift + 2 << this._tertiaryShift); i < i + (1 << this._tertiaryShift); i += 4) {
            if (hashArea[i + 3] == 0) {
                return i;
            }
        }
        final int spilloverEnd = this._spilloverEnd;
        this._spilloverEnd += 4;
        if (this._spilloverEnd >= this._hashSize << 3) {
            if (this._failOnDoS) {
                this._reportTooManyCollisions();
            }
            this._needRehash = true;
        }
        return spilloverEnd;
    }
    
    private int _appendLongName(final int[] array, final int n) {
        final int longNameOffset = this._longNameOffset;
        if (longNameOffset + n > this._hashArea.length) {
            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + Math.max(longNameOffset + n - this._hashArea.length, Math.min(4096, this._hashSize)));
        }
        System.arraycopy(array, 0, this._hashArea, longNameOffset, n);
        this._longNameOffset += n;
        return longNameOffset;
    }
    
    public int calcHash(final int n) {
        final int n2 = n ^ this._seed;
        final int n3 = n2 + (n2 >>> 16);
        final int n4 = n3 ^ n3 << 3;
        return n4 + (n4 >>> 12);
    }
    
    public int calcHash(final int n, final int n2) {
        final int n3 = n + (n >>> 15);
        final int n4 = (n3 ^ n3 >>> 9) + n2 * 33 ^ this._seed;
        final int n5 = n4 + (n4 >>> 16);
        final int n6 = n5 ^ n5 >>> 4;
        return n6 + (n6 << 3);
    }
    
    public int calcHash(final int n, final int n2, final int n3) {
        final int n4 = n ^ this._seed;
        final int n5 = ((n4 + (n4 >>> 9)) * 31 + n2) * 33;
        final int n6 = n5 + (n5 >>> 15) ^ n3;
        final int n7 = n6 + (n6 >>> 4);
        final int n8 = n7 + (n7 >>> 15);
        return n8 ^ n8 << 9;
    }
    
    public int calcHash(final int[] array, final int n) {
        if (n < 4) {
            throw new IllegalArgumentException();
        }
        final int n2 = array[0] ^ this._seed;
        final int n3 = n2 + (n2 >>> 9) + array[1];
        final int n4 = (n3 + (n3 >>> 15)) * 33 ^ array[2];
        int n5 = n4 + (n4 >>> 4);
        for (int i = 3; i < n; ++i) {
            final int n6 = array[i];
            n5 += (n6 ^ n6 >> 21);
        }
        final int n7 = n5 * 65599;
        final int n8 = n7 + (n7 >>> 19);
        return n8 ^ n8 << 5;
    }
    
    private void rehash() {
        this._needRehash = false;
        this._hashShared = false;
        final int[] hashArea = this._hashArea;
        final String[] names = this._names;
        final int hashSize = this._hashSize;
        final int count = this._count;
        final int hashSize2 = hashSize + hashSize;
        final int spilloverEnd = this._spilloverEnd;
        if (hashSize2 > 65536) {
            this.nukeSymbols(true);
            return;
        }
        this._hashArea = new int[hashArea.length + (hashSize << 3)];
        this._hashSize = hashSize2;
        this._secondaryStart = hashSize2 << 2;
        this._tertiaryStart = this._secondaryStart + (this._secondaryStart >> 1);
        this._tertiaryShift = _calcTertiaryShift(hashSize2);
        this._names = new String[names.length << 1];
        this.nukeSymbols(false);
        final int n = 0;
        final int[] array = new int[16];
        for (int i = 0; i < spilloverEnd; i += 4) {
            final int n2 = hashArea[i + 3];
        }
        if (n != count) {
            throw new IllegalStateException("Failed rehash(): old count=" + count + ", copyCount=" + n);
        }
    }
    
    private void nukeSymbols(final boolean b) {
        this._count = 0;
        this._spilloverEnd = this._spilloverStart();
        this._longNameOffset = this._hashSize << 3;
        if (b) {
            Arrays.fill(this._hashArea, 0);
            Arrays.fill(this._names, null);
        }
    }
    
    private final int _spilloverStart() {
        final int hashSize = this._hashSize;
        return (hashSize << 3) - hashSize;
    }
    
    protected void _reportTooManyCollisions() {
        if (this._hashSize <= 1024) {
            return;
        }
        throw new IllegalStateException("Spill-over slots in symbol table with " + this._count + " entries, hash area of " + this._hashSize + " slots is now full (all " + (this._hashSize >> 3) + " slots -- suspect a DoS attack based on hash collisions." + " You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
    }
    
    static int _calcTertiaryShift(final int n) {
        final int n2 = n >> 2;
        if (n2 < 64) {
            return 4;
        }
        if (n2 <= 256) {
            return 5;
        }
        if (n2 <= 1024) {
            return 6;
        }
        return 7;
    }
    
    static /* synthetic */ int access$000(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._hashSize;
    }
    
    static /* synthetic */ int access$100(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._count;
    }
    
    static /* synthetic */ int access$200(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._tertiaryShift;
    }
    
    static /* synthetic */ int[] access$300(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._hashArea;
    }
    
    static /* synthetic */ String[] access$400(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._names;
    }
    
    static /* synthetic */ int access$500(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._spilloverEnd;
    }
    
    static /* synthetic */ int access$600(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        return byteQuadsCanonicalizer._longNameOffset;
    }
}
