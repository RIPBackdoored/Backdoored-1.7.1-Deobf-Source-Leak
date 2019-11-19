package com.fasterxml.jackson.core.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;

public class DefaultIndenter extends DefaultPrettyPrinter.NopIndenter
{
    private static final long serialVersionUID = 1L;
    public static final String SYS_LF;
    public static final DefaultIndenter SYSTEM_LINEFEED_INSTANCE;
    private static final int INDENT_LEVELS = 16;
    private final char[] indents;
    private final int charsPerLevel;
    private final String eol;
    
    public DefaultIndenter() {
        this("  ", DefaultIndenter.SYS_LF);
    }
    
    public DefaultIndenter(final String s, final String eol) {
        super();
        this.charsPerLevel = s.length();
        this.indents = new char[s.length() * 16];
        int n = 0;
        for (int i = 0; i < 16; ++i) {
            s.getChars(0, s.length(), this.indents, n);
            n += s.length();
        }
        this.eol = eol;
    }
    
    public DefaultIndenter withLinefeed(final String s) {
        if (s.equals(this.eol)) {
            return this;
        }
        return new DefaultIndenter(this.getIndent(), s);
    }
    
    public DefaultIndenter withIndent(final String s) {
        if (s.equals(this.getIndent())) {
            return this;
        }
        return new DefaultIndenter(s, this.eol);
    }
    
    @Override
    public boolean isInline() {
        return false;
    }
    
    @Override
    public void writeIndentation(final JsonGenerator jsonGenerator, int i) throws IOException {
        jsonGenerator.writeRaw(this.eol);
        if (i > 0) {
            for (i *= this.charsPerLevel; i > this.indents.length; i -= this.indents.length) {
                jsonGenerator.writeRaw(this.indents, 0, this.indents.length);
            }
            jsonGenerator.writeRaw(this.indents, 0, i);
        }
    }
    
    public String getEol() {
        return this.eol;
    }
    
    public String getIndent() {
        return new String(this.indents, 0, this.charsPerLevel);
    }
    
    static {
        String property;
        try {
            property = System.getProperty("line.separator");
        }
        catch (Throwable t) {
            property = "\n";
        }
        SYS_LF = property;
        SYSTEM_LINEFEED_INSTANCE = new DefaultIndenter("  ", DefaultIndenter.SYS_LF);
    }
}
