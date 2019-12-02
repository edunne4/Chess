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

public class SquareView2D extends SquareView {

    //private StackPane pane;


    SquareView2D(int row, int col) {
        super(7-row, col); //this
//        this.row = 7-row;
//        this.col = col;
    }

    void setSize(int size) {
        this.setPrefSize(size, size);
    }

    void addImageView(ImageView iv) { this.getChildren().add(iv); }

    public void highlight() {
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView2D.LIGHT_HIGHLIGHT_HEX;
        } else {
            squareColor = BoardView2D.DARK_HIGHLIGHT_HEX;
        }
        this.setStyle("-fx-background-color: " + squareColor);
    }

    public void unHighlight(){
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView2D.LIGHT_COLOR_HEX;
        } else {
            squareColor = BoardView2D.DARK_COLOR_HEX;
        }
        this.setStyle("-fx-background-color: " + squareColor);
    }

    public ImageView getPiece(){
        return (ImageView)this.getChildren().get(0);
    }

//    public int getRow() {
//        return row;
//    }
//
//    public int getCol() {
//        return col;
//    }

}
