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
 * Class: Pawn
 *
 * Description:
 * A pawn implementation, will hold the team its on, and whether or not it has moved,
 * It has 4 movement types. Forward 1, its default moevement, forward 2 if it has not moved, and
 * it can move 1 diagonal forward if there is a chess piece it can kill.
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece{

    private boolean hasMoved = false;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public List<Square> getLegalMoves(Square currentSquare, ChessBoard board) {
        ArrayList<Square> allMoves = getAllMoves(currentSquare, board);
        ArrayList<Square> validMoves = getValidMoves(allMoves);
        return  validMoves;
    }

    private ArrayList<Square> getAllMoves(Square currentSquare, ChessBoard board) {
        ArrayList<Square> allMoves = new ArrayList<>(DIRECTIONS);
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        //White team moves up the board
        if (this.team == team.WHITE) {
            Square option1 = board.getSquareAt(row, col + 1);
            allMoves.add(option1);
            //A pawn can move diagonally to capture a piece
            if (!board.getSquareAt(row + 1, col + 1).isEmpty()) {
                Square option2 = board.getSquareAt(row + 1, col + 1);
                allMoves.add(option2);

            }
            //A pawn can move diagonally to capture a piece
            if (!board.getSquareAt(row - 1, col + 1).isEmpty()) {
                Square option3 = board.getSquareAt(row - 1, col + 1);
                allMoves.add(option3);

            }
            //A pawn can move twice for its first move
            if (!hasMoved) {
                Square option4 = board.getSquareAt(row, col + 2);
                allMoves.add(option4);
            }
        }
        //if the pawn is on the black team it will move down the board
        else {
            Square option5 = board.getSquareAt(row, col - 1);
            allMoves.add(option5);
            if (!board.getSquareAt(row + 1, col - 1).isEmpty()) {
                Square option6 = board.getSquareAt(row + 1, col - 1);
                allMoves.add(option6);

            }
            if (!board.getSquareAt(row - 1, col - 1).isEmpty()) {
                Square option7 = board.getSquareAt(row - 1, col - 1);
                allMoves.add(option7);
                if (!hasMoved) {
                    Square option8 = board.getSquareAt(row, col - 2);
                    allMoves.add(option8);
                }
            }
        }
        return allMoves;
    }

    @Override
    public String toString() {
        return "P" + team.toString().substring(0,1);
    }
}