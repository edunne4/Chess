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
 * Package: SinglePlatform.MVC.View
 * Class: BoardView
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View.View2D;

import MVC.View.BoardView;
import MVC.View.PieceEnum;
import javafx.scene.paint.Color;

/**
 * This class is the container for the TilePane that contains all the squares on the board.
 */
public class BoardView2D extends BoardView {

    static final String SQUARE1_COLOR = "#949085";
    static final String SQUARE2_COLOR = "#ede8da";
    static final String LIGHT_HIGHLIGHT_HEX = "#b2edb3";
    static final String DARK_HIGHLIGHT_HEX = "#7bbd7b";

    private static Color PLAYER1_COLOR = Color.GREEN;
    private final Color PLAYER2_COLOR = Color.RED;


    /**
     * creates a board that is a tile pane and puts a SquareView object in each spot
     * @param size pixel width and height of the board
     */
    public BoardView2D(int size){
        //the board will be a grid of squares
        super();
        this.setPrefSize(size,size);
        //for every one of the 64 squares
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                initSquare(size, row, col);
            }
        }

        initPieces();
    }

    /**Creates a SquareView at the specified spot on the board
     *
     * @param size the size of the board
     * @param row the row on the board
     * @param col the column on the board
     */
    private void initSquare(int size, int row, int col) {
        //make each square a SquareView
        SquareView2D square = new SquareView2D(row,col);
        //pick color of square
        String squareColor;
        if ((row + col) % 2 != 0) {
            squareColor = SQUARE1_COLOR;
        } else {
            squareColor = SQUARE2_COLOR;
        }
        //set square color
        square.setStyle("-fx-background-color: " + squareColor);
        this.getChildren().add(square);
    }

    /**
     * initializes the board with all white and black chess pieces
     */
    void initBoard(){
        initWhitePieces();
        initBlackPieces();
    }

    /**
     * initializes black pieces in correct spots on the board
     */
    private void initBlackPieces() {
        //placeholder to avoid errors
        PieceView2D piece = new PieceView2D("https://via.placeholder.com/60");
        //top side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {
            //black rooks
            if(i == 0 || i == 7) {piece = new PieceView2D(PieceEnum.ROOK, PLAYER2_COLOR);}
            //queen
            if(i == 3){piece = new PieceView2D(PieceEnum.QUEEN, PLAYER2_COLOR);}
            //king
            if(i == 4){piece = new PieceView2D(PieceEnum.KING, PLAYER2_COLOR);}
            //black bishops
            if(i == 2 || i == 5) {piece = new PieceView2D(PieceEnum.BISHOP, PLAYER2_COLOR);}
            //knights
            if(i == 1 || i == 6) {piece = new PieceView2D(PieceEnum.KNIGHT, PLAYER2_COLOR);}
            //black pawns
            if(i >=8) {piece = new PieceView2D(PieceEnum.PAWN, PLAYER2_COLOR);}

            SquareView2D square = (SquareView2D)this.getChildren().get(i);
            square.addImageView(piece.getView());
        }
    }

    /**
     * initializes white pieces in correct spots on the board
     */
    private void initWhitePieces()  {
        PieceView2D piece = new PieceView2D("https://via.placeholder.com/60");
        //bottom side
        for (int i = 0; i < SIDE_LENGTH*2; i++) {

            //white pawns
            if(i < 8) { piece = new PieceView2D(PieceEnum.PAWN, PLAYER1_COLOR);}
            if(i == 8 || i ==15){piece = new PieceView2D(PieceEnum.ROOK, PLAYER1_COLOR);}
            //queen
            if(i == 11){piece = new PieceView2D(PieceEnum.QUEEN, PLAYER1_COLOR);}
            //king
            if(i == 12){piece = new PieceView2D(PieceEnum.KING, PLAYER1_COLOR);}
            //bishops
            if(i == 10 || i == 13) {piece = new PieceView2D(PieceEnum.BISHOP, PLAYER1_COLOR);}
            //knights
            if(i == 9 || i == 14) {piece = new PieceView2D(PieceEnum.KNIGHT, PLAYER1_COLOR);}

            int offset = SIDE_LENGTH*SIDE_LENGTH - SIDE_LENGTH*2;
            SquareView2D square = (SquareView2D)this.getChildren().get(i + offset);
            square.addImageView(piece.getView());

            }
            //white rooks

        }


    /**
     * Moves a piece's image from one squareView to a new squareview on the baord
     *
     * @param oldRow the row of the old square
     * @param oldCol the column of the old square
     * @param newRow the row of the new square
     * @param newCol the column of the new square
     */
    public void movePiece(int oldRow,int oldCol,int newRow,int newCol){
        SquareView2D oldLocationSquare = this.getSquareAt(oldRow,oldCol);
        SquareView2D newLocationSquare = this.getSquareAt(newRow,newCol);

        newLocationSquare.addImageView(oldLocationSquare.getPiece());
        oldLocationSquare.getChildren().clear();
    }

    @Override
    public void initPieces() {
        initBoard();
    }


    /**
     * Grabs the SquareView at the specified spot in the board
     *
     * @param row the square's row
     * @param col the square's column
     * @return the SquareView
     */
    public SquareView2D getSquareAt(int row, int col) {
        return (SquareView2D)this.getChildren().get((7-row) * SIDE_LENGTH + col);
    }
}
