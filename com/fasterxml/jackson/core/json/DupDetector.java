package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;

public class DupDetector
{
    protected final Object _source;
    protected String _firstName;
    protected String _secondName;
    protected HashSet<String> _seen;
    
    private DupDetector(final Object source) {
        super();
        this._source = source;
    }
    
    public static DupDetector rootDetector(final JsonParser jsonParser) {
        return new DupDetector(jsonParser);
    }
    
    public static DupDetector rootDetector(final JsonGenerator jsonGenerator) {
        return new DupDetector(jsonGenerator);
    }
    
    public DupDetector child() {
        return new DupDetector(this._source);
    }
    
    public void reset() {
        this._firstName = null;
        this._secondName = null;
        this._seen = null;
    }
    
    public JsonLocation findLocation() {
        if (this._source instanceof JsonParser) {
            return ((JsonParser)this._source).getCurrentLocation();
        }
        return null;
    }
    
    public Object getSource() {
        return this._source;
    }
    
    public boolean isDup(final String s) throws JsonParseException {
        if (this._firstName == null) {
            this._firstName = s;
            return false;
        }
        if (s.equals(this._firstName)) {
            return true;
        }
        if (this._secondName == null) {
            this._secondName = s;
            return false;
        }
        if (s.equals(this._secondName)) {
            return true;
        }
        if (this._seen == null) {
            (this._seen = new HashSet<String>(16)).add(this._firstName);
            this._seen.add(this._secondName);
        }
        return !this._seen.add(s);
    }
}
