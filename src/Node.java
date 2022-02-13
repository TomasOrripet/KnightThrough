public class Node implements Comparable<Node> {
    public State state;
    public Node parent;
    public Coordinates moveFrom, moveTo;
    public int depth;
    public int evaluation;

    /**
     * create the root node of the search tree
     * @param state the state belonging to this node
     * @param val the evaluation of this node
     */
    public Node(State state, int val) {
        this.parent = null;
        this.state = state;
        this.moveFrom = null;
        this.moveTo = null;
        this.depth = 0;
        this.evaluation = val;
    }
    /**
     * create a new node
     * @param parent the parent of the node
     * @param state the state belonging to this node
     * @param moveFrom action that was executed to get to this node
     * @param moveTo action that was executed to get to this node
     * @param val the evaluation of this node
     */

    public Node(Node parent, State state, Coordinates moveFrom, Coordinates moveTo, int val) {
        this.parent = parent;
        this.state = state;
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
        this.depth = parent.depth + 1;
        this.evaluation = val;
    }

    @Override
    public int compareTo(Node o) {
        return this.evaluation - o.evaluation;
    }
}
