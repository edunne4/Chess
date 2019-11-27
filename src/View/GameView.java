package View;

import Model.GameManager;
import View3D.BoardView3D;
import javafx.geometry.Insets;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView {

    private HBox root;
    VBox rightSideContainer;
    private int windowHeight = 750;
    private int windowWidth = 900;
//    BoardView2D board;
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
        board = new BoardView3D();//new BoardView2D(640);
        //board.initPieces();//board.initBoard();
        //add side coords to view
        VBox boardCoordContainer = new VBox();
        HBox sideCoordAndBoardContainer = new HBox();
        sideCoordAndBoardContainer.getChildren().add(makeSideBoardCoords());
        //add the actual board in
        sideCoordAndBoardContainer.getChildren().add(new SubScene(board, 640,640));
        boardCoordContainer.getChildren().add(makeTopBoardCoords());
        boardCoordContainer.getChildren().add(sideCoordAndBoardContainer);
        //put this whole shabang in the root, an HBox
        root.getChildren().add(boardCoordContainer);

        //these four lines demonstrate some board methods
        /*
        board.movePiece(0,0,4,5);
        board.getSquare(0,2).highlight();
        board.getSquare(0,2).unHighlight();
        board.getSquare(4,5).highlight();
        */
        rightSideContainer = new VBox();
        createDeadPieceHolders();
        root.getChildren().add(rightSideContainer);

        //demonstrating killPiece method
        /*
        killPiece(0,4,deadPieceHolderWhite);
        killPiece(0,5,deadPieceHolderWhite);
        killPiece(0,6,deadPieceHolderWhite);
        killPiece(0,7,deadPieceHolderWhite);
        killPiece(1,4,deadPieceHolderWhite);
        killPiece(1,5,deadPieceHolderWhite);
        killPiece(7,4,deadPieceHolderBlack);
        killPiece(7,7,deadPieceHolderBlack);
        */
    }

    private void createDeadPieceHolders() {
        //make right side, which is a vbox containing importnant information and menus
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

    public void killPiece(int row, int col, FlowPane deadPieceHolder){
        SquareView2D oldLocationSquare = (SquareView2D)board.getSquareAt(row,col);
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
