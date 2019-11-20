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
    private HBox pieces;

    private int SQUARE_SIZE = 100;
    private int SQUARE_DEPTH = 10;

    @Override
    public void start(Stage stage) throws IOException {

        //initialize the board
        board = new GridPane();
        //board.setAlignment(Pos.CENTER);

        //add all of the squares to the board
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                Box square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);

                if ((i+j) % 2 == 0) {
                    square.setMaterial(new PhongMaterial(Color.RED));
                }
                else {
                    square.setMaterial(new PhongMaterial(Color.BLUE));
                }

                board.add(square,i,j);
            }

        }


        //create red (team 1) pieces on the back row
        MeshView kingR = createChessPiece("stl/king.stl",Color.RED);
        MeshView queenR = createChessPiece("stl/queen.stl",Color.RED);
        MeshView rookR1 = createChessPiece("stl/rook.stl",Color.RED);
        MeshView knightR1 = createChessPiece("stl/knight.stl",Color.RED);
        MeshView bishopR1 = createChessPiece("stl/bishop.stl",Color.RED);
        MeshView rookR2 = createChessPiece("stl/rook.stl",Color.RED);
        MeshView knightR2 = createChessPiece("stl/knight.stl",Color.RED);
        MeshView bishopR2 = createChessPiece("stl/bishop.stl",Color.RED);


        //add all of the pieces
        pieces = new HBox();
        //pieces.setAlignment(Pos.CENTER);

        Node squareOnBoard = getNodeFromGridPane(board,5,5);
        squareOnBoard.setTranslateX(20);

        squareOnBoard.localToScene(0.0,0.0);

        System.out.println(squareOnBoard.getTranslateX());
        Bounds testttt = pieces.localToScene(pieces.getBoundsInLocal());
        System.out.println(testttt);

        Bounds squareOnBoardPos = squareOnBoard.localToScene(squareOnBoard.getBoundsInLocal());
        Bounds boundsInScene = squareOnBoard.localToScreen(squareOnBoard.getBoundsInLocal());


        //rookR1.setTranslateX(200);
        //rookR1.setTranslateY(100);

        rookR1.relocate(squareOnBoard.getBoundsInLocal().getCenterX(),squareOnBoard.getBoundsInLocal().getCenterY());
        Bounds pieceOnBoardPos = rookR1.localToScene(rookR1.getBoundsInLocal());

//        System.out.printf("X: %f \n",squareOnBoardPos.getCenterX());
//        System.out.printf("Y: %f \n",squareOnBoardPos.getCenterY());


        pieces.getChildren().addAll(rookR1);

//        pieces.add(knightR1,0,1);
//        pieces.add(bishopR1,0,2);
//        pieces.add(queenR,0,3);
//        pieces.add(kingR,0,4);
//        pieces.add(bishopR2,0,5);
//        pieces.add(knightR2,0,6);
//        pieces.add(rookR2,0,7);

        //add the board and the pieces to the root
        root = new StackPane();
        root.getChildren().addAll(board,pieces);

        //create lighting scheme
//        PointLight light = new PointLight(Color.GAINSBORO);
//        root.getChildren().add(light);
//        root.getChildren().add(new AmbientLight(Color.WHITE));

        //initialize the camera
        camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);


        //initialize the scene, and set the camera to the scene
        scene = new Scene(root);
        scene.setCamera(camera);

        changeCameraOnClick();

        //show the scene to the user
        stage.setScene(scene);
        stage.show();

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
        final Translate translate1 = new Translate(0, 0, -4000);
        //final Rotate rotate1 = new Rotate(60,Rotate.X_AXIS);

        //camera view 2 for player 2, assuming we are at home
        final Translate translate2 = new Translate(-1000, -1000, -4000);
        //final Rotate rotate2a = new Rotate(180,Rotate.Z_AXIS);
        //final Rotate rotate2b = new Rotate(60,Rotate.X_AXIS);

        Bounds rootBounds = root.localToScene(root.getBoundsInLocal());




        camera.getTransforms().addAll(translate1); //set camera angle


        scene.setOnMouseClicked(event -> {
            //System.out.println("Click!");
            Bounds boundsInScene = camera.localToScene(camera.getBoundsInLocal());
            System.out.printf("X: %f, Y: %f \n", boundsInScene.getCenterX(), boundsInScene.getCenterY());
            System.out.println(CAMERA);

//            try {
                if (CAMERA == "Cam2") { //we are currently player 2, go to player 1
                    camera.getTransforms().addAll(translate2.createInverse());
                    camera.getTransforms().addAll(translate1);
                    CAMERA = "Cam1";

                }
                else if (CAMERA == "Cam1") {
                    camera.getTransforms().addAll(translate1.createInverse());
                    camera.getTransforms().addAll(translate2);
                    CAMERA = "Cam2";
                }
//            } catch (NonInvertibleTransformException e) {
//                System.out.println("Error!!!!");
//                e.printStackTrace();
//            }

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
