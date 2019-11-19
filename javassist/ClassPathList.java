package javassist;

final class ClassPathList
{
    ClassPathList next;
    ClassPath path;
    
    ClassPathList(final ClassPath path, final ClassPathList next) {
        super();
        this.next = next;
        this.path = path;
    }
}
