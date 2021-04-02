package PathFindingVisualizer;

public class NodeDijkstra extends Node{

  private int distance;

  public NodeDijkstra(int[] position) {
    super(position);
    this.distance = Integer.MAX_VALUE;
  }

  public NodeDijkstra(int i, int j) {
    super(i, j);
    this.distance = Integer.MAX_VALUE;
  }
  
  public Boolean closerDistance(NodeDijkstra other){
    return this.distance < other.distance;
  }

  public int getDistance() {
    return this.distance;
  }

  public void setDistance(int distance){
    this.distance = distance;
  }
  
  @Override
  public NodeDijkstra getParent() {
    return (NodeDijkstra) this.parent;
  }

  public void setParent(NodeDijkstra parent){
    this.parent = parent;
  }
}
