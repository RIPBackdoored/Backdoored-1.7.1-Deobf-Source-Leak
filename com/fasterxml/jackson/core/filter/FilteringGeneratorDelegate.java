package com.fasterxml.jackson.core.filter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.InputStream;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;

public class FilteringGeneratorDelegate extends JsonGeneratorDelegate
{
    protected TokenFilter rootFilter;
    protected boolean _allowMultipleMatches;
    protected boolean _includePath;
    @Deprecated
    protected boolean _includeImmediateParent;
    protected TokenFilterContext _filterContext;
    protected TokenFilter _itemFilter;
    protected int _matchCount;
    
    public FilteringGeneratorDelegate(final JsonGenerator jsonGenerator, final TokenFilter tokenFilter, final boolean includePath, final boolean allowMultipleMatches) {
        super(jsonGenerator, false);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._filterContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = includePath;
        this._allowMultipleMatches = allowMultipleMatches;
    }
    
    public TokenFilter getFilter() {
        return this.rootFilter;
    }
    
    public JsonStreamContext getFilterContext() {
        return this._filterContext;
    }
    
    public int getMatchCount() {
        return this._matchCount;
    }
    
    @Override
    public JsonStreamContext getOutputContext() {
        return this._filterContext;
    }
    
    @Override
    public void writeStartArray() throws IOException {
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext(null, false);
            return;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray();
            return;
        }
        this._itemFilter = this._filterContext.checkValue(this._itemFilter);
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext(null, false);
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            this._itemFilter = this._itemFilter.filterStartArray();
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray();
        }
        else {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, false);
        }
    }
    
    @Override
    public void writeStartArray(final int n) throws IOException {
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext(null, false);
            return;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(n);
            return;
        }
        this._itemFilter = this._filterContext.checkValue(this._itemFilter);
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext(null, false);
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            this._itemFilter = this._itemFilter.filterStartArray();
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(n);
        }
        else {
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, false);
        }
    }
    
    @Override
    public void writeEndArray() throws IOException {
        this._filterContext = this._filterContext.closeArray(this.delegate);
        if (this._filterContext != null) {
            this._itemFilter = this._filterContext.getFilter();
        }
    }
    
    @Override
    public void writeStartObject() throws IOException {
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, false);
            return;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, true);
            this.delegate.writeStartObject();
            return;
        }
        TokenFilter tokenFilter = this._filterContext.checkValue(this._itemFilter);
        if (tokenFilter == null) {
            return;
        }
        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
            tokenFilter = tokenFilter.filterStartObject();
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            this._checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, true);
            this.delegate.writeStartObject();
        }
        else {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, false);
        }
    }
    
    @Override
    public void writeStartObject(final Object o) throws IOException {
        if (this._itemFilter == null) {
            this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, false);
            return;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._filterContext = this._filterContext.createChildObjectContext(this._itemFilter, true);
            this.delegate.writeStartObject(o);
            return;
        }
        TokenFilter tokenFilter = this._filterContext.checkValue(this._itemFilter);
        if (tokenFilter == null) {
            return;
        }
        if (tokenFilter != TokenFilter.INCLUDE_ALL) {
            tokenFilter = tokenFilter.filterStartObject();
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            this._checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, true);
            this.delegate.writeStartObject(o);
        }
        else {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, false);
        }
    }
    
    @Override
    public void writeEndObject() throws IOException {
        this._filterContext = this._filterContext.closeObject(this.delegate);
        if (this._filterContext != null) {
            this._itemFilter = this._filterContext.getFilter();
        }
    }
    
    @Override
    public void writeFieldName(final String fieldName) throws IOException {
        final TokenFilter setFieldName = this._filterContext.setFieldName(fieldName);
        if (setFieldName == null) {
            this._itemFilter = null;
            return;
        }
        if (setFieldName == TokenFilter.INCLUDE_ALL) {
            this._itemFilter = setFieldName;
            this.delegate.writeFieldName(fieldName);
            return;
        }
        if ((this._itemFilter = setFieldName.includeProperty(fieldName)) == TokenFilter.INCLUDE_ALL) {
            this._checkPropertyParentPath();
        }
    }
    
    @Override
    public void writeFieldName(final SerializableString serializableString) throws IOException {
        final TokenFilter setFieldName = this._filterContext.setFieldName(serializableString.getValue());
        if (setFieldName == null) {
            this._itemFilter = null;
            return;
        }
        if (setFieldName == TokenFilter.INCLUDE_ALL) {
            this._itemFilter = setFieldName;
            this.delegate.writeFieldName(serializableString);
            return;
        }
        if ((this._itemFilter = setFieldName.includeProperty(serializableString.getValue())) == TokenFilter.INCLUDE_ALL) {
            this._checkPropertyParentPath();
        }
    }
    
    @Override
    public void writeString(final String s) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeString(s)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeString(s);
    }
    
    @Override
    public void writeString(final char[] array, final int n, final int n2) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final String s = new String(array, n, n2);
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeString(s)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeString(array, n, n2);
    }
    
    @Override
    public void writeString(final SerializableString serializableString) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeString(serializableString.getValue())) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeString(serializableString);
    }
    
    @Override
    public void writeRawUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRawUTF8String(array, n, n2);
        }
    }
    
    @Override
    public void writeUTF8String(final byte[] array, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeUTF8String(array, n, n2);
        }
    }
    
    @Override
    public void writeRaw(final String s) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(s);
        }
    }
    
    @Override
    public void writeRaw(final String s, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(s);
        }
    }
    
    @Override
    public void writeRaw(final SerializableString serializableString) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(serializableString);
        }
    }
    
    @Override
    public void writeRaw(final char[] array, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(array, n, n2);
        }
    }
    
    @Override
    public void writeRaw(final char c) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(c);
        }
    }
    
    @Override
    public void writeRawValue(final String s) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(s);
        }
    }
    
    @Override
    public void writeRawValue(final String s, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(s, n, n2);
        }
    }
    
    @Override
    public void writeRawValue(final char[] array, final int n, final int n2) throws IOException {
        if (this._checkRawValueWrite()) {
            this.delegate.writeRaw(array, n, n2);
        }
    }
    
    @Override
    public void writeBinary(final Base64Variant base64Variant, final byte[] array, final int n, final int n2) throws IOException {
        if (this._checkBinaryWrite()) {
            this.delegate.writeBinary(base64Variant, array, n, n2);
        }
    }
    
    @Override
    public int writeBinary(final Base64Variant base64Variant, final InputStream inputStream, final int n) throws IOException {
        if (this._checkBinaryWrite()) {
            return this.delegate.writeBinary(base64Variant, inputStream, n);
        }
        return -1;
    }
    
    @Override
    public void writeNumber(final short n) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(n)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final int n) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(n)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final long n) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(n)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigInteger bigInteger) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(bigInteger)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(bigInteger);
    }
    
    @Override
    public void writeNumber(final double n) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(n)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final float n) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(n)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(n);
    }
    
    @Override
    public void writeNumber(final BigDecimal bigDecimal) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNumber(bigDecimal)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(bigDecimal);
    }
    
    @Override
    public void writeNumber(final String s) throws IOException, UnsupportedOperationException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeRawValue()) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNumber(s);
    }
    
    @Override
    public void writeBoolean(final boolean b) throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeBoolean(b)) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeBoolean(b);
    }
    
    @Override
    public void writeNull() throws IOException {
        if (this._itemFilter == null) {
            return;
        }
        if (this._itemFilter != TokenFilter.INCLUDE_ALL) {
            final TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != TokenFilter.INCLUDE_ALL && !checkValue.includeNull()) {
                return;
            }
            this._checkParentPath();
        }
        this.delegate.writeNull();
    }
    
    @Override
    public void writeOmittedField(final String s) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeOmittedField(s);
        }
    }
    
    @Override
    public void writeObjectId(final Object o) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeObjectId(o);
        }
    }
    
    @Override
    public void writeObjectRef(final Object o) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeObjectRef(o);
        }
    }
    
    @Override
    public void writeTypeId(final Object o) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeTypeId(o);
        }
    }
    
    protected void _checkParentPath() throws IOException {
        ++this._matchCount;
        if (this._includePath) {
            this._filterContext.writePath(this.delegate);
        }
        if (!this._allowMultipleMatches) {
            this._filterContext.skipParentChecks();
        }
    }
    
    protected void _checkPropertyParentPath() throws IOException {
        ++this._matchCount;
        if (this._includePath) {
            this._filterContext.writePath(this.delegate);
        }
        else if (this._includeImmediateParent) {
            this._filterContext.writeImmediatePath(this.delegate);
        }
        if (!this._allowMultipleMatches) {
            this._filterContext.skipParentChecks();
        }
    }
    
    protected boolean _checkBinaryWrite() throws IOException {
        if (this._itemFilter == null) {
            return false;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (this._itemFilter.includeBinary()) {
            this._checkParentPath();
            return true;
        }
        return false;
    }
    
    protected boolean _checkRawValueWrite() throws IOException {
        if (this._itemFilter == null) {
            return false;
        }
        if (this._itemFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (this._itemFilter.includeRawValue()) {
            this._checkParentPath();
            return true;
        }
        return false;
    }
}
