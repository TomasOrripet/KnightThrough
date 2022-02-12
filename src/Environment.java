import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Environment {
    List<List<Integer>> whitePieces, blackPieces;
    State state;
    String role;
    int width;
    int length;
    ArrayList<List<Integer>> allMoves;
    ArrayList<State> stateMoves;

    public Environment(int width, int length, String role){
        this.state = new State(width, length, role);
        this.whitePieces = state.whitePieces;
        this.blackPieces = state.blackPieces;
        this.role = role;
        this.width = state.width;
        this.length = state.length;
    }

    public State movePiece(List<Integer> move){
        List<List<Integer>> tempWhitePieces = new ArrayList<List<Integer>>();
        List<List<Integer>> tempBlackPieces = new ArrayList<List<Integer>>();
        tempWhitePieces.addAll(whitePieces);
        tempBlackPieces.addAll(blackPieces);
        if(move != null){
            int[] moveArr = move.stream().mapToInt(i->i).toArray();
            int x1 = moveArr[0], y1 = moveArr[1], x2 = moveArr[2], y2 = moveArr[3];
            //System.out.println("Checking for: "+Arrays.asList(x1, y1));
            //System.out.println("x2 y2: "+Arrays.asList(x2, y2));
            if(whitePieces.contains(Arrays.asList(x1, y1))){
                // if there is a diagonal capture taking place then we must remove the captured
                // piece from the respective list
                if(blackPieces.contains(Arrays.asList(x2, y2))){
                    tempBlackPieces.remove(Arrays.asList(x2,y2));
                }
                tempWhitePieces.remove(Arrays.asList(x1,y1));
                tempWhitePieces.add(Arrays.asList(x2,y2));
            }else{
                // if there is a diagonal capture taking place then we must remove the captured
                // piece from the respective list
                if(whitePieces.contains(Arrays.asList(x2, y2))){
                    tempWhitePieces.remove(Arrays.asList(x2,y2));
                }
                tempBlackPieces.remove(Arrays.asList(x1,y1));
                tempBlackPieces.add(Arrays.asList(x2,y2));
            }
            State returnState = new State(width, length, role);
            whitePieces = tempWhitePieces;
            blackPieces = tempBlackPieces;
            returnState.blackPieces = tempBlackPieces;
            returnState.whitePieces = tempWhitePieces;
            return returnState;
        }else{
            return null;
        }
    }

    public ArrayList<List<Integer>> getAvailableMoves(){
    //public ArrayList<State> getAvailableMoves(){
        //ArrayList<List<Integer>> temp = new ArrayList<List<Integer>>();

        allMoves = null;
        stateMoves = null;
        if(role == "white"){
            whitePieces.forEach((temp)->{
                int[] intTemp = temp.stream().mapToInt(i->i).toArray();
                int[] tempInt = new int[4];
                //System.out.println("Position being evaluated: "+Arrays.toString(intTemp));
                if(allMoves != null){
                //if(stateMoves != null){
                    allMoves.addAll(availableMovesWhite(intTemp));
                    //stateMoves.addAll(availableMovesWhite(intTemp));
                }else{
                    allMoves = availableMovesWhite(intTemp);
                    //stateMoves = availableMovesWhite(intTemp);
                }
            });
            return allMoves;
            //return stateMoves;
        }else{
            blackPieces.forEach((temp)->{
                int[] intTemp = temp.stream().mapToInt(i->i).toArray();
                int[] tempInt = new int[4];
                //System.out.println("Position being evaluated: "+Arrays.toString(intTemp));
                if(allMoves != null){
                //if(stateMoves != null){
                    //stateMoves.addAll(availableMovesBlack(intTemp));
                    allMoves.addAll(availableMovesBlack(intTemp));
                }else{
                    //stateMoves = availableMovesBlack(intTemp);
                    allMoves = availableMovesBlack(intTemp);
                }
            });
            return allMoves;
            //return stateMoves;
        }
    }

    public ArrayList<List<Integer>> availableMovesWhite(int[] position){
    //public ArrayList<State> availableMovesWhite(int[] position){
        System.out.println("Starting position: ("+ position[0] + ", "+ position[1] + ")");
        // all possible moves from a given position
        ArrayList<List<Integer>> moves = new ArrayList<List<Integer>>();
        ArrayList<State> states = new ArrayList<State>();


        // can you move up two and one to the right?
        if(!whitePieces.contains(Arrays.asList(position[0]+1,position[1]+2)) && !blackPieces.contains(Arrays.asList(position[0]+1,position[1]+2)) && (0 < position[0]+1 && position[0]+1 <= width) && (0 < position[1]+2 && position[1]+2 <= length)){

            moves.add(Arrays.asList(position[0], position[1], position[0]+1,position[1]+2));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+1,position[1]+2)));
        }

        // can you move up two and one to the left?
        if(!whitePieces.contains(Arrays.asList(position[0]-1,position[1]+2)) && !blackPieces.contains(Arrays.asList(position[0]-1,position[1]+2)) && (0 < position[0]-1 && position[0]-1 <= width) && (0 < position[1]+2 && position[1]+2 <= length)){
            //System.out.println("You can move this guy up two and to the left");
            moves.add(Arrays.asList(position[0], position[1], position[0]-1,position[1]+2));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-1,position[1]+2)));
        }

        // can you move two to the left and one up?
        if(!whitePieces.contains(Arrays.asList(position[0]-2,position[1]+1)) && !blackPieces.contains(Arrays.asList(position[0]-2,position[1]+1)) && (0 < position[0]-2 && position[0]-2 <= width) && (0 < position[1]+1 && position[1]+1 <= length)){
            //System.out.println("You can move this guy up one and two to the left");
            moves.add(Arrays.asList(position[0], position[1], position[0]-2,position[1]+1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-2,position[1]+1)));
        }

        // can you move two the right and one up?
        if(!whitePieces.contains(Arrays.asList(position[0]+2,position[1]+1)) && !blackPieces.contains(Arrays.asList(position[0]+2,position[1]+1)) && (0 < position[0]+2 && position[0]+2 <= width) && (0 < position[1]+1 && position[1]+1 <= length)){
            //System.out.println("You can move this guy up one and two to the right");
            moves.add(Arrays.asList(position[0], position[1], position[0]+2,position[1]+1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+2,position[1]+1)));
        }

        // can you attack an enemy diagonally to the right?
        if(blackPieces.contains(Arrays.asList(position[0]+1,position[1]+1))){
            //System.out.println("You can attack diagonally to the right");
            moves.add(Arrays.asList(position[0], position[1], position[0]+1,position[1]+1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+1,position[1]+1)));
        }

        // can you attack an enemy diagonally to the left?
        if(blackPieces.contains(Arrays.asList(position[0]-1,position[1]+1))){
            //System.out.println("You can attack diagonally to the left");
            moves.add(Arrays.asList(position[0], position[1], position[0]-1,position[1]+1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-1,position[1]+1)));
        }
        //return states;
        return moves;
    }

    public ArrayList<List<Integer>> availableMovesBlack(int[] position){
    //public ArrayList<State> availableMovesBlack(int[] position){
        //System.out.println("Starting position: ("+ position[0] + ", "+ position[1] + ")");
        // all possible moves from a given position
        ArrayList<List<Integer>> moves = new ArrayList<List<Integer>>();
        ArrayList<State> states = new ArrayList<State>();
        // can you move down two and one to the right?
        if(!whitePieces.contains(Arrays.asList(position[0]+1,position[1]-2)) && !blackPieces.contains(Arrays.asList(position[0]+1,position[1]-2)) && (0 < position[0]+1 && position[0]+1 <= width) && (0 < position[1]-2 && position[1]-2 <= length)){
            //System.out.println("You can move this guy up two and to the right");
            moves.add(Arrays.asList(position[0], position[1], position[0]+1,position[1]-2));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+1,position[1]-2)));
        }

        // can you move down two and one to the left?
        if(!whitePieces.contains(Arrays.asList(position[0]-1,position[1]-2)) && !blackPieces.contains(Arrays.asList(position[0]-1,position[1]-2)) && (0 < position[0]-1 && position[0]-1 <= width) && (0 < position[1]-2 && position[1]-2 <= length)){
            //System.out.println("You can move this guy up two and to the left");
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-1,position[1]-2)));
            moves.add(Arrays.asList(position[0], position[1], position[0]-1,position[1]-2));
        }

        // can you move two to the left and one down?
        if(!whitePieces.contains(Arrays.asList(position[0]-2,position[1]-1)) && !blackPieces.contains(Arrays.asList(position[0]-2,position[1]-1)) && (0 < position[0]-2 && position[0]-2 <= width) && (0 < position[1]+1 && position[1]-1 <= length)){
            //System.out.println("You can move this guy up one and two to the left");
            moves.add(Arrays.asList(position[0], position[1], position[0]-1,position[1]-2));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-2,position[1]-1)));
        }

        // can you move two the right and one down?
        if(!whitePieces.contains(Arrays.asList(position[0]+2,position[1]-1)) && !blackPieces.contains(Arrays.asList(position[0]+2,position[1]-1)) && (0 < position[0]+2 && position[0]+2 <= width) && (0 < position[1]+1 && position[1]-1 <= length)){
            //System.out.println("You can move this guy up one and two to the right");
            moves.add(Arrays.asList(position[0], position[1], position[0]+2,position[1]-1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+2,position[1]-1)));
        }

        // can you attack diagonally to the right?
        if(whitePieces.contains(Arrays.asList(position[0]+1,position[1]-1))){
            //System.out.println("You can attack diagonally to the right");
            moves.add(Arrays.asList(position[0], position[1], position[0]+2,position[1]-1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]+1,position[1]-1)));
        }

        // can you attack diagonally to the left?
        if(whitePieces.contains(Arrays.asList(position[0]-1,position[1]-1))){
            //System.out.println("You can attack diagonally to the left");
            moves.add(Arrays.asList(position[0], position[1], position[0]-1,position[1]-1));
            states.add(movePiece(Arrays.asList(position[0], position[1], position[0]-1,position[1]-1)));
        }
        return moves;
        //return states;
    }
}


