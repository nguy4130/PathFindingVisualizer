import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NodeDijkstra extends Node{
  NodeDijkstra parent;
  Boolean visited;
  Boolean blocked;
  int[] position;
  int distance;

  public NodeDijkstra(int[] position) {
    this.position = new int[2];
    this.position[0] = position[0];
    this.position[1] = position[1];
    this.distance = Integer.MAX_VALUE;
    this.parent = null;
    this.visited = false;
    this.blocked = false;
  }

  public NodeDijkstra(int i, int j) {
    this.position = new int[2];
    this.position[0] = i;
    this.position[1] = j;
    this.distance = Integer.MAX_VALUE;
    this.parent = null;
    this.visited = false;
    this.blocked = false;
  }

  public String toString() {
    return "(" + this.position[0] + " , " + this.position[1] + ")";
  }
  
  public Boolean closerDistance(NodeDijkstra other){
    return this.distance < other.distance;
  }

  public List<NodeDijkstra> dijkstra(int[][] map, int[] start, int[] end) {
    int size = map.length;
//    NodeDijkstra start_node = new NodeDijkstra(start);
//    NodeDijkstra end_node = new NodeDijkstra(end);
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
    PriorityQueue<NodeDijkstra> q = new PriorityQueue<>();
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
    return path;
  }
}
