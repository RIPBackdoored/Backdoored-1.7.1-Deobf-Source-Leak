package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class ManualRule extends GenericJson
{
    @Key
    private List<ManualRuleGroup> groups;
    
    public ManualRule() {
        super();
    }
    
    public List<ManualRuleGroup> getGroups() {
        return this.groups;
    }
    
    public ManualRule setGroups(final List<ManualRuleGroup> groups) {
        this.groups = groups;
        return this;
    }
    
    @Override
    public ManualRule set(final String s, final Object o) {
        return (ManualRule)super.set(s, o);
    }
    
    @Override
    public ManualRule clone() {
        return (ManualRule)super.clone();
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
