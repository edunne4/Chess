/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/3/19
 * Time: 8:45 PM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.NController
 * Class: HostPlayer
 *
 * Description:
 *
 * ****************************************
 */
package Networking.NController;

import ChessParts.Movement;
import ChessParts.Player;
import ChessParts.Team;
import Networking.Sockets.Server;

import java.io.IOException;

public class HostPlayer extends Player {
    private Server server;
    public HostPlayer() throws IOException {
        super(Team.BLACK);
        server.connect();
    }

    @Override
    public Movement waitForMove() throws IOException, ClassNotFoundException {
        Movement move = server.readMovementFromClient();
        return move;
    }

    @Override
    public void sendMove(Movement moveMade) throws IOException {
        server.sendMovementToClient(moveMade);
    }


}