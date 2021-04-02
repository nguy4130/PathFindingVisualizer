package PathFindingVisualizer;

public class NodeAStar {
  private NodeAStar parent;
  private int[] position;
  private int g;
  private int h;
  private int f;

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

  public void setParent(NodeAStar parent){
    this.parent = parent;
  }

  public NodeAStar getParent(){
    return this.parent;
  }

  public void setPosition(int[] position){
    this.position = position;
  }

  public int[] getPosition() {
    return this.position;
  }

  public void setG(int g){
    this.g = g;
  }

  public int getG() {
    return this.g;
  }

  public void setH(int h){
    this.h = h;
  }

  public int getH() {
    return this.h;
  }

  public void setF(int f){
    this.f = f;
  }

  public int getF() {
    return this.f;
  }
}
