//A portion of this code was taken from http://www.interactivemesh.org/models/jfx3dimporter.html
//The camera motion aspects of this code were taken from https://stackoverflow.com/questions/28731460/javafx-moving-3d-objects-with-mouse-on-a-virtual-plane

package View.View3D;

//https://stackoverflow.com/questions/31148690/get-real-position-of-a-node-in-javafx
//https://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column

import Model.ChessBoard;
import Model.ChessPieces.ChessPiece;
import Model.Team;
import View.BoardView;
import View.PieceEnum;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

public class BoardView3D extends BoardView {

//    private final Color PLAYER1_COLOR = Color.WHITE;
//    private final Color PLAYER2_COLOR = Color.RED;
//
//    private final Color SQUARE1_COLOR = Color.BLACK;
//    private final Color SQUARE2_COLOR = Color.WHITE;


     public BoardView3D(ChessBoard modelBoard) {
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

         //setup the board based on the state of the board passed in
         initPiecesFromBoard(modelBoard);



     }

    private void initializeBoardSquares() {
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH ; col++) {
                SquareView3D square;
                if ((col+row) % 2 == 0) {
                    square = new SquareView3D(row, col, getSquare2Color());
                }
                else {
                    square = new SquareView3D(row, col, getSquare1Color());
                }
                this.getChildren().add(square);
            }
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
        squareOnBoard.putPieceOnSquare(pieceType,color);

    }

    @Override
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ChessPiece3D removedPiece = removePieceOnBoard(startRow,startCol);
        putPieceOnBoard(endRow,endCol,removedPiece.getPieceType(),removedPiece.getPieceColor());
    }

//    @Override
//    public void initPieces() {
//        //create the pieces for player1
//        putPieceOnBoard(0,0, PieceEnum.ROOK, PLAYER1_COLOR);
//        putPieceOnBoard(0,1,PieceEnum.KNIGHT, PLAYER1_COLOR);
//        putPieceOnBoard(0,2,PieceEnum.BISHOP, PLAYER1_COLOR);
//        putPieceOnBoard(0,3,PieceEnum.QUEEN, PLAYER1_COLOR);
//        putPieceOnBoard(0,4,PieceEnum.KING, PLAYER1_COLOR);
//        putPieceOnBoard(0,5,PieceEnum.BISHOP, PLAYER1_COLOR);
//        putPieceOnBoard(0,6,PieceEnum.KNIGHT, PLAYER1_COLOR);
//        putPieceOnBoard(0,7,PieceEnum.ROOK, PLAYER1_COLOR);
//        for (int i = 0; i < SIDE_LENGTH; i++) {
//            putPieceOnBoard(1,i,PieceEnum.PAWN, PLAYER1_COLOR);
//        }
//
//        //create the pieces for player 2
//        putPieceOnBoard(7,0,PieceEnum.ROOK, PLAYER2_COLOR);
//        putPieceOnBoard(7,1,PieceEnum.KNIGHT, PLAYER2_COLOR);
//        putPieceOnBoard(7,2,PieceEnum.BISHOP, PLAYER2_COLOR);
//        putPieceOnBoard(7,3,PieceEnum.QUEEN, PLAYER2_COLOR);
//        putPieceOnBoard(7,4,PieceEnum.KING, PLAYER2_COLOR);
//        putPieceOnBoard(7,5,PieceEnum.BISHOP, PLAYER2_COLOR);
//        putPieceOnBoard(7,6,PieceEnum.KNIGHT, PLAYER2_COLOR);
//        putPieceOnBoard(7,7,PieceEnum.ROOK, PLAYER2_COLOR);
//        for (int i = 0; i < SIDE_LENGTH; i++) {
//            putPieceOnBoard(6,i,PieceEnum.PAWN, PLAYER2_COLOR);
//        }
//    }

    @Override
    public void initPiecesFromBoard(ChessBoard modelBoard) {
        // loop through the entire board and create 2D pieces where necessary
        for (int row = 0; row < SIDE_LENGTH; row++) {
            for (int col = 0; col < SIDE_LENGTH; col++) {
                //if the current square has a piece, make a 2D representation of it
                if(!modelBoard.getSquareAt(row, col).isEmpty()){
                    ChessPiece currentPiece = modelBoard.getSquareAt(row, col).getCurrentPiece();
                    //get the correct color from the model
                    Color pieceColor = getPlayer1Color();
                    if(currentPiece.getTeam() == Team.BLACK){ // if piece belongs to player2 (assuming black is player 2)
                        pieceColor = getPlayer2Color(); //use player2 color
                    }
                    //create the 3D piece with using the tye enum and color
                    getSquareAt(row, col).putPieceOnSquare(currentPiece.getPieceType(), pieceColor);

                }
            }

        }
    }

    public SquareView3D getSquareAt(int row, int col){
        return (SquareView3D)this.getChildren().get((7-row) * SIDE_LENGTH + col);
    }
}
