package org.json;

public class XMLParserConfiguration
{
    public static final XMLParserConfiguration ORIGINAL;
    public static final XMLParserConfiguration KEEP_STRINGS;
    public final boolean keepStrings;
    public final String cDataTagName;
    public final boolean convertNilAttributeToNull;
    
    public XMLParserConfiguration() {
        this(false, "content", false);
    }
    
    public XMLParserConfiguration(final boolean b) {
        this(b, "content", false);
    }
    
    public XMLParserConfiguration(final String s) {
        this(false, s, false);
    }
    
    public XMLParserConfiguration(final boolean keepStrings, final String cDataTagName) {
        super();
        this.keepStrings = keepStrings;
        this.cDataTagName = cDataTagName;
        this.convertNilAttributeToNull = false;
    }
    
    public XMLParserConfiguration(final boolean keepStrings, final String cDataTagName, final boolean convertNilAttributeToNull) {
        super();
        this.keepStrings = keepStrings;
        this.cDataTagName = cDataTagName;
        this.convertNilAttributeToNull = convertNilAttributeToNull;
    }
    
    static {
        ORIGINAL = new XMLParserConfiguration();
        KEEP_STRINGS = new XMLParserConfiguration(true);
    }
}
