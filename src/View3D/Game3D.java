package View3D;

import View3D.Board3DView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Game3D extends Application {

    private Board3DView board;

    public static void main(String[] args) {
        launch(args);
    }

    Scene scene;
    Group root;
    private PerspectiveCamera camera;

    private int NUM_ROWS = 8;

    private String CAMERA = "Cam2";

    private final Color BACKGROUND_COLOR = Color.GRAY;

    @Override
    public void start(Stage stage) {

        board = new Board3DView();

        root = new Group();

        root.getChildren().add(board);



        //initialize the camera
        camera = new PerspectiveCamera(true);
        //camera.setVerticalFieldOfView(false);
        camera.setNearClip(1.0);
        camera.setFarClip(10000.0);

        scene = new Scene(root);
        scene.setCamera(camera);
        changeCameraOnClick();



        //show the scene to the user
        stage.setScene(scene);
        scene.setFill(BACKGROUND_COLOR);
        stage.show();

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


}
