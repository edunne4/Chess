/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/4/19
 * Time: 11:04 AM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.Sockets
 * Class: ServerClientThread
 *
 * Description:
 *
 * ****************************************
 */
package Networking.Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerClientThread implements Runnable{
    Socket serverClient;

    public ServerClientThread(Socket serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(serverClient.getInputStream());
            ObjectOutputStream outStream = new ObjectOutputStream(serverClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}