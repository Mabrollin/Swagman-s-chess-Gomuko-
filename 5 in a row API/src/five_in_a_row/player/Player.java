/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.player;

import five_in_a_row.game.Game;
import five_in_a_row.game.Mark;

/**
 *
 * @author MARBRO02
 */
public interface Player {
    int [] getAction(Game game);

    public Mark getMark();
}
