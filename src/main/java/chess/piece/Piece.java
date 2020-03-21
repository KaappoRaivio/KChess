package chess.piece;

import java.util.Objects;

public class Piece {
    public static final Piece NO_PIECE = new Piece(null, Type.NO_PIECE);

    private PieceColor color;
    private Type type;

    public Piece (PieceColor color, Type type) {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor () {
        return color;
    }

    public Type getType () {
        return type;
    }

    public static Piece fromChar (char character) {
        return fromString(String.valueOf(character));
    }

    public static Piece fromString (String string) {
        PieceColor color;
        if (string.toLowerCase().equals(string)) {
            color = PieceColor.WHITE;
        } else {
            color = PieceColor.BLACK;
        }

        return new Piece(color, Type.fromString(string));
    }

    @Override
    public String toString () {
        if (color == PieceColor.WHITE) {
            return type.getRepresentation();
        } else {
            return type.getRepresentation().toUpperCase();
        }
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color &&
                type == piece.type;
    }

    @Override
    public int hashCode () {
        return Objects.hash(color, type);
    }
}
