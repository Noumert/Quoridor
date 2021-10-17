package model;

import view.View;

public class Validator {
    public static boolean checkMove(int pastY, int pastX, int y, int x, BoardElements[][] board) {
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

    private static boolean isWall(int y, int x, BoardElements[][] board) {
        return BoardElements.WALL.equals(board[y][x]);
    }

    public static boolean isPlayer(int y, int x, Board board) {
        return BoardElements.PLAYER1.equals(board.getBoard()[y][x]) || BoardElements.PLAYER2.equals(board.getBoard()[y][x]);
    }

    public static boolean checkPutWall(int y1, int x1, int y2, int x2, BoardElements[][] board) {
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

        BoardElements[][] boardTest = copyMatrix(board);


        if (x1 == x2) {
            if (isWall((y1 + y2) / 2, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2, (x1 + x2) / 2 + 1, board) ||
                    isWall((y1 + y2) / 2, (x1 + x2) / 2 + 2, board)) {
                return false;
            }
            boardTest[(y1 + y2) / 2][(x1 + x2) / 2] = BoardElements.WALL;
            boardTest[(y1 + y2) / 2][(x1 + x2) / 2 + 1] = BoardElements.WALL;
            boardTest[(y1 + y2) / 2][(x1 + x2) / 2 + 2] = BoardElements.WALL;
        }
        if (y1 == y2) {
            if (isWall((y1 + y2) / 2, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2 + 1, (x1 + x2) / 2, board) ||
                    isWall((y1 + y2) / 2 + 2, (x1 + x2) / 2, board)) {
                return false;
            }
            boardTest[(y1 + y2) / 2][(x1 + x2) / 2] = BoardElements.WALL;
            boardTest[(y1 + y2) / 2 + 1][(x1 + x2) / 2] = BoardElements.WALL;
            boardTest[(y1 + y2) / 2 + 2][(x1 + x2) / 2] = BoardElements.WALL;
        }

        if (checkPutWallWay(boardTest)) {
            isValid = true;
        } else {
            return false;
        }

        return isValid;
    }

    private static BoardElements[][] copyMatrix(BoardElements[][] board) {
        BoardElements[][] boardTest = new BoardElements[17][17];
        for (int i = 0; i < board.length; i++) {
            boardTest[i] = board[i].clone();
        }
        return boardTest;
    }

    private static boolean checkPutWallWay(BoardElements[][] board) {
        int xP1 = 0;
        int yP1 = 0;
        int xP2 = 0;
        int yP2 = 0;
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                if (board[i][j].equals(BoardElements.PLAYER1)) {
                    yP1 = i;
                    xP1 = j;
                }
                if (board[i][j].equals(BoardElements.PLAYER2)) {
                    yP2 = i;
                    xP2 = j;
                }
            }
        }
        boolean a = checkWayForPlayer(yP1, xP1, copyMatrix(board), 16);
        boolean b = checkWayForPlayer(yP2, xP2, copyMatrix(board), 0);
        if (checkWayForPlayer(yP1, xP1, copyMatrix(board), 16) &&
                checkWayForPlayer(yP2, xP2, copyMatrix(board), 0)) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean checkWayForPlayer(int y, int x, BoardElements[][] board, int aim) {
        boolean isValid = false;
        if (y == aim) {
            return true;
        }
        board[y][x] = BoardElements.CHECKED;
        if (checkMove(y, x, y + 2, x, board)) {
            if (!board[y + 2][x].equals(BoardElements.CHECKED)) {
                board[y + 2][x] = BoardElements.CHECKED;
                isValid = checkWayForPlayer(y + 2, x, board, aim);
                if (isValid) {
                    return isValid;
                }
            }
        }
        if (checkMove(y, x, y - 2, x, board)) {
            if (!board[y - 2][x].equals(BoardElements.CHECKED)) {
                board[y - 2][x] = BoardElements.CHECKED;
                isValid = checkWayForPlayer(y - 2, x, board, aim);
                if (isValid) {
                    return isValid;
                }
            }
        }
        if (checkMove(y, x, y, x + 2, board)) {
            if (!board[y][x + 2].equals(BoardElements.CHECKED)) {
                board[y][x + 2] = BoardElements.CHECKED;
                isValid = checkWayForPlayer(y, x + 2, board, aim);
                if (isValid) {
                    return isValid;
                }
            }
        }
        if (checkMove(y, x, y, x - 2, board)) {
            if (!board[y][x - 2].equals(BoardElements.CHECKED)) {
                board[y][x - 2] = BoardElements.CHECKED;
                isValid = checkWayForPlayer(y, x - 2, board, aim);
                if (isValid) {
                    return isValid;
                }
            }
        }

        return isValid;
    }

}
