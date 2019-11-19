package com.sun.jna;

import java.util.LinkedHashMap;
import java.util.AbstractCollection;
import java.util.WeakHashMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Arrays;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.Buffer;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Map;

public abstract class Structure
{
    public static final int ALIGN_DEFAULT = 0;
    public static final int ALIGN_NONE = 1;
    public static final int ALIGN_GNUC = 2;
    public static final int ALIGN_MSVC = 3;
    protected static final int CALCULATE_SIZE = -1;
    static final Map<Class<?>, LayoutInfo> layoutInfo;
    static final Map<Class<?>, List<String>> fieldOrder;
    private Pointer memory;
    private int size;
    private int alignType;
    private String encoding;
    private int actualAlignType;
    private int structAlignment;
    private Map<String, StructField> structFields;
    private final Map<String, Object> nativeStrings;
    private TypeMapper typeMapper;
    private long typeInfo;
    private boolean autoRead;
    private boolean autoWrite;
    private Structure[] array;
    private boolean readCalled;
    private static final ThreadLocal<Map<Pointer, Structure>> reads;
    private static final ThreadLocal<Set<Structure>> busy;
    private static final Pointer PLACEHOLDER_MEMORY;
    
    protected Structure() {
        this(0);
    }
    
    protected Structure(final TypeMapper typeMapper) {
        this(null, 0, typeMapper);
    }
    
    protected Structure(final int n) {
        this(null, n);
    }
    
    protected Structure(final int n, final TypeMapper typeMapper) {
        this(null, n, typeMapper);
    }
    
    protected Structure(final Pointer pointer) {
        this(pointer, 0);
    }
    
    protected Structure(final Pointer pointer, final int n) {
        this(pointer, n, null);
    }
    
    protected Structure(final Pointer pointer, final int alignType, final TypeMapper typeMapper) {
        super();
        this.size = -1;
        this.nativeStrings = new HashMap<String, Object>();
        this.autoRead = true;
        this.autoWrite = true;
        this.setAlignType(alignType);
        this.setStringEncoding(Native.getStringEncoding(this.getClass()));
        this.initializeTypeMapper(typeMapper);
        this.validateFields();
        if (pointer != null) {
            this.useMemory(pointer, 0, true);
        }
        else {
            this.allocateMemory(-1);
        }
        this.initializeFields();
    }
    
    Map<String, StructField> fields() {
        return this.structFields;
    }
    
    TypeMapper getTypeMapper() {
        return this.typeMapper;
    }
    
    private void initializeTypeMapper(TypeMapper typeMapper) {
        if (typeMapper == null) {
            typeMapper = Native.getTypeMapper(this.getClass());
        }
        this.typeMapper = typeMapper;
        this.layoutChanged();
    }
    
    private void layoutChanged() {
        if (this.size != -1) {
            this.size = -1;
            if (this.memory instanceof AutoAllocated) {
                this.memory = null;
            }
            this.ensureAllocated();
        }
    }
    
    protected void setStringEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    protected String getStringEncoding() {
        return this.encoding;
    }
    
    protected void setAlignType(int structureAlignment) {
        this.alignType = structureAlignment;
        structureAlignment = Native.getStructureAlignment(this.getClass());
        if (Platform.isWindows()) {
            structureAlignment = 3;
        }
        else {
            structureAlignment = 2;
        }
        this.actualAlignType = structureAlignment;
        this.layoutChanged();
    }
    
    protected Memory autoAllocate(final int n) {
        return new AutoAllocated(n);
    }
    
    protected void useMemory(final Pointer pointer) {
        this.useMemory(pointer, 0);
    }
    
    protected void useMemory(final Pointer pointer, final int n) {
        this.useMemory(pointer, n, false);
    }
    
    void useMemory(final Pointer pointer, final int n, final boolean b) {
        try {
            this.nativeStrings.clear();
            if (this instanceof ByValue) {
                final byte[] array = new byte[this.size()];
                pointer.read(0L, array, 0, array.length);
                this.memory.write(0L, array, 0, array.length);
            }
            else {
                this.memory = pointer.share(n);
                if (this.size == -1) {
                    this.size = this.calculateSize(false);
                }
                if (this.size != -1) {
                    this.memory = pointer.share(n, this.size);
                }
            }
            this.array = null;
            this.readCalled = false;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Structure exceeds provided memory bounds", ex);
        }
    }
    
    protected void ensureAllocated() {
        this.ensureAllocated(false);
    }
    
