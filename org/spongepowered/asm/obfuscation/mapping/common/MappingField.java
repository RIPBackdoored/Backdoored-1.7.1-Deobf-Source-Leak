package org.spongepowered.asm.obfuscation.mapping.common;

import com.google.common.base.Strings;
import com.google.common.base.Objects;
import org.spongepowered.asm.obfuscation.mapping.IMapping;

public class MappingField implements IMapping<MappingField>
{
    private final String owner;
    private final String name;
    private final String desc;
    
    public MappingField(final String s, final String s2) {
        this(s, s2, null);
    }
    
    public MappingField(final String owner, final String name, final String desc) {
        super();
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }
    
    @Override
    public Type getType() {
        return Type.FIELD;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public final String getSimpleName() {
        return this.name;
    }
    
    @Override
    public final String getOwner() {
        return this.owner;
    }
    
    @Override
    public final String getDesc() {
        return this.desc;
    }
    
    @Override
    public MappingField getSuper() {
        return null;
    }
    
    @Override
    public MappingField move(final String s) {
        return new MappingField(s, this.getName(), this.getDesc());
    }
    
    @Override
    public MappingField remap(final String s) {
        return new MappingField(this.getOwner(), s, this.getDesc());
    }
    
    @Override
    public MappingField transform(final String s) {
        return new MappingField(this.getOwner(), this.getName(), s);
    }
    
    @Override
    public MappingField copy() {
        return new MappingField(this.getOwner(), this.getName(), this.getDesc());
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.toString());
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof MappingField && Objects.equal(this.toString(), ((MappingField)o).toString()));
    }
    
    @Override
    public String serialise() {
        return this.toString();
    }
    
    @Override
    public String toString() {
        return String.format("L%s;%s:%s", this.getOwner(), this.getName(), Strings.nullToEmpty(this.getDesc()));
    }
    
    @Override
    public /* bridge */ Object getSuper() {
        return this.getSuper();
    }
    
    @Override
    public /* bridge */ Object copy() {
        return this.copy();
    }
    
    @Override
    public /* bridge */ Object transform(final String s) {
        return this.transform(s);
    }
    
    @Override
    public /* bridge */ Object remap(final String s) {
        return this.remap(s);
    }
    
    @Override
    public /* bridge */ Object move(final String s) {
        return this.move(s);
    }
}
