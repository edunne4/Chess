package SinglePlatform;

import Networking.NController.NController;
import SinglePlatform.Controller.*;
import SinglePlatform.Model.GameManager;
import SinglePlatform.View.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChessGameMainNet2 extends Application {


    private GameManager theModel;
    private GameView theView;
    //private BoardView3D theView;
    private NController theController;
    //private Controller3D theController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        theModel = new GameManager();
        theView  = new GameView(theModel);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        theController = new NController(theView,theModel, false);

        //MenuBar menuBar = new MenuBar();
        //GameMenu gameMenuBar = new GameMenu();

        Scene scene = new Scene(theView.getRoot());
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        theController.makeConnection();
    }
}
