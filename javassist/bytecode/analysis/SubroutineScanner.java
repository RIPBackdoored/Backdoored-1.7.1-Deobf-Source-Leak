package javassist.bytecode.analysis;

import javassist.bytecode.BadBytecode;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import javassist.bytecode.Opcode;

public class SubroutineScanner implements Opcode
{
    private Subroutine[] subroutines;
    Map subTable;
    Set done;
    
    public SubroutineScanner() {
        super();
        this.subTable = new HashMap();
        this.done = new HashSet();
    }
    
    public Subroutine[] scan(final MethodInfo methodInfo) throws BadBytecode {
        final CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        final CodeIterator iterator = codeAttribute.iterator();
        this.subroutines = new Subroutine[codeAttribute.getCodeLength()];
        this.subTable.clear();
        this.done.clear();
        this.scan(0, iterator, null);
        final ExceptionTable exceptionTable = codeAttribute.getExceptionTable();
        for (int i = 0; i < exceptionTable.size(); ++i) {
            this.scan(exceptionTable.handlerPc(i), iterator, this.subroutines[exceptionTable.startPc(i)]);
        }
        return this.subroutines;
    }
    
    private void scan(int next, final CodeIterator codeIterator, final Subroutine subroutine) throws BadBytecode {
        if (this.done.contains(new Integer(next))) {
            return;
        }
        this.done.add(new Integer(next));
        final int lookAhead = codeIterator.lookAhead();
        codeIterator.move(next);
        next = codeIterator.next();
        final boolean b = this.scanOp(next, codeIterator, subroutine) && codeIterator.hasNext();
        codeIterator.move(lookAhead);
    }
    
    private boolean scanOp(final int n, final CodeIterator codeIterator, final Subroutine subroutine) throws BadBytecode {
        this.subroutines[n] = subroutine;
        final int byte1 = codeIterator.byteAt(n);
        if (byte1 == 170) {
            this.scanTableSwitch(n, codeIterator, subroutine);
            return false;
        }
        if (byte1 == 171) {
            this.scanLookupSwitch(n, codeIterator, subroutine);
            return false;
        }
        if (Util.isReturn(byte1) || byte1 == 169 || byte1 == 191) {
            return false;
        }
        if (Util.isJumpInstruction(byte1)) {
            final int jumpTarget = Util.getJumpTarget(n, codeIterator);
            if (byte1 == 168 || byte1 == 201) {
                final Subroutine subroutine2 = this.subTable.get(new Integer(jumpTarget));
                if (subroutine2 == null) {
                    final Subroutine subroutine3 = new Subroutine(jumpTarget, n);
                    this.subTable.put(new Integer(jumpTarget), subroutine3);
                    this.scan(jumpTarget, codeIterator, subroutine3);
                }
                else {
                    subroutine2.addCaller(n);
                }
            }
            else {
                this.scan(jumpTarget, codeIterator, subroutine);
                if (Util.isGoto(byte1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void scanLookupSwitch(final int n, final CodeIterator codeIterator, final Subroutine subroutine) throws BadBytecode {
        int i = (n & 0xFFFFFFFC) + 4;
        this.scan(n + codeIterator.s32bitAt(i), codeIterator, subroutine);
        i += 4;
        final int n2 = codeIterator.s32bitAt(i) * 8;
        for (i += 4, final int n3 = n2 + i, i += 4; i < n3; i += 8) {
            this.scan(codeIterator.s32bitAt(i) + n, codeIterator, subroutine);
        }
    }
    
    private void scanTableSwitch(final int n, final CodeIterator codeIterator, final Subroutine subroutine) throws BadBytecode {
        int i = (n & 0xFFFFFFFC) + 4;
        this.scan(n + codeIterator.s32bitAt(i), codeIterator, subroutine);
        i += 4;
        final int s32bit = codeIterator.s32bitAt(i);
        i += 4;
        final int n2 = (codeIterator.s32bitAt(i) - s32bit + 1) * 4;
        for (i += 4; i < n2 + i; i += 4) {
            this.scan(codeIterator.s32bitAt(i) + n, codeIterator, subroutine);
        }
    }
}
