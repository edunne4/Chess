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
 * Package: SinglePlatform.MVC.View
 * Class: SquareView
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View.View2D;

import MVC.View.SquareView;
import javafx.scene.image.ImageView;

public class SquareView2D extends SquareView {

    //private StackPane pane;

    /**Creates a StackPane object for a square that stores the square's location on the board
     *
     * @param row the square's row
     * @param col the square's column
     */
    SquareView2D(int row, int col) {
        super(7-row, col); //this
//        this.row = 7-row;
//        this.col = col;
    }


    /**
     * sets size based off a pixel size
     * @param size the pixel size of the square
     */
    void setSize(int size) {
        this.setPrefSize(size, size);
    }

    /**
     * Adds a child to this square view
     * @param iv the ImageView node to be added
     */
    void addImageView(ImageView iv) { this.getChildren().add(iv); }


    /**
     * Highlights the selected square
     */
    public void highlight() {
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView2D.DARK_HIGHLIGHT_HEX;
        } else {
            squareColor = BoardView2D.LIGHT_HIGHLIGHT_HEX;
        }
        this.setStyle("-fx-background-color: " + squareColor);
    }


    /**
     * unHighlights the selected square
     */
    public void unHighlight(){
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView2D.SQUARE1_COLOR;
        } else {
            squareColor = BoardView2D.SQUARE2_COLOR;
        }
        this.setStyle("-fx-background-color: " + squareColor);
    }

    /**
     * grabs the imageview contained within the square
     * @return the ImageView node of the piece
     */
    public ImageView getPiece(){
        return (ImageView)this.getChildren().get(0);
    }

}
