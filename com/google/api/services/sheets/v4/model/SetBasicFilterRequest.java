package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class SetBasicFilterRequest extends GenericJson
{
    @Key
    private BasicFilter filter;
    
    public SetBasicFilterRequest() {
        super();
    }
    
    public BasicFilter getFilter() {
        return this.filter;
    }
    
    public SetBasicFilterRequest setFilter(final BasicFilter filter) {
        this.filter = filter;
        return this;
    }
    
    @Override
    public SetBasicFilterRequest set(final String s, final Object o) {
        return (SetBasicFilterRequest)super.set(s, o);
    }
    
    @Override
    public SetBasicFilterRequest clone() {
        return (SetBasicFilterRequest)super.clone();
    }
    
    @Override
    public /* bridge */ GenericJson set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    @Override
    public /* bridge */ GenericJson clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData clone() {
        return this.clone();
    }
    
    @Override
    public /* bridge */ GenericData set(final String s, final Object o) {
        return this.set(s, o);
    }
    
    public /* bridge */ Object clone() throws CloneNotSupportedException {
        return this.clone();
    }
}
