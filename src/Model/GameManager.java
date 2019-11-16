package Model;/* *****************************************
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
 * Class: Model.GameManager
 *
 * Description:
 *
 * ****************************************
 */

import ChessParts.ChessBoard;
import ChessParts.ChessPieces.ChessPiece;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to control the flow fo the game
 */
public class GameManager {

    /** Whichever team's turn it is */
    private Team currentTurn = Team.WHITE;

    /** Current board being played on */
    private ChessBoard board;

    public GameManager() {
        this.board = new ChessBoard();
    }

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

        //add killed piece to pieces captured
        if(pieceKilled != null) {
            board.capturePiece(pieceKilled);
        }

        switchTurn();
        return pieceKilled;
    }


    /**
     *
     * @param pieceLocation the square chosen to get the moves of the piece located there
     * @return a list of possible move locations for the piece at that location
     * @throws NullPointerException if there is no piece at that location
     */
    public List<Square> getLegalMoves(Square pieceLocation) throws NullPointerException{
        List<int[]> posVectors = pieceLocation.getCurrentPiece().getLegalMoves(new int[]{pieceLocation.getCol(), pieceLocation.getRow()});

        List<Square> squareList = new ArrayList<>();
        for (int[] vec : posVectors) {
            squareList.add(new Square(vec[1], vec[0]));
        }

        //then, check if the squares created are legal in the board
        for (Square pos : squareList) {
            //if it's not even on the board remove it
            if (!board.posIsInBoard(pos.getRow(), pos.getCol())){
                //squareList.remove(pos);
            }
        }

        return squareList;
    }

    /**
     * Helper function to toggle whose turn it is
     * @return the team whose turn it is
     */
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

    public Team getCurrentTurn() {
        return currentTurn;
    }


}
