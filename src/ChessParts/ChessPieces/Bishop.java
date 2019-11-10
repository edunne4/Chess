/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 12:11 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: Bishop
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece{

    public Bishop(Team team) {
        super(team);
    }

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to. As of right now the bishop returns 32 moves that are not
     * all plausible, i.e. will be off board.
     * @param Position, the position the chess piece is on the board
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<int[]> getMoves(int[] Position) {
        int xCoordinate = Position[0];
        int yCoordinate = Position[1];
        ArrayList<int[]> moves = new ArrayList<>(DIRECTIONS);
        for (int i = 0 ; i < DIRECTIONS; i ++){
            int[] option1 = {Position[0] + i, Position[1]};
            int[] option2 = {Position[0] - i, Position[1]};
            int[] option3 = {Position[0], Position[1] + i};
            int[] option4 = {Position[0], Position[1] - i};
            moves.add(option1);
            moves.add(option2);
            moves.add(option3);
            moves.add(option4);
        }
        return moves;
    }

    @Override
    public String toString() {
        return "B" + team.toString().substring(0,1);
    }
}