package com.google.api.client.http;

import com.google.api.client.util.Charsets;
import java.io.BufferedReader;
import com.google.api.client.util.Preconditions;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.io.InputStream;
import com.google.api.client.util.FieldInfo;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import com.google.api.client.util.Types;
import com.google.api.client.util.Data;
import com.google.api.client.util.escape.CharEscapers;
import java.io.StringWriter;
import com.google.api.client.util.ArrayValueMap;
import java.util.Map;
import com.google.api.client.util.GenericData;
import java.util.Arrays;
import java.lang.reflect.Type;
import com.google.api.client.util.ClassInfo;
import java.io.IOException;
import com.google.api.client.util.Throwables;
import java.io.Reader;
import java.io.StringReader;
import com.google.api.client.util.ObjectParser;

public class UrlEncodedParser implements ObjectParser
{
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String MEDIA_TYPE;
    
    public UrlEncodedParser() {
        super();
    }
    
    public static void parse(final String s, final Object o) {
        if (s == null) {
            return;
        }
        try {
            parse(new StringReader(s), o);
        }
        catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }
    
    public static void parse(final Reader reader, final Object o) throws IOException {
        final Class<?> class1 = o.getClass();
        final ClassInfo of = ClassInfo.of(class1);
        final List<Type> list = Arrays.<Type>asList(class1);
        final GenericData genericData = GenericData.class.isAssignableFrom(class1) ? ((GenericData)o) : null;
        final Map map = Map.class.isAssignableFrom(class1) ? ((Map)o) : null;
        final ArrayValueMap arrayValueMap = new ArrayValueMap(o);
        StringWriter stringWriter = new StringWriter();
        StringWriter stringWriter2 = new StringWriter();
        int n = 1;
    Block_12:
        while (true) {
            final int read = reader.read();
            switch (read) {
                case -1:
                case 38: {
                    final String decodeUri = CharEscapers.decodeUri(stringWriter.toString());
                    if (decodeUri.length() != 0) {
                        final String decodeUri2 = CharEscapers.decodeUri(stringWriter2.toString());
                        final FieldInfo fieldInfo = of.getFieldInfo(decodeUri);
                        if (fieldInfo != null) {
                            final Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(list, fieldInfo.getGenericType());
                            if (Types.isArray(resolveWildcardTypeOrTypeVariable)) {
                                final Class<?> rawArrayComponentType = Types.getRawArrayComponentType(list, Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable));
                                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, list, decodeUri2));
                            }
                            else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(list, resolveWildcardTypeOrTypeVariable), Iterable.class)) {
                                Collection<Object> collectionInstance = (Collection<Object>)fieldInfo.getValue(o);
                                if (collectionInstance == null) {
                                    collectionInstance = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                                    fieldInfo.setValue(o, collectionInstance);
                                }
                                collectionInstance.add(parseValue((resolveWildcardTypeOrTypeVariable == Object.class) ? null : Types.getIterableParameter(resolveWildcardTypeOrTypeVariable), list, decodeUri2));
                            }
                            else {
                                fieldInfo.setValue(o, parseValue(resolveWildcardTypeOrTypeVariable, list, decodeUri2));
                            }
                        }
                        else if (map != null) {
                            ArrayList<?> list2 = map.get(decodeUri);
                            if (list2 == null) {
                                list2 = new ArrayList<Object>();
                                if (genericData != null) {
                                    genericData.set(decodeUri, list2);
                                }
                                else {
                                    map.put(decodeUri, list2);
                                }
                            }
                            list2.add(decodeUri2);
                        }
                    }
                    n = 1;
                    stringWriter = new StringWriter();
                    stringWriter2 = new StringWriter();
                    if (read == -1) {
                        break Block_12;
                    }
                    continue;
                }
                case 61:
                    if (n != 0) {
                        n = 0;
                        continue;
                    }
                    stringWriter2.write(read);
                    continue;
                default:
                    if (n != 0) {
                        stringWriter.write(read);
                        continue;
                    }
                    stringWriter2.write(read);
                    continue;
            }
        }
        arrayValueMap.setValues();
    }
    
    private static Object parseValue(final Type type, final List<Type> list, final String s) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, type), s);
    }
    
    @Override
    public <T> T parseAndClose(final InputStream inputStream, final Charset charset, final Class<T> clazz) throws IOException {
        return this.<T>parseAndClose(new InputStreamReader(inputStream, charset), clazz);
    }
    
    @Override
    public Object parseAndClose(final InputStream inputStream, final Charset charset, final Type type) throws IOException {
        return this.parseAndClose(new InputStreamReader(inputStream, charset), type);
    }
    
    @Override
    public <T> T parseAndClose(final Reader reader, final Class<T> clazz) throws IOException {
        return (T)this.parseAndClose(reader, (Type)clazz);
    }
    
    @Override
    public Object parseAndClose(final Reader reader, final Type type) throws IOException {
        Preconditions.checkArgument(type instanceof Class, (Object)"dataType has to be of type Class<?>");
        final Object instance = Types.<Object>newInstance((Class<Object>)type);
        parse(new BufferedReader(reader), instance);
        return instance;
    }
    
    static {
        MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded").setCharsetParameter(Charsets.UTF_8).build();
    }
}
