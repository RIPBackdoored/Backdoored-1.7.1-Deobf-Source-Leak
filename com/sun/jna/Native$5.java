package com.sun.jna;

import java.io.File;
import java.io.FilenameFilter;

static final class Native$5 implements FilenameFilter {
    Native$5() {
        super();
    }
    
    @Override
    public boolean accept(final File file, final String s) {
        return s.endsWith(".x") && s.startsWith("jna");
    }
}