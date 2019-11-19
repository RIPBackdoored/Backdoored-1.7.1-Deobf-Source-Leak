package com.google.cloud.storage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;

public class CanonicalExtensionHeadersSerializer
{
    private static final char HEADER_SEPARATOR = ':';
    private static final char HEADER_NAME_SEPARATOR = ';';
    private final Storage.SignUrlOption.SignatureVersion signatureVersion;
    
    public CanonicalExtensionHeadersSerializer(final Storage.SignUrlOption.SignatureVersion signatureVersion) {
        super();
        this.signatureVersion = signatureVersion;
    }
    
    public CanonicalExtensionHeadersSerializer() {
        super();
        this.signatureVersion = Storage.SignUrlOption.SignatureVersion.V2;
    }
    
    public StringBuilder serialize(final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        if (map == null || map.isEmpty()) {
            return sb;
        }
        final Map<String, String> lowercaseHeaders = this.getLowercaseHeaders(map);
        final ArrayList list = new ArrayList<String>(lowercaseHeaders.keySet());
        Collections.<Comparable>sort((List<Comparable>)list);
        for (final String s : list) {
            sb.append(s).append(':').append(lowercaseHeaders.get(s).trim().replaceAll("[\\s]{2,}", " ").replaceAll("(\\t|\\r?\\n)+", " ")).append('\n');
        }
        return sb;
    }
    
    public StringBuilder serializeHeaderNames(final Map<String, String> map) {
        final StringBuilder sb = new StringBuilder();
        if (map == null || map.isEmpty()) {
            return sb;
        }
        final ArrayList<String> list = new ArrayList<String>(this.getLowercaseHeaders(map).keySet());
        Collections.<Comparable>sort((List<Comparable>)list);
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(';');
        }
        sb.setLength(sb.length() - 1);
        return sb;
    }
    
    private Map<String, String> getLowercaseHeaders(final Map<String, String> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : new ArrayList<String>(map.keySet())) {
            final String lowerCase = s.toLowerCase();
            if (Storage.SignUrlOption.SignatureVersion.V2.equals(this.signatureVersion)) {
                if ("x-goog-encryption-key".equals(lowerCase)) {
                    continue;
                }
                if ("x-goog-encryption-key-sha256".equals(lowerCase)) {
                    continue;
                }
            }
            hashMap.put(lowerCase, map.get(s));
        }
        return hashMap;
    }
}
