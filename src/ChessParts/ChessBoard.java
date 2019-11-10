/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/10/19
 * Time: 11:51 AM
 *
 * Project: csci205finalproject
 * Package: model
 * Class: ChessBoard
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts;

import ChessParts.ChessPieces.*;

import java.util.Arrays;

public class ChessBoard {
    int BOARD_WIDTH = 8;
    int BOARD_HEIGHT = 8;

    private final Square[][] positions;

    public ChessBoard() {
        //create board
        positions = new Square[BOARD_HEIGHT][BOARD_WIDTH];
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                positions[row][col] = new Square(row, col);
            }
        }

        //set up pieces in starting position
    }

    private void initPieces(){

        //set up white team
        final int WHITE_ROW = 0;
        final int WHITE_PAWN_ROW = 1;
        putPiece(new Rooke(Team.WHITE), WHITE_ROW,0);
        putPiece(new Knight(Team.WHITE), WHITE_ROW, 1);
        putPiece(new Bishop(Team.WHITE), WHITE_ROW, 2);
        putPiece(new King(Team.WHITE), WHITE_ROW, 3);
        putPiece(new Queen(Team.WHITE), WHITE_ROW, 4);
        putPiece(new Knight(Team.WHITE), WHITE_ROW, 6);
        putPiece(new Bishop(Team.WHITE), WHITE_ROW, 5);
        putPiece(new Rooke(Team.WHITE), WHITE_ROW,7);
        for (int col = 0; col < BOARD_WIDTH; col++) {
            putPiece(new Pawn(Team.WHITE), WHITE_PAWN_ROW, col);
        }

        //set up black team
        final int BLACK_ROW = 7;
        final int BLACK_PAWN_ROW = 6;
        putPiece(new Rooke(Team.BLACK), BLACK_ROW,0);
        putPiece(new Knight(Team.BLACK), BLACK_ROW, 1);
        putPiece(new Bishop(Team.BLACK), BLACK_ROW, 2);
        putPiece(new King(Team.BLACK), BLACK_ROW, 3);
        putPiece(new Queen(Team.BLACK), BLACK_ROW, 4);
        putPiece(new Knight(Team.BLACK), BLACK_ROW, 6);
        putPiece(new Bishop(Team.BLACK), BLACK_ROW, 5);
        putPiece(new Rooke(Team.BLACK), BLACK_ROW,7);
        for (int col = 0; col < BOARD_WIDTH; col++) {
            putPiece(new Pawn(Team.BLACK), BLACK_PAWN_ROW, col);
        }




    }

    public void putPiece(ChessPiece newPiece, int row, int col){
        positions[row][col].setCurrentPiece(newPiece);
    }

    public Square getSquareAt(int row, int col){
        return positions[row][col];
    }

    @Override
    public String toString() {
        String strRep = "  a  b  c  d  e  f  g  h \n";
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            strRep += String.valueOf(row+1);
            for (int col = 0; col < BOARD_WIDTH; col++) {
                strRep += " ";
                strRep += positions[row][col].getStringPiece();
            }
            strRep += "\n";
        }

        return strRep;
    }
}
