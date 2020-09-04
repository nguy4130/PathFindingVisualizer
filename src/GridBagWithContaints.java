import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridBagWithContaints {

  public static void main(final String[] args) {
    final JFrame f = new JFrame("Demonstrates the use of gridx, gridy,ipadx, ipady and insets constraints");
    final JPanel p = new JPanel();

    p.setLayout(new GridBagLayout());
    // creates a constraints object
    final GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2, 2, 2, 2); // insets for all components
    c.gridx = 0; // column 0
    c.gridy = 0; // row 0
    c.ipadx = 5; // increases components width by 10 pixels
    c.ipady = 5; // increases components height by 10 pixels
    p.add(new JButton("Java"), c); // constraints passed in
    c.gridx = 1; // column 1
    // c.gridy = 0; // comment out this for reusing the obj
    c.ipadx = 0; // resets the pad to 0
    c.ipady = 0;
    p.add(new JButton("Source"), c);
    c.gridx = 0; // column 0
    c.gridy = 1; // row 1
    p.add(new JButton("and"), c);
    c.gridx = 1; // column 1
    p.add(new JButton("Support."), c);

    // final WindowListener wndCloser = new WindowAdapter() {
    //   public void windowClosing(final WindowEvent e) {
    //     System.exit(0);
    //   }
    // };
    // f.addWindowListener(wndCloser);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(p);
    f.setSize(600, 200);
    f.setVisible(true);
  }
}