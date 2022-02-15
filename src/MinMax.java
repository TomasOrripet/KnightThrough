import java.util.LinkedList;
import java.util.ListIterator;

public class MinMax {
    public Environment env;
    public State state;
    public Heuristics h;
    public int maxdepth, nodesExpanded;
    public boolean role;
    public Node root;

    public MinMax(Environment env, Boolean role) {
        this.env = env;
        this.maxdepth = 1;
        this.nodesExpanded = 0;
        this.h = new Heuristic(env);
        this.role = role;
    }


    public LinkedList<Coordinates> MinMax_Decision(State state) {
        root = new Node(state, h.eval(state), role);
        if (root.role) {
            return getMax(root).move;
        } else {
            return getMin(root).move;
        }
    }

    public Node getMin(Node parent) {
        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node bestnode = null;
        int best = 10000;

        while (legalMoves.hasNext()) {
            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            //child = new Node(parent, next_state, move, 0);
            if (parent.depth < maxdepth) {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, 0);
                child.evaluation = getMax(child).evaluation+ parent.depth;
            } else {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, h.eval(next_state)+parent.depth);

            }
            if (best >= child.evaluation) {
                best = child.evaluation;
                bestnode = child;
            }
        }
        if (bestnode==null){
            parent.evaluation=0;
            return parent;
        }
        return bestnode;
    }

    public Node getMax(Node parent) {
        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node bestnode =null;
        Node child;
        int best = -10000;
        while (legalMoves.hasNext()) {
            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            if (parent.depth < maxdepth) {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, 0);
                child.evaluation = getMin(child).evaluation - parent.depth;
            } else {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, h.eval(next_state)-parent.depth);
            }
            if (best <= child.evaluation) {
                best = child.evaluation;
                bestnode = child;
            }
        }
        if (bestnode==null){
            parent.evaluation=0;
            return parent;
        }


        return bestnode;
    }
}