import java.util.LinkedList;
import java.util.Random;

public class MyAgent implements Agent {
    private Random random = new Random();

    private String role; // the name of this agent's role (white or black)
    private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
    private boolean myTurn; // whether it is this agent's turn or not
    private int width, height; // dimensions of the board
    public Environment env;
    public MinMax minmax;



    /*
        init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
    */
    public void init(String role, int width, int height, int playclock) {
        this.role = role;
        this.playclock = playclock;
        System.out.printf(role);
        myTurn = !role.equals("white");
        this.width = width;
        this.height = height;
        this.env = new Environment(width, height, role);
        this.minmax = new MinMax(env, role.equals("white"));


        // TODO: add your own initialation code here

    }

    // lastMove is null the first time nextAction gets called (in the initial state)
    // otherwise it contains the coordinates x1,y1,x2,y2 of the move that the last player did
    public String nextAction(int[] lastMove) {
        if (lastMove != null) {
            int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
            String roleOfLastPlayer;
            if (myTurn && role.equals("white") || !myTurn && role.equals("black")) {
                roleOfLastPlayer = "white";
                env.doAction(new Coordinates((x1-1),(y1-1)), new Coordinates((x2-1),(y2-1)),true);
            } else {
                roleOfLastPlayer = "black";
                env.doAction(new Coordinates((x1-1),(y1-1)), new Coordinates((x2-1),(y2-1)),false);
            }
            System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
            // prints board
            System.out.println(env);
            // prints black and white knights
            System.out.println("black:" + env.currentState.blackknights);
            System.out.println("white: " + env.currentState.whiteknights);

            // TODO: 1. update your internal world model according to the action that was just executed

        }

        // update turn (above that line it myTurn is still for the previous state)
        myTurn = !myTurn;

        if (myTurn) {


            LinkedList<Coordinates> move = minmax.MinMax_Decision(env.currentState);
            System.out.printf("Move: "+move+"\n");

            // TODO: 2. run alpha-beta search to determine the best move



            int x1, y1, x2, y2;
            x1 = move.get(0).x+1;
            y1 = move.get(0).y+1;
            x2 = move.get(1).x+1;
            y2 = move.get(1).y+1;
            return "(move " + x1 + " " + y1 + " " + x2 + " " + y2 + ")";
        } else {
            return "noop";
        }
    }

    // is called when the game is over or the match is aborted
    @Override
    public void cleanup() {
        // TODO: cleanup so that the agent is ready for the next match
    }

}


