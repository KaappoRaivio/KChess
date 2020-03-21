package chess.piece;

import java.util.Arrays;

public enum Type {
    PAWN    (1, "p"),
    KNIGHT  (3, "n"),
    BISHOP  (3, "b"),
    ROOK    (5, "r"),
    QUEEN   (9, "q"),
    KING    (4, "k"),
    NO_PIECE(0, ".");

    private int value;
    private String representation;

    Type (int value, String representation) {
        this.value = value;
        this.representation = representation.toLowerCase();
    }

    public int getValue () {
        return value;
    }

    public String getRepresentation () {
        return representation;
    }

    public static Type fromString (String string) {
        return Arrays.stream(Type.values()).filter((type) -> type.representation.equals(string.toLowerCase())).findFirst().orElseThrow();
    }


}
