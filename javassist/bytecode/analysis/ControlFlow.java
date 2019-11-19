package javassist.bytecode.analysis;

import java.util.ArrayList;
import javassist.bytecode.stackmap.BasicBlock;
import javassist.bytecode.BadBytecode;
import javassist.CtMethod;
import javassist.bytecode.MethodInfo;
import javassist.CtClass;

public class ControlFlow
{
    private CtClass clazz;
    private MethodInfo methodInfo;
    private Block[] basicBlocks;
    private Frame[] frames;
    
    public ControlFlow(final CtMethod ctMethod) throws BadBytecode {
        this(ctMethod.getDeclaringClass(), ctMethod.getMethodInfo2());
    }
    
    public ControlFlow(final CtClass clazz, final MethodInfo methodInfo) throws BadBytecode {
        super();
        this.clazz = clazz;
        this.methodInfo = methodInfo;
        this.frames = null;
        this.basicBlocks = (Block[])new BasicBlock.Maker() {
            final /* synthetic */ ControlFlow this$0;
            
            ControlFlow$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            protected BasicBlock makeBlock(final int n) {
                return new Block(n, this.this$0.methodInfo);
            }
            
            @Override
            protected BasicBlock[] makeArray(final int n) {
                return new Block[n];
            }
        }.make(methodInfo);
        if (this.basicBlocks == null) {
            this.basicBlocks = new Block[0];
        }
        final int length = this.basicBlocks.length;
        final int[] array = new int[length];
        for (int i = 0; i < length; ++i) {
            final Block block = this.basicBlocks[i];
            block.index = i;
            block.entrances = new Block[block.incomings()];
            array[i] = 0;
        }
        for (int j = 0; j < length; ++j) {
            final Block block2 = this.basicBlocks[j];
            for (int k = 0; k < block2.exits(); ++k) {
                final Block exit = block2.exit(k);
                exit.entrances[array[exit.index]++] = block2;
            }
            final Catcher[] catchers = block2.catchers();
            for (int l = 0; l < catchers.length; ++l) {
                final Block access$100 = catchers[l].node;
                access$100.entrances[array[access$100.index]++] = block2;
            }
        }
    }
    
    public Block[] basicBlocks() {
        return this.basicBlocks;
    }
    
    public Frame frameAt(final int n) throws BadBytecode {
        if (this.frames == null) {
            this.frames = new Analyzer().analyze(this.clazz, this.methodInfo);
        }
        return this.frames[n];
    }
    
    public Node[] dominatorTree() {
        final int length = this.basicBlocks.length;
        return null;
    }
    
    public Node[] postDominatorTree() {
        final int length = this.basicBlocks.length;
        return null;
    }
    
    static /* synthetic */ MethodInfo access$000(final ControlFlow controlFlow) {
        return controlFlow.methodInfo;
    }
}
