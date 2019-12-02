/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/22/19
 * Time: 4:47 PM
 *
 * Project: CSCI205FinalProject
 * Package: Network
 * Class: Client
 *
 * Description:
 *  A Client class took object reading and writing from stack overflow
 *  https://stackoverflow.com/questions/30878881/how-i-can-send-a-object-from-server-to-client-in-java
 * ****************************************
 */
package Network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket;
    static ObjectInputStream in;
    static ObjectOutputStream out;
    public static String serverIPAdress;
    private int portNum = 7778;
    private String lastMessageFromClient;
    private boolean isConnected = false;

    public Client(String serverIPAdress) throws IOException {
        this.serverIPAdress = serverIPAdress;
        connect();
    }

    public void connect() throws IOException {
        Scanner s = new Scanner(System.in);
        setUpClientSeverConnection();
    }

    /**
     * Connects server to client
     * @throws IOException
     */
    private void setUpClientSeverConnection() throws IOException {
        System.out.println("C: Connection... ");
        portNum = 7778;
        socket = new Socket(serverIPAdress, portNum);
        if (socket.isConnected()) {
            System.out.println("C: Connection successful...");
            isConnected = true;
        }
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Reads an object from the server, and returns it
     * @return
     * @throws IOException
     */
    public Object readObjectFromServer() throws IOException {
        Object obj = in.read();
        return obj;
    }

    public void sendObjectToServer(Object o) throws IOException {
        out.writeObject(o);
        out.flush();
        out.close();
    }
}