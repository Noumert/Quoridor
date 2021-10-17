package model;

import java.util.Random;

public class AIplayer extends Player {

    public AIplayer(int y, int x, Board board, BoardElements playerNumber) {
        super(y, x, board, playerNumber);
    }

    @Override
    public void doStep() {
        Random random = new Random();
        if ((random.nextInt(1, 3) == 1) || walls < 1) {
            int deside = random.nextInt(1, 5);
            switch (deside) {
                case 1 -> move(y, x + 2);
                case 2 -> move(y, x - 2);
                case 3 -> move(y - 2, x);
                case 4 -> move(y + 2, x);
            }
        } else {
            do {
                int y1 = random.nextInt(1, 9);
                int x1 = random.nextInt(1, 9);
                int y2;
                int x2;
                if (random.nextBoolean()) {
                    y2 = y1;
                    x2 = x1 + 1;
                } else {
                    y2 = y1 + 1;
                    x2 = x1;
                }
                try {
                    putWall(y1, x1, y2, x2);
                } catch (Exception e) {
                    continue;
                }
                break;
            } while (true);
        }
    }

    @Override
    public void doMove(int y, int x) {
        //nothing
    }

    @Override
    public void doStepWall(int y1, int x1, int y2, int x2) {
        //nothing
    }

    private void putWall(int y1, int x1, int y2, int x2) {
        int realX1 = x1 * 2 - 2;
        int realY1 = y1 * 2 - 2;
        int realX2 = x2 * 2 - 2;
        int realY2 = y2 * 2 - 2;
        if (Validator.checkPutWall(realY1, realX1, realY2, realX2,board)) {
            if (realX1 == realX2) {
                board.getBoard()[(realY1 + realY2) / 2][(realX1 + realX2) / 2] = BoardElements.WALL;
                board.getBoard()[(realY1 + realY2) / 2][(realX1 + realX2) / 2 + 1] = BoardElements.WALL;
                board.getBoard()[(realY1 + realY2) / 2][(realX1 + realX2) / 2 + 2] = BoardElements.WALL;
            }
            if (realY1 == realY2) {
                board.getBoard()[(realY1 + realY2) / 2][(realX1 + realX2) / 2] = BoardElements.WALL;
                board.getBoard()[(realY1 + realY2) / 2 + 1][(realX1 + realX2) / 2] = BoardElements.WALL;
                board.getBoard()[(realY1 + realY2) / 2 + 2][(realX1 + realX2) / 2] = BoardElements.WALL;
            }
            walls--;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void move(int realY, int realX) {
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
