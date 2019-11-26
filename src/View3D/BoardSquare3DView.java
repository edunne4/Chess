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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
    private Box square;


    private int row;
    private int col;

    BoardSquare3DView(int row, int col, Color color) {
        super();
        this.row = row;
        this.col = col;
        this.setAlignment(Pos.CENTER);
        this.color = color;
        this.piece = null;

        this.square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);
        square.setMaterial(new PhongMaterial(color));

        this.getChildren().addAll(square);

        this.isSelected = false;
        this.hasPiece = false;

        changeColor();
    }

    public void addPieceToSquare(PieceEnum pieceType, Color color) {
        if (!this.hasPiece) {
            piece = new ChessPiece3D(pieceType, color);
            this.getChildren().add(piece);
            this.hasPiece = true;
        }
        else {
            System.out.println("There is already a piece here.");
        }

    }

    public ChessPiece3D removePieceFromSquare() {
        if (this.hasPiece) {
            ChessPiece3D pieceToRemove = this.piece;
            this.getChildren().remove(pieceToRemove);
            this.piece = null;
            this.hasPiece = false;
            return pieceToRemove;
        }
        else {
            System.out.println("No piece to remove.");
            return null;
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
        this.square.setMaterial(new PhongMaterial(Color.GREEN));

        if (hasPiece) {
            this.piece.selectPiece();
        }

        //TODO - fix code so that the color of the piece will change as well
    }

    public void deselect() {
        this.isSelected = false;
        this.square.setMaterial(new PhongMaterial(color));

        if (hasPiece) {
            this.piece.deselectPiece();
        }

        //TODO - fix code so that the color of the piece will change as well
    }

    public void highlight() {
        this.square.setMaterial(new PhongMaterial(Color.GREEN));

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


}