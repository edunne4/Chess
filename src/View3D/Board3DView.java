//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    private GridPane board;

    private int NUM_ROWS = 8;

    private final Color PLAYER1_COLOR = Color.RED;
    private final Color PLAYER2_COLOR = Color.WHITE;

    private final Color SQUARE1_COLOR = Color.BLACK;
    private final Color SQUARE2_COLOR = Color.WHITE;
    private final Color BACKGROUND_COLOR = Color.GRAY;

    @Override
    public void start(Stage stage) throws IOException {

        //initialize the board
        board = new GridPane();
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
                StackPane square = null;
                if ((col+row) % 2 == 0) {
                    square = new BoardSquare3DView(SQUARE1_COLOR);
                }
                else {
                    square = new BoardSquare3DView(SQUARE2_COLOR);
                }
                board.add(square,col,row);
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

    private void movePieceOnBoard(int startingCol,int startingRow,int endingCol,int endingRow) {
        try {
            ChessPiece3D removedPiece = removePieceOnBoard(startingCol,startingRow);
            createPieceOnBoard(endingCol,endingRow,removedPiece.getPieceType(),removedPiece.getPieceColor());
        } catch (Exception e) {
            System.err.println("Piece can not be moved.");
        }


    }

    private ChessPiece3D removePieceOnBoard(int col, int row) {
        //remove it from the board
        StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
            int pieceIndex = squareOnBoard.getChildren().size() - 1;

            ChessPiece3D piece3D = null;
            try {
                piece3D = (ChessPiece3D) squareOnBoard.getChildren().get(pieceIndex);
                squareOnBoard.getChildren().remove(pieceIndex);
            } catch (Exception e) {
                System.err.println("Illegal move. There is no piece in the specified spot that can be moved.");
            }

            return piece3D;

    }

    private ChessPiece3D createPieceOnBoard(int col, int row, PieceEnum piece, Color color) {

       ChessPiece3D meshView = null;
        try {
            //create the chess piece
             meshView = new ChessPiece3D(piece, color);

            //add it to the board
            StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
            squareOnBoard.getChildren().add(meshView);

        } catch (Exception e) {
            System.err.println("No piece to be created.");
        }

        return meshView;
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
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
