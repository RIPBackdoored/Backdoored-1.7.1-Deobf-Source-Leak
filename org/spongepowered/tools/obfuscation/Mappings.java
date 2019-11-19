package org.spongepowered.tools.obfuscation;

import org.spongepowered.asm.obfuscation.mapping.IMapping;
import java.util.Iterator;
import java.util.HashMap;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import java.util.Map;
import org.spongepowered.tools.obfuscation.mapping.IMappingConsumer;

class Mappings implements IMappingConsumer
{
    private final Map<ObfuscationType, MappingSet<MappingField>> fieldMappings;
    private final Map<ObfuscationType, MappingSet<MappingMethod>> methodMappings;
    private UniqueMappings unique;
    
    public Mappings() {
        super();
        this.fieldMappings = new HashMap<ObfuscationType, MappingSet<MappingField>>();
        this.methodMappings = new HashMap<ObfuscationType, MappingSet<MappingMethod>>();
        this.init();
    }
    
    private void init() {
        for (final ObfuscationType obfuscationType : ObfuscationType.types()) {
            this.fieldMappings.put(obfuscationType, new MappingSet<MappingField>());
            this.methodMappings.put(obfuscationType, new MappingSet<MappingMethod>());
        }
    }
    
    public IMappingConsumer asUnique() {
        if (this.unique == null) {
            this.unique = new UniqueMappings(this);
        }
        return this.unique;
    }
    
    @Override
    public MappingSet<MappingField> getFieldMappings(final ObfuscationType obfuscationType) {
        final MappingSet<MappingField> set = this.fieldMappings.get(obfuscationType);
        return (set != null) ? set : new MappingSet<MappingField>();
    }
    
    @Override
    public MappingSet<MappingMethod> getMethodMappings(final ObfuscationType obfuscationType) {
        final MappingSet<MappingMethod> set = this.methodMappings.get(obfuscationType);
        return (set != null) ? set : new MappingSet<MappingMethod>();
    }
    
    @Override
    public void clear() {
        this.fieldMappings.clear();
        this.methodMappings.clear();
        if (this.unique != null) {
            this.unique.clearMaps();
        }
        this.init();
    }
    
    @Override
    public void addFieldMapping(final ObfuscationType obfuscationType, final MappingField mappingField, final MappingField mappingField2) {
        MappingSet<MappingField> set = this.fieldMappings.get(obfuscationType);
        if (set == null) {
            set = new MappingSet<MappingField>();
            this.fieldMappings.put(obfuscationType, set);
        }
        set.add(new MappingSet.Pair<MappingField>(mappingField, mappingField2));
    }
    
    @Override
    public void addMethodMapping(final ObfuscationType obfuscationType, final MappingMethod mappingMethod, final MappingMethod mappingMethod2) {
        MappingSet<MappingMethod> set = this.methodMappings.get(obfuscationType);
        if (set == null) {
            set = new MappingSet<MappingMethod>();
            this.methodMappings.put(obfuscationType, set);
        }
        set.add(new MappingSet.Pair<MappingMethod>(mappingMethod, mappingMethod2));
    }
}
