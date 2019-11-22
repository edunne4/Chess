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
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * This class is the container for the TilePane that contains all the squares on the board.
 */
public class BoardView extends TilePane{

    private TilePane board;
    private final int SIDE_LENGTH = 8;
    static final String LIGHT_COLOR_HEX = "#ede8da";
    static final String DARK_COLOR_HEX = "#949085";
    static final String LIGHT_HIGHLIGHT_HEX = "#b2edb3";
    static final String DARK_HIGHLIGHT_HEX = "#7bbd7b";


    BoardView(int size){
        //the board will be a grid of squares
        super();
        this.setPrefSize(size,size);
        //for every one of the 64 squares
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                //make it a stack pane. it will have a color background and the piece can be placed on top of it
                SquareView square = new SquareView(row,col);
                square.setSize(size/SIDE_LENGTH);


                square.setAlignment(Pos.CENTER); // make sure piece will be put in center


                //pick color of square
                String squareColor;
                if ((row + col) % 2 == 0) {
                    squareColor = LIGHT_COLOR_HEX;
                } else {
                    squareColor = DARK_COLOR_HEX;
                }
                //set square color
                square.setStyle("-fx-background-color: " + squareColor);
                //add square to the board
                this.getChildren().add(square);

            }
        }
    }

    void initBoard(){
        //placeholder to avoid errors
        PieceView piece = new PieceView("https://via.placeholder.com/60");
        //top side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {
            //white rooks
            if(i == 0 || i == 7) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/a/a0/Chess_rdt60.png");}
            //queen
            if(i == 4){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/a/af/Chess_qdt60.png");}
            //king
            if(i == 3){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/e/e3/Chess_kdt60.png");}
            //white bishops
            if(i == 2 || i == 5) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/8/81/Chess_bdt60.png");}
            //knights
            if(i == 1 || i == 6) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/f/f1/Chess_ndt60.png");}
            //white pawns
            if(i >=8) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/c/cd/Chess_pdt60.png");}

            SquareView square = (SquareView)this.getChildren().get(i);
            square.addImageView(piece.getView());
        }
        //bottom side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {

            //black pawns
            if(i < 8) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/0/04/Chess_plt60.png");}
            //black rooks
            if(i == 8 || i ==15){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/5/5c/Chess_rlt60.png");}
            //queen
            if(i == 12){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/4/49/Chess_qlt60.png");}
            //king
            if(i == 11){piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/3/3b/Chess_klt60.png");}
            //bishops
            if(i == 10 || i == 13) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/9/9b/Chess_blt60.png");}
            //knights
            if(i == 9 || i == 14) {piece = new PieceView("https://upload.wikimedia.org/wikipedia/commons/2/28/Chess_nlt60.png");}
            int offset = SIDE_LENGTH*SIDE_LENGTH - SIDE_LENGTH*2;
            SquareView square = (SquareView)this.getChildren().get(i + offset);
            square.addImageView(piece.getView());
        }

    }

    public void movePiece(int oldRow,int oldCol,int newRow,int newCol){
        SquareView oldLocationSquare = this.getSquare(oldRow,oldCol);
        SquareView newLocationSquare = this.getSquare(newRow,newCol);

        newLocationSquare.addImageView(oldLocationSquare.getPiece());
        oldLocationSquare.getChildren().clear();
    }


    public SquareView getSquare(int row, int col) {
        return (SquareView)this.getChildren().get(row * 8 + col);
    }
}
