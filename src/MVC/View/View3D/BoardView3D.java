//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package MVC.View.View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import MVC.View.BoardView;
import MVC.View.PieceEnum;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class BoardView3D extends BoardView {

    private final Color PLAYER1_COLOR = Color.WHITE;
    private final Color PLAYER2_COLOR = Color.RED;

    private final Color SQUARE1_COLOR = Color.BLACK;
    private final Color SQUARE2_COLOR = Color.WHITE;


     public BoardView3D() {
         super();

         //initialize the board
         this.setPrefColumns(SIDE_LENGTH);
         //this.setPrefSize(SIDE_LENGTH*SquareView3D.SQUARE_SIZE,SIDE_LENGTH*SquareView3D.SQUARE_SIZE);
         this.setAlignment(Pos.CENTER);

         //center the board in the view
//         this.setTranslateX(SquareView3D.SQUARE_SIZE*-SIDE_LENGTH/2);
//         this.setTranslateY(SquareView3D.SQUARE_SIZE*-SIDE_LENGTH/2);

         //add all of the squares to the board
         initializeBoardSquares();

         //setup the board in a traditional chess fashion
         initPieces();//initializeBoard();

         //TODO - remove these tests
         //removePieceOnBoard(0,0);
         //movePiece(1,4,3,4);


     }

    private void initializeBoardSquares() {
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH ; col++) {
                SquareView3D square;
                if ((col+row) % 2 == 0) {
                    square = new SquareView3D(row, col, SQUARE2_COLOR);
                }
                else {
                    square = new SquareView3D(row, col, SQUARE1_COLOR);
                }
                this.getChildren().add(square);
            }
        }
    }

    private void initializeBoard() {
        //create the pieces for player1
        putPieceOnBoard(0,0, PieceEnum.ROOK, PLAYER1_COLOR);
        putPieceOnBoard(0,1,PieceEnum.KNIGHT, PLAYER1_COLOR);
        putPieceOnBoard(0,2,PieceEnum.BISHOP, PLAYER1_COLOR);
        putPieceOnBoard(0,3,PieceEnum.QUEEN, PLAYER1_COLOR);
        putPieceOnBoard(0,4,PieceEnum.KING, PLAYER1_COLOR);
        putPieceOnBoard(0,5,PieceEnum.BISHOP, PLAYER1_COLOR);
        putPieceOnBoard(0,6,PieceEnum.KNIGHT, PLAYER1_COLOR);
        putPieceOnBoard(0,7,PieceEnum.ROOK, PLAYER1_COLOR);
        for (int i = 0; i < SIDE_LENGTH; i++) {
            putPieceOnBoard(1,i,PieceEnum.PAWN, PLAYER1_COLOR);
        }

        //create the pieces for player 2
        putPieceOnBoard(7,0,PieceEnum.ROOK, PLAYER2_COLOR);
        putPieceOnBoard(7,1,PieceEnum.KNIGHT, PLAYER2_COLOR);
        putPieceOnBoard(7,2,PieceEnum.BISHOP, PLAYER2_COLOR);
        putPieceOnBoard(7,3,PieceEnum.QUEEN, PLAYER2_COLOR);
        putPieceOnBoard(7,4,PieceEnum.KING, PLAYER2_COLOR);
        putPieceOnBoard(7,5,PieceEnum.BISHOP, PLAYER2_COLOR);
        putPieceOnBoard(7,6,PieceEnum.KNIGHT, PLAYER2_COLOR);
        putPieceOnBoard(7,7,PieceEnum.ROOK, PLAYER2_COLOR);
        for (int i = 0; i < SIDE_LENGTH; i++) {
            putPieceOnBoard(6,i,PieceEnum.PAWN, PLAYER2_COLOR);
        }
    }

//    public void movePieceOnBoard(int startingCol,int startingRow,int endingCol,int endingRow) {
//        ChessPiece3D removedPiece = removePieceOnBoard(startingCol,startingRow);
//        createPieceOnBoard(endingCol,endingRow,removedPiece.getPieceType(),removedPiece.getPieceColor());
//    }

    public ChessPiece3D removePieceOnBoard(int row, int col) {
        //get the square we are looking to remove a piece from
        SquareView3D squareOnBoard = this.getSquareAt(row, col);

        //remove the piece
        ChessPiece3D removedPiece = squareOnBoard.removePieceFromSquare();

        return removedPiece;

    }

    public void putPieceOnBoard(int row, int col, PieceEnum pieceType, Color color) {
        //get the square we are looking to add a piece to
        SquareView3D squareOnBoard = (SquareView3D) this.getSquareAt(row, col);

        //add the piece
        squareOnBoard.putPieceToSquare(pieceType,color);

    }

    @Override
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece3D removedPiece = removePieceOnBoard(startRow,startCol);
        putPieceOnBoard(endRow,endCol,removedPiece.getPieceType(),removedPiece.getPieceColor());
    }

    @Override
    public void initPieces() {
        initializeBoard();
    }

    public SquareView3D getSquareAt(int row, int col){
        return (SquareView3D)this.getChildren().get((7-row) * SIDE_LENGTH + col);
    }
}
