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
 * Package: SinglePlatform.View
 * Class: BoardView
 *
 * Description:
 *
 * ****************************************
 */
package View.View2D;

import Model.ChessBoard;
import Model.ChessPieces.ChessPiece;
import Model.Team;
import View.BoardView;
import javafx.scene.paint.Color;

/**
 * This class is the container for the TilePane that contains all the squares on the board.
 */
public class BoardView2D extends BoardView {

//    static final String SQUARE1_COLOR = "#949085";
//    static final String SQUARE2_COLOR = "#ede8da";
//    static final String LIGHT_HIGHLIGHT_HEX = "#b2edb3";
//    static final String DARK_HIGHLIGHT_HEX = "#7bbd7b";

//    private Color PLAYER1_COLOR = Color.GREEN;
//    private Color PLAYER2_COLOR = Color.RED;


    /**
     * creates a board that is a tile pane and puts a SquareView object in each spot
     * @param size pixel width and height of the board
     */
    public BoardView2D(int size, ChessBoard modelBoard){
        //the board will be a grid of squares
        super();
        this.setPrefSize(size,size);
        //for every one of the 64 squares
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                initSquare(size, row, col);
            }
        }

        initPiecesFromBoard(modelBoard);
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
        if ((row + col) % 2 == 0) {
            //squareColor = getSquare1Color().toString();
            square.setColor(getSquare1Color());
        } else {
            square.setColor(getSquare2Color());
        }
        this.getChildren().add(square);
    }

    @Override
    public void initPiecesFromBoard(ChessBoard modelBoard) {
        // loop through the entire board and create 2D pieces where necessary
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                //if the current square has a piece, make a 2D representation of it
                if(!modelBoard.getSquareAt(row, col).isEmpty()){
                    ChessPiece currentPiece = modelBoard.getSquareAt(row, col).getCurrentPiece();
                    //get the correct color from the model
                    Color pieceColor = getPlayer1Color();
                    if(currentPiece.getTeam() == Team.BLACK){ // if piece belongs to player2 (assuming black is player 2)
                        pieceColor = getPlayer2Color(); //use player2 color
                    }
                    //create the 2D piece with using the tye enum and color
                    PieceView2D piece2D = new PieceView2D(currentPiece.getPieceType(), pieceColor);
                    //add that 2D piece to the correct square
                    getSquareAt(row, col).addImageView(piece2D.getView());
                }
            }

        }
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

//    @Override
//    public void initPieces() {
//        initBoard();
//    }


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
