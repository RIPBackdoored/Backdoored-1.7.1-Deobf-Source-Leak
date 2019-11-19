package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer
{
    public static final char SEPARATOR = '/';
    protected static final JsonPointer EMPTY;
    protected final JsonPointer _nextSegment;
    protected volatile JsonPointer _head;
    protected final String _asString;
    protected final String _matchingPropertyName;
    protected final int _matchingElementIndex;
    
    protected JsonPointer() {
        super();
        this._nextSegment = null;
        this._matchingPropertyName = "";
        this._matchingElementIndex = -1;
        this._asString = "";
    }
    
    protected JsonPointer(final String asString, final String matchingPropertyName, final JsonPointer nextSegment) {
        super();
        this._asString = asString;
        this._nextSegment = nextSegment;
        this._matchingPropertyName = matchingPropertyName;
        this._matchingElementIndex = _parseIndex(matchingPropertyName);
    }
    
    protected JsonPointer(final String asString, final String matchingPropertyName, final int matchingElementIndex, final JsonPointer nextSegment) {
        super();
        this._asString = asString;
        this._nextSegment = nextSegment;
        this._matchingPropertyName = matchingPropertyName;
        this._matchingElementIndex = matchingElementIndex;
    }
    
    public static JsonPointer compile(final String s) throws IllegalArgumentException {
        if (s == null || s.length() == 0) {
            return JsonPointer.EMPTY;
        }
        if (s.charAt(0) != '/') {
            throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + s + "\"");
        }
        return _parseTail(s);
    }
    
    public static JsonPointer valueOf(final String s) {
        return compile(s);
    }
    
    public static JsonPointer forPath(JsonStreamContext jsonStreamContext, final boolean b) {
        if (jsonStreamContext == null) {
            return JsonPointer.EMPTY;
        }
        if (!jsonStreamContext.hasPathSegment() && (!b || !jsonStreamContext.inRoot() || !jsonStreamContext.hasCurrentIndex())) {
            jsonStreamContext = jsonStreamContext.getParent();
        }
        JsonPointer jsonPointer = null;
        while (jsonStreamContext != null) {
            if (jsonStreamContext.inObject()) {
                String currentName = jsonStreamContext.getCurrentName();
                if (currentName == null) {
                    currentName = "";
                }
                jsonPointer = new JsonPointer(_fullPath(jsonPointer, currentName), currentName, jsonPointer);
            }
            else if (jsonStreamContext.inArray() || b) {
                final int currentIndex = jsonStreamContext.getCurrentIndex();
                final String value = String.valueOf(currentIndex);
                jsonPointer = new JsonPointer(_fullPath(jsonPointer, value), value, currentIndex, jsonPointer);
            }
            jsonStreamContext = jsonStreamContext.getParent();
        }
        if (jsonPointer == null) {
            return JsonPointer.EMPTY;
        }
        return jsonPointer;
    }
    
    private static String _fullPath(final JsonPointer jsonPointer, final String s) {
        if (jsonPointer == null) {
            final StringBuilder sb = new StringBuilder(s.length() + 1);
            sb.append('/');
            _appendEscaped(sb, s);
            return sb.toString();
        }
        final String asString = jsonPointer._asString;
        final StringBuilder sb2 = new StringBuilder(s.length() + 1 + asString.length());
        sb2.append('/');
        _appendEscaped(sb2, s);
        sb2.append(asString);
        return sb2.toString();
    }
    
    private static void _appendEscaped(final StringBuilder sb, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '/') {
                sb.append("~1");
            }
            else if (char1 == '~') {
                sb.append("~0");
            }
            else {
                sb.append(char1);
            }
        }
    }
    
    public boolean matches() {
        return this._nextSegment == null;
    }
    
    public String getMatchingProperty() {
        return this._matchingPropertyName;
    }
    
    public int getMatchingIndex() {
        return this._matchingElementIndex;
    }
    
    public boolean mayMatchProperty() {
        return this._matchingPropertyName != null;
    }
    
    public boolean mayMatchElement() {
        return this._matchingElementIndex >= 0;
    }
    
    public JsonPointer last() {
        JsonPointer jsonPointer = this;
        if (jsonPointer == JsonPointer.EMPTY) {
            return null;
        }
        JsonPointer nextSegment;
        while ((nextSegment = jsonPointer._nextSegment) != JsonPointer.EMPTY) {
            jsonPointer = nextSegment;
        }
        return jsonPointer;
    }
    
    public JsonPointer append(final JsonPointer jsonPointer) {
        if (this == JsonPointer.EMPTY) {
            return jsonPointer;
        }
        if (jsonPointer == JsonPointer.EMPTY) {
            return this;
        }
        String s = this._asString;
        if (s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        return compile(s + jsonPointer._asString);
    }
    
    public boolean matchesProperty(final String s) {
        return this._nextSegment != null && this._matchingPropertyName.equals(s);
    }
    
    public JsonPointer matchProperty(final String s) {
        if (this._nextSegment != null && this._matchingPropertyName.equals(s)) {
            return this._nextSegment;
        }
        return null;
    }
    
    public boolean matchesElement(final int n) {
        return n == this._matchingElementIndex && n >= 0;
    }
    
    public JsonPointer matchElement(final int n) {
        if (n != this._matchingElementIndex || n < 0) {
            return null;
        }
        return this._nextSegment;
    }
    
    public JsonPointer tail() {
        return this._nextSegment;
    }
    
    public JsonPointer head() {
        JsonPointer head = this._head;
        if (head == null) {
            if (this != JsonPointer.EMPTY) {
                head = this._constructHead();
            }
            this._head = head;
        }
        return head;
    }
    
    @Override
    public String toString() {
        return this._asString;
    }
    
    @Override
    public int hashCode() {
        return this._asString.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o != null && o instanceof JsonPointer && this._asString.equals(((JsonPointer)o)._asString));
    }
    
    private static final int _parseIndex(final String s) {
        final int length = s.length();
        if (length == 0 || length > 10) {
            return -1;
        }
        final char char1 = s.charAt(0);
        if (char1 <= '0') {
            return (length == 1 && char1 == '0') ? 0 : -1;
        }
        if (char1 > '9') {
            return -1;
        }
        for (int i = 1; i < length; ++i) {
            final char char2 = s.charAt(i);
            if (char2 > '9' || char2 < '0') {
                return -1;
            }
        }
        if (length == 10 && NumberInput.parseLong(s) > 0L) {
            return -1;
        }
        return NumberInput.parseInt(s);
    }
    
    protected static JsonPointer _parseTail(final String s) {
        final int length = s.length();
        int i = 1;
        while (i < length) {
            final char char1 = s.charAt(i);
            if (char1 == '/') {
                return new JsonPointer(s, s.substring(1, i), _parseTail(s.substring(i)));
            }
            ++i;
            if (char1 == '~' && i < length) {
                return _parseQuotedTail(s, i);
            }
        }
        return new JsonPointer(s, s.substring(1), JsonPointer.EMPTY);
    }
    
    protected static JsonPointer _parseQuotedTail(final String s, int i) {
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(Math.max(16, length));
        if (i > 2) {
            sb.append(s, 1, i - 1);
        }
        _appendEscape(sb, s.charAt(i++));
        while (i < length) {
            final char char1 = s.charAt(i);
            if (char1 == '/') {
                return new JsonPointer(s, sb.toString(), _parseTail(s.substring(i)));
            }
            ++i;
            if (char1 == '~' && i < length) {
                _appendEscape(sb, s.charAt(i++));
            }
            else {
                sb.append(char1);
            }
        }
        return new JsonPointer(s, sb.toString(), JsonPointer.EMPTY);
    }
    
    protected JsonPointer _constructHead() {
        final JsonPointer last = this.last();
        if (last == this) {
            return JsonPointer.EMPTY;
        }
        final int length = last._asString.length();
        return new JsonPointer(this._asString.substring(0, this._asString.length() - length), this._matchingPropertyName, this._matchingElementIndex, this._nextSegment._constructHead(length, last));
    }
    
    protected JsonPointer _constructHead(final int n, final JsonPointer jsonPointer) {
        if (this == jsonPointer) {
            return JsonPointer.EMPTY;
        }
        final JsonPointer nextSegment = this._nextSegment;
        final String asString = this._asString;
        return new JsonPointer(asString.substring(0, asString.length() - n), this._matchingPropertyName, this._matchingElementIndex, nextSegment._constructHead(n, jsonPointer));
    }
    
    private static void _appendEscape(final StringBuilder sb, char c) {
        if (c == '0') {
            c = '~';
        }
        else if (c == '1') {
            c = '/';
        }
        else {
            sb.append('~');
        }
        sb.append(c);
    }
    
    static {
        EMPTY = new JsonPointer();
    }
}
