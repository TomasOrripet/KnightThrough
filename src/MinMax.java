import java.util.ArrayList;
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
        this.maxdepth = 4;
        this.nodesExpanded = 0;
        this.h = new Heuristic(env);
        this.role = role;
        //this.root = new Node(env.currentState, h.eval(env.currentState), role);
        //this.state = env.currentState;
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
        Node best = null;
        ArrayList<Node> nodes = new ArrayList<Node>();
        while (legalMoves.hasNext()) {

            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            //child = new Node(parent, next_state, move, 0);
            if (parent.depth < maxdepth) {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, 0);
                child.evaluation = getMax(child).evaluation;
            } else {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, h.eval(next_state));
            }

            if (best == null) {
                best = child;
            }
            if (best.evaluation > child.evaluation) {
                best = child;
            }
            nodes.add(child);
        }
        //System.out.printf("worst Returns:" + best + "\n");
        //System.out.printf("All moves: "+nodes);
        if (best == null){
            return new Node(clonestate,0, !parent.role);
        }
        return best;
    }

    public Node getMax(Node parent) {
        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node best = null;
        ArrayList<Node> nodes = new ArrayList<Node>();
        while (legalMoves.hasNext()) {
            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            if (parent.depth < maxdepth) {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, 0);
                child.evaluation = getMin(child).evaluation;

            } else {
                this.nodesExpanded++;
                child = new Node(parent, next_state, move, h.eval(next_state));
            }
            if (best == null) {
                best = child;
            }
            if (best.evaluation < child.evaluation) {
                best = child;
            }
            nodes.add(child);
        }
        //System.out.printf("best Return:" + best + "\n");
        //System.out.printf("All moves: "+nodes+"\n");
        if (best == null){
            return new Node(clonestate,0, !parent.role);
        }
        return best;
    }
}


    /*public Node dosearch(Node parent) {

        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node best = parent;
            if (parent.role){
                return getMax(parent);
            }
            else{
                return getMin(parent);
            }
            //parent = new Node(parent, env.getNextState(parent.state, legal))
           /*LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            int eval = parent.depth + h.eval(next_state);
            child = new Node(parent, next_state, move, eval);
            System.out.printf("child White: " + child.state.whiteknights + "\n child black: " + child.state.blackknights + "\n");
            System.out.println(child);
            if (child.depth < maxdepth) {
                dosearch(child);
            }
            if (best.compareTo(child) < 0) {
                best = child;
            }

        }
        System.out.printf("best Node: "+best+"\n");

        return best;
    }
}
*/