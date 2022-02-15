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
            h+=  1+s.whiteknights.get(i).y;
            if(s.whiteknights.get(i).y == (env.length-1)){
                h += 100;
            }
        }
        for(int i = 0; i<s.blackknights.size();i++){
            if(s.blackknights.get(i).y == 0){
                h -= 100;
            }
            h -=  env.length-s.blackknights.get(i).y;

        }
        h += (s.whiteknights.size()-s.blackknights.size())*10;
        return h;

    }
}
