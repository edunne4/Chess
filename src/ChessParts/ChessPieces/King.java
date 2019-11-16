/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/10/19
 * Time: 12:10 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: King
 *
 * Description:
 * A king piece implementation, the king can move to any adjacent block, any of the 8 directions.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece{
    //How much the King can move on the chess Board
    private int[] movement = {1,1};
    //Says if the King can move anything more special than laterally
    private boolean lateralMovementOnly = true;
    //Says if the Piece can multiply movement
    private boolean canExtrapolateMovement = false;
    private boolean diagonalMovementOnly = false;

    public King(Team team) {
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

        ArrayList<Square> allMoves = getAllMoves(currentSquare, board);
        ArrayList<Square> validMoves = getValidMoves(allMoves);
        return  validMoves;
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

    /**
     * Loops through the allMoves array list and makes a new list containing only the valid moves,
     * i.e is on the board and not blocked by a piece on the same team
     * @param allMoves
     * @return
     */
    private ArrayList<Square> getValidMoves(ArrayList<Square> allMoves) {
        ArrayList<Square> validMoves = new ArrayList<>(DIRECTIONS);
        for (Square square: allMoves){
            if (square != null){
                if (checkSquare(square)){
                    validMoves.add(square);
                }
            }
            continue;
        }
        return validMoves;
    }

    public boolean checkSquare(Square square){
        if (!square.isEmpty()){
            ChessPiece piece = square.getCurrentPiece();
            if (piece.team == this.team){
                return false;
            }
            return true;
        }
        return true;
    }
    @Override
    public String toString() {
        return "K" + team.toString().substring(0,1);
    }
}