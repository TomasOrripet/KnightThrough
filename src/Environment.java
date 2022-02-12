import java.util.ArrayList;

public class Environment {

    public int width, length;
    public State currentState;

    public Environment(int width, int length, String role) {
        this.width = width;
        this.length = length;
        this.currentState = new State(width, length);
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
}


