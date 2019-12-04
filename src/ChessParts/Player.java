package ChessParts;/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 11/14/19
 * Time: 9:00 PM
 *
 * Project: CSCI205FinalProject
 * Package: ChessParts
 * Class: ChessParts.Player
 *
 * Description:
 * A player implementation that can make moves, stores their ipaddress, and
 * ****************************************
 */

import SinglePlatform.Model.GameManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player implements Runnable{
    public boolean isTurn = false;
    private Team team;
    public Player(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }

    public abstract Movement waitForMove() throws IOException, ClassNotFoundException;

    public abstract void sendMove(Movement moveMade) throws IOException;

    public abstract void connect() throws IOException;


//    /**
//     * Retrurns an array list, first two indexes are the indexes of the piece that
//     * is desired to be moved, the last two indexes are the indexes of the position of where
//     * is wished to be moved to
//     *
//     * @return and ArrayList
//     */
//    public ArrayList<Integer> makeMove() {
//        ArrayList<Integer> destinationCoordinates = new ArrayList<>(4);
//        Scanner in = new Scanner(System.in);
//        boolean goodInput = true;
//        while (goodInput) {
//            System.out.println("Enter the first coordinate of the piece you want to move:");
//            int coordinate1;
//            try {
//                coordinate1 = in.nextInt();
//            } catch (Exception e) {
//                System.out.println("Please Enter an Integer");
//                continue;
//            }
//            if (!validInputs.contains(coordinate1)) {
//                System.out.println("Enter a digit between 0-8");
//            }
//            System.out.println("Enter the second coordinate of the piece you want to move:");
//            int coordinate2;
//            try {
//                coordinate2 = in.nextInt();
//            } catch (Exception e) {
//                System.out.println("Please Enter an Integer");
//                continue;
//            }
//            if (!validInputs.contains(coordinate2)) {
//                System.out.println("Enter a digit between 0-8");
//            }
//            int coordinate3;
//            try {
//                coordinate3 = in.nextInt();
//            } catch (Exception e) {
//                System.out.println("Please Enter an Integer");
//                continue;
//            }
//            if (!validInputs.contains(coordinate3)) {
//                System.out.println("Enter a digit between 0-8");
//            }
//            int coordinate4;
//            try {
//                coordinate4 = in.nextInt();
//            } catch (Exception e) {
//                System.out.println("Please Enter an Integer");
//                continue;
//            }
//            if (!validInputs.contains(coordinate4)) {
//                System.out.println("Enter a digit between 0-8");
//            }
//            destinationCoordinates.add(coordinate1);
//            destinationCoordinates.add(coordinate2);
//            destinationCoordinates.add(coordinate3);
//            destinationCoordinates.add(coordinate4);
//        }
//        return destinationCoordinates;
//    }
}