package org.reflections.serializers;

import com.google.common.collect.Multimap;
import java.lang.reflect.Method;
import org.reflections.ReflectionUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import org.reflections.ReflectionsException;
import java.util.LinkedList;
import com.google.common.base.Joiner;
import com.google.common.collect.SetMultimap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import java.util.Set;
import com.google.common.base.Supplier;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import com.google.common.collect.Lists;
import org.reflections.scanners.TypeElementsScanner;
import java.io.IOException;
import com.google.common.io.Files;
import java.nio.charset.Charset;
import java.util.Date;
import org.reflections.util.Utils;
import java.io.File;
import org.reflections.Reflections;
import java.io.InputStream;

public class JavaCodeSerializer implements Serializer
{
    private static final String pathSeparator = "_";
    private static final String doubleSeparator = "__";
    private static final String dotSeparator = ".";
    private static final String arrayDescriptor = "$$";
    private static final String tokenSeparator = "_";
    
    public JavaCodeSerializer() {
        super();
    }
    
    @Override
    public Reflections read(final InputStream inputStream) {
        throw new UnsupportedOperationException("read is not implemented on JavaCodeSerializer");
    }
    
    @Override
    public File save(final Reflections reflections, String substring) {
        if (substring.endsWith("/")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        final String concat = substring.replace('.', '/').concat(".java");
        final File prepareFile = Utils.prepareFile(concat);
        final int lastIndex = substring.lastIndexOf(46);
        String substring2;
        String s;
        if (lastIndex == -1) {
            substring2 = "";
            s = substring.substring(substring.lastIndexOf(47) + 1);
        }
        else {
            substring2 = substring.substring(substring.lastIndexOf(47) + 1, lastIndex);
            s = substring.substring(lastIndex + 1);
        }
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("//generated using Reflections JavaCodeSerializer").append(" [").append(new Date()).append("]").append("\n");
            if (substring2.length() != 0) {
                sb.append("package ").append(substring2).append(";\n");
                sb.append("\n");
            }
            sb.append("public interface ").append(s).append(" {\n\n");
            sb.append(this.toString(reflections));
            sb.append("}\n");
            Files.write((CharSequence)sb.toString(), new File(concat), Charset.defaultCharset());
        }
        catch (IOException ex) {
            throw new RuntimeException();
        }
        return prepareFile;
    }
    
    @Override
    public String toString(final Reflections reflections) {
        if (reflections.getStore().get(TypeElementsScanner.class.getSimpleName()).isEmpty() && Reflections.log != null) {
            Reflections.log.warn("JavaCodeSerializer needs TypeElementsScanner configured");
        }
        final StringBuilder sb = new StringBuilder();
        ArrayList<Object> arrayList = Lists.<Object>newArrayList();
        int n = 1;
        final ArrayList<Object> arrayList2 = Lists.<Object>newArrayList((Iterable<?>)reflections.getStore().get(TypeElementsScanner.class.getSimpleName()).keySet());
        Collections.<Comparable>sort((List<Comparable>)arrayList2);
        for (final String s : arrayList2) {
            ArrayList<String> arrayList3;
            int n2;
            for (arrayList3 = Lists.<String>newArrayList(s.split("\\.")), n2 = 0; n2 < Math.min(arrayList3.size(), arrayList.size()) && ((String)arrayList3.get(n2)).equals(arrayList.get(n2)); ++n2) {}
            for (int i = arrayList.size(); i > n2; --i) {
                sb.append(Utils.repeat("\t", --n)).append("}\n");
            }
            for (int j = n2; j < arrayList3.size() - 1; ++j) {
                sb.append(Utils.repeat("\t", n++)).append("public interface ").append(this.getNonDuplicateName((String)arrayList3.get(j), arrayList3, j)).append(" {\n");
            }
            final String s2 = arrayList3.get(arrayList3.size() - 1);
            final ArrayList<Object> arrayList4 = Lists.<Object>newArrayList();
            final ArrayList<Object> arrayList5 = Lists.<Object>newArrayList();
            final SetMultimap setMultimap = Multimaps.newSetMultimap((Map)new HashMap(), (Supplier)new Supplier<Set<String>>() {
                final /* synthetic */ JavaCodeSerializer this$0;
                
                JavaCodeSerializer$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public Set<String> get() {
                    return (Set<String>)Sets.<Object>newHashSet();
                }
                
                @Override
                public /* bridge */ Object get() {
                    return this.get();
                }
            });
            for (final String s3 : reflections.getStore().get(TypeElementsScanner.class.getSimpleName(), s)) {
                if (s3.startsWith("@")) {
                    arrayList4.add(s3.substring(1));
                }
                else if (s3.contains("(")) {
                    if (s3.startsWith("<")) {
                        continue;
                    }
                    final int index = s3.indexOf(40);
                    final String substring = s3.substring(0, index);
                    final String substring2 = s3.substring(index + 1, s3.indexOf(")"));
                    String string = "";
                    if (substring2.length() != 0) {
                        string = "_" + substring2.replace(".", "_").replace(", ", "__").replace("[]", "$$");
                    }
                    ((Multimap)setMultimap).put((Object)substring, (Object)(substring + string));
                }
                else {
                    if (Utils.isEmpty(s3)) {
                        continue;
                    }
                    arrayList5.add(s3);
                }
            }
            sb.append(Utils.repeat("\t", n++)).append("public interface ").append(this.getNonDuplicateName(s2, arrayList3, arrayList3.size() - 1)).append(" {\n");
            if (!arrayList5.isEmpty()) {
                sb.append(Utils.repeat("\t", n++)).append("public interface fields {\n");
                final Iterator<String> iterator3 = arrayList5.iterator();
                while (iterator3.hasNext()) {
                    sb.append(Utils.repeat("\t", n)).append("public interface ").append(this.getNonDuplicateName(iterator3.next(), arrayList3)).append(" {}\n");
                }
                sb.append(Utils.repeat("\t", --n)).append("}\n");
            }
            if (!((Multimap)setMultimap).isEmpty()) {
                sb.append(Utils.repeat("\t", n++)).append("public interface methods {\n");
                for (final Map.Entry<String, V> entry : ((Multimap)setMultimap).entries()) {
                    final String s4 = entry.getKey();
                    final String s5 = (String)entry.getValue();
                    sb.append(Utils.repeat("\t", n)).append("public interface ").append(this.getNonDuplicateName(this.getNonDuplicateName((((Multimap<String, Object>)setMultimap).get(s4).size() == 1) ? s4 : s5, (List<String>)arrayList5), arrayList3)).append(" {}\n");
                }
                sb.append(Utils.repeat("\t", --n)).append("}\n");
            }
            if (!arrayList4.isEmpty()) {
                sb.append(Utils.repeat("\t", n++)).append("public interface annotations {\n");
                final Iterator<String> iterator5 = arrayList4.iterator();
                while (iterator5.hasNext()) {
                    sb.append(Utils.repeat("\t", n)).append("public interface ").append(this.getNonDuplicateName(iterator5.next(), arrayList3)).append(" {}\n");
                }
                sb.append(Utils.repeat("\t", --n)).append("}\n");
            }
            arrayList = (ArrayList<Object>)arrayList3;
        }
        for (int k = arrayList.size(); k >= 1; --k) {
            sb.append(Utils.repeat("\t", k)).append("}\n");
        }
        return sb.toString();
    }
    
