/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 12:10 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: Pawn
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.List;

public class Pawn extends ChessPiece{
    private int[] movement = {1,1};
    private boolean lateralMovementOnly = true;
    private boolean canExtrapolateMovement = true;
    private boolean diagonalMovementOnly = false;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public List<int[]> getLegalMoves(int[] Position) {
        return null;
    }

    @Override
    public String toString() {
        return "P";
    }
}