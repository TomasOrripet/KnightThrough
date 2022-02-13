public class Heuristic1 implements Heuristics{

    private Environment env;

    Heuristic1(Environment env) {
        this.env = env;
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
        while(s.whiteknights.listIterator().hasNext()){
            if(s.whiteknights.listIterator().next().y == (env.length - 1)){
                return 100;
            }
            h+= s.whiteknights.listIterator().next().y;

        }
        while(s.blackknights.listIterator().hasNext()) {
            if (s.blackknights.listIterator().next().y == 0) {
                return -100;
            }
            h -= s.whiteknights.listIterator().next().y;
        }
        h+= (s.whiteknights.size()-s.whiteknights.size())*10;
        return h;
    }
}
