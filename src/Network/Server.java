/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/22/19
 * Time: 11:45 AM
 *
 * Project: CSCI205FinalProject
 * Package: Network
 * Class: Server
 *
 * Description:
 *
 * ****************************************
 */
package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
    //serverSocket for the Server
    static ServerSocket serverSocket;
    //Client socket
    static Socket socket;
    //Establishes an output stream so we can send info to client
    static DataOutputStream out;
    //Establishes an input stream to get info from the client
    static DataInputStream in;
    //Port number for Server
    private static final int portNum = 7778;
    private Scanner s;

    public Server() throws IOException {
        connect();
    }

    public void connect() throws IOException {
        s = new Scanner(System.in);
        setUpServerClientConnection();
    }

    private void setUpServerClientConnection() throws IOException {
        //Get IP address of the Server
        InetAddress inetAddress = InetAddress.getLocalHost();
        //Get String representation of the internet address
        String IPAdress = inetAddress.getHostAddress();
        System.out.println("S: Your Address " + IPAdress);
        System.out.println("S: Your port: " + portNum);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        if (socket.isConnected()){
            System.out.println("S: Connection successful...");
        }
    }


    public void sendObjectToClient(Object o){

    }
    /**
     * Writes a string to the client
     * @param message
     * @throws IOException
     */
    public void writeStringToClient(String message) throws IOException {
        out.writeUTF(message);
    }
}