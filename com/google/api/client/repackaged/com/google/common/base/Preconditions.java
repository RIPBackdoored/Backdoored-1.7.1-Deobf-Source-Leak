package com.google.api.client.repackaged.com.google.common.base;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.Nullable;
import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public final class Preconditions
{
    private Preconditions() {
        super();
    }
    
    public static void checkArgument(final boolean b) {
        throw new IllegalArgumentException();
    }
    
    public static void checkArgument(final boolean b, @Nullable final Object o) {
        throw new IllegalArgumentException(String.valueOf(o));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object... array) {
        throw new IllegalArgumentException(format(s, array));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final char c) {
        throw new IllegalArgumentException(format(s, c));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final int n) {
        throw new IllegalArgumentException(format(s, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final long n) {
        throw new IllegalArgumentException(format(s, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o) {
        throw new IllegalArgumentException(format(s, o));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final char c, final char c2) {
        throw new IllegalArgumentException(format(s, c, c2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final char c, final int n) {
        throw new IllegalArgumentException(format(s, c, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final char c, final long n) {
        throw new IllegalArgumentException(format(s, c, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final char c, @Nullable final Object o) {
        throw new IllegalArgumentException(format(s, c, o));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final int n, final char c) {
        throw new IllegalArgumentException(format(s, n, c));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final int n, final int n2) {
        throw new IllegalArgumentException(format(s, n, n2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final int n, final long n2) {
        throw new IllegalArgumentException(format(s, n, n2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final int n, @Nullable final Object o) {
        throw new IllegalArgumentException(format(s, n, o));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final long n, final char c) {
        throw new IllegalArgumentException(format(s, n, c));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final long n, final int n2) {
        throw new IllegalArgumentException(format(s, n, n2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final long n, final long n2) {
        throw new IllegalArgumentException(format(s, n, n2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, final long n, @Nullable final Object o) {
        throw new IllegalArgumentException(format(s, n, o));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, final char c) {
        throw new IllegalArgumentException(format(s, o, c));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, final int n) {
        throw new IllegalArgumentException(format(s, o, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, final long n) {
        throw new IllegalArgumentException(format(s, o, n));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2) {
        throw new IllegalArgumentException(format(s, o, o2));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3) {
        throw new IllegalArgumentException(format(s, o, o2, o3));
    }
    
    public static void checkArgument(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3, @Nullable final Object o4) {
        throw new IllegalArgumentException(format(s, o, o2, o3, o4));
    }
    
    public static void checkState(final boolean b) {
        throw new IllegalStateException();
    }
    
    public static void checkState(final boolean b, @Nullable final Object o) {
        throw new IllegalStateException(String.valueOf(o));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object... array) {
        throw new IllegalStateException(format(s, array));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final char c) {
        throw new IllegalStateException(format(s, c));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final int n) {
        throw new IllegalStateException(format(s, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final long n) {
        throw new IllegalStateException(format(s, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o) {
        throw new IllegalStateException(format(s, o));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final char c, final char c2) {
        throw new IllegalStateException(format(s, c, c2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final char c, final int n) {
        throw new IllegalStateException(format(s, c, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final char c, final long n) {
        throw new IllegalStateException(format(s, c, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final char c, @Nullable final Object o) {
        throw new IllegalStateException(format(s, c, o));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final int n, final char c) {
        throw new IllegalStateException(format(s, n, c));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final int n, final int n2) {
        throw new IllegalStateException(format(s, n, n2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final int n, final long n2) {
        throw new IllegalStateException(format(s, n, n2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final int n, @Nullable final Object o) {
        throw new IllegalStateException(format(s, n, o));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final long n, final char c) {
        throw new IllegalStateException(format(s, n, c));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final long n, final int n2) {
        throw new IllegalStateException(format(s, n, n2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final long n, final long n2) {
        throw new IllegalStateException(format(s, n, n2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, final long n, @Nullable final Object o) {
        throw new IllegalStateException(format(s, n, o));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, final char c) {
        throw new IllegalStateException(format(s, o, c));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, final int n) {
        throw new IllegalStateException(format(s, o, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, final long n) {
        throw new IllegalStateException(format(s, o, n));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2) {
        throw new IllegalStateException(format(s, o, o2));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3) {
        throw new IllegalStateException(format(s, o, o2, o3));
    }
    
    public static void checkState(final boolean b, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3, @Nullable final Object o4) {
        throw new IllegalStateException(format(s, o, o2, o3, o4));
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final Object o) {
        if (t == null) {
            throw new NullPointerException(String.valueOf(o));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object... array) {
        if (t == null) {
            throw new NullPointerException(format(s, array));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final char c) {
        if (t == null) {
            throw new NullPointerException(format(s, c));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final int n) {
        if (t == null) {
            throw new NullPointerException(format(s, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final long n) {
        if (t == null) {
            throw new NullPointerException(format(s, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o) {
        if (t == null) {
            throw new NullPointerException(format(s, o));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final char c, final char c2) {
        if (t == null) {
            throw new NullPointerException(format(s, c, c2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final char c, final int n) {
        if (t == null) {
            throw new NullPointerException(format(s, c, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final char c, final long n) {
        if (t == null) {
            throw new NullPointerException(format(s, c, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final char c, @Nullable final Object o) {
        if (t == null) {
            throw new NullPointerException(format(s, c, o));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final int n, final char c) {
        if (t == null) {
            throw new NullPointerException(format(s, n, c));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final int n, final int n2) {
        if (t == null) {
            throw new NullPointerException(format(s, n, n2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final int n, final long n2) {
        if (t == null) {
            throw new NullPointerException(format(s, n, n2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final int n, @Nullable final Object o) {
        if (t == null) {
            throw new NullPointerException(format(s, n, o));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final long n, final char c) {
        if (t == null) {
            throw new NullPointerException(format(s, n, c));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final long n, final int n2) {
        if (t == null) {
            throw new NullPointerException(format(s, n, n2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final long n, final long n2) {
        if (t == null) {
            throw new NullPointerException(format(s, n, n2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, final long n, @Nullable final Object o) {
        if (t == null) {
            throw new NullPointerException(format(s, n, o));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, final char c) {
        if (t == null) {
            throw new NullPointerException(format(s, o, c));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, final int n) {
        if (t == null) {
            throw new NullPointerException(format(s, o, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, final long n) {
        if (t == null) {
            throw new NullPointerException(format(s, o, n));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2) {
        if (t == null) {
            throw new NullPointerException(format(s, o, o2));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3) {
        if (t == null) {
            throw new NullPointerException(format(s, o, o2, o3));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static <T> T checkNotNull(final T t, @Nullable final String s, @Nullable final Object o, @Nullable final Object o2, @Nullable final Object o3, @Nullable final Object o4) {
        if (t == null) {
            throw new NullPointerException(format(s, o, o2, o3, o4));
        }
        return t;
    }
    
    @CanIgnoreReturnValue
    public static int checkElementIndex(final int n, final int n2) {
        return checkElementIndex(n, n2, "index");
    }
    
    @CanIgnoreReturnValue
    public static int checkElementIndex(final int n, final int n2, @Nullable final String s) {
        if (n < 0 || n >= n2) {
            throw new IndexOutOfBoundsException(badElementIndex(n, n2, s));
        }
        return n;
    }
    
    private static String badElementIndex(final int n, final int n2, final String s) {
        if (n < 0) {
            return format("%s (%s) must not be negative", s, n);
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("negative size: " + n2);
        }
        return format("%s (%s) must be less than size (%s)", s, n, n2);
    }
    
    @CanIgnoreReturnValue
    public static int checkPositionIndex(final int n, final int n2) {
        return checkPositionIndex(n, n2, "index");
    }
    
    @CanIgnoreReturnValue
    public static int checkPositionIndex(final int n, final int n2, @Nullable final String s) {
        if (n < 0 || n > n2) {
            throw new IndexOutOfBoundsException(badPositionIndex(n, n2, s));
        }
        return n;
    }
    
    private static String badPositionIndex(final int n, final int n2, final String s) {
        if (n < 0) {
            return format("%s (%s) must not be negative", s, n);
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("negative size: " + n2);
        }
        return format("%s (%s) must not be greater than size (%s)", s, n, n2);
    }
    
    public static void checkPositionIndexes(final int n, final int n2, final int n3) {
        if (n < 0 || n2 < n || n2 > n3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(n, n2, n3));
        }
    }
    
    private static String badPositionIndexes(final int n, final int n2, final int n3) {
        if (n < 0 || n > n3) {
            return badPositionIndex(n, n3, "start index");
        }
        if (n2 < 0 || n2 > n3) {
            return badPositionIndex(n2, n3, "end index");
        }
        return format("end index (%s) must not be less than start index (%s)", n2, n);
    }
    
    static String format(String value, @Nullable final Object... array) {
        value = String.valueOf(value);
        final StringBuilder sb = new StringBuilder(value.length() + 16 * array.length);
        int n = 0;
        int i = 0;
        while (i < array.length) {
            final int index = value.indexOf("%s", n);
            if (index == -1) {
                break;
            }
            sb.append(value, n, index);
            sb.append(array[i++]);
            n = index + 2;
        }
        sb.append(value, n, value.length());
        if (i < array.length) {
            sb.append(" [");
            sb.append(array[i++]);
            while (i < array.length) {
                sb.append(", ");
                sb.append(array[i++]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
