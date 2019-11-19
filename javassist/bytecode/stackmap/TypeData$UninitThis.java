package javassist.bytecode.stackmap;

import java.util.HashSet;
import javassist.bytecode.ConstPool;

public static class UninitThis extends UninitData
{
    UninitThis(final String s) {
        super(-1, s);
    }
    
    @Override
    public UninitData copy() {
        return new UninitThis(this.getName());
    }
    
    @Override
    public int getTypeTag() {
        return 6;
    }
    
    @Override
    public int getTypeData(final ConstPool constPool) {
        return 0;
    }
    
    @Override
    String toString2(final HashSet set) {
        return "uninit:this";
    }
}
