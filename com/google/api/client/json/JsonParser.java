package com.google.api.client.json;

import java.util.concurrent.locks.ReentrantLock;
import java.util.HashSet;
import java.util.Iterator;
import com.google.api.client.util.Sets;
import java.util.Locale;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import com.google.api.client.util.Data;
import java.util.Collection;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Types;
import java.util.Map;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ClassInfo;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.api.client.util.Preconditions;
import java.util.Set;
import java.util.Collections;
import com.google.api.client.util.Beta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

public abstract class JsonParser
{
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields;
    private static final Lock lock;
    
    public JsonParser() {
        super();
    }
    
    public abstract JsonFactory getFactory();
    
    public abstract void close() throws IOException;
    
    public abstract JsonToken nextToken() throws IOException;
    
    public abstract JsonToken getCurrentToken();
    
    public abstract String getCurrentName() throws IOException;
    
    public abstract JsonParser skipChildren() throws IOException;
    
    public abstract String getText() throws IOException;
    
    public abstract byte getByteValue() throws IOException;
    
    public abstract short getShortValue() throws IOException;
    
    public abstract int getIntValue() throws IOException;
    
    public abstract float getFloatValue() throws IOException;
    
    public abstract long getLongValue() throws IOException;
    
    public abstract double getDoubleValue() throws IOException;
    
    public abstract BigInteger getBigIntegerValue() throws IOException;
    
    public abstract BigDecimal getDecimalValue() throws IOException;
    
    public final <T> T parseAndClose(final Class<T> clazz) throws IOException {
        return this.<T>parseAndClose(clazz, null);
    }
    
