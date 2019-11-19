package javassist;

final class CtArray extends CtClass
{
    protected ClassPool pool;
    private CtClass[] interfaces;
    
    CtArray(final String s, final ClassPool pool) {
        super(s);
        this.interfaces = null;
        this.pool = pool;
    }
    
    @Override
    public ClassPool getClassPool() {
        return this.pool;
    }
    
    @Override
    public boolean isArray() {
        return true;
    }
    
    @Override
    public int getModifiers() {
        int n = 16;
        try {
            n |= (this.getComponentType().getModifiers() & 0x7);
        }
        catch (NotFoundException ex) {}
        return n;
    }
    
    @Override
    public CtClass[] getInterfaces() throws NotFoundException {
        if (this.interfaces == null) {
            final Class<?>[] interfaces = Object[].class.getInterfaces();
            this.interfaces = new CtClass[interfaces.length];
            for (int i = 0; i < interfaces.length; ++i) {
                this.interfaces[i] = this.pool.get(interfaces[i].getName());
            }
        }
        return this.interfaces;
    }
    
    @Override
    public boolean subtypeOf(final CtClass ctClass) throws NotFoundException {
        if (super.subtypeOf(ctClass)) {
            return true;
        }
        if (ctClass.getName().equals("java.lang.Object")) {
            return true;
        }
        final CtClass[] interfaces = this.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (interfaces[i].subtypeOf(ctClass)) {
                return true;
            }
        }
        return ctClass.isArray() && this.getComponentType().subtypeOf(ctClass.getComponentType());
    }
    
    @Override
    public CtClass getComponentType() throws NotFoundException {
        final String name = this.getName();
        return this.pool.get(name.substring(0, name.length() - 2));
    }
    
    @Override
    public CtClass getSuperclass() throws NotFoundException {
        return this.pool.get("java.lang.Object");
    }
    
    @Override
    public CtMethod[] getMethods() {
        try {
            return this.getSuperclass().getMethods();
        }
        catch (NotFoundException ex) {
            return super.getMethods();
        }
    }
    
    @Override
    public CtMethod getMethod(final String s, final String s2) throws NotFoundException {
        return this.getSuperclass().getMethod(s, s2);
    }
    
    @Override
    public CtConstructor[] getConstructors() {
        try {
            return this.getSuperclass().getConstructors();
        }
        catch (NotFoundException ex) {
            return super.getConstructors();
        }
    }
}
