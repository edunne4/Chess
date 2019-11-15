package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView extends Application {

    private HBox root;
    private int windowHeight = 750;
    private int windowWidth = 900;

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

        VBox boardCoordContainer = new VBox();
       //add side coords to view
        HBox sideCoordAndBoardContainer = new HBox();
        sideCoordAndBoardContainer.getChildren().add(makeSideBoardCoords());
        sideCoordAndBoardContainer.getChildren().add(board.getPane());
        boardCoordContainer.getChildren().add(makeTopBoardCoords());
        boardCoordContainer.getChildren().add(sideCoordAndBoardContainer);
        root.getChildren().add(boardCoordContainer);



        //make right side, which is a vbox containing importnant information and menus
        //VBox infoMenu = new VBox();
        //root.getChildren().add(infoMenu);


        Scene scene = new Scene(root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public VBox makeSideBoardCoords(){
        VBox sideBoardCoords = new VBox(56);
        sideBoardCoords.setPadding(new Insets(10));
        for (int i = 0; i < 8; i++) {
            Text coord = new Text();
            coord.setFont(new Font(20));
            coord.setText(String.valueOf(i));
            sideBoardCoords.getChildren().add(coord);
        }
        return sideBoardCoords;
    }

    public HBox makeTopBoardCoords(){
        HBox coords = new HBox(67);
        coords.setPadding(new Insets(10));
        Region spacer = new Region();
        spacer.setPrefWidth(0);
        coords.getChildren().add(spacer);
        for (int i = 0; i < 8; i++) {
            Text coord = new Text();
            coord.setFont(new Font(20));
            coord.setText(String.valueOf(i));
            coords.getChildren().add(coord);
        }
        return coords;
    }
}
