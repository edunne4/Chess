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
 * Class: KIng
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.List;

public class King extends ChessPiece{
    //How much the King can move on the chess Board
    private int[] movement = {1,1};
    //Says if the King can move anything more special than laterally
    private boolean lateralMovementOnly = true;
    //Says if the Piece can multiply movement
    private boolean canExtrapolateMovement = false;
    private boolean diagonalMovementOnly = false;

    public King(Team team) {
        super(team);
    }

    @Override
    public List<int[]> getLegalMoves(int[] Position) {
        return null;
    }

    @Override
    public String toString() {
        return "K";
    }
}