/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jake Schaeffer
 * Section: 11am
 * Date: 11/13/19
 * Time: 11:34 AM
 *
 * Project: csci205finalproject
 * Package: View
 * Class: BoardView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class BoardView {

    private TilePane board;
    private final int SIDE_LENGTH = 8;
    private final String LIGHT_COLOR_HEX = "#ede8da";
    private final String DARK_COLOR_HEX = "#949085";
    private SquareView[][] SquareHolder = new SquareView[SIDE_LENGTH][SIDE_LENGTH];

    public BoardView(int size){
        //the board will be a grid of squares
        board = new TilePane();
        board.setPrefSize(size,size);
        //for every one of the 64 squares
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                //make it a stack pane. it will have a color background and the piece can be placed on top of it
                SquareView square = new SquareView(row,col);
                SquareHolder[row][col] = square;
                square.setSize(size/SIDE_LENGTH);


                square.getPane().setAlignment(Pos.CENTER); // make sure piece will be put in center


                //pick color of square
                String squareColor;
                if ((row + col) % 2 == 0) {
                    squareColor = LIGHT_COLOR_HEX;
                } else {
                    squareColor = DARK_COLOR_HEX;
                }
                //set square color
                square.getPane().setStyle("-fx-background-color: " + squareColor);
                //add square to the board
                board.getChildren().add(square.getPane());

            }
        }
    }

    public void initBoard(){
        //placeholder to avoid errors
        PieceView piece = new PieceView("https://via.placeholder.com/60");
        //top side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {
            //white rooks
            if(i == 0 || i == 7) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/5/5c/Chess_rlt60.png");}
            //queen
            if(i == 4){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/4/49/Chess_qlt60.png");}
            //king
            if(i == 3){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/3/3b/Chess_klt60.png");}
            //white bishops
            if(i == 2 || i == 5) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/9/9b/Chess_blt60.png");}
            //knights
            if(i == 1 || i == 6) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/2/28/Chess_nlt60.png");}
            //white pawns
            if(i < 16 && i >=8) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/0/04/Chess_plt60.png");}

            StackPane squarePane = (StackPane)board.getChildren().get(i);
            squarePane.getChildren().add(piece.getView());
        }
        //bottom side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {

            //black pawns
            if(i < 8) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/c/cd/Chess_pdt60.png");}
            //black rooks
            if(i == 8 || i ==15){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/a/a0/Chess_rdt60.png");}
            //queen
            if(i == 12){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/a/af/Chess_qdt60.png");}
            //king
            if(i == 11){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/e/e3/Chess_kdt60.png");}
            //bishops
            if(i == 10 || i == 13) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/8/81/Chess_bdt60.png");}
            //knights
            if(i == 9 || i == 14) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/f/f1/Chess_ndt60.png");}
            int offset = SIDE_LENGTH*SIDE_LENGTH - SIDE_LENGTH*2;
            StackPane squarePane = (StackPane)board.getChildren().get(i + offset);
            squarePane.getChildren().add(piece.getView());
        }

    }

    public void movePiece(int oldRow,int oldCol,int newRow,int newCol){
        StackPane oldLocationStackPane = (StackPane)board.getChildren().get(oldRow*8+oldCol);
        StackPane newLocationStackPane = (StackPane)board.getChildren().get(newRow*8+newCol);

        newLocationStackPane.getChildren().add(oldLocationStackPane.getChildren().get(0));
        oldLocationStackPane.getChildren().clear();
    }

    public TilePane getPane(){return board;}
}
