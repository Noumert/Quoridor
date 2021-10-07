package view;

import model.BoardElements;

public class View {
    public static final String Greet ="Welcome to game Quoridor";
    public static final String User1 ="User 1 is Human or Ai:";
    public static final String User2 ="User 2 is Human or Ai:";
    public static final String MoveU1 ="User 1 move:";
    public static final String MoveU2 ="User 2 move:";
    public static final String Win1 ="User 1 Win!";
    public static final String Win2 ="User 2 Win!";
    public static final String Err ="Invalid move!";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void drawBoard(BoardElements[][] board){

    }

}