/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/10/19
 * Time: 12:12 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: ChessPiece
 *
 * Description:
 * An abstract representation of a chess piece that all the different pieces can inherit.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

public abstract class ChessPiece {
    protected int[] movement;
    protected boolean lateralMovementOnly;
    protected boolean canExtrapolateMovement;
    protected boolean diagonalMovementOnly;
    protected Team team;
    public ChessPiece() {
    }

    public int[] getMovement() {
        return movement;
    }

    public boolean isLateralMovementOnly() {
        return lateralMovementOnly;
    }

    public boolean isCanExtrapolateMovement() {
        return canExtrapolateMovement;
    }

    public boolean isDiagonalMovementOnly() {
        return diagonalMovementOnly;
    }
}