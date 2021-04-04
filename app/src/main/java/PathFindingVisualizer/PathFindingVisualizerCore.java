package PathFindingVisualizer;


public class PathFindingVisualizerCore {
  
  private PathFindingVisualizerGUI gui;

  public PathFindingVisualizerCore(PathFindingVisualizerGUI gui) {
    this.gui = gui;
  } 

  public void search(String algorithm, int[][] grid) {
    if(algorithm.equals("Dijkstra's Search")){
      SearchAlgorithms.dijkstra(grid, gui);
    } else if(algorithm.equals("A* Search")){
      SearchAlgorithms.aStarSearch(grid, gui);
    } else if(algorithm.equals("Breadth First Search")){
      SearchAlgorithms.bfs(grid, gui);
    } else if(algorithm.equals("Depth First Search")){
      SearchAlgorithms.dfs(grid, gui);
    } else if(algorithm.equals("Greedy Best-First Search")){
      // SearchAlgorithms.greedy(grid, gui);
    }
  }
}