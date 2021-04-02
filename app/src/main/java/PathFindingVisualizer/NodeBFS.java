// package PathFindingVisualizer;

// public class Node {
//   private Node parent;
//   private int[] position;
//   private boolean visited;
//   private boolean blocked;

//   public Node(int[] position) {
//     this.parent = null;
//     this.position = position;
//     this.visited = false;
//     this.blocked = false;
//   }

//   public Node(int x, int y) {
//     this.parent = null;
//     this.position = new int[]{x, y};
//     this.visited = false;
//     this.blocked = false;
//   }

//   public String toString() {
//     return "(" + this.position[0] + " , " + this.position[1] + ")";
//   }

//   public boolean match(NodeBFS other){
//     return this.getPosition()[0] == other.getPosition()[0] 
//       && this.getPosition()[1] == other.getPosition()[1];
//   }

//   public int[] getPosition() {
//     return this.position;
//   }

//   public void setPosition(int[] position){
//     this.position = position;
//   }

//   public boolean isVisited(){
//     return this.visited;
//   }

//   public void setVisited(boolean visited){
//     this.visited = visited;
//   }

//   public boolean isBlocked(){
//     return this.blocked;
//   }

//   public void setBlocked(boolean blocked){
//     this.blocked = blocked;
//   }

//   public void setParent(NodeBFS parent){
//     this.parent = parent;
//   }

//   public NodeBFS getParent(){
//     return this.parent;
//   }
// }
