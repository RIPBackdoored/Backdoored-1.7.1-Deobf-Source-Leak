package javassist.bytecode;

import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.AnnotationMemberValue;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.EnumMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.ShortMemberValue;
import javassist.bytecode.annotation.LongMemberValue;
import javassist.bytecode.annotation.IntegerMemberValue;
import javassist.bytecode.annotation.FloatMemberValue;
import javassist.bytecode.annotation.DoubleMemberValue;
import javassist.bytecode.annotation.CharMemberValue;
import javassist.bytecode.annotation.ByteMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.Annotation;

static class Parser extends Walker
{
    ConstPool pool;
    Annotation[][] allParams;
    Annotation[] allAnno;
    Annotation currentAnno;
    MemberValue currentMember;
    
    Parser(final byte[] array, final ConstPool pool) {
        super(array);
        this.pool = pool;
    }
    
    Annotation[][] parseParameters() throws Exception {
        this.parameters();
        return this.allParams;
    }
    
    Annotation[] parseAnnotations() throws Exception {
        this.annotationArray();
        return this.allAnno;
    }
    
    MemberValue parseMemberValue() throws Exception {
        this.memberValue(0);
        return this.currentMember;
    }
    
    @Override
    void parameters(final int n, int annotationArray) throws Exception {
        final Annotation[][] allParams = new Annotation[n][];
        for (int i = 0; i < n; ++i) {
            annotationArray = this.annotationArray(annotationArray);
            allParams[i] = this.allAnno;
        }
        this.allParams = allParams;
    }
    
    @Override
    int annotationArray(int annotation, final int n) throws Exception {
        final Annotation[] allAnno = new Annotation[n];
        for (int i = 0; i < n; ++i) {
            annotation = this.annotation(annotation);
            allAnno[i] = this.currentAnno;
        }
        this.allAnno = allAnno;
        return annotation;
    }
    
    @Override
    int annotation(final int n, final int n2, final int n3) throws Exception {
        this.currentAnno = new Annotation(n2, this.pool);
        return super.annotation(n, n2, n3);
    }
    
    @Override
    int memberValuePair(int memberValuePair, final int n) throws Exception {
        memberValuePair = super.memberValuePair(memberValuePair, n);
        this.currentAnno.addMemberValue(n, this.currentMember);
        return memberValuePair;
    }
    
    @Override
    void constValueMember(final int n, final int n2) throws Exception {
        final ConstPool pool = this.pool;
        MemberValue currentMember = null;
        switch (n) {
            case 66:
                currentMember = new ByteMemberValue(n2, pool);
                break;
            case 67:
                currentMember = new CharMemberValue(n2, pool);
                break;
            case 68:
                currentMember = new DoubleMemberValue(n2, pool);
                break;
            case 70:
                currentMember = new FloatMemberValue(n2, pool);
                break;
            case 73:
                currentMember = new IntegerMemberValue(n2, pool);
                break;
            case 74:
                currentMember = new LongMemberValue(n2, pool);
                break;
            case 83:
                currentMember = new ShortMemberValue(n2, pool);
                break;
            case 90:
                currentMember = new BooleanMemberValue(n2, pool);
                break;
            case 115:
                currentMember = new StringMemberValue(n2, pool);
                break;
            default:
                throw new RuntimeException("unknown tag:" + n);
        }
        this.currentMember = currentMember;
        super.constValueMember(n, n2);
    }
    
    @Override
    void enumMemberValue(final int n, final int n2, final int n3) throws Exception {
        this.currentMember = new EnumMemberValue(n2, n3, this.pool);
        super.enumMemberValue(n, n2, n3);
    }
    
    @Override
    void classMemberValue(final int n, final int n2) throws Exception {
        this.currentMember = new ClassMemberValue(n2, this.pool);
        super.classMemberValue(n, n2);
    }
    
    @Override
    int annotationMemberValue(int annotationMemberValue) throws Exception {
        final Annotation currentAnno = this.currentAnno;
        annotationMemberValue = super.annotationMemberValue(annotationMemberValue);
        this.currentMember = new AnnotationMemberValue(this.currentAnno, this.pool);
        this.currentAnno = currentAnno;
        return annotationMemberValue;
    }
    
    @Override
    int arrayMemberValue(int memberValue, final int n) throws Exception {
        final ArrayMemberValue currentMember = new ArrayMemberValue(this.pool);
        final MemberValue[] value = new MemberValue[n];
        for (int i = 0; i < n; ++i) {
            memberValue = this.memberValue(memberValue);
            value[i] = this.currentMember;
        }
        currentMember.setValue(value);
        this.currentMember = currentMember;
        return memberValue;
    }
}
