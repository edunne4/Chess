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
     * @param currentPosition, the position the chess piece is on the board, board, the chess board that it is on
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<Square> getLegalMoves(Square currentSquare, ChessBoard board) {
        ArrayList<Square> moves = new ArrayList<>(DIRECTIONS);
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        //Get all diagnols
        Square option1 = board.getSquareAt(row+1,col+1);

        Square option2 = board.getSquareAt(row+1, col-1;
        Square option3 = board.getSquareAt(row-1,col+1);
        Square option4 = board.getSquareAt(row-1,col+1);
        //Add options to arraList
        moves.add(option1);
        moves.add(option2);
        moves.add(option3);
        moves.add(option4);
        //Get all non-Diagnols
        Square option5 = board.getSquareAt(row+1,col);
        Square option6 = board.getSquareAt(row-1, col;
        Square option7 = board.getSquareAt(row,col+1);
        Square option8 = board.getSquareAt(row,col-11);
        //Add options to arrayList
        moves.add(option5);
        moves.add(option6);
        moves.add(option7);
        moves.add(option8);
        return  moves;
    }

    public boolean checkSquare(Square square){
        boolean canMove = false;
        if (square.)
    }
    @Override
    public String toString() {
        return "K" + team.toString().substring(0,1);
    }
}