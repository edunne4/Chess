package ChessParts;

/**
 * An enum used to differentiate players by team. White always goes first.
 */
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

    public Team getOtherTeam(){
        if(this == BLACK){return WHITE;}
        else{return BLACK;}
    }
}
