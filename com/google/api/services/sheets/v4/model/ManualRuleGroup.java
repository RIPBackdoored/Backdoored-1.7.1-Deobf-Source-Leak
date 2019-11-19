package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class ManualRuleGroup extends GenericJson
{
    @Key
    private ExtendedValue groupName;
    @Key
    private List<ExtendedValue> items;
    
    public ManualRuleGroup() {
        super();
    }
    
    public ExtendedValue getGroupName() {
        return this.groupName;
    }
    
    public ManualRuleGroup setGroupName(final ExtendedValue groupName) {
        this.groupName = groupName;
        return this;
    }
    
    public List<ExtendedValue> getItems() {
        return this.items;
    }
    
    public ManualRuleGroup setItems(final List<ExtendedValue> items) {
        this.items = items;
        return this;
    }
    
    @Override
    public ManualRuleGroup set(final String s, final Object o) {
        return (ManualRuleGroup)super.set(s, o);
    }
    
    @Override
    public ManualRuleGroup clone() {
        return (ManualRuleGroup)super.clone();
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
    
    static {
        Data.<Object>nullOf(ExtendedValue.class);
    }
}
