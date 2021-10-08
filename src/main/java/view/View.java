package view;

import model.Board;
import model.BoardElements;

public class View {
    public static final String Greet ="Welcome to game Quoridor";
    public static final String Player1 ="User 1 is Human or Ai:";
    public static final String Player2 ="User 2 is Human or Ai:";
    public static final String TurnP1 ="User 1 move(x,y):";
    public static final String TurnP2 ="User 2 move(x,y):";
    public static final String Win1 ="User 1 Win!";
    public static final String Win2 ="User 2 Win!";
    public static final String Err ="Invalid move!";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void drawBoard(Board board){
        int boardSize = board.getBoardSize();
        BoardElements[][] boardElements = board.getBoard();
        System.out.println("  1  2 3 4 5  6 7 8  9");
        int numbers = 1;
        for (int i = 0; i < boardSize; i++) {
            if(i%2==0){
                System.out.print(numbers+" ");
                numbers++;
            }else {
                System.out.print("  ");
            }
            for (int j = 0; j < boardSize; j++) {
                switch (boardElements[i][j]){
                    case PLAYER1 -> System.out.print("1");
                    case PLAYER2 -> System.out.print("2");
                    case EMPTY_CELL -> System.out.print("▦");
                    case EMPTY_CELL_FOR_WALL -> System.out.print(" ");
                    case WALL -> {
                        if (i % 2 == 0) {
                            System.out.println("—");
                        } else {
                            System.out.print("|");
                        }
                    }
                }

            }
            System.out.println();
        }
    }

}