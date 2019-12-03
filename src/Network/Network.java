/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/2/19
 * Time: 7:47 PM
 *
 * Project: CSCI205FinalProject
 * Package: Network
 * Class: Network
 *
 * Description:
 *
 * ****************************************
 */
package Network;

import java.io.IOException;

public class Network {
    Client client;
    Server server;

    public Network() throws IOException {
        this.server = new Server();
        String IPAddress = server.getIPAddress();
        this.client = new Client(IPAddress);
        if(server.isConnected && client.isConnected){
            System.out.println("yippie");
        }
    }

}