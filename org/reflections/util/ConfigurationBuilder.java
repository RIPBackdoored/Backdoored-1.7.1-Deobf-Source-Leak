package org.reflections.util;

import com.google.common.collect.ObjectArrays;
import org.reflections.serializers.XmlSerializer;
import java.util.concurrent.Executors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.reflections.adapters.JavaReflectionAdapter;
import org.reflections.adapters.JavassistAdapter;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import org.reflections.ReflectionsException;
import org.reflections.Reflections;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import java.util.concurrent.ExecutorService;
import org.reflections.serializers.Serializer;
import javax.annotation.Nullable;
import com.google.common.base.Predicate;
import org.reflections.adapters.MetadataAdapter;
import java.net.URL;
import javax.annotation.Nonnull;
import org.reflections.scanners.Scanner;
import java.util.Set;
import org.reflections.Configuration;

public class ConfigurationBuilder implements Configuration
{
    @Nonnull
    private Set<Scanner> scanners;
    @Nonnull
    private Set<URL> urls;
    protected MetadataAdapter metadataAdapter;
    @Nullable
    private Predicate<String> inputsFilter;
    private Serializer serializer;
    @Nullable
    private ExecutorService executorService;
    @Nullable
    private ClassLoader[] classLoaders;
    private boolean expandSuperTypes;
    
    public ConfigurationBuilder() {
        super();
        this.expandSuperTypes = true;
        this.scanners = (Set<Scanner>)Sets.newHashSet((Object[])new Scanner[] { new TypeAnnotationsScanner(), new SubTypesScanner() });
        this.urls = (Set<URL>)Sets.<Object>newHashSet();
    }
    
    public static ConfigurationBuilder build(@Nullable final Object... array) {
        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        if (array != null) {
            for (final Object o : array) {
                if (o != null) {
                    if (((Iterable<Iterable>)o).getClass().isArray()) {
                        for (final Object o2 : (Object[])o) {
                            if (o2 != null) {
                                arrayList.add(o2);
                            }
                        }
                    }
                    else if (o instanceof Iterable) {
                        for (final Iterable next : (Iterable<Iterable>)o) {
                            if (next != null) {
                                arrayList.add(next);
                            }
                        }
                    }
                    else {
                        arrayList.add(o);
                    }
                }
            }
        }
        final ArrayList<Object> arrayList2 = Lists.<Object>newArrayList();
        for (final ClassLoader next2 : arrayList) {
            if (next2 instanceof ClassLoader) {
                arrayList2.add(next2);
            }
        }
        final ClassLoader[] array3 = (ClassLoader[])(arrayList2.isEmpty() ? null : ((ClassLoader[])arrayList2.<ClassLoader>toArray(new ClassLoader[arrayList2.size()])));
        final FilterBuilder filterBuilder = new FilterBuilder();
        final ArrayList<Object> arrayList3 = Lists.<Object>newArrayList();
        for (final Scanner next3 : arrayList) {
            if (next3 instanceof String) {
                configurationBuilder.addUrls(ClasspathHelper.forPackage((String)next3, array3));
                filterBuilder.includePackage((String)next3);
            }
            else if (next3 instanceof Class) {
                if (Scanner.class.isAssignableFrom((Class<?>)next3)) {
                    try {
                        configurationBuilder.addScanners((Scanner)((Class<?>)next3).newInstance());
                    }
                    catch (Exception ex) {}
                }
                configurationBuilder.addUrls(ClasspathHelper.forClass((Class<?>)next3, array3));
                filterBuilder.includePackage((Class<?>)next3);
            }
            else if (next3 instanceof Scanner) {
                arrayList3.add(next3);
            }
            else if (next3 instanceof URL) {
                configurationBuilder.addUrls((URL)next3);
            }
            else {
                if (next3 instanceof ClassLoader) {
                    continue;
                }
                if (next3 instanceof Predicate) {
                    filterBuilder.add((Predicate<String>)next3);
                }
                else if (next3 instanceof ExecutorService) {
                    configurationBuilder.setExecutorService((ExecutorService)next3);
                }
                else {
                    if (Reflections.log != null) {
                        throw new ReflectionsException("could not use param " + next3);
                    }
                    continue;
                }
            }
        }
        if (configurationBuilder.getUrls().isEmpty()) {
            if (array3 != null) {
                configurationBuilder.addUrls(ClasspathHelper.forClassLoader(array3));
            }
            else {
                configurationBuilder.addUrls(ClasspathHelper.forClassLoader());
            }
        }
        configurationBuilder.filterInputsBy(filterBuilder);
        if (!arrayList3.isEmpty()) {
            configurationBuilder.setScanners((Scanner[])arrayList3.<Scanner>toArray(new Scanner[arrayList3.size()]));
        }
        if (!arrayList2.isEmpty()) {
            configurationBuilder.addClassLoaders((Collection<ClassLoader>)arrayList2);
        }
        return configurationBuilder;
    }
    
