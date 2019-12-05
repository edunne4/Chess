import Controller.*;
import Model.GameManager;
import View.GameMenuBar;
import View.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class ChessGameMain extends Application {


    private GameManager theModel;
    private GameView theView;
    //private BoardView3D theView;
    private Controller theController;
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
    public void start(Stage primaryStage) {
        theController = new Controller(theView,theModel);

        Scene scene = new Scene(theView.getRoot());
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
