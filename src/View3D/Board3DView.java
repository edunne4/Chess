//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import javafx.application.Application;
import javafx.geometry.Point3D;
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

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;

    private String CAMERA = "Cam1";

    private volatile boolean isPicking=false;
    private Point3D vecIni, vecPos;
    private double distance;
    private Sphere s;
    private Scene scene;
    private StackPane root;

    private GridPane board;

    private int SQUARE_SIZE = 100;
    private int SQUARE_DEPTH = 10;

    private final Color PLAYER1 = Color.RED;
    private final Color PLAYER2 = Color.BLUE;

    @Override
    public void start(Stage stage) throws IOException {

        //initialize the board
        board = new GridPane();
        board.setAlignment(Pos.CENTER);

        //add all of the squares to the board
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                Box square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);
                StackPane stackPane = new StackPane(square);
                stackPane.setAlignment(Pos.CENTER);

                if ((i+j) % 2 == 0) {
                    square.setMaterial(new PhongMaterial(Color.RED));
                }
                else {
                    square.setMaterial(new PhongMaterial(Color.BLUE));
                }

                board.add(stackPane,i,j);
            }

        }

        //create the pieces for player1
        createPieceOnBoard(0,0,PieceEnum.ROOK,PLAYER1);
        createPieceOnBoard(1,0,PieceEnum.KNIGHT,PLAYER1);
        createPieceOnBoard(2,0,PieceEnum.BISHOP,PLAYER1);
        createPieceOnBoard(3,0,PieceEnum.KING,PLAYER1);
        createPieceOnBoard(4,0,PieceEnum.QUEEN,PLAYER1);
        createPieceOnBoard(5,0,PieceEnum.BISHOP,PLAYER1);
        createPieceOnBoard(6,0,PieceEnum.KNIGHT,PLAYER1);
        createPieceOnBoard(7,0,PieceEnum.ROOK,PLAYER1);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,1,PieceEnum.PAWN,PLAYER1);
        }

        //create the pieces for player 2
        createPieceOnBoard(0,7,PieceEnum.ROOK,PLAYER2);
        createPieceOnBoard(1,7,PieceEnum.KNIGHT,PLAYER2);
        createPieceOnBoard(2,7,PieceEnum.BISHOP,PLAYER2);
        createPieceOnBoard(3,7,PieceEnum.KING,PLAYER2);
        createPieceOnBoard(4,7,PieceEnum.QUEEN,PLAYER2);
        createPieceOnBoard(5,7,PieceEnum.BISHOP,PLAYER2);
        createPieceOnBoard(6,7,PieceEnum.KNIGHT,PLAYER2);
        createPieceOnBoard(7,7,PieceEnum.ROOK,PLAYER2);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,6,PieceEnum.PAWN,PLAYER2);
        }

        removePieceOnBoard(0,0);

        movePieceOnBoard(4,1,3,5);



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

        //camera.getTransforms().addAll(new Translate(0,0,-4000)); //set camera angle

        //show the scene to the user
        stage.setScene(scene);
        scene.setFill(Color.GRAY);
        stage.show();
    }

    private void movePieceOnBoard(int startingCol,int startingRow,int endingCol,int endingRow) {
        ChessPiece3D removedPiece = removePieceOnBoard(startingCol,startingRow);
        createPieceOnBoard(endingCol,endingRow,removedPiece.getPieceType(),removedPiece.getPieceColor());


    }

    private ChessPiece3D removePieceOnBoard(int col, int row) {
        //remove it from the board
        StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
        if (squareOnBoard != null) {
            int pieceIndex = squareOnBoard.getChildren().size() - 1;
            ChessPiece3D piece3D = (ChessPiece3D) squareOnBoard.getChildren().get(pieceIndex);
            squareOnBoard.getChildren().remove(pieceIndex);

            return piece3D;

        }

        //if there is an issue, simply return a black pawn in it's place
        return new ChessPiece3D(PieceEnum.PAWN,Color.BLACK);

    }

    private ChessPiece3D createPieceOnBoard(int col, int row, PieceEnum piece, Color color) {
        //create the chess piece
        ChessPiece3D meshView = new ChessPiece3D(piece, color);

        //add it to the board
        StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
        if (squareOnBoard != null) {
            squareOnBoard.getChildren().add(meshView);
            return meshView;
        }

        //if there is an issue, simply return a black pawn in it's place
        return new ChessPiece3D(PieceEnum.PAWN,Color.BLACK);

//        meshView.setOnMouseClicked(event -> {
//
//            removePieceOnBoard(col,row);
//            createPieceOnBoard(col,row,piece.getFilename(),Color.BLUE.grayscale());
//
//        });
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
