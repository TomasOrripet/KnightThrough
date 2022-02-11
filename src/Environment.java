import java.util.ArrayList;

public class Environment {

    public int width, length;
    public State currentState;
    public ArrayList<Coordinates> knights;


    public Environment(int width, int length, String role) {
        this.width = width;
        this.length = length;
        this.currentState = new State(width, length);
        knights = new ArrayList<Coordinates>();
        if (role == "white"){
            for(int i = 0; i<length-1; i++){
                knights.add(new Coordinates(i,0));
                knights.add(new Coordinates(i,1));
            }
        }
        else{
            for(int i = 0; i<length-1; i++) {
                knights.add(new Coordinates(i, length-1));
                knights.add(new Coordinates(i, length - 2));
            }
        }
        System.out.printf("percepts: " + width, length);
    }

    public void updateboard(int y1,int x1,int x2,int y2,String role){
        currentState.updateboard(y1, x1, x2, y2, role);
    }

    public void makemove(int x1, int y1, int x2, int y2){
        knights.remove(new Coordinates(x1,y1));
        knights.add(new Coordinates(x2,y2));
    }

    @Override
    public Str ing toString() {
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


