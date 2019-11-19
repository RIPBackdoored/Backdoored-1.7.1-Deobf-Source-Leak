package javassist.tools.rmi;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.applet.Applet;
import java.io.Serializable;

public class ObjectImporter implements Serializable
{
    private final byte[] endofline;
    private String servername;
    private String orgServername;
    private int port;
    private int orgPort;
    protected byte[] lookupCommand;
    protected byte[] rmiCommand;
    private static final Class[] proxyConstructorParamTypes;
    
    public ObjectImporter(final Applet applet) {
        super();
        this.endofline = new byte[] { 13, 10 };
        this.lookupCommand = "POST /lookup HTTP/1.0".getBytes();
        this.rmiCommand = "POST /rmi HTTP/1.0".getBytes();
        final URL codeBase = applet.getCodeBase();
        final String host = codeBase.getHost();
        this.servername = host;
        this.orgServername = host;
        final int port = codeBase.getPort();
        this.port = port;
        this.orgPort = port;
    }
    
    public ObjectImporter(final String s, final int n) {
        super();
        this.endofline = new byte[] { 13, 10 };
        this.lookupCommand = "POST /lookup HTTP/1.0".getBytes();
        this.rmiCommand = "POST /rmi HTTP/1.0".getBytes();
        this.servername = s;
        this.orgServername = s;
        this.port = n;
        this.orgPort = n;
    }
    
    public Object getObject(final String s) {
        try {
            return this.lookupObject(s);
        }
        catch (ObjectNotFoundException ex) {
            return null;
        }
    }
    
    public void setHttpProxy(final String servername, final int port) {
        final String string = "POST http://" + this.orgServername + ":" + this.orgPort;
        this.lookupCommand = (string + "/lookup HTTP/1.0").getBytes();
        this.rmiCommand = (string + "/rmi HTTP/1.0").getBytes();
        this.servername = servername;
        this.port = port;
    }
    
    public Object lookupObject(final String s) throws ObjectNotFoundException {
        try {
            final Socket socket = new Socket(this.servername, this.port);
            final OutputStream outputStream = socket.getOutputStream();
            outputStream.write(this.lookupCommand);
            outputStream.write(this.endofline);
            outputStream.write(this.endofline);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeUTF(s);
            objectOutputStream.flush();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            this.skipHeader(bufferedInputStream);
            final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            final int int1 = objectInputStream.readInt();
            final String utf = objectInputStream.readUTF();
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            if (int1 >= 0) {
                return this.createProxy(int1, utf);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ObjectNotFoundException(s, ex);
        }
        throw new ObjectNotFoundException(s);
    }
    
    private Object createProxy(final int n, final String s) throws Exception {
        return Class.forName(s).getConstructor((Class<?>[])ObjectImporter.proxyConstructorParamTypes).newInstance(this, new Integer(n));
    }
    
    public Object call(final int n, final int n2, final Object[] array) throws RemoteException {
        boolean boolean1;
        Object o;
        String utf;
        try {
            final Socket socket = new Socket(this.servername, this.port);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            bufferedOutputStream.write(this.rmiCommand);
            bufferedOutputStream.write(this.endofline);
            bufferedOutputStream.write(this.endofline);
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeInt(n);
            objectOutputStream.writeInt(n2);
            this.writeParameters(objectOutputStream, array);
            objectOutputStream.flush();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            this.skipHeader(bufferedInputStream);
            final ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            boolean1 = objectInputStream.readBoolean();
            o = null;
            utf = null;
            if (boolean1) {
                o = objectInputStream.readObject();
            }
            else {
                utf = objectInputStream.readUTF();
            }
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            if (o instanceof RemoteRef) {
                final RemoteRef remoteRef = (RemoteRef)o;
                o = this.createProxy(remoteRef.oid, remoteRef.classname);
            }
        }
        catch (ClassNotFoundException ex) {
            throw new RemoteException(ex);
        }
        catch (IOException ex2) {
            throw new RemoteException(ex2);
        }
        catch (Exception ex3) {
            throw new RemoteException(ex3);
        }
        if (boolean1) {
            return o;
        }
        throw new RemoteException(utf);
    }
    
    private void skipHeader(final InputStream inputStream) throws IOException {
        int i;
        do {
            i = 0;
            int read;
            while ((read = inputStream.read()) >= 0 && read != 13) {
                ++i;
            }
            inputStream.read();
        } while (i > 0);
    }
    
    private void writeParameters(final ObjectOutputStream objectOutputStream, final Object[] array) throws IOException {
        final int length = array.length;
        objectOutputStream.writeInt(length);
        for (int i = 0; i < length; ++i) {
            if (array[i] instanceof Proxy) {
                objectOutputStream.writeObject(new RemoteRef(((Proxy)array[i])._getObjectId()));
            }
            else {
                objectOutputStream.writeObject(array[i]);
            }
        }
    }
    
    static {
        proxyConstructorParamTypes = new Class[] { ObjectImporter.class, Integer.TYPE };
    }
}
