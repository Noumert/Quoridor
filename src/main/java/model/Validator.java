package model;

public class Validator {
    public static boolean checkMove(int pastY,int pastX,int y,int x,Board board){
        boolean isValid = false;
        if((x >= 0 && x <= 16) && (y >= 0 && y <= 16)
                && (Math.abs(pastX - x) <= 2) && (Math.abs(pastY - y) <= 2)
                && ((pastX != x) ^ (pastY != y))){
            isValid = true;
        }else {
            return false;
        }
        if(pastX != x){
            isValid = !isWall(y, (pastX+x)/2,board);
        }
        if(pastY != y){
            isValid = !isWall((pastY+y)/2, x,board);
        }

        return isValid;
    }

    private static boolean isWall(int y, int x, Board board){
        return BoardElements.WALL.equals(board.getBoard()[y][x]);
    }

    public static boolean isPlayer(int y, int x, Board board){
        return BoardElements.PLAYER1.equals(board.getBoard()[y][x]) || BoardElements.PLAYER2.equals(board.getBoard()[y][x]);
    }
}
