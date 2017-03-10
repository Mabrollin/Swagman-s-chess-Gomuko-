/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author MARBRO02
 */
public class Board {

    /**
     *
     * @return board data
     */
    Mark[][] getBoardData() {
        return boardData;
    }

    private final Mark boardData[][];

    boolean isInGrid(int posX, int posY) {
        return posX >= 0
                && posX < size()
                && posY >= 0
                && posY < size();
    }

    Mark getMark(int posX, int posY) {
        return boardData[posX][posY];
    }
//done

    void setMark(int[] coor, Mark mark) {
        setMark(coor[0], coor[1], mark);
    }

//done
    boolean equalsAt(int posX, int posY, Mark mark) {

        return getMark(posX, posY).equals(mark);
    }

    //funkar 
    boolean isEmptySlot(int x, int y) {
        return equalsAt(x, y, Mark.EMPTY);
    }

    //tror jag funkar
    List<int[]> getEmptySlots() {
        return getSlotsOf(Mark.EMPTY);
    }

    List<int[]> getSlotsOf(Mark mark) {
        List<int[]> coors = new ArrayList<>();
        for (int x = 0; x < size(); x++) {
            for (int y = 0; y < size(); y++) {
                if (equalsAt(x, y, mark)) {
                    coors.add(new int[]{x, y});
                }
            }
        }
        return coors;
    }

    /**
     *
     * @param size create board of size*size and fills it with "empty" data
     */
    Board(int size) {
        boardData = new Mark[size][size];
        for (Mark[] row : boardData) {
            Arrays.fill(row, Mark.EMPTY);
        }
    }

    /**
     *
     * @param x
     * @param y
     * @param mark
     */
    void setMark(int x, int y, Mark mark) {

        boardData[x][y] = mark;
    }
//bra

    int size() {
        return boardData.length;
    }
//bra

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            return Arrays.deepEquals(boardData, boardData);
        } else {
            return false;
        }
    }
//bra

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(boardData);
    }
//bra

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        //row
        for (int y = size() - 1; y >= 0; y--) {

            //create "row overline"
            sb.append(" ");
            for (int i = 0; i < size(); i++) {
                sb.append("- ");
            }

            sb.append("\n");

            // contentRow
            sb.append("|");
            for (int x = 0; x < size(); x++) {
                sb.append(boardData[x][y].toString()).append("|");
            }

            sb.append("\n");
        }

        // last "underline"
        sb.append(" ");
        for (int i = 0; i < size(); i++) {
            sb.append("- ");
        }

        return sb.toString();
    }

}
