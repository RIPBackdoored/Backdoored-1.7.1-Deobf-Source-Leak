package javassist.bytecode;

import java.io.IOException;
import java.io.DataOutputStream;

public interface AttributeWriter
{
    int size();
    
    void write(final DataOutputStream p0) throws IOException;
}
