package javassist.tools.web;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;

public class Viewer extends ClassLoader
{
    private String server;
    private int port;
    
    public static void main(final String[] array) throws Throwable {
        if (array.length >= 3) {
            final Viewer viewer = new Viewer(array[0], Integer.parseInt(array[1]));
            final String[] array2 = new String[array.length - 3];
            System.arraycopy(array, 3, array2, 0, array.length - 3);
            viewer.run(array[2], array2);
        }
        else {
            System.err.println("Usage: java javassist.tools.web.Viewer <host> <port> class [args ...]");
        }
    }
    
    public Viewer(final String server, final int port) {
        super();
        this.server = server;
        this.port = port;
    }
    
    public String getServer() {
        return this.server;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void run(final String s, final String[] array) throws Throwable {
        final Class<?> loadClass = this.loadClass(s);
        try {
            loadClass.getDeclaredMethod("main", String[].class).invoke(null, array);
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }
    
    @Override
    protected synchronized Class loadClass(final String s, final boolean b) throws ClassNotFoundException {
        Class<?> clazz = this.findLoadedClass(s);
        if (clazz == null) {
            clazz = (Class<?>)this.findClass(s);
        }
        if (clazz == null) {
            throw new ClassNotFoundException(s);
        }
        if (b) {
            this.resolveClass(clazz);
        }
        return clazz;
    }
    
    @Override
    protected Class findClass(final String s) throws ClassNotFoundException {
        Class<?> clazz = null;
        if (s.startsWith("java.") || s.startsWith("javax.") || s.equals("javassist.tools.web.Viewer")) {
            clazz = this.findSystemClass(s);
        }
        if (clazz == null) {
            try {
                final byte[] fetchClass = this.fetchClass(s);
                if (fetchClass != null) {
                    clazz = this.defineClass(s, fetchClass, 0, fetchClass.length);
                }
            }
            catch (Exception ex) {}
        }
        return clazz;
    }
    
    protected byte[] fetchClass(final String s) throws Exception {
        final URLConnection openConnection = new URL("http", this.server, this.port, "/" + s.replace('.', '/') + ".class").openConnection();
        openConnection.connect();
        final int contentLength = openConnection.getContentLength();
        final InputStream inputStream = openConnection.getInputStream();
        byte[] stream;
        if (contentLength <= 0) {
            stream = this.readStream(inputStream);
        }
        else {
            stream = new byte[contentLength];
            int i = 0;
            do {
                final int read = inputStream.read(stream, i, contentLength - i);
                if (read < 0) {
                    inputStream.close();
                    throw new IOException("the stream was closed: " + s);
                }
                i += read;
            } while (i < contentLength);
        }
        inputStream.close();
        return stream;
    }
    
    private byte[] readStream(final InputStream inputStream) throws IOException {
        byte[] array = new byte[4096];
        int n = 0;
        int i = 0;
        do {
            n += i;
            if (array.length - n <= 0) {
                final byte[] array2 = new byte[array.length * 2];
                System.arraycopy(array, 0, array2, 0, n);
                array = array2;
            }
            i = inputStream.read(array, n, array.length - n);
        } while (i >= 0);
        final byte[] array3 = new byte[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
}
