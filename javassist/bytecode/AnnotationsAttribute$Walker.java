package javassist.bytecode;

static class Walker
{
    byte[] info;
    
    Walker(final byte[] info) {
        super();
        this.info = info;
    }
    
    final void parameters() throws Exception {
        this.parameters(this.info[0] & 0xFF, 1);
    }
    
    void parameters(final int n, int annotationArray) throws Exception {
        for (int i = 0; i < n; ++i) {
            annotationArray = this.annotationArray(annotationArray);
        }
    }
    
    final void annotationArray() throws Exception {
        this.annotationArray(0);
    }
    
    final int annotationArray(final int n) throws Exception {
        return this.annotationArray(n + 2, ByteArray.readU16bit(this.info, n));
    }
    
    int annotationArray(int annotation, final int n) throws Exception {
        for (int i = 0; i < n; ++i) {
            annotation = this.annotation(annotation);
        }
        return annotation;
    }
    
    final int annotation(final int n) throws Exception {
        return this.annotation(n + 4, ByteArray.readU16bit(this.info, n), ByteArray.readU16bit(this.info, n + 2));
    }
    
    int annotation(int memberValuePair, final int n, final int n2) throws Exception {
        for (int i = 0; i < n2; ++i) {
            memberValuePair = this.memberValuePair(memberValuePair);
        }
        return memberValuePair;
    }
    
    final int memberValuePair(final int n) throws Exception {
        return this.memberValuePair(n + 2, ByteArray.readU16bit(this.info, n));
    }
    
    int memberValuePair(final int n, final int n2) throws Exception {
        return this.memberValue(n);
    }
    
    final int memberValue(final int n) throws Exception {
        final int n2 = this.info[n] & 0xFF;
        if (n2 == 101) {
            this.enumMemberValue(n, ByteArray.readU16bit(this.info, n + 1), ByteArray.readU16bit(this.info, n + 3));
            return n + 5;
        }
        if (n2 == 99) {
            this.classMemberValue(n, ByteArray.readU16bit(this.info, n + 1));
            return n + 3;
        }
        if (n2 == 64) {
            return this.annotationMemberValue(n + 1);
        }
        if (n2 == 91) {
            return this.arrayMemberValue(n + 3, ByteArray.readU16bit(this.info, n + 1));
        }
        this.constValueMember(n2, ByteArray.readU16bit(this.info, n + 1));
        return n + 3;
    }
    
    void constValueMember(final int n, final int n2) throws Exception {
    }
    
    void enumMemberValue(final int n, final int n2, final int n3) throws Exception {
    }
    
    void classMemberValue(final int n, final int n2) throws Exception {
    }
    
    int annotationMemberValue(final int n) throws Exception {
        return this.annotation(n);
    }
    
    int arrayMemberValue(int memberValue, final int n) throws Exception {
        for (int i = 0; i < n; ++i) {
            memberValue = this.memberValue(memberValue);
        }
        return memberValue;
    }
}
