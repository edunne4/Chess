//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import ChessParts.ChessPieces.ChessPiece;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;

public class Board3DView extends TilePane {

    private int NUM_ROWS = 8;

    private final Color PLAYER1_COLOR = Color.RED;
    private final Color PLAYER2_COLOR = Color.WHITE;

    private final Color SQUARE1_COLOR = Color.BLACK;
    private final Color SQUARE2_COLOR = Color.WHITE;


     public Board3DView() {

         super();

        //initialize the board
        this.setPrefColumns(NUM_ROWS);
        this.setAlignment(Pos.CENTER);

        //center the board in the view
        this.setTranslateX(BoardSquare3DView.SQUARE_SIZE*-4);
        this.setTranslateY(BoardSquare3DView.SQUARE_SIZE*-4);

        //add all of the squares to the board
        initializeBoardSquares();

        //setup the board in a traditional chess fashion
        initializeBoard();

        removePieceOnBoard(0,0);
        movePieceOnBoard(4,1,4,3);

        //initialize the scene, and set the camera to the scene
        //changeCameraOnClick();
    }

    private void initializeBoardSquares() {
        for (int col = 0; col < 8 ; col++) {
            for (int row = 0; row < 8; row++) {
                BoardSquare3DView square;
                if ((col+row) % 2 == 0) {
                    square = new BoardSquare3DView(SQUARE1_COLOR);
                }
                else {
                    square = new BoardSquare3DView(SQUARE2_COLOR);
                }
                this.getChildren().add(square);
            }
        }
    }

    private void initializeBoard() {
        //create the pieces for player1
        createPieceOnBoard(0,0, PieceEnum.ROOK, PLAYER1_COLOR);
        createPieceOnBoard(1,0,PieceEnum.KNIGHT, PLAYER1_COLOR);
        createPieceOnBoard(2,0,PieceEnum.BISHOP, PLAYER1_COLOR);
        createPieceOnBoard(3,0,PieceEnum.KING, PLAYER1_COLOR);
        createPieceOnBoard(4,0,PieceEnum.QUEEN, PLAYER1_COLOR);
        createPieceOnBoard(5,0,PieceEnum.BISHOP, PLAYER1_COLOR);
        createPieceOnBoard(6,0,PieceEnum.KNIGHT, PLAYER1_COLOR);
        createPieceOnBoard(7,0,PieceEnum.ROOK, PLAYER1_COLOR);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,1,PieceEnum.PAWN, PLAYER1_COLOR);
        }

        //create the pieces for player 2
        createPieceOnBoard(0,7,PieceEnum.ROOK, PLAYER2_COLOR);
        createPieceOnBoard(1,7,PieceEnum.KNIGHT, PLAYER2_COLOR);
        createPieceOnBoard(2,7,PieceEnum.BISHOP, PLAYER2_COLOR);
        createPieceOnBoard(3,7,PieceEnum.KING, PLAYER2_COLOR);
        createPieceOnBoard(4,7,PieceEnum.QUEEN, PLAYER2_COLOR);
        createPieceOnBoard(5,7,PieceEnum.BISHOP, PLAYER2_COLOR);
        createPieceOnBoard(6,7,PieceEnum.KNIGHT, PLAYER2_COLOR);
        createPieceOnBoard(7,7,PieceEnum.ROOK, PLAYER2_COLOR);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,6,PieceEnum.PAWN, PLAYER2_COLOR);
        }
    }

    public void movePieceOnBoard(int startingCol,int startingRow,int endingCol,int endingRow) {
        ChessPiece3D removedPiece = removePieceOnBoard(startingCol,startingRow);
        createPieceOnBoard(endingCol,endingRow,removedPiece.getPieceType(),removedPiece.getPieceColor());


    }

    public ChessPiece3D removePieceOnBoard(int col, int row) {
        //get the square we are looking to remove a piece from
        BoardSquare3DView squareOnBoard = (BoardSquare3DView) this.getChildren().get(col + row*NUM_ROWS);

        //remove the piece
        ChessPiece3D removedPiece = squareOnBoard.removePieceFromSquare();

        return removedPiece;

    }

    public void createPieceOnBoard(int col, int row, PieceEnum pieceType, Color color) {
        //get the square we are looking to add a piece to
        BoardSquare3DView squareOnBoard = (BoardSquare3DView) this.getChildren().get(col + row*NUM_ROWS);

        //add the piece
        squareOnBoard.addPieceToSquare(pieceType,color);

    }
}
