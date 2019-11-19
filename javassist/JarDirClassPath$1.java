package javassist;

import java.io.File;
import java.io.FilenameFilter;

class JarDirClassPath$1 implements FilenameFilter {
    final /* synthetic */ JarDirClassPath this$0;
    
    JarDirClassPath$1(final JarDirClassPath this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public boolean accept(final File file, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        return lowerCase.endsWith(".jar") || lowerCase.endsWith(".zip");
    }
}