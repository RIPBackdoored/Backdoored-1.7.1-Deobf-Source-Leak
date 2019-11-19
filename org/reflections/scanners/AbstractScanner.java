package org.reflections.scanners;

import org.reflections.adapters.MetadataAdapter;
import org.reflections.ReflectionsException;
import org.reflections.vfs.Vfs;
import com.google.common.base.Predicates;
import com.google.common.base.Predicate;
import com.google.common.collect.Multimap;
import org.reflections.Configuration;

public abstract class AbstractScanner implements Scanner
{
    private Configuration configuration;
    private Multimap<String, String> store;
    private Predicate<String> resultFilter;
    
    public AbstractScanner() {
        super();
        this.resultFilter = (Predicate<String>)Predicates.alwaysTrue();
    }
    
    @Override
    public boolean acceptsInput(final String s) {
        return this.getMetadataAdapter().acceptsInput(s);
    }
    
    @Override
    public Object scan(final Vfs.File file, Object ofCreateClassObject) {
        if (ofCreateClassObject == null) {
            try {
                ofCreateClassObject = this.configuration.getMetadataAdapter().getOfCreateClassObject(file);
            }
            catch (Exception ex) {
                throw new ReflectionsException("could not create class object from file " + file.getRelativePath(), ex);
            }
        }
        this.scan(ofCreateClassObject);
        return ofCreateClassObject;
    }
    
    public abstract void scan(final Object p0);
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    @Override
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }
    
    @Override
    public Multimap<String, String> getStore() {
        return this.store;
    }
    
    @Override
    public void setStore(final Multimap<String, String> store) {
        this.store = store;
    }
    
    public Predicate<String> getResultFilter() {
        return this.resultFilter;
    }
    
    public void setResultFilter(final Predicate<String> resultFilter) {
        this.resultFilter = resultFilter;
    }
    
    @Override
    public Scanner filterResultsBy(final Predicate<String> resultFilter) {
        this.setResultFilter(resultFilter);
        return this;
    }
    
    @Override
    public boolean acceptResult(final String s) {
        return s != null && this.resultFilter.apply(s);
    }
    
    protected MetadataAdapter getMetadataAdapter() {
        return this.configuration.getMetadataAdapter();
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass());
    }
    
    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
