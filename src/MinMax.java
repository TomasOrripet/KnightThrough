import java.util.LinkedList;

public class MinMax{
    Environment env;
    State state;
    Heuristics h ;
    int depth;
    Node root;

    public MinMax(Environment env, Boolean max){
        this.env = env;
        this.depth = 30;
        this.h = new Heuristic1(env);
        this.root = new Node(env.currentState,h.eval(env.currentState));
        this.state = env.currentState;
    }

    public void dosearch(){
        LinkedList<LinkedList<Coordinates>> legalMoves = env.getLegalMoves();
        //shows knight and its legal moves

        for(int i =0; i<legalMoves.size();i++) {
            for (int j=1; j<legalMoves.get(i).size(); j++){
                Coordinates from = legalMoves.get(i).get(0);
                Coordinates to = legalMoves.get(i).get(j);
                State newState;
                if(env.role) {
                    newState = env.updateboard(from.x, from.y, to.x, to.y, "W");
                }
                else {
                    newState = env.updateboard(from.x, from.y, to.x, to.y, "B");
                }
                Node child = new Node(
                        root,
                        newState,
                        from,
                        to,
                        h.eval(newState)
                );
                System.out.printf("node: " + child.state);

            }
            //System.out.printf("knight:" + legalMoves.get(i).get(0) + "\n");
            //System.out.printf("\tLegal moves: " + legalMoves.get(i).subList(1, legalMoves.get(i).size())+"\n");
        }
    }

}
