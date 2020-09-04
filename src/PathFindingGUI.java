import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.logging.*;

public class PathFindingGUI extends JFrame{
  private JTextField tfCount; // Use Swing's JTextField instead of AWT's TextField
  private JButton btnCount; // Using Swing's JButton instead of AWT's Button
  private int count = 0;

  // Constructor to setup the GUI components and event handlers
  public PathFindingGUI() { 
    
    /** Top Panel */
    JPanel topPanel = new JPanel();
    topPanel.add(new JButton("Set Start"));
    topPanel.add(new JButton("Set Wall"));
    topPanel.add(new JButton("Set End"));
    topPanel.add(new JLabel("Choose Algorithm"));
    String[] algorithms = {"------", "Dijkstra's Search", "A* Search", "Modified A* Search", "Greedy Best-First Search"};
    JComboBox algo = new JComboBox(algorithms);
    algo.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		JComboBox<String> combo = (JComboBox<String>) e.getSource();
    		String selectedAlgorithm = (String) combo.getSelectedItem();
    		System.out.println("Choosing " + selectedAlgorithm);
    	}
    });
    topPanel.add(algo);
    JTextField gridSize = new JTextField("Enter grid size");
    JButton enterSize = new JButton("Enter");
    enterSize.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent e){
        try {
          int gridDimension = Integer.parseInt(gridSize.getText());
          System.out.println(gridDimension);
        } catch (Exception exception) {
          //TODO: handle exception
          System.out.println(exception.toString());
        }
      }
    });
    topPanel.add(gridSize);
    topPanel.add(enterSize);
    /** Set general layout */
    setLayout(new BorderLayout()); // The content-pane sets its layout
    setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program if close-window button clicked
    setTitle("PathFinding Visualizer"); // "super" JFrame sets title
    setSize(1000, 1000); // "super" JFrame sets initial size
    setResizable(false);
    setVisible(true); // "super" JFrame shows
    add(topPanel, BorderLayout.PAGE_START);
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