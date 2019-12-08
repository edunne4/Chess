package MVC.View;


import ChessParts.ChessBoard;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public abstract class BoardView extends TilePane {

    protected static final int SIDE_LENGTH = 8;

    private Color player1Color = Color.WHITE;
    private Color player2Color = Color.BLACK;

    private static Color square1Color = Color.BEIGE;//new Color(237, 232, 218, 1);
    private static Color square2Color = Color.BROWN.darker();//new Color(148, 144, 133, 1);
    private static Color square1Highlight = Color.LIGHTGREEN.brighter();//new Color(178, 237, 179, 1);
    private static Color square2Highlight = Color.LIGHTGREEN.darker();//new Color(123, 189, 123, 1);


    public abstract SquareView getSquareAt(int row, int col);

    public abstract void movePiece(int startRow, int startCol, int endRow, int endCol);


    public abstract void initPiecesFromBoard(ChessBoard modelBoard);

    public Color getPlayer1Color() {
        return player1Color;
    }

    public Color getPlayer2Color() {
        return player2Color;
    }

    public static Color getSquare1Color() {
        return square1Color;
    }

    public static Color getSquare2Color() {
        return square2Color;
    }

    public static Color getSquare1Highlight() {
        return square1Highlight;
    }

    public static Color getSquare2Highlight() {
        return square2Highlight;
    }
}
