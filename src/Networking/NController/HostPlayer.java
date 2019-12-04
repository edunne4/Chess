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

public class HostPlayer extends Player{
    private Server server;
    private boolean gameOver = false;
    private NController nController;

    public HostPlayer(NController nController) throws IOException {
        super(Team.BLACK);
        this.nController = nController;
        this.server = new Server();
    }

    @Override
    public Movement waitForMove() throws IOException, ClassNotFoundException {
        Movement move = server.readMovementFromClient();
        System.out.println(move);
        return move;
    }

    @Override
    public void sendMove(Movement moveMade) throws IOException {
        server.sendMovementToClient(moveMade);
    }

    @Override
    public void connect() throws IOException {
        server.connect();
    }


    @Override
    public void run() {
        while(!gameOver){
            try {
                Movement move = server.readMovementFromClient();
                nController.simulateClick(move);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}