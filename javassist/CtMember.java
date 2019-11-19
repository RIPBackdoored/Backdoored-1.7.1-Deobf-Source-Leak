package javassist;

public abstract class CtMember
{
    CtMember next;
    protected CtClass declaringClass;
    
    protected CtMember(final CtClass declaringClass) {
        super();
        this.declaringClass = declaringClass;
        this.next = null;
    }
    
    final CtMember next() {
        return this.next;
    }
    
    void nameReplaced() {
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName());
        sb.append("@");
        sb.append(Integer.toHexString(this.hashCode()));
        sb.append("[");
        sb.append(Modifier.toString(this.getModifiers()));
        this.extendToString(sb);
        sb.append("]");
        return sb.toString();
    }
    
    protected abstract void extendToString(final StringBuffer p0);
    
    public CtClass getDeclaringClass() {
        return this.declaringClass;
    }
    
    public boolean visibleFrom(final CtClass ctClass) {
        final int modifiers = this.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            return true;
        }
        if (Modifier.isPrivate(modifiers)) {
            return ctClass == this.declaringClass;
        }
        final String packageName = this.declaringClass.getPackageName();
        final String packageName2 = ctClass.getPackageName();
        boolean equals;
        if (packageName == null) {
            equals = (packageName2 == null);
        }
        else {
            equals = packageName.equals(packageName2);
        }
        if (Modifier.isProtected(modifiers)) {
            return ctClass.subclassOf(this.declaringClass);
        }
        return equals;
    }
    
    public abstract int getModifiers();
    
    public abstract void setModifiers(final int p0);
    
    public boolean hasAnnotation(final Class clazz) {
        return this.hasAnnotation(clazz.getName());
    }
    
    public abstract boolean hasAnnotation(final String p0);
    
    public abstract Object getAnnotation(final Class p0) throws ClassNotFoundException;
    
    public abstract Object[] getAnnotations() throws ClassNotFoundException;
    
    public abstract Object[] getAvailableAnnotations();
    
    public abstract String getName();
    
    public abstract String getSignature();
    
    public abstract String getGenericSignature();
    
    public abstract void setGenericSignature(final String p0);
    
    public abstract byte[] getAttribute(final String p0);
    
    public abstract void setAttribute(final String p0, final byte[] p1);
}
