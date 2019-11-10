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

import java.util.List;

public class Bishop extends ChessPiece{

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public List<int[]> getMoves(int[] Position) {
        return null;
    }

    @Override
    public String toString() {

        return "B" + team.toString().substring(0,1);
    }
}