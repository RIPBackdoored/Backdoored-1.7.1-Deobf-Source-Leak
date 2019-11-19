package javassist.bytecode;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.io.Serializable;
import java.io.IOException;
import java.io.DataInputStream;
import javassist.CtClass;
import java.util.HashMap;

public final class ConstPool
{
    LongVector items;
    int numOfItems;
    int thisClassInfo;
    HashMap itemsCache;
    public static final int CONST_Class = 7;
    public static final int CONST_Fieldref = 9;
    public static final int CONST_Methodref = 10;
    public static final int CONST_InterfaceMethodref = 11;
    public static final int CONST_String = 8;
    public static final int CONST_Integer = 3;
    public static final int CONST_Float = 4;
    public static final int CONST_Long = 5;
    public static final int CONST_Double = 6;
    public static final int CONST_NameAndType = 12;
    public static final int CONST_Utf8 = 1;
    public static final int CONST_MethodHandle = 15;
    public static final int CONST_MethodType = 16;
    public static final int CONST_InvokeDynamic = 18;
    public static final CtClass THIS;
    public static final int REF_getField = 1;
    public static final int REF_getStatic = 2;
    public static final int REF_putField = 3;
    public static final int REF_putStatic = 4;
    public static final int REF_invokeVirtual = 5;
    public static final int REF_invokeStatic = 6;
    public static final int REF_invokeSpecial = 7;
    public static final int REF_newInvokeSpecial = 8;
    public static final int REF_invokeInterface = 9;
    
    public ConstPool(final String s) {
        super();
        this.items = new LongVector();
        this.itemsCache = null;
        this.numOfItems = 0;
        this.addItem0(null);
        this.thisClassInfo = this.addClassInfo(s);
    }
    
    public ConstPool(final DataInputStream dataInputStream) throws IOException {
        super();
        this.itemsCache = null;
        this.thisClassInfo = 0;
        this.read(dataInputStream);
    }
    
    void prune() {
        this.itemsCache = null;
    }
    
    public int getSize() {
        return this.numOfItems;
    }
    
    public String getClassName() {
        return this.getClassInfo(this.thisClassInfo);
    }
    
    public int getThisClassInfo() {
        return this.thisClassInfo;
    }
    
    void setThisClassInfo(final int thisClassInfo) {
        this.thisClassInfo = thisClassInfo;
    }
    
    ConstInfo getItem(final int n) {
        return this.items.elementAt(n);
    }
    
    public int getTag(final int n) {
        return this.getItem(n).getTag();
    }
    
    public String getClassInfo(final int n) {
        final ClassInfo classInfo = (ClassInfo)this.getItem(n);
        if (classInfo == null) {
            return null;
        }
        return Descriptor.toJavaName(this.getUtf8Info(classInfo.name));
    }
    
    public String getClassInfoByDescriptor(final int n) {
        final ClassInfo classInfo = (ClassInfo)this.getItem(n);
        if (classInfo == null) {
            return null;
        }
        final String utf8Info = this.getUtf8Info(classInfo.name);
        if (utf8Info.charAt(0) == '[') {
            return utf8Info;
        }
        return Descriptor.of(utf8Info);
    }
    
    public int getNameAndTypeName(final int n) {
        return ((NameAndTypeInfo)this.getItem(n)).memberName;
    }
    
    public int getNameAndTypeDescriptor(final int n) {
        return ((NameAndTypeInfo)this.getItem(n)).typeDescriptor;
    }
    
    public int getMemberClass(final int n) {
        return ((MemberrefInfo)this.getItem(n)).classIndex;
    }
    
    public int getMemberNameAndType(final int n) {
        return ((MemberrefInfo)this.getItem(n)).nameAndTypeIndex;
    }
    
    public int getFieldrefClass(final int n) {
        return ((FieldrefInfo)this.getItem(n)).classIndex;
    }
    
    public String getFieldrefClassName(final int n) {
        final FieldrefInfo fieldrefInfo = (FieldrefInfo)this.getItem(n);
        if (fieldrefInfo == null) {
            return null;
        }
        return this.getClassInfo(fieldrefInfo.classIndex);
    }
    
    public int getFieldrefNameAndType(final int n) {
        return ((FieldrefInfo)this.getItem(n)).nameAndTypeIndex;
    }
    
