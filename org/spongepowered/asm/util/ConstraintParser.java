package org.spongepowered.asm.util;

import org.spongepowered.asm.util.throwables.ConstraintViolationException;
import java.util.regex.Matcher;
import org.spongepowered.asm.util.throwables.InvalidConstraintException;
import java.util.regex.Pattern;
import org.spongepowered.asm.lib.tree.AnnotationNode;

public final class ConstraintParser
{
    private ConstraintParser() {
        super();
    }
    
    public static Constraint parse(final String s) {
        if (s == null || s.length() == 0) {
            return Constraint.NONE;
        }
        final String[] split = s.replaceAll("\\s", "").toUpperCase().split(";");
        Constraint constraint = null;
        final String[] array = split;
        for (int length = array.length, i = 0; i < length; ++i) {
            final Constraint constraint2 = new Constraint(array[i]);
            if (constraint == null) {
                constraint = constraint2;
            }
            else {
                constraint.append(constraint2);
            }
        }
        return (constraint != null) ? constraint : Constraint.NONE;
    }
    
    public static Constraint parse(final AnnotationNode annotationNode) {
        return parse(Annotations.<String>getValue(annotationNode, "constraints", ""));
    }
}
