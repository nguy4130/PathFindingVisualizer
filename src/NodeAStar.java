public class NodeAStar {
  NodeAStar parent;
  int[] position;
  double g;
  double h;
  double f;

  public NodeAStar(int[] position, NodeAStar parent, NodeAStar goal){
    this.parent = parent;
    this.position = new int[2];
    this.position[0] = position[0];
    this.position[1] = position[1];
    if(parent == null){
      this.g = 1.0;
    }
    else{
      this.g = parent.g + 1;
    }
    if(goal == null){
      this.h = 0.0;
    }
    else{
      this.h = heuristic(this.position, goal.position);
    }
    this.f = this.g + this.h;
  }

  public void update(NodeAStar parent, NodeAStar goal) {
    if(parent == null){
      this.g = 1.0;
    }
    else{
      this.parent = parent;
      this.g = parent.g + 1;
    }
    if(goal == null){
      this.h = 0;
    }
    else{
      this.h = heuristic(this.position, goal.position);
    }
    this.f = this.g + this.h;
  }

  public String toString(){
    return "(" + this.position[0] + " , " + this.position[1] + ")";
  }

  public boolean match(NodeAStar other){
    return this.position[0] == other.position[0] && this.position[1] == other.position[1];
  }

  private double heuristic(int[] pos1, int[] pos2){
    return Math.sqrt((pos1[0]-pos2[0])*(pos1[0]-pos2[0]) + (pos1[1]-pos2[1])*(pos1[1]-pos2[1]));
  }
}
