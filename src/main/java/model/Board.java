package model;

import java.util.List;

public class Board {
    private final int boardSize = 17;
    private BoardElements[][] board;

    public Board(){
        board = new BoardElements[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(j%2==0 && i%2==0){
                    board[i][j]=BoardElements.EMPTY_CELL;
                }else {
                    board[i][j]=BoardElements.EMPTY_CELL_FOR_WALL;
                }
                board[0][8] = BoardElements.PLAYER1;
                board[16][8] = BoardElements.PLAYER2;
            }
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public BoardElements[][] getBoard() {
        return board;
    }

    public void setPlayerPosition(Player player){
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(board[i][j].equals(player.getPlayerNumber())){
                    board[i][j] = BoardElements.EMPTY_CELL;
                }
            }
        }
        board[player.getY()][player.getX()] = player.getPlayerNumber();
    }
}
