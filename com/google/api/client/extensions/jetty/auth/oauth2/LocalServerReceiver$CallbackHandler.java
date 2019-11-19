package com.google.api.client.extensions.jetty.auth.oauth2;

import java.io.PrintWriter;
import java.io.IOException;
import org.mortbay.jetty.Request;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.mortbay.jetty.handler.AbstractHandler;

class CallbackHandler extends AbstractHandler
{
    final /* synthetic */ LocalServerReceiver this$0;
    
    CallbackHandler(final LocalServerReceiver this$0) {
        this.this$0 = this$0;
        super();
    }
    
    public void handle(final String s, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final int n) throws IOException {
        if (!"/Callback".equals(s)) {
            return;
        }
        try {
            ((Request)httpServletRequest).setHandled(true);
            this.this$0.error = httpServletRequest.getParameter("error");
            this.this$0.code = httpServletRequest.getParameter("code");
            if (this.this$0.error == null && LocalServerReceiver.access$000(this.this$0) != null) {
                httpServletResponse.sendRedirect(LocalServerReceiver.access$000(this.this$0));
            }
            else if (this.this$0.error != null && LocalServerReceiver.access$100(this.this$0) != null) {
                httpServletResponse.sendRedirect(LocalServerReceiver.access$100(this.this$0));
            }
            else {
                this.writeLandingHtml(httpServletResponse);
            }
            httpServletResponse.flushBuffer();
        }
        finally {
            this.this$0.waitUnlessSignaled.release();
        }
    }
    
    private void writeLandingHtml(final HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("text/html");
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>");
        writer.println("<head><title>OAuth 2.0 Authentication Token Received</title></head>");
        writer.println("<body>");
        writer.println("Received verification code. You may now close this window.");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }
}
