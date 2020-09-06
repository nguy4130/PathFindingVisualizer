public class NodeARA {
  int[] position;
  double h;
  double g;
  double f;
  NodeARA parent;

  public NodeARA(int[] position, NodeARA parent, NodeARA goal){
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

  public void update(NodeARA parent, NodeARA goal){
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

  public boolean isMatch(NodeARA other){
    return (this.position[0] == other.position[0]) && (this.position[1] == other.position[1]);
  }

  public boolean notMatch(NodeARA other){
    return (this.position[0] != other.position[0]) || (this.position[1] != other.position[1]);
  }

  public boolean lessThan(NodeARA other){
    return this.f < other.f;
  }

  public boolean lessEqual(NodeARA other){
    return this.f <= other.f;
  }

  public boolean greaterThan(NodeARA other){
    return this.f > other.f;
  }

  public boolean greaterEqual(NodeARA other){
    return this.f >= other.f;
  }

  public boolean isEqual(NodeARA other){
    return this.f == other.f;
  }

  public boolean notEqual(NodeARA other){
    return this.f != other.f;
  }

  public String toString(){
    return "(" + this.position[0] + " , " + this.position[1] + ")";
  }

  public double fvalue(double e){
    return this.g + e* this.h;
  }
}
