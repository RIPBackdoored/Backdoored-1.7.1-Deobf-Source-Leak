package org.json;

import java.io.StringReader;
import java.io.Reader;
import java.util.Iterator;

public class XML
{
    public static final Character AMP;
    public static final Character APOS;
    public static final Character BANG;
    public static final Character EQ;
    public static final Character GT;
    public static final Character LT;
    public static final Character QUEST;
    public static final Character QUOT;
    public static final Character SLASH;
    public static final String NULL_ATTR = "xsi:nil";
    
    public XML() {
        super();
    }
    
    private static Iterable<Integer> codePointIterator(final String s) {
        return new Iterable<Integer>() {
            final /* synthetic */ String val$string;
            
            XML$1() {
                super();
            }
            
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int nextIndex = 0;
                    private int length = s.length();
                    final /* synthetic */ XML$1 this$0;
                    
                    XML$1$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public boolean hasNext() {
                        return this.nextIndex < this.length;
                    }
                    
                    @Override
                    public Integer next() {
                        final int codePoint = s.codePointAt(this.nextIndex);
                        this.nextIndex += Character.charCount(codePoint);
                        return codePoint;
                    }
                    
                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                    
                    @Override
                    public /* bridge */ Object next() {
                        return this.next();
                    }
                };
            }
        };
    }
    
    public static String escape(final String s) {
        final StringBuilder sb = new StringBuilder(s.length());
        for (final int intValue : codePointIterator(s)) {
            switch (intValue) {
                case 38:
                    sb.append("&amp;");
                    continue;
                case 60:
                    sb.append("&lt;");
                    continue;
                case 62:
                    sb.append("&gt;");
                    continue;
                case 34:
                    sb.append("&quot;");
                    continue;
                case 39:
                    sb.append("&apos;");
                    continue;
                default:
                    if (mustEscape(intValue)) {
                        sb.append("&#x");
                        sb.append(Integer.toHexString(intValue));
                        sb.append(';');
                        continue;
                    }
                    sb.appendCodePoint(intValue);
                    continue;
            }
        }
        return sb.toString();
    }
    
    private static boolean mustEscape(final int n) {
        return (Character.isISOControl(n) && n != 9 && n != 10 && n != 13) || ((n < 32 || n > 55295) && (n < 57344 || n > 65533) && (n < 65536 || n > 1114111));
    }
    
    public static String unescape(final String s) {
        final StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '&') {
                final int index = s.indexOf(59, i);
                if (index > i) {
                    final String substring = s.substring(i + 1, index);
                    sb.append(XMLTokener.unescapeEntity(substring));
                    i += substring.length() + 1;
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static void noSpace(final String s) throws JSONException {
        s.length();
        throw new JSONException("Empty string.");
    }
    
    private static boolean parse(final XMLTokener xmlTokener, final JSONObject jsonObject, final String s, final XMLParserConfiguration xmlParserConfiguration) throws JSONException {
        final Object nextToken = xmlTokener.nextToken();
        if (nextToken == XML.BANG) {
            final char next = xmlTokener.next();
            if (next == '-') {
                if (xmlTokener.next() == '-') {
                    xmlTokener.skipPast("-->");
                    return false;
                }
                xmlTokener.back();
            }
            else if (next == '[') {
                if ("CDATA".equals(xmlTokener.nextToken()) && xmlTokener.next() == '[') {
                    final String nextCDATA = xmlTokener.nextCDATA();
                    if (nextCDATA.length() > 0) {
                        jsonObject.accumulate(xmlParserConfiguration.cDataTagName, nextCDATA);
                    }
                    return false;
                }
                throw xmlTokener.syntaxError("Expected 'CDATA['");
            }
            int i = 1;
            do {
                final Object nextMeta = xmlTokener.nextMeta();
                if (nextMeta == null) {
                    throw xmlTokener.syntaxError("Missing '>' after '<!'.");
                }
                if (nextMeta == XML.LT) {
                    ++i;
                }
                else {
                    if (nextMeta != XML.GT) {
                        continue;
                    }
                    --i;
                }
            } while (i > 0);
            return false;
        }
        if (nextToken == XML.QUEST) {
            xmlTokener.skipPast("?>");
            return false;
        }
        if (nextToken == XML.SLASH) {
            final Object nextToken2 = xmlTokener.nextToken();
            if (s == null) {
                throw xmlTokener.syntaxError("Mismatched close tag " + nextToken2);
            }
            if (!nextToken2.equals(s)) {
                throw xmlTokener.syntaxError("Mismatched " + s + " and " + nextToken2);
            }
            if (xmlTokener.nextToken() != XML.GT) {
                throw xmlTokener.syntaxError("Misshaped close tag");
            }
            return true;
        }
        else {
            if (nextToken instanceof Character) {
                throw xmlTokener.syntaxError("Misshaped tag");
            }
            final String s2 = (String)nextToken;
            Object o = null;
            final JSONObject jsonObject2 = new JSONObject();
            boolean b = false;
            while (true) {
                if (o == null) {
                    o = xmlTokener.nextToken();
                }
                if (o instanceof String) {
                    final String s3 = (String)o;
                    o = xmlTokener.nextToken();
                    if (o == XML.EQ) {
                        final Object nextToken3 = xmlTokener.nextToken();
                        if (!(nextToken3 instanceof String)) {
                            throw xmlTokener.syntaxError("Missing value");
                        }
                        if (xmlParserConfiguration.convertNilAttributeToNull && "xsi:nil".equals(s3) && Boolean.parseBoolean((String)nextToken3)) {
                            b = true;
                        }
                        else {
                            jsonObject2.accumulate(s3, xmlParserConfiguration.keepStrings ? ((String)nextToken3) : stringToValue((String)nextToken3));
                        }
                        o = null;
                    }
                    else {
                        jsonObject2.accumulate(s3, "");
                    }
                }
                else if (o == XML.SLASH) {
                    if (xmlTokener.nextToken() != XML.GT) {
                        throw xmlTokener.syntaxError("Misshaped tag");
                    }
                    if (b) {
                        jsonObject.accumulate(s2, JSONObject.NULL);
                    }
                    else if (jsonObject2.length() > 0) {
                        jsonObject.accumulate(s2, jsonObject2);
                    }
                    else {
                        jsonObject.accumulate(s2, "");
                    }
                    return false;
                }
                else {
                    if (o != XML.GT) {
                        throw xmlTokener.syntaxError("Misshaped tag");
                    }
                    while (true) {
                        final Object nextContent = xmlTokener.nextContent();
                        if (nextContent == null) {
                            if (s2 != null) {
                                throw xmlTokener.syntaxError("Unclosed tag " + s2);
                            }
                            return false;
                        }
                        else if (nextContent instanceof String) {
                            final String s4 = (String)nextContent;
                            if (s4.length() <= 0) {
                                continue;
                            }
                            jsonObject2.accumulate(xmlParserConfiguration.cDataTagName, xmlParserConfiguration.keepStrings ? s4 : stringToValue(s4));
                        }
                        else {
                            if (nextContent == XML.LT && parse(xmlTokener, jsonObject2, s2, xmlParserConfiguration)) {
                                if (jsonObject2.length() == 0) {
                                    jsonObject.accumulate(s2, "");
                                }
                                else if (jsonObject2.length() == 1 && jsonObject2.opt(xmlParserConfiguration.cDataTagName) != null) {
                                    jsonObject.accumulate(s2, jsonObject2.opt(xmlParserConfiguration.cDataTagName));
                                }
                                else {
                                    jsonObject.accumulate(s2, jsonObject2);
                                }
                                return false;
                            }
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    public static Object stringToValue(final String s) {
        if (s.equals("")) {
            return s;
        }
        if (s.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (s.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (s.equalsIgnoreCase("null")) {
            return JSONObject.NULL;
        }
        final char char1 = s.charAt(0);
        if (char1 < '0' || char1 > '9') {
            if (char1 != '-') {
                return s;
            }
        }
        try {
            if (s.indexOf(46) > -1 || s.indexOf(101) > -1 || s.indexOf(69) > -1 || "-0".equals(s)) {
                final Double value = Double.valueOf(s);
                if (!value.isInfinite() && !value.isNaN()) {
                    return value;
                }
            }
            else {
                final Long value2 = Long.valueOf(s);
                if (s.equals(value2.toString())) {
                    if (value2 == value2.intValue()) {
                        return value2.intValue();
                    }
                    return value2;
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public static JSONObject toJSONObject(final String s) throws JSONException {
        return toJSONObject(s, XMLParserConfiguration.ORIGINAL);
    }
    
    public static JSONObject toJSONObject(final Reader reader) throws JSONException {
        return toJSONObject(reader, XMLParserConfiguration.ORIGINAL);
    }
    
    public static JSONObject toJSONObject(final Reader reader, final boolean b) throws JSONException {
        if (b) {
            return toJSONObject(reader, XMLParserConfiguration.KEEP_STRINGS);
        }
        return toJSONObject(reader, XMLParserConfiguration.ORIGINAL);
    }
    
    public static JSONObject toJSONObject(final Reader reader, final XMLParserConfiguration xmlParserConfiguration) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        final XMLTokener xmlTokener = new XMLTokener(reader);
        while (xmlTokener.more()) {
            xmlTokener.skipPast("<");
            if (xmlTokener.more()) {
                parse(xmlTokener, jsonObject, null, xmlParserConfiguration);
            }
        }
        return jsonObject;
    }
    
    public static JSONObject toJSONObject(final String s, final boolean b) throws JSONException {
        return toJSONObject(new StringReader(s), b);
    }
    
    public static JSONObject toJSONObject(final String s, final XMLParserConfiguration xmlParserConfiguration) throws JSONException {
        return toJSONObject(new StringReader(s), xmlParserConfiguration);
    }
    
    public static String toString(final Object o) throws JSONException {
        return toString(o, null, XMLParserConfiguration.ORIGINAL);
    }
    
    public static String toString(final Object o, final String s) {
        return toString(o, s, XMLParserConfiguration.ORIGINAL);
    }
    
    public static String toString(final Object o, final String s, final XMLParserConfiguration xmlParserConfiguration) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        if (o instanceof JSONObject) {
            if (s != null) {
                sb.append('<');
                sb.append(s);
                sb.append('>');
            }
            final JSONObject jsonObject = (JSONObject)o;
            for (final String s2 : jsonObject.keySet()) {
                Object opt = jsonObject.opt(s2);
                if (opt == null) {
                    opt = "";
                }
                else if (((JSONArray)opt).getClass().isArray()) {
                    opt = new JSONArray(opt);
                }
                if (s2.equals(xmlParserConfiguration.cDataTagName)) {
                    if (opt instanceof JSONArray) {
                        final JSONArray jsonArray = (JSONArray)opt;
                        for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                            if (i > 0) {
                                sb.append('\n');
                            }
                            sb.append(escape(jsonArray.opt(i).toString()));
                        }
                    }
                    else {
                        sb.append(escape(opt.toString()));
                    }
                }
                else if (opt instanceof JSONArray) {
                    final JSONArray jsonArray2 = (JSONArray)opt;
                    for (int length2 = jsonArray2.length(), j = 0; j < length2; ++j) {
                        final Object opt2 = jsonArray2.opt(j);
                        if (opt2 instanceof JSONArray) {
                            sb.append('<');
                            sb.append(s2);
                            sb.append('>');
                            sb.append(toString(opt2, null, xmlParserConfiguration));
                            sb.append("</");
                            sb.append(s2);
                            sb.append('>');
                        }
                        else {
                            sb.append(toString(opt2, s2, xmlParserConfiguration));
                        }
                    }
                }
                else if ("".equals(opt)) {
                    sb.append('<');
                    sb.append(s2);
                    sb.append("/>");
                }
                else {
                    sb.append(toString(opt, s2, xmlParserConfiguration));
                }
            }
            if (s != null) {
                sb.append("</");
                sb.append(s);
                sb.append('>');
            }
            return sb.toString();
        }
        if (o != null && (o instanceof JSONArray || o.getClass().isArray())) {
            JSONArray jsonArray3;
            if (o.getClass().isArray()) {
                jsonArray3 = new JSONArray(o);
            }
            else {
                jsonArray3 = (JSONArray)o;
            }
            for (int length3 = jsonArray3.length(), k = 0; k < length3; ++k) {
                sb.append(toString(jsonArray3.opt(k), (s == null) ? "array" : s, xmlParserConfiguration));
            }
            return sb.toString();
        }
        final String s3 = (o == null) ? "null" : escape(o.toString());
        return (s == null) ? ("\"" + s3 + "\"") : ((s3.length() == 0) ? ("<" + s + "/>") : ("<" + s + ">" + s3 + "</" + s + ">"));
    }
    
    static {
        AMP = '&';
        APOS = '\'';
        BANG = '!';
        EQ = '=';
        GT = '>';
        LT = '<';
        QUEST = '?';
        QUOT = '\"';
        SLASH = '/';
    }
}
