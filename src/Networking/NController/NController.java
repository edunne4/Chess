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
package Networking.NController;

import ChessParts.Movement;
import ChessParts.Player;
import ChessParts.Square;
import ChessParts.Team;
import SinglePlatform.Controller.Controller;
import SinglePlatform.Model.GameManager;
import SinglePlatform.View.GameView;
import SinglePlatform.View.SquareView;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class NController extends Controller {
    private Player player;
    private boolean isHost;
    public NController(GameView theView, GameManager theModel, boolean isHost) throws IOException, ClassNotFoundException {
        super(theView, theModel);
        this.isHost = isHost;
        try {
            if (isHost){
                this.player = new HostPlayer();
            } else {
                this.player = new ClientPlayer("localhost");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isHost){
            Movement move = player.waitForMove();
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
            if(moveMade != null){
                player.sendMove(moveMade);
                theModel.movePiece(player.waitForMove());
            }
        }
    }
}