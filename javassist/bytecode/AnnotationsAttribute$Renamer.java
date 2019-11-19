package javassist.bytecode;

import java.util.Map;

static class Renamer extends Walker
{
    ConstPool cpool;
    Map classnames;
    
    Renamer(final byte[] array, final ConstPool cpool, final Map classnames) {
        super(array);
        this.cpool = cpool;
        this.classnames = classnames;
    }
    
    @Override
    int annotation(final int n, final int n2, final int n3) throws Exception {
        this.renameType(n - 4, n2);
        return super.annotation(n, n2, n3);
    }
    
    @Override
    void enumMemberValue(final int n, final int n2, final int n3) throws Exception {
        this.renameType(n + 1, n2);
        super.enumMemberValue(n, n2, n3);
    }
    
    @Override
    void classMemberValue(final int n, final int n2) throws Exception {
        this.renameType(n + 1, n2);
        super.classMemberValue(n, n2);
    }
    
    private void renameType(final int n, final int n2) {
        final String utf8Info = this.cpool.getUtf8Info(n2);
        final String rename = Descriptor.rename(utf8Info, this.classnames);
        if (!utf8Info.equals(rename)) {
            ByteArray.write16bit(this.cpool.addUtf8Info(rename), this.info, n);
        }
    }
}
