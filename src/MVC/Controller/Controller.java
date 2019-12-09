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
 * Package: MVC.Controller.Controller
 * Class: MVC.Controller.Controller
 *
 * Description:
 *
 * ****************************************
 */
package MVC.Controller;

import ChessParts.ChessPieces.ChessPiece;
import ChessParts.Movement;
import ChessParts.ChessPieces.King;
import ChessParts.Square;
import ChessParts.Team;
import MVC.Model.GameManager;
import MVC.Model.RuleMaster;
import MVC.View.GameView;
import MVC.View.NetworkingPopUps.HostGamePopUp;
import MVC.View.NetworkingPopUps.JoinGamePopUp;
import MVC.View.SquareView;
import Networking.Player.ClientPlayer;
import Networking.Player.HostPlayer;
import Networking.Player.Player;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class Controller {

    /** The view */
    protected GameView theView;
    /** The model */
    protected GameManager theModel;

    protected MenuBarController MBC;

    protected Square currentSquareSelected;

    private boolean isNetworkingGame;
    private String ipAddressToJoin;


    /**
     * Creates a chess game controller, allowing the user interaction with the view to be connected with the model
     *
     * @param theView the GameView that will display a GUI using javafx
     * @param theModel the GameManager that runs the game by making moves
     */
    public Controller(GameView theView, GameManager theModel) {
        this.theView = theView;
        this.theModel = theModel;



        RadioMenuItem enable2DBtn = (RadioMenuItem)theView.getGameMenuBar().getViewGroup().getToggles().get(0);
        //bind is3D to !2Dselected (or to 3DSelected, either one works)
        theView.is3DProperty().bind(enable2DBtn.selectedProperty().not());
        //set default values for menus
        enable2DBtn.setSelected(true);
        reloadGameViewAndResetBindings();

    }

    /**
     * Reloads the view of the game as well as re-binds all the event handlers and bindings using the new board game loaded
     */
    private void reloadGameViewAndResetBindings() {
        theView.reloadGameView();

        makeSquaresClickable();
        displayIfInCheck();
        setUpMenuBar();
    }

    /**
     * Sets up event handlers for when squares in the view are clicked
     */
    protected void makeSquaresClickable() {
        if (isNetworkingGame) {
            for (Node child : theView.getBoard().getChildren()){
                SquareView squareView = (SquareView) child;
                squareView.setOnMouseClicked(event -> {
                    try {
                        squareWasClickedNetwork((SquareView) event.getSource());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        } else {
            for (Node child : theView.getBoard().getChildren()) {
                SquareView squareView = (SquareView) child;
                squareView.setOnMouseClicked(event -> squareWasClicked((SquareView) event.getSource()));
            }
        }
    }

    /**
     * Set up menu bar bindings and event handlers
     */
    private void setUpMenuBar() {
        setUp2Dvs3DMenuClickHandlers();
        setupMultiplayerMenuClicksHandler();
        bindColorsToPieces();
    }

    private void setupMultiplayerMenuClicksHandler() {

        MenuItem hostGameBtn = theView.getGameMenuBar().getMenus().get(1).getItems().get(0);
        hostGameBtn.setOnAction( event -> {
            new HostGamePopUp();
            try {
                makeConnection(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        MenuItem joinGameBtn = theView.getGameMenuBar().getMenus().get(1).getItems().get(1);
        joinGameBtn.setOnAction( event -> {
            JoinGamePopUp joinGamePopUp = new JoinGamePopUp();
            ipAddressToJoin = joinGamePopUp.getAddressToJoin();
            System.out.println("The user wants to join the game hosted at: " + ipAddressToJoin);
            try {
                makeConnection(false);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Sets up event handlers for when the 2D and 3D menu buttons are clicked
     */
    private void setUp2Dvs3DMenuClickHandlers() {
        //handle 2D button press
        //must be an event handler because the view must be reloaded
        RadioMenuItem enable2DBtn = (RadioMenuItem)theView.getGameMenuBar().getViewGroup().getToggles().get(0);
        enable2DBtn.setOnAction(event -> {
            reloadGameViewAndResetBindings();
        });

        //handle 3D button press
        RadioMenuItem enable3DBtn = (RadioMenuItem)theView.getGameMenuBar().getViewGroup().getToggles().get(1);
        enable3DBtn.setOnAction(event -> {
            reloadGameViewAndResetBindings();

        });

    }

    private void bindColorsToPieces() {
        //TODO

    }

    /**
     * Function to handle event for when a SquareView is clicked
     * @param squareSelected - the square that was clicked
     */
    protected Movement squareWasClicked(SquareView squareSelected){
        System.out.println("Square was clicked");

        //get model's corresponding square
        Square thisSquare = theModel.getBoard().getSquareAt(squareSelected.getRow(), squareSelected.getCol());
        System.out.println(thisSquare.toString());

        Movement moveMade = null;

        //if it has a piece in it in the model (it's not empty)
        if (!thisSquare.isEmpty()) {
            //check if this piece is on the team whose turn it is
            if(thisSquare.getCurrentPiece().getTeam() == theModel.getCurrentTurn()) {
                //select it the piece at this square and highlight its moves
                selectPieceAt(thisSquare);
            }else{
                moveMade = attemptMovementTo(thisSquare);
            }
        }else{
            //else if there is already a square selected
             moveMade = attemptMovementTo(thisSquare);
        }
        return moveMade;
    }


    /**
     * Helper method to show that a piece at a square is selected by highlighting its legal moves
     * @param thisSquare the square to select
     */
    protected void selectPieceAt(Square thisSquare){
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
    protected Movement attemptMovementTo(Square newSquare){
        Movement move = null;
        //if there's currently a square selected
        if(currentSquareSelected != null) {
            //get the legal moves of the current square
            List<Square> legalMoves = theModel.getLegalMoves(currentSquareSelected);
            // if this new square clicked is a legal move, move the chess piece
            if (legalMoves.contains(newSquare)) {

                //move piece to the new square "thisSquare"
                ChessPiece killedPiece = theModel.movePiece(currentSquareSelected, newSquare);

                //if there is a piece at the new location(thisSquare), kill it
                if (killedPiece != null) {
                    if (killedPiece.getTeam() == Team.WHITE) {
                        killPiece(newSquare, killedPiece, Team.BLACK, theView.getDeadPieceHolderWhite());
                    } else {
                        if(killedPiece instanceof King){theView.createEndGameWindow(Team.WHITE);theView.getQuitButton().setOnAction((ActionEvent e) -> {System.exit(0);});}
                        theView.killPiece(newSquare.getRow(), newSquare.getCol(), theView.getDeadPieceHolderBlack());
                    }
                    //if its a king. the game's over.
                }
                //move piece to the new square in the view
                theView.getBoard().movePiece(currentSquareSelected.getRow(), currentSquareSelected.getCol(), newSquare.getRow(), newSquare.getCol());
                //Create a movement object, that has the initial and final positions of the piece moved
                move = new Movement(currentSquareSelected, newSquare);
            }
            //deselect the square and unhighlight the previous square's legal moves
            highlightSquares(legalMoves, false);
            currentSquareSelected = null;
        }
        return move;
    }


    /**
     * Helper method to highlight a list of squares from the model on the view
     * @param legalMoves the squares to highlight
     * @param highlight whether or not the squares should be highlighted or un-highlighted
     */
    protected void highlightSquares(List<Square> legalMoves, boolean highlight){

        //highlight those positions on the board view
        for (Square pos : legalMoves) {
            if(highlight) {
                theView.getBoard().getSquareAt(pos.getRow(), pos.getCol()).highlight();
            }else{
                theView.getBoard().getSquareAt(pos.getRow(), pos.getCol()).unHighlight();
            }
        }
    }

    /**
     * change inCheckText if a player is in check
     */
    protected void displayIfInCheck(){
        RuleMaster rules = theModel.getRuleMaster();
        theView.getInCheckTextBlack().textProperty().bind(Bindings.when(rules.isCheckedBlackProperty())
                .then("Black Is In Check")
                .otherwise(""));
        theView.getInCheckTextWhite().textProperty().bind(Bindings.when(rules.isCheckedWhiteProperty())
                .then("White Is In Check")
                .otherwise(""));
    }


    //********************************************************************************************************

    private Player player;


    protected void squareWasClickedNetwork(SquareView squareSelected) throws IOException, ClassNotFoundException {
        if (theModel.getCurrentTurn() == player.getTeam()){
            Movement moveMade = squareWasClicked(squareSelected);
            makeMove(moveMade);
        }
    }

    private void makeMove(Movement moveMade) throws IOException, ClassNotFoundException {
        if(moveMade != null){
            player.sendMove(moveMade);
        }
    }

    public void simulateClick(Movement opponentsMove) {
        currentSquareSelected = theModel.getBoard().getSquareAt(opponentsMove.getInitialSquare().getRow(),
                opponentsMove.getInitialSquare().getCol());
        SquareView squareViewClicked = theView.getBoard().getSquareAt(opponentsMove.getFinalSquare().getRow(), opponentsMove.getFinalSquare().getCol());
        squareWasClicked(squareViewClicked);
    }

    public void makeConnection(boolean isHost) throws IOException, ClassNotFoundException {
        if (isHost){
            this.player = new HostPlayer(this);
            player.connect();
            Thread thread = new Thread(player);
            thread.start();
        } else {
            this.player = new ClientPlayer(ipAddressToJoin, this);
            player.connect();
            Thread thread = new Thread(player);
            thread.start();
        }
    }
}
