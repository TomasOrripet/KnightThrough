public class Heuristic1 implements Heuristics{

    private Environment env;

    Heuristic1() {
    }

    public void init(Environment env) {
        this.env = env;
    }

    /*
    Heuristic value
    +1 for each space up the board
    (if piece is at y = 3 then +3 points)
    +10 if you can kill a piece
    return 100 if you can win
    -10 if opponent can kill our piece
    return 100 if opponent can win
     */


    public int eval(State s) {
        int h = 0;
        while(s.white.listIterator().hasNext()){
            if(s.white.listIterator().next().y == (s.length - 1)){
                return 100;
            }
            h+= s.white.listIterator().next().y;

        }
        while(s.black.listIterator().hasNext()) {
            if (s.black.listIterator().next().y == 0) {
                return -100;
            }
            h -= s.white.listIterator().next().y;
        }
        h+= (s.white.size()-s.black.size())*10;
        return h;
    }
}
