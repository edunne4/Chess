package View;

import Model.GameManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameView {

    private HBox root;
    VBox rightSideContainer;
    private int windowHeight = 750;
    private int windowWidth = 900;
    BoardView board;
    private FlowPane deadPieceHolderWhite;
    private FlowPane deadPieceHolderBlack;
    GameManager gm;

    public GameView(GameManager model) {
        this.gm = model;
        //make root which is a set of horizontal boxes
        root = new HBox();
        //root.setAlignment(Pos.CENTER);
        root.setMinSize(windowWidth,windowHeight);

        //make the board, which is a grid pane on the left
        board = new BoardView(640);
        board.initBoard();
        //add side coords to view
        VBox boardCoordContainer = new VBox();
        HBox sideCoordAndBoardContainer = new HBox();
        sideCoordAndBoardContainer.getChildren().add(makeSideBoardCoords());
        //add the actual board in
        sideCoordAndBoardContainer.getChildren().add(board);
        boardCoordContainer.getChildren().add(makeTopBoardCoords());
        boardCoordContainer.getChildren().add(sideCoordAndBoardContainer);
        //put this whole shabang in the root, an HBox
        root.getChildren().add(boardCoordContainer);


        rightSideContainer = new VBox();
        createDeadPieceHolders();
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
        deadPieceHolderBlackName.setText("Captured White Pieces:");
        deadPieceHolderWhiteName.setText("Captured Black Pieces:");

        rightSideContainer.getChildren().add(deadPieceHolderBlackName);
        rightSideContainer.getChildren().add(deadPieceHolderBlack);
        rightSideContainer.getChildren().add(deadPieceHolderWhiteName);
        rightSideContainer.getChildren().add(deadPieceHolderWhite);
    }

    /**
     * makes the side board coords
     * @return a Vbox containing the coords
     */
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


    /**
     * makes the top board coords
     * @return a Hbox containing the coords
     */
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

    /**
     * grabs the piece image at the specified spot and puts it in its respective deadpiece holder depending on team
     * @param row the row of the square
     * @param col the coumn of the square
     * @param deadPieceHolder the correct team's dead piece holder
     */
    public void killPiece(int row, int col, FlowPane deadPieceHolder){
        SquareView oldLocationSquare = (SquareView)board.getSquare(row,col);
        deadPieceHolder.getChildren().add(oldLocationSquare.getPiece());
        oldLocationSquare.getChildren().clear();
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
