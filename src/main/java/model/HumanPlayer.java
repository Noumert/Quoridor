package model;

public class HumanPlayer extends Player {

    public HumanPlayer(int y, int x, Board board, BoardElements playerNumber) {
        super(y, x, board, playerNumber);
    }

    @Override
    public void doStep() {
        //nothing
    }

    @Override
    public void doMove(int y, int x) {
        move(y, x);
    }

    @Override
    public void doStepWall(int y1, int x1, int y2, int x2) {
        putWall(y1, x1, y2, x2);
    }

    private void putWall(int y1, int x1, int y2, int x2) {
        int realX1 = x1 * 2 - 2;
        int realY1 = y1 * 2 - 2;
        int realX2 = x2 * 2 - 2;
        int realY2 = y2 * 2 - 2;
        if(Validator.checkPutWall(realY1,realX1,realY2,realX2)){
            if(realX1 == realX2){
                board.getBoard()[(realY1+realY2)/2][(realX1+realX2)/2] = BoardElements.WALL;
                board.getBoard()[(realY1+realY2)/2][(realX1+realX2)/2+1] = BoardElements.WALL;
                board.getBoard()[(realY1+realY2)/2][(realX1+realX2)/2+2] = BoardElements.WALL;
            }
            if(realY1 == realY2){
                board.getBoard()[(realY1+realY2)/2][(realX1+realX2)/2] = BoardElements.WALL;
                board.getBoard()[(realY1+realY2)/2+1][(realX1+realX2)/2] = BoardElements.WALL;
                board.getBoard()[(realY1+realY2)/2+2][(realX1+realX2)/2] = BoardElements.WALL;
            }
        }
    }

    private void move(int y, int x) {
        int realX = x * 2 - 2;
        int realY = y * 2 - 2;
        if (Validator.checkMove(this.y, this.x, realY, realX, board)) {
            if (Validator.isPlayer(realY, realX, board)) {
                jump(realY, realX);
            } else {
                this.x = realX;
                this.y = realY;
                board.setPlayerPosition(this);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void jump(int realY, int realX) {
        int difference = 0;
        int testX = this.x;
        int testY = this.y;
        if (this.x != realX) {
            difference = realX - this.x;
            testX += (difference * 2);
        }
        if (this.y != realY) {
            difference = realY - this.y;
            testY += (difference * 2);
        }
        if (Validator.checkMove(realY, realX, testY, testX, board)) {
            this.y = testY;
            this.x = testX;
        } else {
            throw new IllegalArgumentException();
        }
        board.setPlayerPosition(this);
    }
}
