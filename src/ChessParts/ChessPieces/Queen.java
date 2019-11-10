/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 1:42 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts.ChessPieces
 * Class: Queen
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    public Queen(Team team) {
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
        for (int a = 0 ; a < DIRECTIONS; a ++){
            int[] option1 = {Position[0] + a, Position[1]};
            int[] option2 = {Position[0] - a, Position[1]};
            int[] option3 = {Position[0], Position[1] + a};
            int[] option4 = {Position[0], Position[1] - a};
            moves.add(option1);
            moves.add(option2);
            moves.add(option3);
            moves.add(option4);
        }
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
        return "Q" + team.toString().substring(0,1);
    }
}