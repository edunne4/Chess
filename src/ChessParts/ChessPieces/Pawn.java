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
 * Class: Pawn
 *
 * Description:
 * A pawn implementation, will hold the team its on, and whether or not it has moved,
 * It has 4 movement types. Forward 1, its default moevement, forward 2 if it has not moved, and
 * it can move 1 diagonal forward if there is a chess piece it can kill.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece{

    private boolean hasMoved = false;

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
        if (this.team == team.WHITE) {
            int[] option1 = {Position[0], Position[1] + 1};
            int[] option2 = {Position[0] + 1, Position[1] + 1};
            int[] option3 = {Position[0] - 1, Position[1] + 1};
            moves.add(option1);
            moves.add(option2);
            moves.add(option3);
            if (!hasMoved) {
                int[] option4 = {Position[0], Position[1] + 2};
                moves.add(option4);
            }
        } else {
            int[] option5 = {Position[0], Position[1] - 1};
            int[] option6 = {Position[0] + 1, Position[1] - 1};
            int[] option7 = {Position[0] - 1, Position[1] - 1};
            if (!hasMoved) {
                int[] option8 = {Position[0], Position[1] - 2};
                moves.add(option8);
            }
            moves.add(option5);
            moves.add(option6);
            moves.add(option7);
        }
        return moves;
    }

    @Override
    public String toString() {
        return "P" + team.toString().substring(0,1);
    }
}