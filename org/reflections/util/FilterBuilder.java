package org.reflections.util;

import java.util.regex.Pattern;
import org.reflections.ReflectionsException;
import java.util.ArrayList;
import java.util.Iterator;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.List;
import com.google.common.base.Predicate;

public class FilterBuilder implements Predicate<String>
{
    private final List<Predicate<String>> chain;
    
    public FilterBuilder() {
        super();
        this.chain = (List<Predicate<String>>)Lists.<Object>newArrayList();
    }
    
    private FilterBuilder(final Iterable<Predicate<String>> elements) {
        super();
        this.chain = (List<Predicate<String>>)Lists.<Object>newArrayList((Iterable<?>)elements);
    }
    
    public FilterBuilder include(final String s) {
        return this.add(new Include(s));
    }
    
    public FilterBuilder exclude(final String s) {
        this.add(new Exclude(s));
        return this;
    }
    
    public FilterBuilder add(final Predicate<String> predicate) {
        this.chain.add(predicate);
        return this;
    }
    
    public FilterBuilder includePackage(final Class<?> clazz) {
        return this.add(new Include(packageNameRegex(clazz)));
    }
    
    public FilterBuilder excludePackage(final Class<?> clazz) {
        return this.add(new Exclude(packageNameRegex(clazz)));
    }
    
    public FilterBuilder includePackage(final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.add(new Include(prefix(array[i])));
        }
        return this;
    }
    
    public FilterBuilder excludePackage(final String s) {
        return this.add(new Exclude(prefix(s)));
    }
    
    private static String packageNameRegex(final Class<?> clazz) {
        return prefix(clazz.getPackage().getName() + ".");
    }
    
    public static String prefix(final String s) {
        return s.replace(".", "\\.") + ".*";
    }
    
    @Override
    public String toString() {
        return Joiner.on(", ").join(this.chain);
    }
    
    @Override
    public boolean apply(final String s) {
        boolean apply = this.chain == null || this.chain.isEmpty() || this.chain.get(0) instanceof Exclude;
        if (this.chain != null) {
            for (final Predicate<String> predicate : this.chain) {
                if (apply && predicate instanceof Include) {
                    continue;
                }
                if (predicate instanceof Exclude) {
                    continue;
                }
                apply = predicate.apply(s);
                if (predicate instanceof Exclude) {
                    break;
                }
            }
        }
        return apply;
    }
    
    public static FilterBuilder parse(final String s) {
        final ArrayList<Predicate<String>> list = new ArrayList<Predicate<String>>();
        if (!Utils.isEmpty(s)) {
            final String[] split = s.split(",");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String trim = split[i].trim();
                final char char1 = trim.charAt(0);
                final String substring = trim.substring(1);
                Matcher matcher = null;
                switch (char1) {
                    case 43:
                        matcher = new Include(substring);
                        break;
                    case 45:
                        matcher = new Exclude(substring);
                        break;
                    default:
                        throw new ReflectionsException("includeExclude should start with either + or -");
                }
                list.add(matcher);
            }
            return new FilterBuilder(list);
        }
        return new FilterBuilder();
    }
    
    public static FilterBuilder parsePackages(final String s) {
        final ArrayList<Predicate<String>> list = new ArrayList<Predicate<String>>();
        if (!Utils.isEmpty(s)) {
            final String[] split = s.split(",");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String trim = split[i].trim();
                final char char1 = trim.charAt(0);
                String s2 = trim.substring(1);
                if (!s2.endsWith(".")) {
                    s2 += ".";
                }
                final String prefix = prefix(s2);
                Matcher matcher = null;
                switch (char1) {
                    case 43:
                        matcher = new Include(prefix);
                        break;
                    case 45:
                        matcher = new Exclude(prefix);
                        break;
                    default:
                        throw new ReflectionsException("includeExclude should start with either + or -");
                }
                list.add(matcher);
            }
            return new FilterBuilder(list);
        }
        return new FilterBuilder();
    }
    
    @Override
    public /* bridge */ boolean apply(final Object o) {
        return this.apply((String)o);
    }
}
