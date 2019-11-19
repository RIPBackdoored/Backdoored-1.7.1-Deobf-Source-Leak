package org.json;

import java.util.Collection;
import java.util.Map;
import java.io.IOException;

public class JSONWriter
{
    private static final int maxdepth = 200;
    private boolean comma;
    protected char mode;
    private final JSONObject[] stack;
    private int top;
    protected Appendable writer;
    
    public JSONWriter(final Appendable writer) {
        super();
        this.comma = false;
        this.mode = 'i';
        this.stack = new JSONObject[200];
        this.top = 0;
        this.writer = writer;
    }
    
    private JSONWriter append(final String s) throws JSONException {
        if (s == null) {
            throw new JSONException("Null pointer");
        }
        if (this.mode != 'o') {
            if (this.mode != 'a') {
                throw new JSONException("Value out of sequence.");
            }
        }
        try {
            if (this.comma && this.mode == 'a') {
                this.writer.append(',');
            }
            this.writer.append(s);
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
        if (this.mode == 'o') {
            this.mode = 'k';
        }
        this.comma = true;
        return this;
    }
    
    public JSONWriter array() throws JSONException {
        if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
            this.push(null);
            this.append("[");
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced array.");
    }
    
    private JSONWriter end(final char c, final char c2) throws JSONException {
        if (this.mode != c) {
            throw new JSONException((c == 'a') ? "Misplaced endArray." : "Misplaced endObject.");
        }
        this.pop(c);
        try {
            this.writer.append(c2);
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
        this.comma = true;
        return this;
    }
    
    public JSONWriter endArray() throws JSONException {
        return this.end('a', ']');
    }
    
    public JSONWriter endObject() throws JSONException {
        return this.end('k', '}');
    }
    
    public JSONWriter key(final String s) throws JSONException {
        if (s == null) {
            throw new JSONException("Null key.");
        }
        if (this.mode == 'k') {
            try {
                final JSONObject jsonObject = this.stack[this.top - 1];
                if (jsonObject.has(s)) {
                    throw new JSONException("Duplicate key \"" + s + "\"");
                }
                jsonObject.put(s, true);
                if (this.comma) {
                    this.writer.append(',');
                }
                this.writer.append(JSONObject.quote(s));
                this.writer.append(':');
                this.comma = false;
                this.mode = 'o';
                return this;
            }
            catch (IOException ex) {
                throw new JSONException(ex);
            }
        }
        throw new JSONException("Misplaced key.");
    }
    
    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        if (this.mode == 'o' || this.mode == 'a') {
            this.append("{");
            this.push(new JSONObject());
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced object.");
    }
    
    private void pop(final char c) throws JSONException {
        if (this.top <= 0) {
            throw new JSONException("Nesting error.");
        }
        if (((this.stack[this.top - 1] == null) ? 'a' : 'k') != c) {
            throw new JSONException("Nesting error.");
        }
        --this.top;
        this.mode = ((this.top == 0) ? 'd' : ((this.stack[this.top - 1] == null) ? 'a' : 'k'));
    }
    
    private void push(final JSONObject jsonObject) throws JSONException {
        if (this.top >= 200) {
            throw new JSONException("Nesting too deep.");
        }
        this.stack[this.top] = jsonObject;
        this.mode = ((jsonObject == null) ? 'a' : 'k');
        ++this.top;
    }
    
    public static String valueToString(final Object o) throws JSONException {
        if (o == null || o.equals(null)) {
            return "null";
        }
        if (o instanceof JSONString) {
            String jsonString;
            try {
                jsonString = ((JSONString)o).toJSONString();
            }
            catch (Exception ex) {
                throw new JSONException(ex);
            }
            if (jsonString != null) {
                return jsonString;
            }
            throw new JSONException("Bad value from toJSONString: " + jsonString);
        }
        else if (o instanceof Number) {
            final String numberToString = JSONObject.numberToString((Number)o);
            if (JSONObject.NUMBER_PATTERN.matcher(numberToString).matches()) {
                return numberToString;
            }
            return JSONObject.quote(numberToString);
        }
        else {
            if (o instanceof Boolean || o instanceof JSONObject || o instanceof JSONArray) {
                return o.toString();
            }
            if (o instanceof Map) {
                return new JSONObject((Map<?, ?>)o).toString();
            }
            if (o instanceof Collection) {
                return new JSONArray((Collection<?>)o).toString();
            }
            if (o.getClass().isArray()) {
                return new JSONArray(o).toString();
            }
            if (o instanceof Enum) {
                return JSONObject.quote(((Enum)o).name());
            }
            return JSONObject.quote(o.toString());
        }
    }
    
    public JSONWriter value(final boolean b) throws JSONException {
        return this.append(b ? "true" : "false");
    }
    
    public JSONWriter value(final double n) throws JSONException {
        return this.value((Object)n);
    }
    
    public JSONWriter value(final long n) throws JSONException {
        return this.append(Long.toString(n));
    }
    
    public JSONWriter value(final Object o) throws JSONException {
        return this.append(valueToString(o));
    }
}
