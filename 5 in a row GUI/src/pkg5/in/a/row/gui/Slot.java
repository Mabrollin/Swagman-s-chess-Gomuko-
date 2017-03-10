/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg5.in.a.row.gui;

import javafx.scene.control.Button;

/**
 *
 * @author MARBRO02
 */
public class Slot extends Button {

    public static final double SIZE = 30;
    private final int y;
    private final int x;

    Slot(int x, int y) {

        super(" ");
        this.x = x;
        this.y = y;
        setPrefSize(SIZE, SIZE);
        setLayoutX(SIZE * x);
        setLayoutY(SIZE * y);
    }

    int[] getCoor() {
        return new int[]{x, y};
    }

}
