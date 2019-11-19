package com.fasterxml.jackson.core.sym;

private static final class TableInfo
{
    public final int size;
    public final int count;
    public final int tertiaryShift;
    public final int[] mainHash;
    public final String[] names;
    public final int spilloverEnd;
    public final int longNameOffset;
    
    public TableInfo(final int size, final int count, final int tertiaryShift, final int[] mainHash, final String[] names, final int spilloverEnd, final int longNameOffset) {
        super();
        this.size = size;
        this.count = count;
        this.tertiaryShift = tertiaryShift;
        this.mainHash = mainHash;
        this.names = names;
        this.spilloverEnd = spilloverEnd;
        this.longNameOffset = longNameOffset;
    }
    
    public TableInfo(final ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super();
        this.size = ByteQuadsCanonicalizer.access$000(byteQuadsCanonicalizer);
        this.count = ByteQuadsCanonicalizer.access$100(byteQuadsCanonicalizer);
        this.tertiaryShift = ByteQuadsCanonicalizer.access$200(byteQuadsCanonicalizer);
        this.mainHash = ByteQuadsCanonicalizer.access$300(byteQuadsCanonicalizer);
        this.names = ByteQuadsCanonicalizer.access$400(byteQuadsCanonicalizer);
        this.spilloverEnd = ByteQuadsCanonicalizer.access$500(byteQuadsCanonicalizer);
        this.longNameOffset = ByteQuadsCanonicalizer.access$600(byteQuadsCanonicalizer);
    }
    
    public static TableInfo createInitial(final int n) {
        final int n2 = n << 3;
        return new TableInfo(n, 0, ByteQuadsCanonicalizer._calcTertiaryShift(n), new int[n2], new String[n << 1], n2 - n, n2);
    }
}
