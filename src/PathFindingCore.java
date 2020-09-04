import java.util.*;

public class PathFindingCore {
  static int[][] map1 = {
    {0, 0, 0, 0},
    {0, 1, 1, 0},
    {0, 0, 1, 0},
    {0, 1, 1, 0}
  };
  public static void main(String[] args){
    int[] start = {3, 0};
    int[] end = {3, 3};
    List<NodeDijkstra> path = dijkstra(map1, start, end);
    for(int i = path.size() -1; i >= 0; i--){
      System.out.println(path.get(i).toString());
    }
  }
  /** Dijkstra's Search */
  public static List<NodeDijkstra> dijkstra(int[][] map, int[] start, int[] end) {
    int size = map.length;
    NodeDijkstra startNode = new NodeDijkstra(start);
    NodeDijkstra endNode = new NodeDijkstra(end);
    NodeDijkstra[][] grid = new NodeDijkstra[size][size];
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        int[] position = new int[2];
        position[0] = i;
        position[1] = j;
        grid[i][j] = new NodeDijkstra(position);
        if(map[i][j] == 1){
          grid[i][j].blocked = true;
        }
        else{
          grid[i][j].blocked = false;
        }
      }
    }
    startNode.distance = 0;
    Queue<NodeDijkstra> q = new LinkedList<>();
    q.offer(startNode);
    while(!q.isEmpty()){
      NodeDijkstra currentNode = q.poll();
      /** Move up */
      if ((currentNode.position[0] - 1) >= 0){
        NodeDijkstra tempNode = grid[currentNode.position[0]-1][currentNode.position[1]];
        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
          tempNode.distance = currentNode.distance + 1;
          tempNode.parent = currentNode;
          q.offer(tempNode);
        }
      }
      /** Move left */
      if ((currentNode.position[1] - 1) >= 0){
        NodeDijkstra tempNode = grid[currentNode.position[0]][currentNode.position[1]-1];
        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
          tempNode.distance = currentNode.distance + 1;
          tempNode.parent = currentNode;
          q.offer(tempNode);
        }
      }
      /** Move right */
      if ((currentNode.position[1] + 1) < size){
        NodeDijkstra tempNode = grid[currentNode.position[0]][currentNode.position[1]+1];
        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
          tempNode.distance = currentNode.distance + 1;
          tempNode.parent = currentNode;
          q.offer(tempNode);
        }
      } 
      /** Move down */
      if ((currentNode.position[0] + 1) < size){
        NodeDijkstra tempNode = grid[currentNode.position[0]+1][currentNode.position[1]];
        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
          tempNode.distance = currentNode.distance + 1;
          tempNode.parent = currentNode;
          q.offer(tempNode);
        }
      }
      
      currentNode.visited = true;
    }

    List<NodeDijkstra> path = new ArrayList<>();
    if(grid[endNode.position[0]][endNode.position[1]].distance != Integer.MAX_VALUE){
      NodeDijkstra current = grid[endNode.position[0]][endNode.position[1]];
      while(current.parent != null){
        path.add(current.parent);
        current = current.parent;
      }
    }
    else{
      return new ArrayList<>();
    }
    return path;
  }

  /** A* Search */
  List<NodeAStar> pathAStar = new ArrayList<>();
  public boolean validNode(int[] position, int size){
    return (position[0]>=0) && (position[0]<size) && (position[1]>=0) && (position[1]<size);
  }

  public double minInconAndOpen(List<NodeAStar> inconList, List<NodeAStar> openList){
    NodeAStar minNode = null;
    if(!inconList.isEmpty()){
      minNode = inconList.get(0);
      for(int i = 0; i < inconList.size(); i++){
        if((minNode.g + minNode.h) > (inconList.get(i).g + inconList.get(i).h)){
          minNode = inconList.get(i);
        }
      }
    }
    if(minNode == null){
      minNode = openList.get(0);
    }
    for(int i = 0; i < openList.size(); i++){
      if((minNode.g + minNode.h) > (openList.get(i).g + openList.get(i).h)){
        minNode = openList.get(i);
      }
    }
    return minNode.g + minNode.h;
  }

  public void backTrack(NodeAStar current, NodeAStar start, NodeAStar goal, int[][] a){
    if(current.parent != null){
      backTrack(current.parent, start, goal, a);
    }
    if(current.notMatch(goal) && current.notMatch(start) && (a != null)){
      a[current.position[0]][current.position[1]] = 2;
    }

  }
}
