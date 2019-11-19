package javassist.tools.reflect;

import javassist.NotFoundException;
import javassist.CannotCompileException;
import javassist.Translator;
import javassist.ClassPool;

public class Loader extends javassist.Loader
{
    protected Reflection reflection;
    
    public static void main(final String[] array) throws Throwable {
        new Loader().run(array);
    }
    
    public Loader() throws CannotCompileException, NotFoundException {
        super();
        this.delegateLoadingOf("javassist.tools.reflect.Loader");
        this.reflection = new Reflection();
        this.addTranslator(ClassPool.getDefault(), this.reflection);
    }
    
    public boolean makeReflective(final String s, final String s2, final String s3) throws CannotCompileException, NotFoundException {
        return this.reflection.makeReflective(s, s2, s3);
    }
}
