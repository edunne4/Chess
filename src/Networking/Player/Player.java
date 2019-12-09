package Networking.Player;/* *****************************************
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
 * Package: Model.ChessParts
 * Class: Networking.Player.Player
 *
 * Description:
 * A player implementation that can make moves, stores their ipaddress, and
 * ****************************************
 */

import Model.Movement;
import Model.Team;

import java.io.IOException;

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

}