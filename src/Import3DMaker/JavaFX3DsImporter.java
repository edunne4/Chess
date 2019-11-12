//Code taken from http://www.interactivemesh.org/models/jfx3dimporter.html

package Import3DMaker;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author $Author: tmartinet$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class JavaFX3DsImporter extends Application {

    private PerspectiveCamera camera;
    private final double sceneWidth = 800;
    private final double sceneHeight = 600;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);

    private volatile boolean isPicking=false;
    private Point3D vecIni, vecPos;
    private double distance;
    private Sphere s;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        StlMeshImporter stlImporter = new StlMeshImporter();

        try {
            //stlImporter.read(this.getClass().getResource("Support_Sphere_V2.stl"));
            stlImporter.read(this.getClass().getResource("king.stl"));
        }
        catch (ImportException e) {
            System.out.println("Error!");
            e.printStackTrace();
            return;
        }

        HBox root = new HBox();

        stlImporter.getImport();
        System.out.println(stlImporter.getImport());
        TriangleMesh mesh = stlImporter.getImport();
        stlImporter.close();
        MeshView meshView = new MeshView(mesh);

        meshView.setMaterial(new PhongMaterial(Color.RED));
        meshView.setDrawMode(DrawMode.FILL);
        meshView.setVisible(true);
        meshView.setScaleX(10);
        meshView.setScaleY(10);
        meshView.setScaleZ(10);

        meshView.setRotationAxis(Rotate.X_AXIS);
        meshView.setRotate(90);


        root.getChildren().add(meshView);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);


        scene = new Scene(root, 1024, 800);
        camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);

        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll (rotateX, rotateY, new Translate(0, 0, -3000));

//        PointLight light = new PointLight(Color.GAINSBORO);
//        root.getChildren().add(light);
//        root.getChildren().add(new AmbientLight(Color.WHITE));

        scene.setCamera(camera);

        eventHandlers();

        stage.setScene(scene);
        stage.show();

    }

    public void eventHandlers() {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            PickResult pr = me.getPickResult();
            if(pr!=null && pr.getIntersectedNode() != null && pr.getIntersectedNode() instanceof Sphere){
                distance=pr.getIntersectedDistance();
                s = (Sphere) pr.getIntersectedNode();
                isPicking=true;
                vecIni = unProjectDirection(mousePosX, mousePosY, scene.getWidth(),scene.getHeight());
            }
        });
        scene.setOnMouseDragged((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            if(isPicking){
                vecPos = unProjectDirection(mousePosX, mousePosY, scene.getWidth(),scene.getHeight());
                Point3D p=vecPos.subtract(vecIni).multiply(distance);
                s.getTransforms().add(new Translate(p.getX(),p.getY(),p.getZ()));
                vecIni=vecPos;
                PickResult pr = me.getPickResult();
                if(pr!=null && pr.getIntersectedNode() != null && pr.getIntersectedNode()==s){
                    distance=pr.getIntersectedDistance();
                } else {
                    isPicking=false;
                }
            } else {
                rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
                rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
            }
        });
        scene.setOnMouseReleased((MouseEvent me)->{
            if(isPicking){
                isPicking=false;
            }
        });
    }

    public Point3D unProjectDirection(double sceneX, double sceneY, double sWidth, double sHeight) {
        double tanHFov = Math.tan(Math.toRadians(camera.getFieldOfView()) * 0.5f);
        Point3D vMouse = new Point3D(tanHFov*(2*sceneX/sWidth-1), tanHFov*(2*sceneY/sWidth-sHeight/sWidth), 1);

        Point3D result = localToSceneDirection(vMouse);
        return result.normalize();
    }

    public Point3D localToScene(Point3D pt) {
        Point3D res = camera.localToParentTransformProperty().get().transform(pt);
        if (camera.getParent() != null) {
            res = camera.getParent().localToSceneTransformProperty().get().transform(res);
        }
        return res;
    }

    public Point3D localToSceneDirection(Point3D dir) {
        Point3D res = localToScene(dir);
        return res.subtract(localToScene(new Point3D(0, 0, 0)));
    }


    public static void main(String[] args) {
        launch(args);
    }



}
