package View3D;

public enum PieceEnum {
    ROOK("stlModels/rook.stl"),
    KNIGHT("stlModels/knight.stl"),
    BISHOP("stlModels/bishop.stl"),
    KING("stlModels/king.stl"),
    QUEEN("stlModels/queen.stl"),
    PAWN("stlModels/pawn.stl");

    private final String fileName;

    PieceEnum(String filename) {
        this.fileName = filename;
    }

    public String getFilename() {
        return fileName;
    }
}

