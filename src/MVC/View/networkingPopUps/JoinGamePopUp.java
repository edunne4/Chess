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
package MVC.View.networkingPopUps;

//Resource: https://alvinalexander.com/java/joptionpane-showinputdialog-examples


import javax.swing.*;

public class JoinGamePopUp {

    String addressToJoin;

    public JoinGamePopUp() {
        JFrame frame = new JFrame("Join a Game");

        addressToJoin = JOptionPane.showInputDialog(frame,
                "What is the IP address of the game you want to join?",
                "Join a Game",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public String getAddressToJoin() {
        return addressToJoin;
    }

}