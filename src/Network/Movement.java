/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/2/19
 * Time: 11:36 AM
 *
 * Project: CSCI205FinalProject
 * Package: Network
 * Class: Movement
 *
 * Description:
 * A movement object to pass back and forth between a server and client
 * ****************************************
 */
package Network;

import ChessParts.Square;

public class Movement {
    private Square intitialSquare;
    private Square finalSquare;

    public Movement(Square intitialSquare, Square finalSquare) {
        this.intitialSquare = intitialSquare;
        this.finalSquare = finalSquare;
    }

    public Square getIntitialSquare() {
        return intitialSquare;
    }

    public Square getFinalSquare() {
        return finalSquare;
    }
}