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
 * Class: Rooke
 *
 * Description:
 * A rooke piece that is allowed to move in 4 lateral directions as many spaces as it wants
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Rooke extends ChessPiece {
    private int[] movement = {1,1};
    private boolean lateralMovementOnly = true;
    private boolean diagonalMovementOnly = false;
    private boolean canExtrapolateMovement = true;
    private static final int POSSIBLEMOVES = 8;

    public Rooke(Team team) {
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
        int xCoordinate = Position[0];
        int yCoordinate = Position[1];
        ArrayList<int[]> moves = new ArrayList<>(DIRECTIONS);
        for (int i = 0; i < DIRECTIONS - xCoordinate; i ++){
            int[] option = {Position[0] + i, Position[1]};
            moves.add(option);
            }
        for (int j = 0; j < xCoordinate; j ++){
            int[] option = {Position[0] - j, Position[1]};
            moves.add(option);
        }
        for (int k = 0; k < DIRECTIONS - yCoordinate; k ++){
            int[] option = {Position[0], Position[1] + k};
            moves.add(option);
        }
        for (int l = 0; l < yCoordinate; l ++){
            int[] option = {Position[0], Position[1] - l};
            moves.add(option);
        }
        return moves;
    }

    @Override
    public String toString() {

        return "R" + team.toString().substring(0,1);
    }
}