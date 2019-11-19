package org.json;

public class CDL
{
    public CDL() {
        super();
    }
    
    private static String getValue(final JSONTokener jsonTokener) throws JSONException {
        char next;
        do {
            next = jsonTokener.next();
        } while (next == ' ' || next == '\t');
        switch (next) {
            case 0:
                return null;
            case 34:
            case 39: {
                final char c = next;
                final StringBuffer sb = new StringBuffer();
                while (true) {
                    final char next2 = jsonTokener.next();
                    if (next2 == c) {
                        final char next3 = jsonTokener.next();
                        if (next3 != '\"') {
                            if (next3 > '\0') {
                                jsonTokener.back();
                            }
                            return sb.toString();
                        }
                    }
                    if (next2 == '\0' || next2 == '\n' || next2 == '\r') {
                        throw jsonTokener.syntaxError("Missing close quote '" + c + "'.");
                    }
                    sb.append(next2);
                }
                break;
            }
            case 44:
                jsonTokener.back();
                return "";
            default:
                jsonTokener.back();
                return jsonTokener.nextTo(',');
        }
    }
    
    public static JSONArray rowToJSONArray(final JSONTokener jsonTokener) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        while (true) {
            final String value = getValue(jsonTokener);
            char c = jsonTokener.next();
            if (value == null || (jsonArray.length() == 0 && value.length() == 0 && c != ',')) {
                return null;
            }
            jsonArray.put(value);
            while (c != ',') {
                if (c != ' ') {
                    if (c == '\n' || c != '\r') {}
                    return jsonArray;
                }
                c = jsonTokener.next();
            }
        }
    }
    
    public static JSONObject rowToJSONObject(final JSONArray jsonArray, final JSONTokener jsonTokener) throws JSONException {
        final JSONArray rowToJSONArray = rowToJSONArray(jsonTokener);
        return (rowToJSONArray != null) ? rowToJSONArray.toJSONObject(jsonArray) : null;
    }
    
    public static String rowToString(final JSONArray jsonArray) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); ++i) {
            if (i > 0) {
                sb.append(',');
            }
            final Object opt = jsonArray.opt(i);
            if (opt != null) {
                final String string = opt.toString();
                if (string.length() > 0 && (string.indexOf(44) >= 0 || string.indexOf(10) >= 0 || string.indexOf(13) >= 0 || string.indexOf(0) >= 0 || string.charAt(0) == '\"')) {
                    sb.append('\"');
                    for (int length = string.length(), j = 0; j < length; ++j) {
                        final char char1 = string.charAt(j);
                        if (char1 >= ' ' && char1 != '\"') {
                            sb.append(char1);
                        }
                    }
                    sb.append('\"');
                }
                else {
                    sb.append(string);
                }
            }
        }
        sb.append('\n');
        return sb.toString();
    }
    
    public static JSONArray toJSONArray(final String s) throws JSONException {
        return toJSONArray(new JSONTokener(s));
    }
    
    public static JSONArray toJSONArray(final JSONTokener jsonTokener) throws JSONException {
        return toJSONArray(rowToJSONArray(jsonTokener), jsonTokener);
    }
    
    public static JSONArray toJSONArray(final JSONArray jsonArray, final String s) throws JSONException {
        return toJSONArray(jsonArray, new JSONTokener(s));
    }
    
    public static JSONArray toJSONArray(final JSONArray jsonArray, final JSONTokener jsonTokener) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        final JSONArray jsonArray2 = new JSONArray();
        while (true) {
            final JSONObject rowToJSONObject = rowToJSONObject(jsonArray, jsonTokener);
            if (rowToJSONObject == null) {
                break;
            }
            jsonArray2.put(rowToJSONObject);
        }
        if (jsonArray2.length() == 0) {
            return null;
        }
        return jsonArray2;
    }
    
    public static String toString(final JSONArray jsonArray) throws JSONException {
        final JSONObject optJSONObject = jsonArray.optJSONObject(0);
        if (optJSONObject != null) {
            final JSONArray names = optJSONObject.names();
            if (names != null) {
                return rowToString(names) + toString(names, jsonArray);
            }
        }
        return null;
    }
    
    public static String toString(final JSONArray jsonArray, final JSONArray jsonArray2) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonArray2.length(); ++i) {
            final JSONObject optJSONObject = jsonArray2.optJSONObject(i);
            if (optJSONObject != null) {
                sb.append(rowToString(optJSONObject.toJSONArray(jsonArray)));
            }
        }
        return sb.toString();
    }
}
