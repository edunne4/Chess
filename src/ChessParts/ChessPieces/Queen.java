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

import java.util.List;

public class Queen extends ChessPiece {
    public Queen(Team team) {
        super(team);
    }

    @Override
    public List<int[]> getMoves(int[] Position) {
        return null;
    }

    @Override
    public String toString() {
        return "Q" + team.toString().substring(0,1);
    }
}