/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ethan Dunne
 * Section: 11am
 * Date: 11/10/19
 * Time: 11:51 AM
 *
 * Project: csci205finalproject
 * Package: model
 * Class: ChessBoard
 *
 * Description:
 *
 * ****************************************
 */
package ChessParts;

public class ChessBoard {
    int BOARD_WIDTH = 8;
    int BOARD_HEIGHT = 8;

    private final Square[][] positions;

    public ChessBoard() {
        //create board
        positions = new Square[BOARD_HEIGHT][BOARD_WIDTH];
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                positions[row][col] = new Square(row, col);
            }
        }

        //set up pieces in starting position
    }

    private void initPieces(){

    }
}
