package com.fasterxml.jackson.core;

import java.nio.charset.Charset;
import java.io.Serializable;

public class JsonLocation implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final int MAX_CONTENT_SNIPPET = 500;
    public static final JsonLocation NA;
    protected final long _totalBytes;
    protected final long _totalChars;
    protected final int _lineNr;
    protected final int _columnNr;
    final transient Object _sourceRef;
    
    public JsonLocation(final Object o, final long n, final int n2, final int n3) {
        this(o, -1L, n, n2, n3);
    }
    
    public JsonLocation(final Object sourceRef, final long totalBytes, final long totalChars, final int lineNr, final int columnNr) {
        super();
        this._sourceRef = sourceRef;
        this._totalBytes = totalBytes;
        this._totalChars = totalChars;
        this._lineNr = lineNr;
        this._columnNr = columnNr;
    }
    
    public Object getSourceRef() {
        return this._sourceRef;
    }
    
    public int getLineNr() {
        return this._lineNr;
    }
    
    public int getColumnNr() {
        return this._columnNr;
    }
    
    public long getCharOffset() {
        return this._totalChars;
    }
    
    public long getByteOffset() {
        return this._totalBytes;
    }
    
    public String sourceDescription() {
        return this._appendSourceDesc(new StringBuilder(100)).toString();
    }
    
    @Override
    public int hashCode() {
        return ((((this._sourceRef == null) ? 1 : this._sourceRef.hashCode()) ^ this._lineNr) + this._columnNr ^ (int)this._totalChars) + (int)this._totalBytes;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof JsonLocation)) {
            return false;
        }
        final JsonLocation jsonLocation = (JsonLocation)o;
        if (this._sourceRef == null) {
            if (jsonLocation._sourceRef != null) {
                return false;
            }
        }
        else if (!this._sourceRef.equals(jsonLocation._sourceRef)) {
            return false;
        }
        return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && this.getByteOffset() == jsonLocation.getByteOffset();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        this._appendSourceDesc(sb);
        sb.append("; line: ");
        sb.append(this._lineNr);
        sb.append(", column: ");
        sb.append(this._columnNr);
        sb.append(']');
        return sb.toString();
    }
    
    protected StringBuilder _appendSourceDesc(final StringBuilder sb) {
        final Object sourceRef = this._sourceRef;
        if (sourceRef == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        final Class<? extends Class> clazz = (Class<? extends Class>)((sourceRef instanceof Class) ? sourceRef : ((Class<? extends Class>)sourceRef).getClass());
        String s = clazz.getName();
        if (s.startsWith("java.")) {
            s = clazz.getSimpleName();
        }
        else if (sourceRef instanceof byte[]) {
            s = "byte[]";
        }
        else if (sourceRef instanceof char[]) {
            s = "char[]";
        }
        sb.append('(').append(s).append(')');
        String s2 = " chars";
        int n;
        if (sourceRef instanceof CharSequence) {
            final CharSequence charSequence = (CharSequence)sourceRef;
            final int length = charSequence.length();
            n = length - this._append(sb, charSequence.subSequence(0, Math.min(length, 500)).toString());
        }
        else if (sourceRef instanceof char[]) {
            final char[] array = (char[])sourceRef;
            final int length2 = array.length;
            n = length2 - this._append(sb, new String(array, 0, Math.min(length2, 500)));
        }
        else if (sourceRef instanceof byte[]) {
            final byte[] array2 = (byte[])sourceRef;
            final int min = Math.min(array2.length, 500);
            this._append(sb, new String(array2, 0, min, Charset.forName("UTF-8")));
            n = array2.length - min;
            s2 = " bytes";
        }
        else {
            n = 0;
        }
        if (n > 0) {
            sb.append("[truncated ").append(n).append(s2).append(']');
        }
        return sb;
    }
    
    private int _append(final StringBuilder sb, final String s) {
        sb.append('\"').append(s).append('\"');
        return s.length();
    }
    
    static {
        NA = new JsonLocation(null, -1L, -1L, -1, -1);
    }
}
