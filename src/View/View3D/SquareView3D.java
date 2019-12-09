/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ryan Bailis
 * Section: MWF 11am
 * Instructor: Professor Brian King
 * Date: 11/24/19
 * Time: 12:51 PM
 *
 * Project: csci205FinalProject
 * Package: SinglePlatform.View.View3D.STLParser
 * Class: BoardSquare3DView
 *
 * Description:
 *
 * ****************************************
 */
package View.View3D;

import View.BoardView;
import View.PieceEnum;
import View.SquareView;
import View.View3D.PieceView3D;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class SquareView3D extends SquareView {

    public static int SQUARE_SIZE = 100;
    public static int SQUARE_DEPTH = 10;

    private static double SELECTED_HUE_SHIFT = 1.0;
    private static double SELECTED_SATURATION_FACTOR = 1.0;
    private static double SELECTED_BRIGHTNESS_FACTOR = 1.0;
    private static double SELECTED_OPACITY_FACTOR = 0.3;

    //private boolean isSelected;
    private boolean hasPiece;
    private Color color;
    private PieceView3D piece;
    private Box square;


//    private int row;
//    private int col;

    SquareView3D(int row, int col, Color color) {
        super(7-row, col);
//        this.row = row;
//        this.col = col;
        this.setAlignment(Pos.CENTER);
        this.color = color;
        this.piece = null;

        this.square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);
        square.setMaterial(new PhongMaterial(color));

        this.getChildren().addAll(square);

        //this.isSelected = false;
        this.hasPiece = false;

        //changeColor();
    }

    public void putPieceOnSquare(PieceEnum pieceType, Color color) {
        if (!this.hasPiece) {
            piece = new PieceView3D(pieceType, color);
            this.getChildren().add(piece.getPieceMesh());
            this.hasPiece = true;
        }
        else {
            System.out.println("There is already a piece here.");
        }

    }

    public PieceView3D removePieceFromSquare() {
        if (this.hasPiece) {
            PieceView3D pieceToRemove = this.piece;
            this.getChildren().remove(pieceToRemove.getPieceMesh());
            this.piece = null;
            this.hasPiece = false;
            return pieceToRemove;
        }
        else {
            System.out.println("No piece to remove.");
            return null;
        }
    }


//    public boolean isSelected() {
//        return isSelected;
//    }
//
//    public void changeColor() {
//        //highlight the piece when it is selected
//
//        this.setOnMouseClicked(event -> {
//            System.out.println("Click!");
//            if (this.isSelected()) {
//                deselect();
//            }
//            else {
//                select();
//            }
//        });
//
//    }

//    public void select() {
//        this.isSelected = true;
//        this.square.setMaterial(new PhongMaterial(Color.GREEN));
//
//        if (hasPiece) {
//            this.piece.selectPiece();
//        }
//
//        //TODO - fix code so that the color of the piece will change as well
//    }

//    public void deselect() {
//        this.isSelected = false;
//        this.square.setMaterial(new PhongMaterial(color));
//
//        if (hasPiece) {
//            this.piece.deselectPiece();
//        }
//
//        //TODO - fix code so that the color of the piece will change as well
//    }

    public void highlight() {

        if ((row + col) % 2 == 0) {
            this.square.setMaterial(new PhongMaterial(BoardView.getSquare1Highlight()));
        } else {
            this.square.setMaterial(new PhongMaterial(BoardView.getSquare2Highlight()));
        }

        if (hasPiece) {
            this.piece.selectPiece();
        }
    }

    public void unHighlight(){
        this.square.setMaterial(new PhongMaterial(color));

        if (hasPiece) {
            this.piece.deselectPiece();
        }
    }


}