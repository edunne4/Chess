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
 *
 * ****************************************
 */
package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    public static String serverIPAdress;
    private int portNum = 7778;
    private String lastMessageFromClient;

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
        System.out.println("C: Connection successful...");
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    /**
     * Reads a string from the server and prints it to the
     * terminal
     * @throws IOException
     */
    public void readStringFromServer() throws IOException {
        lastMessageFromClient = in.readUTF();
        System.out.println(lastMessageFromClient);
    }
}