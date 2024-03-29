package PathFindingVisualizer;

import java.util.Comparator;

public class NodeDijkstraComparator implements Comparator<NodeDijkstra> {
  public int compare(NodeDijkstra node1, NodeDijkstra node2) {
    if(node1.getDistance() > node2.getDistance()) {
      return 1;
    }
    else if(node1.getDistance() < node2.getDistance()) {
      return -1;
    }
    else{
      return 0;
    }
  }

}