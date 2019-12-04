package View;

import Model.GameManager;
import View.BoardView;
import View.View2D.BoardView2D;
import View.View2D.SquareView2D;
import View.View3D.BoardView3D;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameView {

    //TODO - change this
    private boolean is3D = false;

    private HBox root;
    VBox rightSideContainer;
    private int windowHeight = 750;
    private int windowWidth = 900;
    BoardView board;
    private FlowPane deadPieceHolderWhite;
    private FlowPane deadPieceHolderBlack;
    GameManager gm;
    Text inCheckTextBlack;
    Text inCheckTextWhite;

    public GameView(GameManager model) {
        this.gm = model;
        //make root which is a set of horizontal boxes
        root = new HBox();
        root.setSpacing(40);
        root.setPadding(new Insets(20));
        //root.setAlignment(Pos.CENTER);
        root.setMinSize(windowWidth,windowHeight);


        //***************************************************************
        //Background stuff
        //TODO - find a different background texture
        String imageLink = "https://images.freecreatives.com/wp-content/uploads/2016/01/Free-Photoshop-Purity-Wood-Texture.jpg";//"https://images.freecreatives.com/wp-content/uploads/2016/01/High-Quality-Oak-Seamless-Wood-Texture.jpg";//"https://tr.rbxcdn.com/7324f5e7134f93c9c9e41e30c4d5bb0a/420/420/Decal/Png";
        BackgroundImage bgImage = new BackgroundImage(new Image(imageLink), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(bgImage);
        root.setBackground(background);
        //***************************************************************

        //add side coords to view
        VBox boardCoordContainer = new VBox();
        HBox sideCoordAndBoardContainer = new HBox();
        //sideCoordAndBoardContainer.getChildren().add(makeSideBoardCoords());


        if(is3D) {
            board = new BoardView3D();
            //***********************************
            //Right now this stuff is only for 3D
            Group miniRoot = new Group();

            //initialize the camera
            PerspectiveCamera camera = new PerspectiveCamera(true);
            //camera.setVerticalFieldOfView(false);
            camera.setNearClip(1.0);
            camera.setFarClip(10000.0);

            SubScene boardScene = new SubScene(miniRoot, 640, 640);
            //SubScene boardScene = new SubScene(board, 640,640);
            boardScene.setCamera(camera);
            boardScene.setFill(Color.GRAY);
            miniRoot.getChildren().add(board);

            sideCoordAndBoardContainer.getChildren().add(boardScene);
            //***********************************
        }else {
            board = new BoardView2D(640);
            //add the actual board in
            sideCoordAndBoardContainer.getChildren().add(board);
        }
        //boardCoordContainer.getChildren().add(makeTopBoardCoords());
        boardCoordContainer.getChildren().add(sideCoordAndBoardContainer);
        //put this whole shabang in the root, an HBox
        root.getChildren().add(boardCoordContainer);


        rightSideContainer = new VBox();
        createDeadPieceHolders();
        makeInCheckText();
        root.getChildren().add(rightSideContainer);

    }

    /**Creates 2 flowpanes
     * Each flowpane contains the dead/captured pieces for each team
     * Adds each flowpane to the rightside Container
     */
    private void createDeadPieceHolders() {

        //make the flowpanes for dead pieces
        deadPieceHolderWhite = new FlowPane();
        deadPieceHolderBlack = new FlowPane();
        Text deadPieceHolderWhiteName = new Text();
        Text deadPieceHolderBlackName = new Text();
        deadPieceHolderBlackName.setFont(new Font(20));
        deadPieceHolderWhiteName.setFont(new Font(20));
        deadPieceHolderBlackName.setText("Captured Black Pieces:");
        deadPieceHolderWhiteName.setText("Captured White Pieces:");

        rightSideContainer.getChildren().add(deadPieceHolderWhiteName);
        rightSideContainer.getChildren().add(deadPieceHolderWhite);
        rightSideContainer.getChildren().add(deadPieceHolderBlackName);
        rightSideContainer.getChildren().add(deadPieceHolderBlack);
    }

//
//    /**
//     * makes the side board coords
//     * @return a Vbox containing the coords
//     */
//    public VBox makeSideBoardCoords(){
//        VBox sideBoardCoords = new VBox(56);
//        sideBoardCoords.setPadding(new Insets(10));
//        for (int i = 0; i < 8; i++) {
//            Text coord = new Text();
//            coord.setFont(new Font(20));
//            coord.setText(String.valueOf(i));
//            sideBoardCoords.getChildren().add(coord);
//        }
//        return sideBoardCoords;
//    }
//
//
//    /**
//     * makes the top board coords
//     * @return a Hbox containing the coords
//     */
//    public HBox makeTopBoardCoords(){
//        HBox coords = new HBox(67);
//        coords.setPadding(new Insets(10));
//        Region spacer = new Region();
//        spacer.setPrefWidth(0);
//        coords.getChildren().add(spacer);
//        for (int i = 0; i < 8; i++) {
//            Text coord = new Text();
//            coord.setFont(new Font(20));
//            coord.setText(String.valueOf(i));
//            coords.getChildren().add(coord);
//        }
//        return coords;
//    }
//
    //TODO change this up probably
    /**
     * grabs the piece image at the specified spot and puts it in its respective deadpiece holder depending on team
     * @param row the row of the square
     * @param col the coumn of the square
     * @param deadPieceHolder the correct team's dead piece holder
     */
    public void killPiece(int row, int col, FlowPane deadPieceHolder){
        if(!is3D){
            SquareView2D oldLocationSquare = (SquareView2D)board.getSquareAt(row,col);
            deadPieceHolder.getChildren().add(oldLocationSquare.getPiece());
            oldLocationSquare.getChildren().clear();
        } else {

        }
    }

    /**
     * Makes text object for showing if a player is in check
     */
    private void makeInCheckText() {
        inCheckTextBlack = new Text();
        inCheckTextWhite = new Text();
        inCheckTextBlack.setFont(new Font(40));
        inCheckTextWhite.setFont(new Font(40));
        inCheckTextBlack.setFill(Color.RED);
        inCheckTextWhite.setFill(Color.RED);
        inCheckTextBlack.setText("");
        inCheckTextWhite.setText("");
        rightSideContainer.getChildren().add(inCheckTextBlack);
        rightSideContainer.getChildren().add(inCheckTextWhite);
    }

    public Text getInCheckTextBlack() {
        return inCheckTextBlack;
    }

    public Text getInCheckTextWhite() {
        return inCheckTextWhite;
    }

    public BoardView getBoard() {
        return board;
    }

    public HBox getRoot() {
        return root;
    }

    public FlowPane getDeadPieceHolderWhite() {
        return deadPieceHolderWhite;
    }

    public FlowPane getDeadPieceHolderBlack() {
        return deadPieceHolderBlack;
    }
}
