/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/3/19
 * Time: 8:05 PM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.NController
 * Class: ControllerImpl
 *
 * Description:
 *
 * ****************************************
 */
package MVC.Controller;

import ChessParts.Movement;
import Networking.Player.Player;
import MVC.Model.GameManager;
import Networking.Player.ClientPlayer;
import Networking.Player.HostPlayer;
import MVC.View.GameView;
import MVC.View.SquareView;
import javafx.scene.Node;

import java.io.IOException;

public class NetController extends Controller {
    private Player player;
    private boolean isHost;
    public NetController(GameView theView, GameManager theModel, boolean isHost) throws IOException, ClassNotFoundException {
        super(theView, theModel);
        this.isHost = isHost;

    }

    public void simulateClick(Movement opponentsMove) {
        currentSquareSelected = theModel.getBoard().getSquareAt(opponentsMove.getInitialSquare().getRow(),
                opponentsMove.getInitialSquare().getCol());
        SquareView squareViewClicked = theView.getBoard().getSquareAt(opponentsMove.getFinalSquare().getRow(), opponentsMove.getFinalSquare().getCol());
        squareWasClicked(squareViewClicked);
    }

    public void makeConnection() throws IOException, ClassNotFoundException {
        if (isHost){
            this.player = new HostPlayer(this);
            player.connect();
            Thread thread = new Thread(player);
            thread.start();
        } else {
            this.player = new ClientPlayer("localhost", this);
            player.connect();
            Thread thread = new Thread(player);
            thread.start();
        }
    }

    @Override
    protected void makeSquaresClickable() {

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
    }

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


}