package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameView extends Application {

    private HBox root;
    private int windowHeight = 700;
    private int windowWidth = 840;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //make root which is a set of horizontal boxes
        root = new HBox();
        //root.setAlignment(Pos.CENTER);
        root.setMinSize(windowWidth,windowHeight);

        //make the board, which is a grid pane on the left
       BoardView board = new BoardView(640);
       board.initBoard();
       board.movePiece(0,0,4,5);
       board.movePiece(1,0,0,0);


        //add the board to the view
        root.getChildren().add(board.getPane());

        //make right side, which is a vbox containing importnant information and menus
        //VBox infoMenu = new VBox();
        //root.getChildren().add(infoMenu);


        Scene scene = new Scene(root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
