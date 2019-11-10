/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/10/19
 * Time: 12:11 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: Knight
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece{
    private boolean lateralMovementOnly = true;
    private boolean diagonalMovementOnly = false;
    private boolean canExtrapolateMovement = true;

    public Knight(Team team) {
        super(team);
    }

    /**
     * Returns the ArrayList containing the x and y values of the legal moves of a knight,
     * Can move either two horizontal and one vertical, or one vertical and two horizontal.
     * @param Position, the Position of the Chess Piece on the chess board.
     * @return, legalMoves, and array list with all the possible values for its moves
     */
    public List<int[]> getLegalMoves(int[] Position){
        ArrayList<int[]> legalMoves = new ArrayList<>(DIRECTIONS);
        int[] option1 = {Position[0] + 1, Position[1] + 2};
        int[] option2 = {Position[0] + 2, Position[1] + 1};
        int[] option3 = {Position[0] + 1, Position[1] - 2};
        int[] option4 = {Position[0] + 2, Position[1] - 1};
        int[] option5 = {Position[0] - 1, Position[1] + 2};
        int[] option6 = {Position[0] - 2, Position[1] + 1};
        int[] option7 = {Position[0] - 1, Position[1] - 2};
        int[] option8 = {Position[0] - 2, Position[1] - 1};
        legalMoves.add(option1);
        legalMoves.add(option2);
        legalMoves.add(option3);
        legalMoves.add(option4);
        legalMoves.add(option5);
        legalMoves.add(option6);
        legalMoves.add(option7);
        legalMoves.add(option8);
        return  legalMoves;
    }

    @Override
    public String toString() {
        return "N";
    }
}