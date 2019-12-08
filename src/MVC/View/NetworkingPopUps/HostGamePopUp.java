/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ryan Bailis
 * Section: MWF 11am
 * Instructor: Professor Brian King
 * Date: 12/8/19
 * Time: 1:43 PM
 *
 * Project: csci205finalproject
 * Package: MVC.View.networkingPopUps
 * Class: joinGamePopUp
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View.NetworkingPopUps;

//Resource: https://alvinalexander.com/java/joptionpane-showinputdialog-examples


import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

//Resource: https://alvinalexander.com/java/joptionpane-showmessagedialog-examples-1

public class HostGamePopUp {

    InetAddress ip;

    {
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host. Better check that out!");
        }
    }

    public HostGamePopUp()  {

        JFrame frame = new JFrame("Host a Game");

        JOptionPane.showMessageDialog(frame,
                "Your IP address is: " + ip.getHostAddress(),
                "Host a Game",
                JOptionPane.INFORMATION_MESSAGE);
    }
}