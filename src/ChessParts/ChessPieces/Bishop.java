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
 * Class: Bishop
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.awt.image.DirectColorModel;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece{

    public Bishop(Team team) {
        super(team);
    }

    /**
     * Will return an ArrayList with square positions of all the possible that
     * a specific chess piece is allowed to move to
     * @param currentSquare, the position the chess piece is on the board
     * @param  board, the board to check for it's legal positions on
     * @return ArrayList of all the possible moves
     */
    @Override
    public List<Square> getLegalMoves(Square currentSquare, ChessBoard board) {
        List<Square> legalMoves = new ArrayList<>();

        //check up and to the right
        legalMoves.addAll(checkDirection(currentSquare, board, 1,1, DIRECTIONS));
        //check down and to the right
        legalMoves.addAll(checkDirection(currentSquare, board, -1,1, DIRECTIONS));
        //check down and to the left
        legalMoves.addAll(checkDirection(currentSquare, board, -1,-1, DIRECTIONS));
        //check up and to the left
        legalMoves.addAll(checkDirection(currentSquare, board, 1,-1, DIRECTIONS));


        return legalMoves;
    }

//    private List<Square> checkDiagonal(Square currentPos, ChessBoard board, int rowDirection, int colDirection){
//        List<Square> diagSquares = new ArrayList<>();
//        int col = currentPos.getCol();
//        int row = currentPos.getRow();
//        for (int i = 1; i < DIRECTIONS; i++) {
//            Square diagSquare = board.getSquareAt(row + i*rowDirection, col + i*colDirection);
//            //check if it's not null
//            if (diagSquare == null){
//                break;
//            }
//            //check if there's a piece there
//            if(!diagSquare.isEmpty()){
//                //if it is on the other team, include that space
//                if(diagSquare.getCurrentPiece().getTeam() != this.team){
//                    diagSquares.add(diagSquare);
//                }
//                break;
//            }
//            diagSquares.add(diagSquare);
//        }
//
//        return diagSquares;
//    }

    @Override
    public String toString() {
        return "B" + team.toString().substring(0,1);
    }
}