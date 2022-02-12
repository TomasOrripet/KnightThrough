import java.util.List;
import java.util.Random;

public class MyAgent implements Agent {
    private Random random = new Random();

    private String role; // the name of this agent's role (white or black)
    private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
    private boolean myTurn; // whether it is this agent's turn or not
    private int width, height; // dimensions of the board
    public Environment env;



    /*
        init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
    */
    public void init(String role, int width, int height, int playclock) {
        this.role = role;
        this.playclock = playclock;
        myTurn = !role.equals("white");
        this.width = width;
        this.height = height;
        this.env = new Environment(width,height,role);


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
            } else {
                roleOfLastPlayer = "black";
            }
            System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
            System.out.println(env.getAvailableMoves());
            System.out.println(env.whitePieces);
            System.out.println(env.blackPieces);


            // TODO: 1. update your internal world model according to the action that was just executed

        }

        // update turn (above that line it myTurn is still for the previous state)
        myTurn = !myTurn;

        if (myTurn) {
            System.out.println(env.getAvailableMoves());
            List<Integer> allMoves = env.getAvailableMoves().get(3);//random.nextInt(10));
            int[] move = allMoves.stream().mapToInt(i->i).toArray();
            // TODO: 2. run alpha-beta search to determine the best move
            int index;

            // Here we just construct a random move (that will most likely not even be possible),
            // this needs to be replaced with the actual best move.
            int x1, y1, x2, y2;

            return "(move " + move[0] + " " + move[1] + " " + move[2] + " " + move[3] + ")";
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


