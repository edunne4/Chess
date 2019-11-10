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
 * Class: Castle
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

public class Rooke extends ChessPiece {
    private int[] movement = {1,1};
    private boolean lateralMovementOnly = true;
    private boolean canExtrapolateMovement = true;
    private boolean diagonalMovementOnly = false;

    public Rooke() {
    }
}