import java.util.*;

public class PathFindingCore {

  public static void printPath(List<NodeDijkstra> path){
    for(int i = 0; i < path.size()-1; i++){
      System.out.print(path.get(i).toString()+" -> ");
    }
    System.out.println(path.get(path.size()-1).toString());
  }

  public static void main(String[] args){
    int[] start = {0, 0};
    int[] end = {3, 3};
    List<NodeDijkstra> pathDijkstra = SearchAlgorithms.dijkstra(Maps.keller);
    List<NodeAStar> pathAStar = SearchAlgorithms.aStar(Maps.keller);
//    System.out.println(pathAStar.size());
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
