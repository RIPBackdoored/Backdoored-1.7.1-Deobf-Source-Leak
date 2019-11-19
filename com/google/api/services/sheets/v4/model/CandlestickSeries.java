package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class CandlestickSeries extends GenericJson
{
    @Key
    private ChartData data;
    
    public CandlestickSeries() {
        super();
    }
    
    public ChartData getData() {
        return this.data;
    }
    
    public CandlestickSeries setData(final ChartData data) {
        this.data = data;
        return this;
    }
    
    @Override
    public CandlestickSeries set(final String s, final Object o) {
        return (CandlestickSeries)super.set(s, o);
    }
    
    @Override
    public CandlestickSeries clone() {
        return (CandlestickSeries)super.clone();
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
