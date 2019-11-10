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
    //The x and y movement capabilities of a chess piece
    protected int[] movement;
    //Says if the chess piece can only move laterally
    protected boolean lateralMovementOnly;
    //Says if the chess piece can only move diagonally
    protected boolean diagonalMovementOnly;
    //Says if the x and y capabilities can be made bigger, i.e. the piece can move more than 1 square in
    //that direction
    protected boolean canExtrapolateMovement;
    //Stores the team of the chess piece
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