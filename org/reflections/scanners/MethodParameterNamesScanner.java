package org.reflections.scanners;

import java.util.Iterator;
import org.reflections.adapters.MetadataAdapter;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.lang.reflect.Modifier;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.LocalVariableAttribute;

public class MethodParameterNamesScanner extends AbstractScanner
{
    public MethodParameterNamesScanner() {
        super();
    }
    
    @Override
    public void scan(final Object o) {
        final MetadataAdapter metadataAdapter = this.getMetadataAdapter();
        for (final MethodInfo next : metadataAdapter.getMethods(o)) {
            final String methodFullKey = metadataAdapter.getMethodFullKey(o, next);
            if (this.acceptResult(methodFullKey)) {
                final LocalVariableAttribute localVariableAttribute = (LocalVariableAttribute)next.getCodeAttribute().getAttribute("LocalVariableTable");
                final int tableLength = localVariableAttribute.tableLength();
                int i = Modifier.isStatic(next.getAccessFlags()) ? 0 : 1;
                if (i >= tableLength) {
                    continue;
                }
                final ArrayList parts = new ArrayList<String>(tableLength - i);
                while (i < tableLength) {
                    parts.add(next.getConstPool().getUtf8Info(localVariableAttribute.nameIndex(i++)));
                }
                this.getStore().put((Object)methodFullKey, (Object)Joiner.on(", ").join(parts));
            }
        }
    }
}
