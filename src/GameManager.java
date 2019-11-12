/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/11/19
 * Time: 11:22 AM
 *
 * Project: csci205finalproject
 * Package: PACKAGE_NAME
 * Class: GameManager
 *
 * Description:
 *
 * ****************************************
 */

import ChessParts.ChessBoard;
import ChessParts.ChessPieces.ChessPiece;
import ChessParts.Square;
import ChessParts.Team;

public class GameManager {

    /** Whichever team's turn it is */
    private Team currentTurn = Team.WHITE;

    /** Current board being played on */
    private ChessBoard board;

    /**
     * Move a game piece from its current location to a new location
     * @param currentLocation the Square where the piece that should be moved is located
     * @param newLocation the location of where to move the piece in current location
     * @return the piece that is killed in the new location, if there is one
     */
    public ChessPiece movePiece(Square currentLocation, Square newLocation) {
        //if there is a piece in the new location, kill it
        ChessPiece pieceKilled = newLocation.getCurrentPiece();
        //put the piece moved in the new location
        newLocation.setCurrentPiece(currentLocation.getCurrentPiece());

        //remove piece from old location
        currentLocation.setCurrentPiece(null);

        switchTurn();
        return pieceKilled;
    }

    private Team switchTurn(){
        if (currentTurn == Team.WHITE){
            currentTurn = Team.BLACK;
        }else{
            currentTurn = Team.WHITE;
        }
        return currentTurn;
    }

    public ChessBoard getBoard() {
        return board;
    }
}
