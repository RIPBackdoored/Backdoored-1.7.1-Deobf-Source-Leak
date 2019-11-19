package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.error.Mark;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import org.yaml.snakeyaml.error.YAMLException;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.ParseException;
import java.text.NumberFormat;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import org.yaml.snakeyaml.nodes.ScalarNode;
import java.math.BigInteger;
import java.util.Set;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Node;
import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import org.yaml.snakeyaml.nodes.NodeTuple;
import java.util.ArrayList;
import java.util.HashMap;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.Tag;
import java.util.regex.Pattern;
import java.util.Map;

public class SafeConstructor extends BaseConstructor
{
    public static final ConstructUndefined undefinedConstructor;
    private static final Map<String, Boolean> BOOL_VALUES;
    private static final Pattern TIMESTAMP_REGEXP;
    private static final Pattern YMD_REGEXP;
    
    public SafeConstructor() {
        super();
        this.yamlConstructors.put(Tag.NULL, new ConstructYamlNull());
        this.yamlConstructors.put(Tag.BOOL, new ConstructYamlBool());
        this.yamlConstructors.put(Tag.INT, new ConstructYamlInt());
        this.yamlConstructors.put(Tag.FLOAT, new ConstructYamlFloat());
        this.yamlConstructors.put(Tag.BINARY, new ConstructYamlBinary());
        this.yamlConstructors.put(Tag.TIMESTAMP, new ConstructYamlTimestamp());
        this.yamlConstructors.put(Tag.OMAP, new ConstructYamlOmap());
        this.yamlConstructors.put(Tag.PAIRS, new ConstructYamlPairs());
        this.yamlConstructors.put(Tag.SET, new ConstructYamlSet());
        this.yamlConstructors.put(Tag.STR, new ConstructYamlStr());
        this.yamlConstructors.put(Tag.SEQ, new ConstructYamlSeq());
        this.yamlConstructors.put(Tag.MAP, new ConstructYamlMap());
        this.yamlConstructors.put(null, SafeConstructor.undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.scalar, SafeConstructor.undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.sequence, SafeConstructor.undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.mapping, SafeConstructor.undefinedConstructor);
    }
    
    protected void flattenMapping(final MappingNode node) {
        this.processDuplicateKeys(node);
        if (node.isMerged()) {
            node.setValue(this.mergeNode(node, true, new HashMap<Object, Integer>(), new ArrayList<NodeTuple>()));
        }
    }
    
