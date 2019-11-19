package javassist.bytecode;

import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.DataInputStream;

class MethodTypeInfo extends ConstInfo
{
    static final int tag = 16;
    int descriptor;
    
    public MethodTypeInfo(final int descriptor, final int n) {
        super(n);
        this.descriptor = descriptor;
    }
    
    public MethodTypeInfo(final DataInputStream dataInputStream, final int n) throws IOException {
        super(n);
        this.descriptor = dataInputStream.readUnsignedShort();
    }
    
    @Override
    public int hashCode() {
        return this.descriptor;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof MethodTypeInfo && ((MethodTypeInfo)o).descriptor == this.descriptor;
    }
    
    @Override
    public int getTag() {
        return 16;
    }
    
    @Override
    public void renameClass(final ConstPool constPool, final String s, final String s2, final HashMap hashMap) {
        final String utf8Info = constPool.getUtf8Info(this.descriptor);
        final String rename = Descriptor.rename(utf8Info, s, s2);
        if (utf8Info != rename) {
            if (hashMap == null) {
                this.descriptor = constPool.addUtf8Info(rename);
            }
            else {
                hashMap.remove(this);
                this.descriptor = constPool.addUtf8Info(rename);
                hashMap.put(this, this);
            }
        }
    }
    
    @Override
    public void renameClass(final ConstPool constPool, final Map map, final HashMap hashMap) {
        final String utf8Info = constPool.getUtf8Info(this.descriptor);
        final String rename = Descriptor.rename(utf8Info, map);
        if (utf8Info != rename) {
            if (hashMap == null) {
                this.descriptor = constPool.addUtf8Info(rename);
            }
            else {
                hashMap.remove(this);
                this.descriptor = constPool.addUtf8Info(rename);
                hashMap.put(this, this);
            }
        }
    }
    
    @Override
    public int copy(final ConstPool constPool, final ConstPool constPool2, final Map map) {
        return constPool2.addMethodTypeInfo(constPool2.addUtf8Info(Descriptor.rename(constPool.getUtf8Info(this.descriptor), map)));
    }
    
    @Override
    public void write(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(16);
        dataOutputStream.writeShort(this.descriptor);
    }
    
    @Override
    public void print(final PrintWriter printWriter) {
        printWriter.print("MethodType #");
        printWriter.println(this.descriptor);
    }
}
