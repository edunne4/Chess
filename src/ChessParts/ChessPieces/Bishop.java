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
     * @param currentPos, the position the chess piece is on the board
     * @return ArrayList of all the legal moves
     */
    @Override
    public List<Square> getLegalMoves(Square currentPos, ChessBoard board) {
        List<Square> legalMoves = new ArrayList<>();

        //check up and to the right
        legalMoves.addAll(checkDiagonal(currentPos, board, 1,1));
        //check down and to the right
        legalMoves.addAll(checkDiagonal(currentPos, board, -1,1));
        //check down and to the left
        legalMoves.addAll(checkDiagonal(currentPos, board, -1,-1));
        //check up and to the left
        legalMoves.addAll(checkDiagonal(currentPos, board, 1,-1));


        return legalMoves;
    }

    private List<Square> checkDiagonal(Square currentPos, ChessBoard board, int rowDirection, int colDirection){
        List<Square> diagSquares = new ArrayList<>();
        int col = currentPos.getCol();
        int row = currentPos.getRow();
        for (int i = 0; i < DIRECTIONS; i++) {
            Square diagSquare = board.getSquareAt(row + i*rowDirection, col + i*colDirection);
            //check if it's not null
            if (diagSquare == null){
                break;
            }
            //check if there's a piece there
            if(!diagSquare.isEmpty()){
                //if it is on the other team, include that space
                if(diagSquare.getCurrentPiece().getTeam() != this.team){
                    diagSquares.add(diagSquare);
                }
                break;
            }
            diagSquares.add(diagSquare);
        }

        return diagSquares;
    }

    @Override
    public String toString() {
        return "B" + team.toString().substring(0,1);
    }
}