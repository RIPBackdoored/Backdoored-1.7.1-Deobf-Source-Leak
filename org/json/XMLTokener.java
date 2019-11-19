package org.json;

import java.io.Reader;
import java.util.HashMap;

public class XMLTokener extends JSONTokener
{
    public static final HashMap<String, Character> entity;
    
    public XMLTokener(final Reader reader) {
        super(reader);
    }
    
    public XMLTokener(final String s) {
        super(s);
    }
    
    public String nextCDATA() throws JSONException {
        final StringBuilder sb = new StringBuilder();
        while (this.more()) {
            sb.append(this.next());
            final int length = sb.length() - 3;
            if (length >= 0 && sb.charAt(length) == ']' && sb.charAt(length + 1) == ']' && sb.charAt(length + 2) == '>') {
                sb.setLength(length);
                return sb.toString();
            }
        }
        throw this.syntaxError("Unclosed CDATA");
    }
    
    public Object nextContent() throws JSONException {
        while (Character.isWhitespace(this.next())) {}
        return null;
    }
    
    public Object nextEntity(final char c) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        char next;
        while (true) {
            next = this.next();
            if (!Character.isLetterOrDigit(next) && next != '#') {
                break;
            }
            sb.append(Character.toLowerCase(next));
        }
        if (next == ';') {
            return unescapeEntity(sb.toString());
        }
        throw this.syntaxError("Missing ';' in XML entity: &" + (Object)sb);
    }
    
    static String unescapeEntity(final String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        if (s.charAt(0) == '#') {
            int n;
            if (s.charAt(1) == 'x') {
                n = Integer.parseInt(s.substring(2), 16);
            }
            else {
                n = Integer.parseInt(s.substring(1));
            }
            return new String(new int[] { n }, 0, 1);
        }
        final Character c = XMLTokener.entity.get(s);
        if (c == null) {
            return '&' + s + ';';
        }
        return c.toString();
    }
    
    public Object nextMeta() throws JSONException {
        char next;
        do {
            next = this.next();
        } while (Character.isWhitespace(next));
        switch (next) {
            case '\0':
                throw this.syntaxError("Misshaped meta tag");
            case '<':
                return XML.LT;
            case '>':
                return XML.GT;
            case '/':
                return XML.SLASH;
            case '=':
                return XML.EQ;
            case '!':
                return XML.BANG;
            case '?':
                return XML.QUEST;
            case '\"':
            case '\'':
                this.next();
                throw this.syntaxError("Unterminated string");
            default:
                while (true) {
                    final char next2 = this.next();
                    if (Character.isWhitespace(next2)) {
                        return Boolean.TRUE;
                    }
                    switch (next2) {
                        case '\0':
                        case '!':
                        case '\"':
                        case '\'':
                        case '/':
                        case '<':
                        case '=':
                        case '>':
                        case '?':
                            this.back();
                            return Boolean.TRUE;
                        default:
                            continue;
                    }
                }
                break;
        }
    }
    
    public Object nextToken() throws JSONException {
        char c;
        do {
            c = this.next();
        } while (Character.isWhitespace(c));
        switch (c) {
            case '\0':
                throw this.syntaxError("Misshaped element");
            case '<':
                throw this.syntaxError("Misplaced '<'");
            case '>':
                return XML.GT;
            case '/':
                return XML.SLASH;
            case '=':
                return XML.EQ;
            case '!':
                return XML.BANG;
            case '?':
                return XML.QUEST;
            case '\"':
            case '\'': {
                final StringBuilder sb = new StringBuilder();
                this.next();
                throw this.syntaxError("Unterminated string");
            }
            default: {
                final StringBuilder sb2 = new StringBuilder();
                while (true) {
                    sb2.append(c);
                    c = this.next();
                    if (Character.isWhitespace(c)) {
                        return sb2.toString();
                    }
                    switch (c) {
                        case '\0':
                            return sb2.toString();
                        case '!':
                        case '/':
                        case '=':
                        case '>':
                        case '?':
                        case '[':
                        case ']':
                            this.back();
                            return sb2.toString();
                        case '\"':
                        case '\'':
                        case '<':
                            throw this.syntaxError("Bad character in a name");
                        default:
                            continue;
                    }
                }
                break;
            }
        }
    }
    
    public void skipPast(final String s) {
        final int n = 0;
        final int length = s.length();
        final char[] array = new char[length];
        if (0 < length) {
            this.next();
            return;
        }
        int n2 = n;
        boolean b = true;
        for (int i = 0; i < length; ++i) {
            if (array[n2] != s.charAt(i)) {
                b = false;
                break;
            }
            if (++n2 >= length) {
                n2 -= length;
            }
        }
        if (b) {
            return;
        }
        this.next();
    }
    
    static {
        (entity = new HashMap<String, Character>(8)).put("amp", XML.AMP);
        XMLTokener.entity.put("apos", XML.APOS);
        XMLTokener.entity.put("gt", XML.GT);
        XMLTokener.entity.put("lt", XML.LT);
        XMLTokener.entity.put("quot", XML.QUOT);
    }
}
