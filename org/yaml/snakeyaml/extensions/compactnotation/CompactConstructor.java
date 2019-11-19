package org.yaml.snakeyaml.extensions.compactnotation;

import org.yaml.snakeyaml.nodes.SequenceNode;
import java.util.Set;
import java.util.List;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import java.util.regex.Matcher;
import org.yaml.snakeyaml.introspector.Property;
import java.util.Iterator;
import org.yaml.snakeyaml.error.YAMLException;
import java.util.Map;
import java.util.HashMap;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.constructor.Construct;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.constructor.Constructor;

public class CompactConstructor extends Constructor
{
    private static final Pattern GUESS_COMPACT;
    private static final Pattern FIRST_PATTERN;
    private static final Pattern PROPERTY_NAME_PATTERN;
    private Construct compactConstruct;
    
    public CompactConstructor() {
        super();
    }
    
    protected Object constructCompactFormat(final ScalarNode scalarNode, final CompactData compactData) {
        try {
            final Object instance = this.createInstance(scalarNode, compactData);
            this.setProperties(instance, new HashMap<String, Object>(compactData.getProperties()));
            return instance;
        }
        catch (Exception cause) {
            throw new YAMLException(cause);
        }
    }
    
    protected Object createInstance(final ScalarNode scalarNode, final CompactData compactData) throws Exception {
        final Class<?> classForName = this.getClassForName(compactData.getPrefix());
        final Class[] array = new Class[compactData.getArguments().size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = String.class;
        }
        final java.lang.reflect.Constructor<?> declaredConstructor = classForName.getDeclaredConstructor((Class<?>[])array);
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(compactData.getArguments().toArray());
    }
    
    protected void setProperties(final Object o, final Map<String, Object> map) throws Exception {
        if (map == null) {
            throw new NullPointerException("Data for Compact Object Notation cannot be null.");
        }
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            final String name = entry.getKey();
            final Property property = this.getPropertyUtils().getProperty(o.getClass(), name);
            try {
                property.set(o, entry.getValue());
            }
            catch (IllegalArgumentException ex) {
                throw new YAMLException("Cannot set property='" + name + "' with value='" + map.get(name) + "' (" + map.get(name).getClass() + ") in " + o);
            }
        }
    }
    
    public CompactData getCompactData(final String s) {
        if (!s.endsWith(")")) {
            return null;
        }
        if (s.indexOf(40) < 0) {
            return null;
        }
        final Matcher matcher = CompactConstructor.FIRST_PATTERN.matcher(s);
        if (!matcher.matches()) {
            return null;
        }
        final String trim = matcher.group(1).trim();
        final String group = matcher.group(3);
        final CompactData compactData = new CompactData(trim);
        if (group.length() == 0) {
            return compactData;
        }
        final String[] split = group.split("\\s*,\\s*");
        for (int i = 0; i < split.length; ++i) {
            final String s2 = split[i];
            if (s2.indexOf(61) < 0) {
                compactData.getArguments().add(s2);
            }
            else {
                final Matcher matcher2 = CompactConstructor.PROPERTY_NAME_PATTERN.matcher(s2);
                if (!matcher2.matches()) {
                    return null;
                }
                compactData.getProperties().put(matcher2.group(1), matcher2.group(2).trim());
            }
        }
        return compactData;
    }
    
    private Construct getCompactConstruct() {
        if (this.compactConstruct == null) {
            this.compactConstruct = this.createCompactConstruct();
        }
        return this.compactConstruct;
    }
    
    protected Construct createCompactConstruct() {
        return new ConstructCompactObject();
    }
    
    @Override
    protected Construct getConstructor(final Node node) {
        if (node instanceof MappingNode) {
            final List<NodeTuple> value = ((MappingNode)node).getValue();
            if (value.size() == 1) {
                final Node keyNode = value.get(0).getKeyNode();
                if (keyNode instanceof ScalarNode && CompactConstructor.GUESS_COMPACT.matcher(((ScalarNode)keyNode).getValue()).matches()) {
                    return this.getCompactConstruct();
                }
            }
        }
        else if (node instanceof ScalarNode && CompactConstructor.GUESS_COMPACT.matcher(((ScalarNode)node).getValue()).matches()) {
            return this.getCompactConstruct();
        }
        return super.getConstructor(node);
    }
    
    protected void applySequence(final Object o, final List<?> list) {
        try {
            this.getPropertyUtils().getProperty(o.getClass(), this.getSequencePropertyName(o.getClass())).set(o, list);
        }
        catch (Exception cause) {
            throw new YAMLException(cause);
        }
    }
    
    protected String getSequencePropertyName(final Class<?> type) {
        final Set<Property> properties = this.getPropertyUtils().getProperties(type);
        final Iterator<Property> iterator = properties.iterator();
        while (iterator.hasNext()) {
            if (!List.class.isAssignableFrom(iterator.next().getType())) {
                iterator.remove();
            }
        }
        if (properties.size() == 0) {
            throw new YAMLException("No list property found in " + type);
        }
        if (properties.size() > 1) {
            throw new YAMLException("Many list properties found in " + type + "; Please override getSequencePropertyName() to specify which property to use.");
        }
        return properties.iterator().next().getName();
    }
    
    static /* synthetic */ List access$000(final CompactConstructor compactConstructor, final SequenceNode node) {
        return compactConstructor.constructSequence(node);
    }
    
    static /* synthetic */ Object access$100(final CompactConstructor compactConstructor, final ScalarNode node) {
        return compactConstructor.constructScalar(node);
    }
    
    static {
        GUESS_COMPACT = Pattern.compile("\\p{Alpha}.*\\s*\\((?:,?\\s*(?:(?:\\w*)|(?:\\p{Alpha}\\w*\\s*=.+))\\s*)+\\)");
        FIRST_PATTERN = Pattern.compile("(\\p{Alpha}.*)(\\s*)\\((.*?)\\)");
        PROPERTY_NAME_PATTERN = Pattern.compile("\\s*(\\p{Alpha}\\w*)\\s*=(.+)");
    }
}
