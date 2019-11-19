package r.k.d.m.q;

import javax.script.Invocable;
import org.apache.logging.log4j.Logger;
import javax.script.ScriptException;
import java.util.Objects;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class u
{
    private final ScriptEngine see;
    public static final int seo;
    public static final boolean set;
    
    public u() {
        super();
        Objects.<ScriptEngine>requireNonNull(this.see = new ScriptEngineManager(null).getEngineByName("nashorn"));
    }
    
    public u sy(final String s) throws ScriptException {
        this.see.eval(s);
        return this;
    }
    
    public u d(final Logger logger) {
        this.see.put("logger", logger);
        return this;
    }
    
    public Object s(final String s, final Object... array) throws ScriptException, NoSuchMethodException {
        return ((Invocable)this.see).invokeFunction(s, array);
    }
}
