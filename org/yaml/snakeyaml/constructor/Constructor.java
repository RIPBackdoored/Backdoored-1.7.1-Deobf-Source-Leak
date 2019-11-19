package org.yaml.snakeyaml.constructor;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Calendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.introspector.Property;
import java.util.Iterator;
import java.util.List;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.NodeTuple;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Properties;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import java.util.HashMap;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.nodes.Tag;
import java.util.Map;

public class Constructor extends SafeConstructor
{
    private final Map<Tag, Class<?>> typeTags;
    protected final Map<Class<?>, TypeDescription> typeDefinitions;
    
    public Constructor() {
        this(Object.class);
    }
    
    public Constructor(final Class<?> theRoot) {
        this(new TypeDescription(checkRoot(theRoot)));
    }
    
    private static Class<?> checkRoot(final Class<?> theRoot) {
        if (theRoot == null) {
            throw new NullPointerException("Root class must be provided.");
        }
        return theRoot;
    }
    
    public Constructor(final TypeDescription theRoot) {
        super();
        if (theRoot == null) {
            throw new NullPointerException("Root type must be provided.");
        }
        this.yamlConstructors.put(null, new ConstructYamlObject());
        if (!Object.class.equals(theRoot.getType())) {
            this.rootTag = new Tag(theRoot.getType());
        }
        this.typeTags = new HashMap<Tag, Class<?>>();
        this.typeDefinitions = new HashMap<Class<?>, TypeDescription>();
        this.yamlClassConstructors.put(NodeId.scalar, new ConstructScalar());
        this.yamlClassConstructors.put(NodeId.mapping, new ConstructMapping());
        this.yamlClassConstructors.put(NodeId.sequence, new ConstructSequence());
        this.addTypeDescription(theRoot);
    }
    
    public TypeDescription addTypeDescription(final TypeDescription definition) {
        if (definition == null) {
            throw new NullPointerException("TypeDescription is required.");
        }
        final Tag tag = definition.getTag();
        this.typeTags.put(tag, definition.getType());
        return this.typeDefinitions.put(definition.getType(), definition);
    }
    
    protected Class<?> getClassForNode(final Node node) {
        final Class<?> classForTag = this.typeTags.get(node.getTag());
        if (classForTag == null) {
            final String name = node.getTag().getClassName();
            Class<?> cl;
            try {
                cl = this.getClassForName(name);
            }
            catch (ClassNotFoundException e) {
                throw new YAMLException("Class not found: " + name);
            }
            this.typeTags.put(node.getTag(), cl);
            return cl;
        }
        return classForTag;
    }
    
    protected Class<?> getClassForName(final String name) throws ClassNotFoundException {
        try {
            return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
        }
        catch (ClassNotFoundException e) {
            return Class.forName(name);
        }
    }
}
