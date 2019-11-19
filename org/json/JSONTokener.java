package org.json;

import java.io.IOException;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.Reader;

public class JSONTokener
{
    private long character;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;
    private long characterPreviousLine;
    
    public JSONTokener(final Reader reader) {
        super();
        this.reader = (reader.markSupported() ? reader : new BufferedReader(reader));
        this.eof = false;
        this.usePrevious = false;
        this.previous = '\0';
        this.index = 0L;
        this.character = 1L;
        this.characterPreviousLine = 0L;
        this.line = 1L;
    }
    
    public JSONTokener(final InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }
    
    public JSONTokener(final String s) {
        this(new StringReader(s));
    }
    
    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0L) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.decrementIndexes();
        this.usePrevious = true;
        this.eof = false;
    }
    
    private void decrementIndexes() {
        --this.index;
        if (this.previous == '\r' || this.previous == '\n') {
            --this.line;
            this.character = this.characterPreviousLine;
        }
        else if (this.character > 0L) {
            --this.character;
        }
    }
    
    public static int dehexchar(final char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        return -1;
    }
    
    public boolean end() {
        return this.eof && !this.usePrevious;
    }
    
    public boolean more() throws JSONException {
        if (this.usePrevious) {
            return true;
        }
        try {
            this.reader.mark(1);
        }
        catch (IOException ex) {
            throw new JSONException("Unable to preserve stream position", ex);
        }
        try {
            if (this.reader.read() <= 0) {
                this.eof = true;
                return false;
            }
            this.reader.reset();
        }
        catch (IOException ex2) {
            throw new JSONException("Unable to read the next character from the stream", ex2);
        }
        return true;
    }
    
    public char next() throws JSONException {
        int n;
        if (this.usePrevious) {
            this.usePrevious = false;
            n = this.previous;
        }
        else {
            try {
                n = this.reader.read();
            }
            catch (IOException ex) {
                throw new JSONException(ex);
            }
        }
        if (n <= 0) {
            this.eof = true;
            return '\0';
        }
        this.incrementIndexes(n);
        return this.previous = (char)n;
    }
    
    private void incrementIndexes(final int n) {
        if (n > 0) {
            ++this.index;
            if (n == 13) {
                ++this.line;
                this.characterPreviousLine = this.character;
                this.character = 0L;
            }
            else if (n == 10) {
                if (this.previous != '\r') {
                    ++this.line;
                    this.characterPreviousLine = this.character;
                }
                this.character = 0L;
            }
            else {
                ++this.character;
            }
        }
    }
    
    public char next(final char c) throws JSONException {
        final char next = this.next();
        if (next == c) {
            return next;
        }
        if (next > '\0') {
            throw this.syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
        }
        throw this.syntaxError("Expected '" + c + "' and instead saw ''");
    }
    
    public String next(final int n) throws JSONException {
        return "";
    }
    
    public char nextClean() throws JSONException {
        char next;
        do {
            next = this.next();
        } while (next != '\0' && next <= ' ');
        return next;
    }
    
    public String nextString(final char c) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final char next = this.next();
            switch (next) {
                case 0:
                case 10:
                case 13:
                    throw this.syntaxError("Unterminated string");
                case 92: {
                    final char next2 = this.next();
                    switch (next2) {
                        case 98:
                            sb.append('\b');
                            continue;
                        case 116:
                            sb.append('\t');
                            continue;
                        case 110:
                            sb.append('\n');
                            continue;
                        case 102:
                            sb.append('\f');
                            continue;
                        case 114:
                            sb.append('\r');
                            continue;
                        case 117:
                            try {
                                sb.append((char)Integer.parseInt(this.next(4), 16));
                                continue;
                            }
                            catch (NumberFormatException ex) {
                                throw this.syntaxError("Illegal escape.", ex);
                            }
                        case 34:
                        case 39:
                        case 47:
                        case 92:
                            sb.append(next2);
                            continue;
                        default:
                            throw this.syntaxError("Illegal escape.");
                    }
                    break;
                }
                default:
                    if (next == c) {
                        return sb.toString();
                    }
                    sb.append(next);
                    continue;
            }
        }
    }
    
    public String nextTo(final char c) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        char next;
        while (true) {
            next = this.next();
            if (next == c || next == '\0' || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != '\0') {
            this.back();
        }
        return sb.toString().trim();
    }
    
    public String nextTo(final String s) throws JSONException {
        final StringBuilder sb = new StringBuilder();
        char next;
        while (true) {
            next = this.next();
            if (s.indexOf(next) >= 0 || next == '\0' || next == '\n' || next == '\r') {
                break;
            }
            sb.append(next);
        }
        if (next != '\0') {
            this.back();
        }
        return sb.toString().trim();
    }
    
    public Object nextValue() throws JSONException {
        char c = this.nextClean();
        switch (c) {
            case 34:
            case 39:
                return this.nextString(c);
            case 123:
                this.back();
                return new JSONObject(this);
            case 91:
                this.back();
                return new JSONArray(this);
            default: {
                final StringBuilder sb = new StringBuilder();
                while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
                    sb.append(c);
                    c = this.next();
                }
                if (!this.eof) {
                    this.back();
                }
                final String trim = sb.toString().trim();
                if ("".equals(trim)) {
                    throw this.syntaxError("Missing value");
                }
                return JSONObject.stringToValue(trim);
            }
        }
    }
    
    public char skipTo(final char c) throws JSONException {
        try {
            final long index = this.index;
            final long character = this.character;
            final long line = this.line;
            this.reader.mark(1000000);
            this.next();
            this.reader.reset();
            this.index = index;
            this.character = character;
            this.line = line;
            return '\0';
        }
        catch (IOException ex) {
            throw new JSONException(ex);
        }
    }
    
    public JSONException syntaxError(final String s) {
        return new JSONException(s + this.toString());
    }
    
    public JSONException syntaxError(final String s, final Throwable t) {
        return new JSONException(s + this.toString(), t);
    }
    
    @Override
    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}
