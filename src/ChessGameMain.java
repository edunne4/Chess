import Controller.*;
import Model.GameManager;
import View.GameView;
import View3D.Board3DView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessGameMain extends Application {


    private GameManager theModel;
    //private GameView theView;
    private Board3DView theView;
//    private Controller theController;
    private Controller3D theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        theModel = new GameManager();
//        theView  = new GameView(theModel);
        theView  = new Board3DView(theModel);
    }

    @Override
    public void start(Stage primaryStage) {
        theController = new Controller3D(theView,theModel);

        Scene scene = new Scene(new VBox(new SubScene(theView, 400, 400)));
        //scene.setCamera(theView.getCamera());
        System.out.println(scene.toString());

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
