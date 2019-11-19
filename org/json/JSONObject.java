package org.json;

import java.io.Writer;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.Closeable;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Set;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JSONObject
{
    static final Pattern NUMBER_PATTERN;
    private final Map<String, Object> map;
    public static final Object NULL;
    
    public JSONObject() {
        super();
        this.map = new HashMap<String, Object>();
    }
    
    public JSONObject(final JSONObject jsonObject, final String[] array) {
        this(array.length);
        for (int i = 0; i < array.length; ++i) {
            try {
                this.putOnce(array[i], jsonObject.opt(array[i]));
            }
            catch (Exception ex) {}
        }
    }
    
    public JSONObject(final JSONTokener jsonTokener) throws JSONException {
        this();
        if (jsonTokener.nextClean() != '{') {
            throw jsonTokener.syntaxError("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (jsonTokener.nextClean()) {
                case '\0':
                    throw jsonTokener.syntaxError("A JSONObject text must end with '}'");
                case '}':
                default: {
                    jsonTokener.back();
                    final String string = jsonTokener.nextValue().toString();
                    if (jsonTokener.nextClean() != ':') {
                        throw jsonTokener.syntaxError("Expected a ':' after a key");
                    }
                    if (string != null) {
                        if (this.opt(string) != null) {
                            throw jsonTokener.syntaxError("Duplicate key \"" + string + "\"");
                        }
                        final Object nextValue = jsonTokener.nextValue();
                        if (nextValue != null) {
                            this.put(string, nextValue);
                        }
                    }
                    switch (jsonTokener.nextClean()) {
                        case ',':
                        case ';':
                            if (jsonTokener.nextClean() == '}') {
                                return;
                            }
                            jsonTokener.back();
                            continue;
                        case '}':
                            return;
                        default:
                            throw jsonTokener.syntaxError("Expected a ',' or '}'");
                    }
                    break;
                }
            }
        }
    }
    
    public JSONObject(final Map<?, ?> map) {
        super();
        if (map == null) {
            this.map = new HashMap<String, Object>();
        }
        else {
            this.map = new HashMap<String, Object>(map.size());
            for (final Map.Entry<K, Object> entry : map.entrySet()) {
                if (entry.getKey() == null) {
                    throw new NullPointerException("Null key.");
                }
                final Object value = entry.getValue();
                if (value == null) {
                    continue;
                }
                this.map.put(String.valueOf(entry.getKey()), wrap(value));
            }
        }
    }
    
    public JSONObject(final Object o) {
        this();
        this.populateMap(o);
    }
    
    public JSONObject(final Object o, final String[] array) {
        this(array.length);
        final Class<?> class1 = o.getClass();
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            try {
                this.putOpt(s, class1.getField(s).get(o));
            }
            catch (Exception ex) {}
        }
    }
    
    public JSONObject(final String s) throws JSONException {
        this(new JSONTokener(s));
    }
    
    public JSONObject(final String s, final Locale locale) throws JSONException {
        this();
        final ResourceBundle bundle = ResourceBundle.getBundle(s, locale, Thread.currentThread().getContextClassLoader());
        final Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            final String nextElement = keys.nextElement();
            if (nextElement != null) {
                final String[] split = nextElement.split("\\.");
                final int n = split.length - 1;
                JSONObject jsonObject = this;
                for (final String s2 : split) {
                    JSONObject optJSONObject = jsonObject.optJSONObject(s2);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                        jsonObject.put(s2, optJSONObject);
                    }
                    jsonObject = optJSONObject;
                }
                jsonObject.put(split[n], bundle.getString(nextElement));
            }
        }
    }
    
    protected JSONObject(final int n) {
        super();
        this.map = new HashMap<String, Object>(n);
    }
    
    public JSONObject accumulate(final String s, final Object o) throws JSONException {
        testValidity(o);
        final Object opt = this.opt(s);
        if (opt == null) {
            this.put(s, (o instanceof JSONArray) ? new JSONArray().put(o) : o);
        }
        else if (opt instanceof JSONArray) {
            ((JSONArray)opt).put(o);
        }
        else {
            this.put(s, new JSONArray().put(opt).put(o));
        }
        return this;
    }
    
    public JSONObject append(final String s, final Object o) throws JSONException {
        testValidity(o);
        final Object opt = this.opt(s);
        if (opt == null) {
            this.put(s, new JSONArray().put(o));
        }
        else {
            if (!(opt instanceof JSONArray)) {
                throw new JSONException("JSONObject[" + s + "] is not a JSONArray.");
            }
            this.put(s, ((JSONArray)opt).put(o));
        }
        return this;
    }
    
    public static String doubleToString(final double n) {
        if (Double.isInfinite(n) || Double.isNaN(n)) {
            return "null";
        }
        String s = Double.toString(n);
        if (s.indexOf(46) > 0 && s.indexOf(101) < 0 && s.indexOf(69) < 0) {
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }
    
    public Object get(final String s) throws JSONException {
        if (s == null) {
            throw new JSONException("Null key.");
        }
        final Object opt = this.opt(s);
        if (opt == null) {
            throw new JSONException("JSONObject[" + quote(s) + "] not found.");
        }
        return opt;
    }
    
    public <E extends Enum<E>> E getEnum(final Class<E> clazz, final String s) throws JSONException {
        final Enum optEnum = this.<Enum>optEnum((Class<Enum>)clazz, s);
        if (optEnum == null) {
            throw new JSONException("JSONObject[" + quote(s) + "] is not an enum of type " + quote(clazz.getSimpleName()) + ".");
        }
        return (E)optEnum;
    }
    
    public boolean getBoolean(final String s) throws JSONException {
        final Object value = this.get(s);
        if (value.equals(Boolean.FALSE) || (value instanceof String && ((String)value).equalsIgnoreCase("false"))) {
            return false;
        }
        if (value.equals(Boolean.TRUE) || (value instanceof String && ((String)value).equalsIgnoreCase("true"))) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a Boolean.");
    }
    
    public BigInteger getBigInteger(final String s) throws JSONException {
        final Object value = this.get(s);
        final BigInteger objectToBigInteger = objectToBigInteger(value, null);
        if (objectToBigInteger != null) {
            return objectToBigInteger;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] could not be converted to BigInteger (" + value + ").");
    }
    
    public BigDecimal getBigDecimal(final String s) throws JSONException {
        final Object value = this.get(s);
        final BigDecimal objectToBigDecimal = objectToBigDecimal(value, null);
        if (objectToBigDecimal != null) {
            return objectToBigDecimal;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] could not be converted to BigDecimal (" + value + ").");
    }
    
    public double getDouble(final String s) throws JSONException {
        return this.getNumber(s).doubleValue();
    }
    
    public float getFloat(final String s) throws JSONException {
        return this.getNumber(s).floatValue();
    }
    
    public Number getNumber(final String s) throws JSONException {
        final Object value = this.get(s);
        try {
            if (value instanceof Number) {
                return (Number)value;
            }
            return stringToNumber(value.toString());
        }
        catch (Exception ex) {
            throw new JSONException("JSONObject[" + quote(s) + "] is not a number.", ex);
        }
    }
    
    public int getInt(final String s) throws JSONException {
        return this.getNumber(s).intValue();
    }
    
    public JSONArray getJSONArray(final String s) throws JSONException {
        final Object value = this.get(s);
        if (value instanceof JSONArray) {
            return (JSONArray)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a JSONArray.");
    }
    
    public JSONObject getJSONObject(final String s) throws JSONException {
        final Object value = this.get(s);
        if (value instanceof JSONObject) {
            return (JSONObject)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] is not a JSONObject.");
    }
    
    public long getLong(final String s) throws JSONException {
        return this.getNumber(s).longValue();
    }
    
    public static String[] getNames(final JSONObject jsonObject) {
        if (jsonObject.isEmpty()) {
            return null;
        }
        return jsonObject.keySet().<String>toArray(new String[jsonObject.length()]);
    }
    
    public static String[] getNames(final Object o) {
        if (o == null) {
            return null;
        }
        final int length = o.getClass().getFields().length;
        return null;
    }
    
    public String getString(final String s) throws JSONException {
        final Object value = this.get(s);
        if (value instanceof String) {
            return (String)value;
        }
        throw new JSONException("JSONObject[" + quote(s) + "] not a string.");
    }
    
    public boolean has(final String s) {
        return this.map.containsKey(s);
    }
    
    public JSONObject increment(final String s) throws JSONException {
        final Object opt = this.opt(s);
        if (opt == null) {
            this.put(s, 1);
        }
        else if (opt instanceof BigInteger) {
            this.put(s, ((BigInteger)opt).add(BigInteger.ONE));
        }
        else if (opt instanceof BigDecimal) {
            this.put(s, ((BigDecimal)opt).add(BigDecimal.ONE));
        }
        else if (opt instanceof Integer) {
            this.put(s, (int)opt + 1);
        }
        else if (opt instanceof Long) {
            this.put(s, (long)opt + 1L);
        }
        else if (opt instanceof Double) {
            this.put(s, (double)opt + 1.0);
        }
        else {
            if (!(opt instanceof Float)) {
                throw new JSONException("Unable to increment [" + quote(s) + "].");
            }
            this.put(s, (float)opt + 1.0f);
        }
        return this;
    }
    
    public boolean isNull(final String s) {
        return JSONObject.NULL.equals(this.opt(s));
    }
    
    public Iterator<String> keys() {
        return this.keySet().iterator();
    }
    
    public Set<String> keySet() {
        return this.map.keySet();
    }
    
    protected Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }
    
    public int length() {
        return this.map.size();
    }
    
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    public JSONArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return new JSONArray(this.map.keySet());
    }
    
    public static String numberToString(final Number n) throws JSONException {
        if (n == null) {
            throw new JSONException("Null pointer");
        }
        testValidity(n);
        String s = n.toString();
        if (s.indexOf(46) > 0 && s.indexOf(101) < 0 && s.indexOf(69) < 0) {
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }
    
    public Object opt(final String s) {
        return (s == null) ? null : this.map.get(s);
    }
    
    public <E extends Enum<E>> E optEnum(final Class<E> clazz, final String s) {
        return this.<E>optEnum(clazz, s, (E)null);
    }
    
    public <E extends Enum<E>> E optEnum(final Class<E> clazz, final String s, final E e) {
        try {
            final Object opt = this.opt(s);
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
    
    public boolean optBoolean(final String s) {
        return this.optBoolean(s, false);
    }
    
    public boolean optBoolean(final String s, final boolean b) {
        final Object opt = this.opt(s);
        if (JSONObject.NULL.equals(opt)) {
            return b;
        }
        if (opt instanceof Boolean) {
            return (boolean)opt;
        }
        try {
            return this.getBoolean(s);
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    public BigDecimal optBigDecimal(final String s, final BigDecimal bigDecimal) {
        return objectToBigDecimal(this.opt(s), bigDecimal);
    }
    
    static BigDecimal objectToBigDecimal(final Object o, final BigDecimal bigDecimal) {
        if (JSONObject.NULL.equals(o)) {
            return bigDecimal;
        }
        if (o instanceof BigDecimal) {
            return (BigDecimal)o;
        }
        if (o instanceof BigInteger) {
            return new BigDecimal((BigInteger)o);
        }
        if (o instanceof Double || o instanceof Float) {
            if (Double.isNaN(((Number)o).doubleValue())) {
                return bigDecimal;
            }
            return new BigDecimal(((Number)o).doubleValue());
        }
        else {
            if (o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) {
                return new BigDecimal(((Number)o).longValue());
            }
            try {
                return new BigDecimal(o.toString());
            }
            catch (Exception ex) {
                return bigDecimal;
            }
        }
    }
    
    public BigInteger optBigInteger(final String s, final BigInteger bigInteger) {
        return objectToBigInteger(this.opt(s), bigInteger);
    }
    
    static BigInteger objectToBigInteger(final Object o, final BigInteger bigInteger) {
        if (JSONObject.NULL.equals(o)) {
            return bigInteger;
        }
        if (o instanceof BigInteger) {
            return (BigInteger)o;
        }
        if (o instanceof BigDecimal) {
            return ((BigDecimal)o).toBigInteger();
        }
        if (o instanceof Double || o instanceof Float) {
            final double doubleValue = ((Number)o).doubleValue();
            if (Double.isNaN(doubleValue)) {
                return bigInteger;
            }
            return new BigDecimal(doubleValue).toBigInteger();
        }
        else {
            if (o instanceof Long || o instanceof Integer || o instanceof Short || o instanceof Byte) {
                return BigInteger.valueOf(((Number)o).longValue());
            }
            try {
                final String string = o.toString();
                if (isDecimalNotation(string)) {
                    return new BigDecimal(string).toBigInteger();
                }
                return new BigInteger(string);
            }
            catch (Exception ex) {
                return bigInteger;
            }
        }
    }
    
    public double optDouble(final String s) {
        return this.optDouble(s, Double.NaN);
    }
    
    public double optDouble(final String s, final double n) {
        final Number optNumber = this.optNumber(s);
        if (optNumber == null) {
            return n;
        }
        return optNumber.doubleValue();
    }
    
    public float optFloat(final String s) {
        return this.optFloat(s, Float.NaN);
    }
    
    public float optFloat(final String s, final float n) {
        final Number optNumber = this.optNumber(s);
        if (optNumber == null) {
            return n;
        }
        return optNumber.floatValue();
    }
    
    public int optInt(final String s) {
        return this.optInt(s, 0);
    }
    
    public int optInt(final String s, final int n) {
        final Number optNumber = this.optNumber(s, null);
        if (optNumber == null) {
            return n;
        }
        return optNumber.intValue();
    }
    
    public JSONArray optJSONArray(final String s) {
        final Object opt = this.opt(s);
        return (opt instanceof JSONArray) ? ((JSONArray)opt) : null;
    }
    
    public JSONObject optJSONObject(final String s) {
        final Object opt = this.opt(s);
        return (opt instanceof JSONObject) ? ((JSONObject)opt) : null;
    }
    
    public long optLong(final String s) {
        return this.optLong(s, 0L);
    }
    
    public long optLong(final String s, final long n) {
        final Number optNumber = this.optNumber(s, null);
        if (optNumber == null) {
            return n;
        }
        return optNumber.longValue();
    }
    
    public Number optNumber(final String s) {
        return this.optNumber(s, null);
    }
    
    public Number optNumber(final String s, final Number n) {
        final Object opt = this.opt(s);
        if (JSONObject.NULL.equals(opt)) {
            return n;
        }
        if (opt instanceof Number) {
            return (Number)opt;
        }
        try {
            return stringToNumber(opt.toString());
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public String optString(final String s) {
        return this.optString(s, "");
    }
    
    public String optString(final String s, final String s2) {
        final Object opt = this.opt(s);
        return JSONObject.NULL.equals(opt) ? s2 : opt.toString();
    }
    
    private void populateMap(final Object o) {
        final Class<?> class1 = o.getClass();
        for (final Method method : (class1.getClassLoader() != null) ? class1.getMethods() : class1.getDeclaredMethods()) {
            final int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0 && !method.isBridge() && method.getReturnType() != Void.TYPE && this.isValidMethodName(method.getName())) {
                final String keyNameFromMethod = this.getKeyNameFromMethod(method);
                if (keyNameFromMethod != null && !keyNameFromMethod.isEmpty()) {
                    try {
                        final Object invoke = method.invoke(o, new Object[0]);
                        if (invoke != null) {
                            this.map.put(keyNameFromMethod, wrap(invoke));
                            if (invoke instanceof Closeable) {
                                try {
                                    ((Closeable)invoke).close();
                                }
                                catch (IOException ex) {}
                            }
                        }
                    }
                    catch (IllegalAccessException ex2) {}
                    catch (IllegalArgumentException ex3) {}
                    catch (InvocationTargetException ex4) {}
                }
            }
        }
    }
    
    private boolean isValidMethodName(final String s) {
        return !"getClass".equals(s) && !"getDeclaringClass".equals(s);
    }
    
    private String getKeyNameFromMethod(final Method method) {
        final int annotationDepth = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (annotationDepth > 0) {
            final int annotationDepth2 = getAnnotationDepth(method, JSONPropertyName.class);
            if (annotationDepth2 < 0 || annotationDepth <= annotationDepth2) {
                return null;
            }
        }
        final JSONPropertyName jsonPropertyName = JSONObject.<JSONPropertyName>getAnnotation(method, JSONPropertyName.class);
        if (jsonPropertyName != null && jsonPropertyName.value() != null && !jsonPropertyName.value().isEmpty()) {
            return jsonPropertyName.value();
        }
        final String name = method.getName();
        String s;
        if (name.startsWith("get") && name.length() > 3) {
            s = name.substring(3);
        }
        else {
            if (!name.startsWith("is") || name.length() <= 2) {
                return null;
            }
            s = name.substring(2);
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return null;
        }
        if (s.length() == 1) {
            s = s.toLowerCase(Locale.ROOT);
        }
        else if (!Character.isUpperCase(s.charAt(1))) {
            s = s.substring(0, 1).toLowerCase(Locale.ROOT) + s.substring(1);
        }
        return s;
    }
    
    private static <A extends Annotation> A getAnnotation(final Method method, final Class<A> clazz) {
        if (method == null || clazz == null) {
            return null;
        }
        if (method.isAnnotationPresent(clazz)) {
            return method.<A>getAnnotation(clazz);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.getSuperclass() == null) {
            return null;
        }
        for (final Class clazz2 : declaringClass.getInterfaces()) {
            try {
                return (A)JSONObject.<Annotation>getAnnotation(clazz2.getMethod(method.getName(), (Class[])method.getParameterTypes()), (Class<Annotation>)clazz);
            }
            catch (SecurityException ex) {}
            catch (NoSuchMethodException ex2) {}
        }
        try {
            return (A)JSONObject.<Annotation>getAnnotation(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), (Class<Annotation>)clazz);
        }
        catch (SecurityException ex3) {
            return null;
        }
        catch (NoSuchMethodException ex4) {
            return null;
        }
    }
    
    private static int getAnnotationDepth(final Method method, final Class<? extends Annotation> clazz) {
        if (method == null || clazz == null) {
            return -1;
        }
        if (method.isAnnotationPresent(clazz)) {
            return 1;
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.getSuperclass() == null) {
            return -1;
        }
        for (final Class clazz2 : declaringClass.getInterfaces()) {
            try {
                final int annotationDepth = getAnnotationDepth(clazz2.getMethod(method.getName(), (Class[])method.getParameterTypes()), clazz);
                if (annotationDepth > 0) {
                    return annotationDepth + 1;
                }
            }
            catch (SecurityException ex) {}
            catch (NoSuchMethodException ex2) {}
        }
        try {
            final int annotationDepth2 = getAnnotationDepth(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), clazz);
            if (annotationDepth2 > 0) {
                return annotationDepth2 + 1;
            }
            return -1;
        }
        catch (SecurityException ex3) {
            return -1;
        }
        catch (NoSuchMethodException ex4) {
            return -1;
        }
    }
    
    public JSONObject put(final String s, final boolean b) throws JSONException {
        return this.put(s, b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public JSONObject put(final String s, final Collection<?> collection) throws JSONException {
        return this.put(s, new JSONArray(collection));
    }
    
    public JSONObject put(final String s, final double n) throws JSONException {
        return this.put(s, (Object)n);
    }
    
    public JSONObject put(final String s, final float n) throws JSONException {
        return this.put(s, (Object)n);
    }
    
    public JSONObject put(final String s, final int n) throws JSONException {
        return this.put(s, (Object)n);
    }
    
    public JSONObject put(final String s, final long n) throws JSONException {
        return this.put(s, (Object)n);
    }
    
    public JSONObject put(final String s, final Map<?, ?> map) throws JSONException {
        return this.put(s, new JSONObject(map));
    }
    
    public JSONObject put(final String s, final Object o) throws JSONException {
        if (s == null) {
            throw new NullPointerException("Null key.");
        }
        if (o != null) {
            testValidity(o);
            this.map.put(s, o);
        }
        else {
            this.remove(s);
        }
        return this;
    }
    
    public JSONObject putOnce(final String s, final Object o) throws JSONException {
        if (s == null || o == null) {
            return this;
        }
        if (this.opt(s) != null) {
            throw new JSONException("Duplicate key \"" + s + "\"");
        }
        return this.put(s, o);
    }
    
    public JSONObject putOpt(final String s, final Object o) throws JSONException {
        if (s != null && o != null) {
            return this.put(s, o);
        }
        return this;
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
    
    public static String quote(final String s) {
        final StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                return quote(s, stringWriter).toString();
            }
            catch (IOException ex) {
                return "";
            }
        }
    }
    
    public static Writer quote(final String s, final Writer writer) throws IOException {
        if (s == null || s.isEmpty()) {
            writer.write("\"\"");
            return writer;
        }
        int char1 = 0;
        final int length = s.length();
        writer.write(34);
        for (int i = 0; i < length; ++i) {
            final int n = char1;
            char1 = s.charAt(i);
            switch (char1) {
                case 34:
                case 92:
                    writer.write(92);
                    writer.write(char1);
                    break;
                case 47:
                    if (n == 60) {
                        writer.write(92);
                    }
                    writer.write(char1);
                    break;
                case 8:
                    writer.write("\\b");
                    break;
                case 9:
                    writer.write("\\t");
                    break;
                case 10:
                    writer.write("\\n");
                    break;
                case 12:
                    writer.write("\\f");
                    break;
                case 13:
                    writer.write("\\r");
                    break;
                default:
                    if (char1 < 32 || (char1 >= 128 && char1 < 160) || (char1 >= 8192 && char1 < 8448)) {
                        writer.write("\\u");
                        final String hexString = Integer.toHexString(char1);
                        writer.write("0000", 0, 4 - hexString.length());
                        writer.write(hexString);
                        break;
                    }
                    writer.write(char1);
                    break;
            }
        }
        writer.write(34);
        return writer;
    }
    
    public Object remove(final String s) {
        return this.map.remove(s);
    }
    
    public boolean similar(final Object o) {
        try {
            if (!(o instanceof JSONObject)) {
                return false;
            }
            if (!this.keySet().equals(((JSONObject)o).keySet())) {
                return false;
            }
            for (final Map.Entry<String, Object> entry : this.entrySet()) {
                final String s = entry.getKey();
                final JSONArray value = entry.getValue();
                final Object value2 = ((JSONObject)o).get(s);
                if (value == value2) {
                    continue;
                }
                if (value == null) {
                    return false;
                }
                if (value instanceof JSONObject) {
                    if (!((JSONObject)value).similar(value2)) {
                        return false;
                    }
                    continue;
                }
                else if (value instanceof JSONArray) {
                    if (!value.similar(value2)) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (!value.equals(value2)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    protected static boolean isDecimalNotation(final String s) {
        return s.indexOf(46) > -1 || s.indexOf(101) > -1 || s.indexOf(69) > -1 || "-0".equals(s);
    }
    
    protected static Number stringToNumber(final String s) throws NumberFormatException {
        final char char1 = s.charAt(0);
        if ((char1 < '0' || char1 > '9') && char1 != '-') {
            throw new NumberFormatException("val [" + s + "] is not a valid number.");
        }
        if (isDecimalNotation(s)) {
            if (s.length() > 14) {
                return new BigDecimal(s);
            }
            final Double value = Double.valueOf(s);
            if (value.isInfinite() || value.isNaN()) {
                return new BigDecimal(s);
            }
            return value;
        }
        else {
            final BigInteger bigInteger = new BigInteger(s);
            if (bigInteger.bitLength() <= 31) {
                return bigInteger.intValue();
            }
            if (bigInteger.bitLength() <= 63) {
                return bigInteger.longValue();
            }
            return bigInteger;
        }
    }
    
    public static Object stringToValue(final String s) {
        if ("".equals(s)) {
            return s;
        }
        if ("true".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        if ("null".equalsIgnoreCase(s)) {
            return JSONObject.NULL;
        }
        final char char1 = s.charAt(0);
        if (char1 < '0' || char1 > '9') {
            if (char1 != '-') {
                return s;
            }
        }
        try {
            if (isDecimalNotation(s)) {
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
    
    public static void testValidity(final Object o) throws JSONException {
        if (o != null) {
            if (o instanceof Double) {
                if (((Double)o).isInfinite() || ((Double)o).isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
            else if (o instanceof Float && (((Float)o).isInfinite() || ((Float)o).isNaN())) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }
    
    public JSONArray toJSONArray(final JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.isEmpty()) {
            return null;
        }
        final JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < jsonArray.length(); ++i) {
            jsonArray2.put(this.opt(jsonArray.getString(i)));
        }
        return jsonArray2;
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
    
    public static String valueToString(final Object o) throws JSONException {
        return JSONWriter.valueToString(o);
    }
    
    public static Object wrap(final Object o) {
        try {
            if (o == null) {
                return JSONObject.NULL;
            }
            if (o instanceof JSONObject || o instanceof JSONArray || JSONObject.NULL.equals(o) || o instanceof JSONString || o instanceof Byte || o instanceof Character || o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof Boolean || o instanceof Float || o instanceof Double || o instanceof String || o instanceof BigInteger || o instanceof BigDecimal || o instanceof Enum) {
                return o;
            }
            if (o instanceof Collection) {
                return new JSONArray((Collection<?>)o);
            }
            if (o.getClass().isArray()) {
                return new JSONArray(o);
            }
            if (o instanceof Map) {
                return new JSONObject((Map<?, ?>)o);
            }
            final Package package1 = o.getClass().getPackage();
            final String s = (package1 != null) ? package1.getName() : "";
            if (s.startsWith("java.") || s.startsWith("javax.") || o.getClass().getClassLoader() == null) {
                return o.toString();
            }
            return new JSONObject(o);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Writer write(final Writer writer) throws JSONException {
        return this.write(writer, 0, 0);
    }
    
    static final Writer writeValue(final Writer writer, final Object o, final int n, final int n2) throws JSONException, IOException {
        if (o == null || o.equals(null)) {
            writer.write("null");
        }
        else if (o instanceof JSONString) {
            String jsonString;
            try {
                jsonString = ((JSONString)o).toJSONString();
            }
            catch (Exception ex) {
                throw new JSONException(ex);
            }
            writer.write((jsonString != null) ? jsonString.toString() : quote(o.toString()));
        }
        else if (o instanceof Number) {
            final String numberToString = numberToString((Number)o);
            if (JSONObject.NUMBER_PATTERN.matcher(numberToString).matches()) {
                writer.write(numberToString);
            }
            else {
                quote(numberToString, writer);
            }
        }
        else if (o instanceof Boolean) {
            writer.write(o.toString());
        }
        else if (o instanceof Enum) {
            writer.write(quote(((Enum)o).name()));
        }
        else if (o instanceof JSONObject) {
            ((JSONObject)o).write(writer, n, n2);
        }
        else if (o instanceof JSONArray) {
            ((JSONArray)o).write(writer, n, n2);
        }
        else if (o instanceof Map) {
            new JSONObject((Map<?, ?>)o).write(writer, n, n2);
        }
        else if (o instanceof Collection) {
            new JSONArray((Collection<?>)o).write(writer, n, n2);
        }
        else if (o.getClass().isArray()) {
            new JSONArray(o).write(writer, n, n2);
        }
        else {
            quote(o.toString(), writer);
        }
        return writer;
    }
    
    static final void indent(final Writer writer, final int n) throws IOException {
        for (int i = 0; i < n; ++i) {
            writer.write(32);
        }
    }
    
    public Writer write(final Writer writer, final int n, final int n2) throws JSONException {
        try {
            int n3 = 0;
            final int length = this.length();
            writer.write(123);
            if (length == 1) {
                final Map.Entry<String, Object> entry = this.entrySet().iterator().next();
                final String s = entry.getKey();
                writer.write(quote(s));
                writer.write(58);
                if (n > 0) {
                    writer.write(32);
                }
                try {
                    writeValue(writer, entry.getValue(), n, n2);
                }
                catch (Exception ex) {
                    throw new JSONException("Unable to write JSONObject value for key: " + s, ex);
                }
            }
            else if (length != 0) {
                final int n4 = n2 + n;
                for (final Map.Entry<String, Object> entry2 : this.entrySet()) {
                    if (n3 != 0) {
                        writer.write(44);
                    }
                    if (n > 0) {
                        writer.write(10);
                    }
                    indent(writer, n4);
                    final String s2 = entry2.getKey();
                    writer.write(quote(s2));
                    writer.write(58);
                    if (n > 0) {
                        writer.write(32);
                    }
                    try {
                        writeValue(writer, entry2.getValue(), n, n4);
                    }
                    catch (Exception ex2) {
                        throw new JSONException("Unable to write JSONObject value for key: " + s2, ex2);
                    }
                    n3 = 1;
                }
                if (n > 0) {
                    writer.write(10);
                }
                indent(writer, n2);
            }
            writer.write(125);
            return writer;
        }
        catch (IOException ex3) {
            throw new JSONException(ex3);
        }
    }
    
    public Map<String, Object> toMap() {
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        for (final Map.Entry<String, Object> entry : this.entrySet()) {
            Object o;
            if (entry.getValue() == null || JSONObject.NULL.equals(entry.getValue())) {
                o = null;
            }
            else if (entry.getValue() instanceof JSONObject) {
                o = entry.getValue().toMap();
            }
            else if (entry.getValue() instanceof JSONArray) {
                o = entry.getValue().toList();
            }
            else {
                o = entry.getValue();
            }
            hashMap.put(entry.getKey(), o);
        }
        return (Map<String, Object>)hashMap;
    }
    
    static {
        NUMBER_PATTERN = Pattern.compile("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?");
        NULL = new Null();
    }
}
