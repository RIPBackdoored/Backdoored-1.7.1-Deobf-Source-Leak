package javassist.bytecode.stackmap;

import java.util.Iterator;
import javassist.bytecode.Descriptor;
import javassist.CtClass;
import java.util.HashSet;
import java.util.ArrayList;
import javassist.NotFoundException;
import javassist.bytecode.ConstPool;
import javassist.bytecode.BadBytecode;
import javassist.ClassPool;

public abstract class TypeData
{
    public static TypeData[] make(final int n) {
        final TypeData[] array = new TypeData[n];
        for (int i = 0; i < n; ++i) {
            array[i] = TypeTag.TOP;
        }
        return array;
    }
    
    protected TypeData() {
        super();
    }
    
    private static void setType(final TypeData typeData, final String s, final ClassPool classPool) throws BadBytecode {
        typeData.setType(s, classPool);
    }
    
    public abstract int getTypeTag();
    
    public abstract int getTypeData(final ConstPool p0);
    
    public TypeData join() {
        return new TypeVar(this);
    }
    
    public abstract BasicType isBasicType();
    
    public abstract boolean is2WordType();
    
    public boolean isNullType() {
        return false;
    }
    
    public boolean isUninit() {
        return false;
    }
    
    public abstract boolean eq(final TypeData p0);
    
    public abstract String getName();
    
    public abstract void setType(final String p0, final ClassPool p1) throws BadBytecode;
    
    public abstract TypeData getArrayType(final int p0) throws NotFoundException;
    
    public int dfs(final ArrayList list, final int n, final ClassPool classPool) throws NotFoundException {
        return n;
    }
    
    protected TypeVar toTypeVar(final int n) {
        return null;
    }
    
    public void constructorCalled(final int n) {
    }
    
    @Override
    public String toString() {
        return super.toString() + "(" + this.toString2(new HashSet()) + ")";
    }
    
    abstract String toString2(final HashSet p0);
    
    public static CtClass commonSuperClassEx(final CtClass ctClass, final CtClass ctClass2) throws NotFoundException {
        if (ctClass == ctClass2) {
            return ctClass;
        }
        if (ctClass.isArray() && ctClass2.isArray()) {
            final CtClass componentType = ctClass.getComponentType();
            final CtClass componentType2 = ctClass2.getComponentType();
            final CtClass commonSuperClassEx = commonSuperClassEx(componentType, componentType2);
            if (commonSuperClassEx == componentType) {
                return ctClass;
            }
            if (commonSuperClassEx == componentType2) {
                return ctClass2;
            }
            return ctClass.getClassPool().get((commonSuperClassEx == null) ? "java.lang.Object" : (commonSuperClassEx.getName() + "[]"));
        }
        else {
            if (ctClass.isPrimitive() || ctClass2.isPrimitive()) {
                return null;
            }
            if (ctClass.isArray() || ctClass2.isArray()) {
                return ctClass.getClassPool().get("java.lang.Object");
            }
            return commonSuperClass(ctClass, ctClass2);
        }
    }
    
    public static CtClass commonSuperClass(final CtClass ctClass, final CtClass ctClass2) throws NotFoundException {
        CtClass superclass = ctClass;
        final CtClass ctClass4;
        CtClass ctClass3 = ctClass4 = ctClass2;
        CtClass superclass2 = superclass;
        while (!eq(superclass, ctClass3) || superclass.getSuperclass() == null) {
            final CtClass superclass3 = superclass.getSuperclass();
            final CtClass superclass4 = ctClass3.getSuperclass();
            CtClass superclass5;
            if (superclass4 == null) {
                superclass5 = ctClass4;
            }
            else {
                if (superclass3 != null) {
                    superclass = superclass3;
                    ctClass3 = superclass4;
                    continue;
                }
                final CtClass ctClass5 = superclass2;
                superclass2 = ctClass4;
                final CtClass ctClass6 = ctClass5;
                superclass = ctClass3;
                superclass5 = ctClass6;
            }
            while (true) {
                superclass = superclass.getSuperclass();
                if (superclass == null) {
                    break;
                }
                superclass2 = superclass2.getSuperclass();
            }
            CtClass superclass6;
            for (superclass6 = superclass2; !eq(superclass6, superclass5); superclass6 = superclass6.getSuperclass(), superclass5 = superclass5.getSuperclass()) {}
            return superclass6;
        }
        return superclass;
    }
    
    static boolean eq(final CtClass ctClass, final CtClass ctClass2) {
        return ctClass == ctClass2 || (ctClass != null && ctClass2 != null && ctClass.getName().equals(ctClass2.getName()));
    }
    
    public static void aastore(final TypeData typeData, final TypeData typeData2, final ClassPool classPool) throws BadBytecode {
        if (typeData instanceof AbsTypeVar && !typeData2.isNullType()) {
            ((AbsTypeVar)typeData).merge(ArrayType.make(typeData2));
        }
        if (typeData2 instanceof AbsTypeVar) {
            if (typeData instanceof AbsTypeVar) {
                ArrayElement.make(typeData);
            }
            else {
                if (!(typeData instanceof ClassName)) {
                    throw new BadBytecode("bad AASTORE: " + typeData);
                }
                if (!typeData.isNullType()) {
                    typeData2.setType(typeName(typeData.getName()), classPool);
                }
            }
        }
    }
}
