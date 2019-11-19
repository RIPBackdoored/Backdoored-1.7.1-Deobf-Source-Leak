package org.json;

import java.io.Serializable;

public class Cookie
{
    public Cookie() {
        super();
    }
    
    public static String escape(final String s) {
        final String trim = s.trim();
        final int length = trim.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = trim.charAt(i);
            if (char1 < ' ' || char1 == '+' || char1 == '%' || char1 == '=' || char1 == ';') {
                sb.append('%');
                sb.append(Character.forDigit((char)(char1 >>> 4 & 0xF), 16));
                sb.append(Character.forDigit((char)(char1 & '\u000f'), 16));
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static JSONObject toJSONObject(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        final JSONTokener jsonTokener = new JSONTokener(s);
        jsonObject.put("name", jsonTokener.nextTo('='));
        jsonTokener.next('=');
        jsonObject.put("value", jsonTokener.nextTo(';'));
        jsonTokener.next();
        while (jsonTokener.more()) {
            final String unescape = unescape(jsonTokener.nextTo("=;"));
            Serializable s2;
            if (jsonTokener.next() != '=') {
                if (!unescape.equals("secure")) {
                    throw jsonTokener.syntaxError("Missing '=' in cookie parameter.");
                }
                s2 = Boolean.TRUE;
            }
            else {
                s2 = unescape(jsonTokener.nextTo(';'));
                jsonTokener.next();
            }
            jsonObject.put(unescape, s2);
        }
        return jsonObject;
    }
    
    public static String toString(final JSONObject jsonObject) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        sb.append(escape(jsonObject.getString("name")));
        sb.append("=");
        sb.append(escape(jsonObject.getString("value")));
        if (jsonObject.has("expires")) {
            sb.append(";expires=");
            sb.append(jsonObject.getString("expires"));
        }
        if (jsonObject.has("domain")) {
            sb.append(";domain=");
            sb.append(escape(jsonObject.getString("domain")));
        }
        if (jsonObject.has("path")) {
            sb.append(";path=");
            sb.append(escape(jsonObject.getString("path")));
        }
        if (jsonObject.optBoolean("secure")) {
            sb.append(";secure");
        }
        return sb.toString();
    }
    
    public static String unescape(final String s) {
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            char char1 = s.charAt(i);
            if (char1 == '+') {
                char1 = ' ';
            }
            else if (char1 == '%' && i + 2 < length) {
                final int dehexchar = JSONTokener.dehexchar(s.charAt(i + 1));
                final int dehexchar2 = JSONTokener.dehexchar(s.charAt(i + 2));
                if (dehexchar >= 0 && dehexchar2 >= 0) {
                    char1 = (char)(dehexchar * 16 + dehexchar2);
                    i += 2;
                }
            }
            sb.append(char1);
        }
        return sb.toString();
    }
}
