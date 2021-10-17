package model;

public class Validator {
    public static boolean checkMove(int pastY, int pastX, int y, int x, Board board) {
        boolean isValid = false;
        if ((x >= 0 && x <= 16) && (y >= 0 && y <= 16)
                && (Math.abs(pastX - x) <= 2) && (Math.abs(pastY - y) <= 2)
                && ((pastX != x) ^ (pastY != y))) {
            isValid = true;
        } else {
            return false;
        }
        if (pastX != x) {
            isValid = !isWall(y, (pastX + x) / 2, board);
        }
        if (pastY != y) {
            isValid = !isWall((pastY + y) / 2, x, board);
        }

        return isValid;
    }

    private static boolean isWall(int y, int x, Board board) {
        return BoardElements.WALL.equals(board.getBoard()[y][x]);
    }

    public static boolean isPlayer(int y, int x, Board board) {
        return BoardElements.PLAYER1.equals(board.getBoard()[y][x]) || BoardElements.PLAYER2.equals(board.getBoard()[y][x]);
    }

    public static boolean checkPutWall(int y1, int x1, int y2, int x2, Board board) {
        boolean isValid = false;
        if (x1 > 14 || y1 > 14) {
            return false;
        }
        if (x1 == x2 ^ y1 == y2) {
            isValid = true;
        } else {
            return false;
        }

        if (y2 - y1 <= 2 && x2 - x1 <= 2) {
            isValid = true;
        } else {
            return false;
        }

        if (x1 == x2) {
            if (isWall((y1 + y2) / 2, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2, (x1 + x2) / 2 + 1, board) ||
                    isWall((y1 + y2) / 2, (x1 + x2) / 2 + 2, board)) {
                return false;
            }
        }
        if (y1 == y2) {
            if (isWall((y1 + y2) / 2, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2 + 1, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2 + 2, (x1 + x2) / 2, board)) {
                return false;
            }
        }

        if (checkPutWallWay(board)) {
            isValid = true;
        } else {
            return false;
        }

        return isValid;
    }

    private static boolean checkPutWallWay(Board board) {
        BoardElements[][] boardElements = board.getBoard();
        return true;
    }
}
