import java.util.LinkedList;

public class Node implements Comparable<Node> {
    public State state;
    public Node parent;
    public LinkedList<Coordinates> move;
    public int depth;
    public int evaluation;
    public boolean role;

    /**
     * create the root node of the search tree
     * @param state the state belonging to this node
     * @param val the evaluation of this node
     */
    public Node(State state, int val, boolean role) {
        System.out.print("create Node");
        this.parent = null;
        this.state = state;
        this.move = null;
        this.depth = 0;
        this.evaluation = val;
        this.role = role;
    }
    /**
     * create a new node
     * @param parent the parent of the node
     * @param state the state belonging to this node
     * @param val the evaluation of this node
     */

    public Node(Node parent, State state, LinkedList<Coordinates> move, int val) {
        this.parent = parent;
        this.state = state;
        this.move = move;
        this.depth = parent.depth + 1;
        this.evaluation = val;
        this.role = !parent.role;
    }

    public String toString(){
        return "Node{move: "+move+" depth: "+ depth + " Value:  " + evaluation + "Role: "+role+"}\n";
    }


    @Override
    public int compareTo(Node o) {
        return this.evaluation - o.evaluation;
    }
}
