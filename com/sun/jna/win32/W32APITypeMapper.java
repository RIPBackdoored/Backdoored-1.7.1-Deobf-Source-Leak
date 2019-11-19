package com.sun.jna.win32;

import com.sun.jna.ToNativeConverter;
import com.sun.jna.FromNativeContext;
import com.sun.jna.WString;
import com.sun.jna.StringArray;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.DefaultTypeMapper;

public class W32APITypeMapper extends DefaultTypeMapper
{
    public static final TypeMapper UNICODE;
    public static final TypeMapper ASCII;
    public static final TypeMapper DEFAULT;
    
    protected W32APITypeMapper(final boolean b) {
        super();
        if (b) {
            final TypeConverter typeConverter = new TypeConverter() {
                final /* synthetic */ W32APITypeMapper this$0;
                
                W32APITypeMapper$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Object toNative(final Object o, final ToNativeContext toNativeContext) {
                    if (o == null) {
                        return null;
                    }
                    if (o instanceof String[]) {
                        return new StringArray((String[])o, true);
                    }
                    return new WString(o.toString());
                }
                
                @Override
                public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
                    if (o == null) {
                        return null;
                    }
                    return o.toString();
                }
                
                @Override
                public Class<?> nativeType() {
                    return WString.class;
                }
            };
            this.addTypeConverter(String.class, typeConverter);
            this.addToNativeConverter(String[].class, typeConverter);
        }
        this.addTypeConverter(Boolean.class, new TypeConverter() {
            final /* synthetic */ W32APITypeMapper this$0;
            
            W32APITypeMapper$2() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public Object toNative(final Object o, final ToNativeContext toNativeContext) {
                return Boolean.TRUE.equals(o) ? 1 : 0;
            }
            
            @Override
            public Object fromNative(final Object o, final FromNativeContext fromNativeContext) {
                return ((int)o != 0) ? Boolean.TRUE : Boolean.FALSE;
            }
            
            @Override
            public Class<?> nativeType() {
                return Integer.class;
            }
        });
    }
    
    static {
        UNICODE = new W32APITypeMapper(true);
        ASCII = new W32APITypeMapper(false);
        DEFAULT = (Boolean.getBoolean("w32.ascii") ? W32APITypeMapper.ASCII : W32APITypeMapper.UNICODE);
    }
}
