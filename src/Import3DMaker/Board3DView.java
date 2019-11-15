//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package Import3DMaker;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private final Rotate rotate = new Rotate(60,Rotate.X_AXIS);
    private final Translate translate = new Translate(0, 0, -2000);

    private volatile boolean isPicking=false;
    private Point3D vecIni, vecPos;
    private double distance;
    private Sphere s;
    private Scene scene;
    private StackPane root;

    private VBox board;
    private VBox pieces;

    private int SQUARE_SIZE = 100;
    private int SQUARE_DEPTH = 10;

    @Override
    public void start(Stage stage) throws IOException {

        //initialize the root
        board = new VBox(SQUARE_SIZE *0.05);
        board.setPadding(new Insets(20));
        board.setAlignment(Pos.CENTER);

        for (int i = 0; i < 8 ; i++) {
            HBox row = new HBox(SQUARE_SIZE *0.05);
            row.setAlignment(Pos.CENTER);
            for (int j = 0; j < 8; j++) {
                Box square = new Box(SQUARE_SIZE, SQUARE_SIZE,SQUARE_DEPTH);

                if ((i+j) % 2 == 0) {
                    square.setMaterial(new PhongMaterial(Color.RED));
                }
                else {
                    square.setMaterial(new PhongMaterial(Color.BLUE));
                }

                row.getChildren().add(square);
            }
            board.getChildren().add(row);

        }

        HBox redPieces1 = new HBox(SQUARE_SIZE*.85);
        //pieces.setPadding(new Insets(20));
        redPieces1.setAlignment(Pos.CENTER);

        //create red pieces
        MeshView kingR = createChessPiece("stl/king.stl",Color.RED);
        MeshView queenR = createChessPiece("stl/queen.stl",Color.RED);
        MeshView rookR1 = createChessPiece("stl/rook.stl",Color.RED);
        MeshView knightR1 = createChessPiece("stl/knight.stl",Color.RED);
        MeshView bishopR1 = createChessPiece("stl/bishop.stl",Color.RED);
        MeshView rookR2 = createChessPiece("stl/rook.stl",Color.RED);
        MeshView knightR2 = createChessPiece("stl/knight.stl",Color.RED);
        MeshView bishopR2 = createChessPiece("stl/bishop.stl",Color.RED);

        redPieces1.getChildren().addAll(rookR1,knightR1,bishopR1,queenR,kingR,bishopR2,knightR2,rookR2);
        //redPieces1.setAlignment(Pos.CENTER);

        HBox redPieces2 = new HBox(SQUARE_SIZE*.85);
        redPieces2.setAlignment(Pos.CENTER);
        for (int i = 0; i < 8; i++) {
            redPieces2.getChildren().add(createChessPiece("stl/pawn.stl",Color.RED));
        }

        pieces = new VBox(SQUARE_SIZE*.85);
        //pieces.setPadding(new Insets(20));
        pieces.setAlignment(Pos.CENTER);
        pieces.getChildren().addAll(redPieces2,redPieces1);
        pieces.setTranslateY(230);

        root = new StackPane();
        root.getChildren().addAll(board,pieces);



        //create lighting scheme
//        PointLight light = new PointLight(Color.GAINSBORO);
//        root.getChildren().add(light);
//        root.getChildren().add(new AmbientLight(Color.WHITE));

        //initialize the camera
        camera = new PerspectiveCamera(false);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll(rotate, translate);

        //initialize the scene, and set the camera to the scene
        scene = new Scene(root);
        scene.setCamera(camera);

        //initialize all of the event handlers to deal with moving the camera
        //moveCameraEvents();

        //show the scene to the user
        stage.setScene(scene);
        stage.show();

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

        int scale = 3;
        meshView.setScaleX(scale);
        meshView.setScaleY(scale);
        meshView.setScaleZ(scale);

        //rotate the mesh
        //meshView.setRotationAxis(Rotate.X_AXIS);
        //meshView.setRotate(90);

        return meshView;
    }


    public static void main(String[] args) {
        launch(args);
    }



}
