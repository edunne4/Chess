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

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece{
    private int[] movement = {1,1};
    private boolean lateralMovementOnly = true;
    private boolean canExtrapolateMovement = true;
    private boolean diagonalMovementOnly = false;

    public Pawn(Team team) {
        super(team);
    }
    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to
     * @param Position, the position the chess piece is on the board
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<int[]> getMoves(int[] Position) {
        ArrayList<int[]> moves = new ArrayList<>(DIRECTIONS);
        int[] option1 = {Position[0], Position[1] + 1};
        int[] option2 = {Position[0] + 1, Position[1] + 1};
        int[] option3 = {Position[0] - 1, Position[1] + 1};
        moves.add(option1);
        moves.add(option2);
        moves.add(option3);
        return moves;
    }

    @Override
    public String toString() {
        return "P" + team.toString().substring(0,1);
    }
}