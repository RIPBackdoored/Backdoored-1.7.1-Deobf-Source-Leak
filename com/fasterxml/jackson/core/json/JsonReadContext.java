package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonStreamContext;

public final class JsonReadContext extends JsonStreamContext
{
    protected final JsonReadContext _parent;
    protected DupDetector _dups;
    protected JsonReadContext _child;
    protected String _currentName;
    protected Object _currentValue;
    protected int _lineNr;
    protected int _columnNr;
    
    public JsonReadContext(final JsonReadContext parent, final DupDetector dups, final int type, final int lineNr, final int columnNr) {
        super();
        this._parent = parent;
        this._dups = dups;
        this._type = type;
        this._lineNr = lineNr;
        this._columnNr = columnNr;
        this._index = -1;
    }
    
    protected void reset(final int type, final int lineNr, final int columnNr) {
        this._type = type;
        this._index = -1;
        this._lineNr = lineNr;
        this._columnNr = columnNr;
        this._currentName = null;
        this._currentValue = null;
        if (this._dups != null) {
            this._dups.reset();
        }
    }
    
    public JsonReadContext withDupDetector(final DupDetector dups) {
        this._dups = dups;
        return this;
    }
    
    @Override
    public Object getCurrentValue() {
        return this._currentValue;
    }
    
    @Override
    public void setCurrentValue(final Object currentValue) {
        this._currentValue = currentValue;
    }
    
    public static JsonReadContext createRootContext(final int n, final int n2, final DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, n, n2);
    }
    
    public static JsonReadContext createRootContext(final DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, 1, 0);
    }
    
    public JsonReadContext createChildArrayContext(final int n, final int n2) {
        JsonReadContext child = this._child;
        if (child == null) {
            child = (this._child = new JsonReadContext(this, (this._dups == null) ? null : this._dups.child(), 1, n, n2));
        }
        else {
            child.reset(1, n, n2);
        }
        return child;
    }
    
    public JsonReadContext createChildObjectContext(final int n, final int n2) {
        final JsonReadContext child = this._child;
        if (child == null) {
            return this._child = new JsonReadContext(this, (this._dups == null) ? null : this._dups.child(), 2, n, n2);
        }
        child.reset(2, n, n2);
        return child;
    }
    
    @Override
    public String getCurrentName() {
        return this._currentName;
    }
    
    @Override
    public boolean hasCurrentName() {
        return this._currentName != null;
    }
    
    @Override
    public JsonReadContext getParent() {
        return this._parent;
    }
    
    @Override
    public JsonLocation getStartLocation(final Object o) {
        return new JsonLocation(o, -1L, this._lineNr, this._columnNr);
    }
    
    public JsonReadContext clearAndGetParent() {
        this._currentValue = null;
        return this._parent;
    }
    
    public DupDetector getDupDetector() {
        return this._dups;
    }
    
    public boolean expectComma() {
        final int n = ++this._index;
        return this._type != 0 && n > 0;
    }
    
    public void setCurrentName(final String currentName) throws JsonProcessingException {
        this._currentName = currentName;
        if (this._dups != null) {
            this._checkDup(this._dups, currentName);
        }
    }
    
    private void _checkDup(final DupDetector dupDetector, final String s) throws JsonProcessingException {
        if (dupDetector.isDup(s)) {
            final Object source = dupDetector.getSource();
            throw new JsonParseException((source instanceof JsonParser) ? ((JsonParser)source) : null, "Duplicate field '" + s + "'");
        }
    }
    
    @Override
    public /* bridge */ JsonStreamContext getParent() {
        return this.getParent();
    }
}