    private void ensureAllocated(final boolean b) {
        if (this.memory == null) {
            this.allocateMemory(b);
        }
        else if (this.size == -1) {
            this.size = this.calculateSize(true, b);
            if (!(this.memory instanceof AutoAllocated)) {
                try {
                    this.memory = this.memory.share(0L, this.size);
                }
                catch (IndexOutOfBoundsException ex) {
                    throw new IllegalArgumentException("Structure exceeds provided memory bounds", ex);
                }
            }
        }
    }
    
    protected void allocateMemory() {
        this.allocateMemory(false);
    }
    
    private void allocateMemory(final boolean b) {
        this.allocateMemory(this.calculateSize(true, b));
    }
    
    protected void allocateMemory(int calculateSize) {
        if (calculateSize == -1) {
            calculateSize = this.calculateSize(false);
        }
        else if (calculateSize <= 0) {
            throw new IllegalArgumentException("Structure size must be greater than zero: " + calculateSize);
        }
        if (calculateSize != -1) {
            if (this.memory == null || this.memory instanceof AutoAllocated) {
                this.memory = this.autoAllocate(calculateSize);
            }
            this.size = calculateSize;
        }
    }
    
    public int size() {
        this.ensureAllocated();
        return this.size;
    }
    
    public void clear() {
        this.ensureAllocated();
        this.memory.clear(this.size());
    }
    
    public Pointer getPointer() {
        this.ensureAllocated();
        return this.memory;
    }
    
    static Set<Structure> busy() {
        return Structure.busy.get();
    }
    
    static Map<Pointer, Structure> reading() {
        return Structure.reads.get();
    }
    
    void conditionalAutoRead() {
        if (!this.readCalled) {
            this.autoRead();
        }
    }
    
    public void read() {
        if (this.memory == Structure.PLACEHOLDER_MEMORY) {
            return;
        }
        this.readCalled = true;
        this.ensureAllocated();
        if (busy().contains(this)) {
            return;
        }
        busy().add(this);
        if (this instanceof ByReference) {
            reading().put(this.getPointer(), this);
        }
        try {
            final Iterator<StructField> iterator = this.fields().values().iterator();
            while (iterator.hasNext()) {
                this.readField(iterator.next());
            }
        }
        finally {
            busy().remove(this);
            if (reading().get(this.getPointer()) == this) {
                reading().remove(this.getPointer());
            }
        }
    }
    
    protected int fieldOffset(final String s) {
        this.ensureAllocated();
        final StructField structField = this.fields().get(s);
        if (structField == null) {
            throw new IllegalArgumentException("No such field: " + s);
        }
        return structField.offset;
    }
    
    public Object readField(final String s) {
        this.ensureAllocated();
        final StructField structField = this.fields().get(s);
        if (structField == null) {
            throw new IllegalArgumentException("No such field: " + s);
        }
        return this.readField(structField);
    }
    
    Object getFieldValue(final Field field) {
        try {
            return field.get(this);
        }
        catch (Exception ex) {
            throw new Error("Exception reading field '" + field.getName() + "' in " + this.getClass(), ex);
        }
    }
    
    void setFieldValue(final Field field, final Object o) {
        this.setFieldValue(field, o, false);
    }
    
    private void setFieldValue(final Field field, final Object o, final boolean b) {
        try {
            field.set(this, o);
        }
        catch (IllegalAccessException ex) {
            if (!Modifier.isFinal(field.getModifiers())) {
                throw new Error("Unexpectedly unable to write to field '" + field.getName() + "' within " + this.getClass(), ex);
            }
            if (b) {
                throw new UnsupportedOperationException("This VM does not support Structures with final fields (field '" + field.getName() + "' within " + this.getClass() + ")", ex);
            }
            throw new UnsupportedOperationException("Attempt to write to read-only field '" + field.getName() + "' within " + this.getClass(), ex);
        }
    }
    
    static Structure updateStructureByReference(final Class<?> clazz, Structure instance, final Pointer pointer) {
        if (pointer == null) {
            instance = null;
        }
        else if (instance == null || !pointer.equals(instance.getPointer())) {
            final Structure structure = reading().get(pointer);
            if (structure != null && clazz.equals(structure.getClass())) {
                instance = structure;
                instance.autoRead();
            }
            else {
                instance = newInstance(clazz, pointer);
                instance.conditionalAutoRead();
            }
        }
        else {
            instance.autoRead();
        }
        return instance;
    }
    
