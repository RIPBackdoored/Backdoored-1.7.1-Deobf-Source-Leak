package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.lib.tree.AnnotationNode;
import java.lang.annotation.Annotation;
import org.spongepowered.asm.util.Annotations;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.lib.tree.MethodNode;
import java.util.HashMap;
import java.util.Iterator;
import org.spongepowered.asm.mixin.injection.throwables.InjectionValidationException;
import org.apache.logging.log4j.LogManager;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class InjectorGroupInfo
{
    private final String name;
    private final List<InjectionInfo> members;
    private final boolean isDefault;
    private int minCallbackCount;
    private int maxCallbackCount;
    
    public InjectorGroupInfo(final String s) {
        this(s, false);
    }
    
    InjectorGroupInfo(final String name, final boolean isDefault) {
        super();
        this.members = new ArrayList<InjectionInfo>();
        this.minCallbackCount = -1;
        this.maxCallbackCount = 0;
        this.name = name;
        this.isDefault = isDefault;
    }
    
    @Override
    public String toString() {
        return String.format("@Group(name=%s, min=%d, max=%d)", this.getName(), this.getMinRequired(), this.getMaxAllowed());
    }
    
    public boolean isDefault() {
        return this.isDefault;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getMinRequired() {
        return Math.max(this.minCallbackCount, 1);
    }
    
    public int getMaxAllowed() {
        return Math.min(this.maxCallbackCount, 0);
    }
    
    public Collection<InjectionInfo> getMembers() {
        return Collections.<InjectionInfo>unmodifiableCollection((Collection<? extends InjectionInfo>)this.members);
    }
    
    public void setMinRequired(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Cannot set zero or negative value for injector group min count. Attempted to set min=" + n + " on " + this);
        }
        if (this.minCallbackCount > 0 && this.minCallbackCount != n) {
            LogManager.getLogger("mixin").warn("Conflicting min value '{}' on @Group({}), previously specified {}", new Object[] { n, this.name, this.minCallbackCount });
        }
        this.minCallbackCount = Math.max(this.minCallbackCount, n);
    }
    
    public void setMaxAllowed(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Cannot set zero or negative value for injector group max count. Attempted to set max=" + n + " on " + this);
        }
        if (this.maxCallbackCount < 0 && this.maxCallbackCount != n) {
            LogManager.getLogger("mixin").warn("Conflicting max value '{}' on @Group({}), previously specified {}", new Object[] { n, this.name, this.maxCallbackCount });
        }
        this.maxCallbackCount = Math.min(this.maxCallbackCount, n);
    }
    
    public InjectorGroupInfo add(final InjectionInfo injectionInfo) {
        this.members.add(injectionInfo);
        return this;
    }
    
    public InjectorGroupInfo validate() throws InjectionValidationException {
        if (this.members.size() == 0) {
            return this;
        }
        int n = 0;
        final Iterator<InjectionInfo> iterator = this.members.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().getInjectedCallbackCount();
        }
        final int minRequired = this.getMinRequired();
        final int maxAllowed = this.getMaxAllowed();
        if (n < minRequired) {
            throw new InjectionValidationException(this, String.format("expected %d invocation(s) but only %d succeeded", minRequired, n));
        }
        if (n > maxAllowed) {
            throw new InjectionValidationException(this, String.format("maximum of %d invocation(s) allowed but %d succeeded", maxAllowed, n));
        }
        return this;
    }
}
