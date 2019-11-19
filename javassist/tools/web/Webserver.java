package javassist.tools.web;

import javassist.CtClass;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.util.Date;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.Socket;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import java.io.IOException;
import javassist.Translator;
import javassist.ClassPool;
import java.net.ServerSocket;

public class Webserver
{
    private ServerSocket socket;
    private ClassPool classPool;
    protected Translator translator;
    private static final byte[] endofline;
    private static final int typeHtml = 1;
    private static final int typeClass = 2;
    private static final int typeGif = 3;
    private static final int typeJpeg = 4;
    private static final int typeText = 5;
    public String debugDir;
    public String htmlfileBase;
    
    public static void main(final String[] array) throws IOException {
        if (array.length == 1) {
            new Webserver(array[0]).run();
        }
        else {
            System.err.println("Usage: java javassist.tools.web.Webserver <port number>");
        }
    }
    
    public Webserver(final String s) throws IOException {
        this(Integer.parseInt(s));
    }
    
    public Webserver(final int n) throws IOException {
        super();
        this.debugDir = null;
        this.htmlfileBase = null;
        this.socket = new ServerSocket(n);
        this.classPool = null;
        this.translator = null;
    }
    
    public void setClassPool(final ClassPool classPool) {
        this.classPool = classPool;
    }
    
    public void addTranslator(final ClassPool classPool, final Translator translator) throws NotFoundException, CannotCompileException {
        this.classPool = classPool;
        (this.translator = translator).start(this.classPool);
    }
    
    public void end() throws IOException {
        this.socket.close();
    }
    
    public void logging(final String s) {
        System.out.println(s);
    }
    
    public void logging(final String s, final String s2) {
        System.out.print(s);
        System.out.print(" ");
        System.out.println(s2);
    }
    
    public void logging(final String s, final String s2, final String s3) {
        System.out.print(s);
        System.out.print(" ");
        System.out.print(s2);
        System.out.print(" ");
        System.out.println(s3);
    }
    
    public void logging2(final String s) {
        System.out.print("    ");
        System.out.println(s);
    }
    
