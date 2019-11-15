/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/13/19
 * Time: 11:34 AM
 *
 * Project: csci205finalproject
 * Package: Controller
 * Class: Controller
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import ChessParts.Square;
import Model.GameManager;
import View.GameView;
import View.SquareView;
import javafx.scene.Node;

import java.util.List;

public class Controller {

    /** The view */
    private GameView theView;
    /** The model */
    private GameManager theModel;


    public Controller(GameView theView, GameManager theModel) {
        this.theView = theView;
        this.theModel = theModel;


        makeSquaresClickable();
    }

    private void makeSquaresClickable() {

        for (Node child : theView.getBoard().getPane().getChildren()){
            //SquareView squareView = (SquareView) child;
            child.setOnMouseClicked(event -> squareWasClicked((SquareView) event.getSource()));
        }
    }

    /**
     * Function to handle event for when a squareview is clicked
     * @param squareSelected - the square that was clicked
     */
    private void squareWasClicked (SquareView squareSelected){
        //get model's corresponding square
        Square thisSquare = theModel.getBoard().getSquareAt(squareSelected.getRow(), squareSelected.getCol());
        //if it has a piece in it in the model (it's not empty)
        if(!thisSquare.isEmpty()){
            //get the possible moves of that piece
            //TODO - try block maybe surrounding this
            List<Square> legalMoves = theModel.getLegalMoves(thisSquare);

            //highlight those positions on the board view
            for (Square pos : legalMoves) {
                theView.getBoard().highlightSquare(pos.getRow(), pos.getCol());
            }
        }



    }
}