    private String getNonDuplicateName(final String s, final List<String> list, final int n) {
        final String normalize = this.normalize(s);
        for (int i = 0; i < n; ++i) {
            if (normalize.equals(list.get(i))) {
                return this.getNonDuplicateName(normalize + "_", list, n);
            }
        }
        return normalize;
    }
    
    private String normalize(final String s) {
        return s.replace(".", "_");
    }
    
    private String getNonDuplicateName(final String s, final List<String> list) {
        return this.getNonDuplicateName(s, list, list.size());
    }
    
    public static Class<?> resolveClassOf(final Class clazz) throws ClassNotFoundException {
        Class declaringClass = clazz;
        final LinkedList linkedList = Lists.newLinkedList();
        while (declaringClass != null) {
            linkedList.addFirst(declaringClass.getSimpleName());
            declaringClass = declaringClass.getDeclaringClass();
        }
        return Class.forName(Joiner.on(".").join(linkedList.subList(1, linkedList.size())).replace(".$", "$"));
    }
    
    public static Class<?> resolveClass(final Class clazz) {
        try {
            return resolveClassOf(clazz);
        }
        catch (Exception ex) {
            throw new ReflectionsException("could not resolve to class " + clazz.getName(), ex);
        }
    }
    
    public static Field resolveField(final Class clazz) {
        try {
            return resolveClassOf(clazz.getDeclaringClass().getDeclaringClass()).getDeclaredField(clazz.getSimpleName());
        }
        catch (Exception ex) {
            throw new ReflectionsException("could not resolve to field " + clazz.getName(), ex);
        }
    }
    
    public static Annotation resolveAnnotation(final Class clazz) {
        try {
            return resolveClassOf(clazz.getDeclaringClass().getDeclaringClass()).<Annotation>getAnnotation(ReflectionUtils.forName(clazz.getSimpleName().replace("_", "."), new ClassLoader[0]));
        }
        catch (Exception ex) {
            throw new ReflectionsException("could not resolve to annotation " + clazz.getName(), ex);
        }
    }
    
    public static Method resolveMethod(final Class clazz) {
        final String simpleName = clazz.getSimpleName();
        try {
            String substring;
            Class<?>[] array;
            if (simpleName.contains("_")) {
                substring = simpleName.substring(0, simpleName.indexOf("_"));
                final String[] split = simpleName.substring(simpleName.indexOf("_") + 1).split("__");
                array = (Class<?>[])new Class[split.length];
                for (int i = 0; i < split.length; ++i) {
                    array[i] = ReflectionUtils.forName(split[i].replace("$$", "[]").replace("_", "."), new ClassLoader[0]);
                }
            }
            else {
                substring = simpleName;
                array = null;
            }
            return resolveClassOf(clazz.getDeclaringClass().getDeclaringClass()).getDeclaredMethod(substring, array);
        }
        catch (Exception ex) {
            throw new ReflectionsException("could not resolve to method " + clazz.getName(), ex);
        }
    }
}
