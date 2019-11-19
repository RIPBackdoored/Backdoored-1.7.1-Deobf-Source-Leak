package javassist.tools.rmi;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import javassist.tools.web.BadHttpRequest;
import java.io.OutputStream;
import java.io.InputStream;
import javassist.Translator;
import javassist.ClassPool;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.util.Hashtable;
import javassist.tools.web.Webserver;

public class AppletServer extends Webserver
{
    private StubGenerator stubGen;
    private Hashtable exportedNames;
    private Vector exportedObjects;
    private static final byte[] okHeader;
    
    public AppletServer(final String s) throws IOException, NotFoundException, CannotCompileException {
        this(Integer.parseInt(s));
    }
    
    public AppletServer(final int n) throws IOException, NotFoundException, CannotCompileException {
        this(ClassPool.getDefault(), new StubGenerator(), n);
    }
    
    public AppletServer(final int n, final ClassPool classPool) throws IOException, NotFoundException, CannotCompileException {
        this(new ClassPool(classPool), new StubGenerator(), n);
    }
    
    private AppletServer(final ClassPool classPool, final StubGenerator stubGen, final int n) throws IOException, NotFoundException, CannotCompileException {
        super(n);
        this.exportedNames = new Hashtable();
        this.exportedObjects = new Vector();
        this.addTranslator(classPool, this.stubGen = stubGen);
    }
    
    @Override
    public void run() {
        super.run();
    }
    
    public synchronized int exportObject(final String s, final Object object) throws CannotCompileException {
        final Class<?> class1 = object.getClass();
        final ExportedObject exportedObject = new ExportedObject();
        exportedObject.object = object;
        exportedObject.methods = class1.getMethods();
        this.exportedObjects.addElement(exportedObject);
        exportedObject.identifier = this.exportedObjects.size() - 1;
        if (s != null) {
            this.exportedNames.put(s, exportedObject);
        }
        try {
            this.stubGen.makeProxyClass(class1);
        }
        catch (NotFoundException ex) {
            throw new CannotCompileException(ex);
        }
        return exportedObject.identifier;
    }
    
    @Override
    public void doReply(final InputStream inputStream, final OutputStream outputStream, final String s) throws IOException, BadHttpRequest {
        if (s.startsWith("POST /rmi ")) {
            this.processRMI(inputStream, outputStream);
        }
        else if (s.startsWith("POST /lookup ")) {
            this.lookupName(s, inputStream, outputStream);
        }
        else {
            super.doReply(inputStream, outputStream, s);
        }
    }
    
    private void processRMI(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        final int int1 = objectInputStream.readInt();
        final int int2 = objectInputStream.readInt();
        Throwable t = null;
        Object convertRvalue = null;
        try {
            final ExportedObject exportedObject = this.exportedObjects.elementAt(int1);
            convertRvalue = this.convertRvalue(exportedObject.methods[int2].invoke(exportedObject.object, this.readParameters(objectInputStream)));
        }
        catch (Exception ex) {
            t = ex;
            this.logging2(ex.toString());
        }
        outputStream.write(AppletServer.okHeader);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        if (t != null) {
            objectOutputStream.writeBoolean(false);
            objectOutputStream.writeUTF(t.toString());
        }
        else {
            try {
                objectOutputStream.writeBoolean(true);
                objectOutputStream.writeObject(convertRvalue);
            }
            catch (NotSerializableException ex2) {
                this.logging2(ex2.toString());
            }
            catch (InvalidClassException ex3) {
                this.logging2(ex3.toString());
            }
        }
        objectOutputStream.flush();
        objectOutputStream.close();
        objectInputStream.close();
    }
    
    private Object[] readParameters(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        final int int1 = objectInputStream.readInt();
        final Object[] array = new Object[int1];
        for (int i = 0; i < int1; ++i) {
            Object o = objectInputStream.readObject();
            if (o instanceof RemoteRef) {
                o = ((ExportedObject)this.exportedObjects.elementAt(((RemoteRef)o).oid)).object;
            }
            array[i] = o;
        }
        return array;
    }
    
    private Object convertRvalue(final Object o) throws CannotCompileException {
        if (o == null) {
            return null;
        }
        final String name = o.getClass().getName();
        if (this.stubGen.isProxyClass(name)) {
            return new RemoteRef(this.exportObject(null, o), name);
        }
        return o;
    }
    
    private void lookupName(final String s, final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        final String utf = DataInputStream.readUTF(objectInputStream);
        final ExportedObject exportedObject = this.exportedNames.get(utf);
        outputStream.write(AppletServer.okHeader);
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        if (exportedObject == null) {
            this.logging2(utf + "not found.");
            objectOutputStream.writeInt(-1);
            objectOutputStream.writeUTF("error");
        }
        else {
            this.logging2(utf);
            objectOutputStream.writeInt(exportedObject.identifier);
            objectOutputStream.writeUTF(exportedObject.object.getClass().getName());
        }
        objectOutputStream.flush();
        objectOutputStream.close();
        objectInputStream.close();
    }
    
    static {
        okHeader = "HTTP/1.0 200 OK\r\n\r\n".getBytes();
    }
}
