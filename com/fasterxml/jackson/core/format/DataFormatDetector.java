package com.fasterxml.jackson.core.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import com.fasterxml.jackson.core.JsonFactory;

public class DataFormatDetector
{
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final JsonFactory[] _detectors;
    protected final MatchStrength _optimalMatch;
    protected final MatchStrength _minimalMatch;
    protected final int _maxInputLookahead;
    
    public DataFormatDetector(final JsonFactory... array) {
        this(array, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }
    
    public DataFormatDetector(final Collection<JsonFactory> collection) {
        this((JsonFactory[])collection.<JsonFactory>toArray(new JsonFactory[collection.size()]));
    }
    
    public DataFormatDetector withOptimalMatch(final MatchStrength matchStrength) {
        if (matchStrength == this._optimalMatch) {
            return this;
        }
        return new DataFormatDetector(this._detectors, matchStrength, this._minimalMatch, this._maxInputLookahead);
    }
    
    public DataFormatDetector withMinimalMatch(final MatchStrength matchStrength) {
        if (matchStrength == this._minimalMatch) {
            return this;
        }
        return new DataFormatDetector(this._detectors, this._optimalMatch, matchStrength, this._maxInputLookahead);
    }
    
    public DataFormatDetector withMaxInputLookahead(final int n) {
        if (n == this._maxInputLookahead) {
            return this;
        }
        return new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, n);
    }
    
    private DataFormatDetector(final JsonFactory[] detectors, final MatchStrength optimalMatch, final MatchStrength minimalMatch, final int maxInputLookahead) {
        super();
        this._detectors = detectors;
        this._optimalMatch = optimalMatch;
        this._minimalMatch = minimalMatch;
        this._maxInputLookahead = maxInputLookahead;
    }
    
    public DataFormatMatcher findFormat(final InputStream inputStream) throws IOException {
        return this._findFormat(new InputAccessor.Std(inputStream, new byte[this._maxInputLookahead]));
    }
    
    public DataFormatMatcher findFormat(final byte[] array) throws IOException {
        return this._findFormat(new InputAccessor.Std(array));
    }
    
    public DataFormatMatcher findFormat(final byte[] array, final int n, final int n2) throws IOException {
        return this._findFormat(new InputAccessor.Std(array, n, n2));
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        final int length = this._detectors.length;
        if (length > 0) {
            sb.append(this._detectors[0].getFormatName());
            for (int i = 1; i < length; ++i) {
                sb.append(", ");
                sb.append(this._detectors[i].getFormatName());
            }
        }
        sb.append(']');
        return sb.toString();
    }
    
    private DataFormatMatcher _findFormat(final InputAccessor.Std std) throws IOException {
        JsonFactory jsonFactory = null;
        MatchStrength matchStrength = null;
        for (final JsonFactory jsonFactory2 : this._detectors) {
            std.reset();
            final MatchStrength hasFormat = jsonFactory2.hasFormat(std);
            if (hasFormat != null) {
                if (hasFormat.ordinal() >= this._minimalMatch.ordinal()) {
                    if (jsonFactory == null || matchStrength.ordinal() < hasFormat.ordinal()) {
                        jsonFactory = jsonFactory2;
                        matchStrength = hasFormat;
                        if (hasFormat.ordinal() >= this._optimalMatch.ordinal()) {
                            break;
                        }
                    }
                }
            }
        }
        return std.createMatcher(jsonFactory, matchStrength);
    }
}
