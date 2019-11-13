import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class BoardView extends Application {

    private int windowHeight = 640;
    private int windowWidth = 840;
    private final int SIDE_LENGTH = 8;
    private final String LIGHT_COLOR_HEX = "#ede8da";
    private final String DARK_COLOR_HEX = "#949085";


    HBox root;
    GridPane board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //make root which is a set of horizontal boxes
        root = new HBox();
        //root.setAlignment(Pos.CENTER);
        root.setMinSize(800,600);

        //make the board, which is a grid pane on the left
        makeBoard();

        //example of putting piece image onto game board

        //grab the square to put the image in
        ObservableList<Node> squares = board.getChildren();
        StackPane theSquare = (StackPane)squares.get(27);
        //add the image to the square
        theSquare.getChildren().add(new PieceView("white_rook.png"));

        //add the board to the view
        root.getChildren().add(board);

        //make right side, which is a vbox containing importnant information and menus
        //VBox infoMenu = new VBox();
        //root.getChildren().add(infoMenu);


        Scene scene = new Scene(root);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * creates the chessboard grid -  a GridPane with each square as a StackPane
     *
     * @author Jake
     * @see <href="https://stackoverflow.com/questions/24082063/chessboard-with-automatic-resizing">
     */
    public void makeBoard(){
        //the board will be a grid of squares
        board = new GridPane();
        board.setPrefSize(windowHeight,windowHeight);
        //for every one of the 64 squares
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                //make it a stack pane. it will have a color background and the piece can be placed on top of it
                StackPane square = new StackPane();
                square.setPrefSize(windowHeight/SIDE_LENGTH, windowHeight/SIDE_LENGTH);

                //make labels for squares on edges
                if(row == 0){
                    Label label = new Label(String.valueOf(col));
                    square.setAlignment(Pos.TOP_CENTER);
                    square.getChildren().add(label);
                }
                if(col == 0){
                    Label label = new Label(String.valueOf(row));
                    square.setAlignment(Pos.CENTER_LEFT);
                    square.getChildren().add(label);
                }


                square.setAlignment(Pos.CENTER); // make sure piece will be put in center


                //pick color of square
                String squareColor;
                if ((row + col) % 2 == 0) {
                    squareColor = LIGHT_COLOR_HEX;
                } else {
                    squareColor = DARK_COLOR_HEX;
                }
                //set square color
                square.setStyle("-fx-background-color: " + squareColor);
                //add square to the board

                board.add(square, col, row);


            }
        }
        //TODO bind board height to root window height - in controller?
        //TODO bind board width to board height - in controller?
    }
}
