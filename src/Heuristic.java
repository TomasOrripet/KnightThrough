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
            if(s.whiteknights.get(i).y == (env.length - 1)){
                return 100;
            }
            h+= s.whiteknights.get(i).y;

        }
        for(int i = 0; i<s.blackknights.size();i++){

            if(s.blackknights.get(i).y == (0)){
                return -100;
            }
            h -=  s.blackknights.get(i).y -(env.length-1);

        }
        h+= (s.whiteknights.size()-s.blackknights.size())*10;
        return h;

    }
}
