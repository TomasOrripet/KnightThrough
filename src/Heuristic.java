

public class Heuristic implements Heuristics{

    private Environment env;

    Heuristic(Environment env) {
        this.env = env;
    }

    public void init(Environment env) {
        this.env = env;
    }


    public int eval(State s) {
        int h = 0;
        for(int i = 0; i<s.whiteknights.size();i++){
            h+=  Math.pow(1+s.whiteknights.get(i).y,2);
            if(s.whiteknights.get(i).y == (env.length-1)){
                h += 1000000;
            }
        }
        for(int i = 0; i<s.blackknights.size();i++){
            if(s.blackknights.get(i).y <= 2) {
                h -= 1000000;
            }
            h -=  Math.pow(env.length-s.blackknights.get(i).y,2);
        }
        h += (s.whiteknights.size()-s.blackknights.size())*10;
        return h;
    }

}
