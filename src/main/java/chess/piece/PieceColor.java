package chess.piece;

public enum PieceColor {
    WHITE, BLACK;

    public static PieceColor fromString (String string) {
        if (string.toLowerCase().equals("w")) {
            return WHITE;
        } else if (string.toLowerCase().equals("b")) {
            return BLACK;
        } else {
            throw new IllegalArgumentException("Unknown string " + string + "!");
        }
    }
}
