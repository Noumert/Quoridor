package view;

import model.Board;
import model.BoardElements;

public class View {
    public static final String Continue = "Continue(N/Any key to continue):";
    public static final String Greet = "Welcome to game Quoridor";
    public static final String Player1 = "User 1 is Human or Ai(h/a):";
    public static final String Player2 = "User 2 is Human or Ai(h/a):";
    public static final String ActionP1 = "User 1 what action(m/w):";
    public static final String ActionP2 = "User 2 what action(m/w)::";
    public static final String MoveP1 = "User 1 move(x,y):";
    public static final String MoveP2 = "User 2 move(x,y):";
    public static final String PutWallP1 = "User 1 put wall(x,y)(x,y):";
    public static final String PutWallP2 = "User 2 put wall(x,y)(x,y):";
    public static final String Win1 = "User 1 Win!";
    public static final String Win2 = "User 2 Win!";
    public static final String Err = "Invalid move!";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void drawBoard(Board board) {
        int boardSize = board.getBoardSize();
        BoardElements[][] boardElements = board.getBoard();
        System.out.println("  1  2   3  4  5  6   7  8  9");
        int numbers = 1;
        for (int i = 0; i < boardSize; i++) {
            if (i % 2 == 0) {
                System.out.print(numbers + " ");
                numbers++;
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j < boardSize; j++) {
                switch (boardElements[i][j]) {
                    case PLAYER1 -> System.out.print("1");
                    case PLAYER2 -> System.out.print("2");
                    case EMPTY_CELL -> System.out.print("▩");
                    case EMPTY_CELL_FOR_WALL -> {
                        if (i % 2 != 0 && j % 2 == 0) {
                            System.out.print(" ");
                        } else {
                            System.out.print("  ");
                        }
                    }
                    case WALL -> {
                        if (i % 2 == 0) {
                            System.out.print("| ");
                        } else {
                            if (j % 2 != 0) {
                                System.out.print("  ");
                            } else {
                                System.out.print("―");
                            }
                        }
                    }
                }

            }
            System.out.println();
        }
    }

}