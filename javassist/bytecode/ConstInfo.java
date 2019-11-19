package javassist.bytecode;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.DataOutputStream;
import java.util.Map;
import java.util.HashMap;

abstract class ConstInfo
{
    int index;
    
    public ConstInfo(final int index) {
        super();
        this.index = index;
    }
    
    public abstract int getTag();
    
    public String getClassName(final ConstPool constPool) {
        return null;
    }
    
    public void renameClass(final ConstPool constPool, final String s, final String s2, final HashMap hashMap) {
    }
    
    public void renameClass(final ConstPool constPool, final Map map, final HashMap hashMap) {
    }
    
    public abstract int copy(final ConstPool p0, final ConstPool p1, final Map p2);
    
    public abstract void write(final DataOutputStream p0) throws IOException;
    
    public abstract void print(final PrintWriter p0);
    
    @Override
    public String toString() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.print(new PrintWriter(byteArrayOutputStream));
        return byteArrayOutputStream.toString();
    }
}
