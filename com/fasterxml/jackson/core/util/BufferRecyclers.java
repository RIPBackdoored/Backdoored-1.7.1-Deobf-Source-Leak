package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import java.lang.ref.SoftReference;

public class BufferRecyclers
{
    public static final String SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS = "com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers";
    private static final ThreadLocalBufferManager _bufferRecyclerTracker;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _encoderRef;
    
    public BufferRecyclers() {
        super();
    }
    
    public static BufferRecycler getBufferRecycler() {
        final SoftReference<BufferRecycler> softReference = BufferRecyclers._recyclerRef.get();
        BufferRecycler bufferRecycler = (softReference == null) ? null : softReference.get();
        if (bufferRecycler == null) {
            bufferRecycler = new BufferRecycler();
            SoftReference<BufferRecycler> wrapAndTrack;
            if (BufferRecyclers._bufferRecyclerTracker != null) {
                wrapAndTrack = BufferRecyclers._bufferRecyclerTracker.wrapAndTrack(bufferRecycler);
            }
            else {
                wrapAndTrack = new SoftReference<BufferRecycler>(bufferRecycler);
            }
            BufferRecyclers._recyclerRef.set(wrapAndTrack);
        }
        return bufferRecycler;
    }
    
    public static int releaseBuffers() {
        if (BufferRecyclers._bufferRecyclerTracker != null) {
            return BufferRecyclers._bufferRecyclerTracker.releaseBuffers();
        }
        return -1;
    }
    
    public static JsonStringEncoder getJsonStringEncoder() {
        final SoftReference<JsonStringEncoder> softReference = BufferRecyclers._encoderRef.get();
        JsonStringEncoder jsonStringEncoder = (softReference == null) ? null : softReference.get();
        if (jsonStringEncoder == null) {
            jsonStringEncoder = new JsonStringEncoder();
            BufferRecyclers._encoderRef.set(new SoftReference<JsonStringEncoder>(jsonStringEncoder));
        }
        return jsonStringEncoder;
    }
    
    public static byte[] encodeAsUTF8(final String s) {
        return getJsonStringEncoder().encodeAsUTF8(s);
    }
    
    public static char[] quoteAsJsonText(final String s) {
        return getJsonStringEncoder().quoteAsString(s);
    }
    
    public static void quoteAsJsonText(final CharSequence charSequence, final StringBuilder sb) {
        getJsonStringEncoder().quoteAsString(charSequence, sb);
    }
    
    public static byte[] quoteAsJsonUTF8(final String s) {
        return getJsonStringEncoder().quoteAsUTF8(s);
    }
    
    static {
        _bufferRecyclerTracker = ("true".equals(System.getProperty("com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers")) ? ThreadLocalBufferManager.instance() : null);
        _recyclerRef = new ThreadLocal<SoftReference<BufferRecycler>>();
        _encoderRef = new ThreadLocal<SoftReference<JsonStringEncoder>>();
    }
}
