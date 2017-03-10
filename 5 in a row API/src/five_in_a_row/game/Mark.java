/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package five_in_a_row.game;

/**
 *
 * @author MARBRO02
 */
public enum Mark {
    X, O, EMPTY, UNKNOWN;

    @Override
    public String toString() {

        switch (this) {
            case X:

            case O:
                return name();
            case EMPTY:
            case UNKNOWN:
            default:
                return " ";

        }
    }

    static Mark parseValue(String arg) {
        arg = arg.toLowerCase();
        switch (arg) {
            case "x":
                return X;
            case "o":
                return O;
            case " ":
            case "empty":
            case "remove":
            case "clear":
            case "delete":
                return EMPTY;
            default:
                return UNKNOWN;
        }
    }

}
