package javassist;

class FieldInitLink
{
    FieldInitLink next;
    CtField field;
    CtField.Initializer init;
    
    FieldInitLink(final CtField field, final CtField.Initializer init) {
        super();
        this.next = null;
        this.field = field;
        this.init = init;
    }
}