    protected void processDuplicateKeys(final MappingNode node) {
        final List<NodeTuple> nodeValue = node.getValue();
        final Map<Object, Integer> keys = new HashMap<Object, Integer>(nodeValue.size());
        final Deque<Integer> toRemove = new ArrayDeque<Integer>();
        int i = 0;
        for (final NodeTuple tuple : nodeValue) {
            final Node keyNode = tuple.getKeyNode();
            if (!keyNode.getTag().equals(Tag.MERGE)) {
                final Object key = this.constructObject(keyNode);
                if (key != null) {
                    try {
                        key.hashCode();
                    }
                    catch (Exception e) {
                        throw new ConstructorException("while constructing a mapping", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
                    }
                }
                final Integer prevIndex = keys.put(key, i);
                if (prevIndex != null) {
                    if (!this.isAllowDuplicateKeys()) {
                        throw new IllegalStateException("duplicate key: " + key);
                    }
                    toRemove.add(prevIndex);
                }
            }
            ++i;
        }
        final Iterator<Integer> indicies2remove = toRemove.descendingIterator();
        while (indicies2remove.hasNext()) {
            nodeValue.remove((int)indicies2remove.next());
        }
    }
    
    private List<NodeTuple> mergeNode(final MappingNode node, final boolean isPreffered, final Map<Object, Integer> key2index, final List<NodeTuple> values) {
        final Iterator<NodeTuple> iter = node.getValue().iterator();
        while (iter.hasNext()) {
            final NodeTuple nodeTuple = iter.next();
            final Node keyNode = nodeTuple.getKeyNode();
            final Node valueNode = nodeTuple.getValueNode();
            if (keyNode.getTag().equals(Tag.MERGE)) {
                iter.remove();
                switch (valueNode.getNodeId()) {
                    case mapping: {
                        final MappingNode mn = (MappingNode)valueNode;
                        this.mergeNode(mn, false, key2index, values);
                        continue;
                    }
                    case sequence: {
                        final SequenceNode sn = (SequenceNode)valueNode;
                        final List<Node> vals = sn.getValue();
                        for (final Node subnode : vals) {
                            if (!(subnode instanceof MappingNode)) {
                                throw new ConstructorException("while constructing a mapping", node.getStartMark(), "expected a mapping for merging, but found " + subnode.getNodeId(), subnode.getStartMark());
                            }
                            final MappingNode mnode = (MappingNode)subnode;
                            this.mergeNode(mnode, false, key2index, values);
                        }
                        continue;
                    }
                    default:
                        throw new ConstructorException("while constructing a mapping", node.getStartMark(), "expected a mapping or list of mappings for merging, but found " + valueNode.getNodeId(), valueNode.getStartMark());
                }
            }
            else {
                final Object key = this.constructObject(keyNode);
                if (!key2index.containsKey(key)) {
                    values.add(nodeTuple);
                    key2index.put(key, values.size() - 1);
                }
                else {
                    if (!isPreffered) {
                        continue;
                    }
                    values.set(key2index.get(key), nodeTuple);
                }
            }
        }
        return values;
    }
    
    @Override
    protected void constructMapping2ndStep(final MappingNode node, final Map<Object, Object> mapping) {
        this.flattenMapping(node);
        super.constructMapping2ndStep(node, mapping);
    }
    
    @Override
    protected void constructSet2ndStep(final MappingNode node, final Set<Object> set) {
        this.flattenMapping(node);
        super.constructSet2ndStep(node, set);
    }
    
    private Number createNumber(final int sign, String number, final int radix) {
        if (sign < 0) {
            number = "-" + number;
        }
        Number result;
        try {
            result = Integer.valueOf(number, radix);
        }
        catch (NumberFormatException e) {
            try {
                result = Long.valueOf(number, radix);
            }
            catch (NumberFormatException e2) {
                result = new BigInteger(number, radix);
            }
        }
        return result;
    }
    
    static /* synthetic */ Map access$000() {
        return SafeConstructor.BOOL_VALUES;
    }
    
    static /* synthetic */ Number access$100(final SafeConstructor x0, final int x1, final String x2, final int x3) {
        return x0.createNumber(x1, x2, x3);
    }
    
    static /* synthetic */ Pattern access$200() {
        return SafeConstructor.YMD_REGEXP;
    }
    
    static /* synthetic */ Pattern access$300() {
        return SafeConstructor.TIMESTAMP_REGEXP;
    }
    
    static {
        undefinedConstructor = new ConstructUndefined();
        (BOOL_VALUES = new HashMap<String, Boolean>()).put("yes", Boolean.TRUE);
        SafeConstructor.BOOL_VALUES.put("no", Boolean.FALSE);
        SafeConstructor.BOOL_VALUES.put("true", Boolean.TRUE);
        SafeConstructor.BOOL_VALUES.put("false", Boolean.FALSE);
        SafeConstructor.BOOL_VALUES.put("on", Boolean.TRUE);
        SafeConstructor.BOOL_VALUES.put("off", Boolean.FALSE);
        TIMESTAMP_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)(?:(?:[Tt]|[ \t]+)([0-9][0-9]?):([0-9][0-9]):([0-9][0-9])(?:\\.([0-9]*))?(?:[ \t]*(?:Z|([-+][0-9][0-9]?)(?::([0-9][0-9])?)?))?)?$");
        YMD_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)$");
    }
}
