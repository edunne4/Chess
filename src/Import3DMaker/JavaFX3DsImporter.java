package Import3DMaker;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
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

    @Override
    public void start(Stage stage) throws IOException {
        StlMeshImporter stlImporter = new StlMeshImporter();

        try {
            //stlImporter.read(this.getClass().getResource("Support_Sphere_V2.stl"));
            stlImporter.read(this.getClass().getResource("Knight.stl"));
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
        root.getChildren().add(meshView);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);


        TriangleMesh pyramidMesh = new TriangleMesh();
        pyramidMesh.getTexCoords().addAll(0,0);
        float h = 150;                    // Height
        float s = 300;                    // Side
        pyramidMesh.getPoints().addAll(
                0,    0,    0,            // Point 0 - Top
                0,    h,    -s/2,         // Point 1 - Front
                -s/2, h,    0,            // Point 2 - Left
                s/2,  h,    0,            // Point 3 - Back
                0,    h,    s/2           // Point 4 - Right
        );

        pyramidMesh.getFaces().addAll(
                0,0,  2,0,  1,0,          // Front left face
                0,0,  1,0,  3,0,          // Front right face
                0,0,  3,0,  4,0,          // Back right face
                0,0,  4,0,  2,0,          // Back left face
                4,0,  1,0,  2,0,          // Bottom rear face
                4,0,  3,0,  1,0           // Bottom front face
        );

        MeshView pyramid = new MeshView(pyramidMesh);
        pyramid.setDrawMode(DrawMode.FILL);
        pyramid.setMaterial(new PhongMaterial(Color.RED));
        pyramid.setTranslateX(200);
        pyramid.setTranslateY(100);
        pyramid.setTranslateZ(200);
        //root.getChildren().add(pyramid);

        Scene scene = new Scene(root, 1024, 800);
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }



}
