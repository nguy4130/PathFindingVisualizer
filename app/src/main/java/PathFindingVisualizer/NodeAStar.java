package PathFindingVisualizer;

public class NodeAStar extends Node{
  
  private int g;
  private int h;
  private int f;

  public NodeAStar(NodeAStar parent, int[] position){
    super(position);
    this.parent = parent;

    this.g = 0;
    this.h = 0;
    this.f = 0;
  }
  public NodeAStar(NodeAStar parent, int x, int y){
    super(x, y);
    this.parent = parent;

    this.g = 0;
    this.h = 0;
    this.f = 0;
  }

  public NodeAStar(int[] position){
    super(position);

    this.g = 0;
    this.h = 0;
    this.f = 0;
  }

  public NodeAStar(int x, int y){
    super(x, y);

    this.g = 0;
    this.h = 0;
    this.f = 0;
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

  // @Override
  // public NodeAStar getParent(){
  //   return this.parent;
  // }

  // @Override
  // public void setParent(NodeAStar parent){
  //   this.parent = (Node) parent;
  // }
}
