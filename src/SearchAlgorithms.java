import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.*;

public class SearchAlgorithms {
  private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  /** Dijkstra's Search */
  public static List<NodeDijkstra> dijkstra(int[][] map) {
    int size = map.length;
    NodeDijkstra start_node = null;
    NodeDijkstra end_node = null;
    NodeDijkstra[][] grid = new NodeDijkstra[size][size];
    for(int i = 0; i < map.length; i++){
      for (int j = 0; j < map[i].length; j++) {
        grid[i][j] = new NodeDijkstra(i, j);
        if(map[i][j] == 1){
          grid[i][j].blocked = true;
        }
        else {
          grid[i][j].blocked = false;
          if(map[i][j] == 2) {
            start_node = grid[i][j];
          }
          else if(map[i][j] == 3) {
            end_node = grid[i][j];
          }
        }
      }
    }
    start_node.distance = 0;
    PriorityQueue<NodeDijkstra> q = new PriorityQueue<>(10, new NodeDijkstraComparator());
    q.add(start_node);
    while(!q.isEmpty()) {
      NodeDijkstra current = q.poll();
      // Move up
      if((current.position[0] - 1) >= 0){
        NodeDijkstra temp = grid[current.position[0]-1][current.position[1]];
        if(!temp.visited && !temp.blocked && (temp.distance > (current.distance+1))) {
          temp.distance = current.distance + 1;
          temp.parent = current;
          q.add(temp);
        }
      }
      // Move left
      if((current.position[1] - 1) >= 0) {
        NodeDijkstra temp = grid[current.position[0]][current.position[1]-1];
        if(!temp.visited && !temp.blocked && (temp.distance > (current.distance+1))){
          temp.distance = current.distance + 1;
          temp.parent = current;
          q.add(temp);
        }
      }
      // Move right
      if((current.position[1] + 1) < size) {
        NodeDijkstra temp = grid[current.position[0]][current.position[1]+1];
        if(!temp.visited && !temp.blocked && (temp.distance > (current.distance+1))){
          temp.distance = current.distance + 1;
          temp.parent = current;
          q.add(temp);
        }
      }
      // Move down
      if((current.position[0] + 1) < size) {
        NodeDijkstra temp = grid[current.position[0]+1][current.position[1]];
        if(!temp.visited && !temp.blocked && (temp.distance > (current.distance+1))){
          temp.distance = current.distance + 1;
          temp.parent = current;
          q.add(temp);
        }
      }
      current.visited = true;
    }

    List<NodeDijkstra> path = new ArrayList<>();
    if(grid[end_node.position[0]][end_node.position[1]].distance != Integer.MAX_VALUE){
      NodeDijkstra current = grid[end_node.position[0]][end_node.position[1]];
      while(current.parent != null) {
        path.add(0, current.parent);
        current = current.parent;
      }
    }
    else{
      return null;
    }
    path.add(end_node);
    for(int i = 0; i < path.size()-1; i++){
      System.out.print(path.get(i).toString()+" -> ");
    }
    System.out.println(path.get(path.size()-1).toString());
    return path;
  }

  /** A* Search */


  private static List<NodeAStar> pathAStar = new ArrayList<>();
  private static boolean validNodeAStar(int[] position, int mapSize) {
    return (position[0] >= 0) && (position[0] < mapSize) && (position[1] >= 0) && (position[1] < mapSize);
  }

  private static void backtrackAStar(NodeAStar current, NodeAStar start, NodeAStar goal, int[][] map){
    if(current.parent != null){
      backtrackAStar(current.parent, start, goal, map);
    }
    if(!current.match(goal) && !current.match(start)){
      if(map != null){
        map[current.position[0]][current.position[1]] = 2;
      }
    }
    pathAStar.add(current);
  }

  private static void findWayAStar(int[][] map, int size, int[] start, int[] goal) {
    NodeAStar goalNode = new NodeAStar(goal, null, null);
    NodeAStar startNode = new NodeAStar(start, null, goalNode);
    NodeAStar S = new NodeAStar(start, null, goalNode);

    if(!validNodeAStar(start, size) || (!validNodeAStar(goal, size))){
      LOGGER.log(Level.SEVERE,"Invalid starting and ending point");
      return;
    }

    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    NodeAStar[][] matrixP = new NodeAStar[size][size];
    for(int i = 0; i < matrixP.length; i++){
      for(int j = 0; j < matrixP[0].length; j++){
        matrixP[i][j] = null;
      }
    }
    matrixP[S.position[0]][S.position[1]] = S;
    PriorityQueue<NodeAStar> q = new PriorityQueue<>(10, new NodeAStarComparator());
    q.add(S);
    while(!q.isEmpty()){
      S = q.remove();
      if(S.match(goalNode)){
        break;
      }
      for(int i = 0; i < dx.length; i++){
        int x = S.position[0] + dx[i];
        int y = S.position[1] + dy[i];
        if(validNodeAStar(new int[]{x,y}, size)){
          if((matrixP[x][y] == null) && (map[x][y] != 1)) {
            NodeAStar temp = new NodeAStar(new int[]{x, y}, S, goalNode);
            matrixP[temp.position[0]][temp.position[1]] = temp;
            q.add(temp);
            if (temp.match(goalNode)) {
              break;
            }
          }
          else{
            if((matrixP[x][y] != null) && (map[x][y] != 1)){
              NodeAStar temp = matrixP[x][y];
              if(temp.g >= 1 + S.g){
                matrixP[x][y].update(S, goalNode);
                q.add(matrixP[x][y]);
                if(temp.match(goalNode)){
                  break;
                }
              }
            }
          }
        }
      }
    }
    if(S.match(goalNode)){
      backtrackAStar(S, startNode, goalNode, null);
    }
  }

  public static List<NodeAStar> aStar(int[][] map){
    int[] start = new int[2];
    int[] end = new int[2];
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[i].length; j++){
        if(map[i][j] == 2){
          start[0] = i;
          start[1] = j;
        }
        else if(map[i][j] == 3){
          end[0] = i;
          end[1] = j;
        }
      }
    }
//    System.out.println(end[0] + "," + end[1]);
    pathAStar.clear();
    findWayAStar(map, map.length, start, end);
    for(int i = 0; i < pathAStar.size()-1; i++){
      System.out.print(pathAStar.get(i).toString()+" -> ");
    }
    System.out.println(pathAStar.get(pathAStar.size()-1).toString());
    return pathAStar;
  }
}
