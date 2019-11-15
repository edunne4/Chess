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

import java.util.ArrayList;
import java.util.List;

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
    //All possible directions on a Chess Board
    protected static final int DIRECTIONS = 8;
    public ChessPiece(Team team) {
        this.team = team;
    }

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to
     * @param Position, the position the chess piece is on the board
     * @return ArrayList of all the possible moves
     */
    public abstract List<int[]> getMoves(int[] Position);

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

    public Team getTeam() {
        return team;
    }
}