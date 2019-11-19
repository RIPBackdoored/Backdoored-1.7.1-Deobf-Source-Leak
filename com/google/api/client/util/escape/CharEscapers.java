package com.google.api.client.util.escape;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class CharEscapers
{
    private static final Escaper URI_ESCAPER;
    private static final Escaper URI_PATH_ESCAPER;
    private static final Escaper URI_RESERVED_ESCAPER;
    private static final Escaper URI_USERINFO_ESCAPER;
    private static final Escaper URI_QUERY_STRING_ESCAPER;
    
    public static String escapeUri(final String s) {
        return CharEscapers.URI_ESCAPER.escape(s);
    }
    
    public static String decodeUri(final String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static String escapeUriPath(final String s) {
        return CharEscapers.URI_PATH_ESCAPER.escape(s);
    }
    
    public static String escapeUriPathWithoutReserved(final String s) {
        return CharEscapers.URI_RESERVED_ESCAPER.escape(s);
    }
    
    public static String escapeUriUserInfo(final String s) {
        return CharEscapers.URI_USERINFO_ESCAPER.escape(s);
    }
    
    public static String escapeUriQuery(final String s) {
        return CharEscapers.URI_QUERY_STRING_ESCAPER.escape(s);
    }
    
    private CharEscapers() {
        super();
    }
    
    static {
        URI_ESCAPER = new PercentEscaper("-_.*", true);
        URI_PATH_ESCAPER = new PercentEscaper("-_.!~*'()@:$&,;=", false);
        URI_RESERVED_ESCAPER = new PercentEscaper("-_.!~*'()@:$&,;=+/?", false);
        URI_USERINFO_ESCAPER = new PercentEscaper("-_.!~*'():$&,;=", false);
        URI_QUERY_STRING_ESCAPER = new PercentEscaper("-_.!~*'()@:$,;/?:", false);
    }
}
