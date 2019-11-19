package org.json;

import java.util.List;
import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import java.util.Map;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class JSONArray implements Iterable<Object>
{
    private final ArrayList<Object> myArrayList;
    
    public JSONArray() {
        super();
        this.myArrayList = new ArrayList<Object>();
    }
    
    public JSONArray(final JSONTokener jsonTokener) throws JSONException {
        this();
        if (jsonTokener.nextClean() != '[') {
            throw jsonTokener.syntaxError("A JSONArray text must start with '['");
        }
        jsonTokener.nextClean();
        throw jsonTokener.syntaxError("Expected a ',' or ']'");
    }
    
    public JSONArray(final String s) throws JSONException {
        this(new JSONTokener(s));
    }
    
    public JSONArray(final Collection<?> collection) {
        super();
        if (collection == null) {
            this.myArrayList = new ArrayList<Object>();
        }
        else {
            this.myArrayList = new ArrayList<Object>(collection.size());
            final Iterator<Object> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.myArrayList.add(JSONObject.wrap(iterator.next()));
            }
        }
    }
    
    public JSONArray(final Object o) throws JSONException {
        this();
        if (o.getClass().isArray()) {
            final int length = Array.getLength(o);
            this.myArrayList.ensureCapacity(length);
            for (int i = 0; i < length; ++i) {
                this.put(JSONObject.wrap(Array.get(o, i)));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }
    
    @Override
    public Iterator<Object> iterator() {
        return this.myArrayList.iterator();
    }
    
    public Object get(final int n) throws JSONException {
        final Object opt = this.opt(n);
        if (opt == null) {
            throw new JSONException("JSONArray[" + n + "] not found.");
        }
        return opt;
    }
    
    public boolean getBoolean(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value.equals(Boolean.FALSE) || (value instanceof String && ((String)value).equalsIgnoreCase("false"))) {
            return false;
        }
        if (value.equals(Boolean.TRUE) || (value instanceof String && ((String)value).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONArray[" + n + "] is not a boolean.");
    }
    
    public double getDouble(final int n) throws JSONException {
        return this.getNumber(n).doubleValue();
    }
    
    public float getFloat(final int n) throws JSONException {
        return this.getNumber(n).floatValue();
    }
    
    public Number getNumber(final int n) throws JSONException {
        final Object value = this.get(n);
        try {
            if (value instanceof Number) {
                return (Number)value;
            }
            return JSONObject.stringToNumber(value.toString());
        }
        catch (Exception ex) {
            throw new JSONException("JSONArray[" + n + "] is not a number.", ex);
        }
    }
    
    public <E extends Enum<E>> E getEnum(final Class<E> clazz, final int n) throws JSONException {
        final Enum optEnum = this.<Enum>optEnum((Class<Enum>)clazz, n);
        if (optEnum == null) {
            throw new JSONException("JSONArray[" + n + "] is not an enum of type " + JSONObject.quote(clazz.getSimpleName()) + ".");
        }
        return (E)optEnum;
    }
    
    public BigDecimal getBigDecimal(final int n) throws JSONException {
        final Object value = this.get(n);
        final BigDecimal objectToBigDecimal = JSONObject.objectToBigDecimal(value, null);
        if (objectToBigDecimal == null) {
            throw new JSONException("JSONArray[" + n + "] could not convert to BigDecimal (" + value + ").");
        }
        return objectToBigDecimal;
    }
    
    public BigInteger getBigInteger(final int n) throws JSONException {
        final Object value = this.get(n);
        final BigInteger objectToBigInteger = JSONObject.objectToBigInteger(value, null);
        if (objectToBigInteger == null) {
            throw new JSONException("JSONArray[" + n + "] could not convert to BigDecimal (" + value + ").");
        }
        return objectToBigInteger;
    }
    
    public int getInt(final int n) throws JSONException {
        return this.getNumber(n).intValue();
    }
    
    public JSONArray getJSONArray(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONArray.");
    }
    
    public JSONObject getJSONObject(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        throw new JSONException("JSONArray[" + n + "] is not a JSONObject.");
    }
    
    public long getLong(final int n) throws JSONException {
        return this.getNumber(n).longValue();
    }
    
    public String getString(final int n) throws JSONException {
        final Object value = this.get(n);
        if (value instanceof String) {
            return (String)value;
        }
        throw new JSONException("JSONArray[" + n + "] not a string.");
    }
    
    public boolean isNull(final int n) {
        return JSONObject.NULL.equals(this.opt(n));
    }
    
    public String join(final String s) throws JSONException {
        this.length();
        return "";
    }
    
    public int length() {
        return this.myArrayList.size();
    }
    
    public Object opt(final int n) {
        return (n < 0 || n >= this.length()) ? null : this.myArrayList.get(n);
    }
    
    public boolean optBoolean(final int n) {
        return this.optBoolean(n, false);
    }
    
    public boolean optBoolean(final int n, final boolean b) {
        try {
            return this.getBoolean(n);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public double optDouble(final int n) {
        return this.optDouble(n, Double.NaN);
    }
    
    public double optDouble(final int n, final double n2) {
        final Number optNumber = this.optNumber(n, null);
        if (optNumber == null) {
            return n2;
        }
        return optNumber.doubleValue();
    }
    
    public float optFloat(final int n) {
        return this.optFloat(n, Float.NaN);
    }
    
    public float optFloat(final int n, final float n2) {
        final Number optNumber = this.optNumber(n, null);
        if (optNumber == null) {
            return n2;
        }
        return optNumber.floatValue();
    }
    
    public int optInt(final int n) {
        return this.optInt(n, 0);
    }
    
    public int optInt(final int n, final int n2) {
        final Number optNumber = this.optNumber(n, null);
        if (optNumber == null) {
            return n2;
        }
        return optNumber.intValue();
    }
    
    public <E extends Enum<E>> E optEnum(final Class<E> clazz, final int n) {
        return this.<E>optEnum(clazz, n, (E)null);
    }
    
    public <E extends Enum<E>> E optEnum(final Class<E> clazz, final int n, final E e) {
        try {
            final Object opt = this.opt(n);
            if (JSONObject.NULL.equals(opt)) {
                return e;
            }
            if (clazz.isAssignableFrom(((Enum<E>)opt).getClass())) {
                return (E)opt;
            }
            return Enum.<E>valueOf(clazz, opt.toString());
        }
        catch (IllegalArgumentException ex) {
            return e;
        }
        catch (NullPointerException ex2) {
            return e;
        }
    }
    
    public BigInteger optBigInteger(final int n, final BigInteger bigInteger) {
        return JSONObject.objectToBigInteger(this.opt(n), bigInteger);
    }
    
    public BigDecimal optBigDecimal(final int n, final BigDecimal bigDecimal) {
        return JSONObject.objectToBigDecimal(this.opt(n), bigDecimal);
    }
    
    public JSONArray optJSONArray(final int n) {
        final Object opt = this.opt(n);
        return (opt instanceof JSONArray) ? ((JSONArray)opt) : null;
    }
    
    public JSONObject optJSONObject(final int n) {
        final Object opt = this.opt(n);
        return (opt instanceof JSONObject) ? ((JSONObject)opt) : null;
    }
    
    public long optLong(final int n) {
        return this.optLong(n, 0L);
    }
    
    public long optLong(final int n, final long n2) {
        final Number optNumber = this.optNumber(n, null);
        if (optNumber == null) {
            return n2;
        }
        return optNumber.longValue();
    }
    
    public Number optNumber(final int n) {
        return this.optNumber(n, null);
    }
    
    public Number optNumber(final int n, final Number n2) {
        final Object opt = this.opt(n);
        if (JSONObject.NULL.equals(opt)) {
            return n2;
        }
        if (opt instanceof Number) {
            return (Number)opt;
        }
        if (opt instanceof String) {
            try {
                return JSONObject.stringToNumber((String)opt);
            }
            catch (Exception ex) {
                return n2;
            }
        }
        return n2;
    }
    
    public String optString(final int n) {
        return this.optString(n, "");
    }
    
    public String optString(final int n, final String s) {
        final Object opt = this.opt(n);
        return JSONObject.NULL.equals(opt) ? s : opt.toString();
    }
    
    public JSONArray put(final boolean b) {
        return this.put(b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public JSONArray put(final Collection<?> collection) {
        return this.put(new JSONArray(collection));
    }
    
    public JSONArray put(final double n) throws JSONException {
        return this.put((Object)n);
    }
    
    public JSONArray put(final float n) throws JSONException {
        return this.put((Object)n);
    }
    
    public JSONArray put(final int n) {
        return this.put((Object)n);
    }
    
    public JSONArray put(final long n) {
        return this.put((Object)n);
    }
    
    public JSONArray put(final Map<?, ?> map) {
        return this.put(new JSONObject(map));
    }
    
    public JSONArray put(final Object o) {
        JSONObject.testValidity(o);
        this.myArrayList.add(o);
        return this;
    }
    
    public JSONArray put(final int n, final boolean b) throws JSONException {
        return this.put(n, b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public JSONArray put(final int n, final Collection<?> collection) throws JSONException {
        return this.put(n, new JSONArray(collection));
    }
    
    public JSONArray put(final int n, final double n2) throws JSONException {
        return this.put(n, (Object)n2);
    }
    
    public JSONArray put(final int n, final float n2) throws JSONException {
        return this.put(n, (Object)n2);
    }
    
    public JSONArray put(final int n, final int n2) throws JSONException {
        return this.put(n, (Object)n2);
    }
    
    public JSONArray put(final int n, final long n2) throws JSONException {
        return this.put(n, (Object)n2);
    }
    
    public JSONArray put(final int n, final Map<?, ?> map) throws JSONException {
        this.put(n, new JSONObject(map));
        return this;
    }
    
    public JSONArray put(final int i, final Object o) throws JSONException {
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < this.length()) {
            JSONObject.testValidity(o);
            this.myArrayList.set(i, o);
            return this;
        }
        if (i == this.length()) {
            return this.put(o);
        }
        this.myArrayList.ensureCapacity(i + 1);
        while (i != this.length()) {
            this.myArrayList.add(JSONObject.NULL);
        }
        return this.put(o);
    }
    
    public Object query(final String s) {
        return this.query(new JSONPointer(s));
    }
    
    public Object query(final JSONPointer jsonPointer) {
        return jsonPointer.queryFrom(this);
    }
    
    public Object optQuery(final String s) {
        return this.optQuery(new JSONPointer(s));
    }
    
    public Object optQuery(final JSONPointer jsonPointer) {
        try {
            return jsonPointer.queryFrom(this);
        }
        catch (JSONPointerException ex) {
            return null;
        }
    }
    
    public Object remove(final int n) {
        return (n >= 0 && n < this.length()) ? this.myArrayList.remove(n) : null;
    }
    
    public boolean similar(final Object o) {
        if (!(o instanceof JSONArray)) {
            return false;
        }
        final int length = this.length();
        if (length != ((JSONArray)o).length()) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            final Object value = this.myArrayList.get(i);
            final Object value2 = ((JSONArray)o).myArrayList.get(i);
            if (value != value2) {
                if (value == null) {
                    return false;
                }
                if (value instanceof JSONObject) {
                    if (!((JSONObject)value).similar(value2)) {
                        return false;
                    }
                }
                else if (value instanceof JSONArray) {
                    if (!((JSONArray)value).similar(value2)) {
                        return false;
                    }
                }
                else if (!value.equals(value2)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public JSONObject toJSONObject(final JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.isEmpty() || this.isEmpty()) {
            return null;
        }
        final JSONObject jsonObject = new JSONObject(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            jsonObject.put(jsonArray.getString(i), this.opt(i));
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        try {
            return this.toString(0);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String toString(final int n) throws JSONException {
        final StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            return this.write(stringWriter, n, 0).toString();
        }
    }
    
    public Writer write(final Writer writer) throws JSONException {
        return this.write(writer, 0, 0);
    }
    
    public Writer write(final Writer writer, final int n, final int n2) throws JSONException {
        try {
            int n3 = 0;
            final int length = this.length();
            writer.write(91);
            Label_0178: {
                if (length == 1) {
                    try {
                        JSONObject.writeValue(writer, this.myArrayList.get(0), n, n2);
                        break Label_0178;
                    }
                    catch (Exception ex) {
                        throw new JSONException("Unable to write JSONArray value at index: 0", ex);
                    }
                }
                if (length != 0) {
                    final int n4 = n2 + n;
                    for (int i = 0; i < length; ++i) {
                        if (n3 != 0) {
                            writer.write(44);
                        }
                        if (n > 0) {
                            writer.write(10);
                        }
                        JSONObject.indent(writer, n4);
                        try {
                            JSONObject.writeValue(writer, this.myArrayList.get(i), n, n4);
                        }
                        catch (Exception ex2) {
                            throw new JSONException("Unable to write JSONArray value at index: " + i, ex2);
                        }
                        n3 = 1;
                    }
                    if (n > 0) {
                        writer.write(10);
                    }
                    JSONObject.indent(writer, n2);
                }
            }
            writer.write(93);
            return writer;
        }
        catch (IOException ex3) {
            throw new JSONException(ex3);
        }
    }
    
    public List<Object> toList() {
        final ArrayList<List<Object>> list = (ArrayList<List<Object>>)new ArrayList<JSONObject>(this.myArrayList.size());
        for (final JSONArray next : this.myArrayList) {
            if (next == null || JSONObject.NULL.equals(next)) {
                list.add((JSONObject)null);
            }
            else if (next instanceof JSONArray) {
                list.add((JSONObject)next.toList());
            }
            else if (next instanceof JSONObject) {
                list.add((JSONObject)((JSONObject)next).toMap());
            }
            else {
                list.add((JSONObject)next);
            }
        }
        return (List<Object>)list;
    }
    
    public boolean isEmpty() {
        return this.myArrayList.isEmpty();
    }
}
