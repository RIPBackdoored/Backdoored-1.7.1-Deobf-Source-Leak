package r.k.c;

import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JPanel;
import java.util.function.Consumer;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class k extends JFrame implements ActionListener
{
    private JTextField sra;
    private JFrame srm;
    private JButton srh;
    private JLabel sfd;
    private Consumer<String> sfs;
    private String sfl;
    public static final int sfy;
    public static final boolean sfx;
    
    public k(final String s, final String s2, final String sfl, final Consumer<String> sfs) {
        super();
        this.sfs = sfs;
        this.sfl = sfl;
        this.srm = new JFrame(s);
        this.sfd = new JLabel(s2);
        (this.srh = new JButton(sfl)).addActionListener(this);
        this.sra = new JTextField(16);
        final JPanel panel = new JPanel();
        panel.add(this.sra);
        panel.add(this.srh);
        panel.add(this.sfd);
        this.srm.add(panel);
        this.srm.setSize(300, 300);
        this.srm.setVisible(true);
        this.srm.setAlwaysOnTop(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(this.sfl)) {
            this.sfs.accept(this.sra.getText());
            this.srm.dispatchEvent(new WindowEvent(this.srm, 201));
        }
    }
}
