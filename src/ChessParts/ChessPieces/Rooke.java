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
 * Class: Rooke
 *
 * Description:
 * A rooke piece that is allowed to move in 4 lateral directions as many spaces as it wants
 * ****************************************
 */
package ChessParts.ChessPieces;

import ChessParts.ChessBoard;
import ChessParts.Square;
import ChessParts.Team;

import java.util.ArrayList;
import java.util.List;

public class Rooke extends ChessPiece {
    private int[] movement = {1,1};
    private boolean lateralMovementOnly = true;
    private boolean diagonalMovementOnly = false;
    private boolean canExtrapolateMovement = true;
    private static final int POSSIBLEMOVES = 8;

    public Rooke(Team team) {
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



        return legalMoves;
    }


    private List<Square> checkDirection(Square currentPos, ChessBoard board, boolean isCol, int dir){
        List<Square> adjacentSquares = new ArrayList<>();
        int col = currentPos.getCol();
        int row = currentPos.getRow();

        //if we're checking columns, don't add anything to the row
        int rowMultiplier = isCol ? 0 : dir;
        //if we're not checking columns, don't add anything to the column
        int colMultiplier = isCol ? dir : 0;


        for (int i = 1; i < DIRECTIONS; i++) {
            Square nextSquare = board.getSquareAt(row + i*rowMultiplier, col + i*colMultiplier);

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

//    /**
//     * Will return an ArrayList with integer arrays of all the possible x y coordinates that
//     * a specific chess piece is allowed to move to
//     * @param Position, the position the chess piece is on the board
//     * @return ArrayList of all the possible moves
//     */
//    @Override
//    public List<int[]> getLegalMoves(int[] Position) {
//        int xCoordinate = Position[0];
//        int yCoordinate = Position[1];
//        ArrayList<int[]> moves = new ArrayList<>(DIRECTIONS);
//        for (int i = 0; i < DIRECTIONS - xCoordinate; i ++){
//            int[] option = {Position[0] + i, Position[1]};
//            moves.add(option);
//            }
//        for (int j = 0; j < xCoordinate; j ++){
//            int[] option = {Position[0] - j, Position[1]};
//            moves.add(option);
//        }
//        for (int k = 0; k < DIRECTIONS - yCoordinate; k ++){
//            int[] option = {Position[0], Position[1] + k};
//            moves.add(option);
//        }
//        for (int l = 0; l < yCoordinate; l ++){
//            int[] option = {Position[0], Position[1] - l};
//            moves.add(option);
//        }
//        return moves;
//    }

    @Override
    public String toString() {

        return "R" + team.toString().substring(0,1);
    }
}