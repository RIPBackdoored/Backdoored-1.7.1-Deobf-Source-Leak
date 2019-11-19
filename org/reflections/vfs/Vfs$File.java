package org.reflections.vfs;

import java.io.IOException;
import java.io.InputStream;

public interface File
{
    String getName();
    
    String getRelativePath();
    
    InputStream openInputStream() throws IOException;
}
