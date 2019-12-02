package View;

public enum PieceEnum {
    ROOK("View/View2D/chessPiecesPNG/rook.png","stlModels/rook.stl"),
    KNIGHT("View/View2D/chessPiecesPNG/knight.png","stlModels/knight.stl"),
    BISHOP("View/View2D/chessPiecesPNG/bishop.png","stlModels/bishop.stl"),
    KING("View/View2D/chessPiecesPNG/king.png","stlModels/king.stl"),
    QUEEN("View/View2D/chessPiecesPNG/queen.png","stlModels/queen.stl"),
    PAWN("View/View2D/chessPiecesPNG/pawn.png","stlModels/pawn.stl");

    private final String fileName2D;
    private final String fileName3D;

    PieceEnum(String filename2D, String fileName3D) {
        this.fileName2D = filename2D;
        this.fileName3D = fileName3D;
    }

    public String getFilename2D() {
        return fileName2D;
    }

    public String getFileName3D(){
        return fileName3D;
    }
}

