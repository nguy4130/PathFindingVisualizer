import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class PathFindingGUI extends JFrame {
  private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
  private final int count = 0;
  private int gridDimension = 1;
  private final int APPLICATION_SIZE = 1000;
  private final int GRID_SIZE = 1000;

  // Constructor to setup the GUI components and event handlers
  public PathFindingGUI() {

    /** Top Panel */
    final JPanel topPanel = new JPanel();
    topPanel.add(new JButton("Set Start"));
    topPanel.add(new JButton("Set Wall"));
    topPanel.add(new JButton("Set End"));
    topPanel.add(new JLabel("Choose Algorithm"));
    final String[] algorithms = { "------", "Dijkstra's Search", "A* Search", "Modified A* Search",
        "Greedy Best-First Search" };
    final JComboBox algo = new JComboBox(algorithms);
    algo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final JComboBox<String> combo = (JComboBox<String>) e.getSource();
        final String selectedAlgorithm = (String) combo.getSelectedItem();
        LOGGER.log(Level.INFO, "Choosing " + selectedAlgorithm);
      }
    });
    topPanel.add(algo);
    final JTextField gridSize = new JTextField("Enter grid size");
    gridSize.setSize(5, 10);
    final JButton enterSize = new JButton("Enter");
    final JPanel pane = new JPanel();
    final List<List<JPanel>> grid = new ArrayList<>();
    List<List<Integer>> bitMap = new ArrayList<>();
    enterSize.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          gridDimension = Integer.parseInt(gridSize.getText());
          LOGGER.log(Level.INFO, gridSize.getText());
          /** Grid section */
          removeAllNestedList(grid);
          pane.removeAll();
          pane.setLayout(new GridLayout(gridDimension, gridDimension));
          pane.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
          for (int row = 0; row < gridDimension; row++) {
            List<JPanel> rowPanel = new ArrayList<>();
            for (int col = 0; col < gridDimension; col++) {
              final JPanel tile = new JPanel();
              tile.setBackground(Color.WHITE);
              tile.setSize(APPLICATION_SIZE / gridDimension, APPLICATION_SIZE / gridDimension);
              tile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                  if (tile.getBackground() == Color.WHITE) {
                    tile.setBackground(Color.BLACK);
                  } else {
                    tile.setBackground(Color.WHITE);
                  }
                }
              });
              tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              rowPanel.add(tile);
              pane.add(tile);
            }
            grid.add(rowPanel);
          }
          pack();
        } catch (final Exception exception) {
          // TODO: handle exception
          LOGGER.log(Level.WARNING, exception.toString());
        }
      }
    });
    final JButton render = new JButton("Render");
    render.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          renderMap(grid);
        } catch (final Exception exception) {
          LOGGER.log(Level.SEVERE, exception.toString());
        }
      }
    });
    topPanel.add(gridSize);
    topPanel.add(enterSize);
    topPanel.add(render);

    /** Set general layout */
    setLayout(new BorderLayout()); // The content-pane sets its layout
    setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program if close-window button clicked
    setTitle("PathFinding Visualizer"); // "super" JFrame sets title
    setSize(APPLICATION_SIZE, APPLICATION_SIZE + 100); // "super" JFrame sets initial size
    setResizable(false);
    setVisible(true); // "super" JFrame shows
    add(topPanel, BorderLayout.NORTH);
    add(pane, BorderLayout.CENTER);
    // add(pane, BorderLayout.CENTER);
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

  public int[][] renderMap(final List<List<JPanel>> grid) {
    final int[][] map = new int[grid.size()][grid.get(0).size()];
    for(int row = 0; row < grid.size(); row++){
      for(int col = 0; col < grid.get(row).size(); col++){
        if(grid.get(row).get(col).getBackground() == Color.WHITE){
          map[row][col] = 0;
        }
        else {
          map[row][col] = 1;
        }
        System.out.print(map[row][col] + " ");
      }
      System.out.println();
    }
    System.out.println();
    return map;
  }

  public void removeAllNestedList(List<List<JPanel>> list){
    for(int row = 0; row < list.size(); row++){
      for(int col = 0; col < list.get(row).size(); col++){
        list.get(row).remove(col);
      }
      list.remove(row);
    }
  }
}