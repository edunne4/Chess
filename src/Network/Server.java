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
 * A server class that can read and write objects to a client
 * https://stackoverflow.com/questions/30878881/how-i-can-send-a-object-from-server-to-client-in-java
 * ****************************************
 */
package Network;

import java.io.*;
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
    static ObjectOutputStream out;
    //Establishes an input stream to get info from the client
    static ObjectInputStream in;
    //Port number for Server
    private static final int portNum = 7778;
    //Scanner to get input
    private Scanner s;
    //To see if the server is connected to client
    public static boolean isConnected = false;

    //IP Address
    private static String IPAddress;

    public Server() throws IOException {
        connect();
    }

    public void connect() throws IOException {
        s = new Scanner(System.in);
        setUpServerClientConnection();
    }

    /**
     * Sets up the server
     * @throws IOException
     */
    private void setUpServerClientConnection() throws IOException {
        findIPAddress();
        System.out.println("S: Your Address " + IPAddress);
        System.out.println("S: Your port: " + portNum);
        serverSocket = new ServerSocket(portNum);
        socket = serverSocket.accept();
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Finds the IPAdress of this server
     * @throws UnknownHostException
     */
    private void findIPAddress() throws UnknownHostException {
        //Get IP address of the Server
        InetAddress inetAddress = InetAddress.getLocalHost();
        //Get String representation of the internet address
        this.IPAddress = inetAddress.getHostAddress();
    }


    /**
     * Writes an object to a client server
     * @param o
     * @throws IOException
     */
    public void sendObjectToClient(Object o) throws IOException {
        out.writeObject(o);
        out.flush();
    }

    /**
     * Reads an object from the client
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object readObjectFromCient() throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        return obj;
    }

    public static String getIPAddress() {
        return IPAddress;
    }
}