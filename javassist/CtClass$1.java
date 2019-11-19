package javassist;

class CtClass$1 extends ClassMap {
    final /* synthetic */ CtClass this$0;
    
    CtClass$1(final CtClass this$0) {
        this.this$0 = this$0;
        super();
    }
    
    @Override
    public void put(final String s, final String s2) {
        this.put0(s, s2);
    }
    
    @Override
    public Object get(final Object o) {
        final String javaName = ClassMap.toJavaName((String)o);
        this.put0(javaName, javaName);
        return null;
    }
    
    @Override
    public void fix(final String s) {
    }
}