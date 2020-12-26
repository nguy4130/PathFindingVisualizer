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
  private static final Color START_COLOR = Color.RED;
  private static final Color WALL_COLOR = Color.BLACK;
  private static final Color END_COLOR = Color.BLUE;
  private static final int START_MODE = 0;
  private static final int WALL_MODE = 1;
  private static final int END_MODE = 2;
  private int mode = -1;
  // Constructor to setup the GUI components and event handlers
  public PathFindingGUI() {

    /** Top Panel */
    final JPanel topPanel = new JPanel();
    final JButton setStartButton = new JButton("Set Start");
    setStartButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(mode != START_MODE){
          mode = START_MODE;
        }
        else{
          mode = -1;
        }
      }
    });

    final JButton setWallButton = new JButton("Set Wall");
    setWallButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(mode != WALL_MODE){
          mode = WALL_MODE;
        }
        else{
          mode = -1;
        }
      }
    });

    final JButton setEndButton = new JButton("Set End");
    setEndButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(mode != END_MODE){
          mode = END_MODE;
        }
        else{
          mode = -1;
        }
      }
    });

    final JLabel chooseAlgoLabel = new JLabel("Choose Algorithm");
    final String[] algorithms = { "------", "Dijkstra's Search", "A* Search", "Modified A* Search",
            "Greedy Best-First Search" };
    final JComboBox algoList = new JComboBox(algorithms);
    algoList.addActionListener(e -> {
      final JComboBox<String> combo = (JComboBox<String>) e.getSource();
      final String selectedAlgorithm = (String) combo.getSelectedItem();
      LOGGER.log(Level.INFO, "Choosing " + selectedAlgorithm);
    });
    JTextField gridSizeText = new JTextField("Enter grid size");
    gridSizeText.setSize(5, 10);
    final JButton enterSize = new JButton("Enter");
    final JPanel pane = new JPanel();
    final List<List<JPanel>> grid = new ArrayList<>();
    List<List<Integer>> bitMap = new ArrayList<>();
    enterSize.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          gridDimension = Integer.parseInt(gridSizeText.getText());
          LOGGER.log(Level.INFO, gridSizeText.getText());
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
                    if(mode == WALL_MODE){
                      tile.setBackground(WALL_COLOR);
                    }
                    else if(mode == START_MODE) {
                      tile.setBackground(START_COLOR);
                    }
                    else if(mode == END_MODE) {
                      tile.setBackground(END_COLOR);
                    }
                    else{
                      tile.setBackground(Color.WHITE);
                    }
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
    JButton renderButton = new JButton("Render");
    renderButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        try {
          renderMap(grid);
        } catch (final Exception exception) {
          LOGGER.log(Level.SEVERE, exception.toString());
        }
      }
    });

    topPanel.add(setStartButton);
    topPanel.add(setWallButton);
    topPanel.add(setEndButton);
    topPanel.add(chooseAlgoLabel);
    topPanel.add(algoList);
    topPanel.add(gridSizeText);
    topPanel.add(enterSize);
    topPanel.add(renderButton);

    /** Set general layout */
    setLayout(new BorderLayout()); // The content-pane sets its layout
    setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program if close-window button clicked
    setTitle("PathFinding Visualizer"); // "super" JFrame sets title
    setSize(APPLICATION_SIZE, APPLICATION_SIZE + 100); // "super" JFrame sets initial size
//    setResizable(false);
    setVisible(true); // "super" JFrame shows
    add(topPanel, BorderLayout.NORTH);
    add(pane, BorderLayout.CENTER);
    // add(pane, BorderLayout.CENTER);
  }

  // The entry main() method
  public static void main(final String[] args) {
    // Run the GUI construction in the Event-Dispatching thread for thread-safety
    SwingUtilities.invokeLater(() -> {
      new PathFindingGUI(); // Let the constructor do the job
    });
  }

  public int[][] renderMap(final List<List<JPanel>> grid) {
    final int[][] map = new int[grid.size()][grid.get(0).size()];
    for(int row = 0; row < grid.size(); row++){
      for(int col = 0; col < grid.get(row).size(); col++){
        if(grid.get(row).get(col).getBackground() == Color.WHITE){
          map[row][col] = 0;
        }
        else if(grid.get(row).get(col).getBackground() == START_COLOR){
          map[row][col] = 2;
        }
        else if(grid.get(row).get(col).getBackground() == WALL_COLOR){
          map[row][col] = 1;
        }
        else if(grid.get(row).get(col).getBackground() == END_COLOR){
          map[row][col] = 3;
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