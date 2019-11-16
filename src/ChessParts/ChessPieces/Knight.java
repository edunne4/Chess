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
 * Class: Knight
 *
 * Description:
 * A Knight implementation, can move in an L shape around the board
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece{
    private boolean lateralMovementOnly = true;
    private boolean diagonalMovementOnly = false;
    private boolean canExtrapolateMovement = true;

    public Knight(Team team) {
        super(team);
    }

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to
     * @param currentSquare, the position the chess piece is on the board, board, the chess board that it is on
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<Square> getLegalMoves(Square currentSquare, ChessBoard board) {
        ArrayList<Square> allMoves = new ArrayList<>(DIRECTIONS);
        ArrayList<Square> allMoves = getAllMoves(currentSquare, board);
        ArrayList<Square> validMoves = getValidMoves(allMoves);
        return  validMoves;
    }
    /**
     * Returns the ArrayList containing the x and y values of the legal moves of a knight,
     * Can move either two horizontal and one vertical, or one vertical and two horizontal.
     * @param Position, the Position of the Chess Piece on the chess board.
     * @return, legalMoves, and array list with all the possible values for its moves
     */
    public List<Square> getMoves(int[] Position){
        int[] option1 = {Position[0] + 1, Position[1] + 2};
        int[] option2 = {Position[0] + 2, Position[1] + 1};
        int[] option3 = {Position[0] + 1, Position[1] - 2};
        int[] option4 = {Position[0] + 2, Position[1] - 1};
        int[] option5 = {Position[0] - 1, Position[1] + 2};
        int[] option6 = {Position[0] - 2, Position[1] + 1};
        int[] option7 = {Position[0] - 1, Position[1] - 2};
        int[] option8 = {Position[0] - 2, Position[1] - 1};
        allMoves.add(option1);
        allMoves.add(option2);
        allMoves.add(option3);
        allMoves.add(option4);
        allMoves.add(option5);
        allMoves.add(option6);
        allMoves.add(option7);
        allMoves.add(option8);
        return  moves;
    }

    /**
     * Method to return all the squares that a piece could potentially move to
     * not minding the bounds of the board or the prescence of other pieces
     * @param currentSquare
     * @param board
     * @return
     */
    private ArrayList<Square> getAllMoves(Square currentSquare, ChessBoard board) {
        ArrayList<Square> allMoves = new ArrayList<>(DIRECTIONS);
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        //Get all diagonals
        Square option1 = board.getSquareAt(row+1,col+1);
        Square option2 = board.getSquareAt(row+1, col-1);
        Square option3 = board.getSquareAt(row-1,col+1);
        Square option4 = board.getSquareAt(row-1,col+1);
        //Add options to arraList
        allMoves.add(option1);
        allMoves.add(option2);
        allMoves.add(option3);
        allMoves.add(option4);
        //Get all non-Diagnols
        Square option5 = board.getSquareAt(row+1,col);
        Square option6 = board.getSquareAt(row-1, col);
        Square option7 = board.getSquareAt(row,col+1);
        Square option8 = board.getSquareAt(row,col-11);
        //Add options to arrayList
        allMoves.add(option5);
        allMoves.add(option6);
        allMoves.add(option7);
        allMoves.add(option8);
        return allMoves;
    }


    @Override
    public String toString() {
        return "N" + team.toString().substring(0,1);
    }
}