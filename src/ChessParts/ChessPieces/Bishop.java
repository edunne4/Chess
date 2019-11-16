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
 * Class: Bishop
 *
 * Description:
 * A bishop implementation, can move in any 4 of the diagonal directions as many places as it wants
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece{


    public Bishop(Team team) {
        super(team);
    }

    /**
     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
     * a specific chess piece is allowed to move to. As of right now the bishop returns 32 moves that are not
     * all plausible, i.e. will be off board.
     * @param Position, the position the chess piece is on the board
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<int[]> getMoves(Square square, ChessBoard Board) {
        int row = square.getRow();
        int col = square.getCol();
        ArrayList<Square> moves = new ArrayList<>(DIRECTIONS);
        for (int i = 0 ; i < DIRECTIONS; i ++){
            Square option1 = Board.getSquareAt(row+i,col+i);
            Square option2 = Board.getSquareAt(row+i, col-i;
            Square option3 = Board.getSquareAt(row-i,col+i);
            Square option4 = Board.getSquareAt(row-i,col+i);
            moves.add(option1);
            moves.add(option2);
            moves.add(option3);
            moves.add(option4);
        }
        return moves;
    }

    @Override
    public String toString() {
        return "B" + team.toString().substring(0,1);
    }
}