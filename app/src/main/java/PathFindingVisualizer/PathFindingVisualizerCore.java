package PathFindingVisualizer;

import java.util.List;

public class PathFindingVisualizerCore {
  
  private PathFindingVisualizerGUI gui;

  public PathFindingVisualizerCore(PathFindingVisualizerGUI gui) {
    this.gui = gui;
  } 

  public void search(String algorithm, int[][] grid) {
    if(algorithm.equals("Dijkstra's Search")){
      List<NodeDijkstra> pathDijkstra = SearchAlgorithms.dijkstra(grid, gui);
      // gui.updateResult(pathDijkstra);
    }
  }
}
