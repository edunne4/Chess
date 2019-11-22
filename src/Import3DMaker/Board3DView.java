//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package Import3DMaker;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.NonInvertibleTransformException;
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
        createPieceOnBoard(0,0,"stl/rook.stl",PLAYER1);
        createPieceOnBoard(1,0,"stl/knight.stl",PLAYER1);
        createPieceOnBoard(2,0,"stl/bishop.stl",PLAYER1);
        createPieceOnBoard(3,0,"stl/king.stl",PLAYER1);
        createPieceOnBoard(4,0,"stl/queen.stl",PLAYER1);
        createPieceOnBoard(5,0,"stl/bishop.stl",PLAYER1);
        createPieceOnBoard(6,0,"stl/knight.stl",PLAYER1);
        createPieceOnBoard(7,0,"stl/rook.stl",PLAYER1);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,1,"stl/pawn.stl",PLAYER1);
        }

        //create the pieces for player 2
        createPieceOnBoard(0,7,"stl/rook.stl",PLAYER2);
        createPieceOnBoard(1,7,"stl/knight.stl",PLAYER2);
        createPieceOnBoard(2,7,"stl/bishop.stl",PLAYER2);
        createPieceOnBoard(3,7,"stl/king.stl",PLAYER2);
        createPieceOnBoard(4,7,"stl/queen.stl",PLAYER2);
        createPieceOnBoard(5,7,"stl/bishop.stl",PLAYER2);
        createPieceOnBoard(6,7,"stl/knight.stl",PLAYER2);
        createPieceOnBoard(7,7,"stl/rook.stl",PLAYER2);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,6,"stl/pawn.stl",PLAYER2);
        }



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

        camera.getTransforms().addAll(new Translate(0,0,-4000)); //set camera angle

        //show the scene to the user
        stage.setScene(scene);
        stage.show();
    }

    private void removePieceOnBoard(int col, int row) {
        //remove it from the board
        StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
        if (squareOnBoard != null) {
            int pieceIndex = squareOnBoard.getChildren().size() - 1;
            squareOnBoard.getChildren().remove(pieceIndex);
        }

    }

    private void createPieceOnBoard(int col, int row, String fileName, Color color) {
        //create the chess piece
        MeshView piece = createChessPiece(fileName, color);

        //add it to the board
        StackPane squareOnBoard = (StackPane) getNodeFromGridPane(board,col,row);
        if (squareOnBoard != null) {
            squareOnBoard.getChildren().add(piece);
        }

        piece.setOnMouseClicked(event -> {

            removePieceOnBoard(col,row);
            createPieceOnBoard(col,row,fileName,Color.BLUE.grayscale());

        });



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

    /**
     * A function to create a MeshView object from an STL file.
     * @param filename the STL file to be parsed and converted to a JavaFX MesView object
     * @param color the color of the MeshView in the viewport
     * @return a MeshView object that will be viewable in the scene
     */
    private MeshView createChessPiece(String filename, Color color) {

        //create a StlMeshImporter object and try parsing with the filename
        StlMeshImporter stlImporter = new StlMeshImporter();
        try {
            stlImporter.read(this.getClass().getResource(filename));
        }
        catch (ImportException e) {
            System.err.println("Error. STL file does not exist.");
            e.printStackTrace();
        }

        //create a MeshView object from the StlMeshImporter
        stlImporter.getImport();
        TriangleMesh mesh = stlImporter.getImport();
        stlImporter.close();
        MeshView meshView = new MeshView(mesh);

        //set the material, general properties, and scale of the mesh
        meshView.setMaterial(new PhongMaterial(color));
        meshView.setDrawMode(DrawMode.FILL);
        meshView.setVisible(true);

        //scale the pieces appropriately
        int scale = 3;
        meshView.setScaleX(scale);
        meshView.setScaleY(scale);
        meshView.setScaleZ(scale);

        return meshView;
    }


    public static void main(String[] args) {
        launch(args);
    }



}
