package controller;

import model.*;
import view.View;

import java.util.Scanner;

public class MainController {
    private static int startP1X = 8;
    private static int startP1Y = 0;
    private static int startP2X = 8;
    private static int startP2Y = 16;

    public void startGame(){
        View view = new View();
        Board board = new Board();
        Player player1;
        Player player2;
        Scanner scanner = new Scanner(System.in);

        view.printMessage(View.Greet);

        view.printMessage(View.Player1);
        player1 = new HumanPlayer(startP1Y,startP1X,board, BoardElements.PLAYER1);

        view.printMessage(View.Player2);
        player2 = new AIplayer(startP2Y,startP2X,board, BoardElements.PLAYER2);

        board.setPlayerPosition(player1);
        board.setPlayerPosition(player2);
        view.drawBoard(board);

        do {
            view.printMessage(View.TurnP1);
            try {
                String[] input = scanner.nextLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                player1.move(y,x);
            } catch (Exception e){
                view.printMessage(View.Err);
                continue;
            }
            board.setPlayerPosition(player1);

            view.drawBoard(board);
        }while (player1.getY()!=16);
    }
}
