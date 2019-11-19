package javassist.bytecode;

static class LdcEntry
{
    LdcEntry next;
    int where;
    int index;
    
    LdcEntry() {
        super();
    }
    
    static byte[] doit(byte[] changeLdcToLdcW, final LdcEntry ldcEntry, final ExceptionTable exceptionTable, final CodeAttribute codeAttribute) throws BadBytecode {
        if (ldcEntry != null) {
            changeLdcToLdcW = CodeIterator.changeLdcToLdcW(changeLdcToLdcW, exceptionTable, codeAttribute, ldcEntry);
        }
        return changeLdcToLdcW;
    }
}
