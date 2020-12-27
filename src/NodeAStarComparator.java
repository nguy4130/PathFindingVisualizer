import java.util.Comparator;

public class NodeAStarComparator implements Comparator<NodeAStar> {

  public int compare(NodeAStar node1, NodeAStar node2) {
    if(node1.f > node2.f) {
      return 1;
    }
    else if(node1.f < node2.f) {
      return -1;
    }
    else{
      return 0;
    }
  }
}
