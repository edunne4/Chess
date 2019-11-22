package View3D;

public enum PieceEnum {
    ROOK("stl/rook.stl"),
    KNIGHT("stl/knight.stl"),
    BISHOP("stl/bishop.stl"),
    KING("stl/king.stl"),
    QUEEN("stl/queen.stl"),
    PAWN("stl/pawn.stl");

    private final String fileName;

    PieceEnum(String filename) {
        this.fileName = filename;
    }

    public String getFilename() {
        return fileName;
    }
}

