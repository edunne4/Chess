//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import View.BoardView;
import View.SquareView;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class BoardView3D extends BoardView {

    private int NUM_ROWS = 8;

    private final Color PLAYER1_COLOR = Color.RED;
    private final Color PLAYER2_COLOR = Color.WHITE;

    private final Color SQUARE1_COLOR = Color.BLACK;
    private final Color SQUARE2_COLOR = Color.WHITE;


     public BoardView3D() {
         super();

         //initialize the board
         this.setPrefColumns(NUM_ROWS);
         this.setAlignment(Pos.CENTER);

         //center the board in the view
         this.setTranslateX(SquareView3D.SQUARE_SIZE*-4);
         this.setTranslateY(SquareView3D.SQUARE_SIZE*-4);

         //add all of the squares to the board
         initializeBoardSquares();

         //setup the board in a traditional chess fashion
         initPieces();//initializeBoard();

         //TODO - remove these tests
         removePieceOnBoard(0,0);
         movePiece(1,4,3,4);


     }

    private void initializeBoardSquares() {
        for (int col = 0; col < 8 ; col++) {
            for (int row = 0; row < 8; row++) {
                SquareView3D square;
                if ((col+row) % 2 == 0) {
                    square = new SquareView3D(row, col, SQUARE1_COLOR);
                }
                else {
                    square = new SquareView3D(row, col, SQUARE2_COLOR);
                }
                this.getChildren().add(square);
            }
        }
    }

    private void initializeBoard() {
        //create the pieces for player1
        createPieceOnBoard(0,0, PieceEnum.ROOK, PLAYER1_COLOR);
        createPieceOnBoard(1,0,PieceEnum.KNIGHT, PLAYER1_COLOR);
        createPieceOnBoard(2,0,PieceEnum.BISHOP, PLAYER1_COLOR);
        createPieceOnBoard(3,0,PieceEnum.KING, PLAYER1_COLOR);
        createPieceOnBoard(4,0,PieceEnum.QUEEN, PLAYER1_COLOR);
        createPieceOnBoard(5,0,PieceEnum.BISHOP, PLAYER1_COLOR);
        createPieceOnBoard(6,0,PieceEnum.KNIGHT, PLAYER1_COLOR);
        createPieceOnBoard(7,0,PieceEnum.ROOK, PLAYER1_COLOR);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,1,PieceEnum.PAWN, PLAYER1_COLOR);
        }

        //create the pieces for player 2
        createPieceOnBoard(0,7,PieceEnum.ROOK, PLAYER2_COLOR);
        createPieceOnBoard(1,7,PieceEnum.KNIGHT, PLAYER2_COLOR);
        createPieceOnBoard(2,7,PieceEnum.BISHOP, PLAYER2_COLOR);
        createPieceOnBoard(3,7,PieceEnum.KING, PLAYER2_COLOR);
        createPieceOnBoard(4,7,PieceEnum.QUEEN, PLAYER2_COLOR);
        createPieceOnBoard(5,7,PieceEnum.BISHOP, PLAYER2_COLOR);
        createPieceOnBoard(6,7,PieceEnum.KNIGHT, PLAYER2_COLOR);
        createPieceOnBoard(7,7,PieceEnum.ROOK, PLAYER2_COLOR);
        for (int i = 0; i < 8; i++) {
            createPieceOnBoard(i,6,PieceEnum.PAWN, PLAYER2_COLOR);
        }
    }

//    public void movePieceOnBoard(int startingCol,int startingRow,int endingCol,int endingRow) {
//        ChessPiece3D removedPiece = removePieceOnBoard(startingCol,startingRow);
//        createPieceOnBoard(endingCol,endingRow,removedPiece.getPieceType(),removedPiece.getPieceColor());
//    }

    public ChessPiece3D removePieceOnBoard(int col, int row) {
        //get the square we are looking to remove a piece from
        SquareView3D squareOnBoard = (SquareView3D) this.getChildren().get(col + row*NUM_ROWS);

        //remove the piece
        ChessPiece3D removedPiece = squareOnBoard.removePieceFromSquare();

        return removedPiece;

    }

    public void createPieceOnBoard(int col, int row, PieceEnum pieceType, Color color) {
        //get the square we are looking to add a piece to
        SquareView3D squareOnBoard = (SquareView3D) this.getChildren().get(col + row*NUM_ROWS);

        //add the piece
        squareOnBoard.addPieceToSquare(pieceType,color);

    }

    @Override
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece3D removedPiece = removePieceOnBoard(startCol,startRow);
        createPieceOnBoard(endCol,endRow,removedPiece.getPieceType(),removedPiece.getPieceColor());
    }

    @Override
    public void initPieces() {
        initializeBoard();
    }

    public SquareView getSquareAt(int row, int col){
        return (SquareView)this.getChildren().get((7-row) * 8 + col);
    }
}
