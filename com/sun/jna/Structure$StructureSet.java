package com.sun.jna;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.AbstractCollection;

static class StructureSet extends AbstractCollection<Structure> implements Set<Structure>
{
    Structure[] elements;
    private int count;
    
    StructureSet() {
        super();
    }
    
    private void ensureCapacity(final int n) {
        if (this.elements == null) {
            this.elements = new Structure[n * 3 / 2];
        }
        else if (this.elements.length < n) {
            final Structure[] elements = new Structure[n * 3 / 2];
            System.arraycopy(this.elements, 0, elements, 0, this.elements.length);
            this.elements = elements;
        }
    }
    
    public Structure[] getElements() {
        return this.elements;
    }
    
    @Override
    public int size() {
        return this.count;
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.indexOf((Structure)o) != -1;
    }
    
    @Override
    public boolean add(final Structure structure) {
        if (!this.contains(structure)) {
            this.ensureCapacity(this.count + 1);
            this.elements[this.count++] = structure;
        }
        return true;
    }
    
    private int indexOf(final Structure structure) {
        for (int i = 0; i < this.count; ++i) {
            final Structure structure2 = this.elements[i];
            if (structure == structure2 || (structure.getClass() == structure2.getClass() && structure.size() == structure2.size() && structure.getPointer().equals(structure2.getPointer()))) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean remove(final Object o) {
        final int index = this.indexOf((Structure)o);
        if (index != -1) {
            if (--this.count >= 0) {
                this.elements[index] = this.elements[this.count];
                this.elements[this.count] = null;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public Iterator<Structure> iterator() {
        final Structure[] array = new Structure[this.count];
        if (this.count > 0) {
            System.arraycopy(this.elements, 0, array, 0, this.count);
        }
        return Arrays.<Structure>asList(array).iterator();
    }
    
    @Override
    public /* bridge */ boolean add(final Object o) {
        return this.add((Structure)o);
    }
}
