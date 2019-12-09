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
 * Package: SinglePlatform.View
 * Class: SquareView
 *
 * Description:
 *
 * ****************************************
 */
package View.View2D;

import View.BoardView;
import View.SquareView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class SquareView2D extends SquareView {

    //private StackPane pane;

    /**Creates a StackPane object for a square that stores the square's location on the board
     *
     * @param row the square's row
     * @param col the square's column
     */
    SquareView2D(int row, int col) {
        super(7-row, col);
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
//        String squareColor;
        if ((row + col) % 2 != 0) {
            setColor(BoardView.getSquare1Highlight());
//            squareColor = BoardView.getSquare1Highlight().toString();
        } else {
            setColor(BoardView.getSquare2Highlight());
//            squareColor = BoardView.getSquare2Color().toString();
        }
//        this.setStyle("-fx-background-color: " + squareColor);
    }


    /**
     * unHighlights the selected square
     */
    public void unHighlight(){
//        String squareColor;
        if ((row + col) % 2 != 0) {
            setColor(BoardView.getSquare1Color());
        } else {
            setColor(BoardView.getSquare2Color());
        }
//        this.setStyle("-fx-background-color: " + squareColor);
    }

    /**
     * grabs the imageview contained within the square
     * @return the ImageView node of the piece
     */
    public ImageView getPiece(){
        return (ImageView)this.getChildren().get(0);
    }

    public void setColor(Color color){
        this.setBackground(new Background(new BackgroundFill(color, null, null)));
    }

}
