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
package Networking.Player;

import ChessParts.Movement;
import ChessParts.Team;
import MVC.Controller.Controller;
import Networking.Sockets.Server;
import javafx.application.Platform;

import java.io.IOException;

public class HostPlayer extends Player{
    private Server server;
    private boolean gameOver = false;
    private Controller Controller;

    public HostPlayer(Controller Controller) throws IOException {
        super(Team.BLACK);
        this.Controller = Controller;
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

    /**
     * Check out our lambda function
     * https://stackoverflow.com/questions/17850191/why-am-i-getting-java-lang-illegalstateexception-not-on-fx-application-thread
     */
    @Override
    public void run() {
        while(!gameOver){
            try {
                Movement move = server.readMovementFromClient();
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