package com.sun.jna;

public abstract class IntegerType extends Number implements NativeMapped
{
    private static final long serialVersionUID = 1L;
    private int size;
    private Number number;
    private boolean unsigned;
    private long value;
    
    public IntegerType(final int n) {
        this(n, 0L, false);
    }
    
    public IntegerType(final int n, final boolean b) {
        this(n, 0L, b);
    }
    
    public IntegerType(final int n, final long n2) {
        this(n, n2, false);
    }
    
    public IntegerType(final int size, final long value, final boolean unsigned) {
        super();
        this.size = size;
        this.unsigned = unsigned;
        this.setValue(value);
    }
    
    public void setValue(final long value) {
        long n = value;
        this.value = value;
        switch (this.size) {
            case 1:
                if (this.unsigned) {
                    this.value = (value & 0xFFL);
                }
                n = (byte)value;
                this.number = (byte)value;
                break;
            case 2:
                if (this.unsigned) {
                    this.value = (value & 0xFFFFL);
                }
                n = (short)value;
                this.number = (short)value;
                break;
            case 4:
                if (this.unsigned) {
                    this.value = (value & 0xFFFFFFFFL);
                }
                n = (int)value;
                this.number = (int)value;
                break;
            case 8:
                this.number = value;
                break;
            default:
                throw new IllegalArgumentException("Unsupported size: " + this.size);
        }
        if (this.size < 8) {
            final long n2 = ~((1L << this.size * 8) - 1L);
            if ((value < 0L && n != value) || (value >= 0L && (n2 & value) != 0x0L)) {
                throw new IllegalArgumentException("Argument value 0x" + Long.toHexString(value) + " exceeds native capacity (" + this.size + " bytes) mask=0x" + Long.toHexString(n2));
            }
        }
    }
    
    @Override
    public Object toNative() {
        return this.number;
    }
    
    @Override
    public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
        final long value = (o == null) ? 0L : ((Number)o).longValue();
        try {
            final IntegerType integerType = (IntegerType)this.getClass().newInstance();
            integerType.setValue(value);
            return integerType;
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Can't instantiate " + this.getClass());
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Not allowed to instantiate " + this.getClass());
        }
    }
    
    @Override
    public Class<?> nativeType() {
        return this.number.getClass();
    }
    
    @Override
    public int intValue() {
        return (int)this.value;
    }
    
    @Override
    public long longValue() {
        return this.value;
    }
    
    @Override
    public float floatValue() {
        return this.number.floatValue();
    }
    
    @Override
    public double doubleValue() {
        return this.number.doubleValue();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof IntegerType && this.number.equals(((IntegerType)o).number);
    }
    
    @Override
    public String toString() {
        return this.number.toString();
    }
    
    @Override
    public int hashCode() {
        return this.number.hashCode();
    }
    
    public static <T extends IntegerType> int compare(final T t, final T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return compare(t.longValue(), t2.longValue());
    }
    
    public static int compare(final IntegerType integerType, final long n) {
        if (integerType == null) {
            return 1;
        }
        return compare(integerType.longValue(), n);
    }
    
    public static final int compare(final long n, final long n2) {
        if (n == n2) {
            return 0;
        }
        if (n < n2) {
            return -1;
        }
        return 1;
    }
}
