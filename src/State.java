import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class State {

    public List<String> temp;
    public ArrayList<List> board;
    public int width, length;
    public LinkedList<Coordinates> black, white;

    public State(int width, int length) {
        this.width = width;
        this.length = length;
        black = new LinkedList<Coordinates>();
        white = new LinkedList<Coordinates>();
        for(int i = 0; i<length-1; i++)
        {
            white.add(new Coordinates(i,0));
            white.add(new Coordinates(i,1));
        }

        for(int i = 0; i<length-1; i++)
        {
            black.add(new Coordinates(i, length - 1));
            black.add(new Coordinates(i, length - 2));
        }
        board = new ArrayList<List>();
        for(int l = 0; l<2;l++ ){
            temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add("W");
            }
            board.add(l, temp);
        }
        for(int l = 2; l<(length-2);l++ ){
            temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add(" ");
            }
            board.add(l, temp);
        }
        for(int l = (length-2); l<length;l++ ){
            temp = new ArrayList<String>();
            for(int w = 0; w < width; w++){
                temp.add("B");
            }
            board.add(l, temp);
        }

    }

    public void updateboard(int x1,int y1,int x2,int y2,String role){
        board.get(y1-1).set(x1-1, " ");
        board.get(y2-1).set(x2-1,role.charAt(0));

    }

    @Override
    public String toString() {
        String str = "";
        for (int i=0; i<board.size();i++){
            str = str + board.get(i)+ "\n";
        }
        return str;
    }
    public static void main(String[] args) {
        State test = new State(7, 7);
        System.out.println(test.board);
        //test.makemove(1,1,2,3);
        System.out.println(test.board);
    }



};


    /*
    List<ArrayList<String>> board;


    public State(int width, int height) {

        // initialize height and width
        this.boardWidth = width;
        this.boardHeight = height;

        // initialize the board
        this.board = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < height; i++) {
            ArrayList<String> row = new ArrayList<>(boardWidth);
            board.add(row);
        }

        // set the pieces on the board
        // start with white
        for (int w = 0; w < width; w++) {
            board.get(height - 1).add("W");
        }
        // finish with black
        for (int w = 0; w < width; w++) {
            board.get(0).add("B");
        }
        // fill up rest
        for (int r = 1; r < height - 1; r++) {
            for (int w = 0; w < width; w++) {
                board.get(r).add(" ");
            }
        }
    }

    public List<ArrayList<String>> getState() {
        return board;
    }

    public void printState() {
        for (int w = 0; w < boardHeight; w++) {
            ArrayList<String> row = board.get(w);
            System.out.println(Arrays.toString(row.toArray()));
        }
    }

    public void update_state(int x1, int y1, int x2, int y2, String player) {
        board.get(x1).get(y1);
    }
}*/


