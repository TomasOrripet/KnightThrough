import java.security.KeyPair;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Environment {

    public int width, length;
    public State currentState;
    private boolean role; // if agent is white then true



    public Environment(int width, int length, String role) {
        this.width = width;
        this.length = length;
        this.currentState = new State(width, length);
        this.role = role.equals("white");

    }

    public void updateboard(int x1, int y1, int x2, int y2,String roleOfLast){
        makemove(x1-1, y1-1, x2-1, y2-1, roleOfLast);
        currentState = new State(
                currentState.updateBoard(x1, y1, x2, y2, roleOfLast),
                currentState.whiteknights,
                currentState.blackknights
                );
    }

    public void makemove(int x1, int y1, int x2, int y2, String RoleOfLast){
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
    }

    public LinkedList<LinkedList<Coordinates>> getLegalMoves(){
        //list of legal moves
        LinkedList<LinkedList<Coordinates>> legalmoves = new LinkedList<LinkedList<Coordinates>>();
        if (role) {
            ListIterator<Coordinates> knightlist = currentState.whiteknights.listIterator();
            while (knightlist.hasNext()) {
                LinkedList<Coordinates> legalmove = new LinkedList<Coordinates>();
                Coordinates place = knightlist.next();
                legalmove.add(place);
                if ((place.x - 2 >= 0) && place.y + 1 >= 0 && currentState.isEmpty(place.x - 2, place.y + 1)) {
                    legalmove.add(new Coordinates(place.x - 2, place.y + 1));
                }
                if ((place.x - 1 >= 0) && place.y + 2 >= 0 && currentState.isEmpty(place.x - 1, place.y + 2)) {
                    legalmove.add(new Coordinates(place.x - 1, place.y + 2));
                }
                if ((place.x + 2 < width) && place.y + 1 >= 0 && currentState.isEmpty(place.x + 2, place.y + 1)) {
                    legalmove.add(new Coordinates(place.x + 2, place.y + 1));
                }
                if ((place.x + 1 < width) && place.y + 2 >= 0 && currentState.isEmpty(place.x + 1, place.y + 2)) {
                    legalmove.add(new Coordinates(place.x + 1, place.y + 2));
                }
                //attacks
                if ((place.x + 1 < width) && place.y + 1 >= 0 && currentState.canAttack(place.x + 1, place.y + 1, "B")) {
                    legalmove.add(new Coordinates(place.x + 1, place.y + 1));
                }
                if ((place.x - 1 >= 0) && place.y + 1 >= 0 && currentState.canAttack(place.x - 1, place.y + 1, "B")) {
                    legalmove.add(new Coordinates(place.x - 1, place.y + 1));
                }
                legalmoves.add(legalmove);
            }
        }
        else {
            ListIterator<Coordinates> knightlist = currentState.blackknights.listIterator();
            while (knightlist.hasNext()) {
                LinkedList<Coordinates> legalmove = new LinkedList<Coordinates>();
                Coordinates place = knightlist.next();
                legalmove.add(place);
                if ((place.x - 2 >= 0) && place.y - 1 >= 0 && currentState.isEmpty(place.x - 2, place.y - 1)) {
                    legalmove.add(new Coordinates(place.x - 2, place.y - 1));
                }
                if ((place.x - 1 >= 0) && place.y - 2 >= 0 && currentState.isEmpty(place.x - 1, place.y - 2)) {
                    legalmove.add(new Coordinates(place.x - 1, place.y - 2));
                }
                if ((place.x + 2 < width) && place.y - 1 >= 0 && currentState.isEmpty(place.x + 2, place.y - 1)) {
                    legalmove.add(new Coordinates(place.x + 2, place.y - 1));
                }
                if ((place.x + 1 < width) && place.y - 2 >= 0 && currentState.isEmpty(place.x + 1, place.y - 2)) {
                    legalmove.add(new Coordinates(place.x + 1, place.y - 2));
                }
                //attacks
                if ((place.x + 1 < width) && place.y - 1 >= 0 && currentState.canAttack(place.x + 1, place.y - 1, "W")) {
                    legalmove.add(new Coordinates(place.x + 1, place.y - 1));
                }
                if ((place.x - 1 >= 0) && place.y - 1 >= 0 && currentState.canAttack(place.x - 1, place.y - 1, "W")) {
                    legalmove.add(new Coordinates(place.x - 1, place.y - 1));
                }
                legalmoves.add(legalmove);
            }
        }
        return legalmoves;
    }

    @Override
    public String toString() {
        return "Environment{\n" + currentState.toString()+'}';
    }
}


