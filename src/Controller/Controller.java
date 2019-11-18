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


    private Square currentSquareSelected;


    public Controller(GameView theView, GameManager theModel) {
        this.theView = theView;
        this.theModel = theModel;


        makeSquaresClickable();
    }

    private void makeSquaresClickable() {

        for (Node child : theView.getBoard().getChildren()){
            SquareView squareView = (SquareView) child;
            squareView.setOnMouseClicked(event -> squareWasClicked((SquareView) event.getSource()));
        }
    }

    /**
     * Function to handle event for when a squareview is clicked
     * @param squareSelected - the square that was clicked
     */
    private void squareWasClicked (SquareView squareSelected){
        System.out.println("Square was clicked");

        //get model's corresponding square
        Square thisSquare = theModel.getBoard().getSquareAt(squareSelected.getRow(), squareSelected.getCol());

        if(currentSquareSelected == null) { //if no other square is selected
            //if it has a piece in it in the model (it's not empty)
            if (!thisSquare.isEmpty()) { //TODO - check if this piece is on the team whose turn it is!!
                //get the possible moves of that piece
                //TODO - try block maybe surrounding this
                List<Square> legalMoves = theModel.getLegalMoves(thisSquare);

                currentSquareSelected = thisSquare;

                //highlight those positions on the board view
                for (Square pos : legalMoves) {
                    theView.getBoard().getSquare(pos.getRow(), pos.getCol()).highlight();
                }
            }
        } else { //else there is already a square selected

            //TODO maybe make a function for this
            List<Square> legalMoves = theModel.getLegalMoves(currentSquareSelected);

            // if this new square clicked is a legal move, move the chess piece
            if(legalMoves.contains(thisSquare)){
                //move piece to the new square "thisSquare"
                theModel.movePiece(currentSquareSelected, thisSquare);
                //move piece to the new square in the view
                theView.getBoard().movePiece(currentSquareSelected.getRow(),currentSquareSelected.getCol(),thisSquare.getRow(),thisSquare.getCol());
            }
            //else, deselect the square and unhighlight legal moves
            for (Square pos : legalMoves) {
                theView.getBoard().getSquare(pos.getRow(), pos.getCol()).unHighlight();
            }
            currentSquareSelected = null;
        }



    }
}
