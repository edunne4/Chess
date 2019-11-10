package ChessParts;

public enum Team {
    BLACK("Black"),
    WHITE("White");

    private String stringRepresentation;
    Team(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
