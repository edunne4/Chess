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
 *
 * ****************************************
 */
package Networking.NController;

import ChessParts.Movement;
import ChessParts.Player;
import ChessParts.Team;
import Networking.Sockets.Client;

import java.io.IOException;

public class ClientPlayer extends Player {
    private Client client;
    public ClientPlayer(String IPAddress) throws IOException {
        super(Team.WHITE);
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
}