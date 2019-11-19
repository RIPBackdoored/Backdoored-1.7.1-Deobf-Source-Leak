package javassist.util;

import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.MethodEntryEvent;
import java.util.List;
import com.sun.jdi.ReferenceType;
import java.util.HashMap;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;
import java.util.Iterator;
import com.sun.jdi.Bootstrap;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import java.io.IOException;
import java.util.Map;
import com.sun.jdi.request.MethodEntryRequest;
import com.sun.jdi.VirtualMachine;

public class HotSwapper
{
    private VirtualMachine jvm;
    private MethodEntryRequest request;
    private Map newClassFiles;
    private Trigger trigger;
    private static final String HOST_NAME = "localhost";
    private static final String TRIGGER_NAME;
    
    public HotSwapper(final int n) throws IOException, IllegalConnectorArgumentsException {
        this(Integer.toString(n));
    }
    
    public HotSwapper(final String value) throws IOException, IllegalConnectorArgumentsException {
        super();
        this.jvm = null;
        this.request = null;
        this.newClassFiles = null;
        this.trigger = new Trigger();
        final AttachingConnector attachingConnector = (AttachingConnector)this.findConnector("com.sun.jdi.SocketAttach");
        final Map defaultArguments = attachingConnector.defaultArguments();
        defaultArguments.get("hostname").setValue("localhost");
        defaultArguments.get("port").setValue(value);
        this.jvm = attachingConnector.attach(defaultArguments);
        this.request = methodEntryRequests(this.jvm.eventRequestManager(), HotSwapper.TRIGGER_NAME);
    }
    
    private Connector findConnector(final String s) throws IOException {
        for (final Connector connector : Bootstrap.virtualMachineManager().allConnectors()) {
            if (connector.name().equals(s)) {
                return connector;
            }
        }
        throw new IOException("Not found: " + s);
    }
    
    private static MethodEntryRequest methodEntryRequests(final EventRequestManager eventRequestManager, final String s) {
        final MethodEntryRequest methodEntryRequest = eventRequestManager.createMethodEntryRequest();
        methodEntryRequest.addClassFilter(s);
        methodEntryRequest.setSuspendPolicy(1);
        return methodEntryRequest;
    }
    
    private void deleteEventRequest(final EventRequestManager eventRequestManager, final MethodEntryRequest methodEntryRequest) {
        eventRequestManager.deleteEventRequest((EventRequest)methodEntryRequest);
    }
    
    public void reload(final String s, final byte[] array) {
        final ReferenceType refType = this.toRefType(s);
        final HashMap<ReferenceType, byte[]> hashMap = new HashMap<ReferenceType, byte[]>();
        hashMap.put(refType, array);
        this.reload2(hashMap, s);
    }
    
    public void reload(final Map map) {
        final Iterator<Map.Entry<String, V>> iterator = map.entrySet().iterator();
        final HashMap<ReferenceType, Object> hashMap = new HashMap<ReferenceType, Object>();
        String s = null;
        while (iterator.hasNext()) {
            final Map.Entry<String, V> entry = iterator.next();
            s = entry.getKey();
            hashMap.put(this.toRefType(s), entry.getValue());
        }
        if (s != null) {
            this.reload2(hashMap, s + " etc.");
        }
    }
    
    private ReferenceType toRefType(final String s) {
        final List classesByName = this.jvm.classesByName(s);
        if (classesByName == null || classesByName.isEmpty()) {
            throw new RuntimeException("no such class: " + s);
        }
        return classesByName.get(0);
    }
    
    private void reload2(final Map newClassFiles, final String s) {
        synchronized (this.trigger) {
            this.startDaemon();
            this.newClassFiles = newClassFiles;
            this.request.enable();
            this.trigger.doSwap();
            this.request.disable();
            if (this.newClassFiles != null) {
                this.newClassFiles = null;
                throw new RuntimeException("failed to reload: " + s);
            }
        }
    }
    
    private void startDaemon() {
        new Thread() {
            final /* synthetic */ HotSwapper this$0;
            
            HotSwapper$1() {
                this.this$0 = this$0;
                super();
            }
            
            private void errorMsg(final Throwable t) {
                System.err.print("Exception in thread \"HotSwap\" ");
                t.printStackTrace(System.err);
            }
            
            @Override
            public void run() {
                EventSet waitEvent = null;
                try {
                    waitEvent = this.this$0.waitEvent();
                    final EventIterator eventIterator = waitEvent.eventIterator();
                    while (eventIterator.hasNext()) {
                        if (eventIterator.nextEvent() instanceof MethodEntryEvent) {
                            this.this$0.hotswap();
                            break;
                        }
                    }
                }
                catch (Throwable t) {
                    this.errorMsg(t);
                }
                try {
                    if (waitEvent != null) {
                        waitEvent.resume();
                    }
                }
                catch (Throwable t2) {
                    this.errorMsg(t2);
                }
            }
        }.start();
    }
    
    EventSet waitEvent() throws InterruptedException {
        return this.jvm.eventQueue().remove();
    }
    
    void hotswap() {
        this.jvm.redefineClasses(this.newClassFiles);
        this.newClassFiles = null;
    }
    
    static {
        TRIGGER_NAME = Trigger.class.getName();
    }
}