    @Beta
    public final <T> T parseAndClose(final Class<T> clazz, final CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return (T)this.<Object>parse((Class<Object>)clazz, customizeJsonParser);
        }
        finally {
            this.close();
        }
    }
    
    public final void skipToKey(final String s) throws IOException {
        this.skipToKey(Collections.<String>singleton(s));
    }
    
    public final String skipToKey(final Set<String> set) throws IOException {
        for (JsonToken jsonToken = this.startParsingObjectOrArray(); jsonToken == JsonToken.FIELD_NAME; jsonToken = this.nextToken()) {
            final String text = this.getText();
            this.nextToken();
            if (set.contains(text)) {
                return text;
            }
            this.skipChildren();
        }
        return null;
    }
    
    private JsonToken startParsing() throws IOException {
        JsonToken jsonToken = this.getCurrentToken();
        if (jsonToken == null) {
            jsonToken = this.nextToken();
        }
        Preconditions.checkArgument(jsonToken != null, (Object)"no JSON input found");
        return jsonToken;
    }
    
    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken jsonToken = this.startParsing();
        switch (jsonToken) {
            case START_OBJECT:
                jsonToken = this.nextToken();
                Preconditions.checkArgument(jsonToken == JsonToken.FIELD_NAME || jsonToken == JsonToken.END_OBJECT, jsonToken);
                break;
            case START_ARRAY:
                jsonToken = this.nextToken();
                break;
        }
        return jsonToken;
    }
    
    public final void parseAndClose(final Object o) throws IOException {
        this.parseAndClose(o, null);
    }
    
    @Beta
    public final void parseAndClose(final Object o, final CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            this.parse(o, customizeJsonParser);
        }
        finally {
            this.close();
        }
    }
    
    public final <T> T parse(final Class<T> clazz) throws IOException {
        return this.<T>parse(clazz, null);
    }
    
    @Beta
    public final <T> T parse(final Class<T> clazz, final CustomizeJsonParser customizeJsonParser) throws IOException {
        return (T)this.parse(clazz, false, customizeJsonParser);
    }
    
    public Object parse(final Type type, final boolean b) throws IOException {
        return this.parse(type, b, null);
    }
    
    @Beta
    public Object parse(final Type type, final boolean b, final CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                this.startParsing();
            }
            return this.parseValue(null, type, new ArrayList<Type>(), null, customizeJsonParser, true);
        }
        finally {
            if (b) {
                this.close();
            }
        }
    }
    
    public final void parse(final Object o) throws IOException {
        this.parse(o, null);
    }
    
    @Beta
    public final void parse(final Object o, final CustomizeJsonParser customizeJsonParser) throws IOException {
        final ArrayList<Type> list = new ArrayList<Type>();
        list.add(o.getClass());
        this.parse(list, o, customizeJsonParser);
    }
    
    private void parse(final ArrayList<Type> list, final Object o, final CustomizeJsonParser customizeJsonParser) throws IOException {
        if (o instanceof GenericJson) {
            ((GenericJson)o).setFactory(this.getFactory());
        }
        JsonToken jsonToken = this.startParsingObjectOrArray();
        final Class<?> class1 = o.getClass();
        final ClassInfo of = ClassInfo.of(class1);
        final boolean assignable = GenericData.class.isAssignableFrom(class1);
        if (Map.class.isAssignableFrom(class1)) {
            this.parseMap(null, (Map<String, Object>)o, Types.getMapValueParameter(class1), list, customizeJsonParser);
            return;
        }
        while (jsonToken == JsonToken.FIELD_NAME) {
            final String text = this.getText();
            this.nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(o, text)) {
                return;
            }
            final FieldInfo fieldInfo = of.getFieldInfo(text);
            if (fieldInfo != null) {
                if (fieldInfo.isFinal() && !fieldInfo.isPrimitive()) {
                    throw new IllegalArgumentException("final array/object fields are not supported");
                }
                final Field field = fieldInfo.getField();
                final int size = list.size();
                list.add(field.getGenericType());
                final Object value = this.parseValue(field, fieldInfo.getGenericType(), list, o, customizeJsonParser, true);
                list.remove(size);
                fieldInfo.setValue(o, value);
            }
            else if (assignable) {
                ((GenericData)o).set(text, this.parseValue(null, null, list, o, customizeJsonParser, true));
            }
            else {
                if (customizeJsonParser != null) {
                    customizeJsonParser.handleUnrecognizedKey(o, text);
                }
                this.skipChildren();
            }
            jsonToken = this.nextToken();
        }
    }
    
    public final <T> Collection<T> parseArrayAndClose(final Class<?> clazz, final Class<T> clazz2) throws IOException {
        return this.<T>parseArrayAndClose(clazz, clazz2, null);
    }
    
    @Beta
    public final <T> Collection<T> parseArrayAndClose(final Class<?> clazz, final Class<T> clazz2, final CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            return (Collection<T>)this.<Object>parseArray(clazz, (Class<Object>)clazz2, customizeJsonParser);
        }
        finally {
            this.close();
        }
    }
    
    public final <T> void parseArrayAndClose(final Collection<? super T> collection, final Class<T> clazz) throws IOException {
        this.<T>parseArrayAndClose(collection, clazz, null);
    }
    
    @Beta
    public final <T> void parseArrayAndClose(final Collection<? super T> collection, final Class<T> clazz, final CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            this.<Object>parseArray((Collection<? super Object>)collection, (Class<Object>)clazz, customizeJsonParser);
        }
        finally {
            this.close();
        }
    }
    
    public final <T> Collection<T> parseArray(final Class<?> clazz, final Class<T> clazz2) throws IOException {
        return this.<T>parseArray(clazz, clazz2, null);
    }
    
    @Beta
    public final <T> Collection<T> parseArray(final Class<?> clazz, final Class<T> clazz2, final CustomizeJsonParser customizeJsonParser) throws IOException {
        final Collection<Object> collectionInstance = Data.newCollectionInstance(clazz);
        this.<T>parseArray(collectionInstance, clazz2, customizeJsonParser);
        return (Collection<T>)collectionInstance;
    }
    
    public final <T> void parseArray(final Collection<? super T> collection, final Class<T> clazz) throws IOException {
        this.<T>parseArray(collection, clazz, null);
    }
    
    @Beta
    public final <T> void parseArray(final Collection<? super T> collection, final Class<T> clazz, final CustomizeJsonParser customizeJsonParser) throws IOException {
        this.<Object>parseArray(null, collection, clazz, new ArrayList<Type>(), customizeJsonParser);
    }
    
    private <T> void parseArray(final Field field, final Collection<T> collection, final Type type, final ArrayList<Type> list, final CustomizeJsonParser customizeJsonParser) throws IOException {
        for (JsonToken jsonToken = this.startParsingObjectOrArray(); jsonToken != JsonToken.END_ARRAY; jsonToken = this.nextToken()) {
            collection.add((T)this.parseValue(field, type, list, collection, customizeJsonParser, true));
        }
    }
    
    private void parseMap(final Field field, final Map<String, Object> map, final Type type, final ArrayList<Type> list, final CustomizeJsonParser customizeJsonParser) throws IOException {
        for (JsonToken jsonToken = this.startParsingObjectOrArray(); jsonToken == JsonToken.FIELD_NAME; jsonToken = this.nextToken()) {
            final String text = this.getText();
            this.nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(map, text)) {
                return;
            }
            map.put(text, this.parseValue(field, type, list, map, customizeJsonParser, true));
        }
    }
    
    private final Object parseValue(final Field field, Type resolveWildcardTypeOrTypeVariable, final ArrayList<Type> list, final Object o, final CustomizeJsonParser customizeJsonParser, final boolean b) throws IOException {
        resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(list, resolveWildcardTypeOrTypeVariable);
        Class<?> rawClass = (Class<?>)((resolveWildcardTypeOrTypeVariable instanceof Class) ? ((Class)resolveWildcardTypeOrTypeVariable) : null);
        if (resolveWildcardTypeOrTypeVariable instanceof ParameterizedType) {
            rawClass = Types.getRawClass((ParameterizedType)resolveWildcardTypeOrTypeVariable);
        }
        if (rawClass == Void.class) {
            this.skipChildren();
            return null;
        }
        final JsonToken currentToken = this.getCurrentToken();
        try {
            switch (this.getCurrentToken()) {
                case START_ARRAY:
                case END_ARRAY: {
                    final boolean array = Types.isArray(resolveWildcardTypeOrTypeVariable);
                    Preconditions.checkArgument(resolveWildcardTypeOrTypeVariable == null || (rawClass != null && Types.isAssignableToOrFrom(rawClass, Collection.class)), "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                    Collection<Object> collection = null;
                    if (customizeJsonParser != null && field != null) {
                        collection = customizeJsonParser.newInstanceForArray(o, field);
                    }
                    if (collection == null) {
                        collection = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                    }
                    Type type = null;
                    if (array) {
                        type = Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable);
                    }
                    else if (rawClass != null && Iterable.class.isAssignableFrom(rawClass)) {
                        type = Types.getIterableParameter(resolveWildcardTypeOrTypeVariable);
                    }
                    final Type resolveWildcardTypeOrTypeVariable2 = Data.resolveWildcardTypeOrTypeVariable(list, type);
                    this.<Object>parseArray(field, collection, resolveWildcardTypeOrTypeVariable2, list, customizeJsonParser);
                    if (array) {
                        return Types.toArray(collection, Types.getRawArrayComponentType(list, resolveWildcardTypeOrTypeVariable2));
                    }
                    return collection;
                }
                case START_OBJECT:
                case FIELD_NAME:
                case END_OBJECT: {
                    Preconditions.checkArgument(!Types.isArray(resolveWildcardTypeOrTypeVariable), "expected object or map type but got %s", resolveWildcardTypeOrTypeVariable);
                    final Field field2 = b ? getCachedTypemapFieldFor(rawClass) : null;
                    Object o2 = null;
                    if (rawClass != null && customizeJsonParser != null) {
                        o2 = customizeJsonParser.newInstanceForObject(o, rawClass);
                    }
                    final boolean b2 = rawClass != null && Types.isAssignableToOrFrom(rawClass, Map.class);
                    if (field2 != null) {
                        o2 = new GenericJson();
                    }
                    else if (o2 == null) {
                        if (rawClass == null) {
                            o2 = Data.newMapInstance(rawClass);
                        }
                        else {
                            o2 = Types.<GenericJson>newInstance(rawClass);
                        }
                    }
                    final int size = list.size();
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        list.add(resolveWildcardTypeOrTypeVariable);
                    }
                    if (b2 && !GenericData.class.isAssignableFrom(rawClass)) {
                        final Type type2 = Map.class.isAssignableFrom(rawClass) ? Types.getMapValueParameter(resolveWildcardTypeOrTypeVariable) : null;
                        if (type2 != null) {
                            this.parseMap(field, (Map<String, Object>)o2, type2, list, customizeJsonParser);
                            return o2;
                        }
                    }
                    this.parse(list, o2, customizeJsonParser);
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        list.remove(size);
                    }
                    if (field2 == null) {
                        return o2;
                    }
                    final Object value = ((GenericJson)o2).get(field2.getName());
                    Preconditions.checkArgument(value != null, (Object)"No value specified for @JsonPolymorphicTypeMap field");
                    final String string = value.toString();
                    final JsonPolymorphicTypeMap jsonPolymorphicTypeMap = field2.<JsonPolymorphicTypeMap>getAnnotation(JsonPolymorphicTypeMap.class);
                    Type ref = null;
                    for (final JsonPolymorphicTypeMap.TypeDef typeDef : jsonPolymorphicTypeMap.typeDefinitions()) {
                        if (typeDef.key().equals(string)) {
                            ref = typeDef.ref();
                            break;
                        }
                    }
                    Preconditions.checkArgument(ref != null, (Object)("No TypeDef annotation found with key: " + string));
                    final JsonFactory factory = this.getFactory();
                    final JsonParser jsonParser = factory.createJsonParser(factory.toString(o2));
                    jsonParser.startParsing();
                    return jsonParser.parseValue(field, ref, list, null, null, false);
                }
                case VALUE_TRUE:
                case VALUE_FALSE:
                    Preconditions.checkArgument(resolveWildcardTypeOrTypeVariable == null || rawClass == Boolean.TYPE || (rawClass != null && rawClass.isAssignableFrom(Boolean.class)), "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                    return (currentToken == JsonToken.VALUE_TRUE) ? Boolean.TRUE : Boolean.FALSE;
                case VALUE_NUMBER_FLOAT:
                case VALUE_NUMBER_INT:
                    Preconditions.checkArgument(field == null || field.<JsonString>getAnnotation(JsonString.class) == null, (Object)"number type formatted as a JSON number cannot use @JsonString annotation");
                    if (rawClass == null || rawClass.isAssignableFrom(BigDecimal.class)) {
                        return this.getDecimalValue();
                    }
                    if (rawClass == BigInteger.class) {
                        return this.getBigIntegerValue();
                    }
                    if (rawClass == Double.class || rawClass == Double.TYPE) {
                        return this.getDoubleValue();
                    }
                    if (rawClass == Long.class || rawClass == Long.TYPE) {
                        return this.getLongValue();
                    }
                    if (rawClass == Float.class || rawClass == Float.TYPE) {
                        return this.getFloatValue();
                    }
                    if (rawClass == Integer.class || rawClass == Integer.TYPE) {
                        return this.getIntValue();
                    }
                    if (rawClass == Short.class || rawClass == Short.TYPE) {
                        return this.getShortValue();
                    }
                    if (rawClass == Byte.class || rawClass == Byte.TYPE) {
                        return this.getByteValue();
                    }
                    throw new IllegalArgumentException("expected numeric type but got " + resolveWildcardTypeOrTypeVariable);
                case VALUE_STRING: {
                    final String lowerCase = this.getText().trim().toLowerCase(Locale.US);
                    if ((rawClass != Float.TYPE && rawClass != Float.class && rawClass != Double.TYPE && rawClass != Double.class) || (!lowerCase.equals("nan") && !lowerCase.equals("infinity") && !lowerCase.equals("-infinity"))) {
                        Preconditions.checkArgument(rawClass == null || !Number.class.isAssignableFrom(rawClass) || (field != null && field.<JsonString>getAnnotation(JsonString.class) != null), (Object)"number field formatted as a JSON string must use the @JsonString annotation");
                    }
                    return Data.parsePrimitiveValue(resolveWildcardTypeOrTypeVariable, this.getText());
                }
                case VALUE_NULL:
                    Preconditions.checkArgument(rawClass == null || !rawClass.isPrimitive(), (Object)"primitive number field but found a JSON null");
                    if (rawClass != null && 0x0 != (rawClass.getModifiers() & 0x600)) {
                        if (Types.isAssignableToOrFrom(rawClass, Collection.class)) {
                            return Data.<Object>nullOf(Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable).getClass());
                        }
                        if (Types.isAssignableToOrFrom(rawClass, Map.class)) {
                            return Data.<Object>nullOf(Data.newMapInstance(rawClass).getClass());
                        }
                    }
                    return Data.<Object>nullOf(Types.getRawArrayComponentType(list, resolveWildcardTypeOrTypeVariable));
                default:
                    throw new IllegalArgumentException("unexpected JSON node type: " + currentToken);
            }
        }
        catch (IllegalArgumentException ex) {
            final StringBuilder sb = new StringBuilder();
            final String currentName = this.getCurrentName();
            if (currentName != null) {
                sb.append("key ").append(currentName);
            }
            if (field != null) {
                if (currentName != null) {
                    sb.append(", ");
                }
                sb.append("field ").append(field);
            }
            throw new IllegalArgumentException(sb.toString(), ex);
        }
    }
    
    private static Field getCachedTypemapFieldFor(final Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        JsonParser.lock.lock();
        try {
            if (JsonParser.cachedTypemapFields.containsKey(clazz)) {
                return JsonParser.cachedTypemapFields.get(clazz);
            }
            Field field = null;
            final Iterator<FieldInfo> iterator = ClassInfo.of(clazz).getFieldInfos().iterator();
            while (iterator.hasNext()) {
                final Field field2 = iterator.next().getField();
                final JsonPolymorphicTypeMap jsonPolymorphicTypeMap = field2.<JsonPolymorphicTypeMap>getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    Preconditions.checkArgument(field == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", clazz);
                    Preconditions.checkArgument(Data.isPrimitive(field2.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", clazz, field2.getType());
                    field = field2;
                    final JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    final HashSet<String> hashSet = Sets.<String>newHashSet();
                    Preconditions.checkArgument(typeDefinitions.length > 0, (Object)"@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (final JsonPolymorphicTypeMap.TypeDef typeDef : typeDefinitions) {
                        Preconditions.checkArgument(hashSet.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                }
            }
            JsonParser.cachedTypemapFields.put(clazz, field);
            return field;
        }
        finally {
            JsonParser.lock.unlock();
        }
    }
    
    static {
        JsonParser.cachedTypemapFields = new WeakHashMap<Class<?>, Field>();
        lock = new ReentrantLock();
    }
}
