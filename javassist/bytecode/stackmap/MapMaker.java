package javassist.bytecode.stackmap;

import javassist.bytecode.ConstPool;
import java.util.ArrayList;
import javassist.bytecode.ByteArray;
import javassist.NotFoundException;
import javassist.bytecode.StackMap;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.StackMapTable;
import javassist.bytecode.MethodInfo;
import javassist.ClassPool;

public class MapMaker extends Tracer
{
    public static StackMapTable make(final ClassPool classPool, final MethodInfo methodInfo) throws BadBytecode {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return null;
        }
        TypedBlock[] blocks;
        try {
            blocks = TypedBlock.makeBlocks(methodInfo, codeAttribute, true);
        }
        catch (BasicBlock.JsrBytecode jsrBytecode) {
            return null;
        }
        if (blocks == null) {
            return null;
        }
        final MapMaker mapMaker = new MapMaker(classPool, methodInfo, codeAttribute);
        try {
            mapMaker.make(blocks, codeAttribute.getCode());
        }
        catch (BadBytecode badBytecode) {
            throw new BadBytecode(methodInfo, badBytecode);
        }
        return mapMaker.toStackMap(blocks);
    }
    
    public static StackMap make2(final ClassPool classPool, final MethodInfo methodInfo) throws BadBytecode {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return null;
        }
        TypedBlock[] blocks;
        try {
            blocks = TypedBlock.makeBlocks(methodInfo, codeAttribute, true);
        }
        catch (BasicBlock.JsrBytecode jsrBytecode) {
            return null;
        }
        if (blocks == null) {
            return null;
        }
        final MapMaker mapMaker = new MapMaker(classPool, methodInfo, codeAttribute);
        try {
            mapMaker.make(blocks, codeAttribute.getCode());
        }
        catch (BadBytecode badBytecode) {
            throw new BadBytecode(methodInfo, badBytecode);
        }
        return mapMaker.toStackMap2(methodInfo.getConstPool(), blocks);
    }
    
    public MapMaker(final ClassPool classPool, final MethodInfo methodInfo, final CodeAttribute codeAttribute) {
        super(classPool, methodInfo.getConstPool(), codeAttribute.getMaxStack(), codeAttribute.getMaxLocals(), TypedBlock.getRetType(methodInfo.getDescriptor()));
    }
    
    protected MapMaker(final MapMaker mapMaker) {
        super(mapMaker);
    }
    
    void make(final TypedBlock[] array, final byte[] array2) throws BadBytecode {
        this.make(array2, array[0]);
        this.findDeadCatchers(array2, array);
        try {
            this.fixTypes(array2, array);
        }
        catch (NotFoundException ex) {
            throw new BadBytecode("failed to resolve types", ex);
        }
    }
    
    private void make(final byte[] array, final TypedBlock typedBlock) throws BadBytecode {
        copyTypeData(typedBlock.stackTop, typedBlock.stackTypes, this.stackTypes);
        this.stackTop = typedBlock.stackTop;
        copyTypeData(typedBlock.localsTypes.length, typedBlock.localsTypes, this.localsTypes);
        this.traceException(array, typedBlock.toCatch);
        int i = typedBlock.position;
        while (i < i + typedBlock.length) {
            i += this.doOpcode(i, array);
            this.traceException(array, typedBlock.toCatch);
        }
        if (typedBlock.exit != null) {
            for (int j = 0; j < typedBlock.exit.length; ++j) {
                final TypedBlock typedBlock2 = (TypedBlock)typedBlock.exit[j];
                if (typedBlock2.alreadySet()) {
                    this.mergeMap(typedBlock2, true);
                }
                else {
                    this.recordStackMap(typedBlock2);
                    new MapMaker(this).make(array, typedBlock2);
                }
            }
        }
    }
    
    private void traceException(final byte[] array, BasicBlock.Catch next) throws BadBytecode {
        while (next != null) {
            final TypedBlock typedBlock = (TypedBlock)next.body;
            if (typedBlock.alreadySet()) {
                this.mergeMap(typedBlock, false);
                if (typedBlock.stackTop < 1) {
                    throw new BadBytecode("bad catch clause: " + next.typeIndex);
                }
                typedBlock.stackTypes[0] = this.merge(this.toExceptionType(next.typeIndex), typedBlock.stackTypes[0]);
            }
            else {
                this.recordStackMap(typedBlock, next.typeIndex);
                new MapMaker(this).make(array, typedBlock);
            }
            next = next.next;
        }
    }
    
    private void mergeMap(final TypedBlock typedBlock, final boolean b) throws BadBytecode {
        for (int length = this.localsTypes.length, i = 0; i < length; ++i) {
            typedBlock.localsTypes[i] = this.merge(validateTypeData(this.localsTypes, length, i), typedBlock.localsTypes[i]);
        }
        if (b) {
            for (int stackTop = this.stackTop, j = 0; j < stackTop; ++j) {
                typedBlock.stackTypes[j] = this.merge(this.stackTypes[j], typedBlock.stackTypes[j]);
            }
        }
    }
    
    private TypeData merge(final TypeData typeData, final TypeData typeData2) throws BadBytecode {
        if (typeData == typeData2) {
            return typeData2;
        }
        if (typeData2 instanceof TypeData.ClassName || typeData2 instanceof TypeData.BasicType) {
            return typeData2;
        }
        if (typeData2 instanceof TypeData.AbsTypeVar) {
            ((TypeData.AbsTypeVar)typeData2).merge(typeData);
            return typeData2;
        }
        throw new RuntimeException("fatal: this should never happen");
    }
    
    private void recordStackMap(final TypedBlock typedBlock) throws BadBytecode {
        final TypeData[] make = TypeData.make(this.stackTypes.length);
        final int stackTop = this.stackTop;
        recordTypeData(stackTop, this.stackTypes, make);
        this.recordStackMap0(typedBlock, stackTop, make);
    }
    
    private void recordStackMap(final TypedBlock typedBlock, final int n) throws BadBytecode {
        final TypeData[] make = TypeData.make(this.stackTypes.length);
        make[0] = this.toExceptionType(n).join();
        this.recordStackMap0(typedBlock, 1, make);
    }
    
    private TypeData.ClassName toExceptionType(final int n) {
        return new TypeData.ClassName("java.lang.Throwable");
    }
    
    private void recordStackMap0(final TypedBlock typedBlock, final int n, final TypeData[] array) throws BadBytecode {
        final int length = this.localsTypes.length;
        final TypeData[] make = TypeData.make(length);
        typedBlock.setStackMap(n, array, recordTypeData(length, this.localsTypes, make), make);
    }
    
    protected static int recordTypeData(final int n, final TypeData[] array, final TypeData[] array2) {
        int n2 = -1;
        for (int i = 0; i < n; ++i) {
            final TypeData validateTypeData = validateTypeData(array, n, i);
            array2[i] = validateTypeData.join();
            if (validateTypeData != MapMaker.TOP) {
                n2 = i + 1;
            }
        }
        return n2 + 1;
    }
    
    protected static void copyTypeData(final int n, final TypeData[] array, final TypeData[] array2) {
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
    }
    
    private static TypeData validateTypeData(final TypeData[] array, final int n, final int n2) {
        final TypeData typeData = array[n2];
        if (typeData.is2WordType() && n2 + 1 < n && array[n2 + 1] != MapMaker.TOP) {
            return MapMaker.TOP;
        }
        return typeData;
    }
    
    private void findDeadCatchers(final byte[] array, final TypedBlock[] array2) throws BadBytecode {
        for (final TypedBlock typedBlock : array2) {
            if (!typedBlock.alreadySet()) {
                this.fixDeadcode(array, typedBlock);
                final BasicBlock.Catch toCatch = typedBlock.toCatch;
                if (toCatch != null) {
                    final TypedBlock typedBlock2 = (TypedBlock)toCatch.body;
                    if (!typedBlock2.alreadySet()) {
                        this.recordStackMap(typedBlock2, toCatch.typeIndex);
                        this.fixDeadcode(array, typedBlock2);
                        typedBlock2.incoming = 1;
                    }
                }
            }
        }
    }
    
    private void fixDeadcode(final byte[] array, final TypedBlock typedBlock) throws BadBytecode {
        final int position = typedBlock.position;
        final int n = typedBlock.length - 3;
        if (n < 0) {
            if (n == -1) {
                array[position] = 0;
            }
            array[position + typedBlock.length - 1] = -65;
            typedBlock.incoming = 1;
            this.recordStackMap(typedBlock, 0);
            return;
        }
        typedBlock.incoming = 0;
        for (int i = 0; i < n; ++i) {
            array[position + i] = 0;
        }
        array[position + n] = -89;
        ByteArray.write16bit(-n, array, position + n + 1);
    }
    
    private void fixTypes(final byte[] array, final TypedBlock[] array2) throws NotFoundException, BadBytecode {
        final ArrayList list = new ArrayList();
        final int length = array2.length;
        int n = 0;
        for (final TypedBlock typedBlock : array2) {
            if (typedBlock.alreadySet()) {
                for (int length2 = typedBlock.localsTypes.length, j = 0; j < length2; ++j) {
                    n = typedBlock.localsTypes[j].dfs(list, n, this.classPool);
                }
                for (int stackTop = typedBlock.stackTop, k = 0; k < stackTop; ++k) {
                    n = typedBlock.stackTypes[k].dfs(list, n, this.classPool);
                }
            }
        }
    }
    
    public StackMapTable toStackMap(final TypedBlock[] array) {
        final StackMapTable.Writer writer = new StackMapTable.Writer(32);
        final int length = array.length;
        TypedBlock typedBlock = array[0];
        int length2 = typedBlock.length;
        if (typedBlock.incoming > 0) {
            writer.sameFrame(0);
            --length2;
        }
        for (int i = 1; i < length; ++i) {
            final TypedBlock typedBlock2 = array[i];
            if (this.isTarget(typedBlock2, array[i - 1])) {
                typedBlock2.resetNumLocals();
                this.toStackMapBody(writer, typedBlock2, stackMapDiff(typedBlock.numLocals, typedBlock.localsTypes, typedBlock2.numLocals, typedBlock2.localsTypes), length2, typedBlock);
                length2 = typedBlock2.length - 1;
                typedBlock = typedBlock2;
            }
            else if (typedBlock2.incoming == 0) {
                writer.sameFrame(length2);
                length2 = typedBlock2.length - 1;
                typedBlock = typedBlock2;
            }
            else {
                length2 += typedBlock2.length;
            }
        }
        return writer.toStackMapTable(this.cpool);
    }
    
    private boolean isTarget(final TypedBlock typedBlock, final TypedBlock typedBlock2) {
        final int incoming = typedBlock.incoming;
        return incoming > 1 || (incoming >= 1 && typedBlock2.stop);
    }
    
    private void toStackMapBody(final StackMapTable.Writer writer, final TypedBlock typedBlock, final int n, final int n2, final TypedBlock typedBlock2) {
        final int stackTop = typedBlock.stackTop;
        writer.sameFrame(n2);
    }
    
    private int[] fillStackMap(final int n, final int n2, final int[] array, final TypeData[] array2) {
        final int diffSize = diffSize(array2, n2, n2 + n);
        final ConstPool cpool = this.cpool;
        final int[] array3 = new int[diffSize];
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final TypeData typeData = array2[n2 + i];
            array3[n3] = typeData.getTypeTag();
            array[n3] = typeData.getTypeData(cpool);
            if (typeData.is2WordType()) {
                ++i;
            }
            ++n3;
        }
        return array3;
    }
    
    private static int stackMapDiff(final int n, final TypeData[] array, final int n2, final TypeData[] array2) {
        final int n3 = n2 - n;
        int n4;
        if (n3 > 0) {
            n4 = n;
        }
        else {
            n4 = n2;
        }
        if (!stackMapEq(array, array2, n4)) {
            return -100;
        }
        if (n3 > 0) {
            return diffSize(array2, n4, n2);
        }
        return -diffSize(array, n4, n);
    }
    
    private static boolean stackMapEq(final TypeData[] array, final TypeData[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (!array[i].eq(array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static int diffSize(final TypeData[] array, int i, final int n) {
        int n2 = 0;
        while (i < n) {
            final TypeData typeData = array[i++];
            ++n2;
            if (typeData.is2WordType()) {
                ++i;
            }
        }
        return n2;
    }
    
    public StackMap toStackMap2(final ConstPool constPool, final TypedBlock[] array) {
        final StackMap.Writer writer = new StackMap.Writer();
        final int length = array.length;
        final boolean[] array2 = new boolean[length];
        array2[0] = (array[0].incoming > 0);
        int n = array2[0] ? 1 : 0;
        for (int i = 1; i < length; ++i) {
            final TypedBlock typedBlock = array[i];
            final boolean[] array3 = array2;
            final int n2 = i;
            final boolean target = this.isTarget(typedBlock, array[i - 1]);
            array3[n2] = target;
            if (target) {
                typedBlock.resetNumLocals();
                ++n;
            }
        }
        return null;
    }
    
    private void writeStackFrame(final StackMap.Writer writer, final ConstPool constPool, final int n, final TypedBlock typedBlock) {
        writer.write16bit(n);
        this.writeVerifyTypeInfo(writer, constPool, typedBlock.localsTypes, typedBlock.numLocals);
        this.writeVerifyTypeInfo(writer, constPool, typedBlock.stackTypes, typedBlock.stackTop);
    }
    
    private void writeVerifyTypeInfo(final StackMap.Writer writer, final ConstPool constPool, final TypeData[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final TypeData typeData = array[i];
            if (typeData != null && typeData.is2WordType()) {
                ++n2;
                ++i;
            }
        }
        writer.write16bit(n - n2);
        for (int j = 0; j < n; ++j) {
            final TypeData typeData2 = array[j];
            writer.writeVerifyTypeInfo(typeData2.getTypeTag(), typeData2.getTypeData(constPool));
            if (typeData2.is2WordType()) {
                ++j;
            }
        }
    }
}
