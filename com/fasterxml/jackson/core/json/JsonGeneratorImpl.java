package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.io.CharTypes;
import java.io.IOException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.base.GeneratorBase;

public abstract class JsonGeneratorImpl extends GeneratorBase
{
    protected static final int[] sOutputEscapes;
    protected final IOContext _ioContext;
    protected int[] _outputEscapes;
    protected int _maximumNonEscapedChar;
    protected CharacterEscapes _characterEscapes;
    protected SerializableString _rootValueSeparator;
    protected boolean _cfgUnqNames;
    
    public JsonGeneratorImpl(final IOContext ioContext, final int n, final ObjectCodec objectCodec) {
        super(n, objectCodec);
        this._outputEscapes = JsonGeneratorImpl.sOutputEscapes;
        this._rootValueSeparator = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._ioContext = ioContext;
        if (Feature.ESCAPE_NON_ASCII.enabledIn(n)) {
            this._maximumNonEscapedChar = 127;
        }
        this._cfgUnqNames = !Feature.QUOTE_FIELD_NAMES.enabledIn(n);
    }
    
    @Override
    public Version version() {
        return VersionUtil.versionFor(this.getClass());
    }
    
    @Override
    public JsonGenerator enable(final Feature feature) {
        super.enable(feature);
        if (feature == Feature.QUOTE_FIELD_NAMES) {
            this._cfgUnqNames = false;
        }
        return this;
    }
    
    @Override
    public JsonGenerator disable(final Feature feature) {
        super.disable(feature);
        if (feature == Feature.QUOTE_FIELD_NAMES) {
            this._cfgUnqNames = true;
        }
        return this;
    }
    
    @Override
    protected void _checkStdFeatureChanges(final int n, final int n2) {
        super._checkStdFeatureChanges(n, n2);
        this._cfgUnqNames = !Feature.QUOTE_FIELD_NAMES.enabledIn(n);
    }
    
    @Override
    public JsonGenerator setHighestNonEscapedChar(final int n) {
        this._maximumNonEscapedChar = ((n < 0) ? 0 : n);
        return this;
    }
    
    @Override
    public int getHighestEscapedChar() {
        return this._maximumNonEscapedChar;
    }
    
    @Override
    public JsonGenerator setCharacterEscapes(final CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        if (characterEscapes == null) {
            this._outputEscapes = JsonGeneratorImpl.sOutputEscapes;
        }
        else {
            this._outputEscapes = characterEscapes.getEscapeCodesForAscii();
        }
        return this;
    }
    
    @Override
    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }
    
    @Override
    public JsonGenerator setRootValueSeparator(final SerializableString rootValueSeparator) {
        this._rootValueSeparator = rootValueSeparator;
        return this;
    }
    
    @Override
    public final void writeStringField(final String s, final String s2) throws IOException {
        this.writeFieldName(s);
        this.writeString(s2);
    }
    
    protected void _verifyPrettyValueWrite(final String s, final int n) throws IOException {
        switch (n) {
            case 1:
                this._cfgPrettyPrinter.writeArrayValueSeparator(this);
                break;
            case 2:
                this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
                break;
            case 3:
                this._cfgPrettyPrinter.writeRootValueSeparator(this);
                break;
            case 0:
                if (this._writeContext.inArray()) {
                    this._cfgPrettyPrinter.beforeArrayValues(this);
                    break;
                }
                if (this._writeContext.inObject()) {
                    this._cfgPrettyPrinter.beforeObjectEntries(this);
                    break;
                }
                break;
            case 5:
                this._reportCantWriteValueExpectName(s);
                break;
            default:
                this._throwInternal();
                break;
        }
    }
    
    protected void _reportCantWriteValueExpectName(final String s) throws IOException {
        this._reportError(String.format("Can not %s, expecting field name (context: %s)", s, this._writeContext.typeDesc()));
    }
    
    static {
        sOutputEscapes = CharTypes.get7BitOutputEscapes();
    }
}
