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

    private int row;
    private int col;

    /**Creates a StackPane object for a square that stores the square's location on the board
     *
     * @param row the square's row
     * @param col the square's column
     */
    SquareView(int row, int col) {
        super(); //this
        this.row = 7-row;
        this.col = col;
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
            squareColor = BoardView.LIGHT_HIGHLIGHT_HEX;
        } else {
            squareColor = BoardView.DARK_HIGHLIGHT_HEX;
        }
        this.setStyle("-fx-background-color: " + squareColor);
        }

    /**
     * unHighlights the selected square
     */
    public void unHighlight(){
        String squareColor;
        if ((row + col) % 2 == 0) {
            squareColor = BoardView.LIGHT_COLOR_HEX;
        } else {
            squareColor = BoardView.DARK_COLOR_HEX;
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
