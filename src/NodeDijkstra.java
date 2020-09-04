public class NodeDijkstra {
  NodeDijkstra parent;
  Boolean visited;
  Boolean blocked;
  int[] position;
  int distance;

  public NodeDijkstra(int[] position) {
    this.position = position;
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
}
