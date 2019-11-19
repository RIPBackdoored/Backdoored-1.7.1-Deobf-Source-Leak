package org.reflections;

import org.reflections.scanners.MemberUsageScanner;
import java.lang.reflect.Member;
import org.reflections.scanners.MethodParameterNamesScanner;
import java.util.List;
import java.util.regex.Pattern;
import org.reflections.scanners.ResourcesScanner;
import java.util.HashSet;
import org.reflections.scanners.FieldAnnotationsScanner;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import java.lang.reflect.Method;
import com.google.common.base.Predicates;
import java.lang.reflect.AnnotatedElement;
import com.google.common.collect.Iterables;
import java.lang.annotation.Inherited;
import org.reflections.scanners.TypeAnnotationsScanner;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.HashMultimap;
import java.util.Set;
import com.google.common.collect.Sets;
import org.reflections.scanners.SubTypesScanner;
import com.google.common.collect.Multimap;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import org.reflections.util.Utils;
import java.io.IOException;
import org.reflections.util.ClasspathHelper;
import org.reflections.serializers.XmlSerializer;
import org.reflections.serializers.Serializer;
import org.reflections.util.FilterBuilder;
import com.google.common.base.Predicate;
import org.reflections.vfs.Vfs;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Future;
import java.net.URL;
import com.google.common.collect.Lists;
import com.google.common.base.Joiner;
import org.reflections.util.ConfigurationBuilder;
import java.util.Iterator;
import org.reflections.scanners.Scanner;
import javax.annotation.Nullable;
import org.slf4j.Logger;

public class Reflections
{
    @Nullable
    public static Logger log;
    protected final transient Configuration configuration;
    protected Store store;
    
    public Reflections(final Configuration configuration) {
        super();
        this.configuration = configuration;
        this.store = new Store(configuration);
        if (configuration.getScanners() != null && !configuration.getScanners().isEmpty()) {
            for (final Scanner scanner : configuration.getScanners()) {
                scanner.setConfiguration(configuration);
                scanner.setStore(this.store.getOrCreate(scanner.getClass().getSimpleName()));
            }
            this.scan();
            if (configuration.shouldExpandSuperTypes()) {
                this.expandSuperTypes();
            }
        }
    }
    
    public Reflections(final String s, @Nullable final Scanner... array) {
        this(new Object[] { s, array });
    }
    
    public Reflections(final Object... array) {
        this(ConfigurationBuilder.build(array));
    }
    
    protected Reflections() {
        super();
        this.configuration = new ConfigurationBuilder();
        this.store = new Store(this.configuration);
    }
    
