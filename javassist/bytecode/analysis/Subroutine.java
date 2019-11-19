package javassist.bytecode.analysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class Subroutine
{
    private List callers;
    private Set access;
    private int start;
    
    public Subroutine(final int start, final int n) {
        super();
        this.callers = new ArrayList();
        this.access = new HashSet();
        this.start = start;
        this.callers.add(new Integer(n));
    }
    
    public void addCaller(final int n) {
        this.callers.add(new Integer(n));
    }
    
    public int start() {
        return this.start;
    }
    
    public void access(final int n) {
        this.access.add(new Integer(n));
    }
    
    public boolean isAccessed(final int n) {
        return this.access.contains(new Integer(n));
    }
    
    public Collection accessed() {
        return this.access;
    }
    
    public Collection callers() {
        return this.callers;
    }
    
    @Override
    public String toString() {
        return "start = " + this.start + " callers = " + this.callers.toString();
    }
}
