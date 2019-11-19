package javassist.tools.web;

import java.io.IOException;
import java.net.Socket;

class ServiceThread extends Thread
{
    Webserver web;
    Socket sock;
    
    public ServiceThread(final Webserver web, final Socket sock) {
        super();
        this.web = web;
        this.sock = sock;
    }
    
    @Override
    public void run() {
        try {
            this.web.process(this.sock);
        }
        catch (IOException ex) {}
    }
}
