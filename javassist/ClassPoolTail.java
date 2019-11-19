package javassist;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

final class ClassPoolTail
{
    protected ClassPathList pathList;
    
    public ClassPoolTail() {
        super();
        this.pathList = null;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("[class path: ");
        for (ClassPathList list = this.pathList; list != null; list = list.next) {
            sb.append(list.path.toString());
            sb.append(File.pathSeparatorChar);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public synchronized ClassPath insertClassPath(final ClassPath classPath) {
        this.pathList = new ClassPathList(classPath, this.pathList);
        return classPath;
    }
    
    public synchronized ClassPath appendClassPath(final ClassPath classPath) {
        final ClassPathList list = new ClassPathList(classPath, null);
        ClassPathList list2 = this.pathList;
        if (list2 == null) {
            this.pathList = list;
        }
        else {
            while (list2.next != null) {
                list2 = list2.next;
            }
            list2.next = list;
        }
        return classPath;
    }
    
    public synchronized void removeClassPath(final ClassPath classPath) {
        ClassPathList list = this.pathList;
        if (list != null) {
            if (list.path == classPath) {
                this.pathList = list.next;
            }
            else {
                while (list.next != null) {
                    if (list.next.path == classPath) {
                        list.next = list.next.next;
                    }
                    else {
                        list = list.next;
                    }
                }
            }
        }
        classPath.close();
    }
    
    public ClassPath appendSystemPath() {
        return this.appendClassPath(new ClassClassPath());
    }
    
    public ClassPath insertClassPath(final String s) throws NotFoundException {
        return this.insertClassPath(makePathObject(s));
    }
    
    public ClassPath appendClassPath(final String s) throws NotFoundException {
        return this.appendClassPath(makePathObject(s));
    }
    
    private static ClassPath makePathObject(final String s) throws NotFoundException {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.endsWith(".jar") || lowerCase.endsWith(".zip")) {
            return new JarClassPath(s);
        }
        final int length = s.length();
        if (length > 2 && s.charAt(length - 1) == '*' && (s.charAt(length - 2) == '/' || s.charAt(length - 2) == File.separatorChar)) {
            return new JarDirClassPath(s.substring(0, length - 2));
        }
        return new DirClassPath(s);
    }
    
    void writeClassfile(final String s, final OutputStream outputStream) throws NotFoundException, IOException, CannotCompileException {
        final InputStream openClassfile = this.openClassfile(s);
        if (openClassfile == null) {
            throw new NotFoundException(s);
        }
        try {
            copyStream(openClassfile, outputStream);
        }
        finally {
            openClassfile.close();
        }
    }
    
    InputStream openClassfile(final String s) throws NotFoundException {
        ClassPathList list = this.pathList;
        InputStream openClassfile = null;
        NotFoundException ex = null;
        while (list != null) {
            try {
                openClassfile = list.path.openClassfile(s);
            }
            catch (NotFoundException ex2) {
                if (ex == null) {
                    ex = ex2;
                }
            }
            if (openClassfile != null) {
                return openClassfile;
            }
            list = list.next;
        }
        if (ex != null) {
            throw ex;
        }
        return null;
    }
    
    public URL find(final String s) {
        for (ClassPathList list = this.pathList; list != null; list = list.next) {
            final URL find = list.path.find(s);
            if (find != null) {
                return find;
            }
        }
        return null;
    }
    
    public static byte[] readStream(final InputStream inputStream) throws IOException {
        final byte[][] array = new byte[8][];
        int n = 4096;
        for (int i = 0; i < 8; ++i) {
            array[i] = new byte[n];
            int j = 0;
            do {
                final int read = inputStream.read(array[i], j, n - j);
                if (read < 0) {
                    final byte[] array2 = new byte[n - 4096 + j];
                    int n2 = 0;
                    for (int k = 0; k < i; ++k) {
                        System.arraycopy(array[k], 0, array2, n2, n2 + 4096);
                        n2 = n2 + n2 + 4096;
                    }
                    System.arraycopy(array[i], 0, array2, n2, j);
                    return array2;
                }
                j += read;
            } while (j < n);
            n *= 2;
        }
        throw new IOException("too much data");
    }
    
    public static void copyStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        int n = 4096;
        byte[] array = null;
        for (int i = 0; i < 64; ++i) {
            if (i < 8) {
                n *= 2;
                array = new byte[n];
            }
            int j = 0;
            do {
                final int read = inputStream.read(array, j, n - j);
                if (read < 0) {
                    outputStream.write(array, 0, j);
                    return;
                }
                j += read;
            } while (j < n);
            outputStream.write(array);
        }
        throw new IOException("too much data");
    }
}
