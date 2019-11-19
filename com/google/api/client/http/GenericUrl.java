package com.google.api.client.http;

import com.google.api.client.util.escape.PercentEscaper;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Iterator;
import java.net.MalformedURLException;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.ArrayList;
import com.google.api.client.util.escape.CharEscapers;
import java.util.Locale;
import java.net.URL;
import java.net.URI;
import java.util.List;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.GenericData;

public class GenericUrl extends GenericData
{
    private static final Escaper URI_FRAGMENT_ESCAPER;
    private String scheme;
    private String host;
    private String userInfo;
    private int port;
    private List<String> pathParts;
    private String fragment;
    
    public GenericUrl() {
        super();
        this.port = -1;
    }
    
    public GenericUrl(final String s) {
        this(parseURL(s));
    }
    
    public GenericUrl(final URI uri) {
        this(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getRawPath(), uri.getRawFragment(), uri.getRawQuery(), uri.getRawUserInfo());
    }
    
    public GenericUrl(final URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }
    
    private GenericUrl(final String s, final String host, final int port, final String s2, final String s3, final String s4, final String s5) {
        super();
        this.port = -1;
        this.scheme = s.toLowerCase(Locale.US);
        this.host = host;
        this.port = port;
        this.pathParts = toPathParts(s2);
        this.fragment = ((s3 != null) ? CharEscapers.decodeUri(s3) : null);
        if (s4 != null) {
            UrlEncodedParser.parse(s4, this);
        }
        this.userInfo = ((s5 != null) ? CharEscapers.decodeUri(s5) : null);
    }
    
    @Override
    public int hashCode() {
        return this.build().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (super.equals(o) && o instanceof GenericUrl && this.build().equals(((GenericUrl)o).build()));
    }
    
    @Override
    public String toString() {
        return this.build();
    }
    
    @Override
    public GenericUrl clone() {
        final GenericUrl genericUrl = (GenericUrl)super.clone();
        if (this.pathParts != null) {
            genericUrl.pathParts = new ArrayList<String>(this.pathParts);
        }
        return genericUrl;
    }
    
    @Override
    public GenericUrl set(final String s, final Object o) {
        return (GenericUrl)super.set(s, o);
    }
    
    public final String getScheme() {
        return this.scheme;
    }
    
    public final void setScheme(final String s) {
        this.scheme = Preconditions.<String>checkNotNull(s);
    }
    
    public String getHost() {
        return this.host;
    }
    
    public final void setHost(final String s) {
        this.host = Preconditions.<String>checkNotNull(s);
    }
    
    public final String getUserInfo() {
        return this.userInfo;
    }
    
