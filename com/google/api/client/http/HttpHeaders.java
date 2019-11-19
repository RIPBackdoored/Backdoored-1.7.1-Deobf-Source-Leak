package com.google.api.client.http;

import java.util.Arrays;
import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.ClassInfo;
import java.lang.reflect.Type;
import com.google.api.client.util.Throwables;
import java.util.Locale;
import java.util.Iterator;
import com.google.api.client.util.Types;
import java.util.Map;
import java.util.HashSet;
import com.google.api.client.util.FieldInfo;
import java.io.IOException;
import java.util.logging.Level;
import com.google.api.client.util.Data;
import java.io.Writer;
import java.util.logging.Logger;
import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.util.GenericData;

public class HttpHeaders extends GenericData
{
    @Key("Accept")
    private List<String> accept;
    @Key("Accept-Encoding")
    private List<String> acceptEncoding;
    @Key("Authorization")
    private List<String> authorization;
    @Key("Cache-Control")
    private List<String> cacheControl;
    @Key("Content-Encoding")
    private List<String> contentEncoding;
    @Key("Content-Length")
    private List<Long> contentLength;
    @Key("Content-MD5")
    private List<String> contentMD5;
    @Key("Content-Range")
    private List<String> contentRange;
    @Key("Content-Type")
    private List<String> contentType;
    @Key("Cookie")
    private List<String> cookie;
    @Key("Date")
    private List<String> date;
    @Key("ETag")
    private List<String> etag;
    @Key("Expires")
    private List<String> expires;
    @Key("If-Modified-Since")
    private List<String> ifModifiedSince;
    @Key("If-Match")
    private List<String> ifMatch;
    @Key("If-None-Match")
    private List<String> ifNoneMatch;
    @Key("If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;
    @Key("If-Range")
    private List<String> ifRange;
    @Key("Last-Modified")
    private List<String> lastModified;
    @Key("Location")
    private List<String> location;
    @Key("MIME-Version")
    private List<String> mimeVersion;
    @Key("Range")
    private List<String> range;
    @Key("Retry-After")
    private List<String> retryAfter;
    @Key("User-Agent")
    private List<String> userAgent;
    @Key("WWW-Authenticate")
    private List<String> authenticate;
    @Key("Age")
    private List<Long> age;
    
    public HttpHeaders() {
        super(EnumSet.<Flags>of(Flags.IGNORE_CASE));
        this.acceptEncoding = new ArrayList<String>(Collections.<String>singleton("gzip"));
    }
    
    @Override
    public HttpHeaders clone() {
        return (HttpHeaders)super.clone();
    }
    
    @Override
    public HttpHeaders set(final String s, final Object o) {
        return (HttpHeaders)super.set(s, o);
    }
    
    public final String getAccept() {
        return this.<String>getFirstHeaderValue(this.accept);
    }
    
    public HttpHeaders setAccept(final String s) {
        this.accept = this.<String>getAsList(s);
        return this;
    }
    
    public final String getAcceptEncoding() {
        return this.<String>getFirstHeaderValue(this.acceptEncoding);
    }
    
    public HttpHeaders setAcceptEncoding(final String s) {
        this.acceptEncoding = this.<String>getAsList(s);
        return this;
    }
    
    public final String getAuthorization() {
        return this.<String>getFirstHeaderValue(this.authorization);
    }
    
    public final List<String> getAuthorizationAsList() {
        return this.authorization;
    }
    
    public HttpHeaders setAuthorization(final String s) {
        return this.setAuthorization(this.<String>getAsList(s));
    }
    
    public HttpHeaders setAuthorization(final List<String> authorization) {
        this.authorization = authorization;
        return this;
    }
    
    public final String getCacheControl() {
        return this.<String>getFirstHeaderValue(this.cacheControl);
    }
    
    public HttpHeaders setCacheControl(final String s) {
        this.cacheControl = this.<String>getAsList(s);
        return this;
    }
    
    public final String getContentEncoding() {
        return this.<String>getFirstHeaderValue(this.contentEncoding);
    }
    
    public HttpHeaders setContentEncoding(final String s) {
        this.contentEncoding = this.<String>getAsList(s);
        return this;
    }
    
    public final Long getContentLength() {
        return this.<Long>getFirstHeaderValue(this.contentLength);
    }
    
    public HttpHeaders setContentLength(final Long n) {
        this.contentLength = this.<Long>getAsList(n);
        return this;
    }
    
    public final String getContentMD5() {
        return this.<String>getFirstHeaderValue(this.contentMD5);
    }
    
    public HttpHeaders setContentMD5(final String s) {
        this.contentMD5 = this.<String>getAsList(s);
        return this;
    }
    
    public final String getContentRange() {
        return this.<String>getFirstHeaderValue(this.contentRange);
    }
    
    public HttpHeaders setContentRange(final String s) {
        this.contentRange = this.<String>getAsList(s);
        return this;
    }
    
    public final String getContentType() {
        return this.<String>getFirstHeaderValue(this.contentType);
    }
    
    public HttpHeaders setContentType(final String s) {
        this.contentType = this.<String>getAsList(s);
        return this;
    }
    
    public final String getCookie() {
        return this.<String>getFirstHeaderValue(this.cookie);
    }
    
    public HttpHeaders setCookie(final String s) {
        this.cookie = this.<String>getAsList(s);
        return this;
    }
    
    public final String getDate() {
        return this.<String>getFirstHeaderValue(this.date);
    }
    
    public HttpHeaders setDate(final String s) {
        this.date = this.<String>getAsList(s);
        return this;
    }
    
    public final String getETag() {
        return this.<String>getFirstHeaderValue(this.etag);
    }
    
    public HttpHeaders setETag(final String s) {
        this.etag = this.<String>getAsList(s);
        return this;
    }
    
    public final String getExpires() {
        return this.<String>getFirstHeaderValue(this.expires);
    }
    
    public HttpHeaders setExpires(final String s) {
        this.expires = this.<String>getAsList(s);
        return this;
    }
    
    public final String getIfModifiedSince() {
        return this.<String>getFirstHeaderValue(this.ifModifiedSince);
    }
    
    public HttpHeaders setIfModifiedSince(final String s) {
        this.ifModifiedSince = this.<String>getAsList(s);
        return this;
    }
    
    public final String getIfMatch() {
        return this.<String>getFirstHeaderValue(this.ifMatch);
    }
    
    public HttpHeaders setIfMatch(final String s) {
        this.ifMatch = this.<String>getAsList(s);
        return this;
    }
    
    public final String getIfNoneMatch() {
        return this.<String>getFirstHeaderValue(this.ifNoneMatch);
    }
    
    public HttpHeaders setIfNoneMatch(final String s) {
        this.ifNoneMatch = this.<String>getAsList(s);
        return this;
    }
    
    public final String getIfUnmodifiedSince() {
        return this.<String>getFirstHeaderValue(this.ifUnmodifiedSince);
    }
    
    public HttpHeaders setIfUnmodifiedSince(final String s) {
        this.ifUnmodifiedSince = this.<String>getAsList(s);
        return this;
    }
    
    public final String getIfRange() {
        return this.<String>getFirstHeaderValue(this.ifRange);
    }
    
    public HttpHeaders setIfRange(final String s) {
        this.ifRange = this.<String>getAsList(s);
        return this;
    }
    
    public final String getLastModified() {
        return this.<String>getFirstHeaderValue(this.lastModified);
    }
    
    public HttpHeaders setLastModified(final String s) {
        this.lastModified = this.<String>getAsList(s);
        return this;
    }
    
    public final String getLocation() {
        return this.<String>getFirstHeaderValue(this.location);
    }
    
    public HttpHeaders setLocation(final String s) {
        this.location = this.<String>getAsList(s);
        return this;
    }
    
    public final String getMimeVersion() {
        return this.<String>getFirstHeaderValue(this.mimeVersion);
    }
    
    public HttpHeaders setMimeVersion(final String s) {
        this.mimeVersion = this.<String>getAsList(s);
        return this;
    }
    
    public final String getRange() {
        return this.<String>getFirstHeaderValue(this.range);
    }
    
    public HttpHeaders setRange(final String s) {
        this.range = this.<String>getAsList(s);
        return this;
    }
    
    public final String getRetryAfter() {
        return this.<String>getFirstHeaderValue(this.retryAfter);
    }
    
    public HttpHeaders setRetryAfter(final String s) {
        this.retryAfter = this.<String>getAsList(s);
        return this;
    }
    
    public final String getUserAgent() {
        return this.<String>getFirstHeaderValue(this.userAgent);
    }
    
    public HttpHeaders setUserAgent(final String s) {
        this.userAgent = this.<String>getAsList(s);
        return this;
    }
    
    public final String getAuthenticate() {
        return this.<String>getFirstHeaderValue(this.authenticate);
    }
    
    public final List<String> getAuthenticateAsList() {
        return this.authenticate;
    }
    
    public HttpHeaders setAuthenticate(final String s) {
        this.authenticate = this.<String>getAsList(s);
        return this;
    }
    
    public final Long getAge() {
        return this.<Long>getFirstHeaderValue(this.age);
    }
    
    public HttpHeaders setAge(final Long n) {
        this.age = this.<Long>getAsList(n);
        return this;
    }
    
    public HttpHeaders setBasicAuthentication(final String s, final String s2) {
        return this.setAuthorization("Basic " + Base64.encodeBase64String(StringUtils.getBytesUtf8(Preconditions.<String>checkNotNull(s) + ":" + Preconditions.<String>checkNotNull(s2))));
    }
    
    private static void addHeader(final Logger logger, final StringBuilder sb, final StringBuilder sb2, final LowLevelHttpRequest lowLevelHttpRequest, final String s, final Object o, final Writer writer) throws IOException {
        if (o == null || Data.isNull(o)) {
            return;
        }
        String stringValue;
        final String s2 = stringValue = toStringValue(o);
        if (("Authorization".equalsIgnoreCase(s) || "Cookie".equalsIgnoreCase(s)) && (logger == null || !logger.isLoggable(Level.ALL))) {
            stringValue = "<Not Logged>";
        }
        if (sb != null) {
            sb.append(s).append(": ");
            sb.append(stringValue);
            sb.append(StringUtils.LINE_SEPARATOR);
        }
        if (sb2 != null) {
            sb2.append(" -H '").append(s).append(": ").append(stringValue).append("'");
        }
        if (lowLevelHttpRequest != null) {
            lowLevelHttpRequest.addHeader(s, s2);
        }
        if (writer != null) {
            writer.write(s);
            writer.write(": ");
            writer.write(s2);
            writer.write("\r\n");
        }
    }
    
    private static String toStringValue(final Object o) {
        return (o instanceof Enum) ? FieldInfo.of((Enum<?>)o).getName() : o.toString();
    }
    
    static void serializeHeaders(final HttpHeaders httpHeaders, final StringBuilder sb, final StringBuilder sb2, final Logger logger, final LowLevelHttpRequest lowLevelHttpRequest) throws IOException {
        serializeHeaders(httpHeaders, sb, sb2, logger, lowLevelHttpRequest, null);
    }
    
    public static void serializeHeadersForMultipartRequests(final HttpHeaders httpHeaders, final StringBuilder sb, final Logger logger, final Writer writer) throws IOException {
        serializeHeaders(httpHeaders, sb, null, logger, null, writer);
    }
    
    static void serializeHeaders(final HttpHeaders httpHeaders, final StringBuilder sb, final StringBuilder sb2, final Logger logger, final LowLevelHttpRequest lowLevelHttpRequest, final Writer writer) throws IOException {
        final HashSet<String> set = new HashSet<String>();
        for (final Map.Entry<String, Object> entry : httpHeaders.entrySet()) {
            final String s = entry.getKey();
            Preconditions.checkArgument(set.add(s), "multiple headers of the same name (headers are case insensitive): %s", s);
            final Object value = entry.getValue();
            if (value != null) {
                String name = s;
                final FieldInfo fieldInfo = httpHeaders.getClassInfo().getFieldInfo(s);
                if (fieldInfo != null) {
                    name = fieldInfo.getName();
                }
                final Class<?> class1 = value.getClass();
                if (value instanceof Iterable || class1.isArray()) {
                    final Iterator<Object> iterator2 = Types.<Object>iterableOf(value).iterator();
                    while (iterator2.hasNext()) {
                        addHeader(logger, sb, sb2, lowLevelHttpRequest, name, iterator2.next(), writer);
                    }
                }
                else {
                    addHeader(logger, sb, sb2, lowLevelHttpRequest, name, value, writer);
                }
            }
        }
        if (writer != null) {
            writer.flush();
        }
    }
    
    public final void fromHttpResponse(final LowLevelHttpResponse lowLevelHttpResponse, final StringBuilder sb) throws IOException {
        this.clear();
        final ParseHeaderState parseHeaderState = new ParseHeaderState(this, sb);
        for (int headerCount = lowLevelHttpResponse.getHeaderCount(), i = 0; i < headerCount; ++i) {
            this.parseHeader(lowLevelHttpResponse.getHeaderName(i), lowLevelHttpResponse.getHeaderValue(i), parseHeaderState);
        }
        parseHeaderState.finish();
    }
    
    private <T> T getFirstHeaderValue(final List<T> list) {
        return (list == null) ? null : list.get(0);
    }
    
    private <T> List<T> getAsList(final T t) {
        if (t == null) {
            return null;
        }
        final ArrayList<T> list = new ArrayList<T>();
        list.add(t);
        return list;
    }
    
    public String getFirstHeaderStringValue(final String s) {
        final Object value = this.get(s.toLowerCase(Locale.US));
        if (value == null) {
            return null;
        }
        final Class<?> class1 = value.getClass();
        if (value instanceof Iterable || class1.isArray()) {
            final Iterator<Object> iterator = Types.<Object>iterableOf(value).iterator();
            if (iterator.hasNext()) {
                return toStringValue(iterator.next());
            }
        }
        return toStringValue(value);
    }
    
    public List<String> getHeaderStringValues(final String s) {
        final Object value = this.get(s.toLowerCase(Locale.US));
        if (value == null) {
            return Collections.<String>emptyList();
        }
        final Class<?> class1 = value.getClass();
        if (value instanceof Iterable || class1.isArray()) {
            final ArrayList<String> list = new ArrayList<String>();
            final Iterator<Object> iterator = Types.<Object>iterableOf(value).iterator();
            while (iterator.hasNext()) {
                list.add(toStringValue(iterator.next()));
            }
            return (List<String>)Collections.<Object>unmodifiableList((List<?>)list);
        }
        return Collections.<String>singletonList(toStringValue(value));
    }
    
    public final void fromHttpHeaders(final HttpHeaders httpHeaders) {
        try {
            final ParseHeaderState parseHeaderState = new ParseHeaderState(this, null);
            serializeHeaders(httpHeaders, null, null, null, new HeaderParsingFakeLevelHttpRequest(this, parseHeaderState));
            parseHeaderState.finish();
        }
        catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
    }
    
    void parseHeader(final String s, final String s2, final ParseHeaderState parseHeaderState) {
        final List<Type> context = parseHeaderState.context;
        final ClassInfo classInfo = parseHeaderState.classInfo;
        final ArrayValueMap arrayValueMap = parseHeaderState.arrayValueMap;
        final StringBuilder logger = parseHeaderState.logger;
        if (logger != null) {
            logger.append(s + ": " + s2).append(StringUtils.LINE_SEPARATOR);
        }
        final FieldInfo fieldInfo = classInfo.getFieldInfo(s);
        if (fieldInfo != null) {
            final Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(context, fieldInfo.getGenericType());
            if (Types.isArray(resolveWildcardTypeOrTypeVariable)) {
                final Class<?> rawArrayComponentType = Types.getRawArrayComponentType(context, Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable));
                arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, context, s2));
            }
            else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(context, resolveWildcardTypeOrTypeVariable), Iterable.class)) {
                Collection<Object> collectionInstance = (Collection<Object>)fieldInfo.getValue(this);
                if (collectionInstance == null) {
                    collectionInstance = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                    fieldInfo.setValue(this, collectionInstance);
                }
                collectionInstance.add(parseValue((resolveWildcardTypeOrTypeVariable == Object.class) ? null : Types.getIterableParameter(resolveWildcardTypeOrTypeVariable), context, s2));
            }
            else {
                fieldInfo.setValue(this, parseValue(resolveWildcardTypeOrTypeVariable, context, s2));
            }
        }
        else {
            ArrayList<String> list = (ArrayList<String>)this.get(s);
            if (list == null) {
                list = new ArrayList<String>();
                this.set(s, list);
            }
            list.add(s2);
        }
    }
    
    private static Object parseValue(final Type type, final List<Type> list, final String s) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, type), s);
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
