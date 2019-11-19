package javassist.bytecode.analysis;

import java.util.Iterator;
import java.util.HashMap;
import javassist.CtClass;
import java.util.Map;

public class MultiType extends Type
{
    private Map interfaces;
    private Type resolved;
    private Type potentialClass;
    private MultiType mergeSource;
    private boolean changed;
    
    public MultiType(final Map map) {
        this(map, null);
    }
    
    public MultiType(final Map interfaces, final Type potentialClass) {
        super(null);
        this.changed = false;
        this.interfaces = interfaces;
        this.potentialClass = potentialClass;
    }
    
    @Override
    public CtClass getCtClass() {
        if (this.resolved != null) {
            return this.resolved.getCtClass();
        }
        return Type.OBJECT.getCtClass();
    }
    
    @Override
    public Type getComponent() {
        return null;
    }
    
    @Override
    public int getSize() {
        return 1;
    }
    
    @Override
    public boolean isArray() {
        return false;
    }
    
    @Override
    boolean popChanged() {
        final boolean changed = this.changed;
        this.changed = false;
        return changed;
    }
    
    @Override
    public boolean isAssignableFrom(final Type type) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    public boolean isAssignableTo(final Type type) {
        if (this.resolved != null) {
            return type.isAssignableFrom(this.resolved);
        }
        if (Type.OBJECT.equals(type)) {
            return true;
        }
        if (this.potentialClass != null && !type.isAssignableFrom(this.potentialClass)) {
            this.potentialClass = null;
        }
        final Map mergeMultiAndSingle = this.mergeMultiAndSingle(this, type);
        if (mergeMultiAndSingle.size() == 1 && this.potentialClass == null) {
            this.resolved = Type.get(mergeMultiAndSingle.values().iterator().next());
            this.propogateResolved();
            return true;
        }
        if (mergeMultiAndSingle.size() >= 1) {
            this.interfaces = mergeMultiAndSingle;
            this.propogateState();
            return true;
        }
        if (this.potentialClass != null) {
            this.resolved = this.potentialClass;
            this.propogateResolved();
            return true;
        }
        return false;
    }
    
    private void propogateState() {
        for (MultiType multiType = this.mergeSource; multiType != null; multiType = multiType.mergeSource) {
            multiType.interfaces = this.interfaces;
            multiType.potentialClass = this.potentialClass;
        }
    }
    
    private void propogateResolved() {
        for (MultiType multiType = this.mergeSource; multiType != null; multiType = multiType.mergeSource) {
            multiType.resolved = this.resolved;
        }
    }
    
    @Override
    public boolean isReference() {
        return true;
    }
    
    private Map getAllMultiInterfaces(final MultiType multiType) {
        final HashMap<String, CtClass> hashMap = new HashMap<String, CtClass>();
        for (final CtClass ctClass : multiType.interfaces.values()) {
            hashMap.put(ctClass.getName(), ctClass);
            this.getAllInterfaces(ctClass, hashMap);
        }
        return hashMap;
    }
    
    private Map mergeMultiInterfaces(final MultiType multiType, final MultiType multiType2) {
        return this.findCommonInterfaces(this.getAllMultiInterfaces(multiType), this.getAllMultiInterfaces(multiType2));
    }
    
    private Map mergeMultiAndSingle(final MultiType multiType, final Type type) {
        return this.findCommonInterfaces(this.getAllMultiInterfaces(multiType), this.getAllInterfaces(type.getCtClass(), null));
    }
    
    private boolean inMergeSource(MultiType mergeSource) {
        while (mergeSource != null) {
            if (mergeSource == this) {
                return true;
            }
            mergeSource = mergeSource.mergeSource;
        }
        return false;
    }
    
    @Override
    public Type merge(final Type type) {
        if (this == type) {
            return this;
        }
        if (type == MultiType.UNINIT) {
            return this;
        }
        if (type == MultiType.BOGUS) {
            return MultiType.BOGUS;
        }
        if (type == null) {
            return this;
        }
        if (this.resolved != null) {
            return this.resolved.merge(type);
        }
        if (this.potentialClass != null) {
            final Type merge = this.potentialClass.merge(type);
            if (!merge.equals(this.potentialClass) || merge.popChanged()) {
                this.potentialClass = (Type.OBJECT.equals(merge) ? null : merge);
                this.changed = true;
            }
        }
        Map interfaces;
        if (type instanceof MultiType) {
            final MultiType mergeSource = (MultiType)type;
            if (mergeSource.resolved != null) {
                interfaces = this.mergeMultiAndSingle(this, mergeSource.resolved);
            }
            else {
                interfaces = this.mergeMultiInterfaces(mergeSource, this);
                if (!this.inMergeSource(mergeSource)) {
                    this.mergeSource = mergeSource;
                }
            }
        }
        else {
            interfaces = this.mergeMultiAndSingle(this, type);
        }
        if (interfaces.size() > 1 || (interfaces.size() == 1 && this.potentialClass != null)) {
            if (interfaces.size() != this.interfaces.size()) {
                this.changed = true;
            }
            else if (!this.changed) {
                final Iterator<Object> iterator = interfaces.keySet().iterator();
                while (iterator.hasNext()) {
                    if (!this.interfaces.containsKey(iterator.next())) {
                        this.changed = true;
                    }
                }
            }
            this.interfaces = interfaces;
            this.propogateState();
            return this;
        }
        if (interfaces.size() == 1) {
            this.resolved = Type.get((CtClass)interfaces.values().iterator().next());
        }
        else if (this.potentialClass != null) {
            this.resolved = this.potentialClass;
        }
        else {
            this.resolved = MultiType.OBJECT;
        }
        this.propogateResolved();
        return this.resolved;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof MultiType)) {
            return false;
        }
        final MultiType multiType = (MultiType)o;
        if (this.resolved != null) {
            return this.resolved.equals(multiType.resolved);
        }
        return multiType.resolved == null && this.interfaces.keySet().equals(multiType.interfaces.keySet());
    }
    
    @Override
    public String toString() {
        if (this.resolved != null) {
            return this.resolved.toString();
        }
        final StringBuffer sb = new StringBuffer("{");
        final Iterator<Object> iterator = this.interfaces.keySet().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        if (this.potentialClass != null) {
            sb.append(", *").append(this.potentialClass.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
