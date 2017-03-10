/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5.in.a.row.gui;

import five_in_a_row.game.Game;
import five_in_a_row.game.Mark;
import five_in_a_row.player.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author MARBRO02
 */
public class FXMLDocumentController implements Initializable, Player {

    private static final int SIZE = 11;
    @FXML
    private AnchorPane mainPane;

    private final Slot[][] slots = new Slot[SIZE][SIZE];
    private final Game game = new Game(SIZE);
    private boolean newAction = false;
    private int[] action;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        game.setPlayer(this);

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                slots[x][y] = new Slot(x, y);
                slots[x][y].setOnAction(slot -> {

                    if (game.hasWinner()) {
                        ((Slot) slot.getSource()).disarm();
                    } else {
                        newAction = true;
                        action = ((Slot) slot.getSource()).getCoor();
                        game.next();
                        updateSlots();
                        
                        game.next();
                        updateSlots();
                    }

                });
                mainPane.getChildren().add(slots[x][y]);

            }
        }

        game.start();

    }

    @Override
    public int[] getAction(Game game) {
        newAction = false;
        return getAction();
    }

    @Override
    public Mark getMark() {
        return Mark.X;
    }

    private int[] getAction() {
        return action;
    }

    private void updateSlots() {
        String[][] board = game.getBoardStringData();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                slots[x][y].setText(board[x][y]);
            }
        }
    }

}
