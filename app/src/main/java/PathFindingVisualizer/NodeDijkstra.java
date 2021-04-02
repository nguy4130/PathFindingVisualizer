package PathFindingVisualizer;

public class NodeDijkstra extends Node{
  // private NodeDijkstra parent;
  // private Boolean visited;
  // private Boolean blocked;
  // private int[] position;
  private int distance;

  public NodeDijkstra(int[] position) {
    super(position);
    // this.position = new int[2];
    // this.position[0] = position[0];
    // this.position[1] = position[1];
    this.distance = Integer.MAX_VALUE;
    // this.parent = null;
    // this.visited = false;
    // this.blocked = false;
  }

  public NodeDijkstra(int i, int j) {
    super(i, j);
    // this.position = new int[2];
    // this.position[0] = i;
    // this.position[1] = j;
    this.distance = Integer.MAX_VALUE;
    // this.parent = null;
    // this.visited = false;
    // this.blocked = false;
  }

  // public String toString() {
  //   return "(" + this.position[0] + " , " + this.position[1] + ")";
  // }
  
  public Boolean closerDistance(NodeDijkstra other){
    return this.distance < other.distance;
  }

  // @Override
  // public int[] getPosition(){
  //   return this.position;
  // }

  // public boolean isVisited() {
  //   return this.visited;
  // }

  // public boolean isBlocked() {
  //   return this.blocked;
  // }

  public int getDistance() {
    return this.distance;
  }

  public void setDistance(int distance){
    this.distance = distance;
  }

  // public void setVisited(boolean visited){
  //   this.visited = visited;
  // }

  // public void setBlocked(boolean blocked){
  //   this.blocked = blocked;
  // }
  
  @Override
  public NodeDijkstra getParent() {
    return (NodeDijkstra) this.parent;
  }

  public void setParent(NodeDijkstra parent){
    this.parent = parent;
  }
}
