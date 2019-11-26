/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/26/19
 * Time: 4:10 PM
 *
 * Project: csci205finalproject
 * Package: Controller
 * Class: Controller3D
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import ChessParts.ChessPieces.ChessPiece;
import ChessParts.Square;
import ChessParts.Team;
import Model.GameManager;
import View.GameView;
import View.SquareView;
import View3D.Board3DView;
import View3D.BoardSquare3DView;
import javafx.scene.Node;

import java.util.List;

public class Controller3D {
    /** The view */
    private Board3DView theView;
    /** The model */
    private GameManager theModel;


    private Square currentSquareSelected;


    public Controller3D(Board3DView theView, GameManager theModel) {
        this.theView = theView;
        this.theModel = theModel;


        makeSquaresClickable();
    }

    private void makeSquaresClickable() {

        for (Node child : theView.getBoard().getChildren()){
            BoardSquare3DView squareView3D = (BoardSquare3DView) child;
            squareView3D.setOnMouseClicked(event -> squareWasClicked((BoardSquare3DView) event.getSource()));
        }
    }

    /**
     * Function to handle event for when a 3D square view is clicked
     * @param squareSelected - the square that was clicked
     */
    private void squareWasClicked (BoardSquare3DView squareSelected){
        System.out.println("Square was clicked");

        //get model's corresponding square
        Square thisSquare = theModel.getBoard().getSquareAt(squareSelected.getRow(), squareSelected.getCol());

        //if it has a piece in it in the model (it's not empty)
        if (!thisSquare.isEmpty()) {
            //check if this piece is on the team whose turn it is
            if(thisSquare.getCurrentPiece().getTeam() == theModel.getCurrentTurn()) {
                //select it the piece at this square and highlight its moves
                selectPieceAt(thisSquare);
            }else{
                attemptMovementTo(thisSquare);
            }
        }else{
            //else if there is already a square selected
            attemptMovementTo(thisSquare);
        }
    }


    /**
     * Helper method to show that a piece at a square is selected by highlighting its legal moves
     * @param thisSquare the square to select
     */
    private void selectPieceAt(Square thisSquare){
        if(currentSquareSelected != null){ //de-highlight current moves
            highlightSquares(theModel.getLegalMoves(currentSquareSelected), false);
        }
        //highlight new square's moves
        highlightSquares(theModel.getLegalMoves(thisSquare), true);

        currentSquareSelected = thisSquare;
    }


    /**
     * Helper method to attempt to move the piece at the currently selected square to a new square location.
     * If there is no currently selected square, do nothing.
     * @param newSquare is the new location to move the currently selected piece to
     */
    private void attemptMovementTo(Square newSquare){
        if(currentSquareSelected != null) {
            //get the legal moves of the current square
            List<Square> legalMoves = theModel.getLegalMoves(currentSquareSelected);
            // if this new square clicked is a legal move, move the chess piece
            if (legalMoves.contains(newSquare)) {

                //move piece to the new square "thisSquare"
                ChessPiece killedPiece = theModel.movePiece(currentSquareSelected, newSquare);

                //if there is a piece at the new location(thisSquare), kill it
//                if (killedPiece != null) {
//                    if (killedPiece.getTeam() == Team.WHITE) {
//                        theView.killPiece(newSquare.getRow(), newSquare.getCol(), theView.getDeadPieceHolderWhite());
//                    } else {
//                        theView.killPiece(newSquare.getRow(), newSquare.getCol(), theView.getDeadPieceHolderBlack());
//                    }
//                }
                //move piece to the new square in the view
                //theView.getBoard().movePiece(currentSquareSelected.getRow(), currentSquareSelected.getCol(), newSquare.getRow(), newSquare.getCol());
                theView.movePiece(currentSquareSelected.getRow(), currentSquareSelected.getCol(), newSquare.getRow(), newSquare.getCol());
            }
            //deselect the square and unhighlight the previous square's legal moves
            highlightSquares(legalMoves, false);
            currentSquareSelected = null;
        }
    }


    /**
     * Helper method to highlight a list of squares from the model on the view
     * @param legalMoves the squares to highlight
     * @param highlight whether or not the squares should be highlighted or un-highlighted
     */
    private void highlightSquares(List<Square> legalMoves, boolean highlight){

        //highlight those positions on the board view
        for (Square pos : legalMoves) {
            if(highlight) {
                theView.getSquareAt(pos.getRow(), pos.getCol()).highlight();
            }else{
                theView.getSquareAt(pos.getRow(), pos.getCol()).unHighlight();
            }
        }
    }
}
