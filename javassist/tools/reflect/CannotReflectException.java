package javassist.tools.reflect;

import javassist.CannotCompileException;

public class CannotReflectException extends CannotCompileException
{
    public CannotReflectException(final String s) {
        super(s);
    }
}
