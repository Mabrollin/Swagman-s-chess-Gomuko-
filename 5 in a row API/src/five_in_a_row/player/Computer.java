/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.player;

import five_in_a_row.game.Board;
import five_in_a_row.game.Game;
import five_in_a_row.game.Mark;

/**
 *
 * @author MARBRO02
 */
public class Computer implements Player {

    private final Mark mark;
    private final Mark oppMark;

    public Computer(Mark mark, Mark oppMark) {
        this.mark = mark;
        this.oppMark = oppMark;
    }
//bra

    @Override
    public int[] getAction(Game game) {

        return getBestEvalMove(2, game);
    }
//bra

    @Override
    public Mark getMark() {
        return mark;
    }

    private final int[] rowValues = {0, 1, 5, 100};

    private int evaluateState(Game game, Mark mark) {
        if(game.isWinner(mark))
            return Integer.MAX_VALUE;
        int[] rows = game.getOpps(mark);
        int value = 0;
        for (int i = 1; i < 4; i++) {
            value += rowValues[i] * rows[i];
        }
        return value;
    }

    //bryt bort board här också, lägg till push och pop av moves.
    private int minimax(Game game, int alpha, int beta, int depth, boolean searchMax) {
        if (depth <= 0) {
            //utifrån vem ska man evaluera!?
            return evaluateState(game, searchMax ? oppMark : mark);
        } else if (searchMax) {
            int bestValue = Integer.MIN_VALUE;
            for (int[] possibleMove : game.getEmptySlots()) {

                game.pushMove(possibleMove, mark);
                bestValue = Math.max(bestValue, minimax(game, alpha, beta, depth - 1, !searchMax));
                game.undoMove();
                //alpha pruning, beta cut-off
                alpha = Math.max(alpha, bestValue);
                if (beta <= alpha) {
                    break;
                }

            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int[] possibleMove : game.getEmptySlots()) {

                game.pushMove(possibleMove, oppMark);
                bestValue = Math.min(bestValue, minimax(game, alpha, beta, depth - 1, !searchMax));
                game.undoMove();
                //beta pruning, alpha cutt-off
                beta = Math.min(beta, bestValue);
                if (beta <= alpha) {
                    break;
                }

            }
            return bestValue;
        }
    }

    //bryt bort board, använd possible moves istället
    private int[] getBestEvalMove(int depth, Game game) {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
        for (int[] possibleMove : game.getEmptySlots()) {

            game.pushMove(possibleMove, mark);
            int newValue = minimax(game, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, false);
            game.undoMove();
            if (newValue > bestValue) {
                bestValue = newValue;
                bestMove[0] = possibleMove[0];
                bestMove[1] = possibleMove[1];
            }

        }
        return bestMove;
    }

    // ful men funkar
    private Mark otherMark(Mark mark) {
        return mark.equals(this.mark) ? this.oppMark : this.mark;
    }
}
