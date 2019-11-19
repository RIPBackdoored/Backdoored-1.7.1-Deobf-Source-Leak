package com.sun.jna;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

private static class LayoutInfo
{
    private int size;
    private int alignment;
    private final Map<String, StructField> fields;
    private int alignType;
    private TypeMapper typeMapper;
    private boolean variable;
    private StructField typeInfoField;
    
    private LayoutInfo() {
        super();
        this.size = -1;
        this.alignment = 1;
        this.fields = Collections.<String, StructField>synchronizedMap(new LinkedHashMap<String, StructField>());
        this.alignType = 0;
    }
    
    static /* synthetic */ boolean access$000(final LayoutInfo layoutInfo) {
        return layoutInfo.variable;
    }
    
    static /* synthetic */ int access$100(final LayoutInfo layoutInfo) {
        return layoutInfo.size;
    }
    
    static /* synthetic */ int access$200(final LayoutInfo layoutInfo) {
        return layoutInfo.alignType;
    }
    
    static /* synthetic */ TypeMapper access$300(final LayoutInfo layoutInfo) {
        return layoutInfo.typeMapper;
    }
    
    static /* synthetic */ int access$400(final LayoutInfo layoutInfo) {
        return layoutInfo.alignment;
    }
    
    static /* synthetic */ Map access$500(final LayoutInfo layoutInfo) {
        return layoutInfo.fields;
    }
    
    LayoutInfo(final Structure$1 threadLocal) {
        this();
    }
    
    static /* synthetic */ int access$202(final LayoutInfo layoutInfo, final int alignType) {
        return layoutInfo.alignType = alignType;
    }
    
    static /* synthetic */ TypeMapper access$302(final LayoutInfo layoutInfo, final TypeMapper typeMapper) {
        return layoutInfo.typeMapper = typeMapper;
    }
    
    static /* synthetic */ boolean access$002(final LayoutInfo layoutInfo, final boolean variable) {
        return layoutInfo.variable = variable;
    }
    
    static /* synthetic */ int access$402(final LayoutInfo layoutInfo, final int alignment) {
        return layoutInfo.alignment = alignment;
    }
    
    static /* synthetic */ StructField access$700(final LayoutInfo layoutInfo) {
        return layoutInfo.typeInfoField;
    }
    
    static /* synthetic */ StructField access$702(final LayoutInfo layoutInfo, final StructField typeInfoField) {
        return layoutInfo.typeInfoField = typeInfoField;
    }
    
    static /* synthetic */ int access$102(final LayoutInfo layoutInfo, final int size) {
        return layoutInfo.size = size;
    }
}
