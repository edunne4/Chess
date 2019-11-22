//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;

public class Board3DView extends Application {

    private PerspectiveCamera camera;

    private String CAMERA = "Cam1";

    private Scene scene;
    private StackPane root;
    private GridPane board;

    private int SQUARE_SIZE = 100;
    private int SQUARE_DEPTH = 10;

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
        board.setTranslateX(SQUARE_SIZE*-4);
        board.setTranslateY(SQUARE_SIZE*-4);

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

        //changeCameraOnClick();

        //camera.getTransforms().addAll(new Translate(0,0,-2000)); //set camera angle for above shot
        camera.getTransforms().addAll(new Translate(0,1800,-2000),new Rotate(40,Rotate.X_AXIS)); //set camera angle for view 1

        //show the scene to the user
        stage.setScene(scene);
        scene.setFill(BACKGROUND_COLOR);
        stage.show();
    }

    private void initializeBoardSquares() {
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                Box square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);
                StackPane stackPane = new StackPane(square);
                stackPane.setAlignment(Pos.CENTER);

                if ((i+j) % 2 == 0) {
                    square.setMaterial(new PhongMaterial(SQUARE1_COLOR));
                }
                else {
                    square.setMaterial(new PhongMaterial(SQUARE2_COLOR));
                }

                board.add(stackPane,i,j);
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

            //highlight the piece when it is selected
            ChessPiece3D finalMeshView = meshView;
            meshView.setOnMouseClicked(event -> {
                finalMeshView.selectPiece();

            });

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
        //camera view 1 for player 1, assuming we are at home
        final Translate translate1 = new Translate(400, 2500, -2000);
        final Rotate rotate1 = new Rotate(45,Rotate.X_AXIS);

        //camera view 2 for player 2, assuming we are at home
        final Translate translate2 = new Translate(-1000, -1000, -4000);
        final Rotate rotate2 = new Rotate(180,Rotate.Z_AXIS);


        camera.getTransforms().addAll(translate1,rotate1); //set camera angle


        scene.setOnMouseClicked(event -> {

            try {
                if (CAMERA == "Cam2") { //we are currently player 2, go to player 1
                    camera.getTransforms().addAll(translate2.createInverse());
                    camera.getTransforms().addAll(translate1,rotate1);
                    CAMERA = "Cam1";

                }
                else if (CAMERA == "Cam1") {
                    camera.getTransforms().addAll(rotate1.createInverse(),translate1.createInverse());
                    camera.getTransforms().addAll(translate2);
                    CAMERA = "Cam2";
                }
            } catch (Exception e) {
                System.out.println("Error!!!!");
                e.printStackTrace();
            }

          });

    }


    public static void main(String[] args) {
        launch(args);
    }



}
