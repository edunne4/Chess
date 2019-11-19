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
    protected static final int MAX_DISTANCE = 8;

    protected boolean hasMoved = false;
    public ChessPiece(Team team) {
        this.team = team;
    }

    /**
     * Will return an ArrayList with square positions of all the possible that
     * a specific chess piece is allowed to move to
     * @param currentSquare, the position the chess piece is on the board
     * @param  board, the board to check for it's legal positions on
     * @return ArrayList of all the possible moves
     */
    public abstract List<Square> getLegalMoves(Square currentSquare, ChessBoard board);


    protected List<Square> checkDirection(Square currentPos, ChessBoard board, int rowDirec, int colDirec, int distance){
        List<Square> adjacentSquares = new ArrayList<>();
        int col = currentPos.getCol();
        int row = currentPos.getRow();


        for (int i = 1; i < distance; i++) {
            Square nextSquare = board.getSquareAt(row + i*rowDirec, col + i*colDirec);

            //check if it's not null
            if (nextSquare == null){
                break;
            }
            //check if there's a piece there
            if(!nextSquare.isEmpty()){
                //if it is on the other team, include that space
                if(nextSquare.getCurrentPiece().getTeam() != this.team){
                    adjacentSquares.add(nextSquare);
                }
                break; //stop looking in this direction
            }
            adjacentSquares.add(nextSquare);

            if(nextSquare != null){ //if the square is on the board
                if(checkSquare(nextSquare)) { //if the square does not contain someone on in it
                    adjacentSquares.add(nextSquare);
                }
            }
        }


        return adjacentSquares;
    }

    /**
     * Returns true if the given square is not empty or occupied by the opposing team
     * @param square the square to check
     * @return whether or not this chess piece can occupy this square
     */
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

    /**
     * Loops through the allMoves array list and makes a new list containing only the valid moves,
     * i.e is on the board and not blocked by a piece on the same team
     * @param allMoves
     * @return
     */
    public ArrayList<Square> getValidMoves(ArrayList<Square> allMoves) {
        ArrayList<Square> validMoves = new ArrayList<>(MAX_DISTANCE);
        for (Square square : allMoves) {
            if (square != null) {
                if (checkSquare(square)) {
                    validMoves.add(square);
                }
            }
            continue;
        }
        return validMoves;
    }

    public Team getTeam() {
        return team;
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }
}