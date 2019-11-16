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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess board
 */
public class ChessBoard {
    int BOARD_WIDTH = 8;
    int BOARD_HEIGHT = 8;

    /** 2D array of squares representing positions that chess pieces can be at */
    private final Square[][] positions;

    /** Pieces that have been captured by the black team */
    private List<ChessPiece> capturedWhitePieces = new ArrayList<>();

    /** Pieces that have been captured by the white team */
    private List<ChessPiece> capturedBlackPieces = new ArrayList<>();

    /**
     * Explicit constructor to initialize the squares array and set up the starting positions of the game pieces
     */
    public ChessBoard() {
        //create board
        positions = new Square[BOARD_HEIGHT][BOARD_WIDTH];
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                positions[row][col] = new Square(row, col);
            }
        }

        //set up pieces in starting position
        initPieces();
    }

    /**
     * Create and set up positions of chess pieces
     */
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

    /**
     * Place a specific Chess Piece in a specified location
     * @param newPiece the ChessPiece to put there
     * @param row the row to put it in
     * @param col the column to put it in
     */
    public void putPiece(ChessPiece newPiece, int row, int col){
        positions[row][col].setCurrentPiece(newPiece);
    }

    /**
     * Get the square at the specified location
     * @param row the row
     * @param col the column
     * @return the Square object, which may or may not contain a ChessPiece. If its out of bounds of the board, return null
     */
    public Square getSquareAt(int row, int col){
        try {
            return positions[row][col];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Square getSquareAt(String chessPos) throws IllegalArgumentException{

        if(!isStringPosValid(chessPos)){
            throw new IllegalArgumentException("That is not a real chess position");
        }
        char letter = chessPos.charAt(0);
        int col = (int)(letter - 97);
        int row = Character.getNumericValue(chessPos.charAt(1)) - 1;
        return getSquareAt(row, col);
    }

    private boolean isStringPosValid(String potentialPos){
        if (potentialPos.length() != 2){
            return false;
        }
        //check letter in bounds of board
        char letter = potentialPos.charAt(0);
        if(letter < 'a' || letter > 'f'){
            return false;
        }
        //check number in bounds of board
        char num = potentialPos.charAt(1);
        if (num < '1' || num > '8'){
            return false;
        }
        //otherwise it's fine
        return true;
    }

    /**
     * @return a string representation of the board
     */
    @Override
    public String toString() {
        String strRep = "  a  b  c  d  e  f  g  h \n";
        for (int row = BOARD_HEIGHT-1; row >= 0; row--) {
            strRep += String.valueOf(row+1);
            for (int col = 0; col < BOARD_WIDTH; col++) {
                strRep += " ";
                strRep += positions[row][col].getStringPiece();
            }
            strRep += "\n";
        }

        return strRep;
    }

    public boolean posIsInBoard(int row, int col){
        if(row<0 || row >= BOARD_HEIGHT || col < 0 || col >= BOARD_WIDTH){
            return false;
        }
        return true;
    }

    public void capturePiece(ChessPiece pieceKilled) {
        if(pieceKilled.getTeam() == Team.WHITE){
            capturedWhitePieces.add(pieceKilled);
        }else{
            capturedBlackPieces.add(pieceKilled);
        }
    }
}