    public String getFieldrefName(final int n) {
        final FieldrefInfo fieldrefInfo = (FieldrefInfo)this.getItem(n);
        if (fieldrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(fieldrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.memberName);
    }
    
    public String getFieldrefType(final int n) {
        final FieldrefInfo fieldrefInfo = (FieldrefInfo)this.getItem(n);
        if (fieldrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(fieldrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.typeDescriptor);
    }
    
    public int getMethodrefClass(final int n) {
        return ((MemberrefInfo)this.getItem(n)).classIndex;
    }
    
    public String getMethodrefClassName(final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (memberrefInfo == null) {
            return null;
        }
        return this.getClassInfo(memberrefInfo.classIndex);
    }
    
    public int getMethodrefNameAndType(final int n) {
        return ((MemberrefInfo)this.getItem(n)).nameAndTypeIndex;
    }
    
    public String getMethodrefName(final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (memberrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.memberName);
    }
    
    public String getMethodrefType(final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (memberrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.typeDescriptor);
    }
    
    public int getInterfaceMethodrefClass(final int n) {
        return ((MemberrefInfo)this.getItem(n)).classIndex;
    }
    
    public String getInterfaceMethodrefClassName(final int n) {
        return this.getClassInfo(((MemberrefInfo)this.getItem(n)).classIndex);
    }
    
    public int getInterfaceMethodrefNameAndType(final int n) {
        return ((MemberrefInfo)this.getItem(n)).nameAndTypeIndex;
    }
    
    public String getInterfaceMethodrefName(final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (memberrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.memberName);
    }
    
    public String getInterfaceMethodrefType(final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (memberrefInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.typeDescriptor);
    }
    
    public Object getLdcValue(final int n) {
        final ConstInfo item = this.getItem(n);
        Serializable stringInfo;
        if (item instanceof StringInfo) {
            stringInfo = this.getStringInfo(n);
        }
        else if (item instanceof FloatInfo) {
            stringInfo = new Float(this.getFloatInfo(n));
        }
        else if (item instanceof IntegerInfo) {
            stringInfo = new Integer(this.getIntegerInfo(n));
        }
        else if (item instanceof LongInfo) {
            stringInfo = new Long(this.getLongInfo(n));
        }
        else if (item instanceof DoubleInfo) {
            stringInfo = new Double(this.getDoubleInfo(n));
        }
        else {
            stringInfo = null;
        }
        return stringInfo;
    }
    
    public int getIntegerInfo(final int n) {
        return ((IntegerInfo)this.getItem(n)).value;
    }
    
    public float getFloatInfo(final int n) {
        return ((FloatInfo)this.getItem(n)).value;
    }
    
    public long getLongInfo(final int n) {
        return ((LongInfo)this.getItem(n)).value;
    }
    
    public double getDoubleInfo(final int n) {
        return ((DoubleInfo)this.getItem(n)).value;
    }
    
    public String getStringInfo(final int n) {
        return this.getUtf8Info(((StringInfo)this.getItem(n)).string);
    }
    
    public String getUtf8Info(final int n) {
        return ((Utf8Info)this.getItem(n)).string;
    }
    
    public int getMethodHandleKind(final int n) {
        return ((MethodHandleInfo)this.getItem(n)).refKind;
    }
    
    public int getMethodHandleIndex(final int n) {
        return ((MethodHandleInfo)this.getItem(n)).refIndex;
    }
    
    public int getMethodTypeInfo(final int n) {
        return ((MethodTypeInfo)this.getItem(n)).descriptor;
    }
    
    public int getInvokeDynamicBootstrap(final int n) {
        return ((InvokeDynamicInfo)this.getItem(n)).bootstrap;
    }
    
    public int getInvokeDynamicNameAndType(final int n) {
        return ((InvokeDynamicInfo)this.getItem(n)).nameAndType;
    }
    
    public String getInvokeDynamicType(final int n) {
        final InvokeDynamicInfo invokeDynamicInfo = (InvokeDynamicInfo)this.getItem(n);
        if (invokeDynamicInfo == null) {
            return null;
        }
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(invokeDynamicInfo.nameAndType);
        if (nameAndTypeInfo == null) {
            return null;
        }
        return this.getUtf8Info(nameAndTypeInfo.typeDescriptor);
    }
    
    public int isConstructor(final String s, final int n) {
        return this.isMember(s, "<init>", n);
    }
    
    public int isMember(final String s, final String s2, final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        if (this.getClassInfo(memberrefInfo.classIndex).equals(s)) {
            final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
            if (this.getUtf8Info(nameAndTypeInfo.memberName).equals(s2)) {
                return nameAndTypeInfo.typeDescriptor;
            }
        }
        return 0;
    }
    
    public String eqMember(final String s, final String s2, final int n) {
        final MemberrefInfo memberrefInfo = (MemberrefInfo)this.getItem(n);
        final NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getItem(memberrefInfo.nameAndTypeIndex);
        if (this.getUtf8Info(nameAndTypeInfo.memberName).equals(s) && this.getUtf8Info(nameAndTypeInfo.typeDescriptor).equals(s2)) {
            return this.getClassInfo(memberrefInfo.classIndex);
        }
        return null;
    }
    
    private int addItem0(final ConstInfo constInfo) {
        this.items.addElement(constInfo);
        return this.numOfItems++;
    }
    
    private int addItem(final ConstInfo constInfo) {
        if (this.itemsCache == null) {
            this.itemsCache = makeItemsCache(this.items);
        }
        final ConstInfo constInfo2 = this.itemsCache.get(constInfo);
        if (constInfo2 != null) {
            return constInfo2.index;
        }
        this.items.addElement(constInfo);
        this.itemsCache.put(constInfo, constInfo);
        return this.numOfItems++;
    }
    
    public int copy(final int n, final ConstPool constPool, final Map map) {
        return 0;
    }
    
    int addConstInfoPadding() {
        return this.addItem0(new ConstInfoPadding(this.numOfItems));
    }
    
    public int addClassInfo(final CtClass ctClass) {
        if (ctClass == ConstPool.THIS) {
            return this.thisClassInfo;
        }
        if (!ctClass.isArray()) {
            return this.addClassInfo(ctClass.getName());
        }
        return this.addClassInfo(Descriptor.toJvmName(ctClass));
    }
    
    public int addClassInfo(final String s) {
        return this.addItem(new ClassInfo(this.addUtf8Info(Descriptor.toJvmName(s)), this.numOfItems));
    }
    
    public int addNameAndTypeInfo(final String s, final String s2) {
        return this.addNameAndTypeInfo(this.addUtf8Info(s), this.addUtf8Info(s2));
    }
    
    public int addNameAndTypeInfo(final int n, final int n2) {
        return this.addItem(new NameAndTypeInfo(n, n2, this.numOfItems));
    }
    
    public int addFieldrefInfo(final int n, final String s, final String s2) {
        return this.addFieldrefInfo(n, this.addNameAndTypeInfo(s, s2));
    }
    
    public int addFieldrefInfo(final int n, final int n2) {
        return this.addItem(new FieldrefInfo(n, n2, this.numOfItems));
    }
    
    public int addMethodrefInfo(final int n, final String s, final String s2) {
        return this.addMethodrefInfo(n, this.addNameAndTypeInfo(s, s2));
    }
    
    public int addMethodrefInfo(final int n, final int n2) {
        return this.addItem(new MethodrefInfo(n, n2, this.numOfItems));
    }
    
    public int addInterfaceMethodrefInfo(final int n, final String s, final String s2) {
        return this.addInterfaceMethodrefInfo(n, this.addNameAndTypeInfo(s, s2));
    }
    
    public int addInterfaceMethodrefInfo(final int n, final int n2) {
        return this.addItem(new InterfaceMethodrefInfo(n, n2, this.numOfItems));
    }
    
    public int addStringInfo(final String s) {
        return this.addItem(new StringInfo(this.addUtf8Info(s), this.numOfItems));
    }
    
    public int addIntegerInfo(final int n) {
        return this.addItem(new IntegerInfo(n, this.numOfItems));
    }
    
    public int addFloatInfo(final float n) {
        return this.addItem(new FloatInfo(n, this.numOfItems));
    }
    
    public int addLongInfo(final long n) {
        final int addItem = this.addItem(new LongInfo(n, this.numOfItems));
        if (addItem == this.numOfItems - 1) {
            this.addConstInfoPadding();
        }
        return addItem;
    }
    
    public int addDoubleInfo(final double n) {
        final int addItem = this.addItem(new DoubleInfo(n, this.numOfItems));
        if (addItem == this.numOfItems - 1) {
            this.addConstInfoPadding();
        }
        return addItem;
    }
    
    public int addUtf8Info(final String s) {
        return this.addItem(new Utf8Info(s, this.numOfItems));
    }
    
    public int addMethodHandleInfo(final int n, final int n2) {
        return this.addItem(new MethodHandleInfo(n, n2, this.numOfItems));
    }
    
    public int addMethodTypeInfo(final int n) {
        return this.addItem(new MethodTypeInfo(n, this.numOfItems));
    }
    
    public int addInvokeDynamicInfo(final int n, final int n2) {
        return this.addItem(new InvokeDynamicInfo(n, n2, this.numOfItems));
    }
    
    public Set getClassNames() {
        final HashSet<String> set = new HashSet<String>();
        final LongVector items = this.items;
        for (int numOfItems = this.numOfItems, i = 1; i < numOfItems; ++i) {
            final String className = items.elementAt(i).getClassName(this);
            if (className != null) {
                set.add(className);
            }
        }
        return set;
    }
    
    public void renameClass(final String s, final String s2) {
        final LongVector items = this.items;
        for (int numOfItems = this.numOfItems, i = 1; i < numOfItems; ++i) {
            items.elementAt(i).renameClass(this, s, s2, this.itemsCache);
        }
    }
    
    public void renameClass(final Map map) {
        final LongVector items = this.items;
        for (int numOfItems = this.numOfItems, i = 1; i < numOfItems; ++i) {
            items.elementAt(i).renameClass(this, map, this.itemsCache);
        }
    }
    
    private void read(final DataInputStream dataInputStream) throws IOException {
        int unsignedShort = dataInputStream.readUnsignedShort();
        this.items = new LongVector(unsignedShort);
        this.numOfItems = 0;
        this.addItem0(null);
        while (--unsignedShort > 0) {
            final int one = this.readOne(dataInputStream);
            if (one == 5 || one == 6) {
                this.addConstInfoPadding();
                --unsignedShort;
            }
        }
    }
    
    private static HashMap makeItemsCache(final LongVector longVector) {
        final HashMap<ConstInfo, ConstInfo> hashMap = new HashMap<ConstInfo, ConstInfo>();
        int n = 1;
        while (true) {
            final ConstInfo element = longVector.elementAt(n++);
            if (element == null) {
                break;
            }
            hashMap.put(element, element);
        }
        return hashMap;
    }
    
    private int readOne(final DataInputStream dataInputStream) throws IOException {
        final int unsignedByte = dataInputStream.readUnsignedByte();
        ConstInfo constInfo = null;
        switch (unsignedByte) {
            case 1:
                constInfo = new Utf8Info(dataInputStream, this.numOfItems);
                break;
            case 3:
                constInfo = new IntegerInfo(dataInputStream, this.numOfItems);
                break;
            case 4:
                constInfo = new FloatInfo(dataInputStream, this.numOfItems);
                break;
            case 5:
                constInfo = new LongInfo(dataInputStream, this.numOfItems);
                break;
            case 6:
                constInfo = new DoubleInfo(dataInputStream, this.numOfItems);
                break;
            case 7:
                constInfo = new ClassInfo(dataInputStream, this.numOfItems);
                break;
            case 8:
                constInfo = new StringInfo(dataInputStream, this.numOfItems);
                break;
            case 9:
                constInfo = new FieldrefInfo(dataInputStream, this.numOfItems);
                break;
            case 10:
                constInfo = new MethodrefInfo(dataInputStream, this.numOfItems);
                break;
            case 11:
                constInfo = new InterfaceMethodrefInfo(dataInputStream, this.numOfItems);
                break;
            case 12:
                constInfo = new NameAndTypeInfo(dataInputStream, this.numOfItems);
                break;
            case 15:
                constInfo = new MethodHandleInfo(dataInputStream, this.numOfItems);
                break;
            case 16:
                constInfo = new MethodTypeInfo(dataInputStream, this.numOfItems);
                break;
            case 18:
                constInfo = new InvokeDynamicInfo(dataInputStream, this.numOfItems);
                break;
            default:
                throw new IOException("invalid constant type: " + unsignedByte + " at " + this.numOfItems);
        }
        this.addItem0(constInfo);
        return unsignedByte;
    }
    
    public void write(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.numOfItems);
        final LongVector items = this.items;
        for (int numOfItems = this.numOfItems, i = 1; i < numOfItems; ++i) {
            items.elementAt(i).write(dataOutputStream);
        }
    }
    
    public void print() {
        this.print(new PrintWriter(System.out, true));
    }
    
    public void print(final PrintWriter printWriter) {
        for (int numOfItems = this.numOfItems, i = 1; i < numOfItems; ++i) {
            printWriter.print(i);
            printWriter.print(" ");
            this.items.elementAt(i).print(printWriter);
        }
    }
    
    static {
        THIS = null;
    }
}
