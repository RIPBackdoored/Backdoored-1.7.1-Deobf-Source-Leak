package javassist.bytecode.stackmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;

public static class Maker
{
    public Maker() {
        super();
    }
    
    protected BasicBlock makeBlock(final int n) {
        return new BasicBlock(n);
    }
    
    protected BasicBlock[] makeArray(final int n) {
        return new BasicBlock[n];
    }
    
    private BasicBlock[] makeArray(final BasicBlock basicBlock) {
        final BasicBlock[] array = this.makeArray(1);
        array[0] = basicBlock;
        return array;
    }
    
    private BasicBlock[] makeArray(final BasicBlock basicBlock, final BasicBlock basicBlock2) {
        final BasicBlock[] array = this.makeArray(2);
        array[0] = basicBlock;
        array[1] = basicBlock2;
        return array;
    }
    
    public BasicBlock[] make(final MethodInfo methodInfo) throws BadBytecode {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if (codeAttribute == null) {
            return null;
        }
        final CodeIterator iterator = codeAttribute.iterator();
        return this.make(iterator, 0, iterator.getCodeLength(), codeAttribute.getExceptionTable());
    }
    
    public BasicBlock[] make(final CodeIterator codeIterator, final int n, final int n2, final ExceptionTable exceptionTable) throws BadBytecode {
        final BasicBlock[] blocks = this.makeBlocks(this.makeMarks(codeIterator, n, n2, exceptionTable));
        this.addCatchers(blocks, exceptionTable);
        return blocks;
    }
    
    private Mark makeMark(final HashMap hashMap, final int n) {
        return this.makeMark0(hashMap, n, true, true);
    }
    
    private Mark makeMark(final HashMap hashMap, final int n, final BasicBlock[] array, final int n2, final boolean b) {
        final Mark mark0 = this.makeMark0(hashMap, n, false, false);
        mark0.setJump(array, n2, b);
        return mark0;
    }
    
    private Mark makeMark0(final HashMap hashMap, final int n, final boolean b, final boolean b2) {
        final Integer n2 = new Integer(n);
        Mark mark = hashMap.get(n2);
        if (mark == null) {
            mark = new Mark(n);
            hashMap.put(n2, mark);
        }
        if (b) {
            if (mark.block == null) {
                mark.block = this.makeBlock(n);
            }
            if (b2) {
                final BasicBlock block = mark.block;
                ++block.incoming;
            }
        }
        return mark;
    }
    
    private HashMap makeMarks(final CodeIterator codeIterator, final int n, final int n2, final ExceptionTable exceptionTable) throws BadBytecode {
        codeIterator.begin();
        codeIterator.move(n);
        final HashMap hashMap = new HashMap();
        while (codeIterator.hasNext()) {
            final int next = codeIterator.next();
            if (next >= n2) {
                break;
            }
            final int byte1 = codeIterator.byteAt(next);
            if ((153 <= byte1 && byte1 <= 166) || byte1 == 198 || byte1 == 199) {
                this.makeMark(hashMap, next, this.makeArray(this.makeMark(hashMap, next + codeIterator.s16bitAt(next + 1)).block, this.makeMark(hashMap, next + 3).block), 3, false);
            }
            else if (167 <= byte1 && byte1 <= 171) {
                switch (byte1) {
                    case 167:
                        this.makeGoto(hashMap, next, next + codeIterator.s16bitAt(next + 1), 3);
                        continue;
                    case 168:
                        this.makeJsr(hashMap, next, next + codeIterator.s16bitAt(next + 1), 3);
                        continue;
                    case 169:
                        this.makeMark(hashMap, next, null, 2, true);
                        continue;
                    case 170: {
                        final int n3 = (next & 0xFFFFFFFC) + 4;
                        final int n4 = codeIterator.s32bitAt(n3 + 8) - codeIterator.s32bitAt(n3 + 4) + 1;
                        final BasicBlock[] array = this.makeArray(n4 + 1);
                        array[0] = this.makeMark(hashMap, next + codeIterator.s32bitAt(n3)).block;
                        int i = n3 + 12;
                        final int n5 = i + n4 * 4;
                        int n6 = 1;
                        while (i < n5) {
                            array[n6++] = this.makeMark(hashMap, next + codeIterator.s32bitAt(i)).block;
                            i += 4;
                        }
                        this.makeMark(hashMap, next, array, n5 - next, true);
                        continue;
                    }
                    case 171: {
                        final int n7 = (next & 0xFFFFFFFC) + 4;
                        final int s32bit = codeIterator.s32bitAt(n7 + 4);
                        final BasicBlock[] array2 = this.makeArray(s32bit + 1);
                        array2[0] = this.makeMark(hashMap, next + codeIterator.s32bitAt(n7)).block;
                        int j = n7 + 8 + 4;
                        final int n8 = j + s32bit * 8 - 4;
                        int n9 = 1;
                        while (j < n8) {
                            array2[n9++] = this.makeMark(hashMap, next + codeIterator.s32bitAt(j)).block;
                            j += 8;
                        }
                        this.makeMark(hashMap, next, array2, n8 - next, true);
                        continue;
                    }
                }
            }
            else if ((172 <= byte1 && byte1 <= 177) || byte1 == 191) {
                this.makeMark(hashMap, next, null, 1, true);
            }
            else if (byte1 == 200) {
                this.makeGoto(hashMap, next, next + codeIterator.s32bitAt(next + 1), 5);
            }
            else if (byte1 == 201) {
                this.makeJsr(hashMap, next, next + codeIterator.s32bitAt(next + 1), 5);
            }
            else {
                if (byte1 != 196 || codeIterator.byteAt(next + 1) != 169) {
                    continue;
                }
                this.makeMark(hashMap, next, null, 4, true);
            }
        }
        if (exceptionTable != null) {
            int size = exceptionTable.size();
            while (--size >= 0) {
                this.makeMark0(hashMap, exceptionTable.startPc(size), true, false);
                this.makeMark(hashMap, exceptionTable.handlerPc(size));
            }
        }
        return hashMap;
    }
    
