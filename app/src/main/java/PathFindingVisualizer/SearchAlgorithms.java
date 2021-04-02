package PathFindingVisualizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;

public class SearchAlgorithms {
  
  public static List<NodeDijkstra> dijkstra(int[][] map, PathFindingVisualizerGUI gui) {
    int size = map.length;
    NodeDijkstra startNode = null;
    NodeDijkstra endNode = null;
    NodeDijkstra[][] grid = new NodeDijkstra[size][size];
    for(int i = 0; i < map.length; i++){
      for (int j = 0; j < map[i].length; j++) {
        grid[i][j] = new NodeDijkstra(i, j);
        if(map[i][j] == 1){
          grid[i][j].setBlocked(true);
        }
        else {
          grid[i][j].setBlocked(false);
          if(map[i][j] == 2) {
            startNode = grid[i][j];
          }
          else if(map[i][j] == 3) {
            endNode = grid[i][j];
          }
        }
      }
    }

    if(startNode == null || endNode == null) {
      return new ArrayList<>();
    }

    startNode.setDistance(0);
    PriorityQueue<NodeDijkstra> q = new PriorityQueue<>(10, new NodeDijkstraComparator());
    q.add(startNode);
    while(!q.isEmpty()) {
      NodeDijkstra current = q.poll();
      // Move up
      if((current.getPosition()[0] - 1) >= 0){
        NodeDijkstra temp = grid[current.getPosition()[0]-1][current.getPosition()[1]];
        if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))) {
          temp.setDistance(current.getDistance() + 1);
          temp.setParent(current);
          q.add(temp);
        }
      }
      // Move left
      if((current.getPosition()[1] - 1) >= 0) {
        NodeDijkstra temp = grid[current.getPosition()[0]][current.getPosition()[1]-1];
        if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
          temp.setDistance(current.getDistance() + 1);
          temp.setParent(current);
          q.add(temp);
        }
      }
      // Move right
      if((current.getPosition()[1] + 1) < size) {
        NodeDijkstra temp = grid[current.getPosition()[0]][current.getPosition()[1]+1];
        if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
          temp.setDistance(current.getDistance() + 1);
          temp.setParent(current);
          q.add(temp);
        }
      }
      // Move down
      if((current.getPosition()[0] + 1) < size) {
        NodeDijkstra temp = grid[current.getPosition()[0]+1][current.getPosition()[1]];
        if(!temp.isVisited() && !temp.isBlocked() && (temp.getDistance() > (current.getDistance()+1))){
          temp.setDistance(current.getDistance() + 1);
          temp.setParent(current);
          q.add(temp);
        }
      }
      current.setVisited(true);
    }

    List<NodeDijkstra> path = new ArrayList<>();
    if(grid[endNode.getPosition()[0]][endNode.getPosition()[1]].getDistance() != Integer.MAX_VALUE){
      NodeDijkstra current = grid[endNode.getPosition()[0]][endNode.getPosition()[1]];
      while(current.getParent() != null) {
        path.add(0, current.getParent());
        current = current.getParent();
      }
    }
    else{
      return new ArrayList<>();
    }
    path.add(endNode);
    for(int i = 0; i < path.size()-1; i++){
        gui.updateTileColor(path.get(i).getPosition(), PathFindingVisualizerGUI.RESULT_COLOR);
        System.out.print(path.get(i).toString()+" -> ");
    }
    System.out.println(path.get(path.size()-1).toString());
    return path;
  }

  /** Breath First Search */
  public static List<Node> bfs(int[][] map, PathFindingVisualizerGUI gui){
    Node startNode = new Node(0, 0);
    Node endNode = new Node(0, 0);
    int size = map.length;
    Node[][] nodeGrid = new Node[size][size];
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        if(map[i][j] == PathFindingVisualizerGUI.START_MODE){
          startNode.setPosition(new int[]{i, j});
          nodeGrid[i][j] = startNode;
          nodeGrid[i][j].setBlocked(false);
        } else if(map[i][j] == PathFindingVisualizerGUI.END_MODE){
          endNode.setPosition(new int[]{i, j});
          nodeGrid[i][j] = endNode;
          nodeGrid[i][j].setBlocked(false);
        } else if(map[i][j] == PathFindingVisualizerGUI.WALL_MODE){
          nodeGrid[i][j] = new Node(i, j);
          nodeGrid[i][j].setBlocked(true);
        } else {
          nodeGrid[i][j] = new Node(i, j);
          nodeGrid[i][j].setBlocked(false);
        }
      }
    }

    List<Node> result = new ArrayList<>();
    if(startNode.match(endNode)){
      return result;
    }

    Queue<Node> q = new LinkedList<>();
    q.add(startNode);

    while(!q.isEmpty()){
      Node current = q.remove();
      current.setVisited(true);
      int[] dx = new int[]{0, 0, 1, -1};
      int[] dy = new int[]{1, -1, 0, 0};
      for(int i = 0; i < dx.length; i++){
        if(validLocation(current.getPosition()[0] + dx[i], current.getPosition()[1] + dy[i], size)){
          Node nextNode = nodeGrid[current.getPosition()[0] + dx[i]][current.getPosition()[1] + dy[i]];
          if(!nextNode.isVisited() && !nextNode.isBlocked()){
            nextNode.setParent(current);
            q.add(nextNode);
          }  
        }
      }
      current.setVisited(true);
    }

    if(endNode.getParent() == null){
      return result; 
    }

    while(endNode != null){
      result.add(0, endNode);
      endNode = endNode.getParent();
    }

    for(Node node: result){
      gui.updateTileColor(node.getPosition(), PathFindingVisualizerGUI.RESULT_COLOR);
      System.out.print(" -> " + node.toString());
    }
    return result;
  }

  /** Depth First Search */
  public static List<Node> dfs(int[][] map, PathFindingVisualizerGUI gui){
    Node startNode = new Node(0, 0);
    Node endNode = new Node(0, 0);
    int size = map.length;
    Node[][] nodeGrid = new Node[size][size];
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        if(map[i][j] == PathFindingVisualizerGUI.START_MODE){
          startNode.setPosition(new int[]{i, j});
          nodeGrid[i][j] = startNode;
          nodeGrid[i][j].setBlocked(false);
        } else if(map[i][j] == PathFindingVisualizerGUI.END_MODE){
          endNode.setPosition(new int[]{i, j});
          nodeGrid[i][j] = endNode;
          nodeGrid[i][j].setBlocked(false);
        } else if(map[i][j] == PathFindingVisualizerGUI.WALL_MODE){
          nodeGrid[i][j] = new Node(i, j);
          nodeGrid[i][j].setBlocked(true);
        } else {
          nodeGrid[i][j] = new Node(i, j);
          nodeGrid[i][j].setBlocked(false);
        }
      }
    }

    List<Node> result = new ArrayList<>();
    if(startNode.match(endNode)){
      return result;
    }

    Stack<Node> stack = new Stack<>();
    stack.push(startNode);

    while(!stack.isEmpty()){
      Node current = stack.pop();
      current.setVisited(true);
      int[] dx = new int[]{0, 0, 1, -1};
      int[] dy = new int[]{1, -1, 0, 0};
      for(int i = 0; i < dx.length; i++){
        if(validLocation(current.getPosition()[0] + dx[i], current.getPosition()[1] + dy[i], size)){
          Node nextNode = nodeGrid[current.getPosition()[0] + dx[i]][current.getPosition()[1] + dy[i]];
          if(!nextNode.isVisited() && !nextNode.isBlocked()){
            nextNode.setParent(current);
            stack.push(nextNode);
          }  
        }
      }
      current.setVisited(true);
    }

    if(endNode.getParent() == null){
      return result; 
    }

    while(endNode != null){
      result.add(0, endNode);
      endNode = endNode.getParent();
    }

    for(Node node: result){
      gui.updateTileColor(node.getPosition(), PathFindingVisualizerGUI.RESULT_COLOR);
      System.out.print(" -> " + node.toString());
    }
    return result;
  }
  /** A* Search */
  // public static List<NodeAStar> aStarSearch(int[] map, PathFindingVisualizerGUI gui){
  //   int size = map.length;
  //   NodeAStar startNode = null;
  //   NodeAStar endNode = null;

  //   for(int i = 0; i < size; i++){
  //     for(int j = 0; j < size; j++){

  //     }
  //   }
  // }

  public static List<NodeAStar> astar(int[][] map,  PathFindingVisualizerGUI gui){
    
    PathFindingVisualizerUtils.LOGGER.log(Level.INFO, "Doing A* Search");

    int[] start = new int[2]; 
    int[] end = new int[2];
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[0].length; j++){
        if(map[i][j] == PathFindingVisualizerGUI.START_MODE){
          start[0] = i;
          start[1] = j;
        } else if(map[i][j] == PathFindingVisualizerGUI.END_MODE){
          end[0] = i;
          end[1] = j;
        }
      }
    }
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
      System.out.println("Here");
      //Get current node
      NodeAStar currentNode = openList.get(0);
      int currentIndex = 0;
      for(int i = 0; i < openList.size(); i++){
        if(openList.get(i).getF() < currentNode.getF()){
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
          current = current.getParent();
        }
        for(NodeAStar node: pathAStar){
          gui.updateTileColor(node.getPosition(), PathFindingVisualizerGUI.RESULT_COLOR);
        }
        return pathAStar;
      }

      //Generate children
      List<NodeAStar> children = new ArrayList<>();
      int[] dx = {0, 0, -1, 1};
      int[] dy = {-1, 1, 0, 0};
      for(int move = 0; move < 4; move++){
        int[] newPosition = new int[2];
        newPosition[0] = currentNode.getPosition()[0] + dx[move];
        newPosition[1] = currentNode.getPosition()[1] + dy[move];

        //Check within range and walkable terrain
        if(newPosition[0] <= (map.length - 1) && 
            newPosition[0] >= 0 &&
            newPosition[1] <= (map[map.length - 1].length - 1) && 
            newPosition[1] >= 0 && 
            map[newPosition[0]][newPosition[1]] == PathFindingVisualizerGUI.DEFAULT_MODE){
              NodeAStar newChildNode = new NodeAStar(currentNode, newPosition);
              children.add(newChildNode);
            }
      }

      //Loop through children nodes
      for(NodeAStar child: children){
        if(!closedList.contains(child)){
          child.setG(currentNode.getG() + 1);
          child.setH((child.getPosition()[0] - endNode.getPosition()[0]) * (child.getPosition()[0] - endNode.getPosition()[0]) +
                    (child.getPosition()[1] - endNode.getPosition()[1]) * (child.getPosition()[1] - endNode.getPosition()[1]));
          child.setF(child.getG() + child.getH());
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
  public boolean validNode(int[] getPosition(), int size){
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

  private static boolean validLocation(int x, int y, int size){
    return x >= 0 && x < size && y >= 0 && y < size;
  }
}
