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
      SearchAlgorithms.astar(grid, gui);
    } else if(algorithm.equals("Breadth First Search")){
      SearchAlgorithms.bfs(grid, gui);
    } else if(algorithm.equals("Depth First Search")){
      SearchAlgorithms.dfs(grid, gui);
    }
  }
}

// int size = map.length;
//     NodeDijkstra startNode = null;
//     NodeDijkstra endNode = null;
//     NodeDijkstra[][] grid = new NodeDijkstra[size][size];
//     for(int i = 0; i < map.length; i++){
//       for (int j = 0; j < map[i].length; j++) {
//         grid[i][j] = new NodeDijkstra(i, j);
//         if(map[i][j] == 1){
//           grid[i][j].setBlocked(true);
//         }
//         else {
//           grid[i][j].setBlocked(false);
//           if(map[i][j] == 2) {
//             startNode = grid[i][j];
//           }
//           else if(map[i][j] == 3) {
//             endNode = grid[i][j];
//           }
//         }
//       }
//     }

//     if(startNode == null || endNode == null) {
//       return new ArrayList<>();
//     }

//     startNode.setDistance(0);
//     PriorityQueue<NodeDijkstra> q = new PriorityQueue<>(10, new NodeDijkstraComparator());
//     q.add(startNode);
//     while(!q.isEmpty()) {
//       NodeDijkstra current = q.poll();
//       // Move up
//       if((current.getPosition()[0] - 1) >= 0){
//         NodeDijkstra temp = grid[current.getPosition()[0]-1][current.getPosition()[1]];
//         if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))) {
//           temp.setDistance(current.getDistance() + 1);
//           temp.setParent(current);
//           q.add(temp);
//         }
//       }
//       // Move left
//       if((current.getPosition()[1] - 1) >= 0) {
//         NodeDijkstra temp = grid[current.getPosition()[0]][current.getPosition()[1]-1];
//         if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
//           temp.setDistance(current.getDistance() + 1);
//           temp.setParent(current);
//           q.add(temp);
//         }
//       }
//       // Move right
//       if((current.getPosition()[1] + 1) < size) {
//         NodeDijkstra temp = grid[current.getPosition()[0]][current.getPosition()[1]+1];
//         if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
//           temp.setDistance(current.getDistance() + 1);
//           temp.setParent(current);
//           q.add(temp);
//         }
//       }
//       // Move down
//       if((current.getPosition()[0] + 1) < size) {
//         NodeDijkstra temp = grid[current.getPosition()[0]+1][current.getPosition()[1]];
//         if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
//           temp.setDistance(current.getDistance() + 1);
//           temp.setParent(current);
//           q.add(temp);
//         }
//       }
//       current.setVisited(true);
//     }

//     List<NodeDijkstra> path = new ArrayList<>();
//     if(grid[endNode.getPosition()[0]][endNode.getPosition()[1]].getDistance() != Integer.MAX_VALUE){
//       NodeDijkstra current = grid[endNode.getPosition()[0]][endNode.getPosition()[1]];
//       while(current.getParent() != null) {
//         path.add(0, current.getParent());
//         current = current.getParent();
//       }
//     }
//     else{
//       return new ArrayList<>();
//     }
//     path.add(endNode);
//     for(int i = 0; i < path.size()-1; i++){
      
//       // try {
//         gui.updateTileColor(path.get(i).getPosition(), PathFindingVisualizerGUI.RESULT_COLOR);
//         System.out.print(path.get(i).toString()+" -> ");
//         // Thread.sleep(500);
//       // } catch (InterruptedException e) {
//       //   e.printStackTrace();
//       // }
//     }
//     System.out.println(path.get(path.size()-1).toString());
//     return path;