    public final void setUserInfo(final String userInfo) {
        this.userInfo = userInfo;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public final void setPort(final int port) {
        Preconditions.checkArgument(port >= -1, (Object)"expected port >= -1");
        this.port = port;
    }
    
    public List<String> getPathParts() {
        return this.pathParts;
    }
    
    public void setPathParts(final List<String> pathParts) {
        this.pathParts = pathParts;
    }
    
    public String getFragment() {
        return this.fragment;
    }
    
    public final void setFragment(final String fragment) {
        this.fragment = fragment;
    }
    
    public final String build() {
        return this.buildAuthority() + this.buildRelativeUrl();
    }
    
    public final String buildAuthority() {
        final StringBuilder sb = new StringBuilder();
        sb.append(Preconditions.<String>checkNotNull(this.scheme));
        sb.append("://");
        if (this.userInfo != null) {
            sb.append(CharEscapers.escapeUriUserInfo(this.userInfo)).append('@');
        }
        sb.append(Preconditions.<String>checkNotNull(this.host));
        final int port = this.port;
        if (port != -1) {
            sb.append(':').append(port);
        }
        return sb.toString();
    }
    
    public final String buildRelativeUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.pathParts != null) {
            this.appendRawPathFromParts(sb);
        }
        addQueryParams(this.entrySet(), sb);
        final String fragment = this.fragment;
        if (fragment != null) {
            sb.append('#').append(GenericUrl.URI_FRAGMENT_ESCAPER.escape(fragment));
        }
        return sb.toString();
    }
    
    public final URI toURI() {
        return toURI(this.build());
    }
    
    public final URL toURL() {
        return parseURL(this.build());
    }
    
    public final URL toURL(final String s) {
        try {
            return new URL(this.toURL(), s);
        }
        catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public Object getFirst(final String s) {
        final Object value = this.get(s);
        if (value instanceof Collection) {
            final Iterator<Object> iterator = ((Collection<Object>)value).iterator();
            return iterator.hasNext() ? iterator.next() : null;
        }
        return value;
    }
    
    public Collection<Object> getAll(final String s) {
        final Object value = this.get(s);
        if (value == null) {
            return Collections.<Object>emptySet();
        }
        if (value instanceof Collection) {
            return Collections.<Object>unmodifiableCollection((Collection<?>)value);
        }
        return (Collection<Object>)Collections.<Collection<? extends T>>singleton(value);
    }
    
    public String getRawPath() {
        if (this.pathParts == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        this.appendRawPathFromParts(sb);
        return sb.toString();
    }
    
    public void setRawPath(final String s) {
        this.pathParts = toPathParts(s);
    }
    
    public void appendRawPath(final String s) {
        if (s != null && s.length() != 0) {
            final List<String> pathParts = toPathParts(s);
            if (this.pathParts == null || this.pathParts.isEmpty()) {
                this.pathParts = pathParts;
            }
            else {
                final int size = this.pathParts.size();
                this.pathParts.set(size - 1, this.pathParts.get(size - 1) + pathParts.get(0));
                this.pathParts.addAll(pathParts.subList(1, pathParts.size()));
            }
        }
    }
    
    public static List<String> toPathParts(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>();
        int n = 0;
        boolean b = true;
        while (b) {
            final int index = s.indexOf(47, n);
            b = (index != -1);
            String s2;
            if (b) {
                s2 = s.substring(n, index);
            }
            else {
                s2 = s.substring(n);
            }
            list.add(CharEscapers.decodeUri(s2));
            n = index + 1;
        }
        return list;
    }
    
    private void appendRawPathFromParts(final StringBuilder sb) {
        for (int size = this.pathParts.size(), i = 0; i < size; ++i) {
            final String s = this.pathParts.get(i);
            if (i != 0) {
                sb.append('/');
            }
            if (s.length() != 0) {
                sb.append(CharEscapers.escapeUriPath(s));
            }
        }
    }
    
    static void addQueryParams(final Set<Map.Entry<String, Object>> set, final StringBuilder sb) {
        boolean b = true;
        for (final Map.Entry<String, Object> entry : set) {
            final Collection<Object> value = entry.getValue();
            if (value != null) {
                final String escapeUriQuery = CharEscapers.escapeUriQuery(entry.getKey());
                if (value instanceof Collection) {
                    final Iterator<Object> iterator2 = value.iterator();
                    while (iterator2.hasNext()) {
                        b = appendParam(b, sb, escapeUriQuery, iterator2.next());
                    }
                }
                else {
                    b = appendParam(b, sb, escapeUriQuery, value);
                }
            }
        }
    }
    
    private static boolean appendParam(boolean b, final StringBuilder sb, final String s, final Object o) {
        if (b) {
            b = false;
            sb.append('?');
        }
        else {
            sb.append('&');
        }
        sb.append(s);
        final String escapeUriQuery = CharEscapers.escapeUriQuery(o.toString());
        if (escapeUriQuery.length() != 0) {
            sb.append('=').append(escapeUriQuery);
        }
        return b;
    }
    
    private static URI toURI(final String s) {
        try {
            return new URI(s);
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    private static URL parseURL(final String s) {
        try {
            return new URL(s);
        }
        catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
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
    
    static {
        URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:", false);
    }
}
