/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/4/19
 * Time: 10:59 AM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.Sockets
 * Class: MultithreadedSocketServer
 *
 * Description:
 *
 * ****************************************
 */
package Networking.Sockets;

import java.io.IOException;
import java.net.Socket;

public class MultithreadedSocketServer {
    private static Server server;

    public MultithreadedSocketServer() throws IOException {
        this.server = new Server();
        server.connect();
    }

    public static void main(String[] args) {
        Socket clientSocket = server.getClientSocket();
        while (true){
            ServerClientThread sct = new ServerClientThread(clientSocket);
            sct.run();
        }
    }
}