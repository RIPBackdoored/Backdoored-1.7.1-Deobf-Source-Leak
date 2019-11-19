package javassist.bytecode;

public static final class ConstPoolWriter
{
    ByteStream output;
    protected int startPos;
    protected int num;
    
    ConstPoolWriter(final ByteStream output) {
        super();
        this.output = output;
        this.startPos = output.getPos();
        this.num = 1;
        this.output.writeShort(1);
    }
    
    public int[] addClassInfo(final String[] array) {
        final int length = array.length;
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array2[i] = this.addClassInfo(array[i]);
        }
        return array2;
    }
    
    public int addClassInfo(final String s) {
        final int addUtf8Info = this.addUtf8Info(s);
        this.output.write(7);
        this.output.writeShort(addUtf8Info);
        return this.num++;
    }
    
    public int addClassInfo(final int n) {
        this.output.write(7);
        this.output.writeShort(n);
        return this.num++;
    }
    
    public int addNameAndTypeInfo(final String s, final String s2) {
        return this.addNameAndTypeInfo(this.addUtf8Info(s), this.addUtf8Info(s2));
    }
    
    public int addNameAndTypeInfo(final int n, final int n2) {
        this.output.write(12);
        this.output.writeShort(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addFieldrefInfo(final int n, final int n2) {
        this.output.write(9);
        this.output.writeShort(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addMethodrefInfo(final int n, final int n2) {
        this.output.write(10);
        this.output.writeShort(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addInterfaceMethodrefInfo(final int n, final int n2) {
        this.output.write(11);
        this.output.writeShort(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addMethodHandleInfo(final int n, final int n2) {
        this.output.write(15);
        this.output.write(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addMethodTypeInfo(final int n) {
        this.output.write(16);
        this.output.writeShort(n);
        return this.num++;
    }
    
    public int addInvokeDynamicInfo(final int n, final int n2) {
        this.output.write(18);
        this.output.writeShort(n);
        this.output.writeShort(n2);
        return this.num++;
    }
    
    public int addStringInfo(final String s) {
        final int addUtf8Info = this.addUtf8Info(s);
        this.output.write(8);
        this.output.writeShort(addUtf8Info);
        return this.num++;
    }
    
    public int addIntegerInfo(final int n) {
        this.output.write(3);
        this.output.writeInt(n);
        return this.num++;
    }
    
    public int addFloatInfo(final float n) {
        this.output.write(4);
        this.output.writeFloat(n);
        return this.num++;
    }
    
    public int addLongInfo(final long n) {
        this.output.write(5);
        this.output.writeLong(n);
        final int num = this.num;
        this.num += 2;
        return num;
    }
    
    public int addDoubleInfo(final double n) {
        this.output.write(6);
        this.output.writeDouble(n);
        final int num = this.num;
        this.num += 2;
        return num;
    }
    
    public int addUtf8Info(final String s) {
        this.output.write(1);
        this.output.writeUTF(s);
        return this.num++;
    }
    
    void end() {
        this.output.writeShort(this.startPos, this.num);
    }
}
