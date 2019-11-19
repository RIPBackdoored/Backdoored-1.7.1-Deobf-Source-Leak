package javassist.bytecode;

import java.util.Map;
import java.io.IOException;
import java.io.DataInputStream;

public class BootstrapMethodsAttribute extends AttributeInfo
{
    public static final String tag = "BootstrapMethods";
    
    BootstrapMethodsAttribute(final ConstPool constPool, final int n, final DataInputStream dataInputStream) throws IOException {
        super(constPool, n, dataInputStream);
    }
    
    public BootstrapMethodsAttribute(final ConstPool constPool, final BootstrapMethod[] array) {
        super(constPool, "BootstrapMethods");
        int n = 2;
        for (int i = 0; i < array.length; ++i) {
            n += 4 + array[i].arguments.length * 2;
        }
        final byte[] array2 = new byte[n];
        ByteArray.write16bit(array.length, array2, 0);
        int n2 = 2;
        for (int j = 0; j < array.length; ++j) {
            ByteArray.write16bit(array[j].methodRef, array2, n2);
            ByteArray.write16bit(array[j].arguments.length, array2, n2 + 2);
            final int[] arguments = array[j].arguments;
            n2 += 4;
            for (int k = 0; k < arguments.length; ++k) {
                ByteArray.write16bit(arguments[k], array2, n2);
                n2 += 2;
            }
        }
        this.set(array2);
    }
    
    public BootstrapMethod[] getMethods() {
        final byte[] value = this.get();
        final int u16bit = ByteArray.readU16bit(value, 0);
        final BootstrapMethod[] array = new BootstrapMethod[u16bit];
        int n = 2;
        for (int i = 0; i < u16bit; ++i) {
            final int u16bit2 = ByteArray.readU16bit(value, n);
            final int u16bit3 = ByteArray.readU16bit(value, n + 2);
            final int[] array2 = new int[u16bit3];
            n += 4;
            for (int j = 0; j < u16bit3; ++j) {
                array2[j] = ByteArray.readU16bit(value, n);
                n += 2;
            }
            array[i] = new BootstrapMethod(u16bit2, array2);
        }
        return array;
    }
    
    @Override
    public AttributeInfo copy(final ConstPool constPool, final Map map) {
        final BootstrapMethod[] methods = this.getMethods();
        final ConstPool constPool2 = this.getConstPool();
        for (int i = 0; i < methods.length; ++i) {
            final BootstrapMethod bootstrapMethod = methods[i];
            bootstrapMethod.methodRef = constPool2.copy(bootstrapMethod.methodRef, constPool, map);
            for (int j = 0; j < bootstrapMethod.arguments.length; ++j) {
                bootstrapMethod.arguments[j] = constPool2.copy(bootstrapMethod.arguments[j], constPool, map);
            }
        }
        return new BootstrapMethodsAttribute(constPool, methods);
    }
}
