package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class AddConditionalFormatRuleRequest extends GenericJson
{
    @Key
    private Integer index;
    @Key
    private ConditionalFormatRule rule;
    
    public AddConditionalFormatRuleRequest() {
        super();
    }
    
    public Integer getIndex() {
        return this.index;
    }
    
    public AddConditionalFormatRuleRequest setIndex(final Integer index) {
        this.index = index;
        return this;
    }
    
    public ConditionalFormatRule getRule() {
        return this.rule;
    }
    
    public AddConditionalFormatRuleRequest setRule(final ConditionalFormatRule rule) {
        this.rule = rule;
        return this;
    }
    
    @Override
    public AddConditionalFormatRuleRequest set(final String s, final Object o) {
        return (AddConditionalFormatRuleRequest)super.set(s, o);
    }
    
    @Override
    public AddConditionalFormatRuleRequest clone() {
        return (AddConditionalFormatRuleRequest)super.clone();
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
