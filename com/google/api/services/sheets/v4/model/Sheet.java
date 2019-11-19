package com.google.api.services.sheets.v4.model;

import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import java.util.List;
import com.google.api.client.json.GenericJson;

public final class Sheet extends GenericJson
{
    @Key
    private List<BandedRange> bandedRanges;
    @Key
    private BasicFilter basicFilter;
    @Key
    private List<EmbeddedChart> charts;
    @Key
    private List<DimensionGroup> columnGroups;
    @Key
    private List<ConditionalFormatRule> conditionalFormats;
    @Key
    private List<GridData> data;
    @Key
    private List<DeveloperMetadata> developerMetadata;
    @Key
    private List<FilterView> filterViews;
    @Key
    private List<GridRange> merges;
    @Key
    private SheetProperties properties;
    @Key
    private List<ProtectedRange> protectedRanges;
    @Key
    private List<DimensionGroup> rowGroups;
    
    public Sheet() {
        super();
    }
    
    public List<BandedRange> getBandedRanges() {
        return this.bandedRanges;
    }
    
    public Sheet setBandedRanges(final List<BandedRange> bandedRanges) {
        this.bandedRanges = bandedRanges;
        return this;
    }
    
    public BasicFilter getBasicFilter() {
        return this.basicFilter;
    }
    
    public Sheet setBasicFilter(final BasicFilter basicFilter) {
        this.basicFilter = basicFilter;
        return this;
    }
    
    public List<EmbeddedChart> getCharts() {
        return this.charts;
    }
    
    public Sheet setCharts(final List<EmbeddedChart> charts) {
        this.charts = charts;
        return this;
    }
    
    public List<DimensionGroup> getColumnGroups() {
        return this.columnGroups;
    }
    
    public Sheet setColumnGroups(final List<DimensionGroup> columnGroups) {
        this.columnGroups = columnGroups;
        return this;
    }
    
    public List<ConditionalFormatRule> getConditionalFormats() {
        return this.conditionalFormats;
    }
    
    public Sheet setConditionalFormats(final List<ConditionalFormatRule> conditionalFormats) {
        this.conditionalFormats = conditionalFormats;
        return this;
    }
    
    public List<GridData> getData() {
        return this.data;
    }
    
    public Sheet setData(final List<GridData> data) {
        this.data = data;
        return this;
    }
    
    public List<DeveloperMetadata> getDeveloperMetadata() {
        return this.developerMetadata;
    }
    
    public Sheet setDeveloperMetadata(final List<DeveloperMetadata> developerMetadata) {
        this.developerMetadata = developerMetadata;
        return this;
    }
    
    public List<FilterView> getFilterViews() {
        return this.filterViews;
    }
    
    public Sheet setFilterViews(final List<FilterView> filterViews) {
        this.filterViews = filterViews;
        return this;
    }
    
    public List<GridRange> getMerges() {
        return this.merges;
    }
    
    public Sheet setMerges(final List<GridRange> merges) {
        this.merges = merges;
        return this;
    }
    
    public SheetProperties getProperties() {
        return this.properties;
    }
    
    public Sheet setProperties(final SheetProperties properties) {
        this.properties = properties;
        return this;
    }
    
    public List<ProtectedRange> getProtectedRanges() {
        return this.protectedRanges;
    }
    
    public Sheet setProtectedRanges(final List<ProtectedRange> protectedRanges) {
        this.protectedRanges = protectedRanges;
        return this;
    }
    
    public List<DimensionGroup> getRowGroups() {
        return this.rowGroups;
    }
    
    public Sheet setRowGroups(final List<DimensionGroup> rowGroups) {
        this.rowGroups = rowGroups;
        return this;
    }
    
    @Override
    public Sheet set(final String s, final Object o) {
        return (Sheet)super.set(s, o);
    }
    
    @Override
    public Sheet clone() {
        return (Sheet)super.clone();
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
        Data.<Object>nullOf(EmbeddedChart.class);
        Data.<Object>nullOf(GridData.class);
    }
}
