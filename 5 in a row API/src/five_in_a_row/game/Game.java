/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.game;

import five_in_a_row.player.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MARBRO02
 */
public class Game {

    private final List<int[]> pushedMoves = new ArrayList<>();
    public final static int NR_TO_WIN = 5;

    Player player1 = new User(Mark.X);
    Player player2 = new Computer(Mark.O, Mark.X);
    Board gameBoard;
    GameEvaluator evaluator;

    Player currentPlayer;

    public static void main(String[] args) {
        Game game = new Game(11);
        game.loop();
    }

    public Game(int size) {
        gameBoard = new Board(size);
        evaluator = new GameEvaluator(gameBoard);
    }

    public List<int[]> getEmptySlots() {
        return gameBoard.getEmptySlots();
    }

    //done
    public boolean hasWinner() {
        return evaluator.hasWinner();
    }

    // funkar för 2
    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    //byt namn?
    private void play(int[] coor, Mark mark) {

        gameBoard.setMark(coor, mark);
    }

    public void pushMove(int[] coor, Mark mark) {
        //check if is empty is already done
        gameBoard.setMark(coor, mark);
        pushedMoves.add(coor);

    }

    public void undoMove() {
        int[] coor = pushedMoves.get(pushedMoves.size() - 1);
        pushedMoves.remove(coor);
        gameBoard.setMark(coor, Mark.EMPTY);
    }

    public void start() {
        currentPlayer = player2;

    }

    public void setPlayer(Player player) {
        player1 = player;
    }

    public void next() {
        play(currentPlayer.getAction(this), currentPlayer.getMark());
        System.out.println(gameBoard.toString());

        switchPlayer();
    }

    private void loop() {
        while (!hasWinner()) {
            next();
        }
    }

    public String[][] getBoardStringData() {
        Mark[][] boardData = gameBoard.getBoardData();
        int size = gameBoard.size();
        String[][] boardStringData = new String[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                boardStringData[x][y] = boardData[x][y].toString();
            }
        }
        return boardStringData;
    }

    public int[] getOpps(Mark mark) {
        return new int[]{
            evaluator.getOpps(2, mark),
            evaluator.getOpps(3, mark),
            evaluator.getOpps(4, mark),
            evaluator.getOpps(5, mark)
        };
    }

    public boolean isWinner(Mark mark) {
        return evaluator.isWinner(mark);
    }

}
