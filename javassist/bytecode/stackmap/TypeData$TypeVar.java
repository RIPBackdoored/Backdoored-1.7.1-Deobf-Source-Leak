package javassist.bytecode.stackmap;

import javassist.CtClass;
import java.util.Iterator;
import javassist.bytecode.Descriptor;
import java.util.HashSet;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.ClassPool;
import javassist.bytecode.ConstPool;
import java.util.ArrayList;

public static class TypeVar extends AbsTypeVar
{
    protected ArrayList lowers;
    protected ArrayList usedBy;
    protected ArrayList uppers;
    protected String fixedType;
    private boolean is2WordType;
    private int visited;
    private int smallest;
    private boolean inList;
    private int dimension;
    
    public TypeVar(final TypeData typeData) {
        super();
        this.visited = 0;
        this.smallest = 0;
        this.inList = false;
        this.dimension = 0;
        this.uppers = null;
        this.lowers = new ArrayList(2);
        this.usedBy = new ArrayList(2);
        this.merge(typeData);
        this.fixedType = null;
        this.is2WordType = typeData.is2WordType();
    }
    
    @Override
    public String getName() {
        if (this.fixedType == null) {
            return this.lowers.get(0).getName();
        }
        return this.fixedType;
    }
    
    @Override
    public BasicType isBasicType() {
        if (this.fixedType == null) {
            return this.lowers.get(0).isBasicType();
        }
        return null;
    }
    
    @Override
    public boolean is2WordType() {
        return this.fixedType == null && this.is2WordType;
    }
    
    @Override
    public boolean isNullType() {
        return this.fixedType == null && this.lowers.get(0).isNullType();
    }
    
    @Override
    public boolean isUninit() {
        return this.fixedType == null && this.lowers.get(0).isUninit();
    }
    
    @Override
    public void merge(final TypeData typeData) {
        this.lowers.add(typeData);
        if (typeData instanceof TypeVar) {
            ((TypeVar)typeData).usedBy.add(this);
        }
    }
    
    @Override
    public int getTypeTag() {
        if (this.fixedType == null) {
            return this.lowers.get(0).getTypeTag();
        }
        return super.getTypeTag();
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        if (this.fixedType == null) {
            return this.lowers.get(0).getTypeData(constPool);
        }
        return super.getTypeData(constPool);
    }
    
    @Override
    public void setType(final String s, final ClassPool classPool) throws BadBytecode {
        if (this.uppers == null) {
            this.uppers = new ArrayList();
        }
        this.uppers.add(s);
    }
    
    @Override
    protected TypeVar toTypeVar(final int dimension) {
        this.dimension = dimension;
        return this;
    }
    
    @Override
    public TypeData getArrayType(final int n) throws NotFoundException {
        return this;
    }
    
    @Override
    public int dfs(final ArrayList list, int dfs, final ClassPool classPool) throws NotFoundException {
        if (this.visited > 0) {
            return dfs;
        }
        final int n = ++dfs;
        this.smallest = n;
        this.visited = n;
        list.add(this);
        this.inList = true;
        for (int size = this.lowers.size(), i = 0; i < size; ++i) {
            final TypeVar typeVar = this.lowers.get(i).toTypeVar(this.dimension);
            if (typeVar != null) {
                if (typeVar.visited == 0) {
                    dfs = typeVar.dfs(list, dfs, classPool);
                    if (typeVar.smallest < this.smallest) {
                        this.smallest = typeVar.smallest;
                    }
                }
                else if (typeVar.inList && typeVar.visited < this.smallest) {
                    this.smallest = typeVar.visited;
                }
            }
        }
        if (this.visited == this.smallest) {
            final ArrayList<TypeVar> list2 = new ArrayList<TypeVar>();
            TypeVar typeVar2;
            do {
                typeVar2 = list.remove(list.size() - 1);
                typeVar2.inList = false;
                list2.add(typeVar2);
            } while (typeVar2 != this);
            this.fixTypes(list2, classPool);
        }
        return dfs;
    }
    
