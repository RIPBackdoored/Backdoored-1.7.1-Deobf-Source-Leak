package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.json.GenericJson;

public final class CandlestickData extends GenericJson
{
    @Key
    private CandlestickSeries closeSeries;
    @Key
    private CandlestickSeries highSeries;
    @Key
    private CandlestickSeries lowSeries;
    @Key
    private CandlestickSeries openSeries;
    
    public CandlestickData() {
        super();
    }
    
    public CandlestickSeries getCloseSeries() {
        return this.closeSeries;
    }
    
    public CandlestickData setCloseSeries(final CandlestickSeries closeSeries) {
        this.closeSeries = closeSeries;
        return this;
    }
    
    public CandlestickSeries getHighSeries() {
        return this.highSeries;
    }
    
    public CandlestickData setHighSeries(final CandlestickSeries highSeries) {
        this.highSeries = highSeries;
        return this;
    }
    
    public CandlestickSeries getLowSeries() {
        return this.lowSeries;
    }
    
    public CandlestickData setLowSeries(final CandlestickSeries lowSeries) {
        this.lowSeries = lowSeries;
        return this;
    }
    
    public CandlestickSeries getOpenSeries() {
        return this.openSeries;
    }
    
    public CandlestickData setOpenSeries(final CandlestickSeries openSeries) {
        this.openSeries = openSeries;
        return this;
    }
    
    @Override
    public CandlestickData set(final String s, final Object o) {
        return (CandlestickData)super.set(s, o);
    }
    
    @Override
    public CandlestickData clone() {
        return (CandlestickData)super.clone();
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