    public ConfigurationBuilder forPackages(final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.addUrls(ClasspathHelper.forPackage(array[i], new ClassLoader[0]));
        }
        return this;
    }
    
    @Nonnull
    @Override
    public Set<Scanner> getScanners() {
        return this.scanners;
    }
    
    public ConfigurationBuilder setScanners(@Nonnull final Scanner... array) {
        this.scanners.clear();
        return this.addScanners(array);
    }
    
    public ConfigurationBuilder addScanners(final Scanner... array) {
        this.scanners.addAll(Sets.newHashSet((Object[])array));
        return this;
    }
    
    @Nonnull
    @Override
    public Set<URL> getUrls() {
        return this.urls;
    }
    
    public ConfigurationBuilder setUrls(@Nonnull final Collection<URL> elements) {
        this.urls = (Set<URL>)Sets.<Object>newHashSet((Iterable<?>)elements);
        return this;
    }
    
    public ConfigurationBuilder setUrls(final URL... array) {
        this.urls = (Set<URL>)Sets.newHashSet((Object[])array);
        return this;
    }
    
    public ConfigurationBuilder addUrls(final Collection<URL> collection) {
        this.urls.addAll(collection);
        return this;
    }
    
    public ConfigurationBuilder addUrls(final URL... array) {
        this.urls.addAll(Sets.newHashSet((Object[])array));
        return this;
    }
    
    @Override
    public MetadataAdapter getMetadataAdapter() {
        if (this.metadataAdapter != null) {
            return this.metadataAdapter;
        }
        try {
            return this.metadataAdapter = new JavassistAdapter();
        }
        catch (Throwable t) {
            if (Reflections.log != null) {
                Reflections.log.warn("could not create JavassistAdapter, using JavaReflectionAdapter", t);
            }
            return this.metadataAdapter = new JavaReflectionAdapter();
        }
    }
    
    public ConfigurationBuilder setMetadataAdapter(final MetadataAdapter metadataAdapter) {
        this.metadataAdapter = metadataAdapter;
        return this;
    }
    
    @Nullable
    @Override
    public Predicate<String> getInputsFilter() {
        return this.inputsFilter;
    }
    
    public void setInputsFilter(@Nullable final Predicate<String> inputsFilter) {
        this.inputsFilter = inputsFilter;
    }
    
    public ConfigurationBuilder filterInputsBy(final Predicate<String> inputsFilter) {
        this.inputsFilter = inputsFilter;
        return this;
    }
    
    @Nullable
    @Override
    public ExecutorService getExecutorService() {
        return this.executorService;
    }
    
    public ConfigurationBuilder setExecutorService(@Nullable final ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }
    
    public ConfigurationBuilder useParallelExecutor() {
        return this.useParallelExecutor(Runtime.getRuntime().availableProcessors());
    }
    
    public ConfigurationBuilder useParallelExecutor(final int n) {
        this.setExecutorService(Executors.newFixedThreadPool(n, new ThreadFactoryBuilder().setDaemon(true).setNameFormat("org.reflections-scanner-%d").build()));
        return this;
    }
    
    @Override
    public Serializer getSerializer() {
        return (this.serializer != null) ? this.serializer : (this.serializer = new XmlSerializer());
    }
    
    public ConfigurationBuilder setSerializer(final Serializer serializer) {
        this.serializer = serializer;
        return this;
    }
    
    @Nullable
    @Override
    public ClassLoader[] getClassLoaders() {
        return this.classLoaders;
    }
    
    @Override
    public boolean shouldExpandSuperTypes() {
        return this.expandSuperTypes;
    }
    
    public ConfigurationBuilder setExpandSuperTypes(final boolean expandSuperTypes) {
        this.expandSuperTypes = expandSuperTypes;
        return this;
    }
    
    public void setClassLoaders(@Nullable final ClassLoader[] classLoaders) {
        this.classLoaders = classLoaders;
    }
    
    public ConfigurationBuilder addClassLoader(final ClassLoader classLoader) {
        return this.addClassLoaders(classLoader);
    }
    
    public ConfigurationBuilder addClassLoaders(final ClassLoader... array) {
        this.classLoaders = (ClassLoader[])((this.classLoaders == null) ? array : ObjectArrays.concat((Object[])this.classLoaders, (Object[])array, (Class)ClassLoader.class));
        return this;
    }
    
    public ConfigurationBuilder addClassLoaders(final Collection<ClassLoader> collection) {
        return this.addClassLoaders((ClassLoader[])collection.<ClassLoader>toArray(new ClassLoader[collection.size()]));
    }
}
