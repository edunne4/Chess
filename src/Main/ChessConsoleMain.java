package Main;/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/14/19
 * Time: 9:45 PM
 *
 * Project: csci205finalproject
 * Package: PACKAGE_NAME
 * Class: SinglePlatform.ConsoleInterface
 *
 * Description:
 *
 * ****************************************
 */

import ChessParts.ChessPieces.ChessPiece;
import ChessParts.Square;
import MVC.Model.GameManager;

import java.util.List;
import java.util.Scanner;

public class ChessConsoleMain {

    public void runChessGame(){
        boolean gameIsRunning = true;
        GameManager gm = new GameManager();

        Scanner in = new Scanner(System.in);

        while(gameIsRunning){
            //print the board
            System.out.println(gm.getBoard().toString());

            System.out.printf("It is %s team's turn\n", gm.getCurrentTurn().toString());



            while (true) {
                try {
                    //get position of piece to move
                    System.out.println("Specify the position of the piece you would like to move (such as f5)");
                    String posToMove = in.next();
                    Square squareToMoveFrom = gm.getBoard().getSquareAt(posToMove);

                    //check if piece is there and get the moves from that piece
                    List<Square> legalMoves = gm.getLegalMoves(squareToMoveFrom);
                    if(legalMoves.isEmpty()){
                        System.err.println("That piece has no legal moves.");
                        throw new IllegalArgumentException("No Legal Moves");
                    }

                    while(true) {
                        System.out.println("Choose one of the following positions to move this piece to:");
                        //print all legal moves for this piece
                        System.out.println(legalMoves);
                        try {

                            String posToMoveTo = in.next();
                            Square squareToMoveTo = gm.getBoard().getSquareAt(posToMoveTo);

                            //check if squareToMoveTo is in valid moves
                            if(!legalMoves.contains(squareToMoveTo)){
                                throw new IllegalArgumentException("That position is not in legal moves.");
                            }

                            //move the piece
                            ChessPiece killed = gm.movePiece(squareToMoveFrom, squareToMoveTo);

                            break;
                        } catch (IllegalArgumentException e) {
                            System.err.println("Invalid chess position to move to.");
                        }


                    }


                    break;
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid chess position.");
                } catch (NullPointerException e){
                    System.err.println("There is no piece at that location.");
                }
            }



        }

    }
}
