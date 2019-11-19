package javassist;

import javassist.bytecode.Descriptor;
import javassist.bytecode.ClassFile;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Comparator;
import java.util.Arrays;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class SerialVersionUID
{
    public SerialVersionUID() {
        super();
    }
    
    public static void setSerialVersionUID(final CtClass ctClass) throws CannotCompileException, NotFoundException {
        try {
            ctClass.getDeclaredField("serialVersionUID");
        }
        catch (NotFoundException ex) {
            if (!isSerializable(ctClass)) {
                return;
            }
            final CtField ctField = new CtField(CtClass.longType, "serialVersionUID", ctClass);
            ctField.setModifiers(26);
            ctClass.addField(ctField, calculateDefault(ctClass) + "L");
        }
    }
    
    private static boolean isSerializable(final CtClass ctClass) throws NotFoundException {
        return ctClass.subtypeOf(ctClass.getClassPool().get("java.io.Serializable"));
    }
    
    public static long calculateDefault(final CtClass ctClass) throws CannotCompileException {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            final ClassFile classFile = ctClass.getClassFile();
            dataOutputStream.writeUTF(javaName(ctClass));
            final CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            int modifiers = ctClass.getModifiers();
            if ((modifiers & 0x200) != 0x0) {
                if (declaredMethods.length > 0) {
                    modifiers |= 0x400;
                }
                else {
                    modifiers &= 0xFFFFFBFF;
                }
            }
            dataOutputStream.writeInt(modifiers);
            final String[] interfaces = classFile.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                interfaces[i] = javaName(interfaces[i]);
            }
            Arrays.sort(interfaces);
            for (int j = 0; j < interfaces.length; ++j) {
                dataOutputStream.writeUTF(interfaces[j]);
            }
            final CtField[] declaredFields = ctClass.getDeclaredFields();
            Arrays.<CtField>sort(declaredFields, new Comparator() {
                SerialVersionUID$1() {
                    super();
                }
                
                @Override
                public int compare(final Object o, final Object o2) {
                    return ((CtField)o).getName().compareTo(((CtField)o2).getName());
                }
            });
            for (int k = 0; k < declaredFields.length; ++k) {
                final CtField ctField = declaredFields[k];
                final int modifiers2 = ctField.getModifiers();
                if ((modifiers2 & 0x2) == 0x0 || (modifiers2 & 0x88) == 0x0) {
                    dataOutputStream.writeUTF(ctField.getName());
                    dataOutputStream.writeInt(modifiers2);
                    dataOutputStream.writeUTF(ctField.getFieldInfo2().getDescriptor());
                }
            }
            if (classFile.getStaticInitializer() != null) {
                dataOutputStream.writeUTF("<clinit>");
                dataOutputStream.writeInt(8);
                dataOutputStream.writeUTF("()V");
            }
            final CtConstructor[] declaredConstructors = ctClass.getDeclaredConstructors();
            Arrays.<CtConstructor>sort(declaredConstructors, new Comparator() {
                SerialVersionUID$2() {
                    super();
                }
                
                @Override
                public int compare(final Object o, final Object o2) {
                    return ((CtConstructor)o).getMethodInfo2().getDescriptor().compareTo(((CtConstructor)o2).getMethodInfo2().getDescriptor());
                }
            });
            for (int l = 0; l < declaredConstructors.length; ++l) {
                final CtConstructor ctConstructor = declaredConstructors[l];
                final int modifiers3 = ctConstructor.getModifiers();
                if ((modifiers3 & 0x2) == 0x0) {
                    dataOutputStream.writeUTF("<init>");
                    dataOutputStream.writeInt(modifiers3);
                    dataOutputStream.writeUTF(ctConstructor.getMethodInfo2().getDescriptor().replace('/', '.'));
                }
            }
            Arrays.<CtMethod>sort(declaredMethods, new Comparator() {
                SerialVersionUID$3() {
                    super();
                }
                
                @Override
                public int compare(final Object o, final Object o2) {
                    final CtMethod ctMethod = (CtMethod)o;
                    final CtMethod ctMethod2 = (CtMethod)o2;
                    ctMethod.getName().compareTo(ctMethod2.getName());
                    return ctMethod.getMethodInfo2().getDescriptor().compareTo(ctMethod2.getMethodInfo2().getDescriptor());
                }
            });
            for (int n = 0; n < declaredMethods.length; ++n) {
                final CtMethod ctMethod = declaredMethods[n];
                final int n2 = ctMethod.getModifiers() & 0xD3F;
                if ((n2 & 0x2) == 0x0) {
                    dataOutputStream.writeUTF(ctMethod.getName());
                    dataOutputStream.writeInt(n2);
                    dataOutputStream.writeUTF(ctMethod.getMethodInfo2().getDescriptor().replace('/', '.'));
                }
            }
            dataOutputStream.flush();
            final byte[] digest = MessageDigest.getInstance("SHA").digest(byteArrayOutputStream.toByteArray());
            long n3 = 0L;
            for (int n4 = Math.min(digest.length, 8) - 1; n4 >= 0; --n4) {
                n3 = (n3 << 8 | (long)(digest[n4] & 0xFF));
            }
            return n3;
        }
        catch (IOException ex) {
            throw new CannotCompileException(ex);
        }
        catch (NoSuchAlgorithmException ex2) {
            throw new CannotCompileException(ex2);
        }
    }
    
    private static String javaName(final CtClass ctClass) {
        return Descriptor.toJavaName(Descriptor.toJvmName(ctClass));
    }
    
    private static String javaName(final String s) {
        return Descriptor.toJavaName(Descriptor.toJvmName(s));
    }
}
