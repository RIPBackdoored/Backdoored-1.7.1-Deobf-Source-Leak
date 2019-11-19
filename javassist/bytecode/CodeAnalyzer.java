package javassist.bytecode;

class CodeAnalyzer implements Opcode
{
    private ConstPool constPool;
    private CodeAttribute codeAttr;
    
    public CodeAnalyzer(final CodeAttribute codeAttr) {
        super();
        this.codeAttr = codeAttr;
        this.constPool = codeAttr.getConstPool();
    }
    
    public int computeMaxStack() throws BadBytecode {
        final CodeIterator iterator = this.codeAttr.iterator();
        final int codeLength = iterator.getCodeLength();
        final int[] array = new int[codeLength];
        this.constPool = this.codeAttr.getConstPool();
        this.initStack(array, this.codeAttr);
        for (int i = 0; i < codeLength; ++i) {
            if (array[i] < 0) {
                this.visitBytecode(iterator, array, i);
            }
        }
        int n = 1;
        for (int j = 0; j < codeLength; ++j) {
            if (array[j] > n) {
                n = array[j];
            }
        }
        return n - 1;
    }
    
    private void initStack(final int[] array, final CodeAttribute codeAttribute) {
        array[0] = -1;
        final ExceptionTable exceptionTable = codeAttribute.getExceptionTable();
        if (exceptionTable != null) {
            for (int size = exceptionTable.size(), i = 0; i < size; ++i) {
                array[exceptionTable.handlerPc(i)] = -2;
            }
        }
    }
    
    private void visitBytecode(final CodeIterator codeIterator, final int[] array, int next) throws BadBytecode {
        final int length = array.length;
        codeIterator.move(next);
        int visitInst = -array[next];
        final int[] array2 = { -1 };
        while (codeIterator.hasNext()) {
            next = codeIterator.next();
            array[next] = visitInst;
            final int byte1 = codeIterator.byteAt(next);
            visitInst = this.visitInst(byte1, codeIterator, next, visitInst);
            if (visitInst < 1) {
                throw new BadBytecode("stack underflow at " + next);
            }
            if (this.processBranch(byte1, codeIterator, next, length, array, visitInst, array2)) {
                break;
            }
            if (isEnd(byte1)) {
                break;
            }
            if (byte1 != 168 && byte1 != 201) {
                continue;
            }
            --visitInst;
        }
    }
    
    private boolean processBranch(final int n, final CodeIterator codeIterator, final int n2, final int n3, final int[] array, final int n4, final int[] array2) throws BadBytecode {
        if ((153 <= n && n <= 166) || n == 198 || n == 199) {
            this.checkTarget(n2, n2 + codeIterator.s16bitAt(n2 + 1), n3, array, n4);
        }
        else {
            switch (n) {
                case 167:
                    this.checkTarget(n2, n2 + codeIterator.s16bitAt(n2 + 1), n3, array, n4);
                    return true;
                case 200:
                    this.checkTarget(n2, n2 + codeIterator.s32bitAt(n2 + 1), n3, array, n4);
                    return true;
                case 168:
                case 201: {
                    int n5;
                    if (n == 168) {
                        n5 = n2 + codeIterator.s16bitAt(n2 + 1);
                    }
                    else {
                        n5 = n2 + codeIterator.s32bitAt(n2 + 1);
                    }
                    this.checkTarget(n2, n5, n3, array, n4);
                    if (array2[0] < 0) {
                        array2[0] = n4;
                        return false;
                    }
                    if (n4 == array2[0]) {
                        return false;
                    }
                    throw new BadBytecode("sorry, cannot compute this data flow due to JSR: " + n4 + "," + array2[0]);
                }
                case 169:
                    if (array2[0] < 0) {
                        array2[0] = n4 + 1;
                        return false;
                    }
                    if (n4 + 1 == array2[0]) {
                        return true;
                    }
                    throw new BadBytecode("sorry, cannot compute this data flow due to RET: " + n4 + "," + array2[0]);
                case 170:
                case 171: {
                    int n6 = (n2 & 0xFFFFFFFC) + 4;
                    this.checkTarget(n2, n2 + codeIterator.s32bitAt(n6), n3, array, n4);
                    if (n == 171) {
                        final int s32bit = codeIterator.s32bitAt(n6 + 4);
                        n6 += 12;
                        for (int i = 0; i < s32bit; ++i) {
                            this.checkTarget(n2, n2 + codeIterator.s32bitAt(n6), n3, array, n4);
                            n6 += 8;
                        }
                    }
                    else {
                        final int n7 = codeIterator.s32bitAt(n6 + 8) - codeIterator.s32bitAt(n6 + 4) + 1;
                        n6 += 12;
                        for (int j = 0; j < n7; ++j) {
                            this.checkTarget(n2, n2 + codeIterator.s32bitAt(n6), n3, array, n4);
                            n6 += 4;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private void checkTarget(final int n, final int n2, final int n3, final int[] array, final int n4) throws BadBytecode {
        if (n2 < 0 || n3 <= n2) {
            throw new BadBytecode("bad branch offset at " + n);
        }
        final int n5 = array[n2];
        array[n2] = -n4;
    }
    
    private static boolean isEnd(final int n) {
        return (172 <= n && n <= 177) || n == 191;
    }
    
    private int visitInst(int byte1, final CodeIterator codeIterator, final int n, int n2) throws BadBytecode {
        switch (byte1) {
            case 180:
                n2 += this.getFieldSize(codeIterator, n) - 1;
                return n2;
            case 181:
                n2 -= this.getFieldSize(codeIterator, n) + 1;
                return n2;
            case 178:
                n2 += this.getFieldSize(codeIterator, n);
                return n2;
            case 179:
                n2 -= this.getFieldSize(codeIterator, n);
                return n2;
            case 182:
            case 183:
                n2 += Descriptor.dataSize(this.constPool.getMethodrefType(codeIterator.u16bitAt(n + 1))) - 1;
                return n2;
            case 184:
                n2 += Descriptor.dataSize(this.constPool.getMethodrefType(codeIterator.u16bitAt(n + 1)));
                return n2;
            case 185:
                n2 += Descriptor.dataSize(this.constPool.getInterfaceMethodrefType(codeIterator.u16bitAt(n + 1))) - 1;
                return n2;
            case 186:
                n2 += Descriptor.dataSize(this.constPool.getInvokeDynamicType(codeIterator.u16bitAt(n + 1)));
                return n2;
            case 191:
                n2 = 1;
                return n2;
            case 197:
                n2 += 1 - codeIterator.byteAt(n + 3);
                return n2;
            case 196:
                byte1 = codeIterator.byteAt(n + 1);
                break;
        }
        n2 += CodeAnalyzer.STACK_GROW[byte1];
        return n2;
    }
    
    private int getFieldSize(final CodeIterator codeIterator, final int n) {
        return Descriptor.dataSize(this.constPool.getFieldrefType(codeIterator.u16bitAt(n + 1)));
    }
}
