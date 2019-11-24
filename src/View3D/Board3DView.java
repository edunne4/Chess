//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import ChessParts.ChessPieces.ChessPiece;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;

public class Board3DView extends Application {

    private PerspectiveCamera camera;

    private String CAMERA = "Cam2";

    private Scene scene;
    private StackPane root;
    private TilePane board;

    private int NUM_ROWS = 8;

    private final Color PLAYER1_COLOR = Color.RED;
    private final Color PLAYER2_COLOR = Color.WHITE;

    private final Color SQUARE1_COLOR = Color.BLACK;
    private final Color SQUARE2_COLOR = Color.WHITE;
    private final Color BACKGROUND_COLOR = Color.GRAY;

    @Override
    public void start(Stage stage) throws IOException {

        //initialize the board
        board = new TilePane();
        board.setPrefColumns(NUM_ROWS);
        board.setAlignment(Pos.CENTER);

        //center the board in the view
        board.setTranslateX(BoardSquare3DView.SQUARE_SIZE*-4);
        board.setTranslateY(BoardSquare3DView.SQUARE_SIZE*-4);

        //add all of the squares to the board
        initializeBoardSquares();

        //setup the board in a traditional chess fashion
        initializeBoard();

        removePieceOnBoard(0,0);
        movePieceOnBoard(4,1,4,3);

        //add the board and the pieces to the root
        root = new StackPane();
        root.getChildren().addAll(board);
        root.setAlignment(Pos.CENTER);

        //initialize the camera
        camera = new PerspectiveCamera(true);
        //camera.setVerticalFieldOfView(false);
        camera.setNearClip(1.0);
        camera.setFarClip(10000.0);

        //initialize the scene, and set the camera to the scene
        scene = new Scene(root);
        scene.setCamera(camera);

        changeCameraOnClick();

        //show the scene to the user
        stage.setScene(scene);
        scene.setFill(BACKGROUND_COLOR);
        stage.show();
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
                board.getChildren().add(square);
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
        BoardSquare3DView squareOnBoard = (BoardSquare3DView) board.getChildren().get(col + row*NUM_ROWS);

        //remove the piece
        ChessPiece3D removedPiece = squareOnBoard.removePieceFromSquare();

        return removedPiece;

    }

    public void createPieceOnBoard(int col, int row, PieceEnum pieceType, Color color) {
        //get the square we are looking to add a piece to
        BoardSquare3DView squareOnBoard = (BoardSquare3DView) board.getChildren().get(col + row*NUM_ROWS);

        //add the piece
        squareOnBoard.addPieceToSquare(pieceType,color);

    }


    private void changeCameraOnClick() {

        Transform cam1A = new Translate(0,1800,-2000);
        Transform cam1B = new Rotate(40,Rotate.X_AXIS);

        Transform cam2A = new Translate(0,BoardSquare3DView.SQUARE_SIZE*NUM_ROWS,0);
        Transform cam2B = new Rotate(180,Rotate.X_AXIS);

        Transform cam2C = new Translate(BoardSquare3DView.SQUARE_SIZE*NUM_ROWS,0,0);
        Transform cam2D = new Rotate(180,Rotate.Y_AXIS);

        camera.getTransforms().addAll(cam1A,cam1B); //set camera angle for view 1

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.C) {
                camera.getTransforms().addAll(cam1A,cam1B);
                if (CAMERA == "Cam1") {
                    try {
                        camera.getTransforms().addAll(cam1B.createInverse(),cam1A.createInverse());
                        board.getTransforms().addAll(cam2D.createInverse(),cam2C.createInverse(),cam2B.createInverse(),cam2A.createInverse());
                        CAMERA = "Cam2";
                    } catch (NonInvertibleTransformException e) {
                        e.printStackTrace();
                    }
                }
                else if (CAMERA == "Cam2")  {
                    try {
                        camera.getTransforms().addAll(cam1B.createInverse(),cam1A.createInverse());
                        board.getTransforms().addAll(cam2A,cam2B,cam2C,cam2D);
                    } catch (NonInvertibleTransformException e) {
                        e.printStackTrace();
                    }
                    CAMERA = "Cam1";

                }

                System.out.println(CAMERA);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }



}
