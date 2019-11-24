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
 * Package: View3D.STLParser
 * Class: BoardSquare3DView
 *
 * Description:
 *
 * ****************************************
 */
package View3D;

import ChessParts.ChessPieces.ChessPiece;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class BoardSquare3DView extends StackPane {

    public static int SQUARE_SIZE = 100;
    public static int SQUARE_DEPTH = 10;

    private static double SELECTED_HUE_SHIFT = 1.0;
    private static double SELECTED_SATURATION_FACTOR = 1.0;
    private static double SELECTED_BRIGHTNESS_FACTOR = 1.0;
    private static double SELECTED_OPACITY_FACTOR = 0.3;

    private boolean isSelected;
    private boolean hasPiece;
    private Color color;
    private ChessPiece3D piece;

    BoardSquare3DView(Color color) {
        this.setAlignment(Pos.CENTER);
        this.color = color;

        Box square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);
        square.setMaterial(new PhongMaterial(color));

        this.getChildren().add(square);

        this.isSelected = false;
        this.hasPiece = false;

        changeColor();
    }

    private void addPieceToSquare(PieceEnum piece, Color color) {
        this.getChildren().add(new ChessPiece3D(piece, color));
        this.hasPiece = true;

    }

    private void removePieceFromSquare() {
        if (this.hasPiece) {
            int pieceIndex = this.getChildren().size() - 1;
            this.getChildren().remove(pieceIndex);
        }
        else {
            System.out.println("No piece to remove");
        }

    }


    public boolean isSelected() {
        return isSelected;
    }

    public void changeColor() {
        //highlight the piece when it is selected

        this.setOnMouseClicked(event -> {
            System.out.println("Click!");
            if (this.isSelected()) {
                deselect();
            }
            else {
                select();
            }
        });

    }

    public void select() {
        this.isSelected = true;
        Box box = (Box) this.getChildren().get(0);
        box.setMaterial(new PhongMaterial(color.deriveColor(SELECTED_HUE_SHIFT,SELECTED_SATURATION_FACTOR,SELECTED_BRIGHTNESS_FACTOR, SELECTED_OPACITY_FACTOR)));
        if (this.hasPiece) {
            ChessPiece3D piece = (ChessPiece3D) this.getChildren().get(1);
            piece.selectPiece();
        }
    }

    public void deselect() {
        this.isSelected = false;
        Box box = (Box) this.getChildren().get(0);
        box.setMaterial(new PhongMaterial(color));

        if (this.hasPiece) {
            ChessPiece3D piece = (ChessPiece3D) this.getChildren().get(1);
            piece.deselectPiece();
        }
    }


}