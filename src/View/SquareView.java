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

import javafx.scene.layout.StackPane;

public class SquareView {

    private StackPane pane;
    private int row;
    private int col;


    public SquareView(int col,int row){
        pane = new StackPane();
        this.row = row;
        this.col = col;
    }

    public void addPieceView(PieceView pv){
        pane.getChildren().add(pv.getView());
    }

    public void setSize(int size){
        pane.setPrefSize(size,size);
    }

    public StackPane getPane(){return pane;}

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
