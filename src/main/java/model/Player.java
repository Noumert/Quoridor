package model;

public abstract class Player {
    protected int x;
    protected int y;
    protected Board board;
    protected BoardElements playerNumber;
    protected int walls = 10;

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

    public int getWalls() {
        return walls;
    }

    public abstract void doStep();

    public abstract void doMove(int y, int x);

    public abstract void doStepWall(int y1,int x1,int y2,int x2);
}
