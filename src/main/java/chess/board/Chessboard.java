package chess.board;

import chess.piece.PieceColor;
import chess.piece.Piece;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chessboard {
    private Piece[][] board;
    private PieceColor turn;

    private Chessboard (Piece[][] board, PieceColor turn) {
        this.board = board;
        this.turn = turn;
    }

    public static Chessboard fromFEN (String path) {
        String rawData = readFile(path);
        List<String> split = Arrays.asList(rawData.split(" "));

        String board = split.get(0);
        String turn = split.get(1);
        String castling = split.get(2);
        String enPassant = split.get(3);
        String halfMoveClock = split.get(4);
        String fullmoveNumber = split.get(5);

        return new Chessboard(parseBoard(board), PieceColor.fromString(turn));
    }

    public static Piece[][] parseBoard (String rawBoard) {
        List<String> rows = Arrays.asList(rawBoard.split("/"));
        if (rows.size() != 8) {
            throw new IllegalStateException("Wrong number of rows in FEN " + rawBoard + "!");
        }

        Piece[][] matrix = new Piece[8][8];

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char current = rows.get(y).charAt(x);
                if (Character.isDigit(current)) {
                    int offset = Integer.parseInt(String.valueOf(current));

                    for (int i = 0; i < offset; i++) {
                        matrix[y][x + i] = Piece.NO_PIECE;
                    }

                    x += offset;
                } else {
                    matrix[y][x] = Piece.fromChar(current);
                }
            }
        }

        return matrix;
    }

    public static String readFile (String path) {
        try {
            return Files.lines(Paths.get(path), StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();

        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                result.append(board[y][x]).append(" ");
            }

            result.append("\n");
        }
        return result.toString();
    }

    public static void main (String[] args) {
        System.out.println(Chessboard.fromFEN("/home/kaappo/git/KChess/src/res/boards/start.fen"));
    }
}
