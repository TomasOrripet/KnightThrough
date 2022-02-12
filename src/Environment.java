import java.security.KeyPair;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class Environment {

    public int width, length;
    public State currentState;
    //public LinkedList<Coordinates>;



    public Environment(int width, int length, String role) {
        this.width = width;
        this.length = length;
        this.currentState = new State(width, length);

        System.out.printf("percepts: " + width, length);
    }

    public void updateboard(int y1, int x1, int x2, int y2, String role){
        currentState.updateboard(y1, x1, x2, y2, role);
    }

    public void makemove(int x1, int y1, int x2, int y2){
        System.out.printf("move: "+ x1,y1,x2,y2);
        currentState.blackknights.remove(new Coordinates(x1,y1));
        currentState.blackknights.add(new Coordinates(x2,y2));
    }

    public LinkedList<LinkedList<Coordinates>> getlegalmoves(){
        LinkedList<LinkedList<Coordinates>> legalmoves = new LinkedList<LinkedList<Coordinates>>();
        ListIterator<Coordinates> knightlist = currentState.blackknights.listIterator();
        while (knightlist.hasNext()) {
            LinkedList<Coordinates> legalmove = new LinkedList<Coordinates>();
            Coordinates place = knightlist.next();
            legalmove.add(place);
            if ((place.x - 2 >= 0) && place.y-1 >= 0 && currentState.isempty(place.x-2, place.y-1))  {
               legalmove.add(new Coordinates(place.x - 2, place.y - 1));

            }
            if ((place.x - 1 >= 0) && place.y-2 >= 0 && currentState.isempty(place.x-1, place.y-2))  {
                legalmove.add(new Coordinates(place.x - 1, place.y - 2));

            }
            if ((place.x + 2 < width) && place.y-1 >= 0 && currentState.isempty(place.x+2, place.y-1))  {
                legalmove.add(new Coordinates(place.x+2, place.y-1));

            }
            if ((place.x + 1 < width) && place.y-2 >= 0 && currentState.isempty(place.x+1, place.y-2))  {
                legalmove.add(new Coordinates(place.x+1, place.y-2));

            }
            //attacks
            if ((place.x + 1 < width) && place.y-1 >= 0 && currentState.canattack(place.x+1, place.y-1, "W")) {
                legalmove.add(new Coordinates(place.x+1, place.y-1));

            }
            if ((place.x - 1 >= 0) && place.y-1 >= 0 && currentState.canattack(place.x-1, place.y-1,"W")) {
                legalmove.add(new Coordinates(place.x-1, place.y-1));

            }
            legalmoves.add(legalmove);
        }
        return legalmoves;
    }


    @Override
    public String toString() {
        return "Environment{\n" + currentState.toString()+'}';
    }



    /*public void init(int width, int length) {
        System.out.printf("percepts: " + width, length);
        currentState = new State(width, length);
    }

    public State getNextState(Coordinates movedfrom, Coordinates movedto){
        //currentState = currentState.moveKnight(movedfrom, movedto);
        return currentState;
    }
*/
/*    /**
     * @return the current state of the environment


    public State getCurrentState() {
        return currentState;
    }

 */

}


