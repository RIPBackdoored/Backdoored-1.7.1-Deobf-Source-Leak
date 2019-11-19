package com.sun.jna.win32;

import com.sun.jna.FunctionMapper;
import com.sun.jna.TypeMapper;
import java.util.HashMap;

static final class W32APIOptions$2 extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    
    W32APIOptions$2() {
        super();
    }
    
    {
        ((HashMap<String, TypeMapper>)this).put("type-mapper", W32APITypeMapper.ASCII);
        ((HashMap<String, FunctionMapper>)this).put("function-mapper", W32APIFunctionMapper.ASCII);
    }
}