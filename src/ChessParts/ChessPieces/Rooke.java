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

    @Override
    public List<int[]> getMoves(int[] Position) {
        return null;
    }

    @Override
    public String toString() {

        return "R" + team.toString().substring(0,1);
    }
}