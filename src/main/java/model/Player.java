package model;

public abstract class Player {
    int x;
    int y;
    Board board;

    public Player(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public int getY() {
        return y;
    }


    public int getX() {
        return x;
    }

    public void move(int x, int y) {
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
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
