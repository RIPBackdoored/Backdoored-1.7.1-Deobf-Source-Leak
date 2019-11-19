package com.fasterxml.jackson.core.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.Serializable;
import com.fasterxml.jackson.core.PrettyPrinter;

public class DefaultPrettyPrinter implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR;
    protected Indenter _arrayIndenter;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries;
    protected transient int _nesting;
    protected Separators _separators;
    protected String _objectFieldValueSeparatorWithSpaces;
    
    public DefaultPrettyPrinter() {
        this(DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    }
    
    public DefaultPrettyPrinter(final String s) {
        this((s == null) ? null : new SerializedString(s));
    }
    
    public DefaultPrettyPrinter(final SerializableString rootSeparator) {
        super();
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        this._spacesInObjectEntries = true;
        this._rootSeparator = rootSeparator;
        this.withSeparators(DefaultPrettyPrinter.DEFAULT_SEPARATORS);
    }
    
    public DefaultPrettyPrinter(final DefaultPrettyPrinter defaultPrettyPrinter) {
        this(defaultPrettyPrinter, defaultPrettyPrinter._rootSeparator);
    }
    
    public DefaultPrettyPrinter(final DefaultPrettyPrinter defaultPrettyPrinter, final SerializableString rootSeparator) {
        super();
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        this._spacesInObjectEntries = true;
        this._arrayIndenter = defaultPrettyPrinter._arrayIndenter;
        this._objectIndenter = defaultPrettyPrinter._objectIndenter;
        this._spacesInObjectEntries = defaultPrettyPrinter._spacesInObjectEntries;
        this._nesting = defaultPrettyPrinter._nesting;
        this._separators = defaultPrettyPrinter._separators;
        this._objectFieldValueSeparatorWithSpaces = defaultPrettyPrinter._objectFieldValueSeparatorWithSpaces;
        this._rootSeparator = rootSeparator;
    }
    
    public DefaultPrettyPrinter withRootSeparator(final SerializableString serializableString) {
        if (this._rootSeparator == serializableString || (serializableString != null && serializableString.equals(this._rootSeparator))) {
            return this;
        }
        return new DefaultPrettyPrinter(this, serializableString);
    }
    
    public DefaultPrettyPrinter withRootSeparator(final String s) {
        return this.withRootSeparator((s == null) ? null : new SerializedString(s));
    }
    
    public void indentArraysWith(final Indenter indenter) {
        this._arrayIndenter = ((indenter == null) ? NopIndenter.instance : indenter);
    }
    
    public void indentObjectsWith(final Indenter indenter) {
        this._objectIndenter = ((indenter == null) ? NopIndenter.instance : indenter);
    }
    
    public DefaultPrettyPrinter withArrayIndenter(Indenter instance) {
        if (instance == null) {
            instance = NopIndenter.instance;
        }
        if (this._arrayIndenter == instance) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._arrayIndenter = instance;
        return defaultPrettyPrinter;
    }
    
    public DefaultPrettyPrinter withObjectIndenter(Indenter instance) {
        if (instance == null) {
            instance = NopIndenter.instance;
        }
        if (this._objectIndenter == instance) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._objectIndenter = instance;
        return defaultPrettyPrinter;
    }
    
    public DefaultPrettyPrinter withSpacesInObjectEntries() {
        return this._withSpaces(true);
    }
    
    public DefaultPrettyPrinter withoutSpacesInObjectEntries() {
        return this._withSpaces(false);
    }
    
    protected DefaultPrettyPrinter _withSpaces(final boolean spacesInObjectEntries) {
        if (this._spacesInObjectEntries == spacesInObjectEntries) {
            return this;
        }
        final DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter._spacesInObjectEntries = spacesInObjectEntries;
        return defaultPrettyPrinter;
    }
    
    public DefaultPrettyPrinter withSeparators(final Separators separators) {
        this._separators = separators;
        this._objectFieldValueSeparatorWithSpaces = " " + separators.getObjectFieldValueSeparator() + " ";
        return this;
    }
    
    @Override
    public DefaultPrettyPrinter createInstance() {
        return new DefaultPrettyPrinter(this);
    }
    
    @Override
    public void writeRootValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        if (this._rootSeparator != null) {
            jsonGenerator.writeRaw(this._rootSeparator);
        }
    }
    
    @Override
    public void writeStartObject(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            ++this._nesting;
        }
    }
    
    @Override
    public void beforeObjectEntries(final JsonGenerator jsonGenerator) throws IOException {
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }
    
    @Override
    public void writeObjectFieldValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        if (this._spacesInObjectEntries) {
            jsonGenerator.writeRaw(this._objectFieldValueSeparatorWithSpaces);
        }
        else {
            jsonGenerator.writeRaw(this._separators.getObjectFieldValueSeparator());
        }
    }
    
    @Override
    public void writeObjectEntrySeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(this._separators.getObjectEntrySeparator());
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }
    
    @Override
    public void writeEndObject(final JsonGenerator jsonGenerator, final int n) throws IOException {
        if (!this._objectIndenter.isInline()) {
            --this._nesting;
        }
        if (n > 0) {
            this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
        }
        else {
            jsonGenerator.writeRaw(' ');
        }
        jsonGenerator.writeRaw('}');
    }
    
    @Override
    public void writeStartArray(final JsonGenerator jsonGenerator) throws IOException {
        if (!this._arrayIndenter.isInline()) {
            ++this._nesting;
        }
        jsonGenerator.writeRaw('[');
    }
    
    @Override
    public void beforeArrayValues(final JsonGenerator jsonGenerator) throws IOException {
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }
    
    @Override
    public void writeArrayValueSeparator(final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeRaw(this._separators.getArrayValueSeparator());
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }
    
    @Override
    public void writeEndArray(final JsonGenerator jsonGenerator, final int n) throws IOException {
        if (!this._arrayIndenter.isInline()) {
            --this._nesting;
        }
        if (n > 0) {
            this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
        }
        else {
            jsonGenerator.writeRaw(' ');
        }
        jsonGenerator.writeRaw(']');
    }
    
    @Override
    public /* bridge */ Object createInstance() {
        return this.createInstance();
    }
    
    static {
        DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    }
}
