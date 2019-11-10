/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/10/19
 * Time: 11:58 AM
 *
 * Project: csci205finalproject
 * Package: model
 * Class: Square
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts;

import ChessParts.ChessPieces.ChessPiece;

/**
 * Representation of a single space on a chess board
 */
public class Square {

    private ChessPiece currentPiece;
    private int xPos = 0;
    private int yPos = 0;

    public Square(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public ChessPiece getCurrentPiece() {
        return currentPiece;
    }
    public void setCurrentPiece(ChessPiece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }

    @Override
    public String toString() {
        //TODO change 97
        return String.format("%c%d", (char)xPos+97, yPos+1);
    }

    public String getStringPiece(){
        if (currentPiece != null){
            return currentPiece.toString();
        }
        return "_|";
    }
}
