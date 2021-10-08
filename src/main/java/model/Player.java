package model;

public abstract class Player {
    private int x;
    private int y;
    private Board board;
    private BoardElements playerNumber;

    public Player(int y, int x, Board board,BoardElements playerNumber) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.playerNumber= playerNumber;
    }

    public BoardElements getPlayerNumber() {
        return playerNumber;
    }

    public int getY() {
        return y;
    }


    public int getX() {
        return x;
    }

    public void move(int y, int x) {
        int realX = x * 2 - 2;
        int realY = y * 2 - 2;
        if (
                (realX >= 0 && realX <= 16) && (realY >= 0 && realY <= 16)
                        && (Math.abs(this.x - realX) <= 2) && (Math.abs(this.y - realY) <= 2)
                        && ((this.x != realX) || (this.y != realY))
        ) {
            //TODO не прошел ли через стену
            if(true) {
                this.x = realX;
                this.y = realY;
                board.setPlayerPosition(this);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
