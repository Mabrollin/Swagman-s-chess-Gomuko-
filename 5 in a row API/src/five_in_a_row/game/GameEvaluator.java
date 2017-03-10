/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.game;

import java.util.Arrays;
import java.util.List;
import static five_in_a_row.game.Game.NR_TO_WIN;

/**
 *
 * @author MARBRO02
 */
public class GameEvaluator {

    Board gameBoard;

    public GameEvaluator(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * // funkar tror jag public int[] getRowEndsOf(Mark mark) { int[] rowEnds
     * = new int[NR_TO_WIN]; List<int[]> markCoors = gameBoard.getSlotsOf(mark);
     *
     * markCoors.forEach(coor -> { int[] newRows = getRowEndsOf(coor); for (int
     * i = 1; i < NR_TO_WIN; i++) { rowEnds[i] += newRows[i]; } }); //remove
     * multible counts for same row rowEnds[1] /= 4; for (int i = 2; i < NR_TO_WIN; i++) {
     * rowEnds[i] /= i;
     * }
     * return rowEnds;
     * }
     * // funkar tror jag
     *
     * private int[] getRowEndsOf(int[] coor) {
     *
     * int[] rowEnds = new int[NR_TO_WIN];
     * Arrays.fill(rowEnds, 0);
     *
     * int posX = coor[0], posY = coor[1];
     * Mark mark = gameBoard.getMark(posX, posY);
     *
     * int[] newArgs;
     *
     * newArgs = getNewRowEndsArgs(getRowParts(posX, posY, mark, 0, 1));
     * rowEnds[newArgs[1]] += newArgs[1];
     * newArgs = getNewRowEndsArgs(getRowParts(posX, posY, mark, 1, 1));
     * rowEnds[newArgs[1]] += newArgs[0];
     * newArgs = getNewRowEndsArgs(getRowParts(posX, posY, mark, 1, 0));
     * rowEnds[newArgs[1]] += newArgs[0];
     * newArgs = getNewRowEndsArgs(getRowParts(posX, posY, mark, 1, 1));
     * rowEnds[newArgs[1]] += newArgs[0];
     *
     * return rowEnds;
     * }
     *
     * private int[] getNewRowEndsArgs(int[] rowParts) {
     *
     * int ends = (rowParts[0] > 0 ? 1 : 0) + (rowParts[1] > 0 ? 1 : 0); int
     * length = Math.abs(rowParts[0]) + Math.abs(rowParts[1]); return new
     * int[]{ends, Math.min(NR_TO_WIN, length)};
     *
     * }
     *
     * /**
     *
     * @param posX
     * @param posY
     * @param mark
     * @param incX
     * @param incY
     * @return length of part from start recursivly to end with increments incX
     * and incY. makes length negativ to signal that the ending is blocked by
     * non empty mark.
     *
     *
     */
// done
    private int getCount(int posX, int posY, Mark mark, int incX, int incY, int count) {

        if (!gameBoard.isInGrid(posX, posY)) {
            //block
            return -count;
        } else if (gameBoard.equalsAt(posX, posY, mark)) {
            //continue
            return getCount(posX + incX, posY + incY, mark, incX, incY, count + 1);

        } else if (gameBoard.equalsAt(posX, posY, Mark.EMPTY)) {
            //open ending
            return count;

        } else {// other mark
            //block
            return -count;

        }
    }

    boolean hasWinner() {

        return isWinner(Mark.X) || isWinner(Mark.O);
    }

    private int[] getRowParts(int posX, int posY, Mark mark, int incX, int incY) {
        int a = getCount(posX, posY, mark, incX, incY, 0);
        int b
                = getCount(posX - incX, posY - incY, mark, -incX, -incY, 0);
        return new int[]{a, b};
    }

    boolean isWinner(Mark mark) {
        List<int[]> markCoors = gameBoard.getSlotsOf(mark);
        int bestRow = 0;
        for (int[] coor : markCoors) {
            bestRow = Math.max(bestRow, getMaxRowWith(coor));
        }
        return bestRow >= 5;
    }

    private int getMaxRowWith(int[] coor) {
        int posX = coor[0], posY = coor[1];
        Mark mark = gameBoard.getMark(posX, posY);
        int row = 0;

        row = Math.max(row, connect(getRowParts(posX, posY, mark, 0, 1)));
        row = Math.max(row, connect(getRowParts(posX, posY, mark, 1, 0)));
        row = Math.max(row, connect(getRowParts(posX, posY, mark, 1, 1)));
        row = Math.max(row, connect(getRowParts(posX, posY, mark, 1, -1)));

        return row;
    }

    private int connect(int[] rowParts) {
        return Math.abs(rowParts[0]) + Math.abs(rowParts[1]);
    }

    int getOpps(int rowSize, Mark mark) {
        int opps = 0;
        opps += getOpps(rowSize, mark, 1, 0); // Horizontal
        opps += getOpps(rowSize, mark, 0, 1); // Vertical
        opps += getOpps(rowSize, mark, 1, 1); // left-Diagonal
        opps += getOpps(rowSize, mark, 1, -1); // right-Diagonal
        return opps;
    }

    private int getOpps(int rowSize, Mark mark, int incX, int incY) {
        int opps = 0;
        for (int x = 0; x < gameBoard.size() - incX * (rowSize + 2); x++) {
            for (int y = (incY == -1 ? 1 : 0) * (rowSize + 2); y < gameBoard.size() - (incY == 1 ? 1 : 0) * (rowSize + 2); y++) {
                boolean isOpp = true;
                boolean first, last;
                int index = 0;
                if (first = gameBoard.equalsAt(x, y, Mark.EMPTY)) {
                    index++;
                }

                for (int i = index; i < rowSize; i++) {
                    isOpp = isOpp && gameBoard.equalsAt(x + incX * i, y + incY * i, mark);
                }
                last = gameBoard.equalsAt(x + incX * (rowSize + 2), y + incY * (rowSize + 2), Mark.EMPTY);

                if (isOpp) {
                    opps += (first ? 1 : 0) + (last ? 1 : 0);
                }
            }
        }
        return opps;
    }

}
