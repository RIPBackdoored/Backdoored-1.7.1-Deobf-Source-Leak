package javassist.runtime;

public class Cflow extends ThreadLocal
{
    public Cflow() {
        super();
    }
    
    @Override
    protected synchronized Object initialValue() {
        return new Depth();
    }
    
    public void enter() {
        this.get().inc();
    }
    
    public void exit() {
        this.get().dec();
    }
    
    public int value() {
        return this.get().get();
    }
}