    protected Object readField(final StructField structField) {
        final int offset = structField.offset;
        Class<?> clazz = structField.type;
        final FromNativeConverter readConverter = structField.readConverter;
        if (readConverter != null) {
            clazz = readConverter.nativeType();
        }
        final Object o = (Structure.class.isAssignableFrom(clazz) || Callback.class.isAssignableFrom(clazz) || (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz)) || Pointer.class.isAssignableFrom(clazz) || NativeMapped.class.isAssignableFrom(clazz) || clazz.isArray()) ? this.getFieldValue(structField.field) : null;
        Object o2;
        if (clazz == String.class) {
            final Pointer pointer = this.memory.getPointer(offset);
            o2 = ((pointer == null) ? null : pointer.getString(0L, this.encoding));
        }
        else {
            o2 = this.memory.getValue(offset, clazz, o);
        }
        if (readConverter != null) {
            o2 = readConverter.fromNative(o2, structField.context);
            if (o != null && o.equals(o2)) {
                o2 = o;
            }
        }
        if (clazz.equals(String.class) || clazz.equals(WString.class)) {
            this.nativeStrings.put(structField.name + ".ptr", this.memory.getPointer(offset));
            this.nativeStrings.put(structField.name + ".val", o2);
        }
        this.setFieldValue(structField.field, o2, true);
        return o2;
    }
    
    public void write() {
        if (this.memory == Structure.PLACEHOLDER_MEMORY) {
            return;
        }
        this.ensureAllocated();
        if (this instanceof ByValue) {
            this.getTypeInfo();
        }
        if (busy().contains(this)) {
            return;
        }
        busy().add(this);
        try {
            for (final StructField structField : this.fields().values()) {
                if (!structField.isVolatile) {
                    this.writeField(structField);
                }
            }
        }
        finally {
            busy().remove(this);
        }
    }
    
    public void writeField(final String s) {
        this.ensureAllocated();
        final StructField structField = this.fields().get(s);
        if (structField == null) {
            throw new IllegalArgumentException("No such field: " + s);
        }
        this.writeField(structField);
    }
    
    public void writeField(final String s, final Object o) {
        this.ensureAllocated();
        final StructField structField = this.fields().get(s);
        if (structField == null) {
            throw new IllegalArgumentException("No such field: " + s);
        }
        this.setFieldValue(structField.field, o);
        this.writeField(structField);
    }
    
    protected void writeField(final StructField structField) {
        if (structField.isReadOnly) {
            return;
        }
        final int offset = structField.offset;
        Object o = this.getFieldValue(structField.field);
        Class<?> clazz = structField.type;
        final ToNativeConverter writeConverter = structField.writeConverter;
        if (writeConverter != null) {
            o = writeConverter.toNative(o, new StructureWriteContext(this, structField.field));
            clazz = writeConverter.nativeType();
        }
        if (String.class == clazz || WString.class == clazz) {
            final boolean b = clazz == WString.class;
            if (o != null) {
                if (this.nativeStrings.containsKey(structField.name + ".ptr") && o.equals(this.nativeStrings.get(structField.name + ".val"))) {
                    return;
                }
                final NativeString nativeString = b ? new NativeString(o.toString(), true) : new NativeString(o.toString(), this.encoding);
                this.nativeStrings.put(structField.name, nativeString);
                o = nativeString.getPointer();
            }
            else {
                this.nativeStrings.remove(structField.name);
            }
            this.nativeStrings.remove(structField.name + ".ptr");
            this.nativeStrings.remove(structField.name + ".val");
        }
        try {
            this.memory.setValue(offset, o, clazz);
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Structure field \"" + structField.name + "\" was declared as " + structField.type + ((structField.type == clazz) ? "" : (" (native type " + clazz + ")")) + ", which is not supported within a Structure", ex);
        }
    }
    
    protected abstract List<String> getFieldOrder();
    
    @Deprecated
    protected final void setFieldOrder(final String[] array) {
        throw new Error("This method is obsolete, use getFieldOrder() instead");
    }
    
    protected void sortFields(final List<Field> list, final List<String> list2) {
        for (int i = 0; i < list2.size(); ++i) {
            final String s = list2.get(i);
            for (int j = 0; j < list.size(); ++j) {
                if (s.equals(list.get(j).getName())) {
                    Collections.swap(list, i, j);
                    break;
                }
            }
        }
    }
    
    protected List<Field> getFieldList() {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (Serializable s = this.getClass(); !s.equals(Structure.class); s = ((Class<? extends Structure>)s).getSuperclass()) {
            final ArrayList<Field> list2 = new ArrayList<Field>();
            final Field[] declaredFields = ((Class)s).getDeclaredFields();
            for (int i = 0; i < declaredFields.length; ++i) {
                final int modifiers = declaredFields[i].getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (Modifier.isPublic(modifiers)) {
                        list2.add(declaredFields[i]);
                    }
                }
            }
            list.addAll(0, list2);
        }
        return (List<Field>)list;
    }
    
    private List<String> fieldOrder() {
        final Class<? extends Structure> class1 = this.getClass();
        synchronized (Structure.fieldOrder) {
            List<String> fieldOrder = Structure.fieldOrder.get(class1);
            if (fieldOrder == null) {
                fieldOrder = this.getFieldOrder();
                Structure.fieldOrder.put(class1, fieldOrder);
            }
            return fieldOrder;
        }
    }
    
    public static List<String> createFieldsOrder(final List<String> list, final String... array) {
        return createFieldsOrder(list, Arrays.<String>asList(array));
    }
    
    public static List<String> createFieldsOrder(final List<String> list, final List<String> list2) {
        final ArrayList list3 = new ArrayList(list.size() + list2.size());
        list3.addAll(list);
        list3.addAll(list2);
        return Collections.<String>unmodifiableList((List<? extends String>)list3);
    }
    
    public static List<String> createFieldsOrder(final String s) {
        return Collections.<String>unmodifiableList((List<? extends String>)Collections.<? extends T>singletonList((T)s));
    }
    
    public static List<String> createFieldsOrder(final String... array) {
        return Collections.<String>unmodifiableList((List<? extends String>)Arrays.<? extends T>asList((T[])array));
    }
    
    private static <T extends Comparable<T>> List<T> sort(final Collection<? extends T> collection) {
        final ArrayList<Comparable> list = new ArrayList<Comparable>(collection);
        Collections.<Comparable>sort(list);
        return (List<T>)list;
    }
    
    protected List<Field> getFields(final boolean b) {
        final List<Field> fieldList = this.getFieldList();
        final HashSet<Comparable<T>> set = new HashSet<Comparable<T>>();
        final Iterator<Field> iterator = fieldList.iterator();
        while (iterator.hasNext()) {
            set.add((T)iterator.next().getName());
        }
        final List<String> fieldOrder = this.fieldOrder();
        if (fieldOrder.size() != fieldList.size() && fieldList.size() > 1) {
            if (b) {
                throw new Error("Structure.getFieldOrder() on " + this.getClass() + " does not provide enough names [" + fieldOrder.size() + "] (" + Structure.<Comparable>sort((Collection<? extends Comparable>)fieldOrder) + ") to match declared fields [" + fieldList.size() + "] (" + Structure.<Comparable>sort((Collection<? extends Comparable>)set) + ")");
            }
            return null;
        }
        else {
            if (!new HashSet(fieldOrder).equals(set)) {
                throw new Error("Structure.getFieldOrder() on " + this.getClass() + " returns names (" + Structure.<Comparable>sort((Collection<? extends Comparable>)fieldOrder) + ") which do not match declared field names (" + Structure.<Comparable>sort((Collection<? extends Comparable>)set) + ")");
            }
            this.sortFields(fieldList, fieldOrder);
            return fieldList;
        }
    }
    
    protected int calculateSize(final boolean b) {
        return this.calculateSize(b, false);
    }
    
    static int size(final Class<?> clazz) {
        return size(clazz, null);
    }
    
    static int size(final Class<?> clazz, Structure instance) {
        final LayoutInfo layoutInfo;
        synchronized (Structure.layoutInfo) {
            layoutInfo = Structure.layoutInfo.get(clazz);
        }
        int size = (layoutInfo != null && !layoutInfo.variable) ? layoutInfo.size : -1;
        if (size == -1) {
            if (instance == null) {
                instance = newInstance(clazz, Structure.PLACEHOLDER_MEMORY);
            }
            size = instance.size();
        }
        return size;
    }
    
    int calculateSize(final boolean b, final boolean b2) {
        int access$100 = -1;
        final Class<? extends Structure> class1 = this.getClass();
        LayoutInfo deriveLayout;
        synchronized (Structure.layoutInfo) {
            deriveLayout = Structure.layoutInfo.get(class1);
        }
        if (deriveLayout == null || this.alignType != deriveLayout.alignType || this.typeMapper != deriveLayout.typeMapper) {
            deriveLayout = this.deriveLayout(b, b2);
        }
        if (deriveLayout != null) {
            this.structAlignment = deriveLayout.alignment;
            this.structFields = deriveLayout.fields;
            if (!deriveLayout.variable) {
                synchronized (Structure.layoutInfo) {
                    if (!Structure.layoutInfo.containsKey(class1) || this.alignType != 0 || this.typeMapper != null) {
                        Structure.layoutInfo.put(class1, deriveLayout);
                    }
                }
            }
            access$100 = deriveLayout.size;
        }
        return access$100;
    }
    
    private void validateField(final String s, final Class<?> clazz) {
        if (this.typeMapper != null) {
            final ToNativeConverter toNativeConverter = this.typeMapper.getToNativeConverter(clazz);
            if (toNativeConverter != null) {
                this.validateField(s, toNativeConverter.nativeType());
                return;
            }
        }
        if (clazz.isArray()) {
            this.validateField(s, clazz.getComponentType());
        }
        else {
            try {
                this.getNativeSize(clazz);
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid Structure field in " + this.getClass() + ", field name '" + s + "' (" + clazz + "): " + ex.getMessage(), ex);
            }
        }
    }
    
    private void validateFields() {
        for (final Field field : this.getFieldList()) {
            this.validateField(field.getName(), field.getType());
        }
    }
    
    private LayoutInfo deriveLayout(final boolean b, final boolean b2) {
        final int n = 0;
        final List<Field> fields = this.getFields(b);
        if (fields == null) {
            return null;
        }
        final LayoutInfo layoutInfo = new LayoutInfo();
        layoutInfo.alignType = this.alignType;
        layoutInfo.typeMapper = this.typeMapper;
        boolean b3 = true;
        for (final Field field : fields) {
            final int modifiers = field.getModifiers();
            final Class<?> type = field.getType();
            if (type.isArray()) {
                layoutInfo.variable = true;
            }
            final StructField structField = new StructField();
            structField.isVolatile = Modifier.isVolatile(modifiers);
            structField.isReadOnly = Modifier.isFinal(modifiers);
            if (structField.isReadOnly) {
                if (!Platform.RO_FIELDS) {
                    throw new IllegalArgumentException("This VM does not support read-only fields (field '" + field.getName() + "' within " + this.getClass() + ")");
                }
                field.setAccessible(true);
            }
            structField.field = field;
            structField.name = field.getName();
            structField.type = type;
            if (Callback.class.isAssignableFrom(type) && !type.isInterface()) {
                throw new IllegalArgumentException("Structure Callback field '" + field.getName() + "' must be an interface");
            }
            if (type.isArray() && Structure.class.equals(type.getComponentType())) {
                throw new IllegalArgumentException("Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined");
            }
            if (!Modifier.isPublic(field.getModifiers())) {
                b3 = false;
            }
            else {
                Object o = this.getFieldValue(structField.field);
                if (o != null || !type.isArray()) {
                    Class<?> nativeType = type;
                    if (NativeMapped.class.isAssignableFrom(type)) {
                        final NativeMappedConverter instance = NativeMappedConverter.getInstance(type);
                        nativeType = instance.nativeType();
                        structField.writeConverter = instance;
                        structField.readConverter = instance;
                        structField.context = new StructureReadContext(this, field);
                    }
                    else if (this.typeMapper != null) {
                        final ToNativeConverter toNativeConverter = this.typeMapper.getToNativeConverter(type);
                        final FromNativeConverter fromNativeConverter = this.typeMapper.getFromNativeConverter(type);
                        if (toNativeConverter != null && fromNativeConverter != null) {
                            o = toNativeConverter.toNative(o, new StructureWriteContext(this, structField.field));
                            nativeType = ((o != null) ? o.getClass() : Pointer.class);
                            structField.writeConverter = toNativeConverter;
                            structField.readConverter = fromNativeConverter;
                            structField.context = new StructureReadContext(this, field);
                        }
                        else if (toNativeConverter != null || fromNativeConverter != null) {
                            throw new IllegalArgumentException("Structures require bidirectional type conversion for " + type);
                        }
                    }
                    if (o == null) {
                        o = this.initializeField(structField.field, type);
                    }
                    try {
                        structField.size = this.getNativeSize(nativeType, o);
                        this.getNativeAlignment(nativeType, o, b3);
                    }
                    catch (IllegalArgumentException ex) {
                        if (this.typeMapper == null) {
                            return null;
                        }
                        throw new IllegalArgumentException("Invalid Structure field in " + this.getClass() + ", field name '" + structField.name + "' (" + structField.type + "): " + ex.getMessage(), ex);
                    }
                    throw new Error("Field alignment is zero for field '" + structField.name + "' within " + this.getClass());
                }
                if (b) {
                    throw new IllegalStateException("Array fields must be initialized");
                }
                return null;
            }
        }
        if (n > 0) {
            final int addPadding = this.addPadding(n, layoutInfo.alignment);
            if (this instanceof ByValue) {
                this.getTypeInfo();
            }
            layoutInfo.size = addPadding;
            return layoutInfo;
        }
        throw new IllegalArgumentException("Structure " + this.getClass() + " has unknown or zero size (ensure all fields are public)");
    }
    
    private void initializeFields() {
        for (final Field field : this.getFieldList()) {
            try {
                if (field.get(this) != null) {
                    continue;
                }
                this.initializeField(field, field.getType());
            }
            catch (Exception ex) {
                throw new Error("Exception reading field '" + field.getName() + "' in " + this.getClass(), ex);
            }
        }
    }
    
    private Object initializeField(final Field field, final Class<?> clazz) {
        Object o = null;
        if (Structure.class.isAssignableFrom(clazz) && !ByReference.class.isAssignableFrom(clazz)) {
            try {
                o = newInstance(clazz, Structure.PLACEHOLDER_MEMORY);
                this.setFieldValue(field, o);
                return o;
            }
            catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Can't determine size of nested structure", ex);
            }
        }
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            o = NativeMappedConverter.getInstance(clazz).defaultValue();
            this.setFieldValue(field, o);
        }
        return o;
    }
    
    private int addPadding(final int n) {
        return this.addPadding(n, this.structAlignment);
    }
    
    private int addPadding(int n, final int n2) {
        if (this.actualAlignType != 1 && n % n2 != 0) {
            n += n2 - n % n2;
        }
        return n;
    }
    
    protected int getStructAlignment() {
        if (this.size == -1) {
            this.calculateSize(true);
        }
        return this.structAlignment;
    }
    
    protected int getNativeAlignment(Class<?> nativeType, Object o, final boolean b) {
        int n = 1;
        if (NativeMapped.class.isAssignableFrom(nativeType)) {
            final NativeMappedConverter instance = NativeMappedConverter.getInstance(nativeType);
            nativeType = instance.nativeType();
            o = instance.toNative(o, new ToNativeContext());
        }
        final int nativeSize = Native.getNativeSize(nativeType, o);
        if (nativeType.isPrimitive() || Long.class == nativeType || Integer.class == nativeType || Short.class == nativeType || Character.class == nativeType || Byte.class == nativeType || Boolean.class == nativeType || Float.class == nativeType || Double.class == nativeType) {
            n = nativeSize;
        }
        else if ((Pointer.class.isAssignableFrom(nativeType) || Function.class.isAssignableFrom(nativeType)) && (!Platform.HAS_BUFFERS || !Buffer.class.isAssignableFrom(nativeType)) && !Callback.class.isAssignableFrom(nativeType) && WString.class != nativeType && String.class != nativeType) {
            if (Structure.class.isAssignableFrom(nativeType)) {
                if (!ByReference.class.isAssignableFrom(nativeType)) {
                    if (o == null) {
                        o = newInstance(nativeType, Structure.PLACEHOLDER_MEMORY);
                    }
                    n = ((Structure)o).getStructAlignment();
                }
            }
            else {
                if (!nativeType.isArray()) {
                    throw new IllegalArgumentException("Type " + nativeType + " has unknown native alignment");
                }
                n = this.getNativeAlignment(nativeType.getComponentType(), null, b);
            }
        }
        if (this.actualAlignType == 1) {
            n = 1;
        }
        else if (this.actualAlignType == 3) {
            n = Math.min(8, n);
        }
        else if (this.actualAlignType == 2) {
            if (!b || !Platform.isMac() || !Platform.isPPC()) {
                n = Math.min(Native.MAX_ALIGNMENT, n);
            }
            if (Platform.isAIX() && (nativeType == Double.TYPE || nativeType == Double.class)) {
                n = 4;
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        return this.toString(Boolean.getBoolean("jna.dump_memory"));
    }
    
    public String toString(final boolean b) {
        return this.toString(0, true, b);
    }
    
    private String format(final Class<?> clazz) {
        final String name = clazz.getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }
    
    private String toString(final int n, final boolean b, final boolean b2) {
        this.ensureAllocated();
        final String property = System.getProperty("line.separator");
        String s = this.format(this.getClass()) + "(" + this.getPointer() + ")";
        if (!(this.getPointer() instanceof Memory)) {
            s = s + " (" + this.size() + " bytes)";
        }
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += "  ";
        }
        String string2 = "...}";
        if (b2) {
            String s2 = string2 + property + "memory dump" + property;
            final byte[] byteArray = this.getPointer().getByteArray(0L, this.size());
            for (int j = 0; j < byteArray.length; ++j) {
                if (j % 4 == 0) {
                    s2 += "[";
                }
                if (byteArray[j] >= 0 && byteArray[j] < 16) {
                    s2 += "0";
                }
                s2 += Integer.toHexString(byteArray[j] & 0xFF);
                if (j % 4 == 3 && j < byteArray.length - 1) {
                    s2 = s2 + "]" + property;
                }
            }
            string2 = s2 + "]";
        }
        return s + " {" + string2;
    }
    
    public Structure[] toArray(final Structure[] array) {
        this.ensureAllocated();
        if (this.memory instanceof AutoAllocated) {
            final Memory memory = (Memory)this.memory;
            final int n = array.length * this.size();
            if (memory.size() < n) {
                this.useMemory(this.autoAllocate(n));
            }
        }
        array[0] = this;
        final int size = this.size();
        for (int i = 1; i < array.length; ++i) {
            (array[i] = newInstance(this.getClass(), this.memory.share(i * size, size))).conditionalAutoRead();
        }
        if (!(this instanceof ByValue)) {
            this.array = array;
        }
        return array;
    }
    
    public Structure[] toArray(final int n) {
        return this.toArray((Structure[])Array.newInstance(this.getClass(), n));
    }
    
    private Class<?> baseClass() {
        if ((this instanceof ByReference || this instanceof ByValue) && Structure.class.isAssignableFrom(this.getClass().getSuperclass())) {
            return this.getClass().getSuperclass();
        }
        return this.getClass();
    }
    
    public boolean dataEquals(final Structure structure) {
        return this.dataEquals(structure, false);
    }
    
    public boolean dataEquals(final Structure structure, final boolean b) {
        if (b) {
            structure.getPointer().clear(structure.size());
            structure.write();
            this.getPointer().clear(this.size());
            this.write();
        }
        final byte[] byteArray = structure.getPointer().getByteArray(0L, structure.size());
        final byte[] byteArray2 = this.getPointer().getByteArray(0L, this.size());
        if (byteArray.length == byteArray2.length) {
            for (int i = 0; i < byteArray.length; ++i) {
                if (byteArray[i] != byteArray2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Structure && o.getClass() == this.getClass() && ((Structure)o).getPointer().equals(this.getPointer());
    }
    
    @Override
    public int hashCode() {
        if (this.getPointer() != null) {
            return this.getPointer().hashCode();
        }
        return this.getClass().hashCode();
    }
    
    protected void cacheTypeInfo(final Pointer pointer) {
        this.typeInfo = pointer.peer;
    }
    
    Pointer getFieldTypeInfo(final StructField structField) {
        Class<?> clazz = structField.type;
        Object o = this.getFieldValue(structField.field);
        if (this.typeMapper != null) {
            final ToNativeConverter toNativeConverter = this.typeMapper.getToNativeConverter(clazz);
            if (toNativeConverter != null) {
                clazz = toNativeConverter.nativeType();
                o = toNativeConverter.toNative(o, new ToNativeContext());
            }
        }
        return get(o, clazz);
    }
    
    Pointer getTypeInfo() {
        final Pointer typeInfo = getTypeInfo(this);
        this.cacheTypeInfo(typeInfo);
        return typeInfo;
    }
    
    public void setAutoSynch(final boolean b) {
        this.setAutoRead(b);
        this.setAutoWrite(b);
    }
    
    public void setAutoRead(final boolean autoRead) {
        this.autoRead = autoRead;
    }
    
    public boolean getAutoRead() {
        return this.autoRead;
    }
    
    public void setAutoWrite(final boolean autoWrite) {
        this.autoWrite = autoWrite;
    }
    
    public boolean getAutoWrite() {
        return this.autoWrite;
    }
    
    static Pointer getTypeInfo(final Object o) {
        return FFIType.get(o);
    }
    
    private static Structure newInstance(final Class<?> clazz, final long n) {
        try {
            final Structure instance = newInstance(clazz, (n == 0L) ? Structure.PLACEHOLDER_MEMORY : new Pointer(n));
            if (n != 0L) {
                instance.conditionalAutoRead();
            }
            return instance;
        }
        catch (Throwable t) {
            System.err.println("JNA: Error creating structure: " + t);
            return null;
        }
    }
    
    public static Structure newInstance(final Class<?> clazz, final Pointer pointer) throws IllegalArgumentException {
        try {
            return (Structure)clazz.getConstructor(Pointer.class).newInstance(pointer);
        }
        catch (NoSuchMethodException ex4) {}
        catch (SecurityException ex5) {}
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Can't instantiate " + clazz, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Instantiation of " + clazz + " (Pointer) not allowed, is it public?", ex2);
        }
        catch (InvocationTargetException ex3) {
            final String string = "Exception thrown while instantiating an instance of " + clazz;
            ex3.printStackTrace();
            throw new IllegalArgumentException(string, ex3);
        }
        final Structure instance = newInstance(clazz);
        if (pointer != Structure.PLACEHOLDER_MEMORY) {
            instance.useMemory(pointer);
        }
        return instance;
    }
    
    public static Structure newInstance(final Class<?> clazz) throws IllegalArgumentException {
        try {
            final Structure structure = (Structure)clazz.newInstance();
            if (structure instanceof ByValue) {
                structure.allocateMemory();
            }
            return structure;
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Can't instantiate " + clazz, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Instantiation of " + clazz + " not allowed, is it public?", ex2);
        }
    }
    
    StructField typeInfoField() {
        final LayoutInfo layoutInfo;
        synchronized (Structure.layoutInfo) {
            layoutInfo = Structure.layoutInfo.get(this.getClass());
        }
        if (layoutInfo != null) {
            return layoutInfo.typeInfoField;
        }
        return null;
    }
    
    private static void structureArrayCheck(final Structure[] array) {
        if (ByReference[].class.isAssignableFrom(array.getClass())) {
            return;
        }
        final Pointer pointer = array[0].getPointer();
        final int size = array[0].size();
        for (int i = 1; i < array.length; ++i) {
            if (array[i].getPointer().peer != pointer.peer + size * i) {
                throw new IllegalArgumentException("Structure array elements must use contiguous memory (bad backing address at Structure array index " + i + ")");
            }
        }
    }
    
    public static void autoRead(final Structure[] array) {
        structureArrayCheck(array);
        if (array[0].array == array) {
            array[0].autoRead();
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != null) {
                    array[i].autoRead();
                }
            }
        }
    }
    
    public void autoRead() {
        if (this.getAutoRead()) {
            this.read();
            if (this.array != null) {
                for (int i = 1; i < this.array.length; ++i) {
                    this.array[i].autoRead();
                }
            }
        }
    }
    
    public static void autoWrite(final Structure[] array) {
        structureArrayCheck(array);
        if (array[0].array == array) {
            array[0].autoWrite();
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != null) {
                    array[i].autoWrite();
                }
            }
        }
    }
    
    public void autoWrite() {
        if (this.getAutoWrite()) {
            this.write();
            if (this.array != null) {
                for (int i = 1; i < this.array.length; ++i) {
                    this.array[i].autoWrite();
                }
            }
        }
    }
    
    protected int getNativeSize(final Class<?> clazz) {
        return this.getNativeSize(clazz, null);
    }
    
    protected int getNativeSize(final Class<?> clazz, final Object o) {
        return Native.getNativeSize(clazz, o);
    }
    
    static void validate(final Class<?> clazz) {
        newInstance(clazz, Structure.PLACEHOLDER_MEMORY);
    }
    
    static /* synthetic */ void access$1900(final Structure structure, final boolean b) {
        structure.ensureAllocated(b);
    }
    
    static /* synthetic */ Pointer access$2000() {
        return Structure.PLACEHOLDER_MEMORY;
    }
    
    static {
        layoutInfo = new WeakHashMap<Class<?>, LayoutInfo>();
        fieldOrder = new WeakHashMap<Class<?>, List<String>>();
        reads = new ThreadLocal<Map<Pointer, Structure>>() {
            Structure$1() {
                super();
            }
            
            @Override
            protected synchronized Map<Pointer, Structure> initialValue() {
                return new HashMap<Pointer, Structure>();
            }
            
            @Override
            protected /* bridge */ Object initialValue() {
                return this.initialValue();
            }
        };
        busy = new ThreadLocal<Set<Structure>>() {
            Structure$2() {
                super();
            }
            
            @Override
            protected synchronized Set<Structure> initialValue() {
                return new StructureSet();
            }
            
            @Override
            protected /* bridge */ Object initialValue() {
                return this.initialValue();
            }
        };
        PLACEHOLDER_MEMORY = new Pointer(0L) {
            Structure$3(final long n) {
                super(n);
            }
            
            @Override
            public Pointer share(final long n, final long n2) {
                return this;
            }
        };
    }
}
