package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class SyntheticAttribute extends AttributeInfo
{
    public static final String tag = "Synthetic";
    
    SyntheticAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public SyntheticAttribute(final ConstPool constPool) {
        super(constPool, "Synthetic", new byte[0]);
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        return new SyntheticAttribute(constPool);
    }
}
