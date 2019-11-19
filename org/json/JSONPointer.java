package org.json;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.List;

public class JSONPointer
{
    private static final String ENCODING = "utf-8";
    private final List<String> refTokens;
    
    public static Builder builder() {
        return new Builder();
    }
    
    public JSONPointer(final String s) {
        super();
        if (s == null) {
            throw new NullPointerException("pointer cannot be null");
        }
        if (s.isEmpty() || s.equals("#")) {
            this.refTokens = Collections.<String>emptyList();
            return;
        }
        String s2 = null;
        Label_0105: {
            if (s.startsWith("#/")) {
                final String substring = s.substring(2);
                try {
                    s2 = URLDecoder.decode(substring, "utf-8");
                    break Label_0105;
                }
                catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (!s.startsWith("/")) {
                throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
            }
            s2 = s.substring(1);
        }
        this.refTokens = new ArrayList<String>();
        int i = -1;
        do {
            final int n = i + 1;
            i = s2.indexOf(47, n);
            if (n == i || n == s2.length()) {
                this.refTokens.add("");
            }
            else if (i >= 0) {
                this.refTokens.add(this.unescape(s2.substring(n, i)));
            }
            else {
                this.refTokens.add(this.unescape(s2.substring(n)));
            }
        } while (i >= 0);
    }
    
    public JSONPointer(final List<String> list) {
        super();
        this.refTokens = new ArrayList<String>(list);
    }
    
    private String unescape(final String s) {
        return s.replace("~1", "/").replace("~0", "~").replace("\\\"", "\"").replace("\\\\", "\\");
    }
    
    public Object queryFrom(final Object o) throws JSONPointerException {
        if (this.refTokens.isEmpty()) {
            return o;
        }
        Object o2 = o;
        for (final String s : this.refTokens) {
            if (o2 instanceof JSONObject) {
                o2 = ((JSONObject)o2).opt(this.unescape(s));
            }
            else {
                if (!(o2 instanceof JSONArray)) {
                    throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", o2, s));
                }
                o2 = this.readByIndexToken(o2, s);
            }
        }
        return o2;
    }
    
    private Object readByIndexToken(final Object o, final String s) throws JSONPointerException {
        try {
            final int int1 = Integer.parseInt(s);
            final JSONArray jsonArray = (JSONArray)o;
            if (int1 >= jsonArray.length()) {
                throw new JSONPointerException(String.format("index %s is out of bounds - the array has %d elements", s, jsonArray.length()));
            }
            try {
                return jsonArray.get(int1);
            }
            catch (JSONException ex) {
                throw new JSONPointerException("Error reading value at index position " + int1, ex);
            }
        }
        catch (NumberFormatException ex2) {
            throw new JSONPointerException(String.format("%s is not an array index", s), ex2);
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        final Iterator<String> iterator = this.refTokens.iterator();
        while (iterator.hasNext()) {
            sb.append('/').append(this.escape(iterator.next()));
        }
        return sb.toString();
    }
    
    private String escape(final String s) {
        return s.replace("~", "~0").replace("/", "~1").replace("\\", "\\\\").replace("\"", "\\\"");
    }
    
    public String toURIFragment() {
        try {
            final StringBuilder sb = new StringBuilder("#");
            final Iterator<String> iterator = this.refTokens.iterator();
            while (iterator.hasNext()) {
                sb.append('/').append(URLEncoder.encode(iterator.next(), "utf-8"));
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
