package javassist;

import javassist.bytecode.InnerClassesAttribute;

class CtNewNestedClass extends CtNewClass
{
    CtNewNestedClass(final String s, final ClassPool classPool, final boolean b, final CtClass ctClass) {
        super(s, classPool, b, ctClass);
    }
    
    @Override
    public void setModifiers(int modifiers) {
        modifiers &= 0xFFFFFFF7;
        super.setModifiers(modifiers);
        updateInnerEntry(modifiers, this.getName(), this, true);
    }
    
    private static void updateInnerEntry(final int n, final String s, final CtClass ctClass, final boolean b) {
        final InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute)ctClass.getClassFile2().getAttribute("InnerClasses");
        if (innerClassesAttribute == null) {
            return;
        }
        for (int tableLength = innerClassesAttribute.tableLength(), i = 0; i < tableLength; ++i) {
            if (s.equals(innerClassesAttribute.innerClass(i))) {
                innerClassesAttribute.setAccessFlags(i, n | (innerClassesAttribute.accessFlags(i) & 0x8));
                final String outerClass = innerClassesAttribute.outerClass(i);
                if (outerClass == null || !b) {
                    break;
                }
                try {
                    updateInnerEntry(n, s, ctClass.getClassPool().get(outerClass), false);
                    break;
                }
                catch (NotFoundException ex) {
                    throw new RuntimeException("cannot find the declaring class: " + outerClass);
                }
            }
        }
    }
}
