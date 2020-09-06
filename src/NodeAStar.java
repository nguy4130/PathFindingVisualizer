import javax.xml.crypto.NodeSetData;

public class NodeAStar {
  NodeAStar parent;
  int[] position;
  int g;
  int h;
  int f;

  public NodeAStar(NodeAStar parent, int[] position){
    this.parent = parent;
    this.position = position;

    this.g = 0;
    this.h = 0;
    this.f = 0;
  }

  public String toString(){
    return "(" + this.position[0] + " , " + this.position[1] + ")";
  }

  public boolean match(NodeAStar other){
    return this.position[0] == other.position[0] && this.position[1] == other.position[1];
  }
}