    private void fixTypes(final ArrayList list, final ClassPool classPool) throws NotFoundException {
        final HashSet<String> set = new HashSet<String>();
        boolean b = false;
        TypeData top = null;
        for (int size = list.size(), i = 0; i < size; ++i) {
            final TypeVar typeVar = list.get(i);
            final ArrayList lowers = typeVar.lowers;
            for (int size2 = lowers.size(), j = 0; j < size2; ++j) {
                final TypeData arrayType = lowers.get(j).getArrayType(typeVar.dimension);
                final BasicType basicType = arrayType.isBasicType();
                if (top == null) {
                    if (basicType == null) {
                        b = false;
                        top = arrayType;
                        if (arrayType.isUninit()) {
                            break;
                        }
                    }
                    else {
                        b = true;
                        top = basicType;
                    }
                }
                else {
                    if (basicType == null) {}
                    if (basicType != null && top != basicType) {
                        b = true;
                        top = TypeTag.TOP;
                        break;
                    }
                }
                if (basicType == null && !arrayType.isNullType()) {
                    set.add(arrayType.getName());
                }
            }
        }
        if (b) {
            this.is2WordType = top.is2WordType();
            this.fixTypes1(list, top);
        }
        else {
            this.fixTypes1(list, new ClassName(this.fixTypes2(list, set, classPool)));
        }
    }
    
    private void fixTypes1(final ArrayList list, final TypeData typeData) throws NotFoundException {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final TypeVar typeVar = list.get(i);
            final TypeData arrayType = typeData.getArrayType(-typeVar.dimension);
            if (arrayType.isBasicType() == null) {
                typeVar.fixedType = arrayType.getName();
            }
            else {
                typeVar.lowers.clear();
                typeVar.lowers.add(arrayType);
                typeVar.is2WordType = arrayType.is2WordType();
            }
        }
    }
    
    private String fixTypes2(final ArrayList list, final HashSet set, final ClassPool classPool) throws NotFoundException {
        final Iterator<String> iterator = set.iterator();
        if (set.size() == 0) {
            return null;
        }
        if (set.size() == 1) {
            return iterator.next();
        }
        CtClass ctClass = classPool.get(iterator.next());
        while (iterator.hasNext()) {
            ctClass = TypeData.commonSuperClassEx(ctClass, classPool.get(iterator.next()));
        }
        if (ctClass.getSuperclass() == null || isObjectArray(ctClass)) {
            ctClass = this.fixByUppers(list, classPool, new HashSet(), ctClass);
        }
        if (ctClass.isArray()) {
            return Descriptor.toJvmName(ctClass);
        }
        return ctClass.getName();
    }
    
    private static boolean isObjectArray(final CtClass ctClass) throws NotFoundException {
        return ctClass.isArray() && ctClass.getComponentType().getSuperclass() == null;
    }
    
    private CtClass fixByUppers(final ArrayList list, final ClassPool classPool, final HashSet set, CtClass fixByUppers) throws NotFoundException {
        if (list == null) {
            return fixByUppers;
        }
        for (int size = list.size(), i = 0; i < size; ++i) {
            final TypeVar typeVar = list.get(i);
            if (!set.add(typeVar)) {
                return fixByUppers;
            }
            if (typeVar.uppers != null) {
                for (int size2 = typeVar.uppers.size(), j = 0; j < size2; ++j) {
                    final CtClass value = classPool.get(typeVar.uppers.get(j));
                    if (value.subtypeOf(fixByUppers)) {
                        fixByUppers = value;
                    }
                }
            }
            fixByUppers = this.fixByUppers(typeVar.usedBy, classPool, set, fixByUppers);
        }
        return fixByUppers;
    }
    
    @Override
    String toString2(final HashSet set) {
        set.add(this);
        if (this.lowers.size() > 0) {
            final TypeData typeData = this.lowers.get(0);
            if (typeData != null && !set.contains(typeData)) {
                return typeData.toString2(set);
            }
        }
        return "?";
    }
}
