package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import java.util.HashMap;
import java.util.ListIterator;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Types;
import com.google.api.client.repackaged.com.google.common.base.Splitter;
import java.util.Iterator;
import com.google.api.client.util.Data;
import java.util.LinkedHashMap;
import java.util.Map;

public class UriTemplate
{
    static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES;
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    
    public UriTemplate() {
        super();
    }
    
    static CompositeOutput getCompositeOutput(final String s) {
        final CompositeOutput compositeOutput = UriTemplate.COMPOSITE_PREFIXES.get(s.charAt(0));
        return (compositeOutput == null) ? CompositeOutput.SIMPLE : compositeOutput;
    }
    
    private static Map<String, Object> getMap(final Object o) {
        final LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
        for (final Map.Entry<String, Object> entry : Data.mapOf(o).entrySet()) {
            final Object value = entry.getValue();
            if (value != null && !Data.isNull(value)) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return (Map<String, Object>)linkedHashMap;
    }
    
    public static String expand(final String s, final String s2, final Object o, final boolean b) {
        String s3;
        if (s2.startsWith("/")) {
            final GenericUrl genericUrl = new GenericUrl(s);
            genericUrl.setRawPath(null);
            s3 = genericUrl.build() + s2;
        }
        else if (s2.startsWith("http://") || s2.startsWith("https://")) {
            s3 = s2;
        }
        else {
            s3 = s + s2;
        }
        return expand(s3, o, b);
    }
    
    public static String expand(final String s, final Object o, final boolean b) {
        final Map<String, Object> map = getMap(o);
        final StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            final int index = s.indexOf(123, i);
            if (index == -1) {
                return s;
            }
            sb.append(s.substring(i, index));
            final int index2 = s.indexOf(125, index + 2);
            i = index2 + 1;
            final String substring = s.substring(index + 1, index2);
            final CompositeOutput compositeOutput = getCompositeOutput(substring);
            final ListIterator<String> listIterator = Splitter.on(',').splitToList(substring).listIterator();
            while (listIterator.hasNext()) {
                final String s2 = listIterator.next();
                final boolean endsWith = s2.endsWith("*");
                final int n = (listIterator.nextIndex() == 1) ? compositeOutput.getVarNameStartIndex() : 0;
                int length = s2.length();
                if (endsWith) {
                    --length;
                }
                final String substring2 = s2.substring(n, length);
                Object o2 = map.remove(substring2);
                if (o2 == null) {
                    continue;
                }
                sb.append(compositeOutput.getExplodeJoiner());
                if (o2 instanceof Iterator) {
                    o2 = getListPropertyValue(substring2, (Iterator<?>)o2, endsWith, compositeOutput);
                }
                else if (o2 instanceof Iterable || ((Enum<?>)o2).getClass().isArray()) {
                    o2 = getListPropertyValue(substring2, Types.<Object>iterableOf(o2).iterator(), endsWith, compositeOutput);
                }
                else if (((Enum<?>)o2).getClass().isEnum()) {
                    if (FieldInfo.of((Enum<?>)o2).getName() != null) {
                        if (compositeOutput.requiresVarAssignment()) {
                            o2 = String.format("%s=%s", substring2, o2);
                        }
                        o2 = CharEscapers.escapeUriPath(o2.toString());
                    }
                }
                else if (!Data.isValueOfPrimitiveType(o2)) {
                    o2 = getMapPropertyValue(substring2, getMap(o2), endsWith, compositeOutput);
                }
                else {
                    if (compositeOutput.requiresVarAssignment()) {
                        o2 = String.format("%s=%s", substring2, o2);
                    }
                    if (compositeOutput.getReservedExpansion()) {
                        o2 = CharEscapers.escapeUriPathWithoutReserved(o2.toString());
                    }
                    else {
                        o2 = CharEscapers.escapeUriPath(o2.toString());
                    }
                }
                sb.append(o2);
            }
        }
        if (b) {
            GenericUrl.addQueryParams(map.entrySet(), sb);
        }
        return sb.toString();
    }
    
    private static String getListPropertyValue(final String s, final Iterator<?> iterator, final boolean b, final CompositeOutput compositeOutput) {
        if (!iterator.hasNext()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        String explodeJoiner;
        if (b) {
            explodeJoiner = compositeOutput.getExplodeJoiner();
        }
        else {
            explodeJoiner = ",";
            if (compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(s));
                sb.append("=");
            }
        }
        while (iterator.hasNext()) {
            if (b && compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(s));
                sb.append("=");
            }
            sb.append(compositeOutput.getEncodedValue(iterator.next().toString()));
            if (iterator.hasNext()) {
                sb.append(explodeJoiner);
            }
        }
        return sb.toString();
    }
    
    private static String getMapPropertyValue(final String s, final Map<String, Object> map, final boolean b, final CompositeOutput compositeOutput) {
        if (map.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        String explodeJoiner;
        String s2;
        if (b) {
            explodeJoiner = compositeOutput.getExplodeJoiner();
            s2 = "=";
        }
        else {
            explodeJoiner = ",";
            s2 = ",";
            if (compositeOutput.requiresVarAssignment()) {
                sb.append(CharEscapers.escapeUriPath(s));
                sb.append("=");
            }
        }
        final Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, Object> entry = iterator.next();
            final String encodedValue = compositeOutput.getEncodedValue(entry.getKey());
            final String encodedValue2 = compositeOutput.getEncodedValue(entry.getValue().toString());
            sb.append(encodedValue);
            sb.append(s2);
            sb.append(encodedValue2);
            if (iterator.hasNext()) {
                sb.append(explodeJoiner);
            }
        }
        return sb.toString();
    }
    
    static {
        COMPOSITE_PREFIXES = new HashMap<Character, CompositeOutput>();
        CompositeOutput.values();
    }
}
