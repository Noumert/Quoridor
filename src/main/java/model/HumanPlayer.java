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
    public void doStep(int y,int x) {
        move(y,x);
    }

    private void move(int y, int x) {
        int realX = x * 2 - 2;
        int realY = y * 2 - 2;
        if (Validator.checkMove(this.y,this.x,realY,realX,board)) {
            if(Validator.isPlayer(realY,realX,board)){
                jump(realY,realX);
            }else {
                this.x = realX;
                this.y = realY;
                board.setPlayerPosition(this);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void jump(int realY,int realX){
        int difference = 0;
        int testX = this.x;
        int testY = this.y;
        if(this.x != realX){
            difference = realX - this.x;
            testX += (difference*2);
        }
        if(this.y != realY){
            difference = realY - this.y;
            testY += (difference*2);
        }
        if(Validator.checkMove(realY,realX,testY,testX,board)){
            this.y = testY;
            this.x = testX;
        }else{
            throw new IllegalArgumentException();
        }
        board.setPlayerPosition(this);
    }
}
