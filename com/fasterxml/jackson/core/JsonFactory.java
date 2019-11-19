package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.BufferRecycler;
import java.io.OutputStreamWriter;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import java.io.DataOutput;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.OutputStream;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import java.io.DataInput;
import java.io.CharArrayReader;
import java.io.StringReader;
import java.io.Reader;
import java.net.URL;
import com.fasterxml.jackson.core.io.IOContext;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.io.IOException;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.Serializable;

public class JsonFactory implements Versioned, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final String FORMAT_NAME_JSON = "JSON";
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS;
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS;
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS;
    private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer;
    protected ObjectCodec _objectCodec;
    protected int _factoryFeatures;
    protected int _parserFeatures;
    protected int _generatorFeatures;
    protected CharacterEscapes _characterEscapes;
    protected InputDecorator _inputDecorator;
    protected OutputDecorator _outputDecorator;
    protected SerializableString _rootValueSeparator;
    
    public JsonFactory() {
        this(null);
    }
    
    public JsonFactory(final ObjectCodec objectCodec) {
        super();
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
    }
    
    protected JsonFactory(final JsonFactory jsonFactory, final ObjectCodec objectCodec) {
        super();
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = JsonFactory.DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = JsonFactory.DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = JsonFactory.DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
        this._factoryFeatures = jsonFactory._factoryFeatures;
        this._parserFeatures = jsonFactory._parserFeatures;
        this._generatorFeatures = jsonFactory._generatorFeatures;
        this._characterEscapes = jsonFactory._characterEscapes;
        this._inputDecorator = jsonFactory._inputDecorator;
        this._outputDecorator = jsonFactory._outputDecorator;
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
    }
    
    public JsonFactory copy() {
        this._checkInvalidCopy(JsonFactory.class);
        return new JsonFactory(this, null);
    }
    
    protected void _checkInvalidCopy(final Class<?> clazz) {
        if (this.getClass() != clazz) {
            throw new IllegalStateException("Failed copy(): " + this.getClass().getName() + " (version: " + this.version() + ") does not override copy(); it has to");
        }
    }
    
    protected Object readResolve() {
        return new JsonFactory(this, this._objectCodec);
    }
    
    public boolean requiresPropertyOrdering() {
        return false;
    }
    
    public boolean canHandleBinaryNatively() {
        return false;
    }
    
    public boolean canUseCharArrays() {
        return true;
    }
    
    public boolean canParseAsync() {
        return this._isJSONFactory();
    }
    
    public Class<? extends FormatFeature> getFormatReadFeatureType() {
        return null;
    }
    
    public Class<? extends FormatFeature> getFormatWriteFeatureType() {
        return null;
    }
    
    public boolean canUseSchema(final FormatSchema formatSchema) {
        if (formatSchema == null) {
            return false;
        }
        final String formatName = this.getFormatName();
        return formatName != null && formatName.equals(formatSchema.getSchemaType());
    }
    
    public String getFormatName() {
        if (this.getClass() == JsonFactory.class) {
            return "JSON";
        }
        return null;
    }
    
    public MatchStrength hasFormat(final InputAccessor inputAccessor) throws IOException {
        if (this.getClass() == JsonFactory.class) {
            return this.hasJSONFormat(inputAccessor);
        }
        return null;
    }
    
    public boolean requiresCustomCodec() {
        return false;
    }
    
    protected MatchStrength hasJSONFormat(final InputAccessor inputAccessor) throws IOException {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }
    
    @Override
    public Version version() {
        return PackageVersion.VERSION;
    }
    
    public final JsonFactory configure(final Feature feature, final boolean b) {
        return b ? this.enable(feature) : this.disable(feature);
    }
    
    public JsonFactory enable(final Feature feature) {
        this._factoryFeatures |= feature.getMask();
        return this;
    }
    
    public JsonFactory disable(final Feature feature) {
        this._factoryFeatures &= ~feature.getMask();
        return this;
    }
    
    public final boolean isEnabled(final Feature feature) {
        return (this._factoryFeatures & feature.getMask()) != 0x0;
    }
    
    public final JsonFactory configure(final JsonParser.Feature feature, final boolean b) {
        return b ? this.enable(feature) : this.disable(feature);
    }
    
    public JsonFactory enable(final JsonParser.Feature feature) {
        this._parserFeatures |= feature.getMask();
        return this;
    }
    
    public JsonFactory disable(final JsonParser.Feature feature) {
        this._parserFeatures &= ~feature.getMask();
        return this;
    }
    
    public final boolean isEnabled(final JsonParser.Feature feature) {
        return (this._parserFeatures & feature.getMask()) != 0x0;
    }
    
    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }
    
    public JsonFactory setInputDecorator(final InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }
    
    public final JsonFactory configure(final JsonGenerator.Feature feature, final boolean b) {
        return b ? this.enable(feature) : this.disable(feature);
    }
    
    public JsonFactory enable(final JsonGenerator.Feature feature) {
        this._generatorFeatures |= feature.getMask();
        return this;
    }
    
    public JsonFactory disable(final JsonGenerator.Feature feature) {
        this._generatorFeatures &= ~feature.getMask();
        return this;
    }
    
    public final boolean isEnabled(final JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0x0;
    }
    
    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }
    
    public JsonFactory setCharacterEscapes(final CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }
    
    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }
    
    public JsonFactory setOutputDecorator(final OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }
    
    public JsonFactory setRootValueSeparator(final String s) {
        this._rootValueSeparator = ((s == null) ? null : new SerializedString(s));
        return this;
    }
    
    public String getRootValueSeparator() {
        return (this._rootValueSeparator == null) ? null : this._rootValueSeparator.getValue();
    }
    
    public JsonFactory setCodec(final ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }
    
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }
    
    public JsonParser createParser(final File file) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(file, true);
        return this._createParser(this._decorate(new FileInputStream(file), createContext), createContext);
    }
    
    public JsonParser createParser(final URL url) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(url, true);
        return this._createParser(this._decorate(this._optimizedStreamFromURL(url), createContext), createContext);
    }
    
    public JsonParser createParser(final InputStream inputStream) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(inputStream, false);
        return this._createParser(this._decorate(inputStream, createContext), createContext);
    }
    
    public JsonParser createParser(final Reader reader) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(reader, false);
        return this._createParser(this._decorate(reader, createContext), createContext);
    }
    
    public JsonParser createParser(final byte[] array) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(array, true);
        if (this._inputDecorator != null) {
            final InputStream decorate = this._inputDecorator.decorate(createContext, array, 0, array.length);
            if (decorate != null) {
                return this._createParser(decorate, createContext);
            }
        }
        return this._createParser(array, 0, array.length, createContext);
    }
    
    public JsonParser createParser(final byte[] array, final int n, final int n2) throws IOException, JsonParseException {
        final IOContext createContext = this._createContext(array, true);
        if (this._inputDecorator != null) {
            final InputStream decorate = this._inputDecorator.decorate(createContext, array, n, n2);
            if (decorate != null) {
                return this._createParser(decorate, createContext);
            }
        }
        return this._createParser(array, n, n2, createContext);
    }
    
    public JsonParser createParser(final String s) throws IOException, JsonParseException {
        final int length = s.length();
        if (this._inputDecorator != null || length > 32768 || !this.canUseCharArrays()) {
            return this.createParser(new StringReader(s));
        }
        final IOContext createContext = this._createContext(s, true);
        final char[] allocTokenBuffer = createContext.allocTokenBuffer(length);
        s.getChars(0, length, allocTokenBuffer, 0);
        return this._createParser(allocTokenBuffer, 0, length, createContext, true);
    }
    
    public JsonParser createParser(final char[] array) throws IOException {
        return this.createParser(array, 0, array.length);
    }
    
    public JsonParser createParser(final char[] array, final int n, final int n2) throws IOException {
        if (this._inputDecorator != null) {
            return this.createParser(new CharArrayReader(array, n, n2));
        }
        return this._createParser(array, n, n2, this._createContext(array, true), false);
    }
    
    public JsonParser createParser(final DataInput dataInput) throws IOException {
        final IOContext createContext = this._createContext(dataInput, false);
        return this._createParser(this._decorate(dataInput, createContext), createContext);
    }
    
    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        this._requireJSONFactory("Non-blocking source not (yet?) support for this format (%s)");
        return new NonBlockingJsonParser(this._createContext(null, false), this._parserFeatures, this._byteSymbolCanonicalizer.makeChild(this._factoryFeatures));
    }
    
    @Deprecated
    public JsonParser createJsonParser(final File file) throws IOException, JsonParseException {
        return this.createParser(file);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final URL url) throws IOException, JsonParseException {
        return this.createParser(url);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final InputStream inputStream) throws IOException, JsonParseException {
        return this.createParser(inputStream);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final Reader reader) throws IOException, JsonParseException {
        return this.createParser(reader);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final byte[] array) throws IOException, JsonParseException {
        return this.createParser(array);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final byte[] array, final int n, final int n2) throws IOException, JsonParseException {
        return this.createParser(array, n, n2);
    }
    
    @Deprecated
    public JsonParser createJsonParser(final String s) throws IOException, JsonParseException {
        return this.createParser(s);
    }
    
    public JsonGenerator createGenerator(final OutputStream outputStream, final JsonEncoding encoding) throws IOException {
        final IOContext createContext = this._createContext(outputStream, false);
        createContext.setEncoding(encoding);
        if (encoding == JsonEncoding.UTF8) {
            return this._createUTF8Generator(this._decorate(outputStream, createContext), createContext);
        }
        return this._createGenerator(this._decorate(this._createWriter(outputStream, encoding, createContext), createContext), createContext);
    }
    
    public JsonGenerator createGenerator(final OutputStream outputStream) throws IOException {
        return this.createGenerator(outputStream, JsonEncoding.UTF8);
    }
    
    public JsonGenerator createGenerator(final Writer writer) throws IOException {
        final IOContext createContext = this._createContext(writer, false);
        return this._createGenerator(this._decorate(writer, createContext), createContext);
    }
    
    public JsonGenerator createGenerator(final File file, final JsonEncoding encoding) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        final IOContext createContext = this._createContext(fileOutputStream, true);
        createContext.setEncoding(encoding);
        if (encoding == JsonEncoding.UTF8) {
            return this._createUTF8Generator(this._decorate(fileOutputStream, createContext), createContext);
        }
        return this._createGenerator(this._decorate(this._createWriter(fileOutputStream, encoding, createContext), createContext), createContext);
    }
    
    public JsonGenerator createGenerator(final DataOutput dataOutput, final JsonEncoding jsonEncoding) throws IOException {
        return this.createGenerator(this._createDataOutputWrapper(dataOutput), jsonEncoding);
    }
    
    public JsonGenerator createGenerator(final DataOutput dataOutput) throws IOException {
        return this.createGenerator(this._createDataOutputWrapper(dataOutput), JsonEncoding.UTF8);
    }
    
    @Deprecated
    public JsonGenerator createJsonGenerator(final OutputStream outputStream, final JsonEncoding jsonEncoding) throws IOException {
        return this.createGenerator(outputStream, jsonEncoding);
    }
    
    @Deprecated
    public JsonGenerator createJsonGenerator(final Writer writer) throws IOException {
        return this.createGenerator(writer);
    }
    
    @Deprecated
    public JsonGenerator createJsonGenerator(final OutputStream outputStream) throws IOException {
        return this.createGenerator(outputStream, JsonEncoding.UTF8);
    }
    
    protected JsonParser _createParser(final InputStream inputStream, final IOContext ioContext) throws IOException {
        return new ByteSourceJsonBootstrapper(ioContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
    }
    
    protected JsonParser _createParser(final Reader reader, final IOContext ioContext) throws IOException {
        return new ReaderBasedJsonParser(ioContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }
    
    protected JsonParser _createParser(final char[] array, final int n, final int n2, final IOContext ioContext, final boolean b) throws IOException {
        return new ReaderBasedJsonParser(ioContext, this._parserFeatures, null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), array, n, n + n2, b);
    }
    
    protected JsonParser _createParser(final byte[] array, final int n, final int n2, final IOContext ioContext) throws IOException {
        return new ByteSourceJsonBootstrapper(ioContext, array, n, n2).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
    }
    
    protected JsonParser _createParser(final DataInput dataInput, final IOContext ioContext) throws IOException {
        this._requireJSONFactory("InputData source not (yet?) support for this format (%s)");
        return new UTF8DataInputJsonParser(ioContext, this._parserFeatures, dataInput, this._objectCodec, this._byteSymbolCanonicalizer.makeChild(this._factoryFeatures), ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput));
    }
    
    protected JsonGenerator _createGenerator(final Writer writer, final IOContext ioContext) throws IOException {
        final WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(ioContext, this._generatorFeatures, this._objectCodec, writer);
        if (this._characterEscapes != null) {
            writerBasedJsonGenerator.setCharacterEscapes(this._characterEscapes);
        }
        final SerializableString rootValueSeparator = this._rootValueSeparator;
        if (rootValueSeparator != JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(rootValueSeparator);
        }
        return writerBasedJsonGenerator;
    }
    
    protected JsonGenerator _createUTF8Generator(final OutputStream outputStream, final IOContext ioContext) throws IOException {
        final UTF8JsonGenerator utf8JsonGenerator = new UTF8JsonGenerator(ioContext, this._generatorFeatures, this._objectCodec, outputStream);
        if (this._characterEscapes != null) {
            utf8JsonGenerator.setCharacterEscapes(this._characterEscapes);
        }
        final SerializableString rootValueSeparator = this._rootValueSeparator;
        if (rootValueSeparator != JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR) {
            utf8JsonGenerator.setRootValueSeparator(rootValueSeparator);
        }
        return utf8JsonGenerator;
    }
    
    protected Writer _createWriter(final OutputStream outputStream, final JsonEncoding jsonEncoding, final IOContext ioContext) throws IOException {
        if (jsonEncoding == JsonEncoding.UTF8) {
            return new UTF8Writer(ioContext, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }
    
    protected final InputStream _decorate(final InputStream inputStream, final IOContext ioContext) throws IOException {
        if (this._inputDecorator != null) {
            final InputStream decorate = this._inputDecorator.decorate(ioContext, inputStream);
            if (decorate != null) {
                return decorate;
            }
        }
        return inputStream;
    }
    
    protected final Reader _decorate(final Reader reader, final IOContext ioContext) throws IOException {
        if (this._inputDecorator != null) {
            final Reader decorate = this._inputDecorator.decorate(ioContext, reader);
            if (decorate != null) {
                return decorate;
            }
        }
        return reader;
    }
    
    protected final DataInput _decorate(final DataInput dataInput, final IOContext ioContext) throws IOException {
        if (this._inputDecorator != null) {
            final DataInput decorate = this._inputDecorator.decorate(ioContext, dataInput);
            if (decorate != null) {
                return decorate;
            }
        }
        return dataInput;
    }
    
    protected final OutputStream _decorate(final OutputStream outputStream, final IOContext ioContext) throws IOException {
        if (this._outputDecorator != null) {
            final OutputStream decorate = this._outputDecorator.decorate(ioContext, outputStream);
            if (decorate != null) {
                return decorate;
            }
        }
        return outputStream;
    }
    
    protected final Writer _decorate(final Writer writer, final IOContext ioContext) throws IOException {
        if (this._outputDecorator != null) {
            final Writer decorate = this._outputDecorator.decorate(ioContext, writer);
            if (decorate != null) {
                return decorate;
            }
        }
        return writer;
    }
    
    public BufferRecycler _getBufferRecycler() {
        if (Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.enabledIn(this._factoryFeatures)) {
            return BufferRecyclers.getBufferRecycler();
        }
        return new BufferRecycler();
    }
    
    protected IOContext _createContext(final Object o, final boolean b) {
        return new IOContext(this._getBufferRecycler(), o, b);
    }
    
    protected OutputStream _createDataOutputWrapper(final DataOutput dataOutput) {
        return new DataOutputAsStream(dataOutput);
    }
    
    protected InputStream _optimizedStreamFromURL(final URL url) throws IOException {
        if ("file".equals(url.getProtocol())) {
            final String host = url.getHost();
            if ((host == null || host.length() == 0) && url.getPath().indexOf(37) < 0) {
                return new FileInputStream(url.getPath());
            }
        }
        return url.openStream();
    }
    
    private final void _requireJSONFactory(final String s) {
        if (!this._isJSONFactory()) {
            throw new UnsupportedOperationException(String.format(s, this.getFormatName()));
        }
    }
    
    private final boolean _isJSONFactory() {
        return this.getFormatName() == "JSON";
    }
    
    static {
        DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
        DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
        DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
        DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }
}