    protected void scan() {
        if (this.configuration.getUrls() == null || this.configuration.getUrls().isEmpty()) {
            if (Reflections.log != null) {
                Reflections.log.warn("given scan urls are empty. set urls in the configuration");
            }
            return;
        }
        if (Reflections.log != null && Reflections.log.isDebugEnabled()) {
            Reflections.log.debug("going to scan these urls:\n" + Joiner.on("\n").join(this.configuration.getUrls()));
        }
        final long currentTimeMillis = System.currentTimeMillis();
        int n = 0;
        final ExecutorService executorService = this.configuration.getExecutorService();
        final ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        for (final URL url : this.configuration.getUrls()) {
            try {
                if (executorService != null) {
                    arrayList.add(executorService.submit(new Runnable() {
                        final /* synthetic */ URL val$url;
                        final /* synthetic */ Reflections this$0;
                        
                        Reflections$1() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            if (Reflections.log != null && Reflections.log.isDebugEnabled()) {
                                Reflections.log.debug("[" + Thread.currentThread().toString() + "] scanning " + url);
                            }
                            this.this$0.scan(url);
                        }
                    }));
                }
                else {
                    this.scan(url);
                }
                ++n;
            }
            catch (ReflectionsException ex) {
                if (Reflections.log == null || !Reflections.log.isWarnEnabled()) {
                    continue;
                }
                Reflections.log.warn("could not create Vfs.Dir from url. ignoring the exception and continuing", ex);
            }
        }
        if (executorService != null) {
            for (final Future<?> future : arrayList) {
                try {
                    future.get();
                }
                catch (Exception ex2) {
                    throw new RuntimeException(ex2);
                }
            }
        }
        final long n2 = System.currentTimeMillis() - currentTimeMillis;
        if (executorService != null) {
            executorService.shutdown();
        }
        if (Reflections.log != null) {
            int n3 = 0;
            int n4 = 0;
            for (final String s : this.store.keySet()) {
                n3 += this.store.get(s).keySet().size();
                n4 += this.store.get(s).size();
            }
            Reflections.log.info(String.format("Reflections took %d ms to scan %d urls, producing %d keys and %d values %s", n2, n, n3, n4, (executorService != null && executorService instanceof ThreadPoolExecutor) ? String.format("[using %d cores]", ((ThreadPoolExecutor)executorService).getMaximumPoolSize()) : ""));
        }
    }
    
    protected void scan(final URL url) {
        final Vfs.Dir fromURL = Vfs.fromURL(url);
        try {
            for (final Vfs.File file : fromURL.getFiles()) {
                final Predicate<String> inputsFilter = this.configuration.getInputsFilter();
                final String relativePath = file.getRelativePath();
                final String replace = relativePath.replace('/', '.');
                if (inputsFilter == null || inputsFilter.apply(relativePath) || inputsFilter.apply(replace)) {
                    Object scan = null;
                    for (final Scanner scanner : this.configuration.getScanners()) {
                        try {
                            if (!scanner.acceptsInput(relativePath) && !scanner.acceptResult(replace)) {
                                continue;
                            }
                            scan = scanner.scan(file, scan);
                        }
                        catch (Exception ex) {
                            if (Reflections.log == null || !Reflections.log.isDebugEnabled()) {
                                continue;
                            }
                            Reflections.log.debug("could not scan file " + file.getRelativePath() + " in url " + url.toExternalForm() + " with scanner " + scanner.getClass().getSimpleName(), ex);
                        }
                    }
                }
            }
        }
        finally {
            fromURL.close();
        }
    }
    
    public static Reflections collect() {
        return collect("META-INF/reflections/", new FilterBuilder().include(".*-reflections.xml"), new Serializer[0]);
    }
    
    public static Reflections collect(final String s, final Predicate<String> predicate, @Nullable final Serializer... array) {
        final Serializer serializer = (array != null && array.length == 1) ? array[0] : new XmlSerializer();
        final Collection<URL> forPackage = ClasspathHelper.forPackage(s, new ClassLoader[0]);
        if (forPackage.isEmpty()) {
            return null;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final Reflections reflections = new Reflections();
        for (final Vfs.File file : Vfs.findFiles(forPackage, s, predicate)) {
            InputStream openInputStream = null;
            try {
                openInputStream = file.openInputStream();
                reflections.merge(serializer.read(openInputStream));
            }
            catch (IOException ex) {
                throw new ReflectionsException("could not merge " + file, ex);
            }
            finally {
                Utils.close(openInputStream);
            }
        }
        if (Reflections.log != null) {
            final Store store = reflections.getStore();
            int n = 0;
            int n2 = 0;
            for (final String s2 : store.keySet()) {
                n += store.get(s2).keySet().size();
                n2 += store.get(s2).size();
            }
            Reflections.log.info(String.format("Reflections took %d ms to collect %d url%s, producing %d keys and %d values [%s]", System.currentTimeMillis() - currentTimeMillis, forPackage.size(), (forPackage.size() > 1) ? "s" : "", n, n2, Joiner.on(", ").join(forPackage)));
        }
        return reflections;
    }
    
    public Reflections collect(final InputStream inputStream) {
        try {
            this.merge(this.configuration.getSerializer().read(inputStream));
            if (Reflections.log != null) {
                Reflections.log.info("Reflections collected metadata from input stream using serializer " + this.configuration.getSerializer().getClass().getName());
            }
        }
        catch (Exception ex) {
            throw new ReflectionsException("could not merge input stream", ex);
        }
        return this;
    }
    
    public Reflections collect(final File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return this.collect(inputStream);
        }
        catch (FileNotFoundException ex) {
            throw new ReflectionsException("could not obtain input stream from file " + file, ex);
        }
        finally {
            Utils.close(inputStream);
        }
    }
    
    public Reflections merge(final Reflections reflections) {
        if (reflections.store != null) {
            for (final String s : reflections.store.keySet()) {
                final Multimap<String, String> value = reflections.store.get(s);
                for (final String s2 : value.keySet()) {
                    final Iterator<String> iterator3 = value.get(s2).iterator();
                    while (iterator3.hasNext()) {
                        this.store.getOrCreate(s).put((Object)s2, (Object)iterator3.next());
                    }
                }
            }
        }
        return this;
    }
    
    public void expandSuperTypes() {
        if (this.store.keySet().contains(index(SubTypesScanner.class))) {
            final Multimap<String, String> value = this.store.get(index(SubTypesScanner.class));
            final Sets.SetView difference = Sets.difference((Set)value.keySet(), (Set)Sets.<Object>newHashSet((Iterable<?>)value.values()));
            final HashMultimap create = HashMultimap.create();
            for (final String s : difference) {
                final Class<?> forName = ReflectionUtils.forName(s, new ClassLoader[0]);
                if (forName != null) {
                    this.expandSupertypes((Multimap<String, String>)create, s, forName);
                }
            }
            value.putAll((Multimap)create);
        }
    }
    
    private void expandSupertypes(final Multimap<String, String> multimap, final String s, final Class<?> clazz) {
        for (final Class<?> clazz2 : ReflectionUtils.getSuperTypes(clazz)) {
            if (multimap.put((Object)clazz2.getName(), (Object)s)) {
                if (Reflections.log != null) {
                    Reflections.log.debug("expanded subtype {} -> {}", clazz2.getName(), s);
                }
                this.expandSupertypes(multimap, clazz2.getName(), clazz2);
            }
        }
    }
    
    public <T> Set<Class<? extends T>> getSubTypesOf(final Class<T> clazz) {
        return (Set<Class<? extends T>>)Sets.<Object>newHashSet((Iterable<?>)ReflectionUtils.<Object>forNames(this.store.getAll(index(SubTypesScanner.class), Arrays.<String>asList(clazz.getName())), this.loaders()));
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> clazz) {
        return this.getTypesAnnotatedWith(clazz, false);
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Class<? extends Annotation> clazz, final boolean b) {
        final Iterable<String> value = this.store.get(index(TypeAnnotationsScanner.class), clazz.getName());
        return (Set<Class<?>>)Sets.<Object>newHashSet((Iterable<?>)Iterables.<Object>concat((Iterable<?>)ReflectionUtils.<Object>forNames(value, this.loaders()), (Iterable<?>)ReflectionUtils.<Object>forNames(this.getAllAnnotated(value, clazz.isAnnotationPresent(Inherited.class), b), this.loaders())));
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Annotation annotation) {
        return this.getTypesAnnotatedWith(annotation, false);
    }
    
    public Set<Class<?>> getTypesAnnotatedWith(final Annotation annotation, final boolean b) {
        final Iterable<String> value = this.store.get(index(TypeAnnotationsScanner.class), annotation.annotationType().getName());
        final Set<Class<?>> filter = ReflectionUtils.<Class<?>>filter(ReflectionUtils.<Object>forNames(value, this.loaders()), ReflectionUtils.<AnnotatedElement>withAnnotation(annotation));
        return (Set<Class<?>>)Sets.<Object>newHashSet((Iterable<?>)Iterables.<Object>concat((Iterable<?>)filter, (Iterable<?>)ReflectionUtils.<Object>forNames(ReflectionUtils.<String>filter(this.getAllAnnotated(Utils.names(filter), annotation.annotationType().isAnnotationPresent(Inherited.class), b), Predicates.not((Predicate)Predicates.<Object>in((Collection<?>)Sets.<Object>newHashSet((Iterable<?>)value)))), this.loaders())));
    }
    
    protected Iterable<String> getAllAnnotated(final Iterable<String> a, final boolean b, final boolean b2) {
        if (!b2) {
            final Iterable<String> concat = Iterables.<String>concat((Iterable<? extends String>)a, (Iterable<? extends String>)this.store.getAll(index(TypeAnnotationsScanner.class), a));
            return (Iterable<String>)Iterables.<Object>concat((Iterable<?>)concat, (Iterable<?>)this.store.getAll(index(SubTypesScanner.class), concat));
        }
        if (b) {
            final Iterable<String> value = this.store.get(index(SubTypesScanner.class), (Iterable<String>)ReflectionUtils.<Object>filter((Iterable<Object>)a, new Predicate<String>() {
                final /* synthetic */ Reflections this$0;
                
                Reflections$2() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public boolean apply(@Nullable final String s) {
                    final Class<?> forName = ReflectionUtils.forName(s, Reflections.this.loaders());
                    return forName != null && !forName.isInterface();
                }
                
                @Override
                public /* bridge */ boolean apply(@Nullable final Object o) {
                    return this.apply((String)o);
                }
            }));
            return (Iterable<String>)Iterables.<Object>concat((Iterable<?>)value, (Iterable<?>)this.store.getAll(index(SubTypesScanner.class), value));
        }
        return a;
    }
    
    public Set<Method> getMethodsAnnotatedWith(final Class<? extends Annotation> clazz) {
        return Utils.getMethodsFromDescriptors(this.store.get(index(MethodAnnotationsScanner.class), clazz.getName()), this.loaders());
    }
    
    public Set<Method> getMethodsAnnotatedWith(final Annotation annotation) {
        return ReflectionUtils.<Method>filter(this.getMethodsAnnotatedWith(annotation.annotationType()), ReflectionUtils.<AnnotatedElement>withAnnotation(annotation));
    }
    
    public Set<Method> getMethodsMatchParams(final Class<?>... array) {
        return Utils.getMethodsFromDescriptors(this.store.get(index(MethodParameterScanner.class), Utils.names(array).toString()), this.loaders());
    }
    
    public Set<Method> getMethodsReturn(final Class clazz) {
        return Utils.getMethodsFromDescriptors(this.store.get(index(MethodParameterScanner.class), Utils.names(clazz)), this.loaders());
    }
    
    public Set<Method> getMethodsWithAnyParamAnnotated(final Class<? extends Annotation> clazz) {
        return Utils.getMethodsFromDescriptors(this.store.get(index(MethodParameterScanner.class), clazz.getName()), this.loaders());
    }
    
    public Set<Method> getMethodsWithAnyParamAnnotated(final Annotation annotation) {
        return ReflectionUtils.<Method>filter(this.getMethodsWithAnyParamAnnotated(annotation.annotationType()), ReflectionUtils.withAnyParameterAnnotation(annotation));
    }
    
    public Set<Constructor> getConstructorsAnnotatedWith(final Class<? extends Annotation> clazz) {
        return Utils.getConstructorsFromDescriptors(this.store.get(index(MethodAnnotationsScanner.class), clazz.getName()), this.loaders());
    }
    
    public Set<Constructor> getConstructorsAnnotatedWith(final Annotation annotation) {
        return (Set<Constructor>)ReflectionUtils.<Constructor>filter(this.getConstructorsAnnotatedWith(annotation.annotationType()), ReflectionUtils.<AnnotatedElement>withAnnotation(annotation));
    }
    
    public Set<Constructor> getConstructorsMatchParams(final Class<?>... array) {
        return Utils.getConstructorsFromDescriptors(this.store.get(index(MethodParameterScanner.class), Utils.names(array).toString()), this.loaders());
    }
    
    public Set<Constructor> getConstructorsWithAnyParamAnnotated(final Class<? extends Annotation> clazz) {
        return Utils.getConstructorsFromDescriptors(this.store.get(index(MethodParameterScanner.class), clazz.getName()), this.loaders());
    }
    
    public Set<Constructor> getConstructorsWithAnyParamAnnotated(final Annotation annotation) {
        return (Set<Constructor>)ReflectionUtils.<Constructor>filter(this.getConstructorsWithAnyParamAnnotated(annotation.annotationType()), ReflectionUtils.withAnyParameterAnnotation(annotation));
    }
    
    public Set<Field> getFieldsAnnotatedWith(final Class<? extends Annotation> clazz) {
        final HashSet<Field> hashSet = Sets.<Field>newHashSet();
        final Iterator<String> iterator = this.store.get(index(FieldAnnotationsScanner.class), clazz.getName()).iterator();
        while (iterator.hasNext()) {
            hashSet.add(Utils.getFieldFromString(iterator.next(), this.loaders()));
        }
        return hashSet;
    }
    
    public Set<Field> getFieldsAnnotatedWith(final Annotation annotation) {
        return ReflectionUtils.<Field>filter(this.getFieldsAnnotatedWith(annotation.annotationType()), ReflectionUtils.<AnnotatedElement>withAnnotation(annotation));
    }
    
    public Set<String> getResources(final Predicate<String> predicate) {
        return (Set<String>)Sets.<Object>newHashSet((Iterable<?>)this.store.get(index(ResourcesScanner.class), Iterables.<String>filter((Iterable<String>)this.store.get(index(ResourcesScanner.class)).keySet(), predicate)));
    }
    
    public Set<String> getResources(final Pattern pattern) {
        return this.getResources(new Predicate<String>() {
            final /* synthetic */ Pattern val$pattern;
            final /* synthetic */ Reflections this$0;
            
            Reflections$3() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public boolean apply(final String s) {
                return pattern.matcher(s).matches();
            }
            
            @Override
            public /* bridge */ boolean apply(final Object o) {
                return this.apply((String)o);
            }
        });
    }
    
    public List<String> getMethodParamNames(final Method method) {
        final Iterable<String> value = this.store.get(index(MethodParameterNamesScanner.class), Utils.name(method));
        return Iterables.isEmpty(value) ? Arrays.<String>asList(new String[0]) : Arrays.<String>asList(Iterables.<String>getOnlyElement(value).split(", "));
    }
    
    public List<String> getConstructorParamNames(final Constructor constructor) {
        final Iterable<String> value = this.store.get(index(MethodParameterNamesScanner.class), Utils.name(constructor));
        return Iterables.isEmpty(value) ? Arrays.<String>asList(new String[0]) : Arrays.<String>asList(Iterables.<String>getOnlyElement(value).split(", "));
    }
    
    public Set<Member> getFieldUsage(final Field field) {
        return Utils.getMembersFromDescriptors(this.store.get(index(MemberUsageScanner.class), Utils.name(field)), new ClassLoader[0]);
    }
    
    public Set<Member> getMethodUsage(final Method method) {
        return Utils.getMembersFromDescriptors(this.store.get(index(MemberUsageScanner.class), Utils.name(method)), new ClassLoader[0]);
    }
    
    public Set<Member> getConstructorUsage(final Constructor constructor) {
        return Utils.getMembersFromDescriptors(this.store.get(index(MemberUsageScanner.class), Utils.name(constructor)), new ClassLoader[0]);
    }
    
    public Set<String> getAllTypes() {
        final HashSet<String> hashSet = Sets.<String>newHashSet((Iterable<? extends String>)this.store.getAll(index(SubTypesScanner.class), Object.class.getName()));
        if (hashSet.isEmpty()) {
            throw new ReflectionsException("Couldn't find subtypes of Object. Make sure SubTypesScanner initialized to include Object class - new SubTypesScanner(false)");
        }
        return hashSet;
    }
    
    public Store getStore() {
        return this.store;
    }
    
    public Configuration getConfiguration() {
        return this.configuration;
    }
    
    public File save(final String s) {
        return this.save(s, this.configuration.getSerializer());
    }
    
    public File save(final String s, final Serializer serializer) {
        final File save = serializer.save(this, s);
        if (Reflections.log != null) {
            Reflections.log.info("Reflections successfully saved in " + save.getAbsolutePath() + " using " + serializer.getClass().getSimpleName());
        }
        return save;
    }
    
    private static String index(final Class<? extends Scanner> clazz) {
        return clazz.getSimpleName();
    }
    
    private ClassLoader[] loaders() {
        return this.configuration.getClassLoaders();
    }
    
    static /* synthetic */ ClassLoader[] access$000(final Reflections reflections) {
        return reflections.loaders();
    }
    
    static {
        Reflections.log = Utils.findLogger(Reflections.class);
    }
}
