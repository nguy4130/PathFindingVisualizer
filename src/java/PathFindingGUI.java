package java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PathFindingGUI extends JFrame {
  private JTextField tfCount; // Use Swing's JTextField instead of AWT's TextField
  private JButton btnCount; // Using Swing's JButton instead of AWT's Button
  private int count = 0;

  // Constructor to setup the GUI components and event handlers
  public PathFindingGUI() {
    // Retrieve the content-pane of the top-level container JFrame
    // All operations done on the content-pane
    final Container cp = getContentPane();
    cp.setLayout(new FlowLayout()); // The content-pane sets its layout

    cp.add(new JLabel("Pathfinding Visualizer"));
    tfCount = new JTextField("0");
    tfCount.setEditable(false);
    cp.add(tfCount);

    btnCount = new JButton("Count");
    cp.add(btnCount);

    // Allocate an anonymous instance of an anonymous inner class that
    // implements ActionListener as ActionEvent listener
    btnCount.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent evt) {
        ++count;
        tfCount.setText(count + "");
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program if close-window button clicked
    setTitle("Swing Counter"); // "super" JFrame sets title
    setSize(300, 100); // "super" JFrame sets initial size
    setVisible(true); // "super" JFrame shows
  }

  // The entry main() method
  public static void main(final String[] args) {
    // Run the GUI construction in the Event-Dispatching thread for thread-safety
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new PathFindingGUI(); // Let the constructor do the job
      }
    });
  }
}