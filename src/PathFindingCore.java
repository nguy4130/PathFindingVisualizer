import java.util.*;

public class PathFindingCore {
  static int[][] map1 = {
    {2, 0, 0, 0},
    {0, 1, 1, 0},
    {0, 0, 1, 0},
    {0, 1, 1, 3}
  };

  static int[][] map2 = {
          {2, 0, 0, 0, 0, 0, 1, 0, 0, 0},
          {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
          {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
          {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
          {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
          {0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
          {0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
          {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
          {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
  };

  public static void printPath(List<NodeDijkstra> path){
    for(int i = 0; i < path.size()-1; i++){
      System.out.print(path.get(i).toString()+" -> ");
    }
    System.out.println(path.get(path.size()-1).toString());
  }

  public static void main(String[] args){
    int[] start = {0, 0};
    int[] end = {3, 3};
    List<NodeDijkstra> pathDijkstra = dijkstra(map1);
    printPath(pathDijkstra);
//    List<NodeAStar> pathAStar = astar(map1, start, end);
//    System.out.println();
//    for(int i = pathAStar.size() -1; i >= 0; i--){
//      System.out.println(pathAStar.get(i).toString());
//    }
  }
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
    return path;
//    NodeDijkstra startNode = new NodeDijkstra(start);
//    NodeDijkstra endNode = new NodeDijkstra(end);
//    NodeDijkstra[][] grid = new NodeDijkstra[size][size];
//    for(int i = 0; i < size; i++){
//      for(int j = 0; j < size; j++){
//        int[] position = new int[2];
//        position[0] = i;
//        position[1] = j;
//        grid[i][j] = new NodeDijkstra(position);
//        if(map[i][j] == 1){
//          grid[i][j].blocked = true;
//        }
//        else{
//          grid[i][j].blocked = false;
//        }
//      }
//    }
//    startNode.distance = 0;
//    Queue<NodeDijkstra> q = new LinkedList<>();
//    q.offer(startNode);
//    while(!q.isEmpty()){
//      NodeDijkstra currentNode = q.poll();
//      /** Move up */
//      if ((currentNode.position[0] - 1) >= 0){
//        NodeDijkstra tempNode = grid[currentNode.position[0]-1][currentNode.position[1]];
//        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
//          tempNode.distance = currentNode.distance + 1;
//          tempNode.parent = currentNode;
//          q.offer(tempNode);
//        }
//      }
//      /** Move left */
//      if ((currentNode.position[1] - 1) >= 0){
//        NodeDijkstra tempNode = grid[currentNode.position[0]][currentNode.position[1]-1];
//        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
//          tempNode.distance = currentNode.distance + 1;
//          tempNode.parent = currentNode;
//          q.offer(tempNode);
//        }
//      }
//      /** Move right */
//      if ((currentNode.position[1] + 1) < size){
//        NodeDijkstra tempNode = grid[currentNode.position[0]][currentNode.position[1]+1];
//        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
//          tempNode.distance = currentNode.distance + 1;
//          tempNode.parent = currentNode;
//          q.offer(tempNode);
//        }
//      }
//      /** Move down */
//      if ((currentNode.position[0] + 1) < size){
//        NodeDijkstra tempNode = grid[currentNode.position[0]+1][currentNode.position[1]];
//        if(!tempNode.visited && !tempNode.blocked && (tempNode.distance > currentNode.distance +1)){
//          tempNode.distance = currentNode.distance + 1;
//          tempNode.parent = currentNode;
//          q.offer(tempNode);
//        }
//      }
//
//      currentNode.visited = true;
//    }
//
//    List<NodeDijkstra> path = new ArrayList<>();
//    if(grid[endNode.position[0]][endNode.position[1]].distance != Integer.MAX_VALUE){
//      NodeDijkstra current = grid[endNode.position[0]][endNode.position[1]];
//      while(current.parent != null){
//        path.add(current.parent);
//        current = current.parent;
//      }
//    }
//    else{
//      return new ArrayList<>();
//    }
//    return path;
  }

  /** A* Search */
  public static List<NodeAStar> astar(int[][] map, int[] start, int[] end){
    //Create start and end node
    NodeAStar startNode = new NodeAStar(null, start);
    NodeAStar endNode = new NodeAStar(null, end);

    //Initialize open and closed lists
    List<NodeAStar> openList = new ArrayList<>();
    List<NodeAStar> closedList = new ArrayList<>();

    //Add startNode to openList
    openList.add(startNode);

    //Loop to find the path
    while(!openList.isEmpty()){
      //Get current node
      NodeAStar currentNode = openList.get(0);
      int currentIndex = 0;
      for(int i = 0; i < openList.size(); i++){
        if(openList.get(i).f < currentNode.f){
          currentNode = openList.get(i);
          currentIndex = i;
        }
      }
      
      //Pop current off open list, add to closed list
      openList.remove(currentIndex);
      closedList.add(currentNode);
      
      //Found the goal
      if(currentNode.match(endNode)){
        List<NodeAStar> pathAStar = new ArrayList<>();
        NodeAStar current = currentNode;
        while(current != null){
          pathAStar.add(current);
          current = current.parent;
        }
        return pathAStar;
      }

      //Generate children
      List<NodeAStar> children = new ArrayList<>();
      int[] dx = {0, 0, -1, 1};
      int[] dy = {-1, 1, 0, 0};
      for(int move = 0; move < 4; move++){
        int[] newPosition = new int[2];
        newPosition[0] = currentNode.position[0] + dx[move];
        newPosition[1] = currentNode.position[1] + dy[move];

        //Check within range and walkable terrain
        if(newPosition[0] <= (map.length - 1) && 
            newPosition[0] >= 0 &&
            newPosition[1] <= (map[map.length - 1].length - 1) && 
            newPosition[1] >= 0 && 
            map[newPosition[0]][newPosition[1]] == 0){
              NodeAStar newChildNode = new NodeAStar(currentNode, newPosition);
              children.add(newChildNode);
            }
      }

      //Loop through children nodes
      for(NodeAStar child: children){
        if(!closedList.contains(child)){
          child.g = currentNode.g + 1;
          child.h = (child.position[0] - endNode.position[0]) * (child.position[0] - endNode.position[0]) +
                    (child.position[1] - endNode.position[1]) * (child.position[1] - endNode.position[1]);
          child.f = child.g + child.h;
        }
        if(!openList.contains(child)){
          openList.add(child);
        }
      }
    }
    return new ArrayList<>();
  }
  /** ARA* Search */
  /*double e = 2.5;
  List<NodeARA> pathARA = new ArrayList<>();
  public boolean validNode(int[] position, int size){
    return (position[0]>=0) && (position[0]<size) && (position[1]>=0) && (position[1]<size);
  }

  public double minInconAndOpen(List<NodeARA> inconList, List<NodeARA> openList){
    NodeARA minNode = null;
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

  public void backTrack(NodeARA current, NodeARA start, NodeARA goal, int[][] a){
    if(current.parent != null){
      backTrack(current.parent, start, goal, a);
    }
    if(current.notMatch(goal) && current.notMatch(start) && (a != null)){
      a[current.position[0]][current.position[1]] = 2;
    }
  }

  public void finalBackTrack(NodeARA current, NodeARA start, NodeARA goal, int [][] a){
    if(current.parent != null){
      finalBackTrack(current.parent, start, goal, a);
    }
    if(current.notMatch(goal) && current.notMatch(start) && (a != null)){
      a[current.position[0]][current.position[1]] = 2;
    }
    pathARA.add(current);
  }

  public boolean inArray(NodeARA[] a, int[] position){
    for(int i = 0; i < a.length; i++){
      if((a[i].position[0] == position[0]) && (a[i].position[1] == position[1])){
        return true;
      }
    }
    return false;
  }

  public void addToInconList(List<NodeARA> inconList, NodeARA current){
    if(!inconList.contains(current)){
      inconList.add(current);
    }
    else{
      for(int i = 0; i < inconList.size(); i++){
        if(inconList.get(i).isMatch(current)){
          inconList.set(i, current);
        }
      }
    }
  }

  public void improvePath(
    int[][] a, 
    NodeARA start, 
    NodeARA goal, 
    int size, 
    Queue<NodeARA> openList, 
    List<NodeARA> closedList, 
    List<NodeARA> inconList,
    NodeARA[][] parentMatrix){
      int[] dx = {0, 0, -1, 1};
      int[] dy = {-1, 1, 0, 0};
      NodeARA parent = start;
      parentMatrix[parent.position[0]][parent.position[1]] = parent;
      int count = 2;
      while(!openList.isEmpty()){
        if(goal.fvalue(e) > openList.peek().fvalue(e)){
          parent = openList.poll();
          closedList.add(parent);
          if(parent.notMatch(goal)){
            for(int i = 0; i < 4; i++){
              int[] pos = new int(2);
              pos[0] = parent.position[0] + dx[i]; //new x
              int x = pos[0];
              pos[1] = parent.position[1] + dy[i]; //new y
              int y = pos[1];
              if(validNode(pos, size)){
                if(a[x][y] != 1){
                  if(parentMatrix[x][y] != null){
                    NodeARA temp = new NodeARA(pos, parent, goal);
                    temp.g = Integer.MAX_VALUE;
                    parentMatrix[x][y] = temp;
                  }
                  if(parentMatrix[x][y].g > (1 + parent.g)){
                    parentMatrix[x][y].update(parent, goal);
                    NodeARA temp = parentMatrix[x][y];
                    if(!inArray(closedList, pos)){
                      if(openList.contains(parentMatrix[x][y])){
                        int openListSize = openList.size();
                        for(int j = 0; j < openListSize; j++){
                          open
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }*/
}