    private void makeGoto(final HashMap hashMap, final int n, final int n2, final int n3) {
        this.makeMark(hashMap, n, this.makeArray(this.makeMark(hashMap, n2).block), n3, true);
    }
    
    protected void makeJsr(final HashMap hashMap, final int n, final int n2, final int n3) throws BadBytecode {
        throw new JsrBytecode();
    }
    
    private BasicBlock[] makeBlocks(final HashMap hashMap) {
        final Mark[] array = (Mark[])hashMap.values().toArray(new Mark[hashMap.size()]);
        Arrays.sort(array);
        final ArrayList<BasicBlock> list = new ArrayList<BasicBlock>();
        int i = 0;
        BasicBlock basicBlock;
        if (array.length > 0 && array[0].position == 0 && array[0].block != null) {
            basicBlock = getBBlock(array[i++]);
        }
        else {
            basicBlock = this.makeBlock(0);
        }
        list.add(basicBlock);
        while (i < array.length) {
            final Mark mark = array[i++];
            final BasicBlock bBlock = getBBlock(mark);
            if (bBlock == null) {
                if (basicBlock.length > 0) {
                    basicBlock = this.makeBlock(basicBlock.position + basicBlock.length);
                    list.add(basicBlock);
                }
                basicBlock.length = mark.position + mark.size - basicBlock.position;
                basicBlock.exit = mark.jump;
                basicBlock.stop = mark.alwaysJmp;
            }
            else {
                if (basicBlock.length == 0) {
                    basicBlock.length = mark.position - basicBlock.position;
                    final BasicBlock basicBlock2 = bBlock;
                    ++basicBlock2.incoming;
                    basicBlock.exit = this.makeArray(bBlock);
                }
                else if (basicBlock.position + basicBlock.length < mark.position) {
                    final BasicBlock block = this.makeBlock(basicBlock.position + basicBlock.length);
                    list.add(block);
                    block.length = mark.position - block.position;
                    block.stop = true;
                    block.exit = this.makeArray(bBlock);
                }
                list.add(bBlock);
                basicBlock = bBlock;
            }
        }
        return list.<BasicBlock>toArray(this.makeArray(list.size()));
    }
    
    private static BasicBlock getBBlock(final Mark mark) {
        final BasicBlock block = mark.block;
        if (block != null && mark.size > 0) {
            block.exit = mark.jump;
            block.length = mark.size;
            block.stop = mark.alwaysJmp;
        }
        return block;
    }
    
    private void addCatchers(final BasicBlock[] array, final ExceptionTable exceptionTable) throws BadBytecode {
        if (exceptionTable == null) {
            return;
        }
        int size = exceptionTable.size();
        while (--size >= 0) {
            final BasicBlock find = BasicBlock.find(array, exceptionTable.handlerPc(size));
            final int startPc = exceptionTable.startPc(size);
            final int endPc = exceptionTable.endPc(size);
            final int catchType = exceptionTable.catchType(size);
            final BasicBlock basicBlock = find;
            --basicBlock.incoming;
            for (int i = 0; i < array.length; ++i) {
                final BasicBlock basicBlock2 = array[i];
                final int position = basicBlock2.position;
                if (startPc <= position && position < endPc) {
                    basicBlock2.toCatch = new Catch(find, catchType, basicBlock2.toCatch);
                    final BasicBlock basicBlock3 = find;
                    ++basicBlock3.incoming;
                }
            }
        }
    }
}
