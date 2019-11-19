package r.k.d.m.p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class p$x implements ActionListener
{
    int nl;
    final /* synthetic */ int ny;
    final /* synthetic */ String[] nx;
    final /* synthetic */ p nk;
    public static final int nq;
    public static final boolean nv;
    
    p$x(final p p3, final int ny, final String[] nx) {
        this.ny = ny;
        this.nx = nx;
        super();
        this.nl = 0;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.nl < this.ny) {
            p.i().player.sendChatMessage(this.nx[this.nl]);
            ++this.nl;
            if (this.nl == this.ny) {
                this.nl = 0;
            }
        }
    }
}
