/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/10/19
 * Time: 12:12 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: ChessPiece
 *
 * Description:
 * An abstract representation of a chess piece that all the different pieces can inherit.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
    //Stores the team of the chess piece
    protected Team team;
    //All possible directions on a Chess Board
    protected static final int DIRECTIONS = 8;
    public ChessPiece(Team team) {
        this.team = team;
    }

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to
     * @param currentPositon, the position the chess piece is on the board, boarf, the chessboard the piece is on
     * @return ArrayList of all the possible moves
     */
    public abstract List<Square> getLegalMoves(Square currentPosition, ChessBoard bo


}