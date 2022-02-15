import java.util.LinkedList;
import java.util.ListIterator;

public class MinMax {
    public Environment env;
    public State state;
    public Heuristics h;
    public int maxdepth;
    public boolean role;

    public MinMax(Environment env, Boolean role) {
        this.env = env;
        this.maxdepth = 4;
        this.h = new Heuristic(env);
        this.role = role;
        //this.root = new Node(env.currentState,h.eval(env.currentState),role);
        //this.state = env.currentState;
    }


    public LinkedList<Coordinates> MinMax_Decision(State state) {
        Node node = dosearch(new Node(state, h.eval(state), role));
        return node.move;
    }

    public Node getMin(Node parent){
        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node best = parent;
        while (legalMoves.hasNext()) {

            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            child = new Node(parent, next_state,move, h.eval(next_state));
            if (child.depth<maxdepth){
                best = getMax(child);
                System.out.printf("maxNode"+best);
            }
            if (child.compareTo(best) < 0){
                best = child;
            }
        }
        return best;
    }

    public Node getMax(Node parent){
        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node best = parent;
        while (legalMoves.hasNext()) {

            LinkedList<Coordinates> move = legalMoves.next();
            State next_state = env.getNextState(move.get(0), move.get(1), clonestate, parent.role);
            child = new Node(parent, next_state,move, h.eval(next_state));
            if (child.depth<maxdepth){
                best = getMin(child);
                System.out.printf("min Node: "+ best);
            }
            if (child.compareTo(best) > 0){
                best = child;
            }
        }
        return best;
    }


    public Node dosearch(Node parent) {

        State clonestate = (State) parent.state.clone();
        ListIterator<LinkedList<Coordinates>> legalMoves = env.getLegalMoves(clonestate, parent.role).listIterator();
        Node child = null;
        Node best = parent;
        while (legalMoves.hasNext()) {
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
*/
        }
        System.out.printf("best Node: "+best+"\n");

        return best;
    }
}