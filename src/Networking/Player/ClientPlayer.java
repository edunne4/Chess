/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/3/19
 * Time: 8:46 PM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.NController
 * Class: ClientPlayer
 *
 * Description:
 * A Client player, that starts a client server and can receive movements from the host player.
 * ****************************************
 */
package Networking.Player;

import Model.Movement;
import Model.Team;
import Controller.Controller;
import Networking.Sockets.Client;
import javafx.application.Platform;

import java.io.IOException;

public class ClientPlayer extends Player{
    private Client client;
    //TODO - extract these to parent class
    private boolean gameOver = false;
    private Controller Controller;
    public ClientPlayer(String IPAddress, Controller Controller) throws IOException {
        super(Team.WHITE);
        this.Controller = Controller;
        this.client = new Client(IPAddress);
    }


    @Override
    public Movement waitForMove() throws IOException, ClassNotFoundException {
        Movement moveMade = client.readMovementFromServer();
        System.out.println(moveMade);
        return moveMade;
    }

    @Override
    public void sendMove(Movement moveMade) throws IOException {
        client.sendMovementToServer(moveMade);
        System.out.println(moveMade);
    }

    @Override
    public void connect() throws IOException {
        client.connect();
    }

    @Override
    public void run() {
        while(!gameOver){
            try {
                Movement move = client.readMovementFromServer();
                Platform.runLater(
                        () -> {
                            Controller.simulateClick(move);

                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}