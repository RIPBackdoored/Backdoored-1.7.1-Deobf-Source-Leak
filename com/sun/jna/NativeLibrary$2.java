package com.sun.jna;

import java.io.File;
import java.io.FilenameFilter;

static final class NativeLibrary$2 implements FilenameFilter {
    final /* synthetic */ String val$libName;
    
    NativeLibrary$2(final String val$libName) {
        this.val$libName = val$libName;
        super();
    }
    
    @Override
    public boolean accept(final File file, final String s) {
        return (s.startsWith("lib" + this.val$libName + ".so") || (s.startsWith(this.val$libName + ".so") && this.val$libName.startsWith("lib"))) && NativeLibrary.access$000(s);
    }
}