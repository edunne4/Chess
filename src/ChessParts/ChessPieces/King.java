/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/10/19
 * Time: 12:10 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: King
 *
 * Description:
 * A king piece implementation, the king can move to any adjacent block, any of the 8 directions.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
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

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to
     * @param Position, the position the chess piece is on the board
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<int[]> getMoves(int[] Position) {
        ArrayList<int[]> moves = new ArrayList<>(DIRECTIONS);
        int[] option1 = {Position[0] + 1, Position[1]};
        int[] option2 = {Position[0] + 1, Position[1] + 1};
        int[] option3 = {Position[0] + 1, Position[1] -1};
        int[] option4 = {Position[0], Position[1] + 1};
        int[] option5 = {Position[0], Position[1] - 1};
        int[] option6 = {Position[0] - 1, Position[1] + 1};
        int[] option7 = {Position[0] - 1, Position[1]};
        int[] option8 = {Position[0] - 1, Position[1] - 1};
        moves.add(option1);
        moves.add(option2);
        moves.add(option3);
        moves.add(option4);
        moves.add(option5);
        moves.add(option6);
        moves.add(option7);
        moves.add(option8);
        return  moves;
    }

    @Override
    public String toString() {
        return "K" + team.toString().substring(0,1);
    }
}