    public void run() {
        System.err.println("ready to service...");
    Label_0008_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        new ServiceThread(this, this.socket.accept()).start();
                    }
                }
                catch (IOException ex) {
                    this.logging(ex.toString());
                    continue Label_0008_Outer;
                }
                continue;
            }
        }
    }
    
    final void process(final Socket socket) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        final String line = this.readLine(bufferedInputStream);
        this.logging(socket.getInetAddress().getHostName(), new Date().toString(), line);
        while (this.skipLine(bufferedInputStream) > 0) {}
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        try {
            this.doReply(bufferedInputStream, bufferedOutputStream, line);
        }
        catch (BadHttpRequest badHttpRequest) {
            this.replyError(bufferedOutputStream, badHttpRequest);
        }
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();
    }
    
    private String readLine(final InputStream inputStream) throws IOException {
        final StringBuffer sb = new StringBuffer();
        int read;
        while ((read = inputStream.read()) >= 0 && read != 13) {
            sb.append((char)read);
        }
        inputStream.read();
        return sb.toString();
    }
    
    private int skipLine(final InputStream inputStream) throws IOException {
        int n = 0;
        int read;
        while ((read = inputStream.read()) >= 0 && read != 13) {
            ++n;
        }
        inputStream.read();
        return n;
    }
    
    public void doReply(final InputStream inputStream, final OutputStream outputStream, final String s) throws IOException, BadHttpRequest {
        if (!s.startsWith("GET /")) {
            throw new BadHttpRequest();
        }
        String s3;
        final String s2 = s3 = s.substring(5, s.indexOf(32, 5));
        int n;
        if (s3.endsWith(".class")) {
            n = 2;
        }
        else if (s3.endsWith(".html") || s3.endsWith(".htm")) {
            n = 1;
        }
        else if (s3.endsWith(".gif")) {
            n = 3;
        }
        else if (s3.endsWith(".jpg")) {
            n = 4;
        }
        else {
            n = 5;
        }
        final int length = s3.length();
        if (n == 2 && this.letUsersSendClassfile(outputStream, s3, length)) {
            return;
        }
        this.checkFilename(s3, length);
        if (this.htmlfileBase != null) {
            s3 = this.htmlfileBase + s3;
        }
        if (File.separatorChar != '/') {
            s3 = s3.replace('/', File.separatorChar);
        }
        final File file = new File(s3);
        if (file.canRead()) {
            this.sendHeader(outputStream, file.length(), n);
            final FileInputStream fileInputStream = new FileInputStream(file);
            final byte[] array = new byte[4096];
            while (true) {
                final int read = fileInputStream.read(array);
                if (read <= 0) {
                    break;
                }
                outputStream.write(array, 0, read);
            }
            fileInputStream.close();
            return;
        }
        if (n == 2) {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/" + s2);
            if (resourceAsStream != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final byte[] array2 = new byte[4096];
                while (true) {
                    final int read2 = resourceAsStream.read(array2);
                    if (read2 <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(array2, 0, read2);
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                this.sendHeader(outputStream, byteArray.length, 2);
                outputStream.write(byteArray);
                resourceAsStream.close();
                return;
            }
        }
        throw new BadHttpRequest();
    }
    
    private void checkFilename(final String s, final int n) throws BadHttpRequest {
        for (int i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            if (!Character.isJavaIdentifierPart(char1) && char1 != '.' && char1 != '/') {
                throw new BadHttpRequest();
            }
        }
        if (s.indexOf("..") >= 0) {
            throw new BadHttpRequest();
        }
    }
    
    private boolean letUsersSendClassfile(final OutputStream outputStream, final String s, final int n) throws IOException, BadHttpRequest {
        if (this.classPool == null) {
            return false;
        }
        final String replace = s.substring(0, n - 6).replace('/', '.');
        byte[] bytecode;
        try {
            if (this.translator != null) {
                this.translator.onLoad(this.classPool, replace);
            }
            final CtClass value = this.classPool.get(replace);
            bytecode = value.toBytecode();
            if (this.debugDir != null) {
                value.writeFile(this.debugDir);
            }
        }
        catch (Exception ex) {
            throw new BadHttpRequest(ex);
        }
        this.sendHeader(outputStream, bytecode.length, 2);
        outputStream.write(bytecode);
        return true;
    }
    
    private void sendHeader(final OutputStream outputStream, final long n, final int n2) throws IOException {
        outputStream.write("HTTP/1.0 200 OK".getBytes());
        outputStream.write(Webserver.endofline);
        outputStream.write("Content-Length: ".getBytes());
        outputStream.write(Long.toString(n).getBytes());
        outputStream.write(Webserver.endofline);
        if (n2 == 2) {
            outputStream.write("Content-Type: application/octet-stream".getBytes());
        }
        else if (n2 == 1) {
            outputStream.write("Content-Type: text/html".getBytes());
        }
        else if (n2 == 3) {
            outputStream.write("Content-Type: image/gif".getBytes());
        }
        else if (n2 == 4) {
            outputStream.write("Content-Type: image/jpg".getBytes());
        }
        else if (n2 == 5) {
            outputStream.write("Content-Type: text/plain".getBytes());
        }
        outputStream.write(Webserver.endofline);
        outputStream.write(Webserver.endofline);
    }
    
    private void replyError(final OutputStream outputStream, final BadHttpRequest badHttpRequest) throws IOException {
        this.logging2("bad request: " + badHttpRequest.toString());
        outputStream.write("HTTP/1.0 400 Bad Request".getBytes());
        outputStream.write(Webserver.endofline);
        outputStream.write(Webserver.endofline);
        outputStream.write("<H1>Bad Request</H1>".getBytes());
    }
    
    static {
        endofline = new byte[] { 13, 10 };
    }
}
