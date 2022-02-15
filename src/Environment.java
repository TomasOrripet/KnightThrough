import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class Environment {

    protected int width, length;
    protected State currentState;
    protected boolean role; // if agent is white then true



    public Environment(int width, int length, String role) {
        this.width = width;
        this.length = length;
        this.currentState = new State(width, length);
        this.role = role.equals("white");
    }



    public void doAction(Coordinates from, Coordinates to, boolean turn){
        currentState = getNextState(from, to, currentState, turn);
        if (turn){
            currentState.board.get(from.y).set(from.x, " ");
            currentState.board.get(to.y).set(to.x, "W");
        }
        else{
            currentState.board.get(from.y).set(from.x, " ");
            currentState.board.get(to.y).set(to.x, "B");
        }

    }

    public State getNextState(Coordinates from, Coordinates to, State state, Boolean turn){
        ArrayList<Coordinates> white = (ArrayList<Coordinates>) state.whiteknights.clone();
        ArrayList<Coordinates> black = (ArrayList<Coordinates>) state.blackknights.clone();
        if (turn) {
            white.remove(from);
            white.add(to);
            if (black.contains(new Coordinates(to.x, to.y))) {
                black.remove(to);
            }
        }
        else {
            black.remove(from);
            black.add(to);
            if (white.contains(new Coordinates(to.x, to.y))) {
                white.remove(to);
            }
        }
        return new State(white, black, state.board);
    }

    public State getCurrentState() {
        return currentState;
    }

    /*public State updateboard(int x1, int y1, int x2, int y2,String roleOfLast){
        makemove(x1-1, y1-1, x2-1, y2-1, roleOfLast);
        return new State(
                currentState.updateBoard(x1, y1, x2, y2, roleOfLast),
                currentState.whiteknights,
                currentState.blackknights
                );

    }*/


    /*public void makemove(int x1, int y1, int x2, int y2, String RoleOfLast){
        // moves position of knight in knight array
        // if knight is attacking we remove knight from knight array
        if (RoleOfLast.equals("W")){

            if(currentState.board.get(y2).get(x2).equals("B")){
                currentState.blackknights.remove(new Coordinates(x2, y2));
            }
            currentState.whiteknights.remove(new Coordinates(x1, y1));
            currentState.whiteknights.add(new Coordinates(x2, y2));
        }
        else {

            if(currentState.board.get(y2).get(x2).equals("W")){
                currentState.whiteknights.remove(new Coordinates(x2, y2));
            }
            currentState.blackknights.remove(new Coordinates(x1, y1));
            currentState.blackknights.add(new Coordinates(x2, y2));
        }
    }*/

    public LinkedList<LinkedList<Coordinates>> getLegalMoves(State state, boolean white){
        //list of legal moves
        LinkedList<LinkedList<Coordinates>> legalmoves = new LinkedList<LinkedList<Coordinates>>();
        if (white) {
            ListIterator<Coordinates> knightlist = state.whiteknights.listIterator();
            while (knightlist.hasNext()) {
                LinkedList<Coordinates> legalmove = new LinkedList<Coordinates>();
                Coordinates place = knightlist.next();
                if ((place.x - 2 >= 0) && place.y + 1 < length && state.isEmpty(place.x - 2, place.y + 1)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 2, place.y + 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x - 1 >= 0) && place.y + 2 < length && state.isEmpty(place.x - 1, place.y + 2)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 1, place.y + 2));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x + 2 < width) && place.y + 1 < length && state.isEmpty(place.x + 2, place.y + 1)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 2, place.y + 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x + 1 < width) && place.y + 2 < length && state.isEmpty(place.x + 1, place.y + 2)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 1, place.y + 2));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                //attacks
                if ((place.x + 1 < width) && place.y + 1 < length && state.canAttack(place.x + 1, place.y + 1, true)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 1, place.y + 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x - 1 >= 0) && place.y + 1 < length && state.canAttack(place.x - 1, place.y + 1, true)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 1, place.y + 1));
                    legalmoves.add(legalmove);
                }
            }
        }
        else {
            ListIterator<Coordinates> knightlist = state.blackknights.listIterator();
            while (knightlist.hasNext()) {
                LinkedList<Coordinates> legalmove = new LinkedList<Coordinates>();
                Coordinates place = knightlist.next();
                if ((place.x - 2 >= 0) && place.y - 1 >= 0 && state.isEmpty(place.x - 2, place.y - 1)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 2, place.y - 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x - 1 >= 0) && place.y - 2 >= 0 && state.isEmpty(place.x - 1, place.y - 2)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 1, place.y - 2));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x + 2 < width) && place.y - 1 >= 0 && state.isEmpty(place.x + 2, place.y - 1)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 2, place.y - 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x + 1 < width) && place.y - 2 >= 0 && state.isEmpty(place.x + 1, place.y - 2)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 1, place.y - 2));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                //attacks
                if ((place.x + 1 < width) && place.y - 1 >= 0 && state.canAttack(place.x + 1, place.y - 1, false)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x + 1, place.y - 1));
                    legalmoves.add(legalmove);
                    legalmove = new LinkedList<Coordinates>();
                }
                if ((place.x - 1 >= 0) && place.y - 1 >= 0 && state.canAttack(place.x - 1, place.y - 1, false)) {
                    legalmove.add(place);
                    legalmove.add(new Coordinates(place.x - 1, place.y - 1));
                    legalmoves.add(legalmove);
                }
            }
        }
        return legalmoves;
    }

    @Override
    public String toString() {
        return "Environment{\n" + currentState.toString()+'}';
    }
}


