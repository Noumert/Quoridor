package controller;

import model.*;
import view.View;

import java.util.Locale;
import java.util.Scanner;

public class MainController {
    private static int startP1X = 8;
    private static int startP1Y = 0;
    private static int startP2X = 8;
    private static int startP2Y = 16;

    public void startGame() {
        do {
            View view = new View();
            Board board = new Board();
            Player player1;
            Player player2;
            Scanner scanner = new Scanner(System.in);

            view.printMessage(View.Greet);

            view.printMessage(View.Player1);
            player1 = getPlayer(startP1Y, startP1X, board, scanner, BoardElements.PLAYER1);

            view.printMessage(View.Player2);
            player2 = getPlayer(startP2Y, startP2X, board, scanner, BoardElements.PLAYER2);

            board.setPlayerPosition(player1);
            board.setPlayerPosition(player2);

            board.getBoard()[5][8] = BoardElements.WALL;
            board.getBoard()[5][6] = BoardElements.WALL;

            view.drawBoard(board);

            do {
                if (doFullTurn(view, board, player1, scanner, View.TurnP1, 16, View.Win1)) break;
                if (doFullTurn(view, board, player2, scanner, View.TurnP2, 0, View.Win2)) break;
            } while (true);

            if (stop(view, scanner)) break;
        }while (true);
    }

    private boolean stop(View view, Scanner scanner) {
        view.printMessage(View.Continue);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("N")) {
            return true;
        }
        return false;
    }

    private boolean doFullTurn(View view, Board board, Player player, Scanner scanner, String turn, int i, String win) {
        do {
            view.printMessage(turn);
            if (doTurn(view, board, player, scanner)) continue;
            view.drawBoard(board);
            break;
        } while (true);
        if (player.getY() == i) {
            view.printMessage(win);
            return true;
        }
        return false;
    }

    private Player getPlayer(int startY,int startX,Board board, Scanner scanner,BoardElements playerNumber) {
        Player player1;
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("h")) {
                    player1 = new HumanPlayer(startY, startX, board, playerNumber);
                    break;
                }
                if (input.equalsIgnoreCase("a")) {
                    player1 = new AIplayer(startY, startX, board, playerNumber);
                    break;
                }
            } catch (Exception ignored) {
            }
        }
        return player1;
    }

    private boolean doTurn(View view, Board board, Player player, Scanner scanner) {
        if (player.getClass() == HumanPlayer.class) {
            try {
                String[] input = scanner.nextLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                player.doStep(y, x);
            } catch (Exception e) {
                view.printMessage(View.Err);
                return true;
            }
            board.setPlayerPosition(player);
        }

        if(player.getClass() == AIplayer.class){
            try {
                player.doStep();
            } catch (Exception e) {
                view.printMessage(View.Err);
                return true;
            }
            board.setPlayerPosition(player);
        }
        return false;
    }
}
