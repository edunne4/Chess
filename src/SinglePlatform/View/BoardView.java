package SinglePlatform.View;


import javafx.scene.layout.TilePane;

public abstract class BoardView extends TilePane {

    protected static final int SIDE_LENGTH = 8;


    public abstract SquareView getSquareAt(int row, int col);

    public abstract void movePiece(int startRow, int startCol, int endRow, int endCol);

    public abstract void initPieces();
}
