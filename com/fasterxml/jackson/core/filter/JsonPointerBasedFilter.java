package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter extends TokenFilter
{
    protected final JsonPointer _pathToMatch;
    
    public JsonPointerBasedFilter(final String s) {
        this(JsonPointer.compile(s));
    }
    
    public JsonPointerBasedFilter(final JsonPointer pathToMatch) {
        super();
        this._pathToMatch = pathToMatch;
    }
    
    @Override
    public TokenFilter includeElement(final int n) {
        final JsonPointer matchElement = this._pathToMatch.matchElement(n);
        if (matchElement == null) {
            return null;
        }
        if (matchElement.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchElement);
    }
    
    @Override
    public TokenFilter includeProperty(final String s) {
        final JsonPointer matchProperty = this._pathToMatch.matchProperty(s);
        if (matchProperty == null) {
            return null;
        }
        if (matchProperty.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchProperty);
    }
    
    @Override
    public TokenFilter filterStartArray() {
        return this;
    }
    
    @Override
    public TokenFilter filterStartObject() {
        return this;
    }
    
    @Override
    protected boolean _includeScalar() {
        return this._pathToMatch.matches();
    }
    
    @Override
    public String toString() {
        return "[JsonPointerFilter at: " + this._pathToMatch + "]";
    }
}
