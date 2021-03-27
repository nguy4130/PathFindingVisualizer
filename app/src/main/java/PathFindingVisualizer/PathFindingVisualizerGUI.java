package PathFindingVisualizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PathFindingVisualizerGUI implements ActionListener, MouseListener {
  
  private static final int APPLICATION_SIZE = 1000;
  private static final int GRID_SIZE = 1000;
  private static final String[] algorithms = {
    "------", 
    "Dijkstra's Search", 
    "A* Search", 
    "Modified A* Search",
    "Greedy Best-First Search"
  };

  private static final int DEFAULT_MODE = -1;
  private static final int WALL_MODE = 0;
  private static final int START_MODE = 1;
  private static final int END_MODE = 2;

  private static final Color WALL_COLOR = Color.BLACK;
  private static final Color START_COLOR = Color.RED;
  private static final Color END_COLOR = Color.BLUE;

  private int gridSize;
  private int mode;

  private boolean startIsSet;
  private boolean endIsSet;

  private PathFindingVisualizerCore backend;

  private JFrame frame;

  /** Top Panel components */
  private JButton setStartButton;
  private JButton setWallButton;
  private JButton setEndButton;
  private JComboBox<String> algoComboBox;
  private JTextField gridSizeText;
  private JButton enterSizeButton;
  private JButton renderButton;
  private JButton searchButton;

  /** Grid Components */
  private JPanel gridPanel;
  private List<List<JPanel>> grid;

  public PathFindingVisualizerGUI() {
    
    /** Set General Frame */
    frame = new JFrame("Pathfinding Visualizer");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(APPLICATION_SIZE, APPLICATION_SIZE));
    frame.setSize(APPLICATION_SIZE, APPLICATION_SIZE + 100);
    frame.setResizable(true);

    /** Top Panel */
    JPanel topPanel = new JPanel();
    
    setStartButton = new JButton("Set Start");
    setStartButton.addActionListener(this);
    topPanel.add(setStartButton);

    setWallButton = new JButton("Set Wall");
    setWallButton.addActionListener(this);
    topPanel.add(setWallButton);

    setEndButton = new JButton("Set End");
    setEndButton.addActionListener(this);
    topPanel.add(setEndButton);

    JLabel chooseAlgoLabel = new JLabel("Choose Algorithm");
    topPanel.add(chooseAlgoLabel);

    algoComboBox = new JComboBox<>(algorithms);
    algoComboBox.addActionListener(this);
    topPanel.add(algoComboBox);

    gridSizeText = new JTextField("Enter grid size");
    gridSizeText.setSize(5, 10);
    gridSizeText.addActionListener(this);
    topPanel.add(gridSizeText);

    enterSizeButton = new JButton("Enter");
    enterSizeButton.addActionListener(this);
    topPanel.add(enterSizeButton);

    renderButton = new JButton("Render");
    renderButton.addActionListener(this);
    topPanel.add(renderButton);

    searchButton = new JButton("Search");
    searchButton.addActionListener(this);
    topPanel.add(searchButton);

    frame.add(topPanel, BorderLayout.NORTH);
    frame.setVisible(true);

    grid = new ArrayList<>();
    gridPanel = new JPanel();
    frame.add(gridPanel, BorderLayout.CENTER);
    frame.pack();
    mode = DEFAULT_MODE;
    startIsSet = false;
    endIsSet = false;
    gridSize = 0;
    
    backend = new PathFindingVisualizerCore(this);
  }

  public void updateGrid() {
    removeAllNestedList(grid);
    gridPanel.removeAll();
    gridPanel.setLayout(new GridLayout(gridSize, gridSize));
    gridPanel.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
    for(int row = 0; row < gridSize; row++){
      List<JPanel> rowPanels = new ArrayList<>();
      for(int col = 0; col < gridSize; col++){
        JPanel tile = new JPanel();
        tile.setBackground(Color.WHITE);
        tile.setSize(APPLICATION_SIZE / gridSize, APPLICATION_SIZE / gridSize);
        tile.addMouseListener(this);
        tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rowPanels.add(tile);
        gridPanel.add(tile);
      }
      grid.add(rowPanels);
    }
    frame.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object eventSource = e.getSource();
    if(eventSource == setStartButton){
      mode = (mode != START_MODE) ? START_MODE : DEFAULT_MODE;
    } else if(eventSource == setWallButton){
      mode = (mode != WALL_MODE) ? WALL_MODE : DEFAULT_MODE;
    } else if(eventSource == setEndButton){
      mode = (mode != END_MODE) ? END_MODE : DEFAULT_MODE;
    } else if(eventSource == algoComboBox){
      String selectedAlgorithm = (String) algoComboBox.getSelectedItem();
      if(!selectedAlgorithm.startsWith("-")){
        PathFindingVisualizerUtils.LOGGER.log(Level.INFO, "Choosing {0}", selectedAlgorithm);
      }
    } else if(eventSource == enterSizeButton) {
      try{
        gridSize = Integer.parseInt(gridSizeText.getText());
        PathFindingVisualizerUtils.LOGGER.log(Level.INFO, "Setting grid to {0} by {0}", gridSize+"");
        updateGrid();
      } catch (NumberFormatException exception){
        PathFindingVisualizerUtils.LOGGER.log(Level.WARNING, "Invalid grid size: {0}", exception.toString());
      }
    } else if(eventSource == renderButton) {
      renderGrid();
    } else if(eventSource == searchButton) {
      int[][] bitmap = renderGrid();
      String selectedAlgorithm = (String) algoComboBox.getSelectedItem();
      if(startIsSet && endIsSet && !selectedAlgorithm.startsWith("-")){
        backend.search(selectedAlgorithm, bitmap);
      }
    }
  
  }

  private void removeAllNestedList(List<List<JPanel>> list) throws IndexOutOfBoundsException, UnsupportedOperationException{
    for(int row = 0; row < list.size(); row++){
      for(int col = 0; col < list.get(row).size(); col++){
        list.get(row).remove(col);
      }
      list.remove(row);
    }
  }

  private int[][] renderGrid() {
    int[][] res = new int[gridSize][gridSize];
    String s = "";
    for(int i = 0; i < gridSize; i++){
      for(int j = 0; j < gridSize; j++){
        if(grid.get(i).get(j).getBackground() == WALL_COLOR){
          res[i][j] = 1;
          s += "1 ";
        } else if(grid.get(i).get(j).getBackground() == START_COLOR){
          res[i][j] = 2;
          s += "2 ";
        } else if(grid.get(i).get(j).getBackground() == END_COLOR){
          res[i][j] = 3;
          s += "3 ";
        } else {
          res[i][j] = 0;
          s += "0 ";
        }
      }
      s += System.lineSeparator();
    }
    System.out.println(s);
    return res;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    JPanel tile = (JPanel) e.getSource();
    if (tile.getBackground() == Color.WHITE) {
      switch(mode){
        case WALL_MODE: tile.setBackground(WALL_COLOR); break;
        case START_MODE: 
          if(!startIsSet) {
            tile.setBackground(START_COLOR); 
            startIsSet = true;
          } 
          break;
        case END_MODE: 
          if(!endIsSet) {
            tile.setBackground(END_COLOR);
            endIsSet = true;  
          }
          break;
        default: tile.setBackground(Color.WHITE); break;
      }
    } else if(tile.getBackground() == START_COLOR){
      startIsSet = false;
      tile.setBackground(Color.WHITE);
    } else if(tile.getBackground() == END_COLOR){
      endIsSet = false;
      tile.setBackground(Color.WHITE);
    }
    else {
      tile.setBackground(Color.WHITE);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
}
