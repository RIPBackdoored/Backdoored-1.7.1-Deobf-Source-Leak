package javassist;

static class Cache extends CtMember
{
    private CtMember methodTail;
    private CtMember consTail;
    private CtMember fieldTail;
    
    @Override
    protected void extendToString(final StringBuffer sb) {
    }
    
    @Override
    public boolean hasAnnotation(final String s) {
        return false;
    }
    
    @Override
    public Object getAnnotation(final Class clazz) throws ClassNotFoundException {
        return null;
    }
    
    @Override
    public Object[] getAnnotations() throws ClassNotFoundException {
        return null;
    }
    
    @Override
    public byte[] getAttribute(final String s) {
        return null;
    }
    
    @Override
    public Object[] getAvailableAnnotations() {
        return null;
    }
    
    @Override
    public int getModifiers() {
        return 0;
    }
    
    @Override
    public String getName() {
        return null;
    }
    
    @Override
    public String getSignature() {
        return null;
    }
    
    @Override
    public void setAttribute(final String s, final byte[] array) {
    }
    
    @Override
    public void setModifiers(final int n) {
    }
    
    @Override
    public String getGenericSignature() {
        return null;
    }
    
    @Override
    public void setGenericSignature(final String s) {
    }
    
    Cache(final CtClassType ctClassType) {
        super(ctClassType);
        this.methodTail = this;
        this.consTail = this;
        this.fieldTail = this;
        this.fieldTail.next = this;
    }
    
    CtMember methodHead() {
        return this;
    }
    
    CtMember lastMethod() {
        return this.methodTail;
    }
    
    CtMember consHead() {
        return this.methodTail;
    }
    
    CtMember lastCons() {
        return this.consTail;
    }
    
    CtMember fieldHead() {
        return this.consTail;
    }
    
    CtMember lastField() {
        return this.fieldTail;
    }
    
    void addMethod(final CtMember ctMember) {
        ctMember.next = this.methodTail.next;
        this.methodTail.next = ctMember;
        if (this.methodTail == this.consTail) {
            this.consTail = ctMember;
            if (this.methodTail == this.fieldTail) {
                this.fieldTail = ctMember;
            }
        }
        this.methodTail = ctMember;
    }
    
    void addConstructor(final CtMember consTail) {
        consTail.next = this.consTail.next;
        this.consTail.next = consTail;
        if (this.consTail == this.fieldTail) {
            this.fieldTail = consTail;
        }
        this.consTail = consTail;
    }
    
    void addField(final CtMember ctMember) {
        ctMember.next = this;
        this.fieldTail.next = ctMember;
        this.fieldTail = ctMember;
    }
    
    static int count(CtMember next, final CtMember ctMember) {
        int n = 0;
        while (next != ctMember) {
            ++n;
            next = next.next;
        }
        return n;
    }
    
    void remove(final CtMember ctMember) {
        CtMember next = this;
        CtMember next2;
        while ((next2 = next.next) != this) {
            if (next2 == ctMember) {
                next.next = next2.next;
                if (next2 == this.methodTail) {
                    this.methodTail = next;
                }
                if (next2 == this.consTail) {
                    this.consTail = next;
                }
                if (next2 == this.fieldTail) {
                    this.fieldTail = next;
                    break;
                }
                break;
            }
            else {
                next = next.next;
            }
        }
    }
}
