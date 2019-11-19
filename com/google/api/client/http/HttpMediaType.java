package com.google.api.client.http;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
import java.util.Locale;
import java.util.regex.Matcher;
import com.google.api.client.util.Preconditions;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.regex.Pattern;

public final class HttpMediaType
{
    private static final Pattern TYPE_REGEX;
    private static final Pattern TOKEN_REGEX;
    private static final Pattern FULL_MEDIA_TYPE_REGEX;
    private static final Pattern PARAMETER_REGEX;
    private String type;
    private String subType;
    private final SortedMap<String, String> parameters;
    private String cachedBuildResult;
    
    public HttpMediaType(final String type, final String subType) {
        super();
        this.type = "application";
        this.subType = "octet-stream";
        this.parameters = new TreeMap<String, String>();
        this.setType(type);
        this.setSubType(subType);
    }
    
    public HttpMediaType(final String s) {
        super();
        this.type = "application";
        this.subType = "octet-stream";
        this.parameters = new TreeMap<String, String>();
        this.fromString(s);
    }
    
    public HttpMediaType setType(final String type) {
        Preconditions.checkArgument(HttpMediaType.TYPE_REGEX.matcher(type).matches(), (Object)"Type contains reserved characters");
        this.type = type;
        this.cachedBuildResult = null;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public HttpMediaType setSubType(final String subType) {
        Preconditions.checkArgument(HttpMediaType.TYPE_REGEX.matcher(subType).matches(), (Object)"Subtype contains reserved characters");
        this.subType = subType;
        this.cachedBuildResult = null;
        return this;
    }
    
    public String getSubType() {
        return this.subType;
    }
    
    private HttpMediaType fromString(final String s) {
        final Matcher matcher = HttpMediaType.FULL_MEDIA_TYPE_REGEX.matcher(s);
        Preconditions.checkArgument(matcher.matches(), (Object)"Type must be in the 'maintype/subtype; parameter=value' format");
        this.setType(matcher.group(1));
        this.setSubType(matcher.group(2));
        final String group = matcher.group(3);
        if (group != null) {
            final Matcher matcher2 = HttpMediaType.PARAMETER_REGEX.matcher(group);
            while (matcher2.find()) {
                final String group2 = matcher2.group(1);
                String s2 = matcher2.group(3);
                if (s2 == null) {
                    s2 = matcher2.group(2);
                }
                this.setParameter(group2, s2);
            }
        }
        return this;
    }
    
    public HttpMediaType setParameter(final String s, final String s2) {
        if (s2 == null) {
            this.removeParameter(s);
            return this;
        }
        Preconditions.checkArgument(HttpMediaType.TOKEN_REGEX.matcher(s).matches(), (Object)"Name contains reserved characters");
        this.cachedBuildResult = null;
        this.parameters.put(s.toLowerCase(Locale.US), s2);
        return this;
    }
    
    public String getParameter(final String s) {
        return this.parameters.get(s.toLowerCase(Locale.US));
    }
    
    public HttpMediaType removeParameter(final String s) {
        this.cachedBuildResult = null;
        this.parameters.remove(s.toLowerCase(Locale.US));
        return this;
    }
    
    public void clearParameters() {
        this.cachedBuildResult = null;
        this.parameters.clear();
    }
    
    public Map<String, String> getParameters() {
        return Collections.<String, String>unmodifiableMap((Map<? extends String, ? extends String>)this.parameters);
    }
    
    static boolean matchesToken(final String s) {
        return HttpMediaType.TOKEN_REGEX.matcher(s).matches();
    }
    
    private static String quoteString(final String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
    
    public String build() {
        if (this.cachedBuildResult != null) {
            return this.cachedBuildResult;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append('/');
        sb.append(this.subType);
        if (this.parameters != null) {
            for (final Map.Entry<String, String> entry : this.parameters.entrySet()) {
                final String s = entry.getValue();
                sb.append("; ");
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(matchesToken(s) ? s : quoteString(s));
            }
        }
        return this.cachedBuildResult = sb.toString();
    }
    
    @Override
    public String toString() {
        return this.build();
    }
    
    public boolean equalsIgnoreParameters(final HttpMediaType httpMediaType) {
        return httpMediaType != null && this.getType().equalsIgnoreCase(httpMediaType.getType()) && this.getSubType().equalsIgnoreCase(httpMediaType.getSubType());
    }
    
    public static boolean equalsIgnoreParameters(final String s, final String s2) {
        return (s == null && s2 == null) || (s != null && s2 != null && new HttpMediaType(s).equalsIgnoreParameters(new HttpMediaType(s2)));
    }
    
    public HttpMediaType setCharsetParameter(final Charset charset) {
        this.setParameter("charset", (charset == null) ? null : charset.name());
        return this;
    }
    
    public Charset getCharsetParameter() {
        final String parameter = this.getParameter("charset");
        return (parameter == null) ? null : Charset.forName(parameter);
    }
    
    @Override
    public int hashCode() {
        return this.build().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof HttpMediaType)) {
            return false;
        }
        final HttpMediaType httpMediaType = (HttpMediaType)o;
        return this.equalsIgnoreParameters(httpMediaType) && this.parameters.equals(httpMediaType.parameters);
    }
    
    static {
        TYPE_REGEX = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
        TOKEN_REGEX = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
        final String s = "[^\\s/=;\"]+";
        FULL_MEDIA_TYPE_REGEX = Pattern.compile("\\s*(" + s + ")/(" + s + ")\\s*(" + ";.*" + ")?", 32);
        PARAMETER_REGEX = Pattern.compile("\\s*;\\s*(" + s + ")=(" + ("\"([^\"]*)\"" + "|" + "[^\\s;\"]*") + ")");
    }
}
