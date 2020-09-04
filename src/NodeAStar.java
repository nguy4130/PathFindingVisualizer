public class NodeAStar {
  int[] position;
  double h;
  double g;
  double f;
  NodeAStar parent;

  public NodeAStar(int[] position, NodeAStar parent, NodeAStar goal){
    this.position = position;
    this.parent = parent;

    if(parent == null){
      this.g = 1.0;
    }
    else{
      this.g = parent.g + 1;
    }

    if(goal == null){
      this.h = 0;
    }
    else{
      this.h = heuristc(this.position, goal.position);
    }
    this.f = this.h + this.g;
  }

  public double heuristc(int[] pos, int[] pos1){
    return Math.sqrt((pos1[0] - pos[0]) * (pos1[0] - pos[0]) + (pos1[1] - pos[1]) * (pos1[1] - pos[1]));
  }

  public void update(NodeAStar parent, NodeAStar goal){
    if(parent == null){
      this.g = 1;
    }
    else{
      this.parent = parent;
      this.g = parent.g + 1;
    }

    if(goal == null){
      this.h = 0;
    }
    else{
      this.h = heuristc(this.position, goal.position);
    }
    this.f = this.g + this.h;
  }

  public boolean isMatch(NodeAStar other){
    return (this.position[0] == other.position[0]) && (this.position[1] == other.position[1]);
  }

  public boolean notMatch(NodeAStar other){
    return (this.position[0] != other.position[0]) || (this.position[1] != other.position[1]);
  }

  public boolean lessThan(NodeAStar other){
    return this.f < other.f;
  }

  public boolean lessEqual(NodeAStar other){
    return this.f <= other.f;
  }

  public boolean greaterThan(NodeAStar other){
    return this.f > other.f;
  }

  public boolean greaterEqual(NodeAStar other){
    return this.f >= other.f;
  }

  public boolean isEqual(NodeAStar other){
    return this.f == other.f;
  }

  public boolean notEqual(NodeAStar other){
    return this.f != other.f;
  }

  public String toString(){
    return "(" + this.position[0] + " , " + this.position[1] + ")";
  }
}
