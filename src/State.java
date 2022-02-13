import java.util.ArrayList;
import java.util.List;


public class State {

    //public List<String> temp;
    public ArrayList<List<String>> board;
    public ArrayList<Coordinates> whiteknights, blackknights;

    public State(int width, int length) {
        board = new ArrayList<List<String>>();
        whiteknights = new ArrayList<Coordinates>();
        blackknights = new ArrayList<Coordinates>();

        // Make list of Coordinate for White and black knights
        for(int i = 0; i<width; i++){
            whiteknights.add(new Coordinates(i,0));
            whiteknights.add(new Coordinates(i,1));
        }

        for(int i = 0; i<width; i++) {
            blackknights.add(new Coordinates(i, length - 1));
            blackknights.add(new Coordinates(i, length - 2));
        }

        // create the initial board and add knights
        for(int l = 0; l<2;l++ ){
            ArrayList<String> temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add("W");
            }
            board.add(l, temp);
        }
        for(int l = 2; l<(length-2);l++ ){
            ArrayList<String> temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add(" ");
            }
            board.add(l, temp);
        }
        for(int l = (length-2); l<length;l++ ){
            ArrayList<String> temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add("B");
            }
            board.add(l, temp);
        }

    }

    public State(ArrayList<List<String>> board, ArrayList<Coordinates> white, ArrayList<Coordinates> black){
        // updates state from move played by player of agent
        this.whiteknights = white;
        this.blackknights = black;
        this.board = board;
    }

    public boolean isEmpty(int x,int y){
        if (board.get(y).get(x) == " " ){
            return true;
        }
        return false;

    }
    public boolean canAttack(int x, int y, String role){
        if (board.get(y).get(x) == role ) {
            return true;
        }
        return false;
    }

    public ArrayList<List<String>> updateBoard(int x1,int y1,int x2,int y2,String role){
        // moves knight from original position two new position
        board.get(y1-1).set(x1-1, " ");
        board.get(y2-1).set(x2-1, role);
        return board;

    }

    @Override
    public String toString() {
        String str = "";
        for (int i=0; i<board.size();i++){
            str = str + board.get(i)+ "\n";
        }
        return str;
    }
}