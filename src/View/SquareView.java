/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jake Schaeffer
 * Section: 11am
 * Date: 11/13/19
 * Time: 11:34 AM
 *
 * Project: csci205finalproject
 * Package: View
 * Class: SquareView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SquareView extends StackPane {

    private StackPane pane;
    private int row;
    private int col;


    public SquareView(int row, int col) {
        super(); //this
        this.row = row;
        this.col = col;
    }

    public void addPieceView(PieceView pv) {
        this.getChildren().add(pv.getView());
    }

    public void setSize(int size) {
        this.setPrefSize(size, size);
    }

    public void addImageView(ImageView iv) {
        this.getChildren().add(iv);
    }

    public void highlight() {

    this.setStyle("-fx-background-color: #7dff9d");

    }

    public void unHighlight(){
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView.LIGHT_COLOR_HEX;
        } else {
            squareColor = BoardView.DARK_COLOR_HEX;
        }
        this.setStyle("-fx-background-color: " + squareColor);
    }

    public ImageView getPiece(){
        return (ImageView)this.getChildren().get(0);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}