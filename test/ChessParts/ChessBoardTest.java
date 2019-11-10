package ChessParts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    private ChessBoard board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        System.out.println(board.toString());
    }

    @Test
    void testGetSquareAt() {
        System.out.println(board.getSquareAt(1, 1).getCurrentPiece());
    }
}