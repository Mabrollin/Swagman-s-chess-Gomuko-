/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.player;

import five_in_a_row.game.Game;
import five_in_a_row.game.Mark;
import java.util.Scanner;

/**
 *
 * @author MARBRO02
 */
public class User implements Player{

    public Mark mark;

    public User(Mark mark) {
        this.mark = mark;
    }

    @Override
    public int[] getAction(Game game) {
        Scanner scanner = new Scanner(System.in);
        return new int []{scanner.nextInt(), scanner.nextInt()};
       
    }

    @Override
    public Mark getMark() {
        return mark;
    }
    
}
