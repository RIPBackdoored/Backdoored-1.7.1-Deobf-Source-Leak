package r.k.d.m.h.p;

import r.k.h;
import r.k.n.i;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Iterator;
import r.k.d.m.g;
import r.k.d.m.o;
import r.k.r.n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import javax.swing.JScrollBar;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import java.awt.Dimension;
import net.minecraftforge.common.MinecraftForge;
import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class s
{
    private JFrame xh;
    private JTextArea kd;
    private JTextField ks;
    private JTextArea kl;
    private JPanel ky;
    private JSplitPane kx;
    private JPanel kk;
    private JPanel kq;
    private JScrollPane kv;
    public static final boolean kj;
    public static final int ke;
    public static final boolean ko;
    
    public s() {
        super();
        this.w();
        this.xh = new JFrame("SwingImpl");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.xh.setSize(screenSize.width / 2, screenSize.height / 2);
        this.xh.setContentPane(this.ky);
        this.xh.pack();
        this.xh.setVisible(true);
        this.ks.addActionListener(this::l);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void d(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (x.tb.yv()) {
            this.kd.append(clientChatReceivedEvent.getMessage().getUnformattedText() + "\n");
        }
        final JScrollBar verticalScrollBar = this.kv.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }
    
    @SubscribeEvent
    public void d(final n n) {
        this.kd.append(n.iTextComponent.getUnformattedText() + "\n");
        final JScrollBar scrollBar;
        scrollBar.setValue(scrollBar.getMaximum());
    }
    
    public void f() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<g> iterator = o.lc().iterator();
        if (iterator.hasNext()) {
            final g g = iterator.next();
            if (g.lo()) {
                sb.append(g.cm).append("\n");
            }
        }
        this.kl.setText(sb.toString());
    }
    
    public void b() {
        this.xh.setVisible(false);
        this.xh.dispose();
    }
    
    private void w() {
        (this.ky = new JPanel()).setLayout(new BorderLayout(0, 0));
        this.ky.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        this.kx = new JSplitPane();
        this.ky.add(this.kx, "Center");
        (this.kk = new JPanel()).setLayout(new BorderLayout(0, 0));
        this.kx.setLeftComponent(this.kk);
        this.ks = new JTextField();
        this.kk.add(this.ks, "South");
        (this.kv = new JScrollPane()).setHorizontalScrollBarPolicy(31);
        this.kv.setVerticalScrollBarPolicy(20);
        (this.kd = new JTextArea()).setLineWrap(true);
        this.kd.setText("");
        this.kv.setViewportView(this.kd);
        (this.kq = new JPanel()).setLayout(new BorderLayout(0, 0));
        this.kx.setRightComponent(this.kq);
        this.kl = new JTextArea();
        this.kq.add(this.kl, "Center");
    }
    
    public JComponent g() {
        return this.ky;
    }
    
    private /* synthetic */ void l(final ActionEvent actionEvent) {
        if (this.ks.getText().startsWith(i.re)) {}
        h.mc.player.sendChatMessage(this.ks.getText());
        this.ks.setText("");
    }
